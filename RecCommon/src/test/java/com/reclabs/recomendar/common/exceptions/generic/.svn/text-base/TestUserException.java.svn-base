/**
 * Project: RecCommon
 * Created by: fjmorales at 05/12/2012 14:54:39
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 * 
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.common.exceptions.generic;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * @author fjmorales
 * 
 */
public class TestUserException {

	/**
	 * @see UserException#UserException(String)
	 */
	@Test
	public void testConstructorCode() {
		String message = "exceptionMessage";
		UserException e = new UserException(message);
		assertEquals(e.getMessage(), message);
	}

	/**
	 * @see UserException#UserException(String, Throwable)
	 */
	@Test
	public void testConstructorThrowCode() {
		String message = "exceptionMessage";
		UserException e = new UserException(message, new Throwable());
		assertEquals(e.getMessage(), message);
	}

	/**
	 * Regla para comparar el mensaje de las excepciones
	 */
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	/**
	 * @throws UserException
	 */
	@Test
	public void testException() throws UserException {
		String message = "exceptionMessage";
		this.thrown.expect(UserException.class);
		this.thrown.expectMessage(message);
		throw new UserException(message);
	}

}
