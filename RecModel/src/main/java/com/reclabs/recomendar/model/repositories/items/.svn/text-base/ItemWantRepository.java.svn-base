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

import com.reclabs.recomendar.model.documents.items.ItemWant;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author raulanatol
 */
public interface ItemWantRepository extends MongoRepository<ItemWant, String> {

    /**
     * Remove itemWant by userId and itemId
     * @param userId Id of user
     * @param itemId Id of item
     */
    void deleteByUserIdAndItemId(String userId, String itemId);

    /**
     * Find itemWant by userId and itemId
     * @param userId Id of user
     * @param itemId Id of item
     * @return The itemWant. NULL if not exist.
     */
    ItemWant findByUserIdAndItemId(String userId, String itemId);

    /**
     * Delete all elements with itemId equals to parameter.
     * @param itemId Id of the Item.
     */
    void deleteByItemId(String itemId);
}
