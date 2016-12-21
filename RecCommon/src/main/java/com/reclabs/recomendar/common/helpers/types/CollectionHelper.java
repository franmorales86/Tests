/**
 * Project: RecCommon
 * Created by: fjmorales at 04/12/2012 19:05:03
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.common.helpers.types;

import com.reclabs.recomendar.common.exceptions.RecIllegalArgumentException;
import com.reclabs.recomendar.common.helpers.ParameterHelper;

import java.util.*;

/**
 * Helper para funciones comunes con el trato de elementos de tipo Collection
 * @author rmorales
 */
public class CollectionHelper {
    /**
     * Indica si el collection pasado por parámetros está vacío o no. Vacio: == null o de tamaño 0
     * @param collection The collection
     * @return Indica la coleccion es vacia
     */
    public static boolean isEmpty(final Collection<?> collection) {
        return (collection == null || collection.size() <= 0);
    }

    /**
     * Dado un listado de strings obtenemos una cadena separada por un separador indicado
     * @param separator The separator
     * @param items The items
     * @return String concatenado resultante
     */
    public static String list2String(final String separator, final Collection<String> items) {
        StringBuffer result = new StringBuffer();
        for (String item : items) {
            if (item != null) {
                result.append(item);
                result.append(separator);
            }
        }
        return (result.length() == 0) ? "" : result.substring(0, result.length() - separator.length());
    }

    /**
     * Dado un listado de strings obtenemos una cadena separada por un separador indicado
     * <b>separator</b> y poniendo antes y después de cada elemento del collection el texto indicado
     * en <b>startElementString</b> y <b>endElementString</b>
     * @param separator
     * @param items
     * @param startElementString
     * @param endElementString
     * @return String concatenado resultante
     */
    public static String list2String(final String separator, final Collection<String> items, final String startElementString, final String endElementString) {
        List<String> preparedItems = new ArrayList<>();
        for (String item : items) {
            if (!StringHelper.isEmpty(item)) {
                preparedItems.add(startElementString + item + endElementString);
            }
        }
        return list2String(separator, preparedItems);
    }

    /**
     * @param separator
     * @param items
     * @param startElementString
     * @param endElementString
     * @param limit
     * @return String concatenado resultante
     */
    public static List<String> list2String(final String separator, final Collection<String> items, final String startElementString, final String endElementString, final int limit) {
        List<String> result = new ArrayList<>();
        int counter = 0;
        List<String> preparedItems = new ArrayList<>();
        for (String item : items) {
            if (!StringHelper.isEmpty(item)) {
                preparedItems.add(startElementString + item + endElementString);
                if (++counter >= limit) {
                    result.add(list2String(separator, preparedItems));
                    counter = 0;
                    preparedItems = new ArrayList<>();
                }
            }
        }
        if (!isEmpty(preparedItems)) {
            result.add(list2String(separator, preparedItems));
        }
        return result;
    }

    /**
     * Verificamos si dos collections son iguales.
     * Para que dos collections sean iguales, deben de tener, el mismo número de elementos iguales dentro y estos a
     * su vez deben de ser iguales, y en el mismo orden.
     * @param collection1 Primer listado a comparar
     * @param collection2 Segundo listado a comparar
     * @param clazz La clase de los objetos que estan contenidos dentro del listado.
     * @return Indica si son iguales dos collecciones.
     */
    @SuppressWarnings("null")
    public static <T> boolean equals(Collection<T> collection1, Collection<T> collection2, Class<T> clazz) {
        boolean result = true;
        boolean isNullCollection1 = (collection1 == null);
        boolean isNullCollection2 = (collection2 == null);
        if (isNullCollection1 != isNullCollection2) {
            result = false;
        } else {
            boolean isEmptyCollection1 = CollectionHelper.isEmpty(collection1);
            boolean isEmptyCollection2 = CollectionHelper.isEmpty(collection2);
            if (isEmptyCollection1 != isEmptyCollection2) {
                result = false;
            } else if (!isEmptyCollection1) {
                if (collection1.size() != collection2.size()) {
                    result = false;
                } else {
                    for (int i = 0; i < collection1.size(); i++) {
                        T object1 = collection1.iterator().next();
                        T object2 = collection2.iterator().next();
                        boolean null1 = (object1 == null);
                        boolean null2 = (object2 == null);
                        if (null1 != null2) {
                            result = false;
                            break;
                        } else {
                            if (object1 != null) {
                                if (!object1.equals(object2)) {
                                    result = false;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    /**
     * Dada un listado de elementos los desordenamos en la base de datos.
     * @param collection El listado a desordenar.
     */
    public static void random(List<?> collection) {
        Collections.shuffle(collection, new Random(NowDateHelper.getCurrentDate().getTime()));
    }

    /**
     * Gets the diff of the collections and return an item of minuend that not exist on subtrahend.
     * @param minuend The minuend of the subtraction.
     * @param subtrahend The subtrahend of the subtraction.
     * @param <T> The class of the content of collections.
     * @return A item that not exist on subtrahend and exist on minuend
     */
    public static <T> T diffCollectionsAndGetOne(Collection<T> minuend, Collection<T> subtrahend) {
        ParameterHelper.assertCollectionEmpty(minuend);
        T result = null;
        if (CollectionHelper.isEmpty(subtrahend)) {
            result = minuend.iterator().next();
        } else {
            for (T element : minuend) {
                if (!subtrahend.contains(element)) {
                    result = element;
                    break;
                }
            }
        }
        if (result == null) {
            throw new RecIllegalArgumentException("Different result is zero");
        }
        return result;
    }

    /**
     * @param minuend The minuend of the subtraction.
     * @param subtrahend The subtrahend of the subtraction.
     * @param <T> The class of the content of collections.
     * @return A list of items result of the collections.
     */
    public static <T> Collection<T> diffCollection(Collection<T> minuend, Collection<T> subtrahend) {
        ParameterHelper.assertNull(minuend);
        List<T> result = new ArrayList<>();
        if (CollectionHelper.isEmpty(subtrahend)) {
            result = (List<T>) minuend;
        } else {
            for (T element : minuend) {
                if (!subtrahend.contains(element)) {
                    result.add(element);
                }
            }
        }
        return result;
    }
}
