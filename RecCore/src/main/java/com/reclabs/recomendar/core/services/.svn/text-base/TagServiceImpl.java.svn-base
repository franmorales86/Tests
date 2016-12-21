/**
 * Project: RecCore
 * Created by: fjmorales at 12/03/2013 18:16:39
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.services;

import com.reclabs.recomendar.common.exceptions.RecIllegalArgumentException;
import com.reclabs.recomendar.common.helpers.types.CollectionHelper;
import com.reclabs.recomendar.common.helpers.types.NullHelper;
import com.reclabs.recomendar.common.helpers.types.StringHelper;
import com.reclabs.recomendar.core.assemblers.TagAssembler;
import com.reclabs.recomendar.model.documents.Tag;
import com.reclabs.recomendar.model.documents.items.ItemTag;
import com.reclabs.recomendar.model.repositories.TagRepository;
import com.reclabs.recomendar.model.services.TagService;
import com.reclabs.recomendar.model.services.items.ItemService;
import com.reclabs.recomendar.objects.TagDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author fjmorales
 */
@Service
public class TagServiceImpl implements TagService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TagServiceImpl.class);

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    protected TagAssembler tagAssembler;

    @Autowired
    protected ItemService itemService;

    /*
     * (non-Javadoc)
     * @see com.reclabs.recomendar.model.services.TagService#findAllTags()
     */
    @Override
    public List<TagDTO> findAllTags() {
        return tagAssembler.toRight(tagRepository.findAll());
    }

    /*
     * (non-Javadoc)
     * @see
     * com.reclabs.recomendar.model.services.TagService#findOrInsertTagsByName
     * (java.util.List)
     */
    @Override
    public List<ItemTag> findOrInsertTagsByName(List<String> names) {
        if (NullHelper.isAnyNull(names)) {
            return null;
        }
        List<ItemTag> result = new ArrayList<>();
        names = StringHelper.toLowerCase(names);
        Map<String, Tag> tags = findListByExactlyName(names);
        if (tags.size() > 0) {
            for (String name : names) {
                if (tags.containsKey(name)) {
                    result.add(tagAssembler.toItemTag(tags.get(name)));
                }
            }
        } else {
            for (String name : names) {
                result.add(tagAssembler.toItemTag(createTag(name)));
            }
        }
        return result;
    }

    /**
     * Obtiene un listado de tags agrupados en un mapa de objetos a partir del
     * nombre, en caso de encontrarse duplicado el nombre lanza una excepcion
     * @param names Listado de nombres de las etiquetas a buscar
     * @return Conjunto de etiquetas existentes en la base de datos
     */
    protected Map<String, Tag> findListByExactlyName(List<String> names) {
        Map<String, Tag> result = new HashMap<>();
        List<Tag> tags = tagRepository.findListByExactlyName(names);
        for (Tag tag : tags) {
            if (result.containsKey(tag.getName())) {
                throw new DuplicateKeyException("Duplicate key tag error");
            } else {
                result.put(tag.getName(), tag);
            }
        }
        return result;
    }

    /**
     * Crea una etiqueta en la base de datos
     * @param name Nombre de la etiqueta a crear
     * @return Objeto que representa la etiqueta en la bbdd
     */
    Tag createTag(final String name) {
        Tag newTag = new Tag();
        newTag.setName(name);
        return tagRepository.save(newTag);
    }

    /*
     * (non-Javadoc)
     * @see
     * com.reclabs.recomendar.model.services.TagService#deleteTag(java.lang.
     * String)
     */
    @Override
    public void deleteTag(final String id) {
        LOGGER.info("[Trying to delete tag {}", id);
        itemService.deleteTag(id);
        tagRepository.delete(id);
    }

    /*
     * (non-Javadoc)
     * @see com.reclabs.recomendar.model.services.TagService#createOrUpdateTags(java.util.List)
     */
    @Override
    public List<ItemTag> createOrUpdateTags(List<ItemTag> tags) {
        List<ItemTag> result = new ArrayList<>();
        if (!CollectionHelper.isEmpty(tags)) {
            for (ItemTag itemTag : tags) {
                if (itemTag.getTagId() == null) {
                    if (!StringHelper.isEmpty(itemTag.getName())) {
                        result.add(createTagByName(itemTag.getName()));
                    }
                } else {
                    result.add(itemTag);
                }
            }
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * @see com.reclabs.recomendar.model.services.TagService#createTagByName(java.lang.String)
     */
    @Override
    public ItemTag createTagByName(String name) {
        String tagName = name.toLowerCase();
        return tagAssembler.toItemTag(tagRepository.getOrCreate(tagName));
    }

    @Override
    public void renameTag(String tagId, String newName) {
        Tag tag = tagRepository.findExactlyName(newName);
        if (tag != null) {
            throw new RecIllegalArgumentException("The name " + newName + " already exists like a tag name");
        }
        tagRepository.renameTag(tagId, newName);
        itemService.renameTag(tagId, newName);
        LOGGER.info("[Finish rename item {} with new name {}", tagId, newName);
    }
}
