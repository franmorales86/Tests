/**
 * Project: RecCommon
 * Created by: fjmorales at 05/12/2012 14:41:40
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 * 
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.common.exceptions.generic;

import static org.junit.Assert.assertEquals;

import java.net.HttpURLConnection;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.reclabs.recomendar.common.types.CodeErrorType;

/**
 * @author fjmorales
 * 
 */
public class TestErrorException {

	/**
	 * @see ErrorException#ErrorException(String)
	 */
	@Test
	public void testConstructorWOCode() {
		String message = "exceptionMessage";
		ErrorException e = new ErrorException(message);
		assertEquals(e.getCode(), HttpURLConnection.HTTP_INTERNAL_ERROR);
		assertEquals(e.getMessage(), message);
	}

	/**
	 * @see ErrorException#ErrorException(CodeErrorType)
	 */
	@Test
	public void testConstructorCodeWOMessage() {
		ErrorException e = new ErrorException(CodeErrorType.PRODUCT_NOT_LOAD);
		assertEquals(e.getCode(), CodeErrorType.PRODUCT_NOT_LOAD.getCode());
		assertEquals(e.getMessage(), CodeErrorType.PRODUCT_NOT_LOAD.getDefaultMessage());
	}

	/**
	 * @see ErrorException#ErrorException(CodeErrorType, String)
	 */
	@Test
	public void testConstructorCode() {
		String message = "exceptionMessage";
		ErrorException e = new ErrorException(CodeErrorType.PRODUCT_NOT_LOAD, message);
		assertEquals(e.getCode(), CodeErrorType.PRODUCT_NOT_LOAD.getCode());
		assertEquals(e.getMessage(), message);
	}

	/**
	 * @see ErrorException#ErrorException(CodeErrorType, UserException)
	 */
	@Test
	public void testConstructorUserExc() {
		String message = "exceptionMessage";
		UserException userExc = new UserException(message);
		ErrorException e = new ErrorException(CodeErrorType.PRODUCT_NOT_LOAD, userExc);
		assertEquals(e.getCode(), CodeErrorType.PRODUCT_NOT_LOAD.getCode());
		assertEquals(e.getMessage(), message);
	}

	/**
	 * @see ErrorException#ErrorException(String, Throwable)
	 */
	@Test
	public void testConstructorThrowWOCode() {
		String message = "exceptionMessage";
		ErrorException e = new ErrorException(message, new Throwable());
		assertEquals(e.getCode(), HttpURLConnection.HTTP_INTERNAL_ERROR);
		assertEquals(e.getMessage(), message);
	}

	/**
	 * @see ErrorException#ErrorException(CodeErrorType, String, Throwable)
	 */
	@Test
	public void testConstructorThrowCode() {
		String message = "exceptionMessage";
		ErrorException e = new ErrorException(CodeErrorType.PRODUCT_NOT_LOAD, message, new Throwable());
		assertEquals(e.getCode(), CodeErrorType.PRODUCT_NOT_LOAD.getCode());
		assertEquals(e.getMessage(), message);
	}

	/**
	 * Regla para comparar el mensaje de las excepciones
	 */
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	/**
	 * @throws ErrorException
	 */
	@Test
	public void testException() {
		String message = "exceptionMessage";
		this.thrown.expect(ErrorException.class);
		this.thrown.expectMessage(message);
		throw new ErrorException(message);
	}

}
