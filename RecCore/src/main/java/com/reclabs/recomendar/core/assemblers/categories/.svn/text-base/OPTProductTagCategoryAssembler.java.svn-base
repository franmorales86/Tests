/**
 * Project: RecCore
 * Created by: fjmorales at 04/04/2013 17:54:49
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.assemblers.categories;

import com.reclabs.recomendar.model.assemblers.GenericAssembler;
import com.reclabs.recomendar.model.documents.categories.OPTProductTagCategory;
import com.reclabs.recomendar.objects.documents.category.CategoryTagsDTO;

/**
 * @author fjmorales
 */
public class OPTProductTagCategoryAssembler extends GenericAssembler<CategoryTagsDTO, OPTProductTagCategory> {

    /*
     * (non-Javadoc)
     * @see com.reclabs.recomendar.model.assemblers.GenericAssembler#internalToRight(java.lang.Object,
     * java.lang.Object)
     */
    @Override
    public CategoryTagsDTO internalToRight(OPTProductTagCategory source, CategoryTagsDTO result) {
        result.setTagName(source.getTag().getName());
        result.setTagId(source.getTag().getId());
        return result;
    }

    /*
     * (non-Javadoc)
     * @see com.reclabs.recomendar.model.assemblers.GenericAssembler#internalToLeft(java.lang.Object,
     * java.lang.Object)
     */
    @Override
    public OPTProductTagCategory internalToLeft(CategoryTagsDTO source, OPTProductTagCategory result) {
        return result;
    }

}
