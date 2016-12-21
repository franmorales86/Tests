/**
 * Project: RecCommon
 * Created by: fjmorales at 06/12/2012 16:57:21
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.common.types;

/**
 * @author fjmorales
 */
public enum CodeErrorType {
    // @formatter:off
    /**
     * En caso de que no se suministren correctamente los parámetros.
     */
    ILLEGAL_PARAMETERS(20, "Parámetros suministrados incorrectamente"),
    /**
     * Productos no cargados correctamente
     */
    PRODUCT_NOT_LOAD(21, "Alguno de los productos no se ha cargado correctamente."),
    /**
     * Producto no encontrado
     */
    PRODUCT_NOT_FOUND(22, "Producto no encontrado."),
    /**
     * Page not found
     */
    NOT_FOUND(404, "Página no encontrada."),
    /**
     * Error interno
     */
    INTERNAL_ERROR(500, "Error interno de la aplicación."),
    /**
     * Indica que no se disponen de los permisos necesarios para realizar dicha acción.
     */
    PERMISSION_DENIED(600, "Permission denied"),

    ///--------------------------------///
    //     CATEGORIAS ///
    ///--------------------------------///
    /**
     * La categoría buscada no existe.
     */
    CATEGORY_NOT_FOUND(23, "Category not found"),

    ///--------------------------------///
    //     USUARIOS  1XXX              ///
    ///--------------------------------///

    /**
     * Usuario duplicado en la base de datos
     */
    DUPLICATE_USER(24, "Usuario duplicado."),
    /**
     * Usuario no existente en la base de datos
     */
    USER_NOT_FOUND(25, "Usuario no existente"),

    USER_NO_HAVE_MORE_RECOMMENDATION_ACTION(126, "User no have more recommendation action"),

    ///--------------------------------///
    //     ITEMS	 ///
    ///--------------------------------///

    /**
     * Item ya existente
     */
    ITEM_EXISTS(26, "El Item ya se encuentra insertado."),

    /**
     * Item no encontrado.
     */
    ITEM_NOT_FOUND(27, "El item no se ha encontrado."),

    ///--------------------------------///
    //     TAGS	 ///
    ///--------------------------------///

    /**
     * Tag no existente
     */
    TAG_NOT_FOUND(37, "Etiqueta no existente."),


    ///--------------------------------///
    //     MISC
    ///--------------------------------///

    /**
     * Error provocado por Barney en la creaación del URL acortada, cuando se ha sobrepasado el número de reintentos.
     */
    ILEGAL_SHORT_CODE_CREATION(31, "Short url creation fail");


    // @formatter:on

    /**
     * Codigo de error
     */
    private int code;

    /**
     * Mensaje de error por defecto
     */
    private String defaultMessage;

    private CodeErrorType(final int code, final String defaultMessage) {
        this.code = code;
        this.defaultMessage = defaultMessage;
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
     * @return the defaultMessage
     */
    public String getDefaultMessage() {
        return this.defaultMessage;
    }

    /**
     * @param defaultMessage the defaultMessage to set
     */
    public void setDefaultMessage(final String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }

}
