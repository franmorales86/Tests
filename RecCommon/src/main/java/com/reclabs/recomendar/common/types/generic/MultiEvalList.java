/**
 * Project: RecCommon
 * Created by: raulanatol at 27/04/2013 18:02:47
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 * 
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.common.types.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * Lista multievaluada. No es mas que un listado de elementos key value.
 * @author raulanatol
 * @param <T> El tipo del nombre
 * @param <V> El tipo del valor almacenado.
 */
public class MultiEvalList<T, V> {
	private List<NameValuePair<T, V>> elements;

	/**
	 * 
	 */
	public MultiEvalList() {
		super();
		elements = new ArrayList<>();
	}

	/**
	 * Creamos un multiEvalList con un único elemento.
	 * @param tValue
	 * @param vValue
	 */
	public MultiEvalList(T tValue, V vValue) {
		super();
		elements = new ArrayList<>();
		elements.add(new NameValuePair<>(tValue, vValue));
	}

	/**
	 * @return the elements
	 */
	public List<NameValuePair<T, V>> getElements() {
		return elements;
	}

	/**
	 * Insertamos un elemento.
	 * @param name
	 * @param value
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void addElement(T name, V value) {
		elements.add(new NameValuePair(name, value));
	}

	/**
	 * El tamaño del listado
	 * @return Listado de los elementos.
	 */
	public int size() {
		return elements.size();
	}
}
