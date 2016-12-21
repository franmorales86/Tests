/**
 * Project: RecObjects
 * Created by: raulanatol at 05/04/2013 18:24:20
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.objects;

import java.io.Serializable;

/**
 * @author raulanatol
 */
public class CommentDTO implements Serializable {
    private static final long serialVersionUID = -4390342885574434530L;
    /**
     * Identificación del comentario.
     */
    private String id;
    /**
     * Id del usuario que realiza el comentario.
     */
    private String ownerId;
    /**
     * Texto del comentario.
     */
    private String text;
    /**
     * Imagen del propietario del comentario.
     */
    private String ownerImageURL;

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
     * @return the ownerId
     */
    public String getOwnerId() {
        return ownerId;
    }

    /**
     * @param ownerId the ownerId to set
     */
    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return the ownerImageURL
     */
    public String getOwnerImageURL() {
        return ownerImageURL;
    }

    /**
     * @param ownerImageURL the ownerImageURL to set
     */
    public void setOwnerImageURL(String ownerImageURL) {
        this.ownerImageURL = ownerImageURL;
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
        result = prime * result + ((ownerId == null) ? 0 : ownerId.hashCode());
        result = prime * result + ((ownerImageURL == null) ? 0 : ownerImageURL.hashCode());
        result = prime * result + ((text == null) ? 0 : text.hashCode());
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
        CommentDTO other = (CommentDTO) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (ownerId == null) {
            if (other.ownerId != null)
                return false;
        } else if (!ownerId.equals(other.ownerId))
            return false;
        if (ownerImageURL == null) {
            if (other.ownerImageURL != null)
                return false;
        } else if (!ownerImageURL.equals(other.ownerImageURL))
            return false;
        if (text == null) {
            if (other.text != null)
                return false;
        } else if (!text.equals(other.text))
            return false;
        return true;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "CommentDTO [id=" + id + ", ownerId=" + ownerId + ", text=" + text + ", ownerImageURL=" + ownerImageURL + "]";
    }
}
