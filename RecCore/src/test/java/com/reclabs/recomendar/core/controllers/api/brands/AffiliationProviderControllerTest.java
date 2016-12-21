/**
 * Project: RecCore
 * Created by: raulanatol at 19/07/13 12:58
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.controllers.api.brands;

import com.reclabs.recomendar.common.exceptions.RecIllegalArgumentException;
import com.reclabs.recomendar.core.services.brand.AffiliationProviderServiceImpl;
import com.reclabs.recomendar.model.documents.brand.AffiliationProvider;
import com.reclabs.recomendar.model.services.brand.AffiliationProviderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author raulanatol
 */
@RunWith(MockitoJUnitRunner.class)
public class AffiliationProviderControllerTest {

    @InjectMocks
    private AffiliationProviderController providerController = new AffiliationProviderController();

    @Mock
    private AffiliationProviderService affiliationProviderService = new AffiliationProviderServiceImpl();

    @Test
    public void findAll() throws Exception {
        providerController.findAll();
        Mockito.verify(affiliationProviderService, Mockito.times(1)).findAll();
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void createWithNullParameter() throws Exception {
        providerController.create(null);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void createWithNotNullParameter() throws Exception {
        AffiliationProvider affiliationProvider = new AffiliationProvider();
        affiliationProvider.setId("notNullParameters");
        providerController.create(affiliationProvider);
    }

    @Test
    public void createWithSimpleParameter() throws Exception {
        AffiliationProvider affiliationProvider = new AffiliationProvider();
        affiliationProvider.setName("TestProvider");
        providerController.create(affiliationProvider);

        Mockito.verify(affiliationProviderService, Mockito.times(1)).createAffiliationProvider(affiliationProvider);
    }
}