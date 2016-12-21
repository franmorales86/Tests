/**
 * Project: RecCommon
 * Created by: raulanatol at 24/11/2012 17:49:41
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.common.helpers;

import com.reclabs.recomendar.common.exceptions.helpers.CurrencyHelperException;
import com.reclabs.recomendar.common.helpers.types.StringHelper;
import com.reclabs.recomendar.common.types.CurrencyFormatter;
import com.reclabs.recomendar.common.types.RecMoney;

import java.math.BigDecimal;

/**
 * Helper de monedas.
 * @author raulanatol
 */
public class CurrencyHelper {

    /**
     * Dado un texto que representa a una moneda obtenemos un valor BigDecimal del mismo.
     * El formato del texto debe ser {@link CurrencyFormatter#ONLY_POINT_DECIMAL}
     * @param currencyText El texto.
     * @return El valor númerico
     * @throws CurrencyHelperException En caso de que la conversión falle.
     */
    public static BigDecimal toCurrency(String currencyText) throws CurrencyHelperException {
        return toCurrency(currencyText, CurrencyFormatter.ONLY_POINT_DECIMAL);
    }

    /**
     * Dado un texto que representa a una moneda y un formato de moneda de entrada obtenemos un valor BigDecimal
     * del mismo.
     * @param currencyText El texto de la moneda "1250.25"
     * @param inputFormat El formato con el que se ha introducido el texto.
     * @return El texto en formato BigDecimal = 1250.25
     * @throws CurrencyHelperException En caso de que no se pueda realizar la conversión correctamente.
     */
    public static BigDecimal toCurrency(final String currencyText, final CurrencyFormatter inputFormat) throws CurrencyHelperException {
        String safeCurrencyText = toSafeCurrentText(currencyText, inputFormat);
        return new BigDecimal(safeCurrencyText);
    }

    /**
     * @param currencyText Text
     * @param inputFormat format
     * @return The string
     * @throws CurrencyHelperException En caso de que no se pueda parsear el texto.
     */
    private static String toSafeCurrentText(final String currencyText, final CurrencyFormatter inputFormat) throws CurrencyHelperException {
        if (StringHelper.isEmpty(currencyText)) {
            throw new CurrencyHelperException("The currencyText param can not be null");
        }
        switch (inputFormat) {
            case ONLY_POINT_DECIMAL:
                return currencyText;
            default:
                throw new CurrencyHelperException("Input format param is not expected " + inputFormat);
        }
    }

    /**
     * Verify if two money elements are equals.
     * @param money1 The first element
     * @param money2 The second element
     * @return True if two elements area equals.
     */
    public static boolean equals(RecMoney money1, RecMoney money2) {
        ParameterHelper.assertAnyNull(money1, money2);
        return (money1.getCurrencyUnit().compareTo(money2.getCurrencyUnit()) == 0 && money1.getAmount().compareTo(money2.getAmount()) == 0);
    }

    /**
     * Parse an string money format CURRENCY VALUE
     * @param money The money representation
     * @return The money
     */
    public static RecMoney toMoney(String money) {
        RecMoney result = null;
        if (!StringHelper.isEmpty(money)) {
            result = RecMoney.parse(money.trim());
        }
        return result;
    }
}
