/**
 * Project: RecCore
 * Created by: raulanatol at 11/01/2013 18:31:07
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.services;

import com.reclabs.barneylib.BarneyServer;
import com.reclabs.barneylib.LinkType;
import com.reclabs.barneylib.types.BarneyData;
import com.reclabs.recomendar.common.exceptions.generic.ErrorException;
import com.reclabs.recomendar.common.helpers.ParameterHelper;
import com.reclabs.recomendar.common.helpers.URLHelper;
import com.reclabs.recomendar.core.misc.BarneyServerHelper;
import com.reclabs.recomendar.model.repositories.BarneyRepository;
import com.reclabs.recomendar.model.services.BarneyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author raulanatol
 */
@Service
public class BarneyServiceImpl implements BarneyService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BarneyServiceImpl.class);

    @Autowired
    private BarneyRepository barneyRepository;

    @Autowired
    private BarneyServerHelper barneyServerHelper;

    @Override
    public String createShortUrl(final String url, String webName, LinkType linkType, String userId) throws ErrorException {
        return createShortUrl(url, webName, linkType, userId, null);
    }

    @Override
    public boolean exist(BarneyServer server, String shortCode) throws ErrorException {
        return barneyRepository.exist(server, shortCode);
    }

    @Override
    public String createItemShortURL(String url, String userId, String itemId) {
        ParameterHelper.assertAnyEmpty(url, userId, itemId);
        LOGGER.info("[Trying to create an item shortURL][ItemURL: {}][UserId: {}]", url, userId);
        return createShortUrl(url, "-", LinkType.REC_ITEM, userId, itemId);
    }

    private String createShortUrl(final String url, String webName, LinkType linkType, String userId, String data1) throws ErrorException {
        ParameterHelper.assertAnyNull(url, webName, linkType, userId);
        ParameterHelper.assertAnyEmpty(url);
        LOGGER.info("[Trying to create short url][URL: " + url + "][WebName: " + webName + "][LinkType: " + linkType.toString() + "][UserId: " + userId + "][Data: " + data1 + "]");
        String shortCode = URLHelper.generateShortCode();
        BarneyData barneyUrlData = barneyServerHelper.generateShortURL(shortCode, url, webName, linkType, userId, data1);
        barneyRepository.save(barneyUrlData.getBarneyServer(), barneyUrlData.getUrlData());
        return barneyUrlData.getUrlData().getShortUrl();
    }


}
