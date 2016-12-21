/**
 * Project: RecCore
 * Created by: raulanatol at 03/11/13 11:27
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.repository.user;

import com.reclabs.recomendar.model.documents.statistics.Karma;
import com.reclabs.recomendar.model.repositories.user.KarmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.support.MongoRepositoryFactory;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author raulanatol
 */
@Repository
public class KarmaRepositoryImpl extends SimpleMongoRepository<Karma, String> implements KarmaRepository {

    @Autowired
    public KarmaRepositoryImpl(final MongoTemplate mongoTemplate) {
        super(new MongoRepositoryFactory(mongoTemplate).<Karma, String>getEntityInformation(Karma.class), mongoTemplate);
    }
}
