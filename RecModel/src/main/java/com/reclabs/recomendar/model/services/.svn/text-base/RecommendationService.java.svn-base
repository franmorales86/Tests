/**
 * Project: RecModel
 * Created by: raulanatol at 02/05/13 16:25
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.model.services;

import com.reclabs.recomendar.model.documents.Recommendation;
import com.reclabs.recomendar.model.documents.items.Item;
import com.reclabs.recomendar.model.documents.users.User;

import java.util.Date;
import java.util.List;

/**
 * @author raulanatol
 */
public interface RecommendationService {

    /**
     * Dadas las ids de item y de usuario realizamos la recomendación de dicho item por el usuario.
     * @param userId La id del usuario.
     * @param itemId La id del item.
     */
    void recommendItem(String userId, String itemId);

    /**
     * Dada la id de un usuario obtenemos todos los items recomendados por el mismo.
     * @param userId El id del usuario
     * @return El listado de items recomendados por el usuario
     */
    List<Item> findItemsByRecommendationUser(String userId);

    /**
     * The item with id = itemId is recommended by the user with id = userId
     * @param userId Id of the user
     * @param itemId Id of the item
     * @return true in affirmative case, false otherwise
     */
    boolean isRecommendedByUserId(String userId, String itemId);

    /**
     * Remove recommendation by userId and itemId
     * @param userId Id of user
     * @param itemId Id of item
     */
    void removeRecommendationByUserIdAndItemId(String userId, String itemId);

    /**
     * Get a lists of items recommended by a user
     * @param userId Id of user
     * @return List of items id.
     */
    List<String> findItemsIdByRecommendationUser(String userId);

    /**
     * Delete all recommendations of the item with id = itemId
     * @param itemId The id of the item
     */
    void deleteItemByItemId(String itemId);

    /**
     * Gets the number of pending recommendations of an specified user
     * @param userId the userId
     * @return The number of pending recommendations.
     */
    Long getPendingRecommendationsByUserId(String userId);

    /**
     * Gets the number of pending recommendations of an specified user
     * @param user the user
     * @return The number of pending recommendations.
     */
    Long getPendingRecommendationsByUser(User user);

    /**
     * Get the list of recommendations of an user into from a date
     * @param userId The id of the user
     * @param from The start of the period
     * @return The list of the recommendations
     */
    List<Recommendation> findRecommendationsByUserFromDate(String userId, Date from);

    /**
     * Indicate if the user can be recommend an item.
     * - Number of pending recommendations
     * - Admin roles of user
     * @param userId The user id
     * @return true if the user can be recommend an item
     */
    boolean userCanBeRecommendItem(String userId);

    /**
     * Indicate if the user can be recommend an item.
     * - Number of pending recommendations
     * - Admin roles of user
     * @param user the user
     * @return true if the user can be recommend an item
     */
    boolean userCanBeRecommendItem(User user);
}
