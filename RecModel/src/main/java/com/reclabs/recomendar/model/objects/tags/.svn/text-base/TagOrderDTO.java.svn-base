/**
 * Project: RecModel
 * Created by: Fran at 24/06/13 16:52
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.model.objects.tags;

import com.reclabs.recomendar.model.documents.items.ItemTag;

import java.io.Serializable;

/**
 * @author Fran
 */
//FIXME en deshuso??
public class TagOrderDTO implements Serializable {
    private static final long serialVersionUID = -7216481079964147476L;

    private ItemTag tag;

    private Long numberOfItems;

    public ItemTag getTag() {
        return tag;
    }

    public void setTag(ItemTag tag) {
        this.tag = tag;
    }

    public Long getNumberOfItems() {
        return numberOfItems;
    }

    public void setNumberOfItems(Long numberOfItems) {
        this.numberOfItems = numberOfItems;
    }

    @SuppressWarnings("RedundantIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TagOrderDTO)) return false;

        TagOrderDTO that = (TagOrderDTO) o;

        if (numberOfItems != null ? !numberOfItems.equals(that.numberOfItems) : that.numberOfItems != null)
            return false;
        if (tag != null ? !tag.equals(that.tag) : that.tag != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tag != null ? tag.hashCode() : 0;
        result = 31 * result + (numberOfItems != null ? numberOfItems.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TagOrderDTO{" +
                "tag=" + tag +
                ", numberOfItems=" + numberOfItems +
                '}';
    }
}

