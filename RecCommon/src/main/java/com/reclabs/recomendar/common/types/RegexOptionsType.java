/**
 * Project: RecCommon
 * Created by: fjmorales at 28/11/2012 13:26:39
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 * 
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.common.types;

/**
 * @author fjmorales
 * 
 */
public enum RegexOptionsType {
	// @formatter:off
	/**
	 * Insensible a las mayusculas y minusculas
	 */
	CASE_INSENSITIVE("i"),
	/**
	 * Permite expresiones regulares multilinea
	 */ 
	MULTILINE("m"),
	/**
	 * Ignora espacios en blancos
	 */ 
	EXTENDED("x");
	// @formatter:on

	private String code;

	private RegexOptionsType(final String code) {
		this.code = code;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return this.code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(final String code) {
		this.code = code;
	}

}
