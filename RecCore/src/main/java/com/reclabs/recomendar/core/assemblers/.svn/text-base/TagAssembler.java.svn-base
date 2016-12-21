/**
 * Project: RecCore
 * Created by: fjmorales at 12/03/2013 18:19:52
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.assemblers;

import com.reclabs.recomendar.model.assemblers.GenericAssembler;
import com.reclabs.recomendar.model.documents.Tag;
import com.reclabs.recomendar.model.documents.items.ItemTag;
import com.reclabs.recomendar.objects.TagDTO;
import org.springframework.beans.BeanUtils;

/**
 * @author fjmorales
 */
public class TagAssembler extends GenericAssembler<TagDTO, Tag> {

    /*
     * (non-Javadoc)
     * @see com.reclabs.recomendar.model.assemblers.GenericAssembler#internalToRight(java.lang.Object,
     * java.lang.Object)
     */
    @Override
    public TagDTO internalToRight(Tag source, TagDTO result) {
        return result;
    }

    /*
     * (non-Javadoc)
     * @see com.reclabs.recomendar.model.assemblers.GenericAssembler#internalToLeft(java.lang.Object,
     * java.lang.Object)
     */
    @Override
    public Tag internalToLeft(TagDTO source, Tag result) {
        return result;
    }

    /**
     * Dado un tag obtenemos el ItemTag.
     * @param tag
     * @return El ItemTag.
     */
    public ItemTag toItemTag(Tag tag) {
        ItemTag result = new ItemTag();
        BeanUtils.copyProperties(tag, result);
        result.setTagId(tag.getId());
        return result;
    }

}
