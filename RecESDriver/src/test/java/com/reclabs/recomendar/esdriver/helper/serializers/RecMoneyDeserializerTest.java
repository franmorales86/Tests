/**
 * Project: RecESDriver
 * Created by: raulanatol at 12/09/13 14:43
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.esdriver.helper.serializers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.reclabs.recomendar.common.types.RecMoney;
import org.junit.Test;

import static com.reclabs.recomendar.common.helpers.ParameterHelper.assertNull;

/**
 * @author raulanatol
 */
public class RecMoneyDeserializerTest {

    @Test
    public void deserializer() throws Exception {
        RecMoneyDeserializer deserializer = new RecMoneyDeserializer();

        JsonParser parser = new JsonParser();
        JsonObject jsonObject = (JsonObject) parser.parse("{\"currency\": \"EUR\", \"amount\": \"44.00\"}");

        RecMoney money = deserializer.deserialize(jsonObject, null, null);
        assertNull(money);
    }
}
