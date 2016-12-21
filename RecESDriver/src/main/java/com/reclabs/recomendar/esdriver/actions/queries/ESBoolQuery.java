/**
 * Project: RecESDriver
 * Created by: Fran at 27/06/13 15:22
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.esdriver.actions.queries;

import com.reclabs.recomendar.common.helpers.types.CollectionHelper;
import com.reclabs.recomendar.esdriver.actions.internal.ESQuery;
import com.reclabs.recomendar.esdriver.types.BoolQueryField;
import org.apache.commons.lang.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Fran
 */
public class ESBoolQuery implements ESQuery {

    private final String QUERY_PATTERN = "{\n\"bool\":{\n\"must\":%1$s, \"must_not\":%2$s, \"should\":%3$s,\n\"minimum_should_match\":1\n}\n}";
    /**
     * Lists of where conditions in the query
     */
    private List<BoolQueryField> whereClause;
    protected String generatedMust = "[ ]";
    protected String generatedMustNot = "[ ]";
    protected String generateShould = "[ ]";

    /**
     * Constructor with parameters
     * @param whereClause
     */
    public ESBoolQuery(List<BoolQueryField> whereClause) {
        this.whereClause = whereClause;
    }

    /**
     * Constructor without parameters
     */
    public ESBoolQuery() {
        this.whereClause = new ArrayList<>();
    }

    @Override
    public String generateQuerySQL() {
        generateWhereConditions();
        return String.format(QUERY_PATTERN, generatedMust, generatedMustNot, generateShould);
    }

    /**
     *
     */
    protected void generateWhereConditions() {
        StringBuffer mustBuffer = new StringBuffer();
        StringBuffer mustNoBuffer = new StringBuffer();
        StringBuffer shouldBuffer = new StringBuffer();

        if (CollectionHelper.isEmpty(whereClause)) {
            generatedMust = "[{\"match_all\":{}}]";
        } else {
            for (BoolQueryField whereItem : whereClause) {
                switch (whereItem.getConditionType()) {
                    case MUST:
                        mustBuffer.append(whereItem.getQuery().generateQuerySQL());
                        mustBuffer.append(",");
                        break;
                    case MUST_NOT:
                        mustNoBuffer.append(whereItem.getQuery().generateQuerySQL());
                        mustNoBuffer.append(",");
                        break;
                    case SHOULD:
                        shouldBuffer.append(whereItem.getQuery().generateQuerySQL());
                        shouldBuffer.append(",");
                        break;
                    default:
                        throw new NotImplementedException();
                }
            }

            if (mustBuffer.length() > 0) {
                generatedMust = "[" + mustBuffer.substring(0, mustBuffer.length() - 1) + "]";
            }
            if (mustNoBuffer.length() > 0) {
                generatedMustNot =  "[" + mustNoBuffer.substring(0, mustNoBuffer.length() - 1) + "]";
            }
            if (shouldBuffer.length() > 0) {
                generateShould = "[" + shouldBuffer.substring(0, shouldBuffer.length() - 1) + "]";
            }
        }
    }

}
