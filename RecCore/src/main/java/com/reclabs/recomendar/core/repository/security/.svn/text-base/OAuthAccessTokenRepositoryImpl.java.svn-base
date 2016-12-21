/**
 * Project: RecCore
 * Created by: raulanatol at 31/12/2012 12:01:56
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.repository.security;

import com.reclabs.recomendar.model.documents.security.OAuthAccessToken;
import com.reclabs.recomendar.model.repositories.security.OAuthAccessTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.support.MongoRepositoryFactory;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author raulanatol
 */
@Repository
public class OAuthAccessTokenRepositoryImpl extends SimpleMongoRepository<OAuthAccessToken, String> implements OAuthAccessTokenRepository {

    /**
     * @param mongoTemplate Template of mongo repository
     */
    @Autowired
    public OAuthAccessTokenRepositoryImpl(final MongoTemplate mongoTemplate) {
        super(new MongoRepositoryFactory(mongoTemplate).<OAuthAccessToken, String>getEntityInformation(OAuthAccessToken.class), mongoTemplate);
    }

    /*
     * (non-Javadoc)
     * @see
     * com.reclabs.recomendar.model.repositories.spring.security.OAuthAccessTokenRepository#deleteByTokenId(java.lang.
     * String)
     */
    @Override
    public void deleteByTokenId(String tokenId) {
        Query query = new Query(Criteria.where("tokenId").is(tokenId));
        getMongoOperations().remove(query, OAuthAccessToken.class);
    }

    /*
     * (non-Javadoc)
     * @see
     * com.reclabs.recomendar.model.repositories.spring.security.OAuthAccessTokenRepository#deleteByRefreshTokenId(java
     * .lang.String)
     */
    @Override
    public void deleteByRefreshTokenId(String refreshTokenId) {
        Query query = new Query(Criteria.where("refreshToken").is(refreshTokenId));
        getMongoOperations().remove(query, OAuthAccessToken.class);
    }

    /*
     * (non-Javadoc)
     * @see
     * com.reclabs.recomendar.model.repositories.spring.security.OAuthAccessTokenRepository#findByTokenId(java.lang.String
     * )
     */
    @Override
    public OAuthAccessToken findByTokenId(String tokenId) {
        Query query = new Query(Criteria.where("tokenId").is(tokenId));
        return getMongoOperations().findOne(query, OAuthAccessToken.class);
    }

    /*
     * (non-Javadoc)
     * @see
     * com.reclabs.recomendar.model.repositories.spring.security.OAuthAccessTokenRepository#findByUsername(java.lang.String
     * )
     */
    @Override
    public List<OAuthAccessToken> findByUsername(String userName) {
        Query query = new Query(Criteria.where("username").is(userName));
        return getMongoOperations().find(query, OAuthAccessToken.class);
    }

    /*
     * (non-Javadoc)
     * @see
     * com.reclabs.recomendar.model.repositories.spring.security.OAuthAccessTokenRepository#findByClientId(java.lang.String
     * )
     */
    @Override
    public List<OAuthAccessToken> findByClientId(String clientId) {
        Query query = new Query(Criteria.where("clientId").is(clientId));
        return getMongoOperations().find(query, OAuthAccessToken.class);
    }

    @Override
    public void deleteByUsername(String username) {
        Query query = Query.query(Criteria.where("user_name").is(username));
        getMongoOperations().remove(query, OAuthAccessToken.class);
    }

    /*
     * (non-Javadoc)
     * @see
     * com.reclabs.recomendar.model.repositories.spring.security.OAuthAccessTokenRepository#findByAuthenticationId(java
     * .lang.String)
     */
    @Override
    public OAuthAccessToken findByAuthenticationId(String authenticationId) {
        Query query = new Query(Criteria.where("authenticationId").is(authenticationId));
        return getMongoOperations().findOne(query, OAuthAccessToken.class);
    }
}
