/**
 * Project: RecModel
 * Created by: raulanatol at 29/12/2012 21:24:57
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.model.repositories.security;

import com.reclabs.recomendar.model.documents.security.OAuthAccessToken;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author raulanatol
 */
public interface OAuthAccessTokenRepository extends MongoRepository<OAuthAccessToken, String> {

    /**
     * Dado un tokenId borramos el accessToken.
     * @param tokenId TokenId to delete.
     */
    void deleteByTokenId(String tokenId);

    /**
     * Dado un refresh token id borramos el access token que lo enlace.
     * @param refreshTokenId Refresh token id to delete
     */
    void deleteByRefreshTokenId(String refreshTokenId);

    /**
     * Obtenemos el {@link OAuthAccessToken} que tenga el authenticationId pasado por parámetros.
     * @param authenticationId Id of the authentication
     * @return El authenticationId
     */
    OAuthAccessToken findByAuthenticationId(String authenticationId);

    /**
     * Dado un token id obtenemos el token access que lo represente.
     * @param tokenId Id of the token
     * @return El AccessToken
     */
    OAuthAccessToken findByTokenId(String tokenId);

    /**
     * @param userName Username of the user
     * @return Listado de {@link OAuthAccessToken} con usuario el indicado por parámetros.
     */
    List<OAuthAccessToken> findByUsername(String userName);

    /**
     * Obtenemos el listado de {@link OAuthAccessToken} que tengan por clientId el pasado por parámetros.
     * @param clientId ClientId of the user
     * @return el listado de {@link OAuthAccessToken}
     */
    List<OAuthAccessToken> findByClientId(String clientId);

    /**
     * Delete all OauthAccessTokens with user_name is the parameter
     * @param username the username
     */
    void deleteByUsername(String username);
}
