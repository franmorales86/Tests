/**
 * Project: RecCommon
 * Created by: raulanatol at 11/09/13 17:59
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.common.types;

import com.reclabs.recomendar.common.exceptions.RecIllegalArgumentException;
import com.reclabs.recomendar.common.helpers.ParameterHelper;
import com.reclabs.recomendar.common.helpers.types.StringHelper;

/**
 * @author raulanatol
 */
public enum CurrencyUnit {
    USD,
    EUR,
    JPY(0),
    GBP,
    CHF,
    AUD,
    CAD;

    private static final int DEFAULT_DECIMAL_PLACES = 2;

    /**
     *
     */
    private CurrencyUnit() {
        this(DEFAULT_DECIMAL_PLACES);
    }

    /**
     * @param decimalPlaces Number of decimal places
     */
    private CurrencyUnit(int decimalPlaces) {
        this.decimalPlaces = decimalPlaces;
    }

    /**
     * The number of the decimals
     */
    private int decimalPlaces;

    public int getDecimalPlaces() {
        return decimalPlaces < 0 ? 0 : decimalPlaces;
    }

    /**
     * @param currency The currency on string format
     * @return The currency
     */
    public static CurrencyUnit of(String currency) {
        ParameterHelper.assertEmpty(currency);
        CurrencyUnit result = null;
        for (CurrencyUnit currencyUnit : values()) {
            if (StringHelper.equals(currencyUnit.name(), currency)) {
                result = currencyUnit;
                break;
            }
        }
        if (result == null) {
            throw new RecIllegalArgumentException("Currency not found");
        }
        return result;
    }
}
