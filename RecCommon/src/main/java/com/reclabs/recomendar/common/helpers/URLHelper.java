/**
 * Project: RecCommon
 * Created by: raulanatol at 02/12/2012 20:07:28
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.common.helpers;

import com.reclabs.recomendar.common.exceptions.RecIllegalArgumentException;
import com.reclabs.recomendar.common.exceptions.generic.UserException;
import com.reclabs.recomendar.common.helpers.types.StringHelper;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

/**
 * Helper de ayuda para el uso de las URLs
 * @author raulanatol
 */
public class URLHelper {
    /**
     * Verificamos si la url pasada por parámetro existe, está bien formada, y no da errores del tipo 404 y demás.
     * @param url La url a verificar.
     * @return true en caso que existea y sea coherente y false en caso contrario.
     */
    public static boolean verifyIfExists(String url) {
        boolean result = false;
        try {
            HttpURLConnection.setFollowRedirects(true);
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setInstanceFollowRedirects(true);
            connection.setRequestMethod("HEAD");
            result = (connection.getResponseCode() == HttpURLConnection.HTTP_OK);
        } catch (Exception ignore) {
            // empty ignore block
        }
        return result;
    }

    /**
     * Obtenemos un shortCode autogenerado.
     * @return El shortCode.
     */
    public static String generateShortCode() {
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            buffer.append(RandomHelper.getRandomCharacter());
        }
        return buffer.toString();
    }

    /**
     * Dada una url acortada obtenemos el código de la misma.
     * <b>Ejemplo</b> http://rcmd.me/12345sas Devolverá 12345sas
     * @param url La url completa.
     * @return El short code de la url acortada.
     * @throws UserException En caso de que la url no sea la correcta.
     */
    public static String getShortCodeFromURL(String url) throws UserException {
        String result = null;
        if (!StringHelper.isEmpty(url)) {
            int lastSlag = url.lastIndexOf('/');
            if (lastSlag != url.length() - 1 && lastSlag != -1) {
                result = url.substring(lastSlag + 1);
            }
        }
        if (StringHelper.isEmpty(result)) {
            throw new UserException("The url is invalid " + url);
        }
        return result;
    }

    /**
     * Gets the domain of a specific url
     * @param url The url to parse.
     * @return the domain url.
     */
    public static String getDomainURL(String url) {
        try {
            URI uri = new URI(url);
            String domain = uri.getHost();
            return domain.startsWith("www.") ? domain.substring(4) : domain;
        } catch (Exception e) {
            throw new RecIllegalArgumentException("The url is invalid: " + url);
        }
    }
}
