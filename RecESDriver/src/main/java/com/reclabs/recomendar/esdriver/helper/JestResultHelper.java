/**
 * Project: RecESDriver
 * Created by: raulanatol at 28/04/13 11:51
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.esdriver.helper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.reclabs.recomendar.common.exceptions.RecIllegalArgumentException;
import com.reclabs.recomendar.common.helpers.types.StringHelper;
import com.reclabs.recomendar.common.types.RecMoney;
import com.reclabs.recomendar.esdriver.helper.serializers.RecMoneyDeserializer;
import io.searchbox.client.JestResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author raulanatol
 */
public class JestResultHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(JestResultHelper.class);

    public static final String ES_METADATA_ID = "es_metadata_id";

    public static <T> Collection<T> getSourceAsObjectListByField(JestResult jestResult, Class<T> resultClass, String fieldName) {
        List<Object> result = new ArrayList<>();
        List<Object> sourceList = (List<Object>) extractSource(jestResult.getJsonMap(), jestResult.getPathToResult());
        for (Object source : sourceList) {
            Object obj = createSourceObject(source, resultClass, fieldName);
            if (obj != null) {
                result.add(obj);
            }
        }
        return (Collection<T>) result;
    }

    /**
     * @param pathToResult
     * @return
     */
    private static String[] getKeys(String pathToResult) {
        return pathToResult == null ? null : (pathToResult + "").split("/");
    }

    /**
     * @param jsonMap
     * @return
     */
    private static Object extractSource(Map jsonMap, String pathToResult) {
        List<Object> sourceList = new ArrayList<>();
        if (jsonMap == null) return sourceList;
        String[] keys = getKeys(pathToResult);
        if (keys == null) {
            sourceList.add(jsonMap);
            return sourceList;
        }
        String sourceKey = keys[keys.length - 1];
        Object obj = jsonMap.get(keys[0]);
        if (keys.length > 1) {
            for (int i = 1; i < keys.length - 1; i++) {
                obj = ((Map) obj).get(keys[i]);
            }
            if (obj instanceof Map) {
                Map<String, Object> source = (Map<String, Object>) ((Map) obj).get(sourceKey);
                if (source != null) sourceList.add(source);
            } else if (obj instanceof List) {
                for (Object newObj : (List) obj) {
                    if (newObj instanceof Map) {
                        Map<String, Object> source = (Map<String, Object>) ((Map) newObj).get(sourceKey);
                        if (source != null) {
                            source.put(ES_METADATA_ID, ((Map) newObj).get("_id"));
                            sourceList.add(source);
                        }
                    }
                }
            }
        } else {
            if (obj != null) {
                sourceList.add(obj);
            }
        }
        return sourceList;
    }

    /**
     * @param source
     * @param type
     * @param <T>
     * @return
     */
    private static <T> T createSourceObject(Object source, Class<?> type, String fieldName) {
        if (source instanceof Map) {
            Object result = ((Map) source).get(fieldName);
            return (T) result;
        }
        throw new RecIllegalArgumentException("fieldName of type is invalid: " + fieldName + " " + type);
    }

    /**
     * @param result
     * @param resultClass
     * @return
     */
    public static <T> Collection<T> getSourceAsObjectList(JestResult result, Class<T> resultClass) {
        List<Object> objectList = new ArrayList<>();
        List<Object> sourceList = (List<Object>) extractSource(result.getJsonMap(), result.getPathToResult());
        for (Object source : sourceList) {
            Object obj = createSourceObject(source, resultClass);
            if (obj != null) objectList.add(obj);
        }
        return (Collection<T>) objectList;
    }

    /**
     * @param source
     * @param type
     * @param <T>
     * @return
     */
    private static <T> T createSourceObject(Object source, Class<?> type) {
        Object obj = null;
        try {
            if (source instanceof Map) {
                GsonBuilder builder = new GsonBuilder();
                builder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                builder.registerTypeAdapter(RecMoney.class, new RecMoneyDeserializer());
                Gson gson = builder.create();
                String json = gson.toJson(source, Map.class);
                obj = gson.fromJson(json, type);
            } else {
                obj = type.cast(source);
            }

            //Fijamos la id
            Field[] fields = type.getDeclaredFields();
            for (Field field : fields) {
                if (StringHelper.equals(field.getName(), "id")) {
                    try {
                        field.setAccessible(true);
                        Object value = field.get(obj);
                        if (value == null) {
                            field.set(obj, ((Map) source).get(ES_METADATA_ID));
                        }
                    } catch (IllegalAccessException e) {
                        LOGGER.error("Unhandled exception occurred while getting annotated id from source");
                    }
                    break;
                }
            }

        } catch (Exception e) {
            LOGGER.error("Unhandled exception occurred while converting source to the object ." + type.getCanonicalName(), e);
        }
        return (T) obj;
    }
}

