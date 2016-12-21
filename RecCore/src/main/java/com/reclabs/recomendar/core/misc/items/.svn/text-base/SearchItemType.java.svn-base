/**
 * Project: RecCore
 * Created by: Fran at 25/06/13 14:04
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 * <p/>
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.misc.items;

import com.reclabs.recomendar.common.helpers.types.StringHelper;
import org.apache.commons.lang.NotImplementedException;

/**
 * Type of item search
 */
public enum SearchItemType {
    QUERY,
    QUERY_CATEGORY,
    QUERY_TAG,
    QUERY_CATEGORY_TAG,
    CATEGORY,
    CATEGORY_TAG,
    TAG,
    MULTI_TAG,
    QUERY_MULTI_TAG,
    QUERY_CATEGORY_MULTI_TAG,
    CATEGORY_MULTI_TAG;

    /**
     * Get the type of item search based on parameters
     * @param query Pattern of search
     * @param category Id of category
     * @param tag tag search field, can be String or String array
     * @return Type of item search
     */
    public static SearchItemType getSearchItemType(String query, String category, Object tag) {
        boolean queryNull = StringHelper.isEmpty(query);
        boolean categoryNull = StringHelper.isEmpty(category);
        boolean tagNull = (tag == null);
        boolean tagMulti = (tag != null && tag instanceof String[]);

        SearchItemType result;
        if (!queryNull && categoryNull && tagNull) {
            result = QUERY;
        } else if (!queryNull && !categoryNull && tagNull) {
            result = QUERY_CATEGORY;
        } else if (!queryNull && categoryNull && tagMulti) {
            result = QUERY_MULTI_TAG;
        } else if (!queryNull && categoryNull) {
            result = QUERY_TAG;
        } else if (!queryNull && tagMulti) {
            result = QUERY_CATEGORY_MULTI_TAG;
        } else if (!queryNull) {
            result = QUERY_CATEGORY_TAG;
        } else if (!categoryNull && tagNull) {
            result = CATEGORY;
        } else if (!categoryNull && tagMulti) {
            result = CATEGORY_MULTI_TAG;
        } else if (!categoryNull) {
            result = CATEGORY_TAG;
        } else if (!tagNull && tagMulti) {
            result = MULTI_TAG;
        } else if (!tagNull) {
            result = TAG;
        } else {
            throw new NotImplementedException();
        }
        return result;
    }
}
