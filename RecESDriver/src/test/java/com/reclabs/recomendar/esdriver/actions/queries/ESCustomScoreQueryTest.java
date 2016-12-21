/**
 * Project: RecESDriver
 * Created by: Fran at 28/06/13 10:47
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
public class ESCustomScoreQueryTest {

    @Test
    public void generateSQLTest() {
        String expectedSQL = "{\n\"custom_score\":{\n\"query\":{ \"text\":\n { \"userId\": \"1234\" }\n},\n\"script\":\"doc['createdDate'].date.getMillis()\"\n}\n}";
        ESQuery classUnderTest = new ESCustomScoreQuery("doc['createdDate'].date.getMillis()", new ESTextQuery("userId", "1234"));
        String sql = classUnderTest.generateQuerySQL();
        assertThat(sql, is(expectedSQL));
    }
}
