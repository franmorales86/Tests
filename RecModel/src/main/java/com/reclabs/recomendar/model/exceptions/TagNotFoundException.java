/**
 * Project: RecModel
 * Created by: fjmorales at 04/04/2013 17:46:19
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.model.exceptions;

import com.reclabs.recomendar.common.exceptions.generic.ApiException;
import com.reclabs.recomendar.common.types.CodeErrorType;

/**
 * @author fjmorales
 */
@SuppressWarnings("WeakerAccess")
public class TagNotFoundException extends ApiException {

    private static final long serialVersionUID = -5371021880699912147L;

    /**
     * @param tagName La etiqueta que no se ha encontrado.
     */
    public TagNotFoundException(String tagName) {
        super(CodeErrorType.TAG_NOT_FOUND, "Tag " + tagName + " is not found");
    }
}
