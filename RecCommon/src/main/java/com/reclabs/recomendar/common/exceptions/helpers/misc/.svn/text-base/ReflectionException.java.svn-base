/**
 * Project: RecCommon
 * Created by: fjmorales at 04/12/2012 19:22:28
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 * 
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.common.exceptions.helpers.misc;

import com.reclabs.recomendar.common.exceptions.generic.ErrorException;

/**
 * Excepción de errores en la ejecución de los reflections...
 * @author raulanatol
 * 
 */
public class ReflectionException extends ErrorException {
	private static final long serialVersionUID = -6441896291877236116L;

	/**
	 * 
	 * @param e
	 */
	public ReflectionException(final SecurityException e) {
		super("Error de acceso por seguridad", e);
	}

	/**
	 * 
	 * @param e
	 */
	public ReflectionException(final NoSuchFieldException e) {
		super("Error de acceso no existente", e);
	}
}
