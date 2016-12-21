/**
 * Project: RecModel
 * Created by: Fran at 2/01/14 12:19
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
 * Represents collection of items VIP
 * @author Fran
 */
@Document
public class ItemVIPCollection implements Serializable {

    private static final long serialVersionUID = 3122347012604906143L;

    /**
     * Id of item collection
     */
    @Id
    private String id;

    /**
     * Id of item
     */
    private String itemId;

    /**
     * Date when item was VIP
     */
    private Date vipDate;

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

    public Date getVipDate() {
        return vipDate;
    }

    public void setVipDate(Date vipDate) {
        this.vipDate = vipDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemVIPCollection)) return false;

        ItemVIPCollection that = (ItemVIPCollection) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (itemId != null ? !itemId.equals(that.itemId) : that.itemId != null) return false;
        if (vipDate != null ? !vipDate.equals(that.vipDate) : that.vipDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (itemId != null ? itemId.hashCode() : 0);
        result = 31 * result + (vipDate != null ? vipDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ItemVIPCollection{" +
                "id='" + id + '\'' +
                ", itemId='" + itemId + '\'' +
                ", vipDate=" + vipDate +
                '}';
    }
}
