/**
 * Project: RecModel
 * Created by: raulanatol at 04/04/2013 14:24:49
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.model.types.items;

/**
 * Listado de tipos de imagenes guardadas en el CDN.
 * @author raulanatol
 */
public enum ItemImageType {
    /**
     * La imagen en el formato original de la fuente.
     */
    ORIGINAL("original"),
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

    /**
     * @param name
     */
    private ItemImageType(final String name) {
        this.name = name;
    }

    /**
     * @return the name
     */
    public String getName() {
        return this.name;
    }
}
