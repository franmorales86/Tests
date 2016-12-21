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

/**
 * @author Fran
 */
public class ESCustomScoreQuery implements ESQuery {

    private final String QUERY_PATTERN = "{\n\"custom_score\":{\n\"query\":%1$s,\n\"script\":\"%2$s\"\n}\n}";

    private String script;
    private ESQuery query;

    /**
     * Constructor with parameters
     * @param script Script of score
     * @param query Query from has child
     */
    public ESCustomScoreQuery(String script, ESQuery query) {
        this.script = script;
        this.query = query;
    }

    @Override
    public String generateQuerySQL() {
        return String.format(QUERY_PATTERN, query.generateQuerySQL(), script);
    }
}
