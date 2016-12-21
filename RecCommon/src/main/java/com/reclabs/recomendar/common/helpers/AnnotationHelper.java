/**
 * Project: RecCommon
 * Created by: fjmorales at 04/12/2012 19:36:11
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 * 
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.common.helpers;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.reclabs.recomendar.common.exceptions.generic.ErrorException;

/**
 * Helper para el uso de annotations.
 * @author raulanatol
 */
public class AnnotationHelper {
	private static final Logger LOGGER = LoggerFactory.getLogger(AnnotationHelper.class);

	/**
	 * Dado un annotationClass y una clase para inspeccionar obtenemos el campo con dicha anotación.
	 * En caso de encontrarse más de uno se obtiene el primero que se encuentra.
	 * @param annotationClass
	 * @param class2Inspect
	 * @return Campo inspeccionado en la anotacion
	 */
	public static <T, S extends Annotation> Field getFieldWithAnnotation(final Class<S> annotationClass, final Class<T> class2Inspect) {
		Field result = null;
		Field[] fields = class2Inspect.getDeclaredFields();
		for (Field field : fields) {
			S annotation = field.getAnnotation(annotationClass);
			if (annotation != null) {
				result = field;
				break;
			}
		}
		return result;
	}

	/**
	 * Dada una {@link Annotation} obtenemos todos los campos marcados por dicha anotación de la
	 * clase pasada por parámetros.
	 * @param annotationClass
	 * @param class2Inspect
	 * @return Listado de campos marcados por dicha anotación
	 */
	public static <T, S extends Annotation> Field[] getFieldsWithAnnotation(final Class<S> annotationClass, final Class<T> class2Inspect) {
		List<Field> result = new ArrayList<>();

		Field[] fields = class2Inspect.getDeclaredFields();
		for (Field field : fields) {
			S annotation = field.getAnnotation(annotationClass);
			if (annotation != null) {
				field.setAccessible(true);
				result.add(field);
			}
		}

		Field[] res = {};
		return result.toArray(res);
	}

	/**
	 * Dado un entity obtenemos el nombre de la tabla ubicado en el {@link Annotation} {@link Entity} en el
	 * parámetro table
	 * @param entityClass
	 * @return Nombre de la tabla ubicado en la anotacion
	 * @throws ErrorException
	 */
	public static <T> String getTableName(final Class<T> entityClass) throws ErrorException {
		try {
			Table annotation = entityClass.getAnnotation(Table.class);
			return annotation.name();
		} catch (Exception e) {
			LOGGER.error("The qualifier @Table is necessary", e);
			throw new ErrorException("The qualifier @Table is necessary", e);
		}
	}
}
