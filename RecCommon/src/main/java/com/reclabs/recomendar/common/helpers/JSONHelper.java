/**
 * Project: RecCommon
 * Created by: raulanatol at 24/10/13 11:34
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.common.helpers;

import com.google.gson.Gson;

/**
 * @author raulanatol
 */
public class JSONHelper {

    public static String toJSON(Object object) {
        return new Gson().toJson(object);
    }

    public static <T> T fromJSON(String object, Class<T> clazz) {
        return new Gson().fromJson(object, clazz);
    }
}
