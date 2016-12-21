/**
 * Project: RecESDriver
 * Created by: Fran at 27/06/13 17:52
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.esdriver.actions.queries;

import com.reclabs.recomendar.esdriver.actions.internal.ESQuery;

/**
 * @author Fran
 */
public class ESQueryStringQuery implements ESQuery {

    private static final String QUERY_STRING_PATTERN = "{ \"query_string\":\n { \"default_field\": \"%1$s\",\n\"query\": \"%2$s\" }\n}";

    private String fieldName;
    private String matchValue;

    /**
     * Constructor with parameters
     * @param fieldName Name of field
     * @param matchValue Value of field
     */
    public ESQueryStringQuery(String fieldName, String matchValue) {
        this.fieldName = fieldName;
        this.matchValue = matchValue;
    }

    @Override
    public String generateQuerySQL() {
        return String.format(QUERY_STRING_PATTERN, fieldName, matchValue);
    }
}
