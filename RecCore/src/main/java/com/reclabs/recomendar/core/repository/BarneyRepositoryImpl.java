/**
 * Project: RecCore
 * Created by: raulanatol at 11/01/2013 18:35:10
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.repository;

import com.amazonaws.services.simpledb.AmazonSimpleDBClient;
import com.amazonaws.services.simpledb.model.*;
import com.reclabs.barneylib.BarneyServer;
import com.reclabs.barneylib.types.BarneyUrlData;
import com.reclabs.recomendar.common.exceptions.generic.ErrorException;
import com.reclabs.recomendar.common.helpers.types.CollectionHelper;
import com.reclabs.recomendar.common.helpers.types.DateHelper;
import com.reclabs.recomendar.model.repositories.BarneyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author raulanatol
 */
@Repository
public class BarneyRepositoryImpl implements BarneyRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(BarneyRepositoryImpl.class);
    /**
     * Conector con la base de datos de amazon de SimpleDB
     */
    @Autowired
    private AmazonSimpleDBClient amazonSimpleDBClientPRO;

    @Override
    public boolean exist(final BarneyServer server, final String shortCode) throws ErrorException {
        try {
            GetAttributesResult select = amazonSimpleDBClientPRO.getAttributes(new GetAttributesRequest(server.getDbName(), shortCode));
            List<Attribute> results = select.getAttributes();
            return (!CollectionHelper.isEmpty(results));
        } catch (RuntimeException e) {
            LOGGER.error("[Error verifying if the shortCode exist on the current Barney server][Server: {}][ShortCode: {}]", server, shortCode);
            throw e;
        }
    }

    @Override
    public void save(BarneyServer server, BarneyUrlData barneyUrlData) throws ErrorException {
        List<ReplaceableAttribute> attributes = new ArrayList<>();
        attributes.add(new ReplaceableAttribute(BarneyUrlData.VALID_URL, barneyUrlData.getValidUrl(), true));
        attributes.add(new ReplaceableAttribute(BarneyUrlData.SHORT_CODE_ATTRIBUTE, barneyUrlData.getShortCode(), true));
        attributes.add(new ReplaceableAttribute(BarneyUrlData.VALID_URL, barneyUrlData.getValidUrl(), true));
        attributes.add(new ReplaceableAttribute(BarneyUrlData.SHORT_CODE_ATTRIBUTE, barneyUrlData.getShortCode(), true));
        attributes.add(new ReplaceableAttribute(BarneyUrlData.SHORT_URL_ATTRIBUTE, barneyUrlData.getShortUrl(), true));
        attributes.add(new ReplaceableAttribute(BarneyUrlData.CREATED_DATE_ATTRIBUTE, DateHelper.toStringExtend(barneyUrlData.getCreatedDate()), true));
        attributes.add(new ReplaceableAttribute(BarneyUrlData.WEB_NAME_ATTRIBUTE, barneyUrlData.getWebname(), true));
        attributes.add(new ReplaceableAttribute(BarneyUrlData.LINK_TYPE_ATTRIBUTE, barneyUrlData.getLinkType().getLabel(), true));
        attributes.add(new ReplaceableAttribute(BarneyUrlData.USER_ID_ATTRIBUTE, barneyUrlData.getUserId(), true));
        amazonSimpleDBClientPRO.putAttributes(new PutAttributesRequest(server.getDbName(), barneyUrlData.getShortCode(), attributes));
    }
}
