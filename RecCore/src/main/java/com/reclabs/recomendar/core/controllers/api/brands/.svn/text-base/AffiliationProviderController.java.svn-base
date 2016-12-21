/**
 * Project: RecCore
 * Created by: raulanatol at 10/07/13 12:14
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.controllers.api.brands;

import com.reclabs.recomendar.common.helpers.ParameterHelper;
import com.reclabs.recomendar.model.documents.brand.AffiliationProvider;
import com.reclabs.recomendar.model.services.brand.AffiliationProviderService;
import com.reclabs.recomendar.objects.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author raulanatol
 */
@Controller
@RequestMapping(value = "/v1/providers")
public class AffiliationProviderController {

    @Autowired
    private AffiliationProviderService affiliationProviderService;

    /**
     * @return all shops on Recomendar.
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("#oauth2.isUser() and #oauth2.clientHasRole('ROLE_SANDBOX') and hasRole('ROLE_SANDBOX_ADMIN') and #oauth2.hasScope('write')")
    public List<AffiliationProvider> findAll() {
        return affiliationProviderService.findAll();
    }

    /**
     * Create an affiliationProvider
     * @param affiliationProvider
     * @return The affiliation provider created
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize("#oauth2.isUser() and #oauth2.clientHasRole('ROLE_SANDBOX') and hasRole('ROLE_SANDBOX_ADMIN') and #oauth2.hasScope('write')")
    public AffiliationProvider create(@RequestBody AffiliationProvider affiliationProvider) {
        ParameterHelper.assertNull(affiliationProvider);
        ParameterHelper.assertNotNull(affiliationProvider.getId());
        ParameterHelper.assertEmpty(affiliationProvider.getName());
        return affiliationProviderService.createAffiliationProvider(affiliationProvider);
    }

    /**
     * Remove an affiliation provider
     * @param providerId The id of the provider to remove.
     */
    @ResponseBody
    @RequestMapping(value = "{providerId}", method = RequestMethod.DELETE)
    @PreAuthorize("#oauth2.isUser() and #oauth2.clientHasRole('ROLE_SANDBOX') and hasRole('ROLE_SANDBOX_ADMIN') and #oauth2.hasScope('write')")
    public ResponseDTO delete(@PathVariable String providerId) {
        ParameterHelper.assertEmpty(providerId);
        affiliationProviderService.deleteById(providerId);
        return ResponseDTO.OK;
    }

    /**
     * Active the provider.
     * @param providerId The provider id
     */
    @ResponseBody
    @RequestMapping(value = "/active/{providerId}", method = RequestMethod.PUT)
    @PreAuthorize("#oauth2.isUser() and #oauth2.clientHasRole('ROLE_SANDBOX') and hasRole('ROLE_SANDBOX_ADMIN') and #oauth2.hasScope('write')")
    public ResponseDTO active(@PathVariable String providerId) {
        ParameterHelper.assertEmpty(providerId);
        affiliationProviderService.activeProvider(providerId);
        return ResponseDTO.OK;
    }
}

