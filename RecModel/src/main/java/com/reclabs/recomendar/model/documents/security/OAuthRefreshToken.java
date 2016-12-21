/**
 * Project: RecModel
 * Created by: raulanatol at 24/12/2012 17:34:35
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.model.documents.security;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * @author raulanatol
 */
@Document(collection = "oauth_refresh_token")
public class OAuthRefreshToken implements Serializable {
    private static final long serialVersionUID = 1076460883458863120L;

    @Id
    @Field("token_id")
    private String tokenId;

    @Field("token")
    private byte[] token;

    @Field("authentication")
    private byte[] authentication;

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
}
