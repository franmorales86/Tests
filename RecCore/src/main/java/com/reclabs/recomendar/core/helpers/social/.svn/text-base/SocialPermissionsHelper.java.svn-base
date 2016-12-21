/**
 * Project: RecCore
 * Created by: raulanatol at 25/11/13 15:43
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.helpers.social;

import com.reclabs.recomendar.common.helpers.types.CollectionHelper;
import com.reclabs.recomendar.common.helpers.types.StringHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.facebook.api.Facebook;

import java.util.List;

/**
 * @author raulanatol
 */
public class SocialPermissionsHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(SocialPermissionsHelper.class);

    public static boolean hasFacebookPermission(Facebook facebook, SocialPermission socialPermission) {
        boolean result = false;
        try {
            List<String> permissions = facebook.userOperations().getUserPermissions();
            if (!CollectionHelper.isEmpty(permissions)) {
                for (String permission : permissions) {
                    if (StringHelper.equals(permission, socialPermission.getValue())) {
                        result = true;
                        break;
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.warn("[Error trying get permissions]", e);
        }
        return result;
    }
}