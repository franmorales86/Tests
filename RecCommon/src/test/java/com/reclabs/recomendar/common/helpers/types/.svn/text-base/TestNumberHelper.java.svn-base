/**
 * Project: RecCommon
 * Created by: fjmorales at 04/12/2012 21:07:19
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 * 
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.common.helpers.types;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
 * @author raulanatol
 */
public class TestNumberHelper {
	/**
	 * @see NumberHelper#maxExtraLongs(String, String)
	 */
	@Test
	public void maxExtraLongsNormalValuesTest() {
		String valueA = "00010101023112312312311141009";
		String valueB = "00010101024123123081239000909";
		assertThat(NumberHelper.maxExtraLongs(valueA, valueB), is(valueB));
	}

    /**
     * @see NumberHelper#decrementLong(Long, long, long)
     */
    @Test
    public void decrementLongTest() {
        assertThat(NumberHelper.decrementLong(null, 2, 4), is(4L));
        assertThat(NumberHelper.decrementLong(10L, 2, 8), is(8L));
        assertThat(NumberHelper.decrementLong(10L, 4, 8), is(8L));
        assertThat(NumberHelper.decrementLong(10L, -4, 8), is(14L));
    }

}
