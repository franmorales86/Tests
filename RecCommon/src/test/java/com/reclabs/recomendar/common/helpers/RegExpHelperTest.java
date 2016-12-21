/**
 * Project: RecCommon
 * Created by: fjmorales at 28/11/2012 13:50:43
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 * 
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.common.helpers;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
 * Helper para expresiones regulares
 * @author fjmorales
 * 
 */
public class RegExpHelperTest {
	/**
	 * @see RegExpHelper#addStartEndSymbol(String)
	 */
	@Test
	public void testAddStartEndSymbol() {
		assertThat(RegExpHelper.addStartEndSymbol("aaa"), is("^aaa$"));
		assertThat(RegExpHelper.addStartEndSymbol("123as"), is("^123as$"));
		assertNull(RegExpHelper.addStartEndSymbol(null));
	}
}
