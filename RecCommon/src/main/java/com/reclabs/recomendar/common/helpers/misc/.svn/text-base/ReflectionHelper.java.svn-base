/**
 * Project: RecCommon
 * Created by: fjmorales at 04/12/2012 19:20:19
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 * 
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.common.helpers.misc;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.reclabs.recomendar.common.exceptions.generic.ErrorException;
import com.reclabs.recomendar.common.exceptions.helpers.misc.ReflectionException;

/**
 * Clase de ayuda para las ejecuciones de reflexión
 * @author raulanatol
 */
public class ReflectionHelper {

	/**
	 * Dado un listado de elementos y un campo a buscar, obtenemos el listado del valor de ese campo en un
	 * Collection.
	 * Ejemplo:
	 * fieldName = "nombre"
	 * inputClassName = "User.class"
	 * outputClassName = "String.class"
	 * items2Find = Listado de usuarios del tipo { user:Paco, user:Manuel... }
	 * 
	 * Obtendríamos un List<String> con los valores = {Paco, Manuel...}
	 * @param fieldName El campo a buscar.
	 * @param inputClassName La clase en la que tenemos que buscar el campo.
	 * @param outputClassName La clase de salida del campo a buscar.
	 * @param items2Find Listado de elementos en los que tendremos que buscar el campo.
	 * @return El listado de elementos.
	 * @throws ReflectionException En caso de error de reflection.
	 */
	@SafeVarargs
	public static <T, R> List<R> getFieldValueByName(final String fieldName, final Class<T> inputClassName, final Class<R> outputClassName, final T... items2Find) throws ReflectionException {
		try {
			Field field = inputClassName.getDeclaredField(fieldName);
			field.setAccessible(true);
			List<R> result = new ArrayList<>();
			for (T item : items2Find) {
				result.add(outputClassName.cast(field.get(item)));
			}
			return result;
		} catch (SecurityException e) {
			throw new ReflectionException(e);
		} catch (NoSuchFieldException e) {
			throw new ReflectionException(e);
		} catch (IllegalArgumentException e) {
			throw new ErrorException("Error de parámetros no válido", e);
		} catch (IllegalAccessException e) {
			throw new ErrorException("Error de acceso no válido", e);
		}
	}

	/**
	 * Igual que {@link ReflectionHelper#getFieldValueByName(String, Class, Class, Object...)} pero esta vez se
	 * pasan los elementos a buscar en un ArrayList
	 * @param fieldName El campo a buscar.
	 * @param inputClassName La clase en la que tenemos que buscar el campo.
	 * @param outputClassName La clase de salida del campo a buscar.
	 * @param items2Find Listado de elementos en los que tendremos que buscar el campo.
	 * @return Listado de valores del campo.
	 * @throws ReflectionException
	 */
	public static <T, R> List<R> getFieldValueByName(final String fieldName, final Class<T> inputClassName, final Class<R> outputClassName, final List<T> items2Find) throws ReflectionException {
		try {
			Field field = inputClassName.getDeclaredField(fieldName);
			field.setAccessible(true);
			List<R> result = new ArrayList<>();
			for (T item : items2Find) {
				result.add(outputClassName.cast(field.get(item)));
			}
			return result;
		} catch (SecurityException e) {
			throw new ReflectionException(e);
		} catch (NoSuchFieldException e) {
			throw new ReflectionException(e);
		} catch (IllegalArgumentException e) {
			throw new ErrorException("Error de parámetros no válido", e);
		} catch (IllegalAccessException e) {
			throw new ErrorException("Error de acceso no válido", e);
		}
	}

	/**
	 * Dado un objeto obtenemos todos los campos del mismo que sean null.
	 * @param element El elemento al que se le hará reflexion.
	 * @param clazz La clase del elemento a hacer reflexion.
	 * @return Listado de todos los campos de la clase indicada que sean null.
	 */
	public static <T> List<String> getNullFields(T element, Class<T> clazz) {
		List<String> result = new ArrayList<>();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			try {
				field.setAccessible(true);
				Object value = field.get(element);
				if (value == null) {
					result.add(field.getName());
				}
			} catch (IllegalArgumentException | IllegalAccessException ignore) {
				// empty
			}
		}
		return result;
	}
}
