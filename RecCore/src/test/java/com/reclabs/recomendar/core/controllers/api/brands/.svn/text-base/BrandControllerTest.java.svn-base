/**
 * Project: RecCore
 * Created by: raulanatol at 19/07/13 13:12
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.controllers.api.brands;

import com.reclabs.recomendar.common.exceptions.RecIllegalArgumentException;
import com.reclabs.recomendar.core.services.brand.AffiliationManagerServiceImpl;
import com.reclabs.recomendar.core.services.brand.AffiliationProviderServiceImpl;
import com.reclabs.recomendar.core.services.brand.BrandServiceImpl;
import com.reclabs.recomendar.model.documents.brand.AffiliationManager;
import com.reclabs.recomendar.model.documents.brand.AffiliationProvider;
import com.reclabs.recomendar.model.documents.brand.Brand;
import com.reclabs.recomendar.model.services.brand.AffiliationManagerService;
import com.reclabs.recomendar.model.services.brand.AffiliationProviderService;
import com.reclabs.recomendar.model.services.brand.BrandService;
import com.reclabs.recomendar.model.types.shop.VendorRegionType;
import org.apache.commons.lang.NotImplementedException;
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
public class BrandControllerTest {

    @InjectMocks
    private BrandController brandController = new BrandController();

    @Mock
    private BrandService brandService = new BrandServiceImpl();

    @Mock
    private AffiliationProviderService affiliationProviderService = new AffiliationProviderServiceImpl();

    @Mock
    private AffiliationManagerService affiliationManagerService = new AffiliationManagerServiceImpl();

    @Test(expected = RecIllegalArgumentException.class)
    public void create() throws Exception {
        brandController.create(null);
    }

    @Test
    public void createSimpleData() throws Exception {
        Brand brand = new Brand();
        brand.setName("Name");
        brandController.create(brand);

        Mockito.verify(brandService, Mockito.times(1)).createBrand(brand);
    }


    @Test(expected = RecIllegalArgumentException.class)
    public void createBrandNullName() {
        Brand brand = new Brand();
        brandController.create(brand);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void createBrandInvalidName() {
        Brand brand = new Brand();
        brand.setName("IN_VALID_NAME");
        brandController.create(brand);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void createBrandInvalidNameWithSpaces() {
        Brand brand = new Brand();
        brand.setName("INVALID NAME");
        brandController.create(brand);
    }

    @Test
    public void findAllTest() {
        brandController.findAll();

        Mockito.verify(brandService, Mockito.times(1)).findAll();
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void findByIdWithNullParameter() throws Exception {
        brandController.findById(null);
    }

    @Test
    public void findByIdWithAnyParameter() throws Exception {
        brandController.findById("testId");
        Mockito.verify(brandService, Mockito.times(1)).findBrandById("testId");
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void disableBrand() throws Exception {
        brandController.disableBrand(null);
    }

    @Test(expected = NotImplementedException.class)
    public void addMarketPlace() throws Exception {
        brandController.addMarketPlace(null, null);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void addAffiliationManagerWithNullBrandId() throws Exception {
        AffiliationManager affiliationManager = new AffiliationManager();
        brandController.addAffiliationManager(null, "affiliationProviderId", affiliationManager);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void addAffiliationManagerWithNullAffiliationProviderId() throws Exception {
        AffiliationManager affiliationManager = new AffiliationManager();
        brandController.addAffiliationManager("brandId", null, affiliationManager);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void addAffiliationManagerWithNullAffiliationManager() throws Exception {
        brandController.addAffiliationManager("brandId", "affiliationProviderId", null);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void addAffiliationManagerWithNullVendorRegion() throws Exception {
        AffiliationManager affiliationManager = new AffiliationManager();
        affiliationManager.setVendorRegionType(null);
        brandController.addAffiliationManager("brandId", "affiliationProviderId", affiliationManager);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void addAffiliationManagerWithNullAlias() throws Exception {
        AffiliationManager affiliationManager = new AffiliationManager();
        affiliationManager.setVendorRegionType(VendorRegionType.ES);
        affiliationManager.setAlias(null);
        brandController.addAffiliationManager("brandId", "affiliationProviderId", affiliationManager);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void addAffiliationManagerWithNullDomain() throws Exception {
        AffiliationManager affiliationManager = new AffiliationManager();
        affiliationManager.setVendorRegionType(VendorRegionType.ES);
        affiliationManager.setAlias("alias");
        affiliationManager.setDomain(null);
        brandController.addAffiliationManager("brandId", "affiliationProviderId", affiliationManager);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void addAffiliationManagerWithNullPattern() throws Exception {
        AffiliationManager affiliationManager = new AffiliationManager();
        affiliationManager.setVendorRegionType(VendorRegionType.ES);
        affiliationManager.setAlias("alias");
        affiliationManager.setDomain("domain");
        affiliationManager.setPattern(null);
        brandController.addAffiliationManager("brandId", "affiliationProviderId", affiliationManager);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void addAffiliationManagerWithDuplicatedAlias() throws Exception {
        AffiliationManager affiliationManager = new AffiliationManager();
        affiliationManager.setVendorRegionType(VendorRegionType.ES);
        affiliationManager.setAlias("alias");
        affiliationManager.setDomain("domain");
        affiliationManager.setPattern("pattern");

        Mockito.when(affiliationManagerService.findAffiliationManagerByAlias("alias")).thenReturn(affiliationManager);

        brandController.addAffiliationManager("brandId", "affiliationProviderId", affiliationManager);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void addAffiliationManagerWithInvalidAffiliationProvider() throws Exception {
        AffiliationManager affiliationManager = new AffiliationManager();
        affiliationManager.setVendorRegionType(VendorRegionType.ES);
        affiliationManager.setAlias("alias");
        affiliationManager.setDomain("domain");
        affiliationManager.setPattern("pattern");

        Mockito.when(affiliationManagerService.findAffiliationManagerByAlias("alias")).thenReturn(null);
        Mockito.when(affiliationProviderService.findAffiliationProviderById("affiliationProviderId")).thenReturn(null);

        brandController.addAffiliationManager("brandId", "affiliationProviderId", affiliationManager);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void addAffiliationManagerWithInvalidBrandId() throws Exception {
        AffiliationManager affiliationManager = new AffiliationManager();
        affiliationManager.setVendorRegionType(VendorRegionType.ES);
        affiliationManager.setAlias("alias");
        affiliationManager.setDomain("domain");
        affiliationManager.setPattern("pattern");

        Mockito.when(affiliationManagerService.findAffiliationManagerByAlias("alias")).thenReturn(null);
        Mockito.when(affiliationProviderService.findAffiliationProviderById("affiliationProviderId")).thenReturn(new AffiliationProvider());
        Mockito.when(brandService.findBrandById("brandId")).thenReturn(null);

        brandController.addAffiliationManager("brandId", "affiliationProviderId", affiliationManager);
    }

    @Test
    public void addAffiliationManagerWithValidParameters() throws Exception {
        AffiliationManager affiliationManager = new AffiliationManager();
        affiliationManager.setVendorRegionType(VendorRegionType.ES);
        affiliationManager.setAlias("alias");
        affiliationManager.setDomain("domain");
        affiliationManager.setPattern("pattern");

        Brand brand = new Brand();
        AffiliationProvider affiliationProvider = new AffiliationProvider();

        Mockito.when(affiliationManagerService.findAffiliationManagerByAlias("alias")).thenReturn(null);
        Mockito.when(affiliationProviderService.findAffiliationProviderById("affiliationProviderId")).thenReturn(affiliationProvider);
        Mockito.when(brandService.findBrandById("brandId")).thenReturn(brand);

        brandController.addAffiliationManager("brandId", "affiliationProviderId", affiliationManager);

        Mockito.verify(affiliationManagerService, Mockito.times(1)).createAndLinkAffiliationManager(brand, affiliationProvider, affiliationManager);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void getAffiliationManagersWithNullParameters() throws Exception {
        brandController.getAffiliationManagers(null);
    }

    @Test
    public void getAffiliationManagersWithValidParameters() throws Exception {
        String brandId = "brandId";
        brandController.getAffiliationManagers(brandId);

        Mockito.verify(affiliationManagerService, Mockito.times(1)).findAffiliationManagerByBrandId(brandId);
    }
}