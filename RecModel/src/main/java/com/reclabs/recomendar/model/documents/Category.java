/**
 * Project: RecModel
 * Created by: raulanatol at 22/03/2013 10:29:44
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.model.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * Representa una categoría de un producto.
 * Puede ser categoría padre -> si parent == null
 * Y subCategoría -> si parent != null
 * @author raulanatol
 */
@Document
public class Category implements Serializable {
    private static final long serialVersionUID = -539649735286195021L;

    /**
     * Identificador de la categoría.
     */
    @Id
    private String id;
    /**
     * El nombre de la categoría.
     */
    @Indexed
    private String name;
    /**
     * La descripción de la categoría.
     */
    private String description;
    /**
     * El padre de la categoría.
     * Si == null -> Categoría padre
     * Si != null -> Subcategoría.
     */
    @DBRef
    private Category parent;
    /**
     * La url de la imagen principal de la categoría
     */
    private String mainImageURL;
    /**
     * La url de la imagen en miniatura de la categoría
     */
    private String thumbnailImageURL;
    /**
     * La imagen de la cabecera de la categoría.
     */
    private String headImageURL;

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
     * @return the parent
     */
    public Category getParent() {
        return parent;
    }

    /**
     * @param parent the parent to set
     */
    public void setParent(Category parent) {
        this.parent = parent;
    }

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
     * @return the mainImageURL
     */
    public String getMainImageURL() {
        return mainImageURL;
    }

    /**
     * @param mainImageURL the mainImageURL to set
     */
    public void setMainImageURL(String mainImageURL) {
        this.mainImageURL = mainImageURL;
    }

    /**
     * @return the thumbnailImageURL
     */
    public String getThumbnailImageURL() {
        return thumbnailImageURL;
    }

    /**
     * @param thumbnailImageURL the thumbnailImageURL to set
     */
    public void setThumbnailImageURL(String thumbnailImageURL) {
        this.thumbnailImageURL = thumbnailImageURL;
    }

    /**
     * @return the headImageURL
     */
    public String getHeadImageURL() {
        return headImageURL;
    }

    /**
     * @param headImageURL the headImageURL to set
     */
    public void setHeadImageURL(String headImageURL) {
        this.headImageURL = headImageURL;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((headImageURL == null) ? 0 : headImageURL.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((mainImageURL == null) ? 0 : mainImageURL.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((parent == null) ? 0 : parent.hashCode());
        result = prime * result + ((thumbnailImageURL == null) ? 0 : thumbnailImageURL.hashCode());
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
        Category other = (Category) obj;
        if (description == null) {
            if (other.description != null) {
                return false;
            }
        } else if (!description.equals(other.description)) {
            return false;
        }
        if (headImageURL == null) {
            if (other.headImageURL != null) {
                return false;
            }
        } else if (!headImageURL.equals(other.headImageURL)) {
            return false;
        }
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (mainImageURL == null) {
            if (other.mainImageURL != null) {
                return false;
            }
        } else if (!mainImageURL.equals(other.mainImageURL)) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (parent == null) {
            if (other.parent != null) {
                return false;
            }
        } else if (!parent.equals(other.parent)) {
            return false;
        }
        if (thumbnailImageURL == null) {
            if (other.thumbnailImageURL != null) {
                return false;
            }
        } else if (!thumbnailImageURL.equals(other.thumbnailImageURL)) {
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
        return "Category [id=" + id + ", name=" + name + ", description=" + description + ", parent=" + parent + ", mainImageURL=" + mainImageURL + ", thumbnailImageURL=" + thumbnailImageURL + ", headImageURL=" + headImageURL + "]";
    }

}
