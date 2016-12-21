/**
 * Project: RecESDriver
 * Created by: fjmorales at 04/04/2013 12:35:50
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.esdriver.index;

/**
 * Representa a cada uno de los diferentes tipos de índices que dispone elasticsearch.
 * @author fjmorales
 */
public enum IndexType {
    /**
     * Índice para la búsqueda de items
     */
    ITEM_INDEX("itemindex"),
    /**
     * Index of users
     */
    USER_INDEX("userindex"),
    /**
     * Índice para la búsqueda de tags.
     */
    TAG_INDEX("tagindex"),
    /**
     * Índice para la búsqueda de recomendaciones e items asociados.
     */
    RECITEM_INDEX("recitemindex"),
    /**
     * Index from search recommendations and users.
     */
    RECUSER_INDEX("recuserindex"),
    /**
     * Index from search items that want a user.
     */
    ITEMWANT_INDEX("itemwantindex"),
    /**
     * Index from search items that have a user.
     */
    ITEMHAVE_INDEX("itemhaveindex"),
    /**
     * Index from search items that are a vip item.
     */
    ITEMVIP_INDEX("itemsvipindex");

    /**
     * El nombre del índice tal y como aparece en ES
     */
    private String name;

    /**
     * @param name
     */
    private IndexType(String name) {
        this.name = name;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

}
