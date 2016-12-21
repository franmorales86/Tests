/**
 * Project: RecCommon
 * Created by: fjmorales at 04/12/2012 20:35:31
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 * 
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.common.types;

import com.reclabs.recomendar.common.helpers.types.StringHelper;

/**
 * Representa cada uno de los diferentes tipos de fuentes de datos para las que pueda llegar
 * información al sistema. No se usará tabla para esto ya que no se preveé que sea más compleja.
 * @author raulanatol
 */
public enum SourceType {
	/**
	 * Fuente de datos de twitter
	 */
	TWITTER("TWITTER_SOURCE"),
	/**
	 * Sin tipo registrado o conocido
	 */
	UNDEFINED("UNDEFINED");

	/**
	 * Codigo de la fuente de datos
	 */
	private String code;

	/**
	 * @return Codigo de la fuente de datos
	 */
	public String getCode() {
		return this.code;
	}

	private SourceType(final String code) {
		this.code = code;
	}

	/**
	 * @param possibleCode
	 * @return Fuente asociada al codigo pasado por parametro
	 */
	public static SourceType getByCode(final String possibleCode) {
		for (SourceType type : values()) {
			if (StringHelper.equals(type.getCode(), possibleCode)) {
				return type;
			}
		}
		return UNDEFINED;
	}
}
