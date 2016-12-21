/**
 * Project: RecESDriver
 * Created by: raulanatol at 20/05/13 17:39
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.esdriver.helper;

import com.reclabs.recomendar.common.exceptions.RecIllegalArgumentException;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author raulanatol
 * @see ESHelper
 */
public class ESHelperTest {

    /**
     * @see ESHelper#toExactlyWords(String)
     */
    @Test(expected = RecIllegalArgumentException.class)
    public void toExactlyWordsWithNull() {
        ESHelper.toExactlyWords(null);
    }

    /**
     * @see ESHelper#toExactlyWords(String)
     */
    @Test(expected = RecIllegalArgumentException.class)
    public void toExactlyWordsWithEmpty() {
        ESHelper.toExactlyWords("");
    }

    /**
     * @see ESHelper#toExactlyWords(String)
     */
    @Test
    public void toExactlyWordsWithOneWord() {
        assertThat(ESHelper.toExactlyWords("one"), is("one"));
    }

    /**
     * @see ESHelper#toExactlyWords(String)
     */
    @Test
    public void toExactlyWordsWithMultipleWord() {
        assertThat(ESHelper.toExactlyWords("one two three"), is("one AND two AND three"));
    }
}
