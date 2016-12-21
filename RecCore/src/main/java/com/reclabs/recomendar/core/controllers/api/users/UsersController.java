/**
 * Project: RecCore
 * Created by: raulanatol at 08/02/2013 14:12:51
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.controllers.api.users;

import com.reclabs.recomendar.common.exceptions.RecIllegalArgumentException;
import com.reclabs.recomendar.common.exceptions.generic.ApiException;
import com.reclabs.recomendar.common.exceptions.generic.RecRuntimeException;
import com.reclabs.recomendar.common.helpers.ParameterHelper;
import com.reclabs.recomendar.common.helpers.types.NullHelper;
import com.reclabs.recomendar.common.helpers.types.StringHelper;
import com.reclabs.recomendar.common.types.CodeErrorType;
import com.reclabs.recomendar.core.assemblers.UserAssembler;
import com.reclabs.recomendar.core.exceptions.common.PermissionDeniedException;
import com.reclabs.recomendar.core.helpers.ContentTypeHelper;
import com.reclabs.recomendar.core.misc.security.OAuthHelper;
import com.reclabs.recomendar.core.misc.security.SecurityHelper;
import com.reclabs.recomendar.esdriver.ESDriver;
import com.reclabs.recomendar.esdriver.actions.ESFind;
import com.reclabs.recomendar.esdriver.actions.queries.ESBoolQuery;
import com.reclabs.recomendar.esdriver.actions.queries.ESCustomScoreQuery;
import com.reclabs.recomendar.esdriver.actions.queries.ESQueryStringQuery;
import com.reclabs.recomendar.esdriver.actions.queries.ESTopChildrenQuery;
import com.reclabs.recomendar.esdriver.actions.queries.generics.ESMatchQuery;
import com.reclabs.recomendar.esdriver.index.ESTypes;
import com.reclabs.recomendar.esdriver.index.IndexType;
import com.reclabs.recomendar.esdriver.types.BoolQueryField;
import com.reclabs.recomendar.esdriver.types.BooldFieldType;
import com.reclabs.recomendar.model.documents.statistics.Karma;
import com.reclabs.recomendar.model.documents.users.SocialUser;
import com.reclabs.recomendar.model.documents.users.User;
import com.reclabs.recomendar.model.objects.user.UserDTO;
import com.reclabs.recomendar.model.objects.user.UserOwnDataDTO;
import com.reclabs.recomendar.model.objects.user.UserSimpleDataDTO;
import com.reclabs.recomendar.model.services.ImageService;
import com.reclabs.recomendar.model.services.UserService;
import com.reclabs.recomendar.model.services.user.KarmaService;
import com.reclabs.recomendar.model.types.SocialUserProviderId;
import com.reclabs.recomendar.objects.ResponseDTO;
import com.reclabs.recomendar.objects.users.ChangePasswordDTO;
import io.searchbox.core.search.sort.Sort;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author raulanatol
 */
@Controller
@RequestMapping(value = "/v1/users/")
public class UsersController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UsersController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserAssembler userAssembler;

    @Autowired
    private ImageService imageService;

    @Autowired
    private OAuthHelper oAuthHelper;

    @Autowired
    private ESDriver esDriver;

    @Autowired
    private KarmaService karmaService;

    /**
     * Unlink the twitter account from the current user.
     * @param twitterAccount Profile name of twitter to erase
     * @param request HttpServletRequest of the request
     * @return La respuesta del API.
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.DELETE, value = "unSubscribe/twitter/{twitterAccount}")
    @PreAuthorize("#oauth2.isUser() and #oauth2.clientHasRole('ROLE_USER') and #oauth2.hasScope('write')")
    public ResponseDTO unlinkTwitter(@PathVariable("twitterAccount") String twitterAccount, HttpServletRequest request) {
        ParameterHelper.assertEmpty(twitterAccount);
        SocialUser socialUser2Remove = SecurityHelper.getLoggedSocialUserByProviderId(SocialUserProviderId.TWITTER);
        if (socialUser2Remove != null && StringHelper.equals(socialUser2Remove.getDisplayName(), twitterAccount)) {
            userService.remSocialUser(socialUser2Remove);
            oAuthHelper.renewLoggedUser(request);
        } else {
            throw new RecRuntimeException("Not permitted action");
        }
        return ResponseDTO.OK;
    }

    /**
     * Unlink facebook social from the current user.
     * @param request HttpServletRequest of the request
     * @return The api response ResponseDTO.OK if all it's ok.
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.DELETE, value = "unSubscribe/facebook")
    @PreAuthorize("#oauth2.isUser() and #oauth2.clientHasRole('ROLE_USER') and #oauth2.hasScope('write')")
    public ResponseDTO unlinkFacebook(HttpServletRequest request) {
        SocialUser socialUser2Remove = SecurityHelper.getLoggedSocialUserByProviderId(SocialUserProviderId.FACEBOOK);
        if (socialUser2Remove != null) {
            userService.remSocialUser(socialUser2Remove);
            oAuthHelper.renewLoggedUser(request);
        } else {
            throw new RecRuntimeException("Not permitted action");
        }
        return ResponseDTO.OK;
    }


    /**
     * @param username Username of user.
     * @return la información del usuario simple.
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "getSimpleData/{username}/")
    public UserSimpleDataDTO getSimpleData(@PathVariable("username") final String username) {
        return userService.getUserSimpleDataByUsername(username);
    }

    /**
     * Gets the simple data of an user.
     * @param id The id of the user to receive
     * @return The simple user data or exception if the id is invalid.
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public UserSimpleDataDTO getSimpleDataById(@PathVariable("id") String id) {
        ParameterHelper.assertEmpty(id);
        return userService.getUserSimpleDataById(id);
    }


    /**
     * http://hugo.publicfy.com:3033/pages/viewpage.action?pageId=1999137
     * @return Data of the user on session
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "ownData")
    @PreAuthorize("#oauth2.isUser() and #oauth2.clientHasRole('ROLE_USER') and #oauth2.hasScope('write')")
    public UserOwnDataDTO getOwnData() {
        User user = SecurityHelper.getLoggedInUser();
        return userService.getUserOwnDataByUser(user);
    }

    /**
     * Actualizamos los datos del usuario con los datos pasados por parámetros.
     * @param userOwnDataDTO Los datos a actualizar.
     * @param request HttpServletRequest of the request
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.PUT, value = "ownData")
    @PreAuthorize("#oauth2.isUser() and #oauth2.clientHasRole('ROLE_USER') and #oauth2.hasScope('write')")
    public ResponseDTO updateOwnData(@RequestBody UserOwnDataDTO userOwnDataDTO, HttpServletRequest request) {
        ParameterHelper.assertAnyNull(userOwnDataDTO, request);
        ParameterHelper.assertAnyNull(userOwnDataDTO.getId(), userOwnDataDTO.getName(), userOwnDataDTO.getEmail());
        User user = SecurityHelper.getLoggedInUser();
        if (StringHelper.equals(user.getUsername(), userOwnDataDTO.getUsername())) {
            userService.updateUserData(user.getId(), userOwnDataDTO);
            oAuthHelper.renewLoggedUser(request);
        } else {
            LOGGER.error("The user with id {} try to modify userId {}", user.getId(), userOwnDataDTO.getUsername());
            throw new PermissionDeniedException();
        }
        return ResponseDTO.OK;
    }

    /**
     * Change the password of a session user.
     * @param changePasswordDTO The data of change
     * @return ResponseDTO.OK on success.
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "changePassword")
    @PreAuthorize("#oauth2.isUser() and #oauth2.clientHasRole('ROLE_USER') and #oauth2.hasScope('write')")
    public ResponseDTO changePassword(@RequestBody ChangePasswordDTO changePasswordDTO) {
        ParameterHelper.assertNull(changePasswordDTO);
        ParameterHelper.assertAnyEmpty(changePasswordDTO.getOldPassword(), changePasswordDTO.getNewPassword());
        User user = SecurityHelper.getLoggedInUser();
        userService.changePassword(user, changePasswordDTO.getOldPassword(), changePasswordDTO.getNewPassword());
        return ResponseDTO.OK;
    }

    /**
     * @param multipartFile File to upload
     * @param request HttpServletRequest of the request
     * @return La url en la que se guarda la imagen.
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "changeOwnPicture")
    @PreAuthorize("#oauth2.isUser() and #oauth2.clientHasRole('ROLE_USER') and #oauth2.hasScope('write')")
    public String upload(@RequestParam("file") MultipartFile multipartFile, HttpServletRequest request) {
        ParameterHelper.assertNull(multipartFile);
        ContentTypeHelper.assertValidImageContentType(multipartFile.getContentType());
        User user = SecurityHelper.getLoggedInUser();
        try {
            InputStream imageSource = multipartFile.getInputStream();
            String newImageURL = imageService.addUserImage(user.getId(), imageSource, multipartFile.getSize(), multipartFile.getContentType());
            //Generamos la url nueva x el problema que existe con el cloudFront.
            //String result = "http://recitems.s3.amazonaws.com/users/" + user.getId() + "/avatar.png?" + Math.random();
            String result = newImageURL + "?" + Math.random();
            user.setAvatarURL(result);
            userService.updateUser(user);
            oAuthHelper.renewLoggedUser(request);
            return result;
        } catch (IOException e) {
            LOGGER.error("Error obtain the image source {}", e);
            throw new RecIllegalArgumentException("Image source is invalid");
        }
    }

    /**
     * Inserta un nuevo usuario en la base de datos
     * @param user Datos del nuevo usuario
     * @return Datos del usuario añadido
     */
    @ResponseBody
    @RequestMapping(value = "/add_user", method = RequestMethod.POST)
    @PreAuthorize("#oauth2.isUser() and #oauth2.clientHasRole('ROLE_SANDBOX') and hasRole('ROLE_SANDBOX_ADMIN') and #oauth2.hasScope('write')")
    public UserDTO createUser(@RequestBody UserDTO user) {
        ParameterHelper.assertNull(user);
        ParameterHelper.assertEmpty(user.getUsername());
        return userService.createUser(user);
    }

    /**
     * Elimina los datos de un usuario de la base de datos
     * @param username Nombre del usuario a eliminar
     * @return Respuesta de la API
     */
    @ResponseBody
    @RequestMapping(value = "/delete_user/{username}", method = RequestMethod.DELETE)
    public ResponseDTO deleteUser(@PathVariable("username") String username) {
        ParameterHelper.assertEmpty(username);
        userService.deleteUser(username);
        return ResponseDTO.OK;
    }

    /**
     * Obtiene los datos de un usuario a partir del username
     * @param username Username del usuario
     * @return Datos del usuario
     */
    @ResponseBody
    @RequestMapping(value = "/search/{username}/", method = RequestMethod.GET)
    public UserDTO findByUsername(@PathVariable("username") String username) {
        ParameterHelper.assertEmpty(username);
        User user = userService.findByUsername(username);
        if (NullHelper.isAnyNull(user)) {
            throw new ApiException(CodeErrorType.USER_NOT_FOUND);
        }
        return userAssembler.toRight(user);
    }

    /**
     * Obtenemos un listado de todos los usuarios de la base de datos.
     * @return Listado de todos los usuarios
     */
    @ResponseBody
    @RequestMapping(value = "/list/all", method = RequestMethod.GET)
    public List<UserDTO> findAll() {
        List<User> result = userService.findAllPaginated(0, 50);
        return userAssembler.toRight(result);
    }

    /**
     * Start the delete account process
     * @return Ok if success
     */
    @ResponseBody
    @RequestMapping(value = "deleteAccount", method = RequestMethod.DELETE)
    @PreAuthorize("#oauth2.isUser() and #oauth2.clientHasRole('ROLE_USER') and #oauth2.hasScope('write')")
    public ResponseDTO deleteAccount() {
        User user = SecurityHelper.getLoggedInUser();
        userService.deleteAccount(user);
        return ResponseDTO.OK;
    }

    /**
     * Returns users most recommended by a item and that don't include concrete user.
     * @param userId Id of user
     * @param itemId Id of item
     * @param page Page of the paginated
     * @param size Number of item per page
     * @return List of items most recommended
     */
    @ResponseBody
    @RequestMapping(value = "/getRecommendsByItem", method = RequestMethod.GET)
    public Collection<User> getRecommendsByItem(@RequestParam(value = "userId") String userId,
                                                @RequestParam(value = "itemId") String itemId,
                                                @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                @RequestParam(value = "size", defaultValue = "20") Integer size) {
        List<BoolQueryField> where = new ArrayList<>();
        where.add(new BoolQueryField(new ESTopChildrenQuery(ESTypes.RECUSER, new ESCustomScoreQuery("doc['createdDate'].date.getMillis()", new ESMatchQuery("itemId", itemId))), BooldFieldType.MUST));
        where.add(new BoolQueryField(new ESMatchQuery("_id", userId), BooldFieldType.MUST_NOT));
        ESFind query = new ESFind(IndexType.RECUSER_INDEX, new ESBoolQuery(where), Arrays.asList(new Sort("_score", Sort.Sorting.DESC)), page, size);
        return esDriver.searchByQueryList(query, User.class);
    }

    /**
     * Find user with patterns
     * @param queryString String to search
     * @param page Number of page
     * @param size Element for page
     * @return List of user.
     */
    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @PreAuthorize("#oauth2.isUser() and #oauth2.clientHasRole('ROLE_SANDBOX') and hasRole('ROLE_SANDBOX_ADMIN') and #oauth2.hasScope('write')")
    public Collection<User> basicSearchUsers(@RequestParam(value = "q", required = false) String queryString,
                                             @RequestParam(value = "page", defaultValue = "0") Integer page,
                                             @RequestParam(value = "size", defaultValue = "20") Integer size) {
        ParameterHelper.assertEmpty(queryString);
        List<BoolQueryField> where = new ArrayList<>();
        where.add(new BoolQueryField(new ESQueryStringQuery("user.name", "*" + queryString + "*"), BooldFieldType.SHOULD));
        where.add(new BoolQueryField(new ESQueryStringQuery("user.lastname1", "*" + queryString + "*"), BooldFieldType.SHOULD));
        where.add(new BoolQueryField(new ESQueryStringQuery("user.lastname2", "*" + queryString + "*"), BooldFieldType.SHOULD));
        where.add(new BoolQueryField(new ESQueryStringQuery("user.username", "*" + queryString + "*"), BooldFieldType.SHOULD));
        ESFind query = new ESFind(IndexType.USER_INDEX, new ESBoolQuery(where), page, size);
        return esDriver.searchByQueryList(query, User.class);
    }

    /**
     * Reset the password of the user with id passed from parameters
     * @param userId Id of the user to reset.
     * @return ResponseDTO.OK if all ok exception in otherwise
     */
    @ResponseBody
    @RequestMapping(value = "/resetPassword/{userId}", method = RequestMethod.PUT)
    @PreAuthorize("#oauth2.isUser() and #oauth2.clientHasRole('ROLE_SANDBOX') and hasRole('ROLE_SANDBOX_ADMIN') and #oauth2.hasScope('write')")
    public ResponseDTO resetPassword(@PathVariable("userId") String userId) {
        ParameterHelper.assertEmpty(userId);
        User user = userService.getUserById(userId);
        if (user == null) {
            throw new ApiException(CodeErrorType.USER_NOT_FOUND);
        }
        userService.resetPassword(user);
        return ResponseDTO.OK;
    }

    /**
     * Ban the user with id is equals that userId
     * @param userId The id of the user
     * @return Result of the action.
     */
    @ResponseBody
    @RequestMapping(value = "/ban/{userId}", method = RequestMethod.PUT)
    @PreAuthorize("#oauth2.isUser() and #oauth2.clientHasRole('ROLE_SANDBOX') and hasRole('ROLE_SANDBOX_ADMIN') and #oauth2.hasScope('write')")
    public ResponseDTO userBan(@PathVariable("userId") String userId) {
        ParameterHelper.assertEmpty(userId);
        User user = userService.getUserById(userId);
        if (user == null) {
            throw new ApiException(CodeErrorType.USER_NOT_FOUND);
        }
        userService.banUser(user);
        return ResponseDTO.OK;
    }


    @ResponseBody
    @RequestMapping(value = "/karma/{userId}", method = RequestMethod.GET)
    public ResponseDTO getKarma(@PathVariable("userId") String userId) {
        ParameterHelper.assertEmpty(userId);
        Karma karma = karmaService.getKarmaByUserId(userId);
        Double value = (karma == null) ? 0D : karma.getValue();
        return new ResponseDTO(ResponseDTO.OK.getCode(), value.toString());
    }

    @ResponseBody
    @RequestMapping(value = "/autoShared/{providerId}/{value}", method = RequestMethod.PUT)
    @PreAuthorize("#oauth2.isUser() and #oauth2.clientHasRole('ROLE_USER') and #oauth2.hasScope('write')")
    public ResponseDTO setAutoShared(@PathVariable("providerId") String providerId, @PathVariable("value") String value, HttpServletRequest request) {
        ParameterHelper.assertAnyEmpty(providerId, value);
        boolean autoSharedValue = StringHelper.toBoolean(value);
        SocialUserProviderId socialUserProviderId = SocialUserProviderId.byProviderId(providerId);
        User user = SecurityHelper.getLoggedInUser();
        userService.setAutoShared(user, socialUserProviderId, autoSharedValue);
        oAuthHelper.renewLoggedUser(request);
        return ResponseDTO.OK;
    }

    @ResponseBody
    @RequestMapping(value = "/getPendingRecommendations", method = RequestMethod.GET)
    @PreAuthorize("#oauth2.isUser() and #oauth2.clientHasRole('ROLE_USER') and #oauth2.hasScope('write')")
    public ResponseDTO getPendingRecommendations() {
        User user = SecurityHelper.getLoggedInUser();
        Long pendingRecommendations = userService.getPendingRecommendationsFromUser(user.getId());
        return new ResponseDTO(ResponseDTO.OK.getCode(), pendingRecommendations.toString());
    }
}
