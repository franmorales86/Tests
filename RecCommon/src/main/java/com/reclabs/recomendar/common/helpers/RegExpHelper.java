/**
 * Project: RecCommon
 * Created by: fjmorales at 28/11/2012 13:50:43
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 * 
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.common.helpers;

import com.reclabs.recomendar.common.helpers.types.NullHelper;

/**
 * Helper para expresiones regulares
 * @author fjmorales
 * 
 */
public class RegExpHelper {

	/**
	 * Metodo que se encarga de añadir los simbolos de comienzo y fin para una expresion regular en la cadena
	 * pasada por parametro
	 * @param text
	 * @return Devuelve la cadena con los símbolos parseados.
	 */
	public static String addStartEndSymbol(final String text) {
		String result = null;
		if (!NullHelper.isAnyNull(text)) {
			result = "^" + text + "$";
		}
		return result;
	}
}
