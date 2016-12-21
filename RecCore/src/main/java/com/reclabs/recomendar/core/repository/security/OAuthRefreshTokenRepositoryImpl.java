/**
 * Project: RecCore
 * Created by: raulanatol at 31/12/2012 12:16:09
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.repository.security;

import com.reclabs.recomendar.model.documents.security.OAuthRefreshToken;
import com.reclabs.recomendar.model.repositories.security.OAuthRefreshTokenRepository;
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
public class OAuthRefreshTokenRepositoryImpl extends SimpleMongoRepository<OAuthRefreshToken, String> implements OAuthRefreshTokenRepository {

    /**
     * @param mongoTemplate
     */
    @Autowired
    public OAuthRefreshTokenRepositoryImpl(final MongoTemplate mongoTemplate) {
        super(new MongoRepositoryFactory(mongoTemplate).<OAuthRefreshToken, String>getEntityInformation(OAuthRefreshToken.class), mongoTemplate);
    }

    /*
     * (non-Javadoc)
     * @see
     * com.reclabs.recomendar.model.repositories.spring.security.OAuthRefreshTokenRepository#deleteByTokenId(java.lang
     * .String)
     */
    @Override
    public void deleteByTokenId(String tokenId) {
        Query query = new Query(Criteria.where("tokenId").is(tokenId));
        getMongoOperations().remove(query, OAuthRefreshToken.class);
    }

    /*
     * (non-Javadoc)
     * @see
     * com.reclabs.recomendar.model.repositories.spring.security.OAuthRefreshTokenRepository#findByTokenId(java.lang.String
     * )
     */
    @Override
    public OAuthRefreshToken findByTokenId(String tokenId) {
        Query query = new Query(Criteria.where("tokenId").is(tokenId));
        return getMongoOperations().findOne(query, OAuthRefreshToken.class);
    }

}
