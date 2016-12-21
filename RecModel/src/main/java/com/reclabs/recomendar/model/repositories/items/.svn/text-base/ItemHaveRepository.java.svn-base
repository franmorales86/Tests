/**
 * Project: RecModel
 * Created by: raulanatol at 02/05/13 16:27
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.model.repositories.items;

import com.reclabs.recomendar.model.documents.items.ItemHave;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author raulanatol
 */
public interface ItemHaveRepository extends MongoRepository<ItemHave, String> {

    /**
     * Remove itemHave by userId and itemId
     * @param userId Id of user
     * @param itemId Id of item
     */
    void deleteByUserIdAndItemId(String userId, String itemId);

    /**
     * Find itemHave by userId and itemId
     * @param userId Id of user
     * @param itemId Id of item
     * @return The itemHave. NULL if not exist.
     */
    ItemHave findByUserIdAndItemId(String userId, String itemId);

    /**
     * Delete all elements with the itemId are equals to parameter.
     * @param itemId The id of the item.
     */
    void deleteByItemId(String itemId);
}
