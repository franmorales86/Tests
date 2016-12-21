/**
 * Project: RecModel
 * Created by: raulanatol at 10/07/13 12:21
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.model.repositories.brand;

import com.reclabs.recomendar.model.documents.brand.AffiliationProvider;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author raulanatol
 */
public interface AffiliationProviderRepository extends MongoRepository<AffiliationProvider, String> {

    AffiliationProvider findByName(String affiliationProviderName);
}
