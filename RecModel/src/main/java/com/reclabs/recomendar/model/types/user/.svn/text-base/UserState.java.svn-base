/**
 * Project: RecModel
 * Created by: raulanatol at 24/11/13 12:26
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.model.types.user;

/**
 * @author raulanatol
 */
public enum UserState {
    /**
     * Active user, normal mode
     */
    ACTIVE,
    /**
     * Inactive user, in case of desactive
     */
    INACTIVE,
    /**
     * Usuario creado pero al que le faltan datos por rellenar, este estado equivale a todos aquellos usuarios
     * dados de alta a través de redes sociales y que luego no se han completado sus datos, de, por ejemplo el
     * email o no han aceptado los términos y condiciones de uso.
     */
    HALF_DATA,
    /**
     * Banned user
     */
    BANNED,
    /**
     * Usuario con toda la información mínima introducida pero a la espera de que verifique el email.
     */
    PENDING_MAIL,
    /**
     * State of an user that no facebook friend's
     */
    PENDING_FRIENDS,
    /**
     * En caso de que la cuenta del usuario haya sido expirada.
     */
    EXPIRED;

    /**
     * @param state
     * @return
     */
    public static boolean isEnabled(UserState state) {
        boolean result = false;
        if (state != null) {
            switch (state) {
                case ACTIVE:
                case HALF_DATA:
                case PENDING_MAIL:
                    result = true;
                    break;
            }
        }
        return result;
    }
}