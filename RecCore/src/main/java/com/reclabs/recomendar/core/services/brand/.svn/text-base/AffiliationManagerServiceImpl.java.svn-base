/**
 * Project: RecCore
 * Created by: raulanatol at 17/07/13 16:56
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.services.brand;

import com.reclabs.recomendar.common.exceptions.RecIllegalArgumentException;
import com.reclabs.recomendar.common.helpers.types.CollectionHelper;
import com.reclabs.recomendar.common.helpers.types.StringHelper;
import com.reclabs.recomendar.core.helpers.AffiliationHelper;
import com.reclabs.recomendar.model.documents.brand.AffiliationManager;
import com.reclabs.recomendar.model.documents.brand.AffiliationProvider;
import com.reclabs.recomendar.model.documents.brand.Brand;
import com.reclabs.recomendar.model.repositories.brand.AffiliationManagerRepository;
import com.reclabs.recomendar.model.services.brand.AffiliationManagerService;
import com.reclabs.recomendar.model.services.brand.AffiliationProviderService;
import com.reclabs.recomendar.model.services.brand.BrandService;
import com.reclabs.recomendar.model.services.items.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author raulanatol
 */
@Service
public class AffiliationManagerServiceImpl implements AffiliationManagerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AffiliationManagerServiceImpl.class);

    @Autowired
    private AffiliationManagerRepository affiliationManagerRepository;

    @Autowired
    private AffiliationProviderService affiliationProviderService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private ItemService itemService;

    @Override
    public List<AffiliationManager> findAffiliationManagerByItemInformationURL(String informationURL) {
        List<AffiliationManager> result;
        if (StringHelper.isEmpty(informationURL)) {
            result = new ArrayList<>();
        } else {
            try {
                String domain = AffiliationHelper.getDomainFromItemInformationURL(informationURL);
                if (!StringHelper.isEmpty(domain)) {
                    result = affiliationManagerRepository.findByDomainURL(domain);
                    if (CollectionHelper.isEmpty(result)) {
                        LOGGER.warn("[Not found affiliationManager for the domain: {}]", domain);
                    }
                } else {
                    result = new ArrayList<>();
                    LOGGER.warn("[Not found an specified domain from informationURL: {}]", informationURL);
                }
            } catch (RecIllegalArgumentException e) {
                LOGGER.error("Impossible to obtain the domain of the informationURL: {}", informationURL, e);
                result = new ArrayList<>();
            }
        }
        return result;
    }

    @Override
    public void createAndLinkAffiliationManager(Brand brand, AffiliationProvider affiliationProvider, AffiliationManager affiliationManagerToCreate) {
        LOGGER.info("[Starting process to add affiliation manager: {} to a brand with id {}]", affiliationManagerToCreate.getAlias(), brand.getId());
        if (isDuplicateLink(brand, affiliationManagerToCreate)) {
            LOGGER.warn("[Trying to append a affiliation manager that already exist. Alias = {}]", affiliationManagerToCreate.getAlias());
        } else {
            affiliationManagerToCreate.setAffiliationProviderData(affiliationProviderService.getAffiliationProviderData(affiliationProvider));
            affiliationManagerToCreate.setBrandData(brandService.getBrandData(brand));
            AffiliationManager newAffiliationManager = affiliationManagerRepository.save(affiliationManagerToCreate);
            itemService.enableOrAddAffiliationManagerInItems(newAffiliationManager);
        }
    }

    @Override
    public boolean isDuplicateLink(Brand brand, AffiliationManager affiliationManager) {
        boolean result = false;
        if (StringHelper.isEmpty(affiliationManager.getAlias())) {
            LOGGER.warn("[Trying to find affiliationManager with empty alias]");
            result = true;
        } else {
            AffiliationManager existAffiliationManager = affiliationManagerRepository.findByAlias(affiliationManager.getAlias());
            if (existAffiliationManager != null && StringHelper.equals(brand.getId(), existAffiliationManager.getBrandData().getId())) {
                LOGGER.warn("[Trying to verify isDuplicateLink and found the same affiliationManager relationship]");
                result = true;
            }
        }
        return result;
    }

    @Override
    public List<AffiliationManager> findAffiliationManagerByBrandId(String brandId) {
        return affiliationManagerRepository.findByBrandId(brandId);
    }

    @Override
    public AffiliationManager findAffiliationManagerByAlias(String alias) {
        return affiliationManagerRepository.findByAlias(alias);
    }

    @Override
    public List<AffiliationManager> findAffiliationManagerByProviderId(String providerId) {
        return affiliationManagerRepository.findAffiliationManagerByProviderId(providerId);
    }
}