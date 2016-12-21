/**
 * Project: RecCore
 * Created by: raulanatol at 23/08/13 10:05
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.misc;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author raulanatol
 */
public class EnvTypeTest {

    @Test
    public void fromValueTest() throws Exception {
        EnvType current = EnvType.fromValue("\"PRO\"");
        assertThat(current, is(EnvType.PRO));
    }

    @Test
    public void fromValueSimpleTest() throws Exception {
        EnvType current = EnvType.fromValue("PRO");
        assertThat(current, is(EnvType.PRO));
    }
}
