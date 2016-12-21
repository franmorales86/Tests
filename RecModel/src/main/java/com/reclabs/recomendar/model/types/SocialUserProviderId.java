/**
 * Project: RecModel
 * Created by: raulanatol at 04/03/2013 12:04:11
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.model.types;

import com.reclabs.recomendar.common.helpers.types.StringHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author raulanatol
 */
@SuppressWarnings("WeakerAccess")
public enum SocialUserProviderId {
    /**
     * SocialUser provider Id de Twitter.
     */
    TWITTER("twitter"),
    /**
     * Provider para Facebook
     */
    FACEBOOK("facebook");
    /**
     * El provider id del social user.
     */
    private final String providerId;

    /**
     * @param providerId
     */
    private SocialUserProviderId(String providerId) {
        this.providerId = providerId;
    }

    /**
     * @return the providerId
     */
    public String getProviderId() {
        return providerId;
    }

    /**
     * @param providerName
     * @return true en caso de que el providerId sea el mismo.
     */
    public boolean is(String providerName) {
        return StringHelper.equals(providerName, providerId);
    }

    /**
     * @param providerId2Find
     * @return Gets SocialUserProviderId by a providerId2Find.
     */
    public static SocialUserProviderId byProviderId(String providerId2Find) {
        SocialUserProviderId result = null;
        if (!StringHelper.isEmpty(providerId2Find)) {
            for (SocialUserProviderId item : values()) {
                if (StringHelper.equals(item.getProviderId(), providerId2Find)) {
                    result = item;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * Dado un listado de providerId obtenemos un listado de SocialUserProviderId
     * @param providersId El listado providerIds
     * @return El listado de {@link SocialUserProviderId}
     */
    public static List<SocialUserProviderId> byStrings(List<String> providersId) {
        List<SocialUserProviderId> result = new ArrayList<>();
        for (String providerId : providersId) {
            SocialUserProviderId item = byProviderId(providerId);
            if (item != null) {
                result.add(item);
            }
        }
        return result;
    }
}
