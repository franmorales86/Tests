/**
 * Project: RecCore
 * Created by: raulanatol at 02/05/2013 15:45:47
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.controllers.api;

import com.reclabs.recomendar.common.helpers.ParameterHelper;
import com.reclabs.recomendar.core.misc.security.SecurityHelper;
import com.reclabs.recomendar.model.documents.items.Item;
import com.reclabs.recomendar.model.documents.users.User;
import com.reclabs.recomendar.model.services.RecommendationService;
import com.reclabs.recomendar.model.services.UserService;
import com.reclabs.recomendar.objects.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Controlador para todas las llamadas de API relacionadas con las recomendaciones.
 * @author raulanatol
 */
@Controller
@RequestMapping(value = "/v1/recommendations/")
public class RecommendationController {

    @Autowired
    private RecommendationService recommendationService;

    @Autowired
    private UserService userService;

    /**
     * Actualizamos los datos del usuario con los datos pasados por parámetros.
     * @param itemId La id del item a recomendar.
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.PUT, value = "recommendItem/{item}")
    @PreAuthorize("#oauth2.isUser() and #oauth2.clientHasRole('ROLE_USER') and #oauth2.hasScope('write')")
    public ResponseDTO recommendItem(@PathVariable("item") String itemId) {
        ParameterHelper.assertEmpty(itemId);
        User user = SecurityHelper.getLoggedInUser();
        recommendationService.recommendItem(user.getId(), itemId);
        return ResponseDTO.OK;
    }

    /**
     * Obtenemos un listado de todos los items recomendados por el usuario en sesión.
     * @return El listado de items recomendados por el usuario.
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("#oauth2.isUser() and #oauth2.clientHasRole('ROLE_USER') and #oauth2.hasScope('write')")
    public List<Item> getOwnRecommendations() {
        User user = SecurityHelper.getLoggedInUser();
        return recommendationService.findItemsByRecommendationUser(user.getId());
    }

    /**
     * Gets all item from a username.
     * @param username
     * @return
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/{username}/")
    public List<Item> getRecommendationsByUsername(@PathVariable String username) {
        ParameterHelper.assertEmpty(username);
        User user = userService.findByUsername(username);
        return recommendationService.findItemsByRecommendationUser(user.getId());
    }

    /**
     * Remove recommendation by userId and itemId
     * @param itemId Id of item
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.DELETE, value = "/{itemId}")
    @PreAuthorize("#oauth2.isUser() and #oauth2.clientHasRole('ROLE_USER') and #oauth2.hasScope('write')")
    public ResponseDTO removeRecommendationsByUserAndItem(@PathVariable("itemId") String itemId) {
        ParameterHelper.assertEmpty(itemId);
        User user = SecurityHelper.getLoggedInUser();
        recommendationService.removeRecommendationByUserIdAndItemId(user.getId(), itemId);
        return ResponseDTO.OK;
    }
}
