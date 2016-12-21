/**
 * Project: RecCore
 * Created by: raulanatol at 22/12/2012 18:49:58
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.exceptions;

import com.reclabs.recomendar.common.exceptions.generic.RecRuntimeException;

/**
 * @author raulanatol
 */
public class AccessDeniedException extends RecRuntimeException {
    private static final long serialVersionUID = 8100721639298518184L;

    /**
     * Excepción con error genérico.
     */
    public AccessDeniedException() {
        super("Access denied, it's not permitted access");
    }

    /**
     * @param message Message on user language
     */
    public AccessDeniedException(final String message) {
        super(message);
    }
}
