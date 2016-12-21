/**
 * Project: RecESDriver
 * Created by: Fran at 27/06/13 16:25
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.esdriver.actions.queries;

import com.reclabs.recomendar.esdriver.actions.internal.ESQuery;
import com.reclabs.recomendar.esdriver.actions.queries.generics.ESMatchQuery;
import com.reclabs.recomendar.esdriver.actions.queries.generics.ESTextQuery;
import com.reclabs.recomendar.esdriver.index.ESTypes;
import com.reclabs.recomendar.esdriver.types.ESScoreMode;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Fran
 */
public class ESHasChildQueryTest {

    @Test
    public void generateSQLTest() {
        String expectedSQL = "{\n\"has_child\":{\n\"type\":\"recommend\",\n\"query\":{ \"match\":\n { \"userId\": \"1234\" }\n}\n}\n}";
        ESQuery classUnderTest = new ESHasChildQuery(ESTypes.RECOMMENDATIONS, new ESMatchQuery("userId", "1234"));
        String sql = classUnderTest.generateQuerySQL();
        assertThat(sql, is(expectedSQL));

        String expectedSQLScore = "{\n\"has_child\":{\n\"type\":\"vip\",\n\"score_mode\":\"avg\",\n\"query\":{\n" +
                "\"custom_score\":{\n" +
                "\"query\":{ \"text\":\n" +
                " { \"userId\": \"1234\" }\n" +
                "},\n" +
                "\"script\":\"doc['createdDate'].date.getMillis()\"\n" +
                "}\n" +
                "}\n}\n}";
        ESQuery query = new ESHasChildQuery(ESTypes.VIP, new ESCustomScoreQuery("doc['createdDate'].date.getMillis()", new ESTextQuery("userId", "1234")), ESScoreMode.AVG);
        String sql2 = query.generateQuerySQL();
        assertThat(sql2, is(expectedSQLScore));

    }

}
