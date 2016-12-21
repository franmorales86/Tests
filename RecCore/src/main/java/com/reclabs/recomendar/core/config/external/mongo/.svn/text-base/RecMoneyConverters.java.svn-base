/**
 * Project: RecCore
 * Created by: raulanatol at 12/09/13 14:09
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.config.external.mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.reclabs.recomendar.common.types.RecMoney;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.ClassUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author raulanatol
 */
public abstract class RecMoneyConverters {
    private static final boolean REC_MONEY_IS_PRESENT = ClassUtils.isPresent("com.reclabs.recomendar.common.types.RecMoney", null);

    /**
     * Returns the converters to be registered. Will only return converters in case JodaTime is present on the class.
     * @return
     */
    public static Collection<Converter<?, ?>> getConvertersToRegister() {

        if (!REC_MONEY_IS_PRESENT) {
            return Collections.emptySet();
        }

        List<Converter<?, ?>> converters = new ArrayList<>();
        converters.add(MoneyToDBObjectConverter.INSTANCE);
        converters.add(DBObjectToMoneyConverter.INSTANCE);

        return converters;
    }

    public static enum MoneyToDBObjectConverter implements Converter<RecMoney, DBObject> {
        INSTANCE;

        @Override
        public DBObject convert(RecMoney source) {
            DBObject result = new BasicDBObject();
            result.put("currency", source.getCurrencyUnit().name());
            result.put("amount", source.getAmount().toPlainString());
            return result;
        }
    }

    public static enum DBObjectToMoneyConverter implements Converter<DBObject, RecMoney> {
        INSTANCE;

        @Override
        public RecMoney convert(DBObject source) {
            String moneyStr = source.get("currency") + " " + source.get("amount");
            return RecMoney.parse(moneyStr);
        }
    }

}
