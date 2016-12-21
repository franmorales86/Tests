/**
 * Project: RecCommon
 * Created by: fjmorales at 04/12/2012 20:28:28
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 * 
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.common.types;

/**
 * Representan los diferentes tipos de lenguajes que se pueden usar en la aplicación
 * @author rmorales
 */
public enum Language {
	// @formatter:off
	/**
	 * Sin lenguaje predefinido 
	 */
	NOTHING("-"), 
	/**
	 * Español
	 */
	SPANISH("es"),
	/**
	 * Ingles
	 */
	ENGLISH("en");
	// @formatter:on

	private String reference;

	/**
	 * @return Referencia del idioma
	 */
	public String getReference() {
		return this.reference;
	}

	/**
	 * 
	 * @param reference
	 */
	private Language(final String reference) {
		this.reference = reference;
	}

	/**
	 * Indicamos si el language está contenido en el listado de lenguajes...
	 * @param searchLang
	 * @param languages
	 * @return Boolean que representa si el lenguaje esta contenido en el listado
	 */
	public static boolean contains(final Language searchLang, final Language[] languages) {
		boolean result = false;
		for (Language language : languages) {
			if (language == searchLang) {
				result = true;
				break;
			}
		}
		return result;
	}

	/**
	 * Dado un isoLanguageCode obtenemos el idioma que le corresponde.
	 * @param isoLanguageCode
	 * @return Idioma asociado
	 */
	public static Language fromIsoLanguageReference(final String isoLanguageCode) {
		for (Language language : Language.values()) {
			if (language.reference.compareTo(isoLanguageCode) == 0) {
				return language;
			}
		}
		return Language.NOTHING;
	}
}
