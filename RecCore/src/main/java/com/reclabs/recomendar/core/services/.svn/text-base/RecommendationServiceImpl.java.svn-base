/**
 * Project: RecCore
 * Created by: raulanatol at 02/05/2013 17:16:38
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.services;

import com.reclabs.recomendar.common.exceptions.RecIllegalArgumentException;
import com.reclabs.recomendar.common.helpers.ParameterHelper;
import com.reclabs.recomendar.common.helpers.types.CollectionHelper;
import com.reclabs.recomendar.common.helpers.types.DateHelper;
import com.reclabs.recomendar.common.helpers.types.NumberHelper;
import com.reclabs.recomendar.common.helpers.types.StringHelper;
import com.reclabs.recomendar.common.types.DatePrecisionType;
import com.reclabs.recomendar.core.misc.security.SecurityHelper;
import com.reclabs.recomendar.model.documents.Recommendation;
import com.reclabs.recomendar.model.documents.items.Item;
import com.reclabs.recomendar.model.documents.users.SocialUser;
import com.reclabs.recomendar.model.documents.users.User;
import com.reclabs.recomendar.model.exceptions.user.UserNoHaveMoreRecommendationActionException;
import com.reclabs.recomendar.model.repositories.RecommendationRepository;
import com.reclabs.recomendar.model.repositories.items.ItemRepository;
import com.reclabs.recomendar.model.services.RecommendationService;
import com.reclabs.recomendar.model.services.UserService;
import com.reclabs.recomendar.model.services.social.ShareService;
import com.reclabs.recomendar.model.types.SocialUserProviderId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author raulanatol
 */
@Service
public class RecommendationServiceImpl implements RecommendationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RecommendationServiceImpl.class);

    @Autowired
    private RecommendationRepository recommendationRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ShareService shareService;

    /*
     * (non-Javadoc)
     * @see com.reclabs.recomendar.model.services.RecommendationService#recommendItem(java.lang.String,
     * java.lang.String)
     */
    @Override
    public void recommendItem(String userId, String itemId) {
        LOGGER.debug("[The user: {} is trying to recommend an item: {}]", userId, itemId);
        ParameterHelper.assertAnyEmpty(userId, itemId);
        User user = userService.getUserById(userId);
        Recommendation oldRecommendation = recommendationRepository.findByUserIdAndItemId(userId, itemId);
        if (oldRecommendation != null) {
            LOGGER.warn("[Trying to recommend an item again][User: {}][Item: {}]", userId, itemId);
        } else if (userCanBeRecommendItem(userId) || SecurityHelper.isSandboxAdmin(user.getRoles())) {
            Item item = itemRepository.findOne(itemId);
            item.setRecommendations(NumberHelper.incrementLong(item.getRecommendations(), 1));
            Recommendation recommendation = new Recommendation();
            recommendation.setItemId(itemId);
            recommendation.setUserId(userId);
            recommendation.setCreatedDate(DateHelper.getCurrentDate(DatePrecisionType.MILLISECOND));
            recommendationRepository.save(recommendation);
            itemRepository.save(item);
            userService.reduceRecommendation(userId);
            shareItemRecommendationOnSocialNetworks(userId, item);
        } else {
            LOGGER.warn("[Trying recommend an item with no actions][User: {}][Item: {}]", userId, itemId);
            throw new UserNoHaveMoreRecommendationActionException();
        }
    }

    protected void shareItemRecommendationOnSocialNetworks(String userId, Item item) {
        User user = SecurityHelper.getLoggedInUser();
        if (StringHelper.equals(userId, user.getId())) {
            if (!CollectionHelper.isEmpty(user.getSocialUsers())) {
                for (SocialUser socialUser : user.getSocialUsers()) {
                    if (socialUser.getAutoShared()) {
                        SocialUserProviderId providerId = SocialUserProviderId.byProviderId(socialUser.getProviderId());
                        shareService.shareItemRecommendationOnSocialNetwork(user, item, providerId);
                    }
                }
            }
        } else {
            LOGGER.warn("[Trying to share on social network an invalid user][Current: {}][Specified: {}]", user.getId(), userId);
        }
    }


    /*
     * (non-Javadoc)
     * @see
     * com.reclabs.recomendar.model.services.RecommendationService#findItemsByRecommendationUser(java.lang.String)
     */
    @Override
    public List<Item> findItemsByRecommendationUser(String userId) {
        ParameterHelper.assertEmpty(userId);
        List<String> recommendations = recommendationRepository.findItemIdByUserId(userId);
        return itemRepository.findByIdsOrderByCreatedDateDesc(recommendations);
    }

    @Override
    public boolean isRecommendedByUserId(String userId, String itemId) {
        ParameterHelper.assertAnyEmpty(userId, itemId);
        return recommendationRepository.findByUserIdAndItemId(userId, itemId) != null;
    }

    @Override
    public void removeRecommendationByUserIdAndItemId(String userId, String itemId) {
        ParameterHelper.assertAnyEmpty(userId, itemId);
        Recommendation recommendToDelete = recommendationRepository.findByUserIdAndItemId(userId, itemId);
        if (recommendToDelete == null) {
            LOGGER.warn("[Trying to remove an null recommendation][User: {}][Item: {}]", userId, itemId);
        } else {
            recommendationRepository.deleteByUserIdAndItemId(userId, itemId);
            Item item = itemRepository.findOne(itemId);
            if (item == null) {
                LOGGER.warn("[Trying to remove a recommendation of an invalid item][Item: {}][User: {}]");
            } else {
                item.setRecommendations(NumberHelper.decrementLong(item.getRecommendations(), 1, 0));
                itemRepository.save(item);
            }
        }
    }

    @Override
    public List<String> findItemsIdByRecommendationUser(String userId) {
        ParameterHelper.assertEmpty(userId);
        return recommendationRepository.findItemIdByUserId(userId);
    }

    @Override
    public void deleteItemByItemId(String itemId) {
        ParameterHelper.assertEmpty(itemId);
        recommendationRepository.deleteByItemId(itemId);
    }

    @Override
    public Long getPendingRecommendationsByUserId(String userId) {
        User user = userService.getUserById(userId);
        if (user == null) {
            LOGGER.warn("[Trying to getPendingRecommendations with invalid userId: {}]", userId);
            throw new RecIllegalArgumentException("The user id is invalid: " + userId);
        }
        return getPendingRecommendationsByUser(user);
    }

    @Override
    public Long getPendingRecommendationsByUser(User user) {
        ParameterHelper.assertNull(user);
        return (user.getPendingRecommends() == null ? 0 : user.getPendingRecommends());
    }

    @Override
    public List<Recommendation> findRecommendationsByUserFromDate(String userId, Date from) {
        ParameterHelper.assertAnyNull(userId, from);
        return recommendationRepository.findRecommendationsByUserFromDate(userId, from);
    }

    @Override
    public boolean userCanBeRecommendItem(String userId) {
        ParameterHelper.assertEmpty(userId);
        User user = userService.getUserById(userId);
        return userCanBeRecommendItem(user);
    }

    @Override
    public boolean userCanBeRecommendItem(User user) {
        ParameterHelper.assertNull(user);
        return (SecurityHelper.isSandboxAdmin(user.getRoles()) || getPendingRecommendationsByUser(user) > 0);
    }
}
