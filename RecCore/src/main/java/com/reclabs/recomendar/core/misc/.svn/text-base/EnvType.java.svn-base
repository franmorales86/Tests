/**
 * Project: RecCore
 * Created by: raulanatol at 11/11/2012 00:51:31
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.misc;

/**
 * Representan los nombres de los diferentes entornos.
 * @author raulanatol
 */
public enum EnvType {
    // @formatter:off
    /**
     * Entorno desconocido.
     */
    UNK("dev"),
    /**
     * Entorno local o de pruebas
     */
    DEV("dev"),
    /**
     * Entorno de preproducción
     */
    PRE("pre"),
    /**
     * Entorno de producción.
     */
    PRO("pro");
    // @formatter:on

    private final String code;

    /**
     * @param code
     */
    private EnvType(String code) {
        this.code = code;
    }

    /**
     * Dado el valor de una posible variable de entorno la obtenemos.
     * @param value El valor de la variable de entorno.
     * @return La variable de entorno no UNKNOWN en caso de no encontrar la correcta.
     */
    public static EnvType fromValue(String value) {
        switch (value) {
            case "DEV":
                return DEV;
            case "PRE":
                return PRE;
            case "PRO":
            case "\"PRO\"":
                return PRO;
            default:
                return UNK;
        }
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }
}
