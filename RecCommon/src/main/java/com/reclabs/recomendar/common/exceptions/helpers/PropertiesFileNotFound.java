/**
 * Project: RecCommon
 * Created by: fjmorales at 04/12/2012 19:29:11
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 * 
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.common.exceptions.helpers;

import com.reclabs.recomendar.common.exceptions.generic.UserException;

/**
 * Excepción en la que no se encuentra el fichero de properties indicado.
 * @author rmorales
 */
public class PropertiesFileNotFound extends UserException {
	private static final long serialVersionUID = 4046468759021927949L;

	/**
	 * Constructor de la clase
	 * @param fileName El nombre del fichero de propiedades que no se ha encontrado.
	 * @param cause Causa del error.
	 */
	public PropertiesFileNotFound(final String fileName, final Throwable cause) {
		super("El fichero de propiedades " + fileName + " no se ha podido abrir", cause);
	}
}
