/**
 * Project: RecCommon
 * Created by: raulanatol at 05/12/13 15:03
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.common.helpers.types;

import com.reclabs.recomendar.common.exceptions.RecIllegalArgumentException;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author raulanatol
 */
public class MimeTypeHelperTest {


    @Test
    public void parseContentTypeWithNormalValue() throws Exception {
        assertThat(MimeTypeHelper.parseContentType("text/html; utf-8"), is("text/html"));
    }

    @Test
    public void parseContentTypeWithSimpleValue() throws Exception {
        assertThat(MimeTypeHelper.parseContentType("text/html"), is("text/html"));
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void parseContentTypeWithEmptyParameter() throws Exception {
        MimeTypeHelper.parseContentType("  ");
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void parseContentTypeWithNullParameter() throws Exception {
        MimeTypeHelper.parseContentType(null);
    }


}
