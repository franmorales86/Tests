/**
 * Project: RecCore
 * Created by: raulanatol at 29/09/13 18:37
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.services.items;

import com.reclabs.recomendar.common.exceptions.RecIllegalArgumentException;
import com.reclabs.recomendar.common.helpers.ParameterHelper;
import com.reclabs.recomendar.common.helpers.types.DateHelper;
import com.reclabs.recomendar.common.types.DatePrecisionType;
import com.reclabs.recomendar.core.helpers.QueueHelper;
import com.reclabs.recomendar.model.documents.items.ItemShortURL;
import com.reclabs.recomendar.model.repositories.items.ItemShortURLRepository;
import com.reclabs.recomendar.model.services.BarneyService;
import com.reclabs.recomendar.model.services.items.ItemShortURLService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author raulanatol
 */
@Service
public class ItemShortURLServiceImpl implements ItemShortURLService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ItemShortURLServiceImpl.class);
    private static final String LARGE_ITEM_URL_WITH_UTM_CAMPAING = "http://web.recomendar.com/item/%1$s?utm_source=%2$s&utm_medium=item_page&utm_campaing=products_shared_by_users&utm_content=%3$s";

    @Autowired
    private ItemShortURLRepository itemShortURLRepository;

    @Autowired
    private BarneyService barneyService;

    @Autowired
    private QueueHelper queueHelper;

    private String generateLargeItemURL(String itemId, String itemName, String utmSource) {
        return String.format(LARGE_ITEM_URL_WITH_UTM_CAMPAING, itemId, utmSource, itemName);
    }

    private String createShortURL(String userId, String itemId, String itemName, String utmSource) {
        try {
            String itemNameEncoding = URLEncoder.encode(itemName, "UTF-8");
            String itemURL = generateLargeItemURL(itemId, itemNameEncoding, utmSource);
            String shortURL = barneyService.createItemShortURL(itemURL, userId, itemId);
            ItemShortURL itemShortURL = new ItemShortURL();
            itemShortURL.setItemId(itemId);
            itemShortURL.setCreatedDate(DateHelper.getCurrentDate(DatePrecisionType.MILLISECOND));
            itemShortURL.setShortURL(shortURL);
            itemShortURL.setUserId(userId);
            itemShortURLRepository.save(itemShortURL);
            return shortURL;
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("Impossible encoding itemName", e);
            throw new RecIllegalArgumentException("The itemName is invalid");
        }
    }

    @Override
    public String getShortURL(String userId, String itemId, String itemName, String utmSource) {
        ParameterHelper.assertAnyEmpty(userId, itemId, itemName, utmSource);
        String result;
        ItemShortURL itemShortURL = itemShortURLRepository.findByUserAndItemId(userId, itemId);
        if (itemShortURL == null) {
            result = createShortURL(userId, itemId, itemName, utmSource);
            queueHelper.trackEventGetShortURL(userId, itemId);
        } else {
            result = itemShortURL.getShortURL();
        }
        return result;
    }
}



