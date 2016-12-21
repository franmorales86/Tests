/**
 * Project: RecCore
 * Created by: raulanatol at 05/03/2013 13:00:42
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.services;

import com.reclabs.recomendar.common.exceptions.RecIllegalArgumentException;
import com.reclabs.recomendar.common.helpers.misc.HashHelper;
import com.reclabs.recomendar.core.assemblers.UserAssembler;
import com.reclabs.recomendar.core.helpers.MailHelper;
import com.reclabs.recomendar.core.helpers.QueueHelper;
import com.reclabs.recomendar.core.helpers.UserHelper;
import com.reclabs.recomendar.core.misc.security.SecurityHelper;
import com.reclabs.recomendar.model.documents.users.SocialUser;
import com.reclabs.recomendar.model.documents.users.User;
import com.reclabs.recomendar.model.exceptions.DuplicateUserException;
import com.reclabs.recomendar.model.objects.user.UserDTO;
import com.reclabs.recomendar.model.objects.user.UserSimpleDataDTO;
import com.reclabs.recomendar.model.repositories.users.UserRepository;
import com.reclabs.recomendar.model.services.UserService;
import com.reclabs.recomendar.model.types.SocialUserProviderId;
import com.reclabs.recomendar.model.types.user.UserState;
import org.apache.commons.lang3.RandomStringUtils;
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
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

/**
 * @author raulanatol
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(SecurityHelper.class)
@PowerMockIgnore({"javax.management.*", "javax.xml.parsers.*", "com.sun.org.apache.xerces.internal.jaxp.*", "ch.qos.logback.*", "org.slf4j.*"})
public class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private ShaPasswordEncoder passwordEncoder;

    @Mock
    private MailHelper mailHelper;

    @Mock
    private UserAssembler userAssembler;

    @Mock
    private QueueHelper queueHelper;

    @InjectMocks
    protected UserServiceImpl userService = new UserServiceImpl();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * @see UserServiceImpl#getUserSimpleDataByUsername(String)
     */
    @Test
    public void getUserSimpleDataByUsernameSimpleTest() {
        String username = "testUserName";

        UserSimpleDataDTO expected = new UserSimpleDataDTO();
        expected.setFacebookLinked(null);
        expected.setNumberOfRec(null);
        expected.setTwitterLinked(false);
        expected.setFacebookLinked(false);
        expected.setUsername(username);
        expected.setVisibleName("John Doe");

        User testUser = new User();
        testUser.setUsername(username);
        testUser.setName("John");
        testUser.setLastname1("Doe");

        userService.userAssembler = new UserAssembler();

        Mockito.when(userRepository.getByUsername(username)).thenReturn(testUser);

        UserSimpleDataDTO actual = userService.getUserSimpleDataByUsername(username);
        assertThat(actual, is(expected));
    }

    /**
     * With socialUser data.
     * @see UserServiceImpl#getUserSimpleDataByUsername(String)
     */
    @Test
    public void getUserSimpleDataByUsernameSocialUserSimpleTest() {
        String username = "testUserName";

        UserSimpleDataDTO expected = new UserSimpleDataDTO();
        expected.setFacebookLinked(null);
        expected.setNumberOfRec(null);
        expected.setTwitterLinked(true);
        expected.setFacebookLinked(false);
        expected.setUsername(username);
        expected.setVisibleName("John Doe");

        User testUser = new User();
        testUser.setUsername(username);
        testUser.setName("John");
        testUser.setLastname1("Doe");
        SocialUser twitterSocial = new SocialUser();
        twitterSocial.setProviderId(SocialUserProviderId.TWITTER.getProviderId());
        List<SocialUser> socialUsers = Arrays.asList(twitterSocial);
        testUser.setSocialUsers(socialUsers);

        userService.userAssembler = new UserAssembler();

        Mockito.when(userRepository.getByUsername(username)).thenReturn(testUser);

        UserSimpleDataDTO actual = userService.getUserSimpleDataByUsername(username);
        assertThat(actual, is(expected));
    }

    /**
     * With null socialUsers
     */
    @Test
    public void userHasProviderIdWithNullValuesTest() {
        User user = new User();
        user.setSocialUsers(null);
        assertFalse(UserHelper.userHasProviderId(user, SocialUserProviderId.TWITTER));
    }

    /**
     * With normal data.
     * @see UserServiceImpl#getUserOwnDataByUser(User)
     */
    // @Test
    public void getUserOwnDataByUserNormalData() {
//        User user = new User();
//        user.setUsername("JohnDoe");
//        UserOwnDataDTO userOwnDataDTO = userService.getUserOwnDataByUser(user);
//
//        UserOwnDataDTO expectedData = new UserOwnDataDTO();
//        expectedData.setUsername("JohnDoe");
//        expectedData.setName("");
//        assertThat(userOwnDataDTO, is(expectedData));
    }

    /**
     * @see UserService#createUser(UserDTO)
     */
    @Test(expected = DuplicateUserException.class)
    public void createUserTestWithExceptions() {
        UserDTO user = new UserDTO();
        user.setUsername("username");
        user.setEmail("test");
        Mockito.when(userRepository.existsUserByUsername(user.getUsername())).thenReturn(true);
        userService.createUser(user);
    }

    /**
     * @see UserService#createUser(UserDTO)
     */
    @Test(expected = DuplicateUserException.class)
    public void updateUserTestWithException() {
        userService.userAssembler = new UserAssembler();

        UserDTO user = new UserDTO();
        user.setId("1234");
        user.setUsername("username");
        User userBBDD = new User();
        userBBDD.setUsername("username2");
        Mockito.when(userRepository.findOne("1234")).thenReturn(userBBDD);
        Mockito.when(userRepository.existsUserByUsername(user.getUsername())).thenReturn(true);
        userService.updateUser(user);
    }

    /**
     * @see UserServiceImpl#verifyEmail(String, String)
     */
    @Test
    public void verifyEmailWithUserEmailNullTest() {
        assertFalse(userService.verifyEmail(null, "codeTest"));
        assertFalse(userService.verifyEmail("user@test.com", null));
    }

    /**
     * @see UserServiceImpl#verifyEmail(String, String)
     */
    @Test
    public void verifyEmailWithUnknownEmailTest() {
        String testEmail = "test@email.com";
        Mockito.when(userRepository.findByEmail(testEmail)).thenReturn(null);
        assertFalse(userService.verifyEmail(testEmail, "testCode"));
    }

    /**
     * @see UserServiceImpl#verifyEmail(String, String)
     */
    @Test
    public void verifyEmailWithInvalidStatusTest() {
        String testEmail = "test@email.com";
        User user = new User();
        user.setState(UserState.ACTIVE);
        Mockito.when(userRepository.findByEmail(testEmail)).thenReturn(user);
        assertFalse(userService.verifyEmail(testEmail, "testCode"));
    }

    /**
     * @see UserServiceImpl#verifyEmail(String, String)
     */
    @Test
    public void verifyEmailWithValidStatusTest() {
        String testEmail = "test@email.com";
        String verificationCode = RandomStringUtils.randomAlphabetic(10);
        User user = new User();
        user.setState(UserState.PENDING_MAIL);
        user.setEmailVerificationCode(verificationCode);
        Mockito.when(userRepository.findByEmail(testEmail)).thenReturn(user);
        assertTrue(userService.verifyEmail(testEmail, HashHelper.encodeStringMode(verificationCode)));

        Mockito.verify(userRepository, Mockito.times(1)).save(user);
        Mockito.verify(mailHelper, Mockito.times(1)).sendVerificationMail(user);
        Mockito.verify(queueHelper, Mockito.times(1)).registerOnQueueNewEmail(user);
    }

    /**
     * @see UserServiceImpl#changePassword(com.reclabs.recomendar.model.documents.users.User, String, String)
     */
    @Test(expected = RecIllegalArgumentException.class)
    public void changePasswordWithInvalidPassword() {
        String encodedPassword = "encodedPassword";
        String oldPassword = "oldPassword";
        String oldInvalidPassword = "oldInvalidPassword";
        User user = new User();
        user.setPassword(oldInvalidPassword);

        Mockito.when(passwordEncoder.encodePassword(oldPassword, null)).thenReturn(encodedPassword);
        userService.changePassword(user, oldPassword, null);
    }

    /**
     * @see UserServiceImpl#changePassword(com.reclabs.recomendar.model.documents.users.User, String, String)
     */
    @Test
    public void changePasswordWithValidPassword() {
        String encodedPassword = "encodedPassword";
        String oldPassword = "oldPassword";
        User user = new User();
        user.setPassword(encodedPassword);

        Mockito.when(passwordEncoder.encodePassword(oldPassword, null)).thenReturn(encodedPassword);
        userService.changePassword(user, oldPassword, null);
        Mockito.verify(userRepository, times(1)).save(user);
    }

    @Test
    public void setAutoSharedWithAnySocialUser() throws Exception {
        User user = new User();
        user.setId("USER_1");
        userService.setAutoShared(user, SocialUserProviderId.FACEBOOK, true);

        Mockito.verify(userRepository, Mockito.never()).save(user);
    }

    @Test
    public void setAutoSharedWithInvalidSocialUserId() throws Exception {
        List<SocialUser> socialUsers = new ArrayList<>();

        SocialUser facebookSocialUser = new SocialUser();
        facebookSocialUser.setProviderId(SocialUserProviderId.FACEBOOK.getProviderId());

        socialUsers.add(facebookSocialUser);

        User user = new User();
        user.setId("USER_1");
        user.setSocialUsers(socialUsers);

        userService.setAutoShared(user, SocialUserProviderId.TWITTER, true);

        Mockito.verify(userRepository, Mockito.never()).save(user);
    }

    /**
     * @see UserServiceImpl#setAutoShared(User, SocialUserProviderId, boolean)
     */
    @Test
    public void setAutoSharedWithValidSocialUserId() throws Exception {
        List<SocialUser> socialUsers = new ArrayList<>();

        SocialUser facebookSocialUser = new SocialUser();
        facebookSocialUser.setProviderId(SocialUserProviderId.FACEBOOK.getProviderId());

        SocialUser facebookSocialUser2 = new SocialUser();
        facebookSocialUser2.setProviderId(SocialUserProviderId.FACEBOOK.getProviderId());

        SocialUser twitterSocialUser = new SocialUser();
        twitterSocialUser.setProviderId(SocialUserProviderId.TWITTER.getProviderId());

        socialUsers.add(twitterSocialUser);
        socialUsers.add(facebookSocialUser);
        socialUsers.add(facebookSocialUser2);

        User user = new User();
        user.setId("JUnitTest");
        user.setSocialUsers(socialUsers);

        PowerMockito.mockStatic(SecurityHelper.class);

        userService.setAutoShared(user, SocialUserProviderId.FACEBOOK, true);

        Mockito.verify(userRepository, Mockito.times(1)).save(user);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void deleteUserWithNullUsername() {
        userService.deleteUser(null);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void deleteUserWithEmptyUsername() {
        userService.deleteUser("");
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void addSocialUserWithNullUser2Link() {
        userService.addSocialUser(null, new SocialUser());
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void addSocialUserWithNullSocialUser() {
        userService.addSocialUser(new User(), null);
    }

    /**
     * @see UserServiceImpl#addSocialUser(User, SocialUser)
     */
    @Test
    public void addSocialUserCheckingUpdateUserDataWithNullOldUser() {
        User user = new User();

        SocialUser socialUser = new SocialUser();

        Mockito.when(userAssembler.clone(user)).thenReturn(null);

        userService.addSocialUser(user, socialUser);

        Mockito.verify(userRepository, Mockito.times(1)).save(user);
    }

    /**
     * @see UserServiceImpl#addSocialUser(User, SocialUser)
     */
    @Test
    public void addSocialUserCheckingUpdateUserDataWhenEmailIsDifferent() {
        User user = new User();
        User oldUser = new User();
        user.setEmail("recommended@recomendar.com");
        oldUser.setEmail("notthesame@recomendar.com");

        SocialUser socialUser = new SocialUser();

        Mockito.when(userAssembler.clone(user)).thenReturn(null);

        userService.addSocialUser(user, socialUser);

        Mockito.verify(userRepository, Mockito.times(1)).save(user);
    }

    /**
     * @see UserServiceImpl#addSocialUser(User, SocialUser)
     */
    @Test
    public void addSocialUserCheckingUpdateUserDataWhenEmailIsDifferentAndUserIsInactive() {
        User user = new User();
        User oldUser = new User();
        user.setEmail("recommended@recomendar.com");
        user.setState(UserState.INACTIVE);
        oldUser.setEmail("notthesame@recomendar.com");

        SocialUser socialUser = new SocialUser();

        Mockito.when(userAssembler.clone(user)).thenReturn(null);

        userService.addSocialUser(user, socialUser);

        Mockito.verify(userRepository, Mockito.times(1)).save(user);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void findByUsernameWithNullUsername() {
        userService.findByUsername(null);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void findByUsernameWithEmptyUsername() {
        userService.findByUsername("");
    }

    /**
     * @see UserServiceImpl#findByUsername(String)
     */
    @Test
    public void findByUsernameWithZeroUsers() {
        List<User> userList = new ArrayList<>(0);

        String username = "myuniqueid";

        Mockito.when(userRepository.findByUsername(username)).thenReturn(userList);

        userService.findByUsername(username);
    }

    /**
     * @see UserServiceImpl#findByUsername(String)
     */
    @Test
    public void findByUsernameWithTwoUsers() {
        List<User> userList = new ArrayList<>(2);
        userList.add(new User());
        userList.add(new User());

        String username = "myuniqueid";

        Mockito.when(userRepository.findByUsername(username)).thenReturn(userList);

        userService.findByUsername(username);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void findByEmailWithNullEmail() {
        userService.findByEmail(null);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void findByEmailWithEmptyEmail() {
        userService.findByEmail("");
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void remSocialUserWithNullSocialUser() {
        userService.remSocialUser(null);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void deleteUserByUserIdWithNullUserId() {
        userService.deleteUserByUserId(null);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void deleteUserByUserIdWithEmptyUserId() {
        userService.deleteUserByUserId("");
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void updateUserWithNullUser() {
        User user = null;
        userService.updateUser(user);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void deleteAccountWithNullUser() {
        userService.deleteAccount(null);
    }

    /**
     * @see UserServiceImpl#deleteAccount(User)
     */
    @Test
    public void deleteAccountWithValidEmailAndActive() {
        User user = new User();
        user.setEmail("awesomemail@recomendar.com");
        user.setState(UserState.ACTIVE);

        Mockito.when(userRepository.save(user)).thenReturn(null);

        userService.deleteAccount(user);

        Mockito.verify(mailHelper, Mockito.times(1)).sendDeleteAccountMail(user);
    }

    /**
     * @see UserServiceImpl#deleteAccount(User)
     */
    @Test
    public void deleteAccountWithValidEmailAndInactiveState() {
        User user = new User();
        user.setEmail("awesomemail@recomendar.com");
        user.setState(UserState.INACTIVE);

        Mockito.when(userRepository.save(user)).thenReturn(null);

        userService.deleteAccount(user);

        Mockito.verify(mailHelper, Mockito.never()).sendDeleteAccountMail(user);
    }

    /**
     * @see UserServiceImpl#deleteAccount(User)
     */
    @Test
    public void deleteAccountWithValidEmailAndNullState() {
        User user = new User();
        user.setEmail("notfalse@recomendar.com");
        user.setState(null);

        Mockito.when(userRepository.save(user)).thenReturn(null);

        userService.deleteAccount(user);

        Mockito.verify(mailHelper, Mockito.never()).sendDeleteAccountMail(user);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void finishDeleteAccountWithNullEmail() {
        userService.finishDeleteAccount(null, "abddrrtt");
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void finishDeleteAccountWithEmptyEmail() {
        userService.finishDeleteAccount("", "abd23rtt");
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void finishDeleteAccountWithNullCode() {
        userService.finishDeleteAccount("notempty", null);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void finishDeleteAccountWithEmptyCode() {
        userService.finishDeleteAccount("userm@il", "");
    }

    @Test
    public void finishDeleteAccountWithNullDeleteAccountCode() throws Exception {
        User dummyUser = new User();
        dummyUser.setDeleteAccountCode(null);
        String email = "test@mail.com";
        Mockito.when(userRepository.findByEmail(email)).thenReturn(dummyUser);
        userService.finishDeleteAccount(email, "ignore");
    }

    @Test
    public void finishDeleteAccountTestingQueueHelperCall() throws Exception {
        String deleteAccountCode = "DELETE_ACCOUNT_CODE";
        User dummyUser = new User();
        dummyUser.setDeleteAccountCode(deleteAccountCode);
        String hashedDeleteAccountCode = "99554715a2b79c8a7fb3f3be517003966e26ef6bff9dbddcae8e3a8e0d79b72d";
        String email = "test@mail.com";
        Mockito.when(userRepository.findByEmail(email)).thenReturn(dummyUser);

        userService.finishDeleteAccount(email, hashedDeleteAccountCode);

        assertThat(dummyUser.getState(), is(UserState.EXPIRED));
        Mockito.verify(userRepository, Mockito.times(1)).save(dummyUser);
        Mockito.verify(queueHelper, Mockito.times(1)).registerUserDelete(dummyUser);
    }


    @Test(expected = RecIllegalArgumentException.class)
    public void resetPasswordWithNullUser() {
        userService.resetPassword(null);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void getUserByIdWithNullUserId() {
        userService.getUserById(null);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void getUserByIdWithEmptyUserId() {
        userService.getUserById("");
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void banUserWithNullUser() {
        userService.banUser(null);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void reduceRecommendationWithNullUserID() {
        userService.reduceRecommendation(null);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void reduceRecommendationWithEmptyUserID() {
        userService.reduceRecommendation("");
    }

    /**
     * @see UserServiceImpl#reduceRecommendation(String)
     */
    @Test
    public void reduceRecommendationCheckingDecrementAndSave() {
        String userId = "SGHI9001";

        User user = new User();
        user.setId(userId);
        user.setPendingRecommends(3L);

        User userToCheck = new User();
        userToCheck.setId(userId);
        userToCheck.setPendingRecommends(2L);

        Mockito.when(userRepository.findByUserId(userId)).thenReturn(user);

        userService.reduceRecommendation(userId);

        Mockito.verify(userRepository, Mockito.times(1)).save(userToCheck);
    }

    @Test
    public void getValidUsernameWithNewUsername() throws Exception {
        String newUsername = "newUsername";

        Mockito.when(userRepository.existsUserByUsername(newUsername)).thenReturn(false);

        assertThat(userService.getValidUsername(newUsername), is(newUsername));
    }

    @Test
    public void getValidUsernameWithExistingUsername() throws Exception {
        String currentUsername = "existingUsername";
        String newUsername = "newUsername";

        Mockito.when(userRepository.existsUserByUsername(currentUsername)).thenReturn(true);
        Mockito.when(userRepository.generateNewPossibleUserName(currentUsername)).thenReturn(newUsername);

        assertThat(userService.getValidUsername(currentUsername), is(newUsername));
    }
}
