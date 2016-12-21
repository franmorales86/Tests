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
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.io.Serializable;

/**
 * @author raulanatol
 */
@Document(collection = "oauth_client_details")
public class OAuthClientDetails implements Serializable {
    private static final long serialVersionUID = -3435116337400059682L;

    @Id
    @Indexed(unique = true, sparse = true)
    @Field("client_id")
    private String clientId;
    @Field("resource_ids")
    private String resourceIds;
    @Field("client_secret")
    private String clientSecret;
    @Field("scope")
    private String scope;
    @Field("authorized_grant_types")
    private String authorizedGrantTypes;
    @Field("web_server_redirect_uri")
    private String webServerRedirectUri;
    @Field("authorities")
    private String authorities;
    @Field("access_token_validity")
    private Integer accessTokenValidity;
    @Field("refresh_token_validity")
    private Integer refreshTokenValidity;
    @Field("additional_information")
    private String additionalInformation;

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
     * @return the resourceIds
     */
    public String getResourceIds() {
        return this.resourceIds;
    }

    /**
     * @param resourceIds the resourceIds to set
     */
    public void setResourceIds(final String resourceIds) {
        this.resourceIds = resourceIds;
    }

    /**
     * @return the clientSecret
     */
    public String getClientSecret() {
        return this.clientSecret;
    }

    /**
     * @param clientSecret the clientSecret to set
     */
    public void setClientSecret(final String clientSecret) {
        this.clientSecret = clientSecret;
    }

    /**
     * @return the scope
     */
    public String getScope() {
        return this.scope;
    }

    /**
     * @param scope the scope to set
     */
    public void setScope(final String scope) {
        this.scope = scope;
    }

    /**
     * @return the authorizedGrantTypes
     */
    public String getAuthorizedGrantTypes() {
        return this.authorizedGrantTypes;
    }

    /**
     * @param authorizedGrantTypes the authorizedGrantTypes to set
     */
    public void setAuthorizedGrantTypes(final String authorizedGrantTypes) {
        this.authorizedGrantTypes = authorizedGrantTypes;
    }

    /**
     * @return the webServerRedirectUri
     */
    public String getWebServerRedirectUri() {
        return this.webServerRedirectUri;
    }

    /**
     * @param webServerRedirectUri the webServerRedirectUri to set
     */
    public void setWebServerRedirectUri(final String webServerRedirectUri) {
        this.webServerRedirectUri = webServerRedirectUri;
    }

    /**
     * @return the authorities
     */
    public String getAuthorities() {
        return this.authorities;
    }

    /**
     * @param authorities the authorities to set
     */
    public void setAuthorities(final String authorities) {
        this.authorities = authorities;
    }

    /**
     * @return the accessTokenValidity
     */
    public Integer getAccessTokenValidity() {
        return this.accessTokenValidity;
    }

    /**
     * @param accessTokenValidity the accessTokenValidity to set
     */
    public void setAccessTokenValidity(final Integer accessTokenValidity) {
        this.accessTokenValidity = accessTokenValidity;
    }

    /**
     * @return the refreshTokenValidity
     */
    public Integer getRefreshTokenValidity() {
        return this.refreshTokenValidity;
    }

    /**
     * @param refreshTokenValidity the refreshTokenValidity to set
     */
    public void setRefreshTokenValidity(final Integer refreshTokenValidity) {
        this.refreshTokenValidity = refreshTokenValidity;
    }

    /**
     * @return the additionalInformation
     */
    public String getAdditionalInformation() {
        return this.additionalInformation;
    }

    /**
     * @param additionalInformation the additionalInformation to set
     */
    public void setAdditionalInformation(final String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    /**
     * Obtenemos la query que nos devuelve la id única.
     * @return La consulta.
     */
    public Query getUniqueQuery() {
        return new Query(Criteria.where("clientId").is(this.clientId));
    }
}
