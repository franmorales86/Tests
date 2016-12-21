/**
 * Project: RecCore
 * Created by: Diego Nieto at 12/11/2013 09:26:22
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.services;

import com.reclabs.recomendar.common.exceptions.RecIllegalArgumentException;
import com.reclabs.recomendar.common.helpers.types.DateHelper;
import com.reclabs.recomendar.common.types.DatePrecisionType;
import com.reclabs.recomendar.core.misc.security.SecurityHelper;
import com.reclabs.recomendar.model.documents.Recommendation;
import com.reclabs.recomendar.model.documents.items.Item;
import com.reclabs.recomendar.model.documents.users.SocialUser;
import com.reclabs.recomendar.model.documents.users.User;
import com.reclabs.recomendar.model.exceptions.user.UserNoHaveMoreRecommendationActionException;
import com.reclabs.recomendar.model.repositories.RecommendationRepository;
import com.reclabs.recomendar.model.repositories.items.ItemRepository;
import com.reclabs.recomendar.model.services.UserService;
import com.reclabs.recomendar.model.services.social.ShareService;
import com.reclabs.recomendar.model.types.SecurityRole;
import com.reclabs.recomendar.model.types.SocialUserProviderId;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Diego Nieto
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({DateHelper.class, SecurityHelper.class})
@PowerMockIgnore({"javax.management.*", "javax.xml.parsers.*", "com.sun.org.apache.xerces.internal.jaxp.*", "ch.qos.logback.*", "org.slf4j.*"})
public class RecommendationServiceImplTest {

    @Mock
    private RecommendationRepository recommendationRepository;

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private UserService userService;

    @Mock
    private User user;

    @Mock
    private ShareService shareService;

    @InjectMocks
    private RecommendationServiceImpl recommendationServiceImpl = new RecommendationServiceImpl();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void recommendItemWithEmptyUserId() throws Exception {
        String itemId = "84db26ac";
        recommendationServiceImpl.recommendItem("", itemId);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void recommendItemWithNullUserId() throws Exception {
        String itemId = "84db26ac";
        recommendationServiceImpl.recommendItem(null, itemId);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void recommendItemWithEmptyItemId() throws Exception {
        String userId = "bc32ad14";
        recommendationServiceImpl.recommendItem(userId, "");
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void recommendItemWithNullItemId() throws Exception {
        String userId = "bc32ad14";
        recommendationServiceImpl.recommendItem(userId, null);
    }

    @Test
    public void recommendItemWithOldRecommendation() throws Exception {
        String userId = "ad14bc32";
        String itemId = "26ac84db";
        Recommendation oldRecommendation = new Recommendation();
        Mockito.when(recommendationRepository.findByUserIdAndItemId(userId, itemId)).thenReturn(oldRecommendation);
        recommendationServiceImpl.recommendItem(userId, itemId);
    }

    @Test
    public void recommendItemWithCheckingReduceRecommendations() throws Exception {
        String userId = "ad14bc32";
        String itemId = "26ac84db";
        Date currentDate = DateHelper.getFixedDate(2010, 10, 10);

        Item item = new Item();
        item.setId(itemId);
        item.setRecommendations(10L);

        Recommendation newRecommendation = new Recommendation();
        newRecommendation.setItemId(itemId);
        newRecommendation.setUserId(userId);
        newRecommendation.setCreatedDate(currentDate);

        User user = new User();
        user.setId(userId);
        user.setPendingRecommends(3L);

        PowerMockito.mockStatic(DateHelper.class);

        PowerMockito.mockStatic(SecurityHelper.class);

        Mockito.when(SecurityHelper.getLoggedInUser()).thenReturn(user);

        Mockito.when(DateHelper.getCurrentDate(DatePrecisionType.MILLISECOND)).thenReturn(currentDate);
        Mockito.when(DateHelper.getCurrentDatePrecision(DatePrecisionType.DAY)).thenReturn(currentDate);
        Mockito.when(DateHelper.subtract(currentDate, 1, DatePrecisionType.DAY)).thenReturn(currentDate);

        Mockito.when(recommendationRepository.findByUserIdAndItemId(userId, itemId)).thenReturn(null);

        Mockito.when(userService.getUserById(userId)).thenReturn(user);

        Mockito.when(itemRepository.findOne(itemId)).thenReturn(item);

        Mockito.when(recommendationRepository.save(newRecommendation)).thenReturn(null);

        Mockito.when(itemRepository.save(item)).thenReturn(null);

        recommendationServiceImpl.recommendItem(userId, itemId);

        Mockito.verify(recommendationRepository, Mockito.times(1)).save(newRecommendation);

        Mockito.verify(itemRepository, Mockito.times(1)).save(item);

        Mockito.verify(userService, Mockito.times(1)).reduceRecommendation(userId);
    }

    @Test
    public void recommendItemWithSocialUsers() throws Exception {
        String userId = "ad14bc32";
        String itemId = "26ac84db";
        Date currentDate = DateHelper.getFixedDate(2010, 10, 10);

        Item item = new Item();
        item.setId(itemId);
        item.setRecommendations(10L);

        Recommendation newRecommendation = new Recommendation();
        newRecommendation.setItemId(itemId);
        newRecommendation.setUserId(userId);
        newRecommendation.setCreatedDate(currentDate);

        SocialUser socialUser = new SocialUser();
        socialUser.setAutoShared(true);
        socialUser.setProviderId(SocialUserProviderId.FACEBOOK.getProviderId());

        List<SocialUser> socialUsersList = new ArrayList<>();
        socialUsersList.add(socialUser);
        socialUsersList.add(socialUser);
        socialUsersList.add(socialUser);

        User user = new User();
        user.setId(userId);
        user.setPendingRecommends(3L);
        user.setSocialUsers(socialUsersList);

        PowerMockito.mockStatic(DateHelper.class);

        PowerMockito.mockStatic(SecurityHelper.class);

        Mockito.when(SecurityHelper.getLoggedInUser()).thenReturn(user);

        Mockito.when(DateHelper.getCurrentDate(DatePrecisionType.MILLISECOND)).thenReturn(currentDate);
        Mockito.when(DateHelper.getCurrentDatePrecision(DatePrecisionType.DAY)).thenReturn(currentDate);
        Mockito.when(DateHelper.subtract(currentDate, 1, DatePrecisionType.DAY)).thenReturn(currentDate);

        Mockito.when(recommendationRepository.findByUserIdAndItemId(userId, itemId)).thenReturn(null);

        Mockito.when(userService.getUserById(userId)).thenReturn(user);

        Mockito.when(itemRepository.findOne(itemId)).thenReturn(item);

        Mockito.when(recommendationRepository.save(newRecommendation)).thenReturn(null);

        Mockito.when(itemRepository.save(item)).thenReturn(new Item());

        recommendationServiceImpl.recommendItem(userId, itemId);
    }

    @Test(expected = UserNoHaveMoreRecommendationActionException.class)
    public void recommendItemWhenUserNotHaveRecommendations() throws Exception {
        String userId = "14adbc32";
        String itemId = "ac2684db";

        List<Recommendation> recommendations = new ArrayList<>();
        recommendations.add(new Recommendation());
        recommendations.add(new Recommendation());
        recommendations.add(new Recommendation());
        recommendations.add(new Recommendation());

        User user = new User();
        user.setId(userId);
        user.setPendingRecommends(0L);
        user.setRoles(new ArrayList<SecurityRole>());

        Mockito.when(recommendationRepository.findByUserIdAndItemId(userId, itemId)).thenReturn(null);
        Mockito.when(userService.getUserById(userId)).thenReturn(user);
        Mockito.when(recommendationRepository.findRecommendationsByUserFromDate(Mockito.eq(userId), Mockito.any(Date.class))).thenReturn(recommendations);

        recommendationServiceImpl.recommendItem(userId, itemId);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void userCanBeRecommendItemWithEmptyUserId() throws Exception {
        recommendationServiceImpl.userCanBeRecommendItem("");
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void userCanBeRecommendItemWithNullUserId() throws Exception {
        User user = null;
        recommendationServiceImpl.userCanBeRecommendItem(user);
    }

    @Test
    public void userCanBeRecommendItemWithoutPendingRecommendationsAndSandboxAdminRole() throws Exception {
        List<SecurityRole> roles = new ArrayList<>();
        roles.add(SecurityRole.ROLE_USER);
        roles.add(SecurityRole.ROLE_SANDBOX_USER);
        User user = new User();
        user.setPendingRecommends(0L);
        user.setRoles(roles);
        assertTrue(recommendationServiceImpl.userCanBeRecommendItem(user));
    }

    @Test
    public void userCanBeRecommendItemWithPendingRecommendationsAndSandboxAdminRole() throws Exception {
        List<SecurityRole> roles = new ArrayList<>();
        roles.add(SecurityRole.ROLE_USER);
        roles.add(SecurityRole.ROLE_SANDBOX_USER);
        User user = new User();
        user.setPendingRecommends(3L);
        user.setRoles(roles);
        assertTrue(recommendationServiceImpl.userCanBeRecommendItem(user));
    }

    @Test
    public void userCanBeRecommendItemWithoutPendingRecommendationsAndNoSandboxAdminRole() throws Exception {
        List<SecurityRole> roles = new ArrayList<>();
        roles.add(SecurityRole.ROLE_USER);
        User user = new User();
        user.setPendingRecommends(0L);
        user.setRoles(roles);
        assertFalse(recommendationServiceImpl.userCanBeRecommendItem(user));
    }

    @Test
    public void userCanBeRecommendItemWithoutRecommendations() throws Exception {
        String userId = "14adbc32";
        Date currentDate = DateHelper.getFixedDate(2010, 10, 10);
        List<Recommendation> recommendations = new ArrayList<>();
        recommendations.add(new Recommendation());
        recommendations.add(new Recommendation());
        recommendations.add(new Recommendation());
        recommendations.add(new Recommendation());

        User user = new User();
        user.setId(userId);
        user.setPendingRecommends(0L);
        List<SecurityRole> roles = new ArrayList<>();
        user.setRoles(roles);

        PowerMockito.mockStatic(DateHelper.class);

        Mockito.when(DateHelper.getCurrentDate(DatePrecisionType.MILLISECOND)).thenReturn(currentDate);
        Mockito.when(DateHelper.getCurrentDatePrecision(DatePrecisionType.DAY)).thenReturn(currentDate);
        Mockito.when(DateHelper.subtract(currentDate, 1, DatePrecisionType.DAY)).thenReturn(currentDate);

        Mockito.when(recommendationRepository.findRecommendationsByUserFromDate(userId, currentDate)).thenReturn(recommendations);
        Mockito.when(userService.getUserById(userId)).thenReturn(user);

        assertFalse(recommendationServiceImpl.userCanBeRecommendItem(userId));
    }

    @Test
    public void userCanBeRecommendItemWithSomeRecommendations() throws Exception {
        String userId = "14adbc32";

        Random random = new Random();

        User user = new User();
        user.setId(userId);
        user.setPendingRecommends(Math.abs(random.nextLong()) + 1L);

        Mockito.when(userService.getUserById(userId)).thenReturn(user);

        assertTrue(recommendationServiceImpl.userCanBeRecommendItem(userId));
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void findItemsByRecommendationUserWithNullUserId() throws Exception {
        recommendationServiceImpl.findItemsByRecommendationUser(null);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void findItemsByRecommendationUserWithEmptyUserId() throws Exception {
        recommendationServiceImpl.findItemsByRecommendationUser("");
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void isRecommendedByUserIdWithNullUserId() throws Exception {
        String itemId = "ac2gh4db";
        recommendationServiceImpl.isRecommendedByUserId(null, itemId);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void isRecommendedByUserIdWithEmptyUserId() throws Exception {
        String itemId = "ac2gh4db";
        recommendationServiceImpl.isRecommendedByUserId("", itemId);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void isRecommendedByUserIdWithNullItemId() throws Exception {
        String userId = "14athc32";
        recommendationServiceImpl.isRecommendedByUserId(userId, null);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void isRecommendedByUserIdWithEmptyItemId() throws Exception {
        String userId = "14ahtc32";
        recommendationServiceImpl.isRecommendedByUserId(userId, "");
    }

    @Test
    public void isRecommendedByUserIdWhenResultIsFalse() throws Exception {
        String userId = "14adbc32";
        String itemId = "2684acdb";

        Mockito.when(recommendationRepository.findByUserIdAndItemId(userId, itemId)).thenReturn(null);

        assertFalse(recommendationServiceImpl.isRecommendedByUserId(userId, itemId));
    }

    @Test
    public void isRecommendedByUserIdWhenResultIsTrue() throws Exception {
        String userId = "14adbc32";
        String itemId = "2684acdb";

        Recommendation recommendationFound = new Recommendation();

        Mockito.when(recommendationRepository.findByUserIdAndItemId(userId, itemId)).thenReturn(recommendationFound);

        assertTrue(recommendationServiceImpl.isRecommendedByUserId(userId, itemId));
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void removeRecommendationByUserIdAndItemIdWithNullUserId() throws Exception {
        String itemId = "284acd6b";
        recommendationServiceImpl.removeRecommendationByUserIdAndItemId(null, itemId);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void removeRecommendationByUserIdAndItemIdWithEmptyUserId() throws Exception {
        String itemId = "26a84cdb";
        recommendationServiceImpl.removeRecommendationByUserIdAndItemId("", itemId);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void removeRecommendationByUserIdAndItemIdWithNullItemId() throws Exception {
        String userId = "1ad4bc32";
        recommendationServiceImpl.removeRecommendationByUserIdAndItemId(userId, null);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void removeRecommendationByUserIdAndItemIdWithEmptyItemId() throws Exception {
        String userId = "1c4adb32";
        recommendationServiceImpl.removeRecommendationByUserIdAndItemId(userId, null);
    }

    @Test
    public void removeRecommendationByUserIdAndItemIdWithNullRecommendation() throws Exception {
        String userId = "ae15bc32ae15bc32";
        String itemId = "16ac84da16ac";

        Mockito.when(recommendationRepository.findByUserIdAndItemId(userId, itemId)).thenReturn(null);
        recommendationServiceImpl.removeRecommendationByUserIdAndItemId(userId, itemId);
    }

    @Test
    public void removeRecommendationByUserIdAndItemIdCheckingDecrementRecommendation() throws Exception {
        String userId = "ae15bc32ae15bc32";
        String itemId = "16ac84da16ac";

        Recommendation recommendationToDelete = new Recommendation();

        Item item = new Item();

        Mockito.when(recommendationRepository.findByUserIdAndItemId(userId, itemId)).thenReturn(recommendationToDelete);

        Mockito.when(itemRepository.findOne(itemId)).thenReturn(item);

        recommendationServiceImpl.removeRecommendationByUserIdAndItemId(userId, itemId);

        Mockito.verify(itemRepository, Mockito.times(1)).save(item);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void findItemsIdByRecommendationUserWithNullUserId() throws Exception {
        recommendationServiceImpl.findItemsIdByRecommendationUser(null);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void findItemsIdByRecommendationUserWithEmptyUserId() throws Exception {
        recommendationServiceImpl.findItemsIdByRecommendationUser("");
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void deleteItemByItemIdWithNullItemId() throws Exception {
        recommendationServiceImpl.deleteItemByItemId(null);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void deleteItemByItemIdWithEmptyItemId() throws Exception {
        recommendationServiceImpl.deleteItemByItemId("");
    }
}
