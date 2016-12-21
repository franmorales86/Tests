/**
 * Project: RecModel
 * Created by: raulanatol at 18/11/13 11:23
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.model.services.social;

import com.reclabs.recomendar.model.documents.items.Item;
import com.reclabs.recomendar.model.documents.users.User;
import com.reclabs.recomendar.model.types.SocialUserProviderId;

/**
 * @author raulanatol
 */
public interface ShareService {
    /**
     * Gets the short url from an item to share on Facebook with anonymous user
     * @param itemId The item to share
     * @return The shortURL
     */
    public String getShortURLFromItemToShareOnFacebookAnonymousUser(String itemId);

    /**
     * Gets the short url from an item to share on Facebook with userId
     * @param itemId The item to share
     * @param userId The user to share
     * @return The shortURL
     */
    public String getShortURLFromItemToShareOnFacebook(String itemId, String userId);

    /**
     * Gets the shortURL from an item to share on Google+ with anonymous user
     * @param itemId The item to share
     * @return The shortURL
     */
    public String getShortURLFromItemToShareOnGooglePlusAnonymousUser(String itemId);

    /**
     * Gets the shortURL from an item to share on Google+ with userId
     * @param itemId The item to share
     * @param userId The user to share
     * @return The shortURL
     */
    public String getShortURLFromItemToShareOnGooglePlus(String itemId, String userId);

    /**
     * Gets the shortURL from an item to share on clipboard with anonymous user
     * @param itemId The item to share
     * @return The shortURL
     */
    public String getShortURLFromItemToShareOnClipboardAnonymousUser(String itemId);

    /**
     * Gets the shortURL from an item to share on clipboard with userID
     * @param itemId The item to share
     * @param userId The user to share
     * @return The shortURL
     */
    public String getShortURLFromItemToShareOnClipboard(String itemId, String userId);

    /**
     * Gets the short url from an item to share on Twitter with userId
     * @param itemId The item to share
     * @param userId The user to share
     * @return The shortURL
     */
    public String getShortURLFromItemToShareOnTwitter(String itemId, String userId);

    /**
     * Gets the short url from an item to share on Twitter with anonymous user
     * @param itemId The item to share
     * @return The shortURL
     */
    public String getShortURLFromItemToShareOnTwitterAnonymousUser(String itemId);

    /**
     * Share a recommendation on an social network
     * @param user The user to share
     * @param item The item to share
     * @param providerId The social network
     */
    public void shareItemRecommendationOnSocialNetwork(User user, Item item, SocialUserProviderId providerId);
}
