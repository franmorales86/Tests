/**
 * Project: RecCommon
 * Created by: raulanatol at 09/06/13 13:55
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.common.helpers.misc;

import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertTrue;

/**
 * @author raulanatol
 */
public class ImageHelperTest {

    @Test
    public void getImageDimensionsByURLWithValidURL() {
        Dimension dimension = ImageHelper.getImageDimensionsByURL("http://blog.recomendar.com/wp-content/uploads/2012/12/rec-blog.png");
        int width = dimension.width;
        int height = dimension.height;
        assertTrue(width > 0);
        assertTrue(height > 0);
    }
}
