/**
 * Project: RecObjects
 * Created by: raulanatol at 05/04/2013 10:45:16
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.objects.documents.items;

import java.io.Serializable;

/**
 * @author raulanatol
 */
public class ItemDTO implements Serializable {
    private static final long serialVersionUID = -6159354492719936039L;

    /**
     * La url de la imagen principal del item.
     */
    private String urlImagenItem;
    /**
     * La url de la imagen del usuario que ha recomendado por primera vez el item.
     */
    private String urlImagenUsuario;
    /**
     * El nombre del item.
     */
    private String name;
    /**
     * EL identificador del item en la base de datos.
     */
    private String itemId;

    /**
     * @return the urlImagenItem
     */
    public String getUrlImagenItem() {
        return urlImagenItem;
    }

    /**
     * @param urlImagenItem the urlImagenItem to set
     */
    public void setUrlImagenItem(String urlImagenItem) {
        this.urlImagenItem = urlImagenItem;
    }

    /**
     * @return the urlImagenUsuario
     */
    public String getUrlImagenUsuario() {
        return urlImagenUsuario;
    }

    /**
     * @param urlImagenUsuario the urlImagenUsuario to set
     */
    public void setUrlImagenUsuario(String urlImagenUsuario) {
        this.urlImagenUsuario = urlImagenUsuario;
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

    /**
     * @return the itemId
     */
    public String getItemId() {
        return itemId;
    }

    /**
     * @param itemId the itemId to set
     */
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((itemId == null) ? 0 : itemId.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((urlImagenItem == null) ? 0 : urlImagenItem.hashCode());
        result = prime * result + ((urlImagenUsuario == null) ? 0 : urlImagenUsuario.hashCode());
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
        ItemDTO other = (ItemDTO) obj;
        if (itemId == null) {
            if (other.itemId != null)
                return false;
        } else if (!itemId.equals(other.itemId))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (urlImagenItem == null) {
            if (other.urlImagenItem != null)
                return false;
        } else if (!urlImagenItem.equals(other.urlImagenItem))
            return false;
        if (urlImagenUsuario == null) {
            if (other.urlImagenUsuario != null)
                return false;
        } else if (!urlImagenUsuario.equals(other.urlImagenUsuario))
            return false;
        return true;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ItemDTO [urlImagenItem=" + urlImagenItem + ", urlImagenUsuario=" + urlImagenUsuario + ", name=" + name + ", itemId=" + itemId + "]";
    }
}
