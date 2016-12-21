/**
 * Project: RecCore
 * Created by: raulanatol at 08/05/2013 12:13:17
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.misc.security.tokens;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @author raulanatol
 */
public class TokenHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(TokenHelper.class);

    /**
     * Dado un request con un token de tipo oauth lo parseamos y obtenemos el valor del mismo.
     * @param request
     * @return El token parseado
     */
    public static String parseToken(HttpServletRequest request) {
        // first check the header...
        String token = parseHeaderToken(request);

        // bearer type allows a request parameter as well
        if (token == null) {
            LOGGER.debug("Token not found in headers. Trying request parameters.");
            token = request.getParameter(OAuth2AccessToken.ACCESS_TOKEN);
            if (token == null) {
                LOGGER.debug("Token not found in request parameters.  Not an OAuth2 request.");
            }
        }

        return token;
    }

    /**
     * Parse the OAuth header parameters. The parameters will be oauth-decoded.
     * @param request The request.
     * @return The parsed parameters, or null if no OAuth authorization header was supplied.
     */
    private static String parseHeaderToken(HttpServletRequest request) {
        @SuppressWarnings("unchecked")
        Enumeration<String> headers = request.getHeaders("Authorization");
        while (headers.hasMoreElements()) { // typically there is only one (most servers enforce that)
            String value = headers.nextElement();
            if ((value.toLowerCase().startsWith(OAuth2AccessToken.BEARER_TYPE.toLowerCase()))) {
                String authHeaderValue = value.substring(OAuth2AccessToken.BEARER_TYPE.length()).trim();
                int commaIndex = authHeaderValue.indexOf(',');
                if (commaIndex > 0) {
                    authHeaderValue = authHeaderValue.substring(0, commaIndex);
                }
                return authHeaderValue;
            }
        }
        return null;
    }

}
