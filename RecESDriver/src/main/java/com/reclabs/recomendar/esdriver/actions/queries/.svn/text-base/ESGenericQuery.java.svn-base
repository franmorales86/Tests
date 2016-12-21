/**
 * Project: RecESDriver
 * Created by: Fran at 27/06/13 17:39
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.esdriver.actions.queries;

import com.reclabs.recomendar.esdriver.actions.internal.ESQuery;
import com.reclabs.recomendar.esdriver.types.GenericQueryType;

/**
 * @author Fran
 */
public class ESGenericQuery implements ESQuery {

    private static final String CODE_PARSER = "{ \"%1$s\":\n { \"%2$s\": \"%3$s\" }\n}";
    protected String name;
    protected String fieldName;
    protected String matchValue;

    /**
     * Constructor with parameters
     * @param queryType Type of generic query
     * @param fieldName Name of field
     * @param matchValue Value of field
     */
    public ESGenericQuery(GenericQueryType queryType, String fieldName, String matchValue) {
        this.name = queryType.getName();
        this.fieldName = fieldName;
        this.matchValue = matchValue;
    }

    @Override
    public String generateQuerySQL() {
        return String.format(CODE_PARSER, name, fieldName, matchValue);

    }
}
