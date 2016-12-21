/**
 * Project: RecCommon
 * Created by: raulanatol at 22/12/2012 18:50:55
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 * 
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.common.exceptions.generic;

/**
 * @author raulanatol
 * 
 */
public class RecRuntimeException extends RuntimeException {
	private static final long serialVersionUID = 108609280979302879L;
	/**
	 * El mensaje que se le muestra el usuario de la excepción.
	 */
	private final String userMessage;

	/**
	 * @param message
	 */
	public RecRuntimeException(final String message) {
		super(message);
		this.userMessage = message;
	}

	/**
	 * @return the userMessage
	 */
	public String getUserMessage() {
		return this.userMessage;
	}
}
