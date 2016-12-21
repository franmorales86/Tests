/**
 * Project: RecCommon
 * Created by: raulanatol at 24/11/2012 18:10:40
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 * 
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.common.exceptions.helpers;

import com.reclabs.recomendar.common.exceptions.generic.UserException;

/**
 * @author raulanatol
 * 
 */
public class CurrencyHelperException extends UserException {
	private static final long serialVersionUID = -3945852673996216336L;

	/**
	 * @param code
	 * @param userMessage
	 */
	public CurrencyHelperException(final String userMessage) {
		super(userMessage);
	}
}
