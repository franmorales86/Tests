/**
 * Project: RecCore
 * Created by: raulanatol at 04/10/13 12:56
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.misc.security;

import com.reclabs.recomendar.core.exceptions.login.RecUserPendingMailLoginException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author raulanatol
 */
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomAuthenticationFailureHandler.class);

    private String pendingMailFailureURL;

    public CustomAuthenticationFailureHandler(String defaultFailureUrl, String pendingMailFailureURL) {
        super(defaultFailureUrl);
        this.pendingMailFailureURL = pendingMailFailureURL;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        if (exception instanceof RecUserPendingMailLoginException) {
            getRedirectStrategy().sendRedirect(request, response, pendingMailFailureURL);
        } else {
            super.onAuthenticationFailure(request, response, exception);
        }
    }
}
