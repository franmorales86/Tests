/**
 * Project: RecCommon
 * Created by: raulanatol at 27/04/2013 18:04:04
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 * 
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.common.types.generic;

/**
 * 
 * @author raulanatol
 * 
 * @param <T>
 * @param <V>
 */
public class NameValuePair<T, V> {
	private T name;
	private V value;

	/**
	 * @param name
	 * @param value
	 */
	public NameValuePair(T name, V value) {
		super();
		this.name = name;
		this.value = value;
	}

	/**
	 * @return the name
	 */
	public T getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(T name) {
		this.name = name;
	}

	/**
	 * @return the value
	 */
	public V getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(V value) {
		this.value = value;
	}
}
