/**
 * Project: RecModel
 * Created by: raulanatol at 03/11/13 11:22
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.model.services.user;

import com.reclabs.recomendar.model.documents.statistics.Karma;

/**
 * @author raulanatol
 */
public interface KarmaService {
    /**
     * Gets the karma from a specified userId
     * @param userId The userId
     * @return the karma of this user or null
     */
    Karma getKarmaByUserId(String userId);
}
