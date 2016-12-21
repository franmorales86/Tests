/**
 * Project: RecModel
 * Created by: raulanatol at 15/11/2012 19:36:18
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.model.documents.users;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @author raulanatol
 */
@Document
public class SocialUser implements Serializable {

    private static final long serialVersionUID = 2138168538248106020L;

    @Id
    private String userId;
    /**
     * Id del proveedor del servicio social.
     * Twitter, Facebook...
     */
    @Indexed
    private String providerId;
    /**
     * La id que tiene el usuario en ese providerId
     */
    @Indexed
    private String providerUserId;
    /**
     * El nombre por el que se le conoce en el providerId
     */
    private String displayName;
    /**
     * Token de acceso al servicio
     */
    private String accessToken;
    /**
     * Secret para el servicio
     */
    private String secret;
    /**
     * Token de refresco de tokens...
     */
    private String refreshToken;
    /**
     * Tiempo de vida del token
     */
    private Long expireTime;

    /**
     * True if the autoShared on this social network is enabled, false otherwise
     */
    private Boolean autoShared;

    /**
     * @return the userId
     */
    public String getUserId() {
        return this.userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(final String userId) {
        this.userId = userId;
    }

    /**
     * @return the providerId
     */
    public String getProviderId() {
        return this.providerId;
    }

    /**
     * @param providerId the providerId to set
     */
    public void setProviderId(final String providerId) {
        this.providerId = providerId;
    }

    /**
     * @return the providerUserId
     */
    public String getProviderUserId() {
        return this.providerUserId;
    }

    /**
     * @param providerUserId the providerUserId to set
     */
    public void setProviderUserId(final String providerUserId) {
        this.providerUserId = providerUserId;
    }

    /**
     * @return the displayName
     */
    public String getDisplayName() {
        return this.displayName;
    }

    /**
     * @param displayName the displayName to set
     */
    public void setDisplayName(final String displayName) {
        this.displayName = displayName;
    }

    /**
     * @return the accessToken
     */
    public String getAccessToken() {
        return this.accessToken;
    }

    /**
     * @param accessToken the accessToken to set
     */
    public void setAccessToken(final String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * @return the secret
     */
    public String getSecret() {
        return this.secret;
    }

    /**
     * @param secret the secret to set
     */
    public void setSecret(final String secret) {
        this.secret = secret;
    }

    /**
     * @return the refreshToken
     */
    public String getRefreshToken() {
        return this.refreshToken;
    }

    /**
     * @param refreshToken the refreshToken to set
     */
    public void setRefreshToken(final String refreshToken) {
        this.refreshToken = refreshToken;
    }

    /**
     * @return the expireTime
     */
    public Long getExpireTime() {
        return this.expireTime;
    }

    /**
     * @param expireTime the expireTime to set
     */
    public void setExpireTime(final Long expireTime) {
        this.expireTime = expireTime;
    }

    public Boolean getAutoShared() {
        return autoShared;
    }

    public void setAutoShared(Boolean autoShared) {
        this.autoShared = autoShared;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SocialUser)) return false;

        SocialUser that = (SocialUser) o;

        if (accessToken != null ? !accessToken.equals(that.accessToken) : that.accessToken != null) return false;
        if (autoShared != null ? !autoShared.equals(that.autoShared) : that.autoShared != null) return false;
        if (displayName != null ? !displayName.equals(that.displayName) : that.displayName != null) return false;
        if (expireTime != null ? !expireTime.equals(that.expireTime) : that.expireTime != null) return false;
        if (providerId != null ? !providerId.equals(that.providerId) : that.providerId != null) return false;
        if (providerUserId != null ? !providerUserId.equals(that.providerUserId) : that.providerUserId != null) return false;
        if (refreshToken != null ? !refreshToken.equals(that.refreshToken) : that.refreshToken != null) return false;
        if (secret != null ? !secret.equals(that.secret) : that.secret != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;

        return true;
    }

    @Override
    public String toString() {
        return "SocialUser{" +
                "userId='" + userId + '\'' +
                ", providerId='" + providerId + '\'' +
                ", providerUserId='" + providerUserId + '\'' +
                ", displayName='" + displayName + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", secret='" + secret + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", expireTime=" + expireTime +
                ", autoShared=" + autoShared +
                '}';
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (providerId != null ? providerId.hashCode() : 0);
        result = 31 * result + (providerUserId != null ? providerUserId.hashCode() : 0);
        result = 31 * result + (displayName != null ? displayName.hashCode() : 0);
        result = 31 * result + (accessToken != null ? accessToken.hashCode() : 0);
        result = 31 * result + (secret != null ? secret.hashCode() : 0);
        result = 31 * result + (refreshToken != null ? refreshToken.hashCode() : 0);
        result = 31 * result + (expireTime != null ? expireTime.hashCode() : 0);
        result = 31 * result + (autoShared != null ? autoShared.hashCode() : 0);
        return result;
    }
}
