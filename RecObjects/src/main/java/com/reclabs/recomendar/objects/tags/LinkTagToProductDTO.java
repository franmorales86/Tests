/**
 * Project: RecObjects
 * Created by: fjmorales at 17/03/2013 17:47:51
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.objects.tags;

import java.io.Serializable;
import java.util.List;

/**
 * Representa los datos de entrada necesarios para realizar el etiquetado de un listado de productos
 * @author fjmorales
 */
public class LinkTagToProductDTO implements Serializable {

    private static final long serialVersionUID = 7649390412289852851L;

    /**
     * Listado de etiquetas a añadir
     */
    private List<String> tagsName;
    /**
     * Listado de productos a etiquetar
     */
    private List<String> productsIds;

    /**
     * @return the tagsName
     */
    public List<String> getTagsName() {
        return tagsName;
    }

    /**
     * @param tagsName the tagsName to set
     */
    public void setTagsName(final List<String> tagsName) {
        this.tagsName = tagsName;
    }

    /**
     * @return the productsIds
     */
    public List<String> getProductsIds() {
        return productsIds;
    }

    /**
     * @param productsIds the productsIds to set
     */
    public void setProductsIds(final List<String> productsIds) {
        this.productsIds = productsIds;
    }

}
