/**
 * Project: RecCore
 * Created by: raulanatol at 18/02/2013 21:09:00
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.controllers.api.utilities;

import com.reclabs.barneylib.LinkType;
import com.reclabs.libs.recwebparser.RecWebParser;
import com.reclabs.recomendar.common.exceptions.RecIllegalArgumentException;
import com.reclabs.recomendar.common.exceptions.generic.ErrorException;
import com.reclabs.recomendar.common.exceptions.generic.RecRuntimeException;
import com.reclabs.recomendar.common.helpers.ParameterHelper;
import com.reclabs.recomendar.core.helpers.ContentTypeHelper;
import com.reclabs.recomendar.core.misc.security.SecurityHelper;
import com.reclabs.recomendar.model.documents.users.User;
import com.reclabs.recomendar.model.objects.utils.ShortURLDTO;
import com.reclabs.recomendar.model.services.BarneyService;
import com.reclabs.recomendar.model.services.ImageService;
import com.reclabs.recomendar.model.services.UserService;
import com.reclabs.recomendar.objects.ResponseDTO;
import com.reclabs.recomendar.objects.WebImage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

/**
 * API methods of utilities.
 * @author raulanatol
 */
@Controller
@RequestMapping(value = "/v1/utilities/")
public class UtilitiesController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UtilitiesController.class);

    @Autowired
    private BarneyService barneyService;

    @Autowired
    private UserService userService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private RecWebParser recWebParser;

    /**
     * Exist or no username on database.
     * Api documentation: http://hugo.publicfy.com:3033/pages/viewpage.action?pageId=1999114
     * @param username Name of user to verify
     * @return true if user exist false otherwise
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "user_exist/{username}/")
    public ResponseDTO userExist(@PathVariable("username") final String username) {
        ParameterHelper.assertEmpty(username);
        User user = userService.findByUsername(username);
        return (user != null) ? ResponseDTO.TRUE : ResponseDTO.FALSE;
    }

    /**
     * Exist or no email on database.
     * Api documentation: http://hugo.publicfy.com:3033/pages/viewpage.action?pageId=1999118
     * @param email Email to verify
     * @return true if user exist false otherwise
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "email_exist/{email}/")
    public ResponseDTO emailExist(@PathVariable("email") final String email) {
        ParameterHelper.assertEmpty(email);
        return (userService.findByEmail(email) == null) ? ResponseDTO.FALSE : ResponseDTO.TRUE;
    }

    /**
     * @return Los datos del usuario en sessión.
     * @throws ErrorException
     * @deprecated No usar sólo para pruebas. Eliminar cuando estas se terminen.
     */
    @Deprecated
    @ResponseBody
    @RequestMapping(value = "/login_info", method = RequestMethod.GET)
    public User loginInfo() throws ErrorException {
        User user = SecurityHelper.getLoggedInUser();
        if (user == null) {
            throw new ErrorException("Usuario no logeado");
        }
        return user;
    }

    /**
     * Create a short url.
     * @param shortURLDTO Data
     * @return La url acortada.
     * @throws ErrorException En caso de error en la creación de la url acortada.
     */
    @ResponseBody
    @RequestMapping(value = "/create_short_url", method = RequestMethod.POST)
    public String createShortUrl(@RequestBody ShortURLDTO shortURLDTO) throws ErrorException {
        ParameterHelper.assertAnyEmpty(shortURLDTO.getUrl(), shortURLDTO.getWebName(), shortURLDTO.getLinkType());
        LinkType linkTypesValue = LinkType.fromString(shortURLDTO.getLinkType());
        String userId;
        try {
            User user = SecurityHelper.getLoggedInUser();
            userId = user.getId();
        } catch (RecRuntimeException e) {
            userId = "0";
        }
        // TODO realizar un parseo para dejar las urls válidas para mostrar en un browser.
        // TODO verificar que todas las url a acortar comienzan por recomendar.com para evitar que usen la
        // plataforma para hacer sus propias acortaciones. Esto deberá hacerse en el momento en el que creemos los
        // iframes de elementos, en el que todas las web pasarán por tener el iframe de Recomendar, por lo que
        // todas las tiendas serán de Recomendar..
        return barneyService.createShortUrl(shortURLDTO.getUrl(), shortURLDTO.getWebName(), linkTypesValue, userId);
    }

    @ResponseBody
    @RequestMapping(value = "/createShortURL", method = RequestMethod.POST)
    public ResponseDTO createShortURLJSON(@RequestBody ShortURLDTO shortURLDTO) {
        String result = createShortUrl(shortURLDTO);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setCode(ResponseDTO.OK.getCode());
        responseDTO.setMessage(result);
        return responseDTO;
    }

    /**
     * @param urlWeb
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getImages", method = RequestMethod.GET)
    public Collection<WebImage> parseWeb(@RequestParam("url") String urlWeb) {
        return recWebParser.parseURL(urlWeb);
    }

    /**
     * Upload a temporal image to recomendar.
     * @param multipartFile
     * @param request
     * @return The url of the image.
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "uploadTemporalImage")
    @PreAuthorize("#oauth2.isUser() and #oauth2.clientHasRole('ROLE_USER') and #oauth2.hasScope('write')")
    public String uploadTemporalImages(@RequestParam("file") MultipartFile multipartFile, HttpServletRequest request) {
        LOGGER.info("[Trying to upload a temporal image]");
        ParameterHelper.assertNull(multipartFile);
        ContentTypeHelper.assertValidImageContentType(multipartFile.getContentType());
        try {
            InputStream imageSource = multipartFile.getInputStream();
            return imageService.addTemporalImage(imageSource, multipartFile.getSize(), multipartFile.getContentType());
        } catch (IOException e) {
            LOGGER.error("Error obtain the image source {}", e);
            throw new RecIllegalArgumentException("Image source is invalid");
        }
    }
}
