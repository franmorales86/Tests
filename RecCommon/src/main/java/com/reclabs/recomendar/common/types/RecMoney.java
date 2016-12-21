/**
 * Project: RecCommon
 * Created by: raulanatol at 11/09/13 17:50
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.common.types;

import com.reclabs.recomendar.common.helpers.ParameterHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.regex.Pattern;

/**
 * @author raulanatol
 */
public class RecMoney {
    private static final Logger LOGGER = LoggerFactory.getLogger(RecMoney.class);
    private static final Pattern PARSE_REGEX = Pattern.compile("[+-]?[0-9]*[.]?[0-9]*");
    private BigDecimal amount;
    private CurrencyUnit currencyUnit;

    public RecMoney() {
        super();
    }

    public RecMoney(CurrencyUnit currencyUnit, BigDecimal amount) {
        super();
        this.currencyUnit = currencyUnit;
        this.amount = amount;
    }

    /**
     * @param value String to represent the money
     * @return The money
     */
    public static RecMoney parse(String value) {
        ParameterHelper.assertEmpty(value);
        if (value.length() < 5 || value.charAt(3) != ' ') {
            LOGGER.error("[Trying to parse: {}, error the length is invalid]", value);
            throw new IllegalArgumentException("RecMoney '" + value + "' cannot be parsed");
        }
        String currency = value.substring(0, 3);
        String amount = value.substring(4);
        if (!PARSE_REGEX.matcher(amount).matches()) {
            LOGGER.error("[Trying to parse: {}, error money amount cannot be parsed]", value);
            throw new IllegalArgumentException("Money amount '" + value + "' cannot be parsed");
        }
        return RecMoney.of(currency, amount);
    }

    /**
     * @param currency currency
     * @param amount the amount of the money
     * @return the money
     */
    public static RecMoney of(String currency, String amount) {
        ParameterHelper.assertAnyEmpty(currency, amount);
        CurrencyUnit currencyUnit = CurrencyUnit.of(currency);
        BigDecimal amountValue = new BigDecimal(amount);
        if (amountValue.scale() > currencyUnit.getDecimalPlaces()) {
            LOGGER.error("Scale of amount {} is greater than the scale of the currency {}", amount, currencyUnit);
            throw new ArithmeticException("Scale of amount " + amount + " is greater than the scale of the currency " + currencyUnit);
        }
        return new RecMoney(currencyUnit, amountValue);
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public CurrencyUnit getCurrencyUnit() {
        return currencyUnit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RecMoney)) return false;

        RecMoney recMoney = (RecMoney) o;

        if (amount != null ? !amount.equals(recMoney.amount) : recMoney.amount != null) return false;
        if (currencyUnit != recMoney.currencyUnit) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = amount != null ? amount.hashCode() : 0;
        result = 31 * result + (currencyUnit != null ? currencyUnit.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RecMoney{" +
                "amount=" + amount +
                ", currencyUnit=" + currencyUnit +
                '}';
    }
}
