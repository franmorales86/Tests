/**
 * Project: RecESDriver
 * Created by: Fran at 27/06/13 16:05
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.esdriver.actions.queries;

import com.reclabs.recomendar.esdriver.actions.internal.ESQuery;
import com.reclabs.recomendar.esdriver.index.ESTypes;

/**
 * @author Fran
 */
public class ESTopChildrenQuery implements ESQuery {

    private final String QUERY_PATTERN = "{\n\"top_children\":{\n\"type\":\"%1$s\",\n\"query\":%2$s\n}\n}";

    private ESTypes type;
    private ESQuery query;

    /**
     * Constructor with parameters
     * @param type Type of item of search
     * @param query Query from has child
     */
    public ESTopChildrenQuery(ESTypes type, ESQuery query) {
        this.type = type;
        this.query = query;
    }

    @Override
    public String generateQuerySQL() {
        return String.format(QUERY_PATTERN, type.getType(), query.generateQuerySQL());
    }
}
