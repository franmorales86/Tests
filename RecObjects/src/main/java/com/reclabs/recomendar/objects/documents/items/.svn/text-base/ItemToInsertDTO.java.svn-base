/**
 * Project: RecObjects
 * Created by: fjmorales at 03/04/2013 09:57:51
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.objects.documents.items;

import java.io.Serializable;
import java.util.List;

/**
 * Datos de los nuevos items a insertar
 * @author fjmorales
 */
public class ItemToInsertDTO implements Serializable {
    private static final long serialVersionUID = 7573695724809045522L;

    /**
     * Nombre del item.
     */
    private String name;
    /**
     * Descripcion del producto
     */
    private String description;
    /**
     * Listado de todas las imágenes que tiene el producto.
     */
    private List<String> imagesURL;
    /**
     * Número de identificación europea, a modo código de barras. Sirve para comparar productos en el futuro.
     * <b>Ejemplo:</b> "3700225608322"
     * En caso de que la tenga.
     */
    private String ean;
    /**
     * Identificado isbn del producto.
     * En caso de que la tenga.
     */
    private String isbn;
    /**
     * Listado de etiquetas asociados al producto
     */
    private List<String> tags;
    /**
     * Identificador de la tienda a la que se va a añadir.
     * Si no hay tienda asociada un null funciona.
     */
    private String shopId;
    /**
     * El precio del producto en la tienda indicada.
     * En caso de no tener tienda no puede haber precio.
     */
    private String price;
    /**
     * Categoria del producto. Es un campo obligatorio
     */
    private String category;

    /**
     * La url de acceso del item a la página origen.
     */
    private String itemURL;

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

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the imagesURL
     */
    public List<String> getImagesURL() {
        return imagesURL;
    }

    /**
     * @param imagesURL the imagesURL to set
     */
    public void setImagesURL(List<String> imagesURL) {
        this.imagesURL = imagesURL;
    }

    /**
     * @return the ean
     */
    public String getEan() {
        return ean;
    }

    /**
     * @param ean the ean to set
     */
    public void setEan(String ean) {
        this.ean = ean;
    }

    /**
     * @return the isbn
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * @param isbn the isbn to set
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * @return the tags
     */
    public List<String> getTags() {
        return tags;
    }

    /**
     * @param tags the tags to set
     */
    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    /**
     * @return the shopId
     */
    public String getShopId() {
        return shopId;
    }

    /**
     * @param shopId the shopId to set
     */
    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    /**
     * @return the price
     */
    public String getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((category == null) ? 0 : category.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((ean == null) ? 0 : ean.hashCode());
        result = prime * result + ((imagesURL == null) ? 0 : imagesURL.hashCode());
        result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
        result = prime * result + ((itemURL == null) ? 0 : itemURL.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((price == null) ? 0 : price.hashCode());
        result = prime * result + ((shopId == null) ? 0 : shopId.hashCode());
        result = prime * result + ((tags == null) ? 0 : tags.hashCode());
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
        ItemToInsertDTO other = (ItemToInsertDTO) obj;
        if (category == null) {
            if (other.category != null)
                return false;
        } else if (!category.equals(other.category))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (ean == null) {
            if (other.ean != null)
                return false;
        } else if (!ean.equals(other.ean))
            return false;
        if (imagesURL == null) {
            if (other.imagesURL != null)
                return false;
        } else if (!imagesURL.equals(other.imagesURL))
            return false;
        if (isbn == null) {
            if (other.isbn != null)
                return false;
        } else if (!isbn.equals(other.isbn))
            return false;
        if (itemURL == null) {
            if (other.itemURL != null)
                return false;
        } else if (!itemURL.equals(other.itemURL))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (price == null) {
            if (other.price != null)
                return false;
        } else if (!price.equals(other.price))
            return false;
        if (shopId == null) {
            if (other.shopId != null)
                return false;
        } else if (!shopId.equals(other.shopId))
            return false;
        if (tags == null) {
            if (other.tags != null)
                return false;
        } else if (!tags.equals(other.tags))
            return false;
        return true;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ItemToInsertDTO [name=" + name + ", description=" + description + ", imagesURL=" + imagesURL + ", ean=" + ean + ", isbn=" + isbn + ", tags=" + tags + ", shopId=" + shopId + ", price=" + price + ", category=" + category + ", itemURL="
                + itemURL + "]";
    }

    /**
     * @return the itemURL
     */
    public String getItemURL() {
        return itemURL;
    }

    /**
     * @param itemURL the itemURL to set
     */
    public void setItemURL(String itemURL) {
        this.itemURL = itemURL;
    }

}
