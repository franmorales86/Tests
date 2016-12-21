/**
 * Project: RecModel
 * Created by: fjmorales at 02/04/2013 17:10:58
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
 * Excepción lanzada cuando se intenta insertar un usuario y el username ya existe.
 * @author fjmorales
 */
@SuppressWarnings("WeakerAccess")
public class DuplicateUserException extends ApiException {
    private static final long serialVersionUID = -7227426742913925962L;

    /**
     * @param username The username of the user duplicated
     */
    public DuplicateUserException(String username) {
        super(CodeErrorType.DUPLICATE_USER, "El usuario " + username + " ya existe.");
    }

}
