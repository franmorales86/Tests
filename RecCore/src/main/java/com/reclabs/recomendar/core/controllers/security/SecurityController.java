/**
 * Project: RecCore
 * Created by: raulanatol at 22/12/2012 18:59:29
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.controllers.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Controlador de seguridad del API
 * @author raulanatol
 */
@Controller
@SessionAttributes(types = AuthorizationRequest.class)
public class SecurityController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityController.class);
    /**
     *
     */
    @Autowired
    private ClientDetailsService clientDetailsService;

    /**
     * @param clientDetailsService
     */
    @Autowired
    public void setClientDetailsService(ClientDetailsService clientDetailsService) {
        this.clientDetailsService = clientDetailsService;
    }

    /**
     * @param model
     * @param request
     * @param authorizationRequest
     * @return la url de acceso
     */
    @RequestMapping("/oauth/confirm_access")
    public String handleRequestInternal(ModelMap model, HttpServletRequest request, @ModelAttribute AuthorizationRequest authorizationRequest) {
        LOGGER.debug("AccessConfirmationController.getAccessConfirmation clientAuth {}", authorizationRequest);

        ClientDetails client = clientDetailsService.loadClientByClientId(authorizationRequest.getClientId());

        model.put("auth_request", authorizationRequest);
        model.put("client", client);

        return "oauth/access_confirmation";
    }

    /**
     * @param model
     * @return el error
     * @throws Exception
     */
    @RequestMapping("/oauth/error")
    public String handleError(Map<String, Object> model) throws Exception {
        // We can add more stuff to the model here for JSP rendering. If the client was a machine then
        // the JSON will already have been rendered.
        model.put("message", "There was a problem with the OAuth2 protocol");
        return "oauth_error";
    }
}
