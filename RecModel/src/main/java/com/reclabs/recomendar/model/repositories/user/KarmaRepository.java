/**
 * Project: RecModel
 * Created by: raulanatol at 03/11/13 11:24
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.model.repositories.user;

import com.reclabs.recomendar.model.documents.statistics.Karma;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author raulanatol
 */
public interface KarmaRepository extends MongoRepository<Karma, String> {

}
