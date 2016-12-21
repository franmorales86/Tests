/**
 * Project: RecObjects
 * Created by: fjmorales at 05/04/2013 12:23:53
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.objects.documents.communities;

import com.reclabs.recomendar.objects.documents.items.ItemDTO;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author fjmorales
 */
public class CommunityDTO implements Serializable {

    private static final long serialVersionUID = 636623377585439419L;

    private String id;
    private String name;
    private long numberOfFollowers;
    private Collection<ItemDTO> item;

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
     * @return the numberOfFollowers
     */
    public long getNumberOfFollowers() {
        return numberOfFollowers;
    }

    /**
     * @param numberOfFollowers the numberOfFollowers to set
     */
    public void setNumberOfFollowers(long numberOfFollowers) {
        this.numberOfFollowers = numberOfFollowers;
    }

    /**
     * @return the item
     */
    public Collection<ItemDTO> getItem() {
        return item;
    }

    /**
     * @param item the item to set
     */
    public void setItem(Collection<ItemDTO> item) {
        this.item = item;
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
        result = prime * result + ((item == null) ? 0 : item.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + (int) (numberOfFollowers ^ (numberOfFollowers >>> 32));
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
        CommunityDTO other = (CommunityDTO) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (item == null) {
            if (other.item != null) {
                return false;
            }
        } else if (!item.equals(other.item)) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (numberOfFollowers != other.numberOfFollowers) {
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
        return "CommunityDTO [id=" + id + ", name=" + name + ", numberOfFollowers=" + numberOfFollowers + ", item=" + item + "]";
    }

}
