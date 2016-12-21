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

import com.reclabs.recomendar.model.documents.security.OAuthRefreshToken;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author raulanatol
 */
public interface OAuthRefreshTokenRepository extends MongoRepository<OAuthRefreshToken, String> {

    /**
     * Dado un tokenId borramos el accessToken.
     * @param tokenId The token ID
     */
    void deleteByTokenId(String tokenId);

    /**
     * Dado un tokenId obtenemos el document {@link OAuthRefreshToken}
     * @param tokenId The token ID
     * @return El document encontrado.
     */
    OAuthRefreshToken findByTokenId(String tokenId);
}
