/**
 * Project: RecESDriver
 * Created by: raulanatol at 13/05/13 12:58
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.esdriver.types;

import com.reclabs.recomendar.esdriver.actions.internal.ESQuery;

/**
 * It's a representation of an field of a bool query sentence.
 * @author raulanatol
 */
public class BoolQueryField {

    private ESQuery query;
    private BooldFieldType conditionType;

    /**
     * @param query
     * @param conditionType
     */
    public BoolQueryField(ESQuery query, BooldFieldType conditionType) {
        super();
        this.query = query;
        this.conditionType = conditionType;
    }

    public ESQuery getQuery() {
        return query;
    }

    public void setQuery(ESQuery query) {
        this.query = query;
    }

    public BooldFieldType getConditionType() {
        return conditionType;
    }

    public void setConditionType(BooldFieldType conditionType) {
        this.conditionType = conditionType;
    }
}
