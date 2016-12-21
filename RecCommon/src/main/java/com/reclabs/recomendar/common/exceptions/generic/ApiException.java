/**
 * Project: RecCommon
 * Created by: fjmorales at 06/12/2012 16:53:42
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.common.exceptions.generic;

import com.reclabs.recomendar.common.types.CodeErrorType;

/**
 * Error con excepción devuelta por la API de Recomendar. Todos los errores
 * controlados de la API deben ser devueltos mediante este tipo de excepciones.
 * Las excepciones de usuario (@link UserException) solo se usaran a nivel interno
 * dentro de la propia API.
 * @author fjmorales
 */
public class ApiException extends RuntimeException {
    private static final long serialVersionUID = -8443302736234919955L;
    /**
     * El código de la excepción
     */
    private final CodeErrorType code;

    /**
     * Constructor de la excepción en la que se le pasa el mensaje del usuario,
     * la causa de la excepción y el código de error.
     * @param code Código de error de la excepción
     * @param apiMessage Mensaje de error para el usuario
     * @param cause Excepción lanzada
     */
    public ApiException(final CodeErrorType code, final String apiMessage, final Throwable cause) {
        super(apiMessage, cause);
        this.code = code;
    }

    /**
     * Constructor en la que se pasa el mensaje hacia el usuario y el código de error.
     * @param code Código de error de la excepción
     * @param apiMessage Mensaje de error para el usuario
     */
    public ApiException(final CodeErrorType code, final String apiMessage) {
        super(apiMessage);
        this.code = code;
    }

    /**
     * Constructor de la excepción en la que se le pasa el código de error
     * y la causa de la excepción, ya que el mensaje de error lo obtiene por
     * defecto del código de error.
     * @param code Código de error de la excepción
     * @param cause Excepción lanzada
     */
    public ApiException(final CodeErrorType code, final Throwable cause) {
        super(code.getDefaultMessage(), cause);
        this.code = code;
    }

    /**
     * Constructor en la que se pasa el código de error, ya que el mensaje
     * de error lo obtiene por defecto del código de error.
     * @param code Código de error de la excepción
     */
    public ApiException(final CodeErrorType code) {
        super(code.getDefaultMessage());
        this.code = code;
    }

    /**
     * Getter del código de error
     * @return the code
     */
    public int getCode() {
        return this.code.getCode();
    }
}
