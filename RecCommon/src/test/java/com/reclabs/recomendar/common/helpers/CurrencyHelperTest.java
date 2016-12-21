/**
 * Project: RecCommon
 * Created by: raulanatol at 22/07/13 10:54
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.common.helpers;

import com.reclabs.recomendar.common.types.CurrencyUnit;
import com.reclabs.recomendar.common.types.RecMoney;
import org.junit.Test;

import java.math.BigDecimal;

import static junit.framework.Assert.assertNull;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author raulanatol
 */
public class CurrencyHelperTest {

    @Test
    public void toMoneySimpleData() throws Exception {
        RecMoney money = CurrencyHelper.toMoney("EUR 18");
        assertThat(money.getAmount().compareTo(new BigDecimal(18)), is(0));
        assertThat(money.getCurrencyUnit(), is(CurrencyUnit.EUR));
    }

    @Test
    public void toMoneyDecimalData() throws Exception {
        RecMoney money = CurrencyHelper.toMoney("EUR 18.45");
        BigDecimal amount = money.getAmount();
        BigDecimal expected = new BigDecimal("18.45");
        assertThat(amount.compareTo(expected), is(0));
        assertThat(money.getCurrencyUnit(), is(CurrencyUnit.EUR));
    }

    @Test
    public void toMoneyDecimalWithExtraData() throws Exception {
        String moneyString = " EUR 18.45";
        RecMoney money = CurrencyHelper.toMoney(moneyString);
        BigDecimal amount = money.getAmount();
        BigDecimal expected = new BigDecimal("18.45");
        assertThat(amount.compareTo(expected), is(0));
        assertThat(money.getCurrencyUnit(), is(CurrencyUnit.EUR));
    }

    @Test(expected = ArithmeticException.class)
    public void toMoneyRoundDecimalData() throws Exception {
        CurrencyHelper.toMoney("EUR 18.45320341231");
    }

    @Test
    public void toMoneyNullData() throws Exception {
        assertNull(CurrencyHelper.toMoney(null));
    }

    @Test
    public void toMoneyEmptyData() throws Exception {
        assertNull(CurrencyHelper.toMoney(""));
    }
}
