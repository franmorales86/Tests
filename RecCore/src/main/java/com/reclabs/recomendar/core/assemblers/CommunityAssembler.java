/**
 * Project: RecCore
 * Created by: fjmorales at 05/04/2013 17:44:23
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.assemblers;

import com.reclabs.recomendar.model.assemblers.GenericAssembler;
import com.reclabs.recomendar.model.documents.communities.Community;
import com.reclabs.recomendar.objects.documents.communities.CommunityDTO;

/**
 * @author fjmorales
 */
public class CommunityAssembler extends GenericAssembler<CommunityDTO, Community> {

    /*
     * (non-Javadoc)
     * @see com.reclabs.recomendar.model.assemblers.GenericAssembler#internalToRight(java.lang.Object,
     * java.lang.Object)
     */
    @Override
    public CommunityDTO internalToRight(Community source, CommunityDTO result) {
        return result;
    }

    /*
     * (non-Javadoc)
     * @see com.reclabs.recomendar.model.assemblers.GenericAssembler#internalToLeft(java.lang.Object,
     * java.lang.Object)
     */
    @Override
    public Community internalToLeft(CommunityDTO source, Community result) {
        return result;
    }

}
