/**
 * Project: RecCore
 * Created by: raulanatol at 29/09/13 18:47
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.repository.items;

import com.reclabs.recomendar.model.documents.items.ItemShortURL;
import com.reclabs.recomendar.model.repositories.items.ItemShortURLRepository;
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
public class ItemShortURLRepositoryImpl extends SimpleMongoRepository<ItemShortURL, String> implements ItemShortURLRepository {

    @Autowired
    public ItemShortURLRepositoryImpl(final MongoTemplate mongoTemplate) {
        super(new MongoRepositoryFactory(mongoTemplate).<ItemShortURL, String>getEntityInformation(ItemShortURL.class), mongoTemplate);
    }

    @Override
    public ItemShortURL findByUserAndItemId(String userId, String itemId) {
        Query query = Query.query(Criteria.where("userId").is(userId).and("itemId").is(itemId));
        return getMongoOperations().findOne(query, ItemShortURL.class);
    }
}
