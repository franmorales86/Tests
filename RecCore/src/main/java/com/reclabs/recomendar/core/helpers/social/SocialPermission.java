/**
 * Project: RecCore
 * Created by: raulanatol at 25/11/13 15:44
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.helpers.social;

/**
 * @author raulanatol
 */
public enum SocialPermission {
    FACEBOOK_PUBLISH_STREAM("publish_stream");

    public String value;

    private SocialPermission(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
