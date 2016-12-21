/**
 * Project: RecCore
 * Created by: raulanatol at 14/11/2012 10:16:22
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
import com.reclabs.recomendar.core.services.security.UserDetailsServiceImpl;
import com.reclabs.recomendar.model.documents.users.SocialUser;
import com.reclabs.recomendar.model.documents.users.User;
import com.reclabs.recomendar.model.types.SecurityRole;
import com.reclabs.recomendar.model.types.SocialUserProviderId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.List;

/**
 * Listado de funcionalidades referentes a la seguridad de la aplicación.
 * @author raulanatol
 */
public class SecurityHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityHelper.class);

    /**
     * @return The current user or null if an anonymous user.
     */
    public static User getSessionUserOrNull() {
        User result = null;
        try {
            result = getLoggedInUser();
        } catch (Exception ignore) {
            //empty
        }
        return result;
    }

    /**
     * Obtenemos el usuario logeado en el sistema.
     * @return El usuario logeado.
     */
    public static User getLoggedInUser() {
        Object principal = getPrincipal();
        User result;
        if (principal instanceof UserDetailsImpl) {
            result = ((UserDetailsImpl) principal).getUser();
        } else if (principal instanceof AnonymousAuthenticationToken) {
            LOGGER.warn("User principal unknown");
            throw new RecRuntimeException("User anonymous");
        } else {
            LOGGER.error("User principal unknown");
            throw new RecRuntimeException("User principal unknown");
        }
        return result;
    }

    /**
     * @return Obtenemos el authentication de la sesión
     */
    public static Authentication getAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new RecRuntimeException("User anonymous");
        }
        return authentication;
    }

    /**
     * Dado un providerId obtenemos el SocialUser de ese providerId del usuario logeado.
     * @param providerId ProviderId to find on the User logged.
     * @return SocialUser of the logged user.
     */
    public static SocialUser getLoggedSocialUserByProviderId(SocialUserProviderId providerId) {
        SocialUser result = null;
        User userLogged = getLoggedInUser();
        List<SocialUser> socialUsers = userLogged.getSocialUsers();
        for (SocialUser socialUser : socialUsers) {
            if (StringHelper.equals(socialUser.getProviderId(), providerId.getProviderId())) {
                result = socialUser;
                break;
            }
        }
        return result;
    }

    /**
     * Indica si hay un usuario logeado o no.
     * @return true si el usuario está logeado, false en caso contrario.
     */
    public static boolean existLoggedUser() {
        Object principal = getPrincipal();
        return (principal instanceof UserDetailsImpl);
    }

    /**
     * Obtenemos el usuario en sesión.
     * @return El usuario en sesión.
     */
    private static Object getPrincipal() {
        return getAuthentication().getPrincipal();
    }

    /**
     * Realizamos el login del usuario pasado por parámetros.
     * @param userId The id of the user
     * @param userDetailsService The userDetailsService
     */
    public static void performLogin(String userId, UserDetailsServiceImpl userDetailsService) {
        UserDetails userDetails = userDetailsService.findByUserId(userId);
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    /**
     * Deslogeamos el usuario en sesión.
     * @param request El request
     * @param response El respose.
     */
    public static void logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
    }

    /**
     * Veridy if the login is possible for access to sandbox. In case of the clientId are sandbox
     * @param clienId The clientId
     * @param authorities The authorities
     * @return The result
     */
    public static boolean verifySecurityForSandbox(String clienId, Collection<GrantedAuthority> authorities) {
        boolean result = true;
        if (StringHelper.equals(clienId, "recomendar_sandbox")) {
            result = false;
            for (GrantedAuthority grantedAuthority : authorities) {
                if (SecurityRole.permitLoginSandbox(grantedAuthority.getAuthority())) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    public static boolean isSandboxAdmin(List<SecurityRole> roles) {
        boolean result = false;
        if (!CollectionHelper.isEmpty(roles)) {
            for (SecurityRole securityRole : roles) {
                if (securityRole == SecurityRole.ROLE_SANDBOX_ADMIN || securityRole == SecurityRole.ROLE_SANDBOX_USER) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }
}