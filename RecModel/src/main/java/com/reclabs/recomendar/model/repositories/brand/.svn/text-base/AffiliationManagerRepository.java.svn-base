/**
 * Project: RecModel
 * Created by: raulanatol at 10/07/13 12:24
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.model.repositories.brand;

import com.reclabs.recomendar.model.documents.brand.AffiliationManager;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author raulanatol
 */
public interface AffiliationManagerRepository extends MongoRepository<AffiliationManager, String> {
    List<AffiliationManager> getAffiliationManagerByProviderName(String providerName);

    List<AffiliationManager> getAffiliationManagerByBrandName(String brandName);

    /**
     * Gets the list of the affiliation manager that his domainURL are "domainURL"
     * @param domainURL The url to find
     * @return A list of affiliation manager
     */
    List<AffiliationManager> findByDomainURL(String domainURL);

    /**
     * @param alias Alias to find
     * @return The affiliation manager
     */
    AffiliationManager findByAlias(String alias);

    /**
     * Gets a list of affiliation manager with the brand equals that brandId
     * @param brandId The id of brand to search
     * @return List of affiliation managers
     */
    List<AffiliationManager> findByBrandId(String brandId);

    /**
     * Gets all affiliations manager that his affiliation provider id are providerId parameter
     * @param providerId The id of the affiliation provider
     * @return List of affiliation manager
     */
    List<AffiliationManager> findAffiliationManagerByProviderId(String providerId);
}
