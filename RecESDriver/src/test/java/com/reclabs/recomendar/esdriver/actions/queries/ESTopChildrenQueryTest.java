/**
 * Project: RecESDriver
 * Created by: Fran at 28/06/13 10:32
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.esdriver.actions.queries;

import com.reclabs.recomendar.esdriver.actions.internal.ESQuery;
import com.reclabs.recomendar.esdriver.actions.queries.generics.ESMatchQuery;
import com.reclabs.recomendar.esdriver.index.ESTypes;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Fran
 */
public class ESTopChildrenQueryTest {

    @Test
    public void generateSQLTest() {
        String expectedSQL = "{\n\"top_children\":{\n\"type\":\"recuser\",\n\"query\":{\n\"custom_score\":{\n\"query\":{ \"match\":\n { \"userId\": \"1234\" }\n},\n\"script\":\"doc['createdDate'].date.getMillis()\"\n}\n}\n}\n}";
        ESQuery classUnderTest = new ESTopChildrenQuery(ESTypes.RECUSER, new ESCustomScoreQuery("doc['createdDate'].date.getMillis()", new ESMatchQuery("userId", "1234")));
        String sql = classUnderTest.generateQuerySQL();
        assertThat(sql, is(expectedSQL));
    }
}
