/**
 * Project: RecModel
 * Created by: raulanatol at 12/06/13 11:07
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.model.repositories.items;

import com.reclabs.recomendar.model.documents.items.ItemToVerify;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author raulanatol
 */
public interface ItemToVerifyRepository extends MongoRepository<ItemToVerify, String> {
    /**
     * Delete all elements with itemId equals to parameter.
     * @param itemId Id of the Item.
     */
    void deleteByItemId(String itemId);
}
