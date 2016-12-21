/**
 * Project: RecSandbox
 * Created by: fjmorales at 24/06/2013 13:50:12
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.model.objects.items;

import com.reclabs.recomendar.model.documents.items.Item;
import com.reclabs.recomendar.model.objects.tags.TagOrderDTO;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author fjmorales
 */
@SuppressWarnings("WeakerAccess")
public class ListItemsTagsOrdered implements Serializable {
    private static final long serialVersionUID = -4826149214856709211L;

    private Collection<Item> items;

    private Collection<TagOrderDTO> tags;

    /**
     * @return items
     */
    public Collection<Item> getItems() {
        return items;
    }

    /**
     * @param items New items
     */
    public void setItems(Collection<Item> items) {
        this.items = items;
    }

    /**
     * @return tags
     */
    public Collection<TagOrderDTO> getTags() {
        return tags;
    }

    /**
     * @param tags New tags
     */
    public void setTags(Collection<TagOrderDTO> tags) {
        this.tags = tags;
    }

    @SuppressWarnings("RedundantIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ListItemsTagsOrdered)) return false;

        ListItemsTagsOrdered that = (ListItemsTagsOrdered) o;

        if (items != null ? !items.equals(that.items) : that.items != null) return false;
        if (tags != null ? !tags.equals(that.tags) : that.tags != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = items != null ? items.hashCode() : 0;
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ListItemsTagsOrdered{" +
                "items=" + items +
                ", tags=" + tags +
                '}';
    }
}
