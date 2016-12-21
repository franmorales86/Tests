/**
 * Project: RecCore
 * Created by: fjmorales at 02/04/2013 18:20:38
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.controllers.api.users;

import com.reclabs.recomendar.common.exceptions.RecIllegalArgumentException;
import com.reclabs.recomendar.common.exceptions.generic.RecRuntimeException;
import com.reclabs.recomendar.core.misc.security.OAuthHelper;
import com.reclabs.recomendar.core.misc.security.SecurityHelper;
import com.reclabs.recomendar.model.documents.users.User;
import com.reclabs.recomendar.model.objects.user.UserDTO;
import com.reclabs.recomendar.model.objects.user.UserOwnDataDTO;
import com.reclabs.recomendar.model.services.UserService;
import com.reclabs.recomendar.model.types.SocialUserProviderId;
import com.reclabs.recomendar.objects.ResponseDTO;
import com.reclabs.recomendar.objects.users.ChangePasswordDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.servlet.http.HttpServletRequest;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.internal.verification.VerificationModeFactory.times;

/**
 * @author fjmorales
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(SecurityHelper.class)
@PowerMockIgnore({"javax.management.*", "javax.xml.parsers.*", "com.sun.org.apache.xerces.internal.jaxp.*", "ch.qos.logback.*", "org.slf4j.*"})
public class UsersControllerTest {

    @InjectMocks
    protected UsersController usersController = new UsersController();

    @Mock
    private UserService userService;

    @Mock
    private OAuthHelper oAuthHelper;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * @see UsersController#createUser(UserDTO)
     */
    @Test(expected = RecIllegalArgumentException.class)
    public void createUserTestWithNull() {
        usersController.createUser(null);
    }

    /**
     * @see UsersController#createUser(UserDTO)
     */
    @Test(expected = RecIllegalArgumentException.class)
    public void createUserTestWithEmptyUsername() {
        usersController.createUser(new UserDTO());
    }

    /**
     * @see UsersController#deleteUser(String)
     */
    @Test(expected = RecIllegalArgumentException.class)
    public void deleteUserTestWithNull() {
        usersController.deleteUser(null);
    }

    /**
     * @see UsersController#findByUsername(String)
     */
    @Test(expected = RecIllegalArgumentException.class)
    public void searchUserTestWithNull() {
        usersController.findByUsername(null);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void updateUserOwnDataUserTestWithNull() {
        usersController.updateOwnData(null, null);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void updateUserTestWithEmptyUsername() {
        usersController.updateOwnData(new UserOwnDataDTO(), null);
    }

    /**
     * @see UsersController#changePassword(com.reclabs.recomendar.objects.users.ChangePasswordDTO)
     */
    @Test(expected = RecIllegalArgumentException.class)
    public void changePasswordWithNullParameter() {
        usersController.changePassword(null);
    }

    /**
     * @see UsersController#changePassword(com.reclabs.recomendar.objects.users.ChangePasswordDTO)
     */
    @Test(expected = RecIllegalArgumentException.class)
    public void changePasswordWithNullOldPasswordParameter() {
        ChangePasswordDTO parameter = new ChangePasswordDTO();
        parameter.setOldPassword(null);
        usersController.changePassword(parameter);
    }

    /**
     * @see UsersController#changePassword(com.reclabs.recomendar.objects.users.ChangePasswordDTO)
     */
    @Test(expected = RecIllegalArgumentException.class)
    public void changePasswordWithEmptyOldPasswordParameter() {
        ChangePasswordDTO parameter = new ChangePasswordDTO();
        parameter.setOldPassword("");
        usersController.changePassword(parameter);
    }

    /**
     * @see UsersController#changePassword(com.reclabs.recomendar.objects.users.ChangePasswordDTO)
     */
    @Test(expected = RecIllegalArgumentException.class)
    public void changePasswordWithEmptyNewPasswordParameter() {
        ChangePasswordDTO parameter = new ChangePasswordDTO();
        parameter.setOldPassword("asdad");
        parameter.setNewPassword("");
        usersController.changePassword(parameter);
    }

    /**
     * @see UsersController#changePassword(com.reclabs.recomendar.objects.users.ChangePasswordDTO)
     */
    @Test(expected = RecIllegalArgumentException.class)
    public void changePasswordWithNullNewPasswordParameter() {
        ChangePasswordDTO parameter = new ChangePasswordDTO();
        parameter.setOldPassword("oldPassword");
        parameter.setNewPassword(null);
        usersController.changePassword(parameter);
    }

    /**
     * @see UsersController#changePassword(com.reclabs.recomendar.objects.users.ChangePasswordDTO)
     */
    //@Test TODO mirar como hacer para que se pueda hacer un mockstatic del SecurityHelper
    public void changePasswordWithValidParameters() {
        String newPassword = "newPassword";
        String oldPassword = "oldPassword";
        ChangePasswordDTO parameter = new ChangePasswordDTO();
        parameter.setOldPassword(oldPassword);
        parameter.setNewPassword(newPassword);
        usersController.changePassword(parameter);
        Mockito.verify(userService, times(1)).changePassword(Mockito.any(User.class), oldPassword, newPassword);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void setAutoSharedWithNullProviderId() throws Exception {
        //FIXME ver como hacer un new del httpServletRequest
        HttpServletRequest dummyHttpServletRequest = null;
        usersController.setAutoShared(null, "value", dummyHttpServletRequest);
    }

//    @Test
//    public void setAutoSharedWithInvalidValue() throws Exception {
//        FIXME ver como hacer un new del httpServletRequest
//        HttpServletRequest dummyHttpServletRequest = null;
//        usersController.setAutoShared(SocialUserProviderId.FACEBOOK.getProviderId(), "invalidValue", dummyHttpServletRequest);
//    }

//    @Test
//    public void setAutoSharedWithInvalidProviderId() throws Exception {
//        FIXME ver como hacer un new del httpServletRequest
//        HttpServletRequest dummyHttpServletRequest = null;
//        usersController.setAutoShared("invalidSocialProviderId", "true", dummyHttpServletRequest);
//    }

    @Test(expected = RecRuntimeException.class)
    public void setAutoSharedWithAnonymousUser() throws Exception {
        String providerId = SocialUserProviderId.FACEBOOK.getProviderId();
        String value = "true";
        PowerMockito.mockStatic(SecurityHelper.class);
        Mockito.when(SecurityHelper.getLoggedInUser()).thenThrow(new RecRuntimeException("User principal unknown"));
        usersController.setAutoShared(providerId, value, null);
    }

    //TODO mirar xq el userService es null - No está funcionando el @Mock
    @Test
    public void setAutoSharedWithNormalData() throws Exception {
        User user = new User();
        String providerId = SocialUserProviderId.TWITTER.getProviderId();
        String value = "true";
        HttpServletRequest request = null; //TODO hacer un dummy del request

        PowerMockito.mockStatic(SecurityHelper.class);
        Mockito.when(SecurityHelper.getLoggedInUser()).thenReturn(user);

        ResponseDTO response = usersController.setAutoShared(providerId, value, request);
        assertThat(response, is(ResponseDTO.OK));
    }
}
