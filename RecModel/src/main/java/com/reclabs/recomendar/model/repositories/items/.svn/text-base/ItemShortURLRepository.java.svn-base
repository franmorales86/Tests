/**
 * Project: RecModel
 * Created by: raulanatol at 29/09/13 18:41
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.model.repositories.items;

import com.reclabs.recomendar.model.documents.items.ItemShortURL;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author raulanatol
 */
public interface ItemShortURLRepository extends MongoRepository<ItemShortURL, String> {
    /**
     * @param userId The id of the user
     * @param itemID The item of the item
     * @return The short url or null.
     */
    ItemShortURL findByUserAndItemId(String userId, String itemID);
}
