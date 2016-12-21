/**
 * Project: RecModel
 * Created by: raulanatol at 29/10/13 19:14
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.model.documents.statistics;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * @author raulanatol
 */
@Document
public class ShortURLItemClick implements Serializable {
    private static final long serialVersionUID = -4242789761100220826L;

    private String id;
    private String itemId;
    private String ownerId;
    private Date clickDate;
    private String userIp;

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

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public Date getClickDate() {
        return clickDate;
    }

    public void setClickDate(Date clickDate) {
        this.clickDate = clickDate;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShortURLItemClick)) return false;

        ShortURLItemClick that = (ShortURLItemClick) o;

        if (clickDate != null ? !clickDate.equals(that.clickDate) : that.clickDate != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (itemId != null ? !itemId.equals(that.itemId) : that.itemId != null) return false;
        if (ownerId != null ? !ownerId.equals(that.ownerId) : that.ownerId != null) return false;
        if (userIp != null ? !userIp.equals(that.userIp) : that.userIp != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (itemId != null ? itemId.hashCode() : 0);
        result = 31 * result + (ownerId != null ? ownerId.hashCode() : 0);
        result = 31 * result + (clickDate != null ? clickDate.hashCode() : 0);
        result = 31 * result + (userIp != null ? userIp.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ShortURLItemClick{" +
                "id='" + id + '\'' +
                ", itemId='" + itemId + '\'' +
                ", ownerId='" + ownerId + '\'' +
                ", clickDate=" + clickDate +
                ", userIp='" + userIp + '\'' +
                '}';
    }
}
