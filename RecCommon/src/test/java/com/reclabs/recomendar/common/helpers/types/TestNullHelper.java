/**
 * Project: RecCommon
 * Created by: raulanatol at 13/02/2013 20:25:24
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 * 
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.common.helpers.types;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * @author raulanatol
 */
public class TestNullHelper {

	/**
	 * @see NullHelper#isAnyNull(Object...)
	 */
	@Test
	public void isAnyNullTest() {
		assertTrue(NullHelper.isAnyNull("dos", 3, null, 25D));
		assertFalse(NullHelper.isAnyNull("dos", 3, 25D));
	}

	/**
	 * @see NullHelper#isAnyEmpty(Object...)
	 */
	@Test
	public void isAnyEmptyTest() {
		assertTrue(NullHelper.isAnyEmpty("dos", 3, null, 25D));
		assertTrue(NullHelper.isAnyEmpty("dos", 3, " ", 25D));
		assertTrue(NullHelper.isAnyEmpty("dos", 3, "", 25D));
		assertFalse(NullHelper.isAnyNull("dos", 3, 25D));
	}

    /**
     * @see NullHelper#isAllNull(Object...)
     */
    @Test
    public void isAllNullTest() {
        assertFalse(NullHelper.isAllNull(null, "dos", 3, null, 25D));
        assertTrue(NullHelper.isAnyNull(null, null, null));
        assertFalse(NullHelper.isAllNull(null, null, null, null, 25D));
    }
}
