/**
 * Project: RecModel
 * Created by: raulanatol at 21/11/2012 19:20:07
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.model.types.products;

/**
 * Representan los diferentes tipos de imágenes que puede tener un producto
 * @author raulanatol
 */
public enum ProductImageType {
    /**
     *
     */
    SMALL("small"),
    /**
     *
     */
    MEDIUM("medium"),
    /**
     *
     */
    LARGE("large");

    /**
     * El nombre del subdirectorio dentro del S3 de productos de la imagen con el formato de imagen dado.
     * Ejemplo: El producto con id: 1235 y tipo SMALL estará ubicado en "products/small/1235"
     */
    private final String name;

    private ProductImageType(final String name) {
        this.name = name;
    }

    /**
     * @return the name
     */
    public String getName() {
        return this.name;
    }
}
