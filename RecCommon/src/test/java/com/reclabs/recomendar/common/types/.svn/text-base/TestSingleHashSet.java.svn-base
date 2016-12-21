/**
 * Project: RecCommon
 * Created by: fjmorales at 04/12/2012 21:11:59
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 * 
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.common.types;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.reclabs.recomendar.common.helpers.types.CollectionHelper;

/**
 * @see SingleHashSet
 * @author raulanatol
 */
public class TestSingleHashSet {

	/**
	 * @see SingleHashSet#addItem(Object, Object)
	 */
	@SuppressWarnings("boxing")
	@Test
	public void simpleAddItemTest() {
		SingleHashSet<String, String> item = new SingleHashSet<>();
		item.addItem("UNO", "UNO.UNO");
		item.addItem("UNO", "UNO.DOS");
		item.addItem("UNO", "UNO.TRES");

		Set<String> keySet = item.getIteratorKey1();

		assertThat(keySet.size(), is(1));
		assertTrue(keySet.contains("UNO"));
	}

	/**
	 * @see SingleHashSet#addItem(Object, Object)
	 */
	@SuppressWarnings("boxing")
	@Test
	public void multipleItemsAddsTest() {
		SingleHashSet<String, String> item = new SingleHashSet<>();
		item.addItem("UNO", "UNO.UNO");
		item.addItem("UNO", "UNO.DOS");
		item.addItem("UNO", "UNO.TRES");
		item.addItem("DOS", "UNO.TRES");
		item.addItem("DOS", "UNO.TRES");
		item.addItem("TRES", "UNO.TRES");

		Set<String> keySet = item.getIteratorKey1();

		assertThat(keySet.size(), is(3));
		assertTrue(keySet.contains("UNO"));
		assertTrue(keySet.contains("DOS"));
		assertTrue(keySet.contains("TRES"));
	}

	/**
	 * @see SingleHashSet#addItem(Object, Object)
	 */
	@SuppressWarnings("boxing")
	@Test
	public void emptyObjectTest() {
		SingleHashSet<String, String> item = new SingleHashSet<>();
		Set<String> keySet = item.getIteratorKey1();

		assertThat(keySet.size(), is(0));
	}

	/**
	 * @see SingleHashSet#addItem(Object, Object)
	 */
	@SuppressWarnings("boxing")
	@Test
	public void key2AddTest() {
		SingleHashSet<String, String> item = new SingleHashSet<>();
		item.addItem("UNO", "UNO.UNO");
		item.addItem("UNO", "UNO.DOS");
		item.addItem("UNO", "UNO.TRES");
		item.addItem("DOS", "UNO.TRES");
		item.addItem("DOS", "UNO.TRES");
		item.addItem("TRES", "UNO.TRES");

		HashSet<String> key1Set = item.getKey2ListByKey1("UNO");
		assertThat(key1Set.size(), is(3));
		assertTrue(key1Set.contains("UNO.UNO"));
		assertTrue(key1Set.contains("UNO.DOS"));
		assertTrue(key1Set.contains("UNO.TRES"));

		HashSet<String> key2Set = item.getKey2ListByKey1("DOS");
		assertThat(key2Set.size(), is(1));
		assertTrue(key2Set.contains("UNO.TRES"));

		HashSet<String> key3Set = item.getKey2ListByKey1("TRES");
		assertThat(key3Set.size(), is(1));
		assertTrue(key3Set.contains("UNO.TRES"));
	}

	/**
	 * @see SingleHashSet#addItem(Object, Object)
	 */
	@Test
	public void key2GetUnknown() {
		SingleHashSet<String, String> item = new SingleHashSet<>();
		item.addItem("UNO", "UNO.UNO");
		item.addItem("DOS", "UNO.DOS");
		HashSet<String> result = item.getKey2ListByKey1("TRES");
		assertTrue(CollectionHelper.isEmpty(result));
	}
}
