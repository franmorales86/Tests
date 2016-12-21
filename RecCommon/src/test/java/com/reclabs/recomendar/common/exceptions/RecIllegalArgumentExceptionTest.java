/**
 * Project: RecCommon
 * Created by: raulanatol at 12/09/13 15:31
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.common.exceptions;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author raulanatol
 */
public class RecIllegalArgumentExceptionTest {

    @Test
    public void toStringParametersWithNullValues() throws Exception {
        assertThat(RecIllegalArgumentException.toStringParameters((String) null), is("Illegal parameters"));
    }

    @Test
    public void toStringParametersWithSimpleValue() throws Exception {
        assertThat(RecIllegalArgumentException.toStringParameters("a"), is("Illegal parameters a"));
    }

    @Test
    public void toStringParametersWithMultipleValue() throws Exception {
        assertThat(RecIllegalArgumentException.toStringParameters("a", "b", "c"), is("Illegal parameters a b c"));
    }

    @Test
    public void toStringParametersWithHalfValuesValue() throws Exception {
        assertThat(RecIllegalArgumentException.toStringParameters("a", null, "c"), is("Illegal parameters a c"));
    }
}
