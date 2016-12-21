/**
 * Project: RecCore
 * Created by: raulanatol at 17/07/13 10:16
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.repository.brand;

import com.reclabs.recomendar.model.documents.brand.AffiliationManager;
import com.reclabs.recomendar.model.repositories.brand.AffiliationManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
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
public class AffiliationManagerRepositoryImpl extends SimpleMongoRepository<AffiliationManager, String> implements AffiliationManagerRepository {

    @Autowired
    public AffiliationManagerRepositoryImpl(MongoTemplate mongoTemplate) {
        super(new MongoRepositoryFactory(mongoTemplate).<AffiliationManager, String>getEntityInformation(AffiliationManager.class), mongoTemplate);
    }

    @Override
    public List<AffiliationManager> getAffiliationManagerByProviderName(String providerName) {
        Query query = Query.query(Criteria.where("affiliationProvider.name").is(providerName));
        return getMongoOperations().find(query, AffiliationManager.class);
    }

    @Override
    public List<AffiliationManager> getAffiliationManagerByBrandName(String brandName) {
        Query query = Query.query(Criteria.where("brandData.name").is(brandName));
        return getMongoOperations().find(query, AffiliationManager.class);
    }

    @Override
    public List<AffiliationManager> findByDomainURL(String domainURL) {
        Query query = Query.query(Criteria.where("domain").is(domainURL));
        return getMongoOperations().find(query, AffiliationManager.class);
    }

    @Override
    public AffiliationManager findByAlias(String alias) {
        Query query = Query.query(Criteria.where("alias").is(alias));
        return getMongoOperations().findOne(query, AffiliationManager.class);
    }

    @Override
    public List<AffiliationManager> findByBrandId(String brandId) {
        Query query = new BasicQuery("{brandData._id: { $oid: '" + brandId + "'}}");
        return getMongoOperations().find(query, AffiliationManager.class);
    }

    @Override
    public List<AffiliationManager> findAffiliationManagerByProviderId(String providerId) {
        Query query = Query.query(Criteria.where("affiliationProviderData.id").is(providerId));
        return getMongoOperations().find(query, AffiliationManager.class);
    }
}

