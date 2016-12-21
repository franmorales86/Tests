/**
 * Project: RecCore
 * Created by: fjmorales at 10/11/2012 20:20:34
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.repository;

import com.reclabs.recomendar.common.helpers.RegExpHelper;
import com.reclabs.recomendar.common.types.RegexOptionsType;
import com.reclabs.recomendar.model.documents.Tag;
import com.reclabs.recomendar.model.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.repository.support.MongoRepositoryFactory;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author fjmorales
 */
@Repository
public class TagRepositoryImpl extends SimpleMongoRepository<Tag, String> implements TagRepository {
    /**
     * @param mongoTemplate Template of mongoDB
     */
    @Autowired
    public TagRepositoryImpl(final MongoTemplate mongoTemplate) {
        super(new MongoRepositoryFactory(mongoTemplate).<Tag, String>getEntityInformation(Tag.class), mongoTemplate);
    }

    /*
     * (non-Javadoc)
     * @see com.reclabs.recomendar.model.repositories.TagRepository#findByName(java.lang.String)
     */
    @Override
    public List<Tag> findByName(final String name) {
        Query query = new Query(Criteria.where("name").regex(name, RegexOptionsType.CASE_INSENSITIVE.getCode()));
        return getMongoOperations().find(query, Tag.class);
    }

    /*
     * (non-Javadoc)
     * @see com.reclabs.recomendar.model.repositories.TagRepository#findByExactlyName(java.lang.String)
     */
    @Override
    public List<Tag> findByExactlyName(final String name) {
        // Se realiza la busqueda por nombre no teniendo en cuenta mayusculas y minusculas
        Query query = new Query(Criteria.where("name").regex(RegExpHelper.addStartEndSymbol(name), RegexOptionsType.CASE_INSENSITIVE.getCode()));
        return getMongoOperations().find(query, Tag.class);
    }

    /*
     * (non-Javadoc)
     * @see com.reclabs.recomendar.model.repositories.TagRepository#findListByExactlyName(java.util.List)
     */
    @Override
    public List<Tag> findListByExactlyName(final List<String> name) {
        // Se realiza la busqueda por nombre no teniendo en cuenta mayusculas y minusculas
        Query query = new Query(Criteria.where("name").in(name));
        return getMongoOperations().find(query, Tag.class);
    }

    /*
     * (non-Javadoc)
     * @see com.reclabs.recomendar.model.repositories.TagRepository#getOrCreate(java.lang.String)
     */
    @Override
    public Tag getOrCreate(String tagName) {
        // FIXME optimizar con un findAndModify
        Tag found = findExactlyName(tagName);
        if (found == null) {
            found = new Tag();
            found.setName(tagName);
            save(found);
        }
        return found;
    }

    @Override
    public void renameTag(String tagId, String newName) {
        Query query = Query.query(Criteria.where("id").is(tagId));
        Update update = Update.update("name", newName);
        getMongoOperations().updateFirst(query, update, Tag.class);
    }

    /*
     * (non-Javadoc)
     * @see com.reclabs.recomendar.model.repositories.TagRepository#findExactlyName(java.lang.String)
     */
    @Override
    public Tag findExactlyName(String name) {
        Query query = Query.query(Criteria.where("name").is(name));
        return getMongoOperations().findOne(query, Tag.class);
    }

}
