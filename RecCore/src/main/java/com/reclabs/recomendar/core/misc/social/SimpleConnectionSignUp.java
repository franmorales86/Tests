/**
 * Project: RecCore
 * Created by: raulanatol at 15/11/2012 18:14:04
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.misc.social;

import com.reclabs.recomendar.common.exceptions.RecIllegalArgumentException;
import com.reclabs.recomendar.common.exceptions.generic.RecRuntimeException;
import com.reclabs.recomendar.common.helpers.types.CollectionHelper;
import com.reclabs.recomendar.common.helpers.types.StringHelper;
import com.reclabs.recomendar.core.helpers.UserHelper;
import com.reclabs.recomendar.core.misc.security.SecurityHelper;
import com.reclabs.recomendar.model.documents.users.SocialUser;
import com.reclabs.recomendar.model.documents.users.User;
import com.reclabs.recomendar.model.services.ImageService;
import com.reclabs.recomendar.model.services.UserService;
import com.reclabs.recomendar.model.types.SecurityRole;
import com.reclabs.recomendar.model.types.SocialUserProviderId;
import com.reclabs.recomendar.model.types.user.UserState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.twitter.api.UserOperations;
import org.springframework.social.twitter.api.impl.TwitterTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * SignUp connector for social login
 * @author raulanatol
 */
public class SimpleConnectionSignUp implements ConnectionSignUp {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleConnectionSignUp.class);

    @Autowired
    private UserService userService;

    @Autowired
    private ImageService imageService;

    @SuppressWarnings("unchecked")
    @Override
    public String execute(Connection<?> connection) {
        String result = null;
        ConnectionKey connectionKey = connection.getKey();
        boolean userLogged = SecurityHelper.existLoggedUser();
        switch (connectionKey.getProviderId()) {
            case "twitter":
                result = (userLogged) ? twitterLink(connection) : twitterSignUp(connection);
                break;
            case "facebook":
                result = (userLogged) ? facebookLink(connection) : facebookSignUp((Connection<Facebook>) connection);
                break;
            default:
                LOGGER.error("Provider not supported {}", connectionKey.getProviderId());
        }
        return result;
    }

    /**
     * Gets the current logged user from the database.
     * @return The user from the database
     */
    private User getLoggedInUserFromDB() {
        String userId = SecurityHelper.getLoggedInUser().getId();
        return userService.getUserById(userId);
    }

    /**
     * Link a twitter account to current user session.
     * @param connection Twitter account.
     * @return User id of linked session.
     */
    private String twitterLink(Connection<?> connection) {
        LOGGER.info("[Linking with twitter]");
        SocialUser socialUser = getSocialUserByConnection(connection);
        User user2Link = getLoggedInUserFromDB();
        if (UserHelper.userHasProviderId(user2Link, SocialUserProviderId.byProviderId(socialUser.getProviderId()))) {
            LOGGER.warn("[User][{}] User already have a Twitter link", user2Link.getId());
            throw new RecIllegalArgumentException("User already have a Twitter link");
        }
        return userService.addSocialUser(user2Link, socialUser).getId();
    }

    /**
     * Link a facebook account to current user session.
     * @param connection Facebook connection
     * @return User id of linked session.
     */
    private String facebookLink(Connection<?> connection) {
        LOGGER.info("[Linking with facebook]");
        SocialUser socialUser = getSocialUserByConnection(connection);
        User user2Link = getLoggedInUserFromDB();
        if (UserHelper.userHasProviderId(user2Link, SocialUserProviderId.byProviderId(socialUser.getProviderId()))) {
            throw new RecRuntimeException("User already have a Facebook link");
        }
        User user = userService.addSocialUser(user2Link, socialUser);
        return user.getId();
    }

    /**
     * @param user The user modify
     * @param connection Connection to get data to perform the modification.
     */
    private void updateUserDataFromTwitterConnection(User user, SocialUser socialUser, Connection<?> connection) {
        TwitterTemplate twitterTemplate = (TwitterTemplate) connection.getApi();
        UserOperations userOperations = twitterTemplate.userOperations();
        user.setUsername(userService.getValidUsername(userOperations.getScreenName()));
        user.setName(userOperations.getUserProfile().getName());
        user.setSocialUsers(Arrays.asList(socialUser));
        user.setRoles(Arrays.asList(SecurityRole.ROLE_USER));
        //TODO se tendría que usar esta llamada pero no está activa todavía en el twitterTemplate para esta versión "https://api.twitter.com/1.1/users/profile_banner.json", Object.class);
        //String userImageURL = connection.getImageUrl();
        //if (!StringHelper.isEmpty(userImageURL)) {
        //user.setAvatarURL(userImageURL);
        //}
        user.setAvatarURL(null);
    }

    /**
     * @param user The user
     * @param socialUser The social user
     * @param connection A connection
     */
    protected void updateUserDataFromFacebookConnection(User user, SocialUser socialUser, Connection<Facebook> connection) {
        Facebook facebook = connection.getApi();
        FacebookProfile userProfile = facebook.userOperations().getUserProfile();
        user.setName(userProfile.getName());
        user.setUsername(userService.getValidUsername(userProfile.getUsername()));
        user.setSocialUsers(new ArrayList<>(Arrays.asList(socialUser)));
        user.setRoles(Arrays.asList(SecurityRole.ROLE_USER));
        user.setEmail(userProfile.getEmail());
        //FIXME mirar como hacer con el cumpleaños. user.setBirthday(userProfile.getBirthday());
        user.setLastname1(userProfile.getMiddleName());
        user.setLastname2(userProfile.getLastName());
        user.setDescription(userProfile.getBio());
        updateUserAvatar(facebook, user);
        //FIXME mirar como hacer el parser user.setSex(userProfile.getGender());
        //FIXME mirar como hacer para coger el avatarURL user.setAvatarURL(userProfile.);
    }

    private void updateUserAvatar(Facebook facebook, User user) {
        byte[] userProfileImage = facebook.userOperations().getUserProfileImage();
        if (userProfileImage == null) {
            LOGGER.warn("[Trying to get the userProfile from userId: {}]", user.getId());
            user.setAvatarURL(null);
        } else {
            user.setAvatarURL(imageService.addTemporalImage(userProfileImage));
        }
    }

    /**
     * Sign up of an user to twitter data.
     * Can be link o creation of user. Link is when a user exist on recomendar.
     * To distinguish the kind of sign up, must be show if exist current user on session.
     * @param connection Twitter connection.
     * @return The id of the user sign up.
     */
    private String twitterSignUp(Connection<?> connection) {
        LOGGER.info("[Twitter SignUp]");
        verifyIfUserExists(connection);
        User user = new User();
        user.setState(UserState.HALF_DATA);
        updateUserDataFromTwitterConnection(user, getSocialUserByConnection(connection), connection);
        User createdUser = userService.createNewUser(user);
        //TODO mirar como hacerlo xq lo que se tendrá que hacer es guardar la imagen en el S3
        User newUser = updateUserImages(createdUser);
        return newUser.getId();
    }

    /**
     * Perform the sign up of facebook user.
     * @param connection A connection.
     * @return The user id connected
     */
    private String facebookSignUp(Connection<Facebook> connection) {
        LOGGER.info("[Starting process to register user with Facebook connection]");
        verifyIfUserExists(connection);
        User user = new User();
        user.setState(UserState.HALF_DATA);
        updateUserDataFromFacebookConnection(user, getSocialUserByConnection(connection), connection);
        User createdUser = userService.createNewUser(user);
        //TODO mirar como hacerlo xq lo que se tendrá que hacer es guardar la imagen en el S3
        User newUser = updateUserImages(createdUser);
        return newUser.getId();
    }

    private boolean connectionHaveFacebookFriends(Connection<Facebook> connection) {
        List<String> friendsIds = connection.getApi().friendOperations().getFriendIds();
        List<User> recFriends = userService.getUserByFacebookFriendsIds(friendsIds);
        return (!CollectionHelper.isEmpty(recFriends));
    }

    /**
     * Update user images and create links if necessary
     * @param userToModify User to modify
     * @return the new user with images updates
     */
    private User updateUserImages(User userToModify) {
        if (StringHelper.isEmpty(userToModify.getAvatarURL())) {
            userToModify.setAvatarURL(userService.getDefaultUserAvatar());
        }
        userToModify.setAvatarURL(imageService.addUserImage(userToModify.getId(), userToModify.getAvatarURL()));
        userService.updateUser(userToModify);
        return userToModify;
    }

    /**
     * Buscamos si el providerUserId del providerId dados ya están dados de alta en la base de datos, en caso de
     * estarlo verificamos que no esté ya asociado al usuario actual y en caso contrario error.
     * @param connection The connection
     */
    private void verifyIfUserExists(Connection<?> connection) {
        if (userService.existLinkedUser(connection.getKey().getProviderId(), connection.getKey().getProviderUserId())) {
            LOGGER.error("[This connection is on use][ProviderId: {}][ProviderUserId: {}]", connection.getKey().getProviderId(), connection.getKey().getProviderUserId());
            //TODO implementar una página de aviso para esto.
            throw new RecRuntimeException("Esta conexión ya está siendo usada para otro usuario");
        }
    }

    /**
     * Gets a social user represented by a social connection.
     * @param connection The social connection.
     * @return The socialUser that represent this social connection.
     */
    private SocialUser getSocialUserByConnection(Connection<?> connection) {
        SocialUser result = new SocialUser();
        result.setProviderId(connection.getKey().getProviderId());
        result.setProviderUserId(connection.getKey().getProviderUserId());
        result.setAutoShared(true);
        return result;
    }
}
