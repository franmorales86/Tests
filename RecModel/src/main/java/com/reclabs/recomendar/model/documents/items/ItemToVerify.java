/**
 * Project: RecModel
 * Created by: raulanatol at 11/06/13 17:41
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.model.documents.items;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author raulanatol
 */
@Document
public class ItemToVerify implements Serializable {
    private static final long serialVersionUID = -875040997482666254L;
    /**
     *
     */
    @Id
    private String id;
    /**
     * Id of the item.
     */
    private String itemId;
    /**
     * Id of the user.
     */
    private String userId;
    /**
     * URL source of the item.
     * Can be the web of the company of the shops or the web of the product page in the shops.
     */
    private String currentWeb;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCurrentWeb() {
        return currentWeb;
    }

    public void setCurrentWeb(String currentWeb) {
        this.currentWeb = currentWeb;
    }

    @SuppressWarnings("RedundantIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemToVerify)) return false;

        ItemToVerify that = (ItemToVerify) o;

        if (currentWeb != null ? !currentWeb.equals(that.currentWeb) : that.currentWeb != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (itemId != null ? !itemId.equals(that.itemId) : that.itemId != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (itemId != null ? itemId.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (currentWeb != null ? currentWeb.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ItemToVerify{" +
                "id='" + id + '\'' +
                ", itemId='" + itemId + '\'' +
                ", userId='" + userId + '\'' +
                ", currentWeb='" + currentWeb + '\'' +
                '}';
    }
}
