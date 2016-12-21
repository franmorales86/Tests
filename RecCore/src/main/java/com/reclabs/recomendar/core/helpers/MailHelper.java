/**
 * Project: RecCore
 * Created by: raulanatol at 11/03/2013 09:23:15
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.helpers;

import com.reclabs.barneylib.LinkType;
import com.reclabs.recomendar.common.helpers.misc.HashHelper;
import com.reclabs.recomendar.common.helpers.types.DateHelper;
import com.reclabs.recomendar.maildriver.RecMailDriver;
import com.reclabs.recomendar.maildriver.data.*;
import com.reclabs.recomendar.maildriver.types.MailType;
import com.reclabs.recomendar.model.documents.social.Invitation;
import com.reclabs.recomendar.model.documents.users.User;
import com.reclabs.recomendar.model.services.BarneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.Arrays;
import java.util.Date;

/**
 * Clase de ayuda para el servicio de emails.
 * @author raulanatol
 */
@SuppressWarnings("WeakerAccess")
public class MailHelper {
    private static final String NO_REPLY_RECOMENDAR_MAIL = "\"Recomendar.com\" <no-reply@recomendar.com>";

    @Autowired
    private RecMailDriver recMailDriver;

    @Value("${misc.web.url}")
    protected String prefixWebURL;

    @Value("${misc.core.url}")
    protected String prefixCoreURL;

    @Autowired
    private BarneyService barneyService;

    /**
     * Generate an email verification url that will be sent to user for to validate his email.
     * @param verificationCode
     * @param userEmail
     * @return
     */
    protected String generateEmailVerificationURL(String verificationCode, String userEmail) {
        String encodingVerificationCode = HashHelper.encodeStringMode(verificationCode);
        String campaingCode = "utm_campaing=verify_email&utm_source=email&utm_medium=register_email";
        String largeURL = prefixCoreURL + "/verify/" + userEmail + "/" + encodingVerificationCode + "?" + campaingCode;
        //TODO en el futuro cambiar LinkType.SIMPLE por VERIFY_EMAIL o por TEMPORAL_LINK (este mejor porque así podremos hacer un cron que borre las que tengan más de X tiempo)
        return barneyService.createShortUrl(largeURL, "verifyEmail", LinkType.SIMPLE, "0");
    }

    /**
     * Generate an url invitation
     * @param code the code of the invitation
     * @param email the destination email
     * @param userId the id of the user
     * @param creationDate Date of the creation of the invitation
     * @return The url of the invitation
     */
    protected String generateInvitationURL(String code, String email, String userId, Date creationDate) {
        String encodingVerificationCode = HashHelper.encodeStringMode(code);
        String campaingCode = "utm_campaing=invitation_email&utm_source=email&utm_medium=invitation_email&content=" + DateHelper.getMonthName(creationDate);
        String largeURL = prefixCoreURL + "/verifyInvitation/" + email + "/" + encodingVerificationCode + "?" + campaingCode;
        return barneyService.createShortUrl(largeURL, "invitationURL", LinkType.INVITATION, userId);
    }

    /**
     * Generate a url that will be sent to user for to validate his email.
     * @param verificationCode
     * @param userEmail
     * @return
     */
    protected String generateDeleteAccountURL(String verificationCode, String userEmail) {
        //FIXME en el futuro usar una url acortada.
        String encodingDeleteCode = HashHelper.encodeStringMode(verificationCode);
        return prefixCoreURL + "/deleteAccount/" + userEmail + "/" + encodingDeleteCode;
    }

    /**
     * Enviamos el email de confirmación al usuario pasado por parámetros.
     * En el envío de la confirmación será necesario crear un link para que el usuario desde su email, pueda
     * validar el valor del mismo.
     * @param user El usuario al que se le enviará la notificación vía email.
     */
    public void sendConfirmationMail(User user) {
        String subject = "Alta en recomendar";
        ConfirmMailObject mailData = new ConfirmMailObject(Arrays.asList(user.getEmail()), subject, NO_REPLY_RECOMENDAR_MAIL);
        mailData.setNameUser(user.getName());
        mailData.setUrlRecomendar(prefixWebURL);
        mailData.setUrlVerifyEmail(generateEmailVerificationURL(user.getEmailVerificationCode(), user.getEmail()));
        recMailDriver.sendMail(MailType.CONFIRMATION_MAIL, mailData);
    }

    /**
     * Send mail to indicate to user that the email confirmation is correct.
     * @param user User to advice.
     */
    public void sendVerificationMail(User user) {
        String subject = "Correo verificado correctamente";
        VerificationMailObject mailData = new VerificationMailObject(Arrays.asList(user.getEmail()), subject, NO_REPLY_RECOMENDAR_MAIL);
        mailData.setNameUser(user.getName());
        recMailDriver.sendMail(MailType.VERIFICATION_MAIL, mailData);
    }

    /**
     * Send mail to indicate to user that your delete account process is starting.
     * @param user User to advice
     */
    public void sendDeleteAccountMail(User user) {
        String subject = "Baja en recomendar";
        DeleteAccountObject mailData = new DeleteAccountObject(Arrays.asList(user.getEmail()), subject, NO_REPLY_RECOMENDAR_MAIL);
        mailData.setNameUser(user.getName());
        //FIXME meter la url de la campaña
        mailData.setUrlRecomendar(prefixWebURL);
        mailData.setUrlVerifyEmail(generateDeleteAccountURL(user.getDeleteAccountCode(), user.getEmail()));
        recMailDriver.sendMail(MailType.DELETE_ACCOUNT, mailData);
    }

    /**
     * Send mail with the new password.
     * @param user User to advice.
     * @param newPassword New password generated by recomendar.
     */
    public void sendPasswordReset(User user, String newPassword) {
        String subject = "Nueva contraseña";
        ResetPassword mailData = new ResetPassword(Arrays.asList(user.getEmail()), subject, NO_REPLY_RECOMENDAR_MAIL);
        mailData.setNameUser(user.getName());
        mailData.setNewPassword(newPassword);
        recMailDriver.sendMail(MailType.RESET_PASSWORD, mailData);
    }

    /**
     * Send an invitation email with the url of the invitation
     * @param invitation The invitation data
     * @param userId The userId that do the invitation
     */
    public void sendInvitation(Invitation invitation, String userId) {
        String subject = "Invitación para entrar en Recomendar";
        InvitationMailObject mailData = new InvitationMailObject(Arrays.asList(invitation.getEmail()), subject, NO_REPLY_RECOMENDAR_MAIL);
        mailData.setInvitationURL(generateInvitationURL(invitation.getCode(), invitation.getEmail(), userId, invitation.getCreationDate()));
        recMailDriver.sendMail(MailType.INVITATION, mailData);
    }
}
