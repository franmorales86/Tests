/**
 * Project: RecObjects
 * Created by: fjmorales at 04/04/2013 17:50:53
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.objects.documents.category;

import java.io.Serializable;

/**
 * @author fjmorales
 */
public class CategoryTagsDTO implements Serializable {

    private static final long serialVersionUID = 5982821633785575989L;

    private String tagId;

    private String tagName;

    private long numberOfProducts;

    /**
     * @return the tagName
     */
    public String getTagName() {
        return tagName;
    }

    /**
     * @param tagName the tagName to set
     */
    public void setTagName(String tagName) {
        this.tagName = tagName;
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

    /**
     * @return the tagId
     */
    public String getTagId() {
        return tagId;
    }

    /**
     * @param tagId the tagId to set
     */
    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (numberOfProducts ^ (numberOfProducts >>> 32));
        result = prime * result + ((tagId == null) ? 0 : tagId.hashCode());
        result = prime * result + ((tagName == null) ? 0 : tagName.hashCode());
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
        CategoryTagsDTO other = (CategoryTagsDTO) obj;
        if (numberOfProducts != other.numberOfProducts) {
            return false;
        }
        if (tagId == null) {
            if (other.tagId != null) {
                return false;
            }
        } else if (!tagId.equals(other.tagId)) {
            return false;
        }
        if (tagName == null) {
            if (other.tagName != null) {
                return false;
            }
        } else if (!tagName.equals(other.tagName)) {
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
        return "CategoryTagsDTO [tagId=" + tagId + ", tagName=" + tagName + ", numberOfProducts=" + numberOfProducts + "]";
    }

}
