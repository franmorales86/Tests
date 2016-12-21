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
import com.reclabs.recomendar.esdriver.types.ESScoreMode;

/**
 * @author Fran
 */
public class ESHasChildQuery implements ESQuery {

    private final String QUERY_PATTERN = "{\n\"has_child\":{\n\"type\":\"%1$s\",\n\"query\":%2$s\n}\n}";

    private final String QUERY_PATTERN_SCORE = "{\n\"has_child\":{\n\"type\":\"%1$s\",\n" +
            "\"score_mode\":\"%2$s\",\n\"query\":%3$s\n}\n}";

    /*"query": {
        "has_child": {
            "type": "recommend",
                    "query": {
                "match": {
                    "userId": "51b60dd70364e95eda0816ed"
                }
            }
        }
    } */

    private ESTypes type;
    private ESQuery query;
    private ESScoreMode scoreMode;

    /**
     * Constructor with parameters
     * @param type Type of item of search
     * @param query Query from has child
     */
    public ESHasChildQuery(ESTypes type, ESQuery query) {
        this.type = type;
        this.query = query;
    }

    /**
     * Constructor with parameters
     * @param type Type of item of search
     * @param query Query from has child
     */
    public ESHasChildQuery(ESTypes type, ESQuery query, ESScoreMode scoreMode) {
        this.type = type;
        this.query = query;
        this.scoreMode = scoreMode;
    }

    @Override
    public String generateQuerySQL() {
        if (this.scoreMode != null) {
            return String.format(QUERY_PATTERN_SCORE, type.getType(), scoreMode.getMode(), query.generateQuerySQL());
        } else {
            return String.format(QUERY_PATTERN, type.getType(), query.generateQuerySQL());
        }
    }
}
