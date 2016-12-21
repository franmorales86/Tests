/**
 * Project: RecObjects
 * Created by: raulanatol at 13/02/2013 13:25:37
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.objects.users;

/**
 * Representan los datos de la información social de un usuario.
 * @author raulanatol
 */
public class SocialUserDTO {
    /**
     * Identificador del usuario en la red social indicada.
     */
    private String providerUserId;

    /**
     * @return the providerUserId
     */
    public String getProviderUserId() {
        return providerUserId;
    }

    /**
     * @param providerUserId the providerUserId to set
     */
    public void setProviderUserId(String providerUserId) {
        this.providerUserId = providerUserId;
    }
}
