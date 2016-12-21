/**
 * Project: RecCore
 * Created by: raulanatol at 05/11/13 12:59
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.services.social;

import com.reclabs.recomendar.common.helpers.RandomHelper;
import com.reclabs.recomendar.common.helpers.misc.HashHelper;
import com.reclabs.recomendar.common.helpers.types.DateHelper;
import com.reclabs.recomendar.common.helpers.types.StringHelper;
import com.reclabs.recomendar.common.types.DatePrecisionType;
import com.reclabs.recomendar.model.documents.social.Invitation;
import com.reclabs.recomendar.model.repositories.social.InvitationRepository;
import com.reclabs.recomendar.model.services.social.InvitationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author raulanatol
 */
@Service
public class InvitationServiceImpl implements InvitationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(InvitationServiceImpl.class);
    private static final int VALID_DAYS = 5;

    @Autowired
    private InvitationRepository invitationRepository;

    @Override
    public Invitation createInvitation(String email) {
        LOGGER.info("[Creating an invitation][Email: {}]", email);
        String code = RandomHelper.getRandomNumber(8);
        Invitation invitation = new Invitation();
        invitation.setCode(code);
        invitation.setEmail(email);
        invitation.setCreationDate(DateHelper.getCurrentDate(DatePrecisionType.MILLISECOND));
        return invitationRepository.save(invitation);
    }

    @Override
    public boolean existInvitation(String email, String code) {
        boolean result = false;
        List<Invitation> invitations = invitationRepository.findByEmail(email);
        for (Invitation invitation : invitations) {
            if (DateHelper.getDateDiff(DateHelper.getCurrentDate(DatePrecisionType.MILLISECOND), invitation.getCreationDate()) < VALID_DAYS) {
                String encodingCode = HashHelper.encodeStringMode(invitation.getCode());
                if (!StringHelper.equals(encodingCode, code)) {
                    LOGGER.warn("[Trying to use invitation with invalid code][Email: {}][Code: {}]", email, code);
                } else {
                    result = true;
                    break;
                }
            } else {
                LOGGER.warn("[Trying to use invitation with invalid email or expired][Email: {}][Code: {}]", email, code);
            }
        }
        return result;
    }
}
