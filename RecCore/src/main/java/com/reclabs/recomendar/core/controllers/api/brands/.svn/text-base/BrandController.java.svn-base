/**
 * Project: RecCore
 * Created by: raulanatol at 10/07/13 18:06
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.controllers.api.brands;

import com.reclabs.recomendar.common.exceptions.RecIllegalArgumentException;
import com.reclabs.recomendar.common.helpers.ParameterHelper;
import com.reclabs.recomendar.common.helpers.types.StringHelper;
import com.reclabs.recomendar.model.documents.brand.AffiliationManager;
import com.reclabs.recomendar.model.documents.brand.AffiliationProvider;
import com.reclabs.recomendar.model.documents.brand.Brand;
import com.reclabs.recomendar.model.documents.brand.MarketPlaceManager;
import com.reclabs.recomendar.model.services.brand.AffiliationManagerService;
import com.reclabs.recomendar.model.services.brand.AffiliationProviderService;
import com.reclabs.recomendar.model.services.brand.BrandService;
import com.reclabs.recomendar.objects.ResponseDTO;
import org.apache.commons.lang.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author raulanatol
 */
@Controller
@RequestMapping(value = "/v1/brands/")
public class BrandController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BrandController.class);

    @Autowired
    private BrandService brandService;

    @Autowired
    private AffiliationProviderService affiliationProviderService;

    @Autowired
    private AffiliationManagerService affiliationManagerService;

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize("#oauth2.isUser() and #oauth2.clientHasRole('ROLE_SANDBOX') and hasAnyRole('ROLE_SANDBOX_ADMIN', 'ROLE_SANDBOX_USER') and #oauth2.hasScope('write')")
    public Brand create(@RequestBody Brand brand) {
        ParameterHelper.assertNull(brand);
        ParameterHelper.assertEmpty(brand.getName());
        ParameterHelper.assertTrue(StringHelper.containsAny(brand.getName(), "_", "-", " ", ".com"), "Invalid brand name");
        return brandService.createBrand(brand);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("#oauth2.isUser() and #oauth2.clientHasRole('ROLE_SANDBOX') and hasAnyRole('ROLE_SANDBOX_ADMIN', 'ROLE_SANDBOX_USER') and #oauth2.hasScope('write')")
    public List<Brand> findAll() {
        return brandService.findAll();
    }

    /**
     * @param brandId Id of the brand
     * @return the brand with the indicate id.
     */
    @ResponseBody
    @RequestMapping(value = "/{brandId}", method = RequestMethod.GET)
    public Brand findById(@PathVariable String brandId) {
        ParameterHelper.assertEmpty(brandId);
        return brandService.findBrandById(brandId);
    }

    @ResponseBody
    @RequestMapping(value = "/disable/{brandId}", method = RequestMethod.POST)
    @PreAuthorize("#oauth2.isUser() and #oauth2.clientHasRole('ROLE_SANDBOX') and hasAnyRole('ROLE_SANDBOX_ADMIN', 'ROLE_SANDBOX_USER') and #oauth2.hasScope('write')")
    public void disableBrand(@PathVariable String brandId) {
        ParameterHelper.assertEmpty(brandId);
        throw new NotImplementedException();
//        brandService.disableBrandById(brandId);
    }

    @ResponseBody
    @RequestMapping(value = "/addMarketPlace/{brandId}", method = RequestMethod.POST)
    public void addMarketPlace(@PathVariable String brandId, @RequestBody MarketPlaceManager marketPlaceManager) {
        throw new NotImplementedException();
//        ParameterHelper.assertEmpty(brandId);
//        ParameterHelper.assertNull(marketPlaceManager);
//        brandService.addMarketPlace(brandId, marketPlaceManager);
    }

    @ResponseBody
    @RequestMapping(value = "/addAffiliation/{brandId}/{affiliationProviderId}", method = RequestMethod.POST)
    public ResponseDTO addAffiliationManager(@PathVariable String brandId, @PathVariable String affiliationProviderId, @RequestBody AffiliationManager affiliationManager) {
        ParameterHelper.assertNull(affiliationManager);
        ParameterHelper.assertNull(affiliationManager.getVendorRegionType());
        ParameterHelper.assertAnyEmpty(brandId, affiliationProviderId, affiliationManager.getAlias(), affiliationManager.getDomain(), affiliationManager.getPattern());
        AffiliationManager duplicatedAffiliationManager = affiliationManagerService.findAffiliationManagerByAlias(affiliationManager.getAlias());
        if (duplicatedAffiliationManager != null) {
            LOGGER.warn("[Trying to create an affiliation manager with duplicated alias: {}]", affiliationManager.getAlias());
            throw new RecIllegalArgumentException("Alias already exists");
        }
        AffiliationProvider affiliationProvider = affiliationProviderService.findAffiliationProviderById(affiliationProviderId);
        ParameterHelper.assertNull(affiliationProvider);
        Brand brand = brandService.findBrandById(brandId);
        ParameterHelper.assertNull(brand);
        affiliationManagerService.createAndLinkAffiliationManager(brand, affiliationProvider, affiliationManager);
        return ResponseDTO.OK;
    }

    @ResponseBody
    @RequestMapping(value = "{brandId}/affiliationManagers", method = RequestMethod.GET)
    public List<AffiliationManager> getAffiliationManagers(@PathVariable String brandId) {
        ParameterHelper.assertEmpty(brandId);
        return affiliationManagerService.findAffiliationManagerByBrandId(brandId);
    }

    @ResponseBody
    @RequestMapping(value = "/deleteAffiliation/{brandId}/{affiliationProviderId}", method = RequestMethod.POST)
    public void deleteAffiliationManager(@PathVariable String brandId, @PathVariable String affiliationProviderId) {
        ParameterHelper.assertAnyEmpty(brandId, affiliationProviderId);

    }
}
