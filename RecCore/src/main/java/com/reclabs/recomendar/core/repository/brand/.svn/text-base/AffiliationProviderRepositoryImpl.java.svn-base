/**
 * Project: RecCore
 * Created by: raulanatol at 10/07/13 12:32
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.repository.brand;

import com.reclabs.recomendar.model.documents.brand.AffiliationProvider;
import com.reclabs.recomendar.model.repositories.brand.AffiliationProviderRepository;
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
public class AffiliationProviderRepositoryImpl extends SimpleMongoRepository<AffiliationProvider, String> implements AffiliationProviderRepository {
    /**
     * @param mongoTemplate The mongo template
     */
    @Autowired
    public AffiliationProviderRepositoryImpl(final MongoTemplate mongoTemplate) {
        super(new MongoRepositoryFactory(mongoTemplate).<AffiliationProvider, String>getEntityInformation(AffiliationProvider.class), mongoTemplate);
    }

    @Override
    public AffiliationProvider findByName(String affiliationProviderName) {
        Query query = Query.query(Criteria.where("name").is(affiliationProviderName));
        return getMongoOperations().findOne(query, AffiliationProvider.class);
    }
}
