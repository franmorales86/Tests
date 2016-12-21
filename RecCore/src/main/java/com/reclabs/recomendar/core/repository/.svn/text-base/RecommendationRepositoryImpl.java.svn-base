/**
 * Project: RecCore
 * Created by: raulanatol at 02/05/2013 16:34:35
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.repository;

import com.reclabs.recomendar.model.documents.Recommendation;
import com.reclabs.recomendar.model.repositories.RecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.support.MongoRepositoryFactory;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author raulanatol
 */
@Repository
public class RecommendationRepositoryImpl extends SimpleMongoRepository<Recommendation, String> implements RecommendationRepository {

    @Autowired
    public RecommendationRepositoryImpl(final MongoTemplate mongoTemplate) {
        super(new MongoRepositoryFactory(mongoTemplate).<Recommendation, String>getEntityInformation(Recommendation.class), mongoTemplate);
    }

    /*
     * (non-Javadoc)
     * @see com.reclabs.recomendar.model.repositories.RecommendationRepository#findItemIdByUserId(java.lang.String)
     */
    @Override
    public List<String> findItemIdByUserId(String userId) {
        Query query = new Query(Criteria.where("userId").is(userId));
        query.fields().include("itemId");
        List<Recommendation> items = getMongoOperations().find(query, Recommendation.class);
        List<String> result = new ArrayList<>();
        for (Recommendation recommendation : items) {
            result.add(recommendation.getItemId());
        }
        return result;
    }

    @Override
    public Recommendation findByUserIdAndItemId(String userId, String itemId) {
        Query query = Query.query(Criteria.where("userId").is(userId).and("itemId").is(itemId));
        return getMongoOperations().findOne(query, Recommendation.class);
    }

    @Override
    public void deleteByUserIdAndItemId(String userId, String itemId) {
        Query query = Query.query(Criteria.where("userId").is(userId).and("itemId").is(itemId));
        getMongoOperations().remove(query, Recommendation.class);
    }

    @Override
    public void deleteByItemId(String itemId) {
        Query query = Query.query(Criteria.where("itemId").is(itemId));
        getMongoOperations().findAndRemove(query, Recommendation.class);
    }

    @Override
    public List<Recommendation> findRecommendationsByUserFromDate(String userId, Date from) {
        Query query = Query.query(Criteria.where("userId").is(userId));
        query.addCriteria(Criteria.where("createdDate").gte(from));
        return getMongoOperations().find(query, Recommendation.class);
    }
}
