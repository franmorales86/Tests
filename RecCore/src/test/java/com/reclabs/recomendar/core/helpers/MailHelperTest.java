/**
 * Project: RecCore
 * Created by: raulanatol at 18/05/13 19:54
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.helpers;

import com.reclabs.barneylib.LinkType;
import com.reclabs.recomendar.common.helpers.misc.HashHelper;
import com.reclabs.recomendar.common.helpers.types.DateHelper;
import com.reclabs.recomendar.model.services.BarneyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author raulanatol
 * @see MailHelper
 */
@RunWith(MockitoJUnitRunner.class)
public class MailHelperTest {

    @Mock
    private BarneyService barneyService;


    @InjectMocks
    protected MailHelper mailHelper = new MailHelper();

    /**
     * @see MailHelper#generateEmailVerificationURL(String, String)
     */
    @Test
    public void generateEmailVerificationURLSimpleTest() {
        String coreURL = "https://api.recomendar.com";
        mailHelper.prefixCoreURL = coreURL;
        String verificationCode = "juofkdjanl";
        String userEmail = "test@email.com";
        String expected = "http://rcmd.me/XDs12G";
        String encodingVerificationCode = HashHelper.encodeStringMode(verificationCode);
        String campaingCode = "utm_campaing=verify_email&utm_source=email&utm_medium=register_email";
        String largeURL = coreURL + "/verify/" + userEmail + "/" + encodingVerificationCode + "?" + campaingCode;

        Mockito.when(barneyService.createShortUrl(largeURL, "verifyEmail", LinkType.SIMPLE, "0")).thenReturn(expected);
        String result = mailHelper.generateEmailVerificationURL(verificationCode, userEmail);

        assertThat(result, is(expected));
    }


    @Test
    public void generateInvitationURLWithSimpleData() throws Exception {
        String coreURL = "https://api.recomendar.com";
        mailHelper.prefixCoreURL = coreURL;
        String email = "test@recomendar.com";
        String code = "okhgfbs";
        String encodingVerificationCode = HashHelper.encodeStringMode(code);
        Date creationDate = DateHelper.getFixedDate(2010, 10, 10);
        String userId = "12";
        String campaingCode = "utm_campaing=invitation_email&utm_source=email&utm_medium=invitation_email&content=" + DateHelper.getMonthName(creationDate);
        String largeURL = coreURL + "/verifyInvitation/" + email + "/" + encodingVerificationCode + "?" + campaingCode;
        String expected = "http://rcmd.me/XDs12G";

        Mockito.when(barneyService.createShortUrl(largeURL, "invitationURL", LinkType.INVITATION, userId)).thenReturn(expected);
        String result = mailHelper.generateInvitationURL(code, email, userId, creationDate);

        assertThat(result, is(expected));
    }
}
