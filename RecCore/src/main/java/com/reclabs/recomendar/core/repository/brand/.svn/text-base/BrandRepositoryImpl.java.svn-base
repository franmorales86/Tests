/**
 * Project: RecCore
 * Created by: raulanatol at 10/07/13 18:24
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.repository.brand;

import com.reclabs.recomendar.model.documents.brand.Brand;
import com.reclabs.recomendar.model.repositories.brand.BrandRepository;
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
public class BrandRepositoryImpl extends SimpleMongoRepository<Brand, String> implements BrandRepository {

    /**
     * @param mongoTemplate The mongo template
     */
    @Autowired
    public BrandRepositoryImpl(final MongoTemplate mongoTemplate) {
        super(new MongoRepositoryFactory(mongoTemplate).<Brand, String>getEntityInformation(Brand.class), mongoTemplate);
    }

    @Override
    public List<Brand> findByAffiliationDomain(String domain) {
        Query query = Query.query(Criteria.where("affiliationManager.domain").is(domain));
        return getMongoOperations().find(query, Brand.class);
    }

    @Override
    public Brand findByName(String brandName) {
        Query query = Query.query(Criteria.where("name").is(brandName));
        return getMongoOperations().findOne(query, Brand.class);
    }
}
