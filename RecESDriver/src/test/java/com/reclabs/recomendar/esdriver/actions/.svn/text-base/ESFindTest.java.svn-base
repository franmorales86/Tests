/**
 * Project: RecESDriver
 * Created by: raulanatol at 13/05/13 10:45
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.esdriver.actions;

import com.reclabs.recomendar.esdriver.actions.queries.ESBoolQuery;
import com.reclabs.recomendar.esdriver.actions.queries.generics.ESWildcardQuery;
import com.reclabs.recomendar.esdriver.index.IndexType;
import com.reclabs.recomendar.esdriver.types.BoolQueryField;
import com.reclabs.recomendar.esdriver.types.BooldFieldType;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author raulanatol
 */
public class ESFindTest {

    /**
     * @see com.reclabs.recomendar.esdriver.actions.ESFind#generateSQL()
     */
    @Test
    public void generateSQLTest() {
        String expectedSQL = "{\n\"query\":{\n\"bool\":{\n\"must\":[{ \"wildcard\":\n { \"item.tags.name\": \"*all*\" }\n},{ \"wildcard\":\n { \"item.name\": \"*a*\" }\n}], \"must_not\":[ ], \"should\":[ ],\n\"minimum_should_match\":1\n}\n},\n\"from\":0,\n\"size\":20}";
        List<BoolQueryField> whereClause = new ArrayList<>();
        whereClause.add(new BoolQueryField(new ESWildcardQuery("item.tags.name", "*all*"), BooldFieldType.MUST));
        whereClause.add(new BoolQueryField(new ESWildcardQuery("item.name", "*a*"), BooldFieldType.MUST));
        ESFind classUnderTest = new ESFind(IndexType.ITEM_INDEX, new ESBoolQuery(whereClause));
        String sql = classUnderTest.generateSQL();
        assertThat(sql, is(expectedSQL));
    }

    /**
     * @see com.reclabs.recomendar.esdriver.actions.ESFind#generateFieldsJSON()
     */
    @Test
    public void generateFieldsJSONTest() {
        String expected = "\"fields\": [\"item.name\",\"item.description\"],";
        List<String> fields = Arrays.asList("item.name", "item.description");
        ESFind esFind = new ESFind(null, fields, null);
        assertThat(esFind.generateFieldsJSON(), is(expected));
    }

}
