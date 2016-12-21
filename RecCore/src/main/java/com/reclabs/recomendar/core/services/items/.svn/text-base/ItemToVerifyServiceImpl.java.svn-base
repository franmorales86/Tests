/**
 * Project: RecCore
 * Created by: raulanatol at 12/06/13 11:50
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.services.items;

import com.reclabs.recomendar.model.documents.items.ItemToVerify;
import com.reclabs.recomendar.model.repositories.items.ItemToVerifyRepository;
import com.reclabs.recomendar.model.services.items.ItemToVerifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author raulanatol
 */
@Service
public class ItemToVerifyServiceImpl implements ItemToVerifyService {

    @Autowired
    private ItemToVerifyRepository itemToVerifyRepository;

    @Override
    public ItemToVerify createItem(String currentWeb, String itemId, String userId) {
        ItemToVerify itemToVerify = new ItemToVerify();
        itemToVerify.setCurrentWeb(currentWeb);
        itemToVerify.setItemId(itemId);
        itemToVerify.setUserId(userId);
        return itemToVerifyRepository.save(itemToVerify);
    }

    @Override
    public void deleteItemByItemId(String itemId) {
        itemToVerifyRepository.deleteByItemId(itemId);
    }
}
