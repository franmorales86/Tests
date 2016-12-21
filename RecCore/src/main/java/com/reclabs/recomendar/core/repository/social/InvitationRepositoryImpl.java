/**
 * Project: RecCore
 * Created by: raulanatol at 05/11/13 12:41
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.repository.social;

import com.reclabs.recomendar.model.documents.social.Invitation;
import com.reclabs.recomendar.model.repositories.social.InvitationRepository;
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
public class InvitationRepositoryImpl extends SimpleMongoRepository<Invitation, String> implements InvitationRepository {

    @Autowired
    public InvitationRepositoryImpl(final MongoTemplate mongoTemplate) {
        super(new MongoRepositoryFactory(mongoTemplate).<Invitation, String>getEntityInformation(Invitation.class), mongoTemplate);
    }

    @Override
    public List<Invitation> findByEmail(String email) {
        Query query = Query.query(Criteria.where("email").is(email));
        return getMongoOperations().find(query, Invitation.class);
    }
}
