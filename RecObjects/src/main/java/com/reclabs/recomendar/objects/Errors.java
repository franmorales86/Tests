/**
 * Project: RecObjects
 * Created by: fjmorales at 24/02/2013 15:26:20
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.objects;

import java.io.Serializable;

/**
 * Clase utilizada para la recepcion de errores del Core
 * @author fjmorales
 */
public class Errors implements Serializable {

    private static final long serialVersionUID = -1691921105161589100L;

    private ResponseDTO errors;

    /**
     * @return the errors
     */
    public ResponseDTO getErrors() {
        return errors;
    }

    /**
     * @param errors the errors to set
     */
    public void setErrors(final ResponseDTO errors) {
        this.errors = errors;
    }

}
