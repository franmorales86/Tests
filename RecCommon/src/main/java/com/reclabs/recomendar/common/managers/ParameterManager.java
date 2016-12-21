/**
 * Project: RecCommon
 * Created by: fjmorales at 04/12/2012 20:10:34
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 * 
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.common.managers;

import java.util.HashMap;
import java.util.Map;

import com.reclabs.recomendar.common.exceptions.generic.ErrorException;
import com.reclabs.recomendar.common.helpers.types.StringHelper;
import com.reclabs.recomendar.common.types.Parameter;

/**
 * Objeto usado para la gestión de parámetros. Todos los parámetros deben
 * comenzar por -{nombreParametro}={valor}
 * 
 * @author raulanatol
 */
public class ParameterManager {
	/**
	 * Listado de todos los parámetros registrados.
	 */
	private final Map<String, Parameter> parameters;

	/**
	 * Constructor al que le pasamos todos los parámetros a registrar.
	 * 
	 * @param parameters
	 */
	public ParameterManager(final Parameter... parameters) {
		super();
		this.parameters = new HashMap<>();
		for (Parameter parameter : parameters) {
			this.parameters.put(parameter.getKey(), parameter);
		}
	}

	/**
	 * Parseamos todos los parámetros registrados dado el listado de args de la
	 * ejecución
	 * 
	 * @param args
	 * @throws ErrorException
	 *         En caso de que los parámetros no puedan ser parseados
	 */
	public void parse(final String[] args) throws ErrorException {
		for (String string : args) {
			String id = getIdentifier(string);
			if (!StringHelper.isEmpty(id) && this.parameters.containsKey(id)) {
				this.parameters.get(id).setValue(getParameterValue(string));
			}
		}
		// Miramos que todos los mandatory estén fijados.
		for (Parameter parameter : this.parameters.values()) {
			if (parameter.isMandatory() && parameter.getValue() == null) {
				throw new ErrorException("El parámetro " + parameter.getKey() + " es obligatorio y no se ha definido.", new Throwable());
			}
		}
	}

	/**
	 * Buscamos el valor de un parámetro dada una cadena de parámetro-valor.
	 * 
	 * @param string
	 * @return
	 */
	private String getParameterValue(final String string) {
		String result = null;
		String valueWithEqual = StringHelper.getSubstringFrom(string, "=");
		if (!StringHelper.isEmpty(valueWithEqual)) {
			result = valueWithEqual.substring(1);
		}
		return result;
	}

	/**
	 * Buscamos el identificador de un parámetro dada una cadena de
	 * parámetro-valor.
	 * 
	 * @param string
	 * @return
	 */
	private String getIdentifier(final String string) {
		return StringHelper.getSubstringBetween(string, "-", "=");
	}

	/**
	 * Dada la id de un parámetro obtenemos su valor.
	 * 
	 * @param modeParameter
	 * @return Valor asociado
	 */
	public String getValue(final String modeParameter) {
		return this.parameters.get(modeParameter).getValue();
	}
}
