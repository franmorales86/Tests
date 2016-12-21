/**
 * Project: RecCore
 * Created by: raulanatol at 13/11/2012 18:10:10
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.controllers.social;

import com.reclabs.recomendar.core.misc.security.SecurityHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author raulanatol
 */
@Controller
public class HomeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping("/")
    public void init(HttpServletResponse response) {
        try {
            LOGGER.warn("[Trying to access to / path]");
            response.sendRedirect("http://recomendar.com");
        } catch (IOException e) {
            LOGGER.error("Error on redirect", e);
        }
    }

    /**
     * @return The linkTwitter result
     */
    @RequestMapping("/linkTwitter")
    public String linkTwitter() {
        String result = "linkTwitter";
        //TODO cuando se ha perdido el login del usuario se mostrará un error. Así que tendremos que gestionar la forma de recargar el login.
        boolean existLoggedUser = SecurityHelper.existLoggedUser();
        if (!existLoggedUser) {
            result = "404";
            LOGGER.warn("[Trying to linkTwitter with null logged user");
        }
        return result;
    }

    /**
     * @return
     */
    @RequestMapping("/linkFacebook")
    public String linkFacebook() {
        String result = "linkFacebook";
        //TODO cuando se ha perdido el login del usuario se mostrará un error. Así que tendremos que gestionar la forma de recargar el login.
        boolean existLoggedUser = SecurityHelper.existLoggedUser();
        if (!existLoggedUser) {
            result = "404";
            LOGGER.warn("[Trying to linkTwitter with null logged user");
        }
        return result;

    }

    @RequestMapping(value = "/linkFacebookSec", method = RequestMethod.GET)
    @PreAuthorize("#oauth2.isUser() and #oauth2.clientHasRole('ROLE_USER') and #oauth2.hasScope('write')")
    public String secureLinkFacebook() {
        String result = "linkFacebook";
        boolean existLoggedUser = SecurityHelper.existLoggedUser();
        if (!existLoggedUser) {
            LOGGER.error("OAuth request with a null user");
            result = "404";
        }
        return result;
    }
}