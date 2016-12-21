/**
 * Project: RecCore
 * Created by: raulanatol at 13/02/2013 16:30:48
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.assemblers;

import com.reclabs.recomendar.model.assemblers.GenericAssembler;
import com.reclabs.recomendar.model.documents.users.SocialUser;
import com.reclabs.recomendar.objects.users.SocialUserDTO;

/**
 * @author raulanatol
 */
public class SocialUserAssembler extends GenericAssembler<SocialUserDTO, SocialUser> {

    /*
     * (non-Javadoc)
     * @see com.reclabs.recomendar.model.assemblers.GenericAssembler#internalToRight(java.lang.Object,
     * java.lang.Object)
     */
    @Override
    public SocialUserDTO internalToRight(SocialUser source, SocialUserDTO result) {
        return result;
    }

    /*
     * (non-Javadoc)
     * @see com.reclabs.recomendar.model.assemblers.GenericAssembler#internalToLeft(java.lang.Object,
     * java.lang.Object)
     */
    @Override
    public SocialUser internalToLeft(SocialUserDTO source, SocialUser result) {
        return result;
    }
}
