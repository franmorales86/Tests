/**
 * Project: RecModel
 * Created by: raulanatol at 10/07/13 12:25
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.model.repositories.brand;

import com.reclabs.recomendar.model.documents.brand.Brand;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author raulanatol
 */
public interface BrandRepository extends MongoRepository<Brand, String> {

    /**
     * Gets all brands that any of your affiliation manager domain are the same of the domain parameter
     * @param domain The domain to search
     * @return List of brands
     */
    List<Brand> findByAffiliationDomain(String domain);

    /**
     * Gets the brand that name are brandName
     * @param brandName The brand name to find.
     * @return The brand found
     */
    Brand findByName(String brandName);
}
