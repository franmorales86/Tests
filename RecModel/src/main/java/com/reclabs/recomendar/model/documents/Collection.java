/**
 * Project: RecModel
 * Created by: fjmorales at 10/11/2012 10:35:21
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.model.documents;

import com.reclabs.recomendar.model.documents.users.User;
import com.reclabs.recomendar.model.types.CollectionType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

// import com.reclabs.recomendar.model.documents.product.ProductCollection;

/**
 * @author fjmorales
 *         Representa los datos de una coleccion de un usuario.
 */
@SuppressWarnings("WeakerAccess")
@Document
public class Collection implements Serializable {

    private static final long serialVersionUID = 1941647885713028616L;

    @Id
    private String id;
    /**
     * Nombre de la coleccion
     */
    @Indexed
    private String name;
    /**
     * Usuario tiene asociada la coleccion
     */
    @DBRef
    private User user;
    /**
     * Tipo de la coleccion (coleccion vacia, generica...)
     */
    private CollectionType type;
    /**
     * URL de la imagen de la coleccion
     */
    private String imageURL;

    /**
     * Listado de productos de la coleccion
     */
    // private List<ProductCollection> products;

    /**
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * @param id the id to set
     */
    public void setId(final String id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * @param name the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @return the user
     */
    public User getUser() {
        return this.user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(final User user) {
        this.user = user;
    }

    /**
     * @return the type
     */
    public CollectionType getType() {
        return this.type;
    }

    /**
     * @param type the type to set
     */
    public void setType(final CollectionType type) {
        this.type = type;
    }

    /**
     * @return the imageURL
     */
    public String getImageURL() {
        return this.imageURL;
    }

    /**
     * @param imageURL the imageURL to set
     */
    public void setImageURL(final String imageURL) {
        this.imageURL = imageURL;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((imageURL == null) ? 0 : imageURL.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        result = prime * result + ((user == null) ? 0 : user.hashCode());
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
        Collection other = (Collection) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (imageURL == null) {
            if (other.imageURL != null) {
                return false;
            }
        } else if (!imageURL.equals(other.imageURL)) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (type != other.type) {
            return false;
        }
        if (user == null) {
            if (other.user != null) {
                return false;
            }
        } else if (!user.equals(other.user)) {
            return false;
        }
        return true;
    }

}
