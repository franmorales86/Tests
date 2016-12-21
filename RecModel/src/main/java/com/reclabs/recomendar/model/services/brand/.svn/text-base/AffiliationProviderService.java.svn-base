/**
 * Project: RecModel
 * Created by: raulanatol at 10/07/13 12:21
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.model.services.brand;

import com.reclabs.recomendar.model.documents.brand.AffiliationProvider;
import com.reclabs.recomendar.model.documents.brand.data.AffiliationProviderData;

import java.util.List;

/**
 * @author raulanatol
 */
public interface AffiliationProviderService {
    /**
     * @return Gets all affiliations providers
     */
    List<AffiliationProvider> findAll();

    /**
     * Create a new affiliation provider
     * @return The affiliation providers
     */
    AffiliationProvider createAffiliationProvider(AffiliationProvider affiliationProvider);

    /**
     * Find an affiliation provider for a specified id
     * @param affiliationProviderId The if to find.
     * @return The affiliation provider found.
     */
    AffiliationProvider findAffiliationProviderById(String affiliationProviderId);

    /**
     * Gets a AffiliationProviderData from an affiliationProvider
     * @param affiliationProvider The affiliation provider
     * @return The affiliation provider data
     */
    AffiliationProviderData getAffiliationProviderData(AffiliationProvider affiliationProvider);

    /**
     * Delete an provider by the id.
     * @param providerId The id of the provider
     */
    void deleteById(String providerId);

    /**
     * Active the provider.
     * @param providerId The id of the provider.
     */
    void activeProvider(String providerId);
}
