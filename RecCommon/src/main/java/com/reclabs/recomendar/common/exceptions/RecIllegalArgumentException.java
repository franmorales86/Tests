/**
 * Project: RecCommon
 * Created by: raulanatol at 21/11/2012 18:25:36
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.common.exceptions;

import com.reclabs.recomendar.common.exceptions.generic.ErrorException;
import com.reclabs.recomendar.common.helpers.types.StringHelper;
import com.reclabs.recomendar.common.types.CodeErrorType;

/**
 * Excepción lanzada cuando los parámetros de un método no son los correctos, contienen valores no válidos, etc.
 * @author raulanatol
 */
public class RecIllegalArgumentException extends ErrorException {
    private static final long serialVersionUID = -7683757166453154824L;

    /**
     * Dado el listado de parámetros incorrectos creamos la excepción.
     * @param paramsName Name of the parameters
     */
    public RecIllegalArgumentException(final String... paramsName) {
        super(CodeErrorType.ILLEGAL_PARAMETERS, toStringParameters(paramsName));
    }

    protected static String toStringParameters(final String... paramsName) {
        StringBuilder result = new StringBuilder();
        result.append("Illegal parameters");
        if (paramsName != null) {
            for (String paramName : paramsName) {
                if (!StringHelper.isEmpty(paramName)) {
                    result.append(" " + paramName);
                }
            }
        }
        return result.toString();
    }
}
