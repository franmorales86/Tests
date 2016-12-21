/**
 * Project: RecCore
 * Created by: raulanatol at 10/07/13 12:27
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.services.brand;

import com.reclabs.recomendar.common.exceptions.RecIllegalArgumentException;
import com.reclabs.recomendar.common.helpers.types.CollectionHelper;
import com.reclabs.recomendar.model.documents.brand.AffiliationManager;
import com.reclabs.recomendar.model.documents.brand.AffiliationProvider;
import com.reclabs.recomendar.model.documents.brand.data.AffiliationProviderData;
import com.reclabs.recomendar.model.repositories.brand.AffiliationProviderRepository;
import com.reclabs.recomendar.model.services.brand.AffiliationManagerService;
import com.reclabs.recomendar.model.services.brand.AffiliationProviderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author raulanatol
 */
@Service
public class AffiliationProviderServiceImpl implements AffiliationProviderService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AffiliationProviderServiceImpl.class);

    @Autowired
    private AffiliationProviderRepository affiliationProviderRepository;

    @Autowired
    private AffiliationManagerService affiliationManagerService;


    @Override
    public List<AffiliationProvider> findAll() {
        return affiliationProviderRepository.findAll();
    }

    @Override
    public AffiliationProvider createAffiliationProvider(AffiliationProvider affiliationProvider) {
        LOGGER.info("[Starting process to create an affiliation provider with name: {}]", affiliationProvider.getName());
        AffiliationProvider existAffiliationProvider = affiliationProviderRepository.findByName(affiliationProvider.getName());
        if (existAffiliationProvider != null) {
            throw new RecIllegalArgumentException("The provider name is duplicated");
        }
        return affiliationProviderRepository.save(affiliationProvider);
    }

    @Override
    public AffiliationProvider findAffiliationProviderById(String affiliationProviderId) {
        return affiliationProviderRepository.findOne(affiliationProviderId);
    }

    @Override
    public AffiliationProviderData getAffiliationProviderData(AffiliationProvider affiliationProvider) {
        AffiliationProviderData result = new AffiliationProviderData();
        result.setId(affiliationProvider.getId());
        result.setName(affiliationProvider.getName());
        result.setEnabled(affiliationProvider.getEnabled());
        return result;
    }

    @Override
    public void deleteById(String providerId) {
        LOGGER.info("[Starting process to delete an provider with id: {}]", providerId);
        AffiliationProvider affiliationProvider = affiliationProviderRepository.findOne(providerId);
        if (affiliationProvider == null) {
            throw new RecIllegalArgumentException("[Trying to delete an provider with invalid id: {}]", providerId);
        }
        List<AffiliationManager> affiliationManagers = affiliationManagerService.findAffiliationManagerByProviderId(providerId);
        if (!CollectionHelper.isEmpty(affiliationManagers)) {
            LOGGER.warn("[Trying to delete an affiliation provider id: {} with children]", providerId);
            throw new RecIllegalArgumentException("The affiliation provider has children");
        }
        affiliationProviderRepository.delete(providerId);
    }

    @Override
    public void activeProvider(String providerId) {
        AffiliationProvider affiliationProvider = affiliationProviderRepository.findOne(providerId);
        if (affiliationProvider == null) {
            throw new RecIllegalArgumentException("[Trying to active an provider with invalid id: {}]", providerId);
        }
        if (affiliationProvider.getEnabled()) {
            throw new RecIllegalArgumentException("[Trying to active an active provider id: {}]", providerId);
        }
        //FIXME active the provider.
    }
}
