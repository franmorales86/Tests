/**
 * Project: RecCommon
 * Created by: fjmorales at 04/12/2012 19:32:16
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 * 
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.common.exceptions.helpers.types;

import com.reclabs.recomendar.common.exceptions.generic.ErrorException;
import com.reclabs.recomendar.common.helpers.types.StringHelper;

/**
 * Excepción usada por el {@link StringHelper} para indicar los errores en sus funciones.
 * @author raulanatol
 */
public class StringHelperException extends ErrorException {
	private static final long serialVersionUID = -2176440046160702189L;

	/**
	 * @param userMessage
	 */
	public StringHelperException(final String userMessage) {
		super(userMessage);
	}
}
