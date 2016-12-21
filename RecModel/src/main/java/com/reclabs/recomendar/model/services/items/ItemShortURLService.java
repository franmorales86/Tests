/**
 * Project: RecModel
 * Created by: raulanatol at 29/09/13 18:41
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.model.services.items;

/**
 * @author raulanatol
 */
public interface ItemShortURLService {

    /**
     * Get a shortURL of an item.
     * @param userId The id of the user
     * @param itemId The id of the item
     * @param itemName The name of the item
     * @param utmSource The utm source value
     * @return the short URL (new creation or old)
     */
    String getShortURL(String userId, String itemId, String itemName, String utmSource);
}
