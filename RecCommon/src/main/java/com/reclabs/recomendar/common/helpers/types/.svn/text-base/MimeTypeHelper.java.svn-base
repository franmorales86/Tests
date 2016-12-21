/**
 * Project: RecCommon
 * Created by: raulanatol at 05/12/13 14:03
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.common.helpers.types;

import com.reclabs.recomendar.common.helpers.ParameterHelper;
import com.reclabs.recomendar.common.types.MimeType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;

/**
 * @author raulanatol
 */
public class MimeTypeHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(MimeTypeHelper.class);

    /**
     * Gets the mime type af an specified url
     * @param urlValue The url
     * @return The mimeType
     */
    public static MimeType getMimeTypeFromURL(String urlValue) {
        MimeType result;
        try {
            URL url = new URL(urlValue);
            String contentType = url.openConnection().getContentType();
            result = MimeType.fromString(parseContentType(contentType));
        } catch (Exception e) {
            LOGGER.warn("[Trying to get the mime tye of an invalid url: {}]", urlValue, e);
            result = MimeType.UNKNOWN;
        }
        return result;
    }

    protected static String parseContentType(String contentType) {
        ParameterHelper.assertEmpty(contentType);
        String[] values = contentType.split(";");
        return (values.length > 0) ? values[0] : "";
    }
}
