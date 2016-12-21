/**
 * Project: RecCommon
 * Created by: raulanatol at 12/09/13 13:06
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.common.types;

import com.reclabs.recomendar.common.exceptions.RecIllegalArgumentException;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author raulanatol
 */
public class TestCurrencyUnit {
    @Test
    public void ofTestValidData() throws Exception {
        assertThat(CurrencyUnit.of("EUR"), is(CurrencyUnit.EUR));
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void ofTestNullData() throws Exception {
        assertThat(CurrencyUnit.of(null), is(CurrencyUnit.EUR));
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void ofTestEmptyData() throws Exception {
        assertThat(CurrencyUnit.of(""), is(CurrencyUnit.EUR));
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void ofTestWithUnknownCurrency() throws Exception {
        assertThat(CurrencyUnit.of("ZZZ"), is(CurrencyUnit.AUD));
    }
}
