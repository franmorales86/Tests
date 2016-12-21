/**
 * Project: RecESDriver
 * Created by: raulanatol at 27/04/13 16:44
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 * <p/>
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.esdriver.exceptions;

import com.reclabs.recomendar.common.exceptions.generic.ErrorException;

public class ESCommunicationException extends ErrorException {

    /**
     * @param userMessage
     * @param exception
     */
    public ESCommunicationException(String userMessage, Exception exception) {
        super(userMessage, exception);
    }
}
