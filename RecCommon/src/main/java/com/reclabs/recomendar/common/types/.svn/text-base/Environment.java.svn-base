/**
 * Project: RecCommon
 * Created by: fjmorales at 04/12/2012 20:19:59
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 * 
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.common.types;

/**
 * Se definen los diferentes entornos de la aplicación
 * @author raulanatol
 */
public enum Environment {
	// @formatter:off
	/**
	 * Entorno desconocido.
	 */
	UNK("dev"),
	/**
	 * Entorno de desarrollo
	 */
	DEV("dev"), 
	/**
	 * Entorno de preproducción
	 */
	PRE("pre"), 
	/**
	 * Entorno de producción
	 */
	PRO("pro");
	// @formatter:on

	private String name;

	private Environment(final String name) {
		this.name = name;
	}

	/**
	 * @return Nombre del entorno
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Dado el valor de una posible variable de entorno la obtenemos.
	 * @param value El valor de la variable de entorno.
	 * @return La variable de entorno no UNKNOWN en caso de no encontrar la correcta.
	 */
	public static Environment fromValue(final String value) {
		switch (value) {
			case "DEV":
				return DEV;
			case "PRE":
				return PRE;
			case "PRO":
				return PRO;
			default:
				return UNK;
		}
	}
}
