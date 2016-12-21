/**
 * Project: RecESDriver
 * Created by: Fran at 27/06/13 18:35
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.esdriver.actions.queries;

import com.reclabs.recomendar.esdriver.actions.internal.ESQuery;
import com.reclabs.recomendar.esdriver.actions.queries.generics.ESTextQuery;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Fran
 */
public class ESTextQueryTest {

    @Test
    public void generateSQLTest() {
        String expectedSQL = "{ \"text\":\n { \"userId\": \"1234\" }\n}";
        ESQuery classUnderTest = new ESTextQuery("userId", "1234");
        String sql = classUnderTest.generateQuerySQL();
        assertThat(sql, is(expectedSQL));
    }

}
