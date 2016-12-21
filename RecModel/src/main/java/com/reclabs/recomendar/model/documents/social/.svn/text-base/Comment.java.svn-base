/**
 * Project: RecModel
 * Created by: raulanatol at 22/10/13 15:06
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.model.documents.social;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * @author raulanatol
 */
@Document
public class Comment implements Serializable {
    private static final long serialVersionUID = -4585540171760280894L;
    /**
     * Id of the comment
     */
    private String id;
    /**
     * The id of the user who commented
     */
    private String userId;
    /**
     * The id of the item commented
     */
    private String itemId;
    /**
     * Text of the comment
     */
    private String comment;
    /**
     * Date of creation
     */
    private Date creationDate;
    /**
     * value of the abuse. > 0 comment is marked like abuse
     */
    private Integer abuse;
    /**
     * username of the owner
     */
    private String ownerName;

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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getAbuse() {
        return abuse;
    }

    public void setAbuse(Integer abuse) {
        this.abuse = abuse;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;

        Comment comment1 = (Comment) o;

        if (abuse != null ? !abuse.equals(comment1.abuse) : comment1.abuse != null) return false;
        if (comment != null ? !comment.equals(comment1.comment) : comment1.comment != null) return false;
        if (creationDate != null ? !creationDate.equals(comment1.creationDate) : comment1.creationDate != null)
            return false;
        if (id != null ? !id.equals(comment1.id) : comment1.id != null) return false;
        if (itemId != null ? !itemId.equals(comment1.itemId) : comment1.itemId != null) return false;
        if (ownerName != null ? !ownerName.equals(comment1.ownerName) : comment1.ownerName != null) return false;
        return !(userId != null ? !userId.equals(comment1.userId) : comment1.userId != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (itemId != null ? itemId.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + (abuse != null ? abuse.hashCode() : 0);
        result = 31 * result + (ownerName != null ? ownerName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", itemId='" + itemId + '\'' +
                ", comment='" + comment + '\'' +
                ", creationDate=" + creationDate +
                ", abuse=" + abuse +
                ", ownerName='" + ownerName + '\'' +
                '}';
    }
}
