/**
 * Project: RecCommon
 * Created by: raulanatol at 09/11/2012 23:07:25
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 * 
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.common.exceptions.generic;

import com.reclabs.recomendar.common.types.CodeErrorType;

/**
 * Excepción de mayor prioridad, siempre que se produzca un error de este tipo debería ser llevado
 * hasta el final y ser auditado para ser corregido.
 * @author raulanatol
 */
public class ErrorException extends RuntimeException {
	private static final long serialVersionUID = -6100830505651822480L;

	/**
	 * El código de la excepción. Por defecto es el error interno.
	 */
	private CodeErrorType code = CodeErrorType.INTERNAL_ERROR;

	/**
	 * Creamos la excepción con mensaje, codigo de error y causa
	 * @param code El codigo de error de la excepcion
	 * @param userMessage El mensaje que ve el usuario final.
	 * @param cause La traza con la causa del error para poder ser auditado.
	 */
	public ErrorException(final CodeErrorType code, final String userMessage, final Throwable cause) {
		super(userMessage, cause);
		this.code = code;
	}

	/**
	 * Creamos la excepción con mensaje y causa
	 * @param code El codigo de error de la excepcion
	 * @param userMessage El mensaje que ve el usuario final.
	 * @param cause La traza con la causa del error para poder ser auditado.
	 */
	public ErrorException(final String userMessage, final Throwable cause) {
		super(userMessage, cause);
	}

	/**
	 * Construimos un ErrorException a través de un UserException y un codigo de error
	 * @param code Codigo de error de la excepcion
	 * @param userException
	 */
	public ErrorException(final CodeErrorType code, final UserException userException) {
		super(userException.getMessage(), userException);
		this.code = code;
	}

	/**
	 * Construimos un {@link ErrorException} con un codigo y un mensaje de error.
	 * @param code El codigo de error de la excepcion
	 * @param userMessage Mensaje que pueda leer el usuario.
	 */
	public ErrorException(final CodeErrorType code, final String userMessage) {
		super(userMessage);
		this.code = code;
	}

	/**
	 * Construimos un {@link ErrorException} con un codigo y el mensaje de error
	 * se obtiene del mensaje por defecto para ese codigo.
	 * @param code El codigo de error de la excepcion
	 */
	public ErrorException(final CodeErrorType code) {
		super(code.getDefaultMessage());
		this.code = code;
	}

	/**
	 * Construimos un {@link ErrorException} con un mensaje de error y con el codigo
	 * de error por defecto.
	 * @param userMessage Mensaje que pueda leer el usuario.
	 */
	public ErrorException(final String userMessage) {
		super(userMessage);
	}

	/**
	 * Getter del codigo de error
	 * @return Codigo de error
	 */
	public int getCode() {
		return this.code.getCode();
	}
}
