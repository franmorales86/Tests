/**
 * Project: RecCore
 * Created by: raulanatol at 12/06/13 11:55
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.repository.items;

import com.reclabs.recomendar.model.documents.items.ItemToVerify;
import com.reclabs.recomendar.model.repositories.items.ItemToVerifyRepository;
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
public class ItemToVerifyRepositoryImpl extends SimpleMongoRepository<ItemToVerify, String> implements ItemToVerifyRepository {

    @Autowired
    public ItemToVerifyRepositoryImpl(MongoTemplate mongoTemplate) {
        super(new MongoRepositoryFactory(mongoTemplate).<ItemToVerify, String>getEntityInformation(ItemToVerify.class), mongoTemplate);
    }

    @Override
    public void deleteByItemId(String itemId) {
        Query query = Query.query(Criteria.where("itemId").is(itemId));
        getMongoOperations().findAndRemove(query, ItemToVerify.class);
    }
}
