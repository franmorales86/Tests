/**
 * Project: RecModel
 * Created by: raulanatol at 17/07/13 16:40
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.model.services.brand;

import com.reclabs.recomendar.model.documents.brand.AffiliationManager;
import com.reclabs.recomendar.model.documents.brand.AffiliationProvider;
import com.reclabs.recomendar.model.documents.brand.Brand;

import java.util.List;

/**
 * @author raulanatol
 */
public interface AffiliationManagerService {
    /**
     * Get a list of affiliation managers that can be affiliate the current information url of the item.
     * @param informationURL The url of the item in the domain of the affiliation.
     * @return List of possible affiliation managers.
     */
    List<AffiliationManager> findAffiliationManagerByItemInformationURL(String informationURL);

    /**
     * Create and link the affiliation manager passed trough parameters and link with the rest of elements.
     * @param brand The brand to link
     * @param affiliationProvider The affiliation provider to link
     * @param affiliationManagerToCreate The affiliation manager to create
     */
    void createAndLinkAffiliationManager(Brand brand, AffiliationProvider affiliationProvider, AffiliationManager affiliationManagerToCreate);

    /**
     * Verify if the link between brand and affiliationManager already exist.
     * @param brand The brand
     * @param affiliationManager The new affiliationManager
     * @return true if the duplicated link exist
     */
    boolean isDuplicateLink(Brand brand, AffiliationManager affiliationManager);

    /**
     * Gets all elements from a brandId
     * @param brandId The id of the brand
     * @return List of affiliation managers
     */
    List<AffiliationManager> findAffiliationManagerByBrandId(String brandId);

    /**
     * Get an affiliation manager from a specified alias.
     * @param alias The alias of the affiliation manager
     * @return The affiliation manager
     */
    AffiliationManager findAffiliationManagerByAlias(String alias);

    /**
     * Gets all affiliations manager that his affiliation provider id are providerId parameter
     * @param providerId The id of the affiliation provider
     * @return List of affiliation manager
     */
    List<AffiliationManager> findAffiliationManagerByProviderId(String providerId);
}
