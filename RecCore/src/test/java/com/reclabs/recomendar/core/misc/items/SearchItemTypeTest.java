/**
 * Project: RecCore
 * Created by: raulanatol at 01/07/13 18:51
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.misc.items;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author raulanatol
 * @see SearchItemType
 */
public class SearchItemTypeTest {

    /**
     * @see SearchItemType#getSearchItemType(String, String, Object)
     */
    @Test
    public void getSearchItemTypeResultQuery() {
        assertThat(SearchItemType.getSearchItemType("notNull", null, null), is(SearchItemType.QUERY));
    }

    /**
     * @see SearchItemType#getSearchItemType(String, String, Object)
     */
    @Test
    public void getSearchItemTypeResultQueryCategory() {
        assertThat(SearchItemType.getSearchItemType("notNull", "notnull", null), is(SearchItemType.QUERY_CATEGORY));
    }

    /**
     * @see SearchItemType#getSearchItemType(String, String, Object)
     */
    @Test
    public void getSearchItemTypeResultQueryTag() {
        assertThat(SearchItemType.getSearchItemType("notNull", null, "notNull"), is(SearchItemType.QUERY_TAG));
    }

    /**
     * @see SearchItemType#getSearchItemType(String, String, Object)
     */
    @Test
    public void getSearchItemTypeResultQueryCategoryTag() {
        assertThat(SearchItemType.getSearchItemType("notNull", "notNull", "notNull"), is(SearchItemType.QUERY_CATEGORY_TAG));
    }

    /**
     * @see SearchItemType#getSearchItemType(String, String, Object)
     */
    @Test
    public void getSearchItemTypeResultCategory() {
        assertThat(SearchItemType.getSearchItemType(null, "notNull", null), is(SearchItemType.CATEGORY));
    }

    /**
     * @see SearchItemType#getSearchItemType(String, String, Object)
     */
    @Test
    public void getSearchItemTypeResultCategoryTag() {
        assertThat(SearchItemType.getSearchItemType(null, "notNull", "notNull"), is(SearchItemType.CATEGORY_TAG));
    }

    /**
     * @see SearchItemType#getSearchItemType(String, String, Object)
     */
    @Test
    public void getSearchItemTypeResultTag() {
        assertThat(SearchItemType.getSearchItemType(null, null, "notNull"), is(SearchItemType.TAG));
    }

    /**
     * @see SearchItemType#getSearchItemType(String, String, Object)
     */
    @Test
    public void getSearchItemTypeResultMultiTag() {
        assertThat(SearchItemType.getSearchItemType(null, null, new String[]{"one", "two"}), is(SearchItemType.MULTI_TAG));
    }

    /**
     * @see SearchItemType#getSearchItemType(String, String, Object)
     */
    @Test
    public void getSearchItemTypeResultQueryMultiTag() {
        assertThat(SearchItemType.getSearchItemType("notNull", null, new String[]{"one", "two"}), is(SearchItemType.QUERY_MULTI_TAG));
    }

    /**
     * @see SearchItemType#getSearchItemType(String, String, Object)
     */
    @Test
    public void getSearchItemTypeResultQueryCategoryMultiTag() {
        assertThat(SearchItemType.getSearchItemType("notNull", "notNull", new String[]{"one", "two"}), is(SearchItemType.QUERY_CATEGORY_MULTI_TAG));
    }

    /**
     * @see SearchItemType#getSearchItemType(String, String, Object)
     */
    @Test
    public void getSearchItemTypeResultCategoryMultiTag() {
        assertThat(SearchItemType.getSearchItemType(null, "notNull", new String[]{"one", "two"}), is(SearchItemType.CATEGORY_MULTI_TAG));
    }
}
