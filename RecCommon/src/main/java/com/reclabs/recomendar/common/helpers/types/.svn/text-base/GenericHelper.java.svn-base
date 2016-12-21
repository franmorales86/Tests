/**
 * Project: RecCommon
 * Created by: raulanatol at 13/02/2013 16:18:32
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 * 
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.common.helpers.types;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.reclabs.recomendar.common.exceptions.generic.ErrorException;

/**
 * Helper para los tipos genéricos de Java
 * @author raulanatol
 */
public class GenericHelper {
	private static final Logger LOGGER = LoggerFactory.getLogger(GenericHelper.class);

	/**
	 * Dada una clase genérica cualquiera creamos una nueva instancia de la misma.
	 * @param clazz
	 * @return Obtenemos la instancia de la clase pasada por parámetros.
	 */
	public static <T> T getInstance(Class<T> clazz) {
		try {
			return clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			LOGGER.warn("Error on newInstance()", e);
			throw new ErrorException("Unexpected error", e);
		}
	}
}
