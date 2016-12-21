/**
 * Project: RecCommon
 * Created by: raulanatol at 18/05/13 20:05
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.common.helpers.misc;

import com.reclabs.recomendar.common.helpers.RandomHelper;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author raulanatol
 * @see HashHelper
 */
public class HashHelperTest {

    /**
     * @see HashHelper#encodeStringMode(String)
     */
    @Test
    public void encodeStringModeSimpleTest() {
        String code = "Testing with a simple code";
        String expected = "f6b1bdf39629b585ca21b83746cfaf845554b616db1fd2f781003ee23ec94e3b";
        assertThat(HashHelper.encodeStringMode(code), is(expected));
    }

    /**
     * @see HashHelper#encodeStringMode(String)
     */
    @Test
    public void verifyEqualsInputEqualsOutput() {
        String code = RandomHelper.getMailVerificationCode();
        assertThat(HashHelper.encodeStringMode(code), is(HashHelper.encodeStringMode(code)));
    }

    /**
     * @see HashHelper#encodeStringMode(String)
     */
    @Test
    public void verifyDataOfSpecifiedInput() {
        String code = "OZEgJ6nP";
        String expected = "0a6c954a2a32b9d8943dcae8cba27e2089bf035abbd1f00accf6945929563c28";
        assertThat(HashHelper.encodeStringMode(code), is(expected));
    }

    @Test
    public void encodeStringModeSimpleValue() throws Exception {
        String encodingCode = HashHelper.encodeStringMode("qqwPGsEx");
        assertThat(encodingCode, is("a08419453af330e2e01979ecf82b054ba58e1a41b944e117a58d0b0aa31a75f1"));
    }
}