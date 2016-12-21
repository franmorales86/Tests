/**
 * Project: RecCore
 * Created by: raulanatol at 09/11/2012 22:36:59
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.services;

import com.reclabs.recomendar.common.exceptions.RecIllegalArgumentException;
import com.reclabs.recomendar.common.exceptions.generic.ApiException;
import com.reclabs.recomendar.common.helpers.ParameterHelper;
import com.reclabs.recomendar.common.helpers.RandomHelper;
import com.reclabs.recomendar.common.helpers.misc.HashHelper;
import com.reclabs.recomendar.common.helpers.types.*;
import com.reclabs.recomendar.common.types.CodeErrorType;
import com.reclabs.recomendar.common.types.DatePrecisionType;
import com.reclabs.recomendar.core.assemblers.UserAssembler;
import com.reclabs.recomendar.core.helpers.MailHelper;
import com.reclabs.recomendar.core.helpers.QueueHelper;
import com.reclabs.recomendar.core.helpers.UserHelper;
import com.reclabs.recomendar.core.misc.security.OAuthHelper;
import com.reclabs.recomendar.core.misc.security.SecurityHelper;
import com.reclabs.recomendar.core.services.security.UserDetailsServiceImpl;
import com.reclabs.recomendar.model.documents.users.SocialUser;
import com.reclabs.recomendar.model.documents.users.User;
import com.reclabs.recomendar.model.exceptions.DuplicateUserException;
import com.reclabs.recomendar.model.objects.user.UserDTO;
import com.reclabs.recomendar.model.objects.user.UserOwnDataDTO;
import com.reclabs.recomendar.model.objects.user.UserSimpleDataDTO;
import com.reclabs.recomendar.model.repositories.users.UserRepository;
import com.reclabs.recomendar.model.services.ImageService;
import com.reclabs.recomendar.model.services.RecommendationService;
import com.reclabs.recomendar.model.services.UserService;
import com.reclabs.recomendar.model.types.SecurityRole;
import com.reclabs.recomendar.model.types.SocialUserProviderId;
import com.reclabs.recomendar.model.types.user.UserState;
import org.apache.commons.lang.NotImplementedException;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author raulanatol
 */
@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private static final String DEFAULT_USER_AVATAR = "http://images.recstatic.com/assets/user-unknow.png";

    /**
     * Assembler of user document
     */
    @Autowired
    protected UserAssembler userAssembler;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShaPasswordEncoder passwordEncoder;

    @Autowired
    private MailHelper mailHelper;

    @Autowired
    private OAuthHelper oAuthHelper;

    @Autowired
    private ImageService imageService;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private RecommendationService recommendationService;

    @Autowired
    private QueueHelper queueHelper;

    @Override
    public List<User> findAllPaginated(final int page, final int size) {
        Pageable pageable = new PageRequest(page, size);
        Page<User> userPage = this.userRepository.findAll(pageable);
        return userPage.getContent();
    }

    @Override
    public List<User> findListUsersByUsername(final String pattern) {
        throw new NotImplementedException();
    }

    @Override
    public void deleteUser(final String username) {
        ParameterHelper.assertEmpty(username);
        User user = findByUsername(username);
        if (!NullHelper.isAnyNull(user)) {
            deleteUserByUserId(user.getId());
        }
    }

    @Override
    public User createNewUser(final User user) {
        ParameterHelper.assertNull(user);
        return updateUserData(null, user);
    }

    @Override
    public void completeRegister(final UserDTO user) {
        User user2Modify = userRepository.findByUserId(user.getId());
        User oldUser = userAssembler.clone(user2Modify);
        userAssembler.copyNotNullValues(user2Modify, user);
        user2Modify.setState(UserState.PENDING_MAIL);
        updateUserData(oldUser, user2Modify);
    }

    @Override
    public User registerUser(final UserDTO user) {
        User user2Create = userAssembler.toLeft(user);
        user2Create.setState(UserState.PENDING_MAIL);
        user2Create.setRoles(Arrays.asList(SecurityRole.ROLE_USER));
        User newUser = updateUserData(null, user2Create);
        imageService.addUserImage(newUser.getId(), DEFAULT_USER_AVATAR);
        return newUser;
    }

    @Override
    public User addSocialUser(final User user2Link, final SocialUser socialUser) {
        ParameterHelper.assertAnyNull(user2Link, socialUser);
        LOGGER.debug("[Trying to AddSocialUser][User: {}][SocialUser: {}]", user2Link.getId(), socialUser.getProviderUserId());
        User oldUser = userAssembler.clone(user2Link);
        List<SocialUser> socialUsers = user2Link.getSocialUsers();
        if (CollectionHelper.isEmpty(socialUsers)) {
            socialUsers = new ArrayList<>();
        }
        //default autoShared default = true
        socialUser.setAutoShared(true);
        socialUsers.add(socialUser);
        user2Link.setSocialUsers(socialUsers);
        return updateUserData(oldUser, user2Link);
    }

    @Override
    public User findByUsername(final String username) {
        ParameterHelper.assertEmpty(username);
        User result = null;
        List<User> users = userRepository.findByUsername(username);
        if (!CollectionHelper.isEmpty(users)) {
            result = users.get(0);
        }
        return result;
    }

    @Override
    public User findByEmail(final String email) {
        ParameterHelper.assertEmpty(email);
        return userRepository.findByEmail(email);
    }

    @Override
    public void remSocialUser(final SocialUser socialUser) {
        ParameterHelper.assertNull(socialUser);
        userRepository.removeSocialUser(socialUser);
    }

    @Override
    public UserSimpleDataDTO getUserSimpleDataByUsername(final String username) {
        User user = userRepository.getByUsername(username);
        return userAssembler.toUserSimpleDTO(user);
    }

    @Override
    public UserSimpleDataDTO getUserSimpleDataById(String id) {
        User user = userRepository.findByUserId(id);
        return userAssembler.toUserSimpleDTO(user);
    }

    @Override
    public UserOwnDataDTO getUserOwnDataByUser(final User user) {
        UserOwnDataDTO result = new UserOwnDataDTO();
        result.setId(user.getId());
        result.setAvatarURL(user.getAvatarURL());
        result.setBio(user.getDescription());
        result.setEmail(user.getEmail());
        result.setName(user.getName());
        result.setState(user.getState());
        result.setUsername(user.getUsername());
        updateSocialUserTwitter(user, result);
        updateSocialUserFacebook(user, result);
        return result;
    }

    private void updateSocialUserFacebook(User user, UserOwnDataDTO result) {
        SocialUser facebookSocialUser = UserHelper.getSocialUserByProviderId(user, SocialUserProviderId.FACEBOOK);
        if (facebookSocialUser != null) {
            result.setFacebookLinked(true);
            result.setFacebookUsername(facebookSocialUser.getDisplayName());
            result.setFacebookAutoShare(facebookSocialUser.getAutoShared() == null ? false : facebookSocialUser.getAutoShared());
        } else {
            result.setFacebookLinked(false);
            result.setFacebookAutoShare(false);
        }
    }

    private void updateSocialUserTwitter(User user, UserOwnDataDTO result) {
        SocialUser twitterSocialUser = UserHelper.getSocialUserByProviderId(user, SocialUserProviderId.TWITTER);
        if (twitterSocialUser != null) {
            result.setTwitterLinked(true);
            result.setTwitterUsername(twitterSocialUser.getDisplayName());
            result.setTwitterAutoShare(twitterSocialUser.getAutoShared() == null ? false : twitterSocialUser.getAutoShared());
        } else {
            result.setTwitterLinked(false);
            result.setTwitterAutoShare(false);
        }
    }

    @Override
    public UserDTO createUser(UserDTO user) {
        ParameterHelper.assertAnyEmpty(user.getUsername(), user.getEmail());
        LOGGER.debug("[Starting process to create an user][Username: {}]", user.getUsername());
        if (userRepository.existsUserByUsername(user.getUsername())) {
            LOGGER.warn("[Trying to create an user with an existing name][username {}]", user.getUsername());
            throw new DuplicateUserException(user.getUsername());
        } else if (userRepository.findByEmail(user.getEmail()) != null) {
            LOGGER.warn("[Trying to create an user with an existing email][username {}][Email {}]", user.getUsername(), user.getEmail());
            throw new IllegalArgumentException("This email " + user.getEmail() + " already exists on Recomendar");
        }
        user.setPassword(passwordEncoder.encodePassword(user.getPassword(), null));
        return userAssembler.toRight(registerUser(user));
    }

    @Override
    public void deleteUserByUserId(String userId) {
        ParameterHelper.assertEmpty(userId);
        userRepository.delete(userId);
    }

    @Override
    public void updateUser(UserDTO user) {
        User userBBDD = userRepository.findOne(user.getId());
        User oldUser = userAssembler.clone(userBBDD);
        if (NullHelper.isAnyNull(userBBDD)) {
            throw new ApiException(CodeErrorType.USER_NOT_FOUND);
        }
        if (!StringHelper.equals(userBBDD.getUsername(), user.getUsername())) {
            if (userRepository.existsUserByUsername(user.getUsername())) {
                throw new DuplicateUserException(user.getUsername());
            }
        }
        user.setPassword(passwordEncoder.encodePassword(user.getPassword(), null));
        userBBDD = userAssembler.copyNotNullValues(userBBDD, user);
        updateUserData(oldUser, userBBDD);
    }

    @Override
    public void updateUser(User user2Update) {
        ParameterHelper.assertNull(user2Update);
        if (user2Update.getCreatedDate() == null) {
            user2Update.setCreatedDate(DateHelper.getCurrentDate(DatePrecisionType.MILLISECOND));
        }
        userRepository.save(user2Update);
    }

    @Override
    public void updateUserData(String userId, UserOwnDataDTO userOwnDataDTO) {
        User user = userRepository.findByUserId(userId);
        if (!StringHelper.equals(user.getAvatarURL(), userOwnDataDTO.getAvatarURL())) {
            user.setAvatarURL(userOwnDataDTO.getAvatarURL());
            LOGGER.info("[User changed][{}] Change avatar", user.getId());
        }

        if (!StringHelper.equals(user.getDescription(), userOwnDataDTO.getBio())) {
            user.setDescription(userOwnDataDTO.getBio());
            LOGGER.info("[User changed][{}] Update bio", user.getId());
        }

        if (!StringHelper.equals(user.getEmail(), userOwnDataDTO.getEmail())) {
            user.setEmail(userOwnDataDTO.getEmail());
            LOGGER.info("[User changed][{}] Change email", user.getId());
        }

        if (!StringHelper.equals(user.getName(), userOwnDataDTO.getName())) {
            user.setName(userOwnDataDTO.getName());
        }

        if (user.getCreatedDate() == null) {
            user.setCreatedDate(DateHelper.getCurrentDate(DatePrecisionType.MILLISECOND));
        }
        userRepository.save(user);
    }

    @Override
    public boolean existLinkedUser(String providerId, String providerUserId) {
        return !CollectionHelper.isEmpty(userRepository.findUserIdsByProviderIdAndProviderUserId(providerId, providerUserId));
    }

    /**
     * Verify email of the user with the data for parameters.
     * <b>Note: This method modify user document</b>
     * @param userEmail Email of user
     * @param verificationCode Verify code of the email
     * @return true if the user is verified. False otherwise
     */
    @Override
    public boolean verifyEmail(String userEmail, String verificationCode) {
        LOGGER.info("[Starting process to verify email][userEmail {}][VerificationCode {}]", userEmail, verificationCode);
        boolean result = false;
        if (!StringHelper.isAnyEmpty(userEmail, verificationCode)) {
            User user = findByEmail(userEmail);
            if (user != null && user.getState() == UserState.PENDING_MAIL) {
                String code = user.getEmailVerificationCode();
                String expectedCode = HashHelper.encodeStringMode(code);
                if (StringHelper.equals(verificationCode, expectedCode)) {
                    user.setState(UserState.ACTIVE);
                    user.setPendingRecommends(3L);
                    userRepository.save(user);
                    mailHelper.sendVerificationMail(user);
                    result = true;
                    queueHelper.registerOnQueueNewEmail(user);
                    LOGGER.info("[User mail validation successful][userID: {}]", user.getId());
                } else {
                    LOGGER.warn("[Trying email verification error, verification code is invalid][code {}][expected {}]", code, expectedCode);
                }
            } else {
                if (user == null) {
                    LOGGER.warn("[Trying email verification error the user is null][userEmail {}]", userEmail);
                } else {
                    LOGGER.warn("[Trying email verification error, user is not pendingMail status][userID {}][UserState {}]", user.getId(), user.getState());
                }
            }
        } else {
            LOGGER.warn("[Error trying email verification. A parameter is empty][userEmail {}][verificationCode {}]", userEmail, verificationCode);
        }
        return result;
    }

    /**
     * Gets a valid username from un possible valid username.
     * A valid username is an name of user unique on database.
     * @param possibleUserName Recommendation of username.
     * @return The valid username. For example: if possibleUsername is johnDoe and johnDoe exist on database this method return johnDoe12 that not exist on recomendar users database.
     */
    @Override
    public String getValidUsername(String possibleUserName) {
        String result = possibleUserName;
        if (userRepository.existsUserByUsername(possibleUserName)) {
            result = userRepository.generateNewPossibleUserName(possibleUserName);
        }
        return result;
    }

    @Override
    public void changePassword(User user, String oldPassword, String newPassword) {
        String oldPasswordEncoded = passwordEncoder.encodePassword(oldPassword, null);
        if (!StringHelper.equals(oldPasswordEncoded, user.getPassword())) {
            throw new RecIllegalArgumentException("Invalid password");
        }
        String newPasswordEncoded = passwordEncoder.encodePassword(newPassword, null);
        user.setPassword(newPasswordEncoded);
        userRepository.save(user);
    }

    @Override
    public void deleteAccount(User user) {
        ParameterHelper.assertNull(user);
        if (!StringHelper.isEmpty(user.getDeleteAccountCode())) {
            LOGGER.warn("[Trying to delete account again][UserId: {}]", user.getId());
        } else {
            String deleteCode = RandomHelper.getDeleteAccountVerificationCode();
            user.setDeleteAccountCode(deleteCode);
            userRepository.save(user);
        }
        if (user.getEmail() != null && UserState.isEnabled(user.getState())) {
            LOGGER.info("[deleteAccount][{}] Starting email send", user.getId());
            mailHelper.sendDeleteAccountMail(user);
        } else {
            LOGGER.warn("[Trying to delete an account with no email or invalid status][UserId: {}]", user.getId());
        }
    }

    @Override
    public void finishDeleteAccount(String email, String code) {
        ParameterHelper.assertAnyEmpty(email, code);
        User user = userRepository.findByEmail(email);
        if (user != null && !StringHelper.isEmpty(user.getDeleteAccountCode())) {
            String encodingDeleteCode = HashHelper.encodeStringMode(user.getDeleteAccountCode());
            if (StringHelper.equals(encodingDeleteCode, code)) {
                user.setState(UserState.EXPIRED);
                userRepository.save(user);
                LOGGER.info("[User with email {} is deleted]", email);
                queueHelper.registerUserDelete(user);
            } else {
                LOGGER.warn("[Trying to delete account with invalid code][Email: {}][Code: {}]", email, code);
                throw new RecIllegalArgumentException("Invalid code");
            }
        } else {
            LOGGER.error("[Trying to delete account from an invalid email][Email: {}]", email);
        }
    }

    @Override
    public void resetPassword(User user) {
        ParameterHelper.assertNull(user);
        if (UserState.isEnabled(user.getState())) {
            LOGGER.info("[Starting reset password by user {} with status {}", user.getId(), user.getState());
            String newPassword = RandomStringUtils.randomAlphanumeric(10);
            user.setPassword(passwordEncoder.encodePassword(newPassword, null));
            userRepository.save(user);
            mailHelper.sendPasswordReset(user, newPassword);
        } else {
            LOGGER.info("[Trying resetPassword user {} with status {}", user.getId(), user.getState());
        }
    }

    @Override
    public User getUserById(String userId) {
        ParameterHelper.assertEmpty(userId);
        return userRepository.findByUserId(userId);
    }

    @Override
    public String getDefaultUserAvatar() {
        return DEFAULT_USER_AVATAR;
    }

    @Override
    public void banUser(User user) {
        ParameterHelper.assertNull(user);
        LOGGER.info("[Ban user {}]", user.getId());
        user.setState(UserState.BANNED);
        userRepository.save(user);
        oAuthHelper.clearTokensByUsername(user.getUsername());
    }

    @Override
    public void reduceRecommendation(String userId) {
        ParameterHelper.assertEmpty(userId);
        LOGGER.debug("[Reducing recommendation point to user {}]", userId);
        User user = userRepository.findByUserId(userId);
        if (user == null) {
            LOGGER.error("[Trying reduce the recommendation point of an null user][UserId: {}]", userId);
            throw new IllegalArgumentException("The user id is invalid: " + userId);
        }
        user.setPendingRecommends(NumberHelper.decrementLong(user.getPendingRecommends(), 1, 0));
        userRepository.save(user);
    }

    @Override
    public void setAutoShared(User user, SocialUserProviderId socialUserProviderId, boolean value) {
        LOGGER.debug("[Trying to change autoShared User: " + user.getId() + "][socialUserProviderId: {}][Value: {}]", socialUserProviderId, value);
        if (!CollectionHelper.isEmpty(user.getSocialUsers())) {
            for (SocialUser socialUser : user.getSocialUsers()) {
                if (socialUserProviderId.is(socialUser.getProviderId())) {
                    socialUser.setAutoShared(value);
                    LOGGER.debug("[AutoShared changed {}]", socialUser.getAutoShared());
                    userRepository.save(user);
                    SecurityHelper.performLogin(user.getId(), userDetailsServiceImpl);
                    break;
                }
            }
        } else {
            LOGGER.warn("[Trying to change autoShared of an user with no contains any socialUser]");
        }
    }

    @Override
    public Long getPendingRecommendationsFromUser(String userId) {
        return recommendationService.getPendingRecommendationsByUserId(userId);
    }

    @Override
    public List<User> getUserByFacebookFriendsIds(List<String> friendsIds) {
        List<User> result;
        if (CollectionHelper.isEmpty(friendsIds)) {
            result = new ArrayList<>();
        } else {
            result = userRepository.findUsersByProviderIdAndProviderUserIds(SocialUserProviderId.FACEBOOK, friendsIds);
        }
        return result;
    }

    /**
     * All modifications of user must be pass through this method.
     * @param oldUser Old user values.
     * @param newUser New user values.
     * @return The user after being stored in the database.
     */
    protected User updateUserData(User oldUser, User newUser) {
        if (oldUser == null || !StringHelper.equals(oldUser.getEmail(), newUser.getEmail())) {
            emailChangedTrigger(newUser);
        }
        return userRepository.save(newUser);
    }

    /**
     * Trigger dispatcher when email of user is changed...
     * @param user The user modified
     */
    private void emailChangedTrigger(User user) {
        if (user.getEmail() != null && (user.getState() == null || UserState.isEnabled(user.getState()))) {
            LOGGER.info("[updateUserData][{}] Email trigger dispatcher", user.getId());
            String emailVerificationCode = RandomHelper.getMailVerificationCode();
            user.setEmailVerificationCode(emailVerificationCode);
            user.setState(UserState.PENDING_MAIL);
            mailHelper.sendConfirmationMail(user);
        }
    }
}
