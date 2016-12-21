/**
 * Project: RecCore
 * Created by: fjmorales at 04/04/2013 16:14:46
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.repository.categories;

import com.reclabs.recomendar.model.documents.categories.OPTProductTagCategory;
import com.reclabs.recomendar.model.repositories.categories.OPTProductTagCategoryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.support.MongoRepositoryFactory;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author fjmorales
 */
@Repository
public class OPTProductTagCategoryRepositoryImpl extends SimpleMongoRepository<OPTProductTagCategory, String> implements OPTProductTagCategoryRepository {

    /**
     * @param mongoTemplate
     */
    @Autowired
    public OPTProductTagCategoryRepositoryImpl(final MongoTemplate mongoTemplate) {
        super(new MongoRepositoryFactory(mongoTemplate).<OPTProductTagCategory, String>getEntityInformation(OPTProductTagCategory.class), mongoTemplate);
    }

    /*
     * (non-Javadoc)
     * @see
     * com.reclabs.recomendar.model.repositories.categories.OPTProductTagCategoryRepository#findByTagCategory(java
     * .lang.String, java.lang.String)
     */
    @Override
    public OPTProductTagCategory findByTagCategory(String tagId, String categoryId) {
        Query query = Query.query(new Criteria().andOperator(Criteria.where("tag.$id").is(new ObjectId(tagId)), Criteria.where("category.$id").is(new ObjectId(categoryId))));
        return getMongoOperations().findOne(query, OPTProductTagCategory.class);
    }

    /*
     * (non-Javadoc)
     * @see
     * com.reclabs.recomendar.model.repositories.categories.OPTProductTagCategoryRepository#findByCategory(java
     * .lang.String)
     */
    @Override
    public List<OPTProductTagCategory> findByCategory(String categoryId) {
        Query query = Query.query(Criteria.where("category.$id").is(new ObjectId(categoryId)));
        return getMongoOperations().find(query, OPTProductTagCategory.class);
    }

    /*
     * (non-Javadoc)
     * @see
     * com.reclabs.recomendar.model.repositories.categories.OPTProductTagCategoryRepository#findByCategoryPriority
     * (java.lang.String, int)
     */
    @Override
    public List<OPTProductTagCategory> findByCategoryPriority(String categoryId, int numberOfTags) {
        Query query = Query.query(Criteria.where("category.$id").is(new ObjectId(categoryId)));
        query.with(new PageRequest(0, numberOfTags, new Sort(Direction.DESC, "numberOfProducts")));
        query.fields().exclude("category");
        return getMongoOperations().find(query, OPTProductTagCategory.class);
    }

}
