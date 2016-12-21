/**
 * Project: RecCore
 * Created by: raulanatol at 31/12/2012 12:15:48
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.repository.security;

import com.reclabs.recomendar.model.documents.security.OAuthClientDetails;
import com.reclabs.recomendar.model.repositories.security.OAuthClientDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.support.MongoRepositoryFactory;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author raulanatol
 */
@Repository
public class OAuthClientDetailsRepositoryImpl extends SimpleMongoRepository<OAuthClientDetails, String> implements OAuthClientDetailsRepository {

    /**
     * @param mongoTemplate
     */
    @Autowired
    public OAuthClientDetailsRepositoryImpl(final MongoTemplate mongoTemplate) {
        super(new MongoRepositoryFactory(mongoTemplate).<OAuthClientDetails, String>getEntityInformation(OAuthClientDetails.class), mongoTemplate);
    }

    /*
     * (non-Javadoc)
     * @see
     * com.reclabs.recomendar.model.repositories.spring.security.OAuthClientDetailsRepository#updateClientSecret(java.
     * lang.String, java.lang.String)
     */
    @Override
    public void updateClientSecret(String clientId, String clientSecret) {
        Query query = new Query(Criteria.where("clientId").is(clientId));
        OAuthClientDetails clientDetails = getMongoOperations().findOne(query, OAuthClientDetails.class);
        if (clientDetails != null) {
            clientDetails.setClientSecret(clientSecret);
            save(clientDetails);
        }
    }

    /*
     * (non-Javadoc)
     * @see
     * com.reclabs.recomendar.model.repositories.spring.security.OAuthClientDetailsRepository#deleteByClientId(java.lang
     * .String)
     */
    @Override
    public void deleteByClientId(String clientId) {
        Query query = new Query(Criteria.where("clientId").is(clientId));
        getMongoOperations().remove(query, OAuthClientDetails.class);
    }

    /*
     * (non-Javadoc)
     * @see
     * com.reclabs.recomendar.model.repositories.spring.security.OAuthClientDetailsRepository#findByClientId(java.lang
     * .String)
     */
    @Override
    public OAuthClientDetails findByClientId(String clientId) {
        Query query = new Query(Criteria.where("clientId").is(clientId));
        return getMongoOperations().findOne(query, OAuthClientDetails.class);
    }
}
