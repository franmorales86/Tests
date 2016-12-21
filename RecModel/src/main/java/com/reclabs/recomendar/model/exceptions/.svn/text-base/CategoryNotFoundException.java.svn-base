/**
 * Project: RecModel
 * Created by: raulanatol at 02/04/2013 13:54:19
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
 * Excepción lanzada cuando no existan categorías
 * @author raulanatol
 */
@SuppressWarnings("WeakerAccess")
public class CategoryNotFoundException extends ApiException {
    private static final long serialVersionUID = -9090994784104085808L;

    /**
     * @param categoryName El nombre de la categoría que no se ha encontrado.
     */
    public CategoryNotFoundException(String categoryName) {
        super(CodeErrorType.CATEGORY_NOT_FOUND, "Category " + categoryName + " is not found");
    }
}
