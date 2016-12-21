/**
 * Project: RecCommon
 * Created by: raulanatol at 09/11/2012 23:07:49
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 * 
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.common.exceptions.generic;

/**
 * Error con excepción para el usuario, no es un error grave sino una
 * falta de acierto por parte del usuario, por ejemplo un userExcepcion
 * se produce cuando se intenta busar un usuario en la base de datos y
 * este no se encuentra
 * @author raulanatol
 */
public class UserException extends Exception {
	private static final long serialVersionUID = -6551281503302328339L;

	/**
	 * Constructor de la excepción en la que se le pasa el mensaje del usuario
	 * y la causa de la excepción
	 * @param userMessage Mensaje de error para el usuario
	 * @param cause Excepcion lanzada
	 */
	public UserException(final String userMessage, final Throwable cause) {
		super(userMessage, cause);
	}

	/**
	 * Constructor en la que se pasa el mensaje hacia el usuario
	 * @param code Codigo de error de la excepcion
	 * @param userMessage Mensaje de error para el usuario
	 */
	public UserException(final String userMessage) {
		super(userMessage);
	}

}
