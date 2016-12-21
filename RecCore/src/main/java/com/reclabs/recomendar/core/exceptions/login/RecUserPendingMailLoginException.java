/**
 * Project: RecCore
 * Created by: raulanatol at 04/10/13 13:11
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.exceptions.login;

import org.springframework.security.authentication.AccountStatusException;

/**
 * @author raulanatol
 */
public class RecUserPendingMailLoginException extends AccountStatusException {
    private static final long serialVersionUID = -5149798371352132980L;

    /**
     * @param userId The user id
     * @param email The email used to login
     */
    public RecUserPendingMailLoginException(String userId, String email) {
        super("The user " + userId + " with email " + email + " trying to login with pending mail status");
    }
}
