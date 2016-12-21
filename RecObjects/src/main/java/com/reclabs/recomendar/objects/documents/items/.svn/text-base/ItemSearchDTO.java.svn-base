/**
 * Project: RecObjects
 * Created by: raulanatol at 23/03/2013 13:08:45
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.objects.documents.items;

/**
 * Items resultados de una búsqueda.
 * @author raulanatol
 */
public class ItemSearchDTO {
    private String name;
    private Integer numberRecommends;
    private String avatarURL;

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
     * @return the numberRecommends
     */
    public Integer getNumberRecommends() {
        return numberRecommends;
    }

    /**
     * @param numberRecommends the numberRecommends to set
     */
    public void setNumberRecommends(Integer numberRecommends) {
        this.numberRecommends = numberRecommends;
    }

    /**
     * @return the avatarURL
     */
    public String getAvatarURL() {
        return avatarURL;
    }

    /**
     * @param avatarURL the avatarURL to set
     */
    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((avatarURL == null) ? 0 : avatarURL.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((numberRecommends == null) ? 0 : numberRecommends.hashCode());
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
        ItemSearchDTO other = (ItemSearchDTO) obj;
        if (avatarURL == null) {
            if (other.avatarURL != null)
                return false;
        } else if (!avatarURL.equals(other.avatarURL))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (numberRecommends == null) {
            if (other.numberRecommends != null)
                return false;
        } else if (!numberRecommends.equals(other.numberRecommends))
            return false;
        return true;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ItemSearchDTO [name=" + name + ", numberRecommends=" + numberRecommends + ", avatarURL=" + avatarURL + "]";
    }
}
