/**
 * Project: RecCore
 * Created by: raulanatol at 05/11/13 13:09
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.controllers.api.social;

import com.reclabs.recomendar.common.helpers.ParameterHelper;
import com.reclabs.recomendar.core.dto.generic.EmailDTO;
import com.reclabs.recomendar.core.helpers.MailHelper;
import com.reclabs.recomendar.core.misc.security.SecurityHelper;
import com.reclabs.recomendar.model.documents.social.Invitation;
import com.reclabs.recomendar.model.documents.users.User;
import com.reclabs.recomendar.model.services.social.InvitationService;
import com.reclabs.recomendar.objects.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author raulanatol
 */
@Controller
@RequestMapping(value = "/v1/invitations/")
public class InvitationController {

    @Autowired
    private InvitationService invitationService;

    @Autowired
    private MailHelper mailHelper;

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize("#oauth2.isUser() and #oauth2.clientHasRole('ROLE_USER') and #oauth2.hasScope('write')")
    public ResponseDTO inviteUser(@RequestBody EmailDTO emailDTO) {
        ParameterHelper.assertNull(emailDTO);
        ParameterHelper.assertEmpty(emailDTO.getEmail());
        User user = SecurityHelper.getLoggedInUser();
        Invitation invitation = invitationService.createInvitation(emailDTO.getEmail());
        mailHelper.sendInvitation(invitation, user.getId());
        return ResponseDTO.OK;
    }
}
