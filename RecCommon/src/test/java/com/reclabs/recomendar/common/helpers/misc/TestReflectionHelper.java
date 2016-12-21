/**
 * Project: RecCommon
 * Created by: fjmorales at 04/12/2012 21:09:35
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 * 
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.common.helpers.misc;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;

import com.reclabs.recomendar.common.exceptions.generic.ErrorException;
import com.reclabs.recomendar.common.exceptions.helpers.misc.ReflectionException;
import com.reclabs.recomendar.common.helpers.DummyObject;

/**
 * Casos de prueba de la clase {@link ReflectionHelper}
 * @author raulanatol
 */
public class TestReflectionHelper {
	/**
	 * Prueba con un dato simple
	 * @throws ErrorException
	 * @throws ReflectionException
	 */
	@Test
	public void getFieldValueByNameSimpleData() throws ReflectionException, ErrorException {
		DummyObject dummyObject = new DummyObject();
		dummyObject.setDummyField1("Value1");
		assertThat(ReflectionHelper.getFieldValueByName("dummyField1", DummyObject.class, String.class, dummyObject), is(Arrays.asList("Value1")));
	}

	/**
	 * Prueba con multidatos
	 * @throws ErrorException
	 * @throws ReflectionException
	 */
	@Test
	public void getFieldValueByNameMultipleData() throws ReflectionException, ErrorException {
		DummyObject dummyObject = new DummyObject();
		dummyObject.setDummyField1("Value1");

		DummyObject dummyObject1 = new DummyObject();
		dummyObject1.setDummyField1("Value2");

		assertThat(ReflectionHelper.getFieldValueByName("dummyField1", DummyObject.class, String.class, dummyObject, dummyObject1), is(Arrays.asList("Value1", "Value2")));
		assertThat(ReflectionHelper.getFieldValueByName("dummyField1", DummyObject.class, String.class, dummyObject1, dummyObject), is(Arrays.asList("Value2", "Value1")));
	}

	/**
	 * @see ReflectionHelper#getNullFields(Object, Class)
	 */
	@Test
	public void getNullFieldsBasicTest() {
		DummyObject dummyObject = new DummyObject();
		dummyObject.setDummyField1(null);
		dummyObject.setDummyField2(15);
		Collection<String> result = ReflectionHelper.getNullFields(dummyObject, DummyObject.class);
		Collection<String> expected = Arrays.asList("dummyField1");
		assertEquals(expected, result);
	}
}
