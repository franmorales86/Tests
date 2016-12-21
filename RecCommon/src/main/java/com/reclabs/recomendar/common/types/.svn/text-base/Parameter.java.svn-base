/**
 * Project: RecCommon
 * Created by: fjmorales at 04/12/2012 20:31:16
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 * 
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.common.types;

import java.io.Serializable;

/**
 * Representa a un elemento o parámetro.
 * 
 * @author raulanatol
 */
public class Parameter implements Serializable {
	private static final long serialVersionUID = 4421710085975678477L;
	/**
	 * Identificador del parámetro usado tanto para pasarlo por parámetros como
	 * para localizarlo. Por ejemplo "-f" es el parámetro de búsqueda del Scout
	 * pero también su identificador.
	 */
	private String key;
	/**
	 * El valor de dicho parámetro. <b>null</b> implica parámetro no fijado.
	 */
	private String value;
	/**
	 * Indica si es obligatorio o no la aparición del parámetro para la
	 * ejecución de la clase.
	 */
	private boolean mandatory;

	/**
	 * Construimos el parámetro pasándole la clave.
	 * 
	 * @param key
	 *        Por ejemplo -m
	 * @param mandatory
	 */
	public Parameter(final String key, final boolean mandatory) {
		super();
		this.key = key;
		this.mandatory = mandatory;
		this.value = null;
	}

	/**
	 * @return Identificador del parametro
	 */
	public String getKey() {
		return this.key;
	}

	/**
	 * @param key
	 */
	public void setKey(final String key) {
		this.key = key;
	}

	/**
	 * @return Valor del parametro
	 */
	public String getValue() {
		return this.value;
	}

	/**
	 * @param value
	 */
	public void setValue(final String value) {
		this.value = value;
	}

	/**
	 * 
	 * @return Devuelve si es obligatorio la aparicion de este parametro
	 */
	public boolean isMandatory() {
		return this.mandatory;
	}

	/**
	 * @param mandatory
	 */
	public void setMandatory(final boolean mandatory) {
		this.mandatory = mandatory;
	}
}
