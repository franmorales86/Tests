/**
 * Project: RecCore
 * Created by: raulanatol at 29/01/2013 17:40:09
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.config.filters;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Filtro para la activación de CORS
 * @author raulanatol
 */
public class CorsFilter extends OncePerRequestFilter {
    private static final String ACCESS_CONTROL_MAX_AGE = "Access-Control-Max-Age";
    private static final String ACCESS_CONTROL_ALLOW_HEADERS = "Access-Control-Allow-Headers";
    private static final String ACCESS_CONTROL_ALLOW_METHODS = "Access-Control-Allow-Methods";
    private static final String ACCESS_CONTROL_ALLOW_ORIGIN = "Access-Control-Allow-Origin";
    // private static final String ACCESS_CONTROL_REQUEST_METHOD = "Access-Control-Request-Method";
    private static final String ACCESS_CONTROL_ALLOW_CREDENTIALS = "Access-Control-Allow-Credentials";

    /*
     * (non-Javadoc)
     * @see
     * org.springframework.web.filter.OncePerRequestFilter#doFilterInternal(javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain)
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        response.addHeader(ACCESS_CONTROL_ALLOW_ORIGIN, "*");
        // if (request.getHeader(ACCESS_CONTROL_REQUEST_METHOD) != null && "OPTIONS".equals(request.getMethod())) {
        // response.addHeader(ACCESS_CONTROL_ALLOW_METHODS, "GET, POST, PUT, DELETE");
        // response.addHeader(ACCESS_CONTROL_ALLOW_HEADERS, "Authorization");
        // response.addHeader(ACCESS_CONTROL_MAX_AGE, "1728000");
        // }
        switch (request.getMethod()) {
            case "POST":
                response.addHeader(ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
                response.addHeader(ACCESS_CONTROL_ALLOW_METHODS, "GET, POST, PUT, DELETE");
                response.addHeader(ACCESS_CONTROL_ALLOW_HEADERS, "DNT,X-Mx-ReqToken,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type");
                break;
            case "GET":
                response.addHeader(ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
                response.addHeader(ACCESS_CONTROL_ALLOW_METHODS, "GET, POST, PUT, DELETE");
                response.addHeader(ACCESS_CONTROL_ALLOW_HEADERS, "DNT,X-Mx-ReqToken,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type");
                break;
            case "OPTIONS":
                response.addHeader(ACCESS_CONTROL_ALLOW_METHODS, "GET, POST, PUT, DELETE");
                response.addHeader(ACCESS_CONTROL_ALLOW_HEADERS, "Authorization,Content-Type");
                response.addHeader(ACCESS_CONTROL_MAX_AGE, "1728000");
                break;
            default:
                response.addHeader(ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
                response.addHeader(ACCESS_CONTROL_ALLOW_METHODS, "GET, POST, PUT, DELETE");
                response.addHeader(ACCESS_CONTROL_ALLOW_HEADERS, "DNT,X-Mx-ReqToken,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type");
        }

        filterChain.doFilter(request, response);
    }
}
