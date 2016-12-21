/**
 * Project: RecESDriver
 * Created by: raulanatol at 12/09/13 14:40
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.esdriver.helper.serializers;

import com.google.gson.*;
import com.reclabs.recomendar.common.types.RecMoney;

import java.lang.reflect.Type;

/**
 * Deserializer of RecMoney
 * @author raulanatol
 */
public class RecMoneyDeserializer implements JsonDeserializer<RecMoney> {

    @Override
    public RecMoney deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = (JsonObject) json;
        JsonPrimitive currency = jsonObject.getAsJsonPrimitive("currency");
        JsonPrimitive amount = jsonObject.getAsJsonPrimitive("amount");
        return RecMoney.parse(currency.getAsString() + " " + amount.getAsString());
    }
}
