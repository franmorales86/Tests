/**
 * Project: RecESDriver
 * Created by: raulanatol at 13/05/13 13:12
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.esdriver.types;

import com.reclabs.recomendar.esdriver.actions.queries.ESQueryStringQuery;
import com.reclabs.recomendar.esdriver.actions.queries.generics.ESTextQuery;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author raulanatol
 */
public class BoolQueryFieldTest {

    /**
     * @see BoolQueryField#getQuery()
     */
    @Test
    public void getSimpleCodeTest() {
        BoolQueryField field = new BoolQueryField(new ESTextQuery("item.category", "hogar"), BooldFieldType.MUST);

        String expectedCode = "{ \"text\":\n { \"item.category\": \"hogar\" }\n}";
        assertThat(field.getQuery().generateQuerySQL(), is(expectedCode));
    }

    /**
     * @see BoolQueryField#getQuery()
     */
    @Test
    public void getSimpleCodeQueryStringTest() {
        BoolQueryField field = new BoolQueryField(new ESQueryStringQuery("item.category", "hogar"), BooldFieldType.MUST);

        String expectedCode = "{ \"query_string\":\n { \"default_field\": \"item.category\",\n\"query\": \"hogar\" }\n}";
        assertThat(field.getQuery().generateQuerySQL(), is(expectedCode));
    }
}
