/**
 * Project: RecModel
 * Created by: raulanatol at 12/06/13 11:10
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.model.services.items;

import com.reclabs.recomendar.model.documents.items.ItemToVerify;

/**
 * @author raulanatol
 */
public interface ItemToVerifyService {
    /**
     * Create a new item to verify
     * @param currentWeb Current web of the item.
     * @param itemId Id of the item created.
     * @param userId Id of the user owner of new item.
     * @return The item created.
     */
    ItemToVerify createItem(String currentWeb, String itemId, String userId);

    /**
     * Delete all elements with itemId = itemId
     * @param itemId Id of the item
     */
    void deleteItemByItemId(String itemId);
}
