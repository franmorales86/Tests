/**
 * Project: RecModel
 * Created by: raulanatol at 02/05/13 16:25
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.model.services.items;

/**
 * @author fjmorales
 */
public interface ItemHaveService {

    /**
     * Add item that have a user
     * @param userId Id of user
     * @param itemId Id of item
     */
    void userHaveItem(String userId, String itemId);

    /**
     * Remove item that have a user by userId and itemId
     * @param userId Id of user
     * @param itemId Id of item
     */
    void removeItemHaveByUserIdAndItemId(String userId, String itemId);

    /**
     * Delete all elements with itemId = itemId
     * @param itemId Id of the item
     */
    void deleteItemByItemId(String itemId);
}
