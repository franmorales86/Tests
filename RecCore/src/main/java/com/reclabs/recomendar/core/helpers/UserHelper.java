/**
 * Project: RecCore
 * Created by: raulanatol at 08/03/2013 12:56:32
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.helpers;

import com.reclabs.recomendar.common.exceptions.generic.RecRuntimeException;
import com.reclabs.recomendar.common.helpers.types.CollectionHelper;
import com.reclabs.recomendar.common.helpers.types.StringHelper;
import com.reclabs.recomendar.model.documents.users.SocialUser;
import com.reclabs.recomendar.model.documents.users.User;
import com.reclabs.recomendar.model.objects.user.UserOwnDataDTO;
import com.reclabs.recomendar.model.types.SocialUserProviderId;

import java.util.List;

/**
 * @author raulanatol
 */
public class UserHelper {
    /**
     * @param user the user to generate visible name.
     * @return Return the result of concat the name, lastname1 and lastname2 of parameters passed.
     */
    public static String toVisibleName(User user) {
        String param1 = (user.getName() != null) ? user.getName() : "";
        String param2 = (user.getLastname1() != null && !StringHelper.contains(param1, user.getLastname1())) ? user.getLastname1() : "";
        String param3 = (user.getLastname2() != null && !StringHelper.contains(param1, user.getLastname2())) ? user.getLastname2() : "";
        return StringHelper.toStringSeparator(" ", param1, param2, param3);
    }

    /**
     * @param user The user to generate avatar url
     * @return Gets the avatar url from id.
     * @deprecated Ya no se usa se pone directamente la url de la imagen en el usuario.
     */
    @Deprecated
    public static String getAvatarUrl(User user) {
        if (user == null || StringHelper.isEmpty(user.getUsername())) {
            throw new RecRuntimeException("Username is invalid");
        }
        return "http://recomendar/images/" + user.getUsername();
    }

    /**
     * @param user The user
     * @param providerId id of the provider.
     * @return True if the user has provider indicate from parameter.
     */
    public static boolean userHasProviderId(User user, SocialUserProviderId providerId) {
        boolean result = false;
        List<SocialUser> socialUsers = user.getSocialUsers();
        if (!CollectionHelper.isEmpty(socialUsers)) {
            for (SocialUser socialUser : socialUsers) {
                if (providerId.is(socialUser.getProviderId())) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * @param user The user to find username
     * @param providerId The provider to find.
     * @return The username of the user in the provider indicate, and null if the providerId is not founded
     */
    public static String usernameFromProviderId(User user, SocialUserProviderId providerId) {
        String result = null;
        List<SocialUser> socialUsers = user.getSocialUsers();
        if (!CollectionHelper.isEmpty(socialUsers)) {
            for (SocialUser socialUser : socialUsers) {
                if (providerId.is(socialUser.getProviderId())) {
                    result = socialUser.getDisplayName();
                    break;
                }
            }
        }
        return result;
    }

    /**
     * @param user User to find providers.
     * @param providerIds List of providers to find
     * @return True if the user has all the providerIds.
     */
    public static boolean userHasProviders(User user, List<String> providerIds) {
        boolean result = true;
        for (String providerId : providerIds) {
            SocialUserProviderId socialUserProviderId = SocialUserProviderId.byProviderId(providerId);
            if (socialUserProviderId == null || !userHasProviderId(user, socialUserProviderId)) {
                result = false;
                break;
            }
        }
        return result;
    }

    /**
     * Dado un user y un userOwnDataDTO indicamos si son diferentes o no.
     * @param user El usuario
     * @param userOwnDataDTO El userOwnData
     * @return true en caso de que sean diferentes.
     */
    public static boolean isDifferent(User user, UserOwnDataDTO userOwnDataDTO) {
        boolean result = false;
        if (StringHelper.equals(user.getUsername(), userOwnDataDTO.getUsername())) {
            if (StringHelper.equals(user.getAvatarURL(), userOwnDataDTO.getAvatarURL())) {
                // FIXME terminar de completar con el resto de condiciones.
                result = true;
            }
        }
        return result;
    }

    /**
     * Gets the socialUSer from a specified providerId
     * @param user The user to find the socialUser
     * @param providerId The providerId
     * @return The socialUser or null if the socialUser don't exists.
     */
    public static SocialUser getSocialUserByProviderId(User user, SocialUserProviderId providerId) {
        SocialUser result = null;
        List<SocialUser> socialUsers = user.getSocialUsers();
        if (!CollectionHelper.isEmpty(socialUsers)) {
            for (SocialUser socialUser : socialUsers) {
                if (providerId.is(socialUser.getProviderId())) {
                    result = socialUser;
                    break;
                }
            }
        }
        return result;
    }
}
