/**
 * Project: RecCore
 * Created by: fjmorales at 24/05/2013 17:16:38
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.services.items;

import com.reclabs.recomendar.common.helpers.types.DateHelper;
import com.reclabs.recomendar.common.types.DatePrecisionType;
import com.reclabs.recomendar.model.documents.items.ItemHave;
import com.reclabs.recomendar.model.repositories.items.ItemHaveRepository;
import com.reclabs.recomendar.model.services.items.ItemHaveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author fjmorales
 */
@Service
public class ItemHaveServiceImpl implements ItemHaveService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ItemHaveServiceImpl.class);

    @Autowired
    private ItemHaveRepository itemHaveRepository;

    @Override
    public void userHaveItem(String userId, String itemId) {
        ItemHave itemHaveBBDD = itemHaveRepository.findByUserIdAndItemId(userId, itemId);
        if (itemHaveBBDD == null) {
            ItemHave itemHave = new ItemHave();
            itemHave.setItemId(itemId);
            itemHave.setUserId(userId);
            itemHave.setCreatedDate(DateHelper.getCurrentDate(DatePrecisionType.MILLISECOND));
            itemHaveRepository.save(itemHave);
        } else {
            LOGGER.warn("[Trying to have item again][User: {}][Item: {}]", userId, itemId);
        }
    }

    @Override
    public void removeItemHaveByUserIdAndItemId(String userId, String itemId) {
        itemHaveRepository.deleteByUserIdAndItemId(userId, itemId);
    }

    @Override
    public void deleteItemByItemId(String itemId) {
        itemHaveRepository.deleteByItemId(itemId);
    }
}
