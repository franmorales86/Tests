/**
 * Project: RecCore
 * Created by: raulanatol at 02/05/2013 16:34:35
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.repository.items;

import com.reclabs.recomendar.model.documents.items.ItemToVerify;
import com.reclabs.recomendar.model.documents.items.ItemWant;
import com.reclabs.recomendar.model.repositories.items.ItemWantRepository;
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
public class ItemWantRepositoryImpl extends SimpleMongoRepository<ItemWant, String> implements ItemWantRepository {

    /**
     * @param mongoTemplate
     */
    @Autowired
    public ItemWantRepositoryImpl(final MongoTemplate mongoTemplate) {
        super(new MongoRepositoryFactory(mongoTemplate).<ItemWant, String>getEntityInformation(ItemWant.class), mongoTemplate);
    }

    @Override
    public void deleteByUserIdAndItemId(String userId, String itemId) {
        Query query = Query.query(Criteria.where("userId").is(userId).and("itemId").is(itemId));
        getMongoOperations().remove(query, ItemWant.class);
    }

    @Override
    public ItemWant findByUserIdAndItemId(String userId, String itemId) {
        Query query = Query.query(Criteria.where("userId").is(userId).and("itemId").is(itemId));
        return getMongoOperations().findOne(query, ItemWant.class);
    }

    @Override
    public void deleteByItemId(String itemId) {
        Query query = Query.query(Criteria.where("itemId").is(itemId));
        getMongoOperations().findAndRemove(query, ItemToVerify.class);
    }
}