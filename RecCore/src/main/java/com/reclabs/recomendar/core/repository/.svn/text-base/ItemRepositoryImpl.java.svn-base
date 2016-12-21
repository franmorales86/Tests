/**
 * Project: RecCore
 * Created by: fjmorales at 10/11/2012 20:45:24
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.repository;

import com.reclabs.recomendar.common.helpers.RegExpHelper;
import com.reclabs.recomendar.common.helpers.types.CollectionHelper;
import com.reclabs.recomendar.common.types.RegexOptionsType;
import com.reclabs.recomendar.model.documents.items.Item;
import com.reclabs.recomendar.model.documents.items.ItemTag;
import com.reclabs.recomendar.model.repositories.items.ItemRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.repository.support.MongoRepositoryFactory;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fjmorales
 */
@Repository
public class ItemRepositoryImpl extends SimpleMongoRepository<Item, String> implements ItemRepository {
    /**
     * @param mongoTemplate Template of mongoDB
     */
    @Autowired
    public ItemRepositoryImpl(final MongoTemplate mongoTemplate) {
        super(new MongoRepositoryFactory(mongoTemplate).<Item, String>getEntityInformation(Item.class), mongoTemplate);
    }

    /*
     * (non-Javadoc)
     * @see com.reclabs.recomendar.model.repositories.ItemRepository#findByName(java.lang.String)
     */
    @Override
    public List<Item> findByExactlyName(final String name) {
        Query query = Query.query(Criteria.where("name").regex(RegExpHelper.addStartEndSymbol(name), RegexOptionsType.CASE_INSENSITIVE.getCode()));
        return getMongoOperations().find(query, Item.class);
    }

    /*
     * (non-Javadoc)
     * @see com.reclabs.recomendar.model.repositories.ItemRepository#findByContainsName(java.lang.String)
     */
    @Override
    public List<Item> findByName(final String name) {
        // Se realiza la busqueda por nombre no teniendo en cuenta mayusculas y minusculas
        Query query = new Query(Criteria.where("name").regex(name, "i"));
        return getMongoOperations().find(query, Item.class);
    }

    /*
     * (non-Javadoc)
     * @see com.reclabs.recomendar.model.repositories.ItemRepository#findByNameOrDescription(java.lang.String)
     */
    @Override
    public List<Item> findByNameOrDescription(final String name) {
        // Se realiza la busqueda por nombre no teniendo en cuenta mayusculas y minusculas
        Query query = new Query(new Criteria().orOperator(Criteria.where("name").regex(name, RegexOptionsType.CASE_INSENSITIVE.getCode()), Criteria.where("description").regex(name, RegexOptionsType.CASE_INSENSITIVE.getCode())));
        return getMongoOperations().find(query, Item.class);
    }

    /*
     * (non-Javadoc)
     * @see com.reclabs.recomendar.model.repositories.ItemRepository#findByIds(java.util.List)
     */
    @Override
    public List<Item> findByIds(List<String> itemIds) {
        List<Item> result;
        if (CollectionHelper.isEmpty(itemIds)) {
            result = new ArrayList<>();
        } else {
            Query query = Query.query(Criteria.where("id").in(itemIds));
            result = getMongoOperations().find(query, Item.class);
        }
        return result;
    }

    @Override
    public List<Item> findByIdsOrderByCreatedDateDesc(List<String> itemIds) {
        List<Item> result;
        if (CollectionHelper.isEmpty(itemIds)) {
            result = new ArrayList<>();
        } else {
            Query query = Query.query(Criteria.where("id").in(itemIds));
            query.with(new Sort(Sort.Direction.DESC, "createdDate"));
            result = getMongoOperations().find(query, Item.class);
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * @see com.reclabs.recomendar.model.repositories.ItemRepository#findByCategory(java.lang.String,
     * java.lang.Integer, java.lang.Integer)
     */
    @Override
    public List<Item> findByCategory(String categoryId, Integer page, Integer size) {
        Query query = Query.query(Criteria.where("category").is(categoryId));
        query.with(new PageRequest(page, size));
        return getMongoOperations().find(query, Item.class);
    }

    @Override
    public void deleteTag(String tagId) {
        ItemTag itemTag = new ItemTag();
        itemTag.setTagId(tagId);
        Query removeQuery = Query.query(Criteria.where("tags.tagId").is(tagId));
        Update update = new Update().pull("tags", itemTag);
        getMongoOperations().updateMulti(removeQuery, update, Item.class);
    }

    @Override
    public void renameTag(String tagId, String newName) {
        ItemTag itemTag = new ItemTag();
        itemTag.setName(newName);
        itemTag.setTagId(tagId);
        Query updateQuery = Query.query(Criteria.where("tags.tagId").is(tagId));
        Update update = new Update().set("tags.$", itemTag);
        getMongoOperations().updateMulti(updateQuery, update, Item.class);
    }

    @Override
    public List<Item> findByDomainURL(String domainToSearch) {
        Query query = new Query(Criteria.where("informationURL").regex(domainToSearch, "i"));
        return getMongoOperations().find(query, Item.class);
    }

    @Override
    public List<Item> findItemsByAffiliationManager(String alias) {
        Query query = Query.query(Criteria.where("affiliationManagerDataList.alias").is(alias));
        return getMongoOperations().find(query, Item.class);
    }

    @Override
    public Item findByInformationURL(String informationURL) {
        Query query = Query.query(Criteria.where("informationURL").is(informationURL));
        return getMongoOperations().findOne(query, Item.class);
    }

    @Override
    public Item findByOriginImageURL(String originImageURL) {
        Query query = Query.query(Criteria.where("originImageURL").is(originImageURL));
        return getMongoOperations().findOne(query, Item.class);

    }
}
