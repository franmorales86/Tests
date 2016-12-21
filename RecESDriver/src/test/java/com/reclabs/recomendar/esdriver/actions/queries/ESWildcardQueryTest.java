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
import com.reclabs.recomendar.esdriver.actions.queries.generics.ESWildcardQuery;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Fran
 */
public class ESWildcardQueryTest {

    @Test
    public void generateSQLTest() {
        String expectedSQL = "{ \"wildcard\":\n { \"userId\": \"1234\" }\n}";
        ESQuery classUnderTest = new ESWildcardQuery("userId", "1234");
        String sql = classUnderTest.generateQuerySQL();
        assertThat(sql, is(expectedSQL));
    }

}
