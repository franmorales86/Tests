/**
 * Project: RecCommon
 * Created by: fjmorales at 04/12/2012 19:41:42
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 * 
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.common.helpers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.reclabs.recomendar.common.exceptions.helpers.PropertiesFileNotFound;

/**
 * Helper para la ayuda y obtención de elementos y ficheros de propiedades
 * @author rmorales
 */
public class PropertiesHelper {

	/**
	 * Obtenemos un objeto de propiedades a través del nombre pasado por parámetros
	 * @param propertiesFileName El nombre del fichero pasado por parámetros
	 * @param classLoader La clase que solicita el fichero
	 * @return El fichero properties con las propiedades
	 * @throws PropertiesFileNotFound En caso de que no se encuentre el fichero de propiedades o no pueda ser
	 *         leído.
	 */
	public static <T> Properties getPropertiesFile(final String propertiesFileName, final Class<T> classLoader) throws PropertiesFileNotFound {
		try {
			Properties properties = new Properties();
			properties.load(classLoader.getClassLoader().getResourceAsStream(propertiesFileName));
			return properties;
		} catch (FileNotFoundException e) {
			throw new PropertiesFileNotFound(propertiesFileName, e);
		} catch (IOException e) {
			throw new PropertiesFileNotFound(propertiesFileName, e);
		} catch (RuntimeException e) {
			throw new PropertiesFileNotFound(propertiesFileName, e);
		}
	}
}
