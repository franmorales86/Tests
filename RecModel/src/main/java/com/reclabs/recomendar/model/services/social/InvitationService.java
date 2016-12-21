/**
 * Project: RecModel
 * Created by: raulanatol at 05/11/13 12:29
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.model.services.social;

import com.reclabs.recomendar.model.documents.social.Invitation;

/**
 * @author raulanatol
 */
public interface InvitationService {
    /**
     * Create an invitation
     * @param email The email to the new user
     * @return The invitation.
     */
    Invitation createInvitation(String email);

    /**
     * @param email The email to verify
     * @param code The code to verify
     * @return True if the invitation is correct
     */
    boolean existInvitation(String email, String code);

}
