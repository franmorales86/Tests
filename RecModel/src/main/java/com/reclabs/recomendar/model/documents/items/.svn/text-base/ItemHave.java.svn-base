/**
 * Project: RecModel
 * Created by: raulanatol at 02/05/13 15:37
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.model.documents.items;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * Represents a item that a user have
 * @author fjmorales
 */
@Document
public class ItemHave implements Serializable {
    private static final long serialVersionUID = 5122697760004252684L;
    /**
     * Id of ItemHave
     */
    @Id
    private String id;
    /**
     * Id of user that have the item
     */
    private String userId;
    /**
     * Id of item
     */
    private String itemId;
    /**
     * Date of creation of relation
     */
    private Date createdDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @SuppressWarnings("RedundantIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemHave)) return false;

        ItemHave itemHave = (ItemHave) o;

        if (createdDate != null ? !createdDate.equals(itemHave.createdDate) : itemHave.createdDate != null)
            return false;
        if (id != null ? !id.equals(itemHave.id) : itemHave.id != null) return false;
        if (itemId != null ? !itemId.equals(itemHave.itemId) : itemHave.itemId != null) return false;
        if (userId != null ? !userId.equals(itemHave.userId) : itemHave.userId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (itemId != null ? itemId.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ItemHave{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", itemId='" + itemId + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }
}
