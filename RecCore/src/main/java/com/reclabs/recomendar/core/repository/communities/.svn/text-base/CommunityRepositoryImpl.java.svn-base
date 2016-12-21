/**
 * Project: RecCore
 * Created by: fjmorales at 05/04/2013 16:49:32
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.repository.communities;

import com.reclabs.recomendar.model.documents.communities.Community;
import com.reclabs.recomendar.model.repositories.communities.CommunityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.support.MongoRepositoryFactory;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author fjmorales
 */
@Repository
public class CommunityRepositoryImpl extends SimpleMongoRepository<Community, String> implements CommunityRepository {
    /**
     * @param mongoTemplate
     */
    @Autowired
    public CommunityRepositoryImpl(final MongoTemplate mongoTemplate) {
        super(new MongoRepositoryFactory(mongoTemplate).<Community, String>getEntityInformation(Community.class), mongoTemplate);
    }
}
