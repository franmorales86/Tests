/**
 * Project: RecCore
 * Created by: raulanatol at 23/03/2013 16:00:14
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.repository;

import com.reclabs.recomendar.common.helpers.RegExpHelper;
import com.reclabs.recomendar.common.types.RegexOptionsType;
import com.reclabs.recomendar.model.documents.Category;
import com.reclabs.recomendar.model.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mapping.model.MappingException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.support.MongoRepositoryFactory;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author raulanatol
 */
@Repository
public class CategoryRepositoryImpl extends SimpleMongoRepository<Category, String> implements CategoryRepository {

    /**
     * @param mongoTemplate
     */
    @Autowired
    public CategoryRepositoryImpl(final MongoTemplate mongoTemplate) {
        super(new MongoRepositoryFactory(mongoTemplate).<Category, String>getEntityInformation(Category.class), mongoTemplate);
    }

    /*
     * (non-Javadoc)
     * @see com.reclabs.recomendar.model.repositories.CategoryRepository#getMainCategories()
     */
    @Override
    public List<Category> getMainCategories() {
        List<Category> result;
        try {
            // FIXME el exists no está funcionando mirar xq.
            Query query = Query.query(Criteria.where("parent").exists(false));
            result = getMongoOperations().find(query, Category.class);
        } catch (MappingException e) {
            // para el caso en el que no hayan subcategorías.
            List<Category> temp = getMongoOperations().findAll(Category.class);
            result = new ArrayList<>();
            for (Category category : temp) {
                if (category.getParent() == null) {
                    result.add(category);
                }
            }
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * @see com.reclabs.recomendar.model.repositories.CategoryRepository#findByName(java.lang.String)
     */
    @Override
    public Category findByName(String categoryName) {
        Query query = Query.query(Criteria.where("name").regex(RegExpHelper.addStartEndSymbol(categoryName), RegexOptionsType.CASE_INSENSITIVE.getCode()));
        return getMongoOperations().findOne(query, Category.class);
    }
}
