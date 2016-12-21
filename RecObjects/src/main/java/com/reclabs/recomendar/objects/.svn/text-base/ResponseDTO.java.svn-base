/**
 * Project: RecObjects
 * Created by: raulanatol at 27/11/2012 19:04:54
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.objects;

import java.io.Serializable;

/**
 * Respuesta simple de un servicio json
 * @author raulanatol
 */
public class ResponseDTO implements Serializable {
    private static final long serialVersionUID = 1184742190805080068L;
    /**
     * Respuesta correcta de un servicio JSON
     */
    public static final ResponseDTO OK = new ResponseDTO(200, "Ok");
    /**
     * Respuesta de un TRUE de un servicio JSON
     */
    public static final ResponseDTO TRUE = new ResponseDTO(200, "true");
    /**
     * Respuesta de un FALSE de un servicio JSON
     */
    public static final ResponseDTO FALSE = new ResponseDTO(200, "false");
    /**
     * El código de la respuesta
     */
    private int code;
    /**
     * El mensaje de la respusta.
     */
    private String message;

    /**
     * Constructor por defecto del ResposeDTO
     */
    public ResponseDTO() {
        super();
    }

    /**
     * @param code
     * @param message
     */
    public ResponseDTO(final int code, final String message) {
        super();
        this.code = code;
        this.message = message;
    }

    /**
     * @return the code
     */
    public int getCode() {
        return this.code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(final int code) {
        this.code = code;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(final String message) {
        this.message = message;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + code;
        result = prime * result + ((message == null) ? 0 : message.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ResponseDTO other = (ResponseDTO) obj;
        if (code != other.code)
            return false;
        if (message == null) {
            if (other.message != null)
                return false;
        } else if (!message.equals(other.message))
            return false;
        return true;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ResponseDTO [code=" + code + ", message=" + message + "]";
    }
}
