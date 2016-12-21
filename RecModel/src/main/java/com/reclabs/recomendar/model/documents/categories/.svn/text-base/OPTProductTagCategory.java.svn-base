/**
 * Project: RecModel
 * Created by: fjmorales at 04/04/2013 14:48:03
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.model.documents.categories;

import com.reclabs.recomendar.model.documents.Category;
import com.reclabs.recomendar.model.documents.Tag;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * Representa la tabla para optimizar la obtención del numero de productos asociados a una categoria agrupados por
 * tags. Esta tabla se crea para realizar las búsquedas de forma óptima y sus datos deben coincidir con los
 * obtenidos mediante la relación entre los productos, los tags y las categorias
 * @author fjmorales
 */
@Document
public class OPTProductTagCategory implements Serializable {

    private static final long serialVersionUID = -4796949376323166876L;

    /**
     * Identificador de la relacion categoria con etiqueta
     */
    @Id
    private String id;

    @DBRef
    private Category category;

    @DBRef
    private Tag tag;

    private long numberOfProducts;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * @return the tag
     */
    public Tag getTag() {
        return tag;
    }

    /**
     * @param tag the tag to set
     */
    public void setTag(Tag tag) {
        this.tag = tag;
    }

    /**
     * @return the numberOfProducts
     */
    public long getNumberOfProducts() {
        return numberOfProducts;
    }

    /**
     * @param numberOfProducts the numberOfProducts to set
     */
    public void setNumberOfProducts(long numberOfProducts) {
        this.numberOfProducts = numberOfProducts;
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
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + (int) (numberOfProducts ^ (numberOfProducts >>> 32));
        result = prime * result + ((tag == null) ? 0 : tag.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        OPTProductTagCategory other = (OPTProductTagCategory) obj;
        if (category == null) {
            if (other.category != null) {
                return false;
            }
        } else if (!category.equals(other.category)) {
            return false;
        }
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (numberOfProducts != other.numberOfProducts) {
            return false;
        }
        if (tag == null) {
            if (other.tag != null) {
                return false;
            }
        } else if (!tag.equals(other.tag)) {
            return false;
        }
        return true;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "OPTProductTagCategory [id=" + id + ", category=" + category + ", tag=" + tag + ", numberOfProducts=" + numberOfProducts + "]";
    }

}
