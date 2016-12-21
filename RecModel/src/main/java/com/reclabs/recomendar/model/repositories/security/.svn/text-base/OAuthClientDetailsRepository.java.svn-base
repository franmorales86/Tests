/**
 * Project: RecModel
 * Created by: raulanatol at 30/12/2012 19:37:56
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.model.repositories.security;

import com.reclabs.recomendar.model.documents.security.OAuthClientDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author raulanatol
 */
public interface OAuthClientDetailsRepository extends MongoRepository<OAuthClientDetails, String> {
    /**
     * Actualizamos el secret del {@link OAuthClientDetails} cuyo clientId corresponde con el pasado por
     * parámetros.
     * @param clientId
     * @param clientSecrect
     */
    void updateClientSecret(String clientId, String clientSecrect);

    /**
     * Dado el clientId borramos todos los elementos cuyo clientId correspondienten al pasado por parámetro.
     * @param clientId
     */
    void deleteByClientId(String clientId);

    /**
     * Dado un clientId obtenemos el {@link OAuthClientDetails} con ese clienteId
     * @param clientId
     * @return El clienteId
     */
    OAuthClientDetails findByClientId(String clientId);
}
