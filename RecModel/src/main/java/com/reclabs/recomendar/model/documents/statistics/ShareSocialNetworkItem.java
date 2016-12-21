/**
 * Project: RecModel
 * Created by: raulanatol at 30/10/13 09:56
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
public class ShareSocialNetworkItem implements Serializable {
    private static final long serialVersionUID = -1018441876741922770L;

    private String id;
    /**
     * Owner of the share
     */
    private String ownerId;
    /**
     * Date of event creation.
     */
    private Date eventDate;

    private String itemId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShareSocialNetworkItem)) return false;

        ShareSocialNetworkItem that = (ShareSocialNetworkItem) o;

        if (eventDate != null ? !eventDate.equals(that.eventDate) : that.eventDate != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (itemId != null ? !itemId.equals(that.itemId) : that.itemId != null) return false;
        if (ownerId != null ? !ownerId.equals(that.ownerId) : that.ownerId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (ownerId != null ? ownerId.hashCode() : 0);
        result = 31 * result + (eventDate != null ? eventDate.hashCode() : 0);
        result = 31 * result + (itemId != null ? itemId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ShareSocialNetworkItem{" +
                "id='" + id + '\'' +
                ", ownerId='" + ownerId + '\'' +
                ", eventDate=" + eventDate +
                ", itemId='" + itemId + '\'' +
                '}';
    }

}
