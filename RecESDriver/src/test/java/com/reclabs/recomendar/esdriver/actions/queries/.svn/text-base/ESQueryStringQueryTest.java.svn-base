/**
 * Project: RecESDriver
 * Created by: Fran at 27/06/13 18:22
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.esdriver.actions.queries;

import com.reclabs.recomendar.esdriver.actions.internal.ESQuery;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Fran
 */
public class ESQueryStringQueryTest {

    @Test
    public void generateSQLTest() {
        String expectedSQL = "{ \"query_string\":\n { \"default_field\": \"userId\",\n\"query\": \"1234\" }\n}";
        ESQuery classUnderTest = new ESQueryStringQuery("userId", "1234");
        String sql = classUnderTest.generateQuerySQL();
        assertThat(sql, is(expectedSQL));
    }
}
