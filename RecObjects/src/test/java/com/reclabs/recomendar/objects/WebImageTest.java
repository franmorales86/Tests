/**
 * Project: RecObjects
 * Created by: raulanatol at 13/06/13 22:05
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.objects;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author raulanatol
 */
public class WebImageTest {

    @Test
    public void integrateTest() {
        WebImage webImage = new WebImage();
        webImage.setName("test");
        assertThat(webImage.getName(), is("test"));
    }
}
