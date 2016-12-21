/**
 * Project: RecCore
 * Created by: raulanatol at 13/11/13 09:53
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.services.social;

import com.reclabs.recomendar.common.exceptions.RecIllegalArgumentException;
import com.reclabs.recomendar.core.helpers.social.SocialPermission;
import com.reclabs.recomendar.core.helpers.social.SocialPermissionsHelper;
import com.reclabs.recomendar.model.documents.items.Item;
import com.reclabs.recomendar.model.documents.users.User;
import com.reclabs.recomendar.model.services.items.ItemService;
import com.reclabs.recomendar.model.services.items.ItemShortURLService;
import com.reclabs.recomendar.model.services.social.ShareService;
import com.reclabs.recomendar.model.types.SocialUserProviderId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.NotAuthorizedException;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookLink;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Service;

/**
 * @author raulanatol
 */
@Service
public class ShareServiceImpl implements ShareService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShareServiceImpl.class);

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemShortURLService itemShortURLService;

    @Autowired
    private Facebook facebook;

    @Autowired
    private Twitter twitter;

    private void shareTwitterLink(String messageToShare, String userId) {
        try {
            twitter.timelineOperations().updateStatus(messageToShare);
            LOGGER.debug("[The user: {} was posted a tweet]", userId);
        } catch (NotAuthorizedException e) {
            LOGGER.warn("[The user: {} is trying to postLink and no have permissions on this social network: Twitter]", userId);
        }
    }

    private void shareFacebookLink(String shortURL, String messageToShare, String userId) {
        try {
            if (SocialPermissionsHelper.hasFacebookPermission(facebook, SocialPermission.FACEBOOK_PUBLISH_STREAM)) {
                FacebookLink facebookLink = new FacebookLink(shortURL, "Recomendar.com", "He recomendado", "He recomendado esto en Recomendar");
                facebook.feedOperations().postLink(messageToShare, facebookLink);
                LOGGER.debug("[The user: {} was shared a facebook link]", userId);
            } else {
                LOGGER.info("[The user: {} don't have publish permission]", userId);
            }
        } catch (Exception e) {
            LOGGER.warn("[The user: {} is trying to postLink and no have permissions on this social network: Facebook]", userId);
        }
    }

    private String getShortURLFromItemToShareOnSocialNetwork(String itemId, String userId, String network) {
        Item item = itemService.getById(itemId);
        if (item == null) {
            LOGGER.warn("[Trying to get a shortURL from an item with illegal itemId][Item id: {}]", itemId);
            throw new RecIllegalArgumentException("Item id is invalid");
        }
        return itemShortURLService.getShortURL(userId, itemId, item.getName(), network);
    }

    @Override
    public String getShortURLFromItemToShareOnFacebookAnonymousUser(String itemId) {
        return getShortURLFromItemToShareOnFacebook(itemId, "0");
    }

    @Override
    public String getShortURLFromItemToShareOnFacebook(String itemId, String userId) {
        return getShortURLFromItemToShareOnSocialNetwork(itemId, userId, "Facebook");
    }

    @Override
    public String getShortURLFromItemToShareOnGooglePlusAnonymousUser(String itemId) {
        return getShortURLFromItemToShareOnGooglePlus(itemId, "0");
    }

    @Override
    public String getShortURLFromItemToShareOnGooglePlus(String itemId, String userId) {
        return getShortURLFromItemToShareOnSocialNetwork(itemId, userId, "Google+");
    }

    @Override
    public String getShortURLFromItemToShareOnClipboardAnonymousUser(String itemId) {
        return getShortURLFromItemToShareOnClipboard(itemId, "0");
    }

    @Override
    public String getShortURLFromItemToShareOnClipboard(String itemId, String userId) {
        return getShortURLFromItemToShareOnSocialNetwork(itemId, userId, "Clipboard");
    }

    @Override
    public String getShortURLFromItemToShareOnTwitter(String itemId, String userId) {
        return getShortURLFromItemToShareOnSocialNetwork(itemId, userId, "Twitter");
    }


    @Override
    public String getShortURLFromItemToShareOnTwitterAnonymousUser(String itemId) {
        return getShortURLFromItemToShareOnTwitter(itemId, "0");
    }

    @Override
    public void shareItemRecommendationOnSocialNetwork(User user, Item item, SocialUserProviderId providerId) {
        String shortURL = getShortURLFromItemToShareOnSocialNetwork(item.getId(), user.getId(), providerId.getProviderId());
        String messageToShare = "He recomendado \"" + item.getName() + "\" en Recomendar. Míralo aquí: " + shortURL;
        switch (providerId) {
            case FACEBOOK:
                shareFacebookLink(shortURL, messageToShare, user.getId());
                break;
            case TWITTER:
                shareTwitterLink(messageToShare, user.getId());
                break;
            default:
                LOGGER.error("[Trying to share an unexpected providerId: {}]", providerId);
        }
    }
}
