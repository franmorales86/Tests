/**
 * Project: RecCommon
 * Created by: fjmorales at 06/12/2012 20:23:31
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

import com.reclabs.recomendar.common.types.CodeErrorType;

/**
 * @author fjmorales
 * 
 */
public class TestApiException {
	/**
	 * @see ApiException#ApiException(CodeErrorType, String)
	 */
	@Test
	public void testConstructorCode() {
		String message = "exceptionMessage";
		ApiException e = new ApiException(CodeErrorType.PRODUCT_NOT_LOAD, message);
		assertEquals(e.getCode(), CodeErrorType.PRODUCT_NOT_LOAD.getCode());
		assertEquals(e.getMessage(), message);
	}

	/**
	 * @see ApiException#ApiException(CodeErrorType, String, Throwable)
	 */
	@Test
	public void testConstructorThrowCode() {
		String message = "exceptionMessage";
		ApiException e = new ApiException(CodeErrorType.PRODUCT_NOT_LOAD, message, new Throwable());
		assertEquals(e.getCode(), CodeErrorType.PRODUCT_NOT_LOAD.getCode());
		assertEquals(e.getMessage(), message);
	}

	/**
	 * @see ApiException#ApiException(CodeErrorType)
	 */
	@Test
	public void testConstructorWOMessage() {
		ApiException e = new ApiException(CodeErrorType.PRODUCT_NOT_LOAD);
		assertEquals(e.getCode(), CodeErrorType.PRODUCT_NOT_LOAD.getCode());
		assertEquals(e.getMessage(), CodeErrorType.PRODUCT_NOT_LOAD.getDefaultMessage());
	}

	/**
	 * @see ApiException#ApiException(CodeErrorType, Throwable)
	 */
	@Test
	public void testConstructorThrowWOMessage() {
		ApiException e = new ApiException(CodeErrorType.PRODUCT_NOT_LOAD, new Throwable());
		assertEquals(e.getCode(), CodeErrorType.PRODUCT_NOT_LOAD.getCode());
		assertEquals(e.getMessage(), CodeErrorType.PRODUCT_NOT_LOAD.getDefaultMessage());
	}

	/**
	 * Regla para comparar el mensaje de las excepciones
	 */
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	/**
	 * @throws ApiException
	 */
	@Test
	public void testException() throws ApiException {
		String message = "exceptionMessage";
		this.thrown.expect(ApiException.class);
		this.thrown.expectMessage(message);
		throw new ApiException(CodeErrorType.INTERNAL_ERROR, message);
	}

}
