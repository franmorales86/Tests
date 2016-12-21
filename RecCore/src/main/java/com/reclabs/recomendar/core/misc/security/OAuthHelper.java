/**
 * Project: RecCore
 * Created by: raulanatol at 08/05/2013 12:33:47
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.misc.security;

import com.reclabs.recomendar.common.exceptions.generic.RecRuntimeException;
import com.reclabs.recomendar.common.helpers.types.CollectionHelper;
import com.reclabs.recomendar.common.helpers.types.StringHelper;
import com.reclabs.recomendar.core.misc.security.tokens.TokenHelper;
import com.reclabs.recomendar.core.misc.security.tokens.TokenStoreImpl;
import com.reclabs.recomendar.model.documents.users.SocialUser;
import com.reclabs.recomendar.model.documents.users.User;
import com.reclabs.recomendar.model.repositories.users.UserRepository;
import com.reclabs.recomendar.model.types.SocialUserProviderId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * @author raulanatol
 */
@SuppressWarnings("WeakerAccess")
public class OAuthHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(OAuthHelper.class);

    @Autowired
    private TokenStoreImpl tokenStore;

    @Autowired
    private UserRepository userRepository;

    /**
     * @return Obtenemos el authentication
     */
    private Authentication getAuthentication() {
        Authentication authentication = SecurityHelper.getAuthentication();
        if (!(authentication.getPrincipal() instanceof UserDetailsImpl)) {
            throw new RecRuntimeException("User principal unknown");
        }
        return authentication;
    }

    /**
     * Renovamos al usuario actualmente logeado.
     * @param request La petición para obtener el token actual.
     */
    public void renewLoggedUser(HttpServletRequest request) {
        Authentication authentication = getAuthentication();
        UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authentication.getPrincipal();
        User user = userDetailsImpl.getUser();
        User newUser = null;
        if (user.getId() != null) {
            newUser = userRepository.findByUserId(user.getId());
        } else if (user.getUsername() != null) {
            newUser = userRepository.getByUsername(user.getUsername());
        }

        if (newUser != null) {
            BeanUtils.copyProperties(newUser, user);
            String tokenValue = TokenHelper.parseToken(request);
            tokenValue = (tokenValue == null) ? getTokenValueFromUsername(user.getUsername()) : tokenValue;
            if (tokenValue != null) {
                tokenStore.renewAuthentication(tokenValue, authentication);
            } else {
                LOGGER.warn("[User][{}] Renew logged user without token on request", user.getId());
            }
        } else {
            LOGGER.warn("[Impossible to renewLoggedUser][User {}]", user);
        }
    }

    /**
     * Gets tokenValue from username
     * @param username User to gets token value
     * @return The token value or null
     */
    public String getTokenValueFromUsername(String username) {
        String result = null;
        Collection<OAuth2AccessToken> possibleToken = tokenStore.findTokensByUserName(username);
        if (!CollectionHelper.isEmpty(possibleToken)) {
            OAuth2AccessToken token = possibleToken.iterator().next();
            result = token.getValue();
        }
        return result;
    }

    /**
     * @param username Username to clear
     */
    public void clearTokensByUsername(String username) {
        tokenStore.removeAccessTokenByUsername(username);
    }

    /**
     * Gets the accessToken from an specified user and provider
     * @param user The user
     * @param socialUserProviderId The provider
     * @return The accessToken or null
     */
    public String getAccessTokenFromUser(User user, SocialUserProviderId socialUserProviderId) {
        String result = null;
        if (!CollectionHelper.isEmpty(user.getSocialUsers())) {
            for (SocialUser socialUser : user.getSocialUsers()) {
                if (StringHelper.equals(socialUser.getProviderId(), socialUserProviderId.getProviderId())) {
                    result = socialUser.getAccessToken();
                    break;
                }
            }
        }
        return result;
    }
}
