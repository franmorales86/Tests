/**
 * Project: RecModel
 * Created by: raulanatol at 24/12/2012 18:01:45
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.model.types;

import com.reclabs.recomendar.common.helpers.types.StringHelper;

/**
 * Representa el rol de seguridad que puede tener un determinado usuario.
 * @author raulanatol
 */
public enum SecurityRole {
    /**
     * Usuario del sandbox, el content manager o lo que veamos.
     */
    ROLE_SANDBOX_USER,
    /**
     * Adminsitrador del sandbox de usuarios y demás.
     */
    ROLE_SANDBOX_ADMIN,
    /**
     * El adminsitrador de la web.
     */
    ROLE_WEB_ADMIN,
    /**
     * El usuario normal de la web.
     */
    ROLE_WEB_USER,
    /**
     * Rol de pruebas, eliminar en cuanto hayan terminado las pruebas.
     */
    ROLE_USER;

    /**
     * @param value The value of verify
     * @return True if the current value is permit login sandbox
     */
    public static boolean permitLoginSandbox(String value) {
        return StringHelper.isAnyEquals(value, ROLE_SANDBOX_ADMIN.name(), ROLE_SANDBOX_USER.name());
    }
}
