/**
 * Project: RecESDriver
 * Created by: Fran at 27/06/13 15:46
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.esdriver.actions.queries;

import com.reclabs.recomendar.esdriver.actions.queries.generics.ESWildcardQuery;
import com.reclabs.recomendar.esdriver.types.BoolQueryField;
import com.reclabs.recomendar.esdriver.types.BooldFieldType;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Fran
 */
public class ESBoolQueryTest {
    /**
     * @see com.reclabs.recomendar.esdriver.actions.queries.ESBoolQuery#generateWhereConditions()
     */
    @Test
    public void generateWhereConditionsTest() {
        List<BoolQueryField> where = new ArrayList<>();
        where.add(new BoolQueryField(new ESWildcardQuery("item.tags.name", "*all*"), BooldFieldType.MUST));
        where.add(new BoolQueryField(new ESWildcardQuery("item.name", "*a*"), BooldFieldType.SHOULD));
        ESBoolQuery esBoolQuery = new ESBoolQuery(where);
        esBoolQuery.generateWhereConditions();
        assertThat(esBoolQuery.generatedMust, is("[{ \"wildcard\":\n { \"item.tags.name\": \"*all*\" }\n}]"));
        assertThat(esBoolQuery.generatedMustNot, is("[ ]"));
        assertThat(esBoolQuery.generateShould, is("[{ \"wildcard\":\n { \"item.name\": \"*a*\" }\n}]"));
    }

    /**
     * @see com.reclabs.recomendar.esdriver.actions.queries.ESBoolQuery#generateWhereConditions()
     */
    @Test
    public void generateWhereConditionsMultipleConditionsTest() {
        List<BoolQueryField> where = new ArrayList<>();
        where.add(new BoolQueryField(new ESWildcardQuery("item.tags.name", "*all*"), BooldFieldType.MUST));
        where.add(new BoolQueryField(new ESWildcardQuery("item.description", "*old*"), BooldFieldType.MUST));
        where.add(new BoolQueryField(new ESWildcardQuery("item.name", "*a*"), BooldFieldType.SHOULD));
        ESBoolQuery esQuery = new ESBoolQuery(where);
        esQuery.generateWhereConditions();
        assertThat(esQuery.generatedMust, is("[{ \"wildcard\":\n { \"item.tags.name\": \"*all*\" }\n},{ \"wildcard\":\n { \"item.description\": \"*old*\" }\n}]"));
        assertThat(esQuery.generatedMustNot, is("[ ]"));
        assertThat(esQuery.generateShould, is("[{ \"wildcard\":\n { \"item.name\": \"*a*\" }\n}]"));
    }

}
