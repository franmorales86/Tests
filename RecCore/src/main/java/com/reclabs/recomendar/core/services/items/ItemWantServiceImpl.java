/**
 * Project: RecCore
 * Created by: raulanatol at 02/05/2013 17:16:38
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.services.items;

import com.reclabs.recomendar.common.helpers.types.DateHelper;
import com.reclabs.recomendar.common.helpers.types.NullHelper;
import com.reclabs.recomendar.common.types.DatePrecisionType;
import com.reclabs.recomendar.model.documents.items.ItemWant;
import com.reclabs.recomendar.model.repositories.items.ItemWantRepository;
import com.reclabs.recomendar.model.services.items.ItemWantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author fjmorales
 */
@Service
public class ItemWantServiceImpl implements ItemWantService {

    @Autowired
    private ItemWantRepository itemWantRepository;

    @Override
    public void userWantItem(String userId, String itemId) {
        ItemWant itemWantBBDD = itemWantRepository.findByUserIdAndItemId(userId, itemId);
        if (NullHelper.isAnyNull(itemWantBBDD)) {
            ItemWant itemWant = new ItemWant();
            itemWant.setItemId(itemId);
            itemWant.setUserId(userId);
            itemWant.setCreatedDate(DateHelper.getCurrentDate(DatePrecisionType.MILLISECOND));
            itemWantRepository.save(itemWant);
        }
    }

    @Override
    public void removeItemWantByUserIdAndItemId(String userId, String itemId) {
        itemWantRepository.deleteByUserIdAndItemId(userId, itemId);
    }

    @Override
    public void deleteItemByItemId(String itemId) {
        itemWantRepository.deleteByItemId(itemId);
    }
}
