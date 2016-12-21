/**
 * Project: RecModel
 * Created by: raulanatol at 29/12/2012 20:43:35
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.model.documents.security;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author raulanatol
 */
@Document(collection = "oauth_access_token")
public class OAuthAccessToken implements Serializable {
    private static final long serialVersionUID = 2851420172638067474L;

    @Id
    private String id;

    @Field("token_id")
    private String tokenId;

    @Field("token")
    private byte[] token;

    @Field("authentication_id")
    private String authenticationId;

    @Field("user_name")
    private String username;

    @Field("client_id")
    private String clientId;

    @Field("authentication")
    private byte[] authentication;

    @Field("refresh_token")
    private String refreshToken;

    /**
     * @return the tokenId
     */
    public String getTokenId() {
        return this.tokenId;
    }

    /**
     * @param tokenId the tokenId to set
     */
    public void setTokenId(final String tokenId) {
        this.tokenId = tokenId;
    }

    /**
     * @return the authenticationId
     */
    public String getAuthenticationId() {
        return this.authenticationId;
    }

    /**
     * @param authenticationId the authenticationId to set
     */
    public void setAuthenticationId(final String authenticationId) {
        this.authenticationId = authenticationId;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(final String username) {
        this.username = username;
    }

    /**
     * @return the clientId
     */
    public String getClientId() {
        return this.clientId;
    }

    /**
     * @param clientId the clientId to set
     */
    public void setClientId(final String clientId) {
        this.clientId = clientId;
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
     * @return the token
     */
    public byte[] getToken() {
        return this.token;
    }

    /**
     * @param token the token to set
     */
    public void setToken(final byte[] token) {
        this.token = token;
    }

    /**
     * @return the authentication
     */
    public byte[] getAuthentication() {
        return this.authentication;
    }

    /**
     * @param authentication the authentication to set
     */
    public void setAuthentication(final byte[] authentication) {
        this.authentication = authentication;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
