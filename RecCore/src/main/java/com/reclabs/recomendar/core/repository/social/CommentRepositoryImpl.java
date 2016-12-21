/**
 * Project: RecCore
 * Created by: raulanatol at 22/10/13 16:26
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.repository.social;

import com.reclabs.recomendar.model.documents.social.Comment;
import com.reclabs.recomendar.model.repositories.social.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
public class CommentRepositoryImpl extends SimpleMongoRepository<Comment, String> implements CommentRepository {

    @Autowired
    public CommentRepositoryImpl(final MongoTemplate mongoTemplate) {
        super(new MongoRepositoryFactory(mongoTemplate).<Comment, String>getEntityInformation(Comment.class), mongoTemplate);
    }

    @Override
    public List<Comment> findByItemId(String itemId) {
        Query query = Query.query(Criteria.where("itemId").is(itemId));
        query.with(new Sort(Sort.Direction.ASC, "creationDate"));
        return getMongoOperations().find(query, Comment.class);
    }
}
