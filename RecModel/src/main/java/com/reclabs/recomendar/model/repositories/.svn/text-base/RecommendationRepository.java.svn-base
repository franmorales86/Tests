/**
 * Project: RecModel
 * Created by: raulanatol at 02/05/13 16:27
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.model.repositories;

import com.reclabs.recomendar.model.documents.Recommendation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

/**
 * @author raulanatol
 */
public interface RecommendationRepository extends MongoRepository<Recommendation, String> {
    /**
     * Dado un userId obtenemos el listado de ids de items recomendados por ese usuario.
     * @param userId El id del usuario
     * @return El listado de items recomendado por el usuario.
     */
    List<String> findItemIdByUserId(String userId);

    /**
     * Find recommendation by userId and itemId
     * @param userId Id of user
     * @param itemId Id of item
     * @return The recommendation. NULL if not exist.
     */
    Recommendation findByUserIdAndItemId(String userId, String itemId);

    /**
     * Remove recommendation by userId and itemId
     * @param userId Id of user
     * @param itemId Id of item
     */
    void deleteByUserIdAndItemId(String userId, String itemId);

    /**
     * Delete all elements with the itemId are equals to parameter.
     * @param itemId The id of the item.
     */
    void deleteByItemId(String itemId);

    /**
     * Get the list of recommendations of an user into from a date
     * @param userId The id of the user
     * @param from The start of the period
     * @return The list of the recommendations
     */
    List<Recommendation> findRecommendationsByUserFromDate(String userId, Date from);
}
