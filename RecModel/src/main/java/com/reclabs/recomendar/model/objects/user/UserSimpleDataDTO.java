/**
 * Project: RecModel
 * Created by: raulanatol at 15/09/13 18:37
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.model.objects.user;

import java.io.Serializable;

/**
 * @author raulanatol
 */
public class UserSimpleDataDTO implements Serializable {
    private static final long serialVersionUID = -7050496189353988862L;

    private String id;
    /**
     * El nombre del usuario (name + lastName...)
     */
    private String visibleName;
    /**
     * Número de recomendaciones realizadas por el usuario.
     */
    private Integer numberOfRec;
    /**
     * Indica si tiene una cuenta de twitter linkeada.
     */
    private Boolean twitterLinked;
    private String twitterUsername;
    /**
     * Indica si tiene una cuenta de facebook linkeada
     */
    private Boolean facebookLinked;
    private String facebookUsername;
    /**
     * El nombre de usuario de recomendar, identificativo y único en todo recomendar.
     */
    private String username;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVisibleName() {
        return visibleName;
    }

    public void setVisibleName(String visibleName) {
        this.visibleName = visibleName;
    }

    public Integer getNumberOfRec() {
        return numberOfRec;
    }

    public void setNumberOfRec(Integer numberOfRec) {
        this.numberOfRec = numberOfRec;
    }

    public Boolean getTwitterLinked() {
        return twitterLinked;
    }

    public void setTwitterLinked(Boolean twitterLinked) {
        this.twitterLinked = twitterLinked;
    }

    public Boolean getFacebookLinked() {
        return facebookLinked;
    }

    public void setFacebookLinked(Boolean facebookLinked) {
        this.facebookLinked = facebookLinked;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTwitterUsername() {
        return twitterUsername;
    }

    public void setTwitterUsername(String twitterUsername) {
        this.twitterUsername = twitterUsername;
    }

    public String getFacebookUsername() {
        return facebookUsername;
    }

    public void setFacebookUsername(String facebookUsername) {
        this.facebookUsername = facebookUsername;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserSimpleDataDTO)) return false;

        UserSimpleDataDTO that = (UserSimpleDataDTO) o;

        if (facebookLinked != null ? !facebookLinked.equals(that.facebookLinked) : that.facebookLinked != null)
            return false;
        if (facebookUsername != null ? !facebookUsername.equals(that.facebookUsername) : that.facebookUsername != null)
            return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (numberOfRec != null ? !numberOfRec.equals(that.numberOfRec) : that.numberOfRec != null) return false;
        if (twitterLinked != null ? !twitterLinked.equals(that.twitterLinked) : that.twitterLinked != null)
            return false;
        if (twitterUsername != null ? !twitterUsername.equals(that.twitterUsername) : that.twitterUsername != null)
            return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (visibleName != null ? !visibleName.equals(that.visibleName) : that.visibleName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (visibleName != null ? visibleName.hashCode() : 0);
        result = 31 * result + (numberOfRec != null ? numberOfRec.hashCode() : 0);
        result = 31 * result + (twitterLinked != null ? twitterLinked.hashCode() : 0);
        result = 31 * result + (twitterUsername != null ? twitterUsername.hashCode() : 0);
        result = 31 * result + (facebookLinked != null ? facebookLinked.hashCode() : 0);
        result = 31 * result + (facebookUsername != null ? facebookUsername.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        return result;
    }


    @Override
    public String toString() {
        return "UserSimpleDataDTO{" +
                "id='" + id + '\'' +
                ", visibleName='" + visibleName + '\'' +
                ", numberOfRec=" + numberOfRec +
                ", twitterLinked=" + twitterLinked +
                ", twitterUsername='" + twitterUsername + '\'' +
                ", facebookLinked=" + facebookLinked +
                ", facebookUsername='" + facebookUsername + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
