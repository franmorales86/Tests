/**
 * Project: RecCore
 * Created by: raulanatol at 11/07/13 14:28
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.services.brand;

import com.reclabs.recomendar.model.documents.brand.AffiliationManager;
import com.reclabs.recomendar.model.repositories.brand.BrandRepository;
import com.reclabs.recomendar.model.services.brand.BrandService;
import com.reclabs.recomendar.model.services.items.ItemService;
import org.apache.commons.lang.NotImplementedException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author raulanatol
 */
@RunWith(MockitoJUnitRunner.class)
public class BrandServiceImplTest {
    @Mock
    private BrandRepository brandRepository;

    @Mock
    private ItemService itemService;

    @InjectMocks
    private BrandService brandService = new BrandServiceImpl();

    /**
     * oldBrand enabled
     * newBrand enabled
     */
    @Test
    public void updateBrandTestEnabledMode1() {
//        String brandId = "BRAND_ID";
//        Brand newBrand = new Brand();
//        newBrand.setId(brandId);
//        newBrand.setEnabled(true);
//        Brand oldBrand = new Brand();
//        oldBrand.setId(brandId);
//        oldBrand.setEnabled(true);
//
//        Mockito.when(brandRepository.findOne(brandId)).thenReturn(oldBrand);
//        Mockito.when(brandRepository.save(newBrand)).thenReturn(newBrand);
//        brandService.updateBrand(newBrand);
//
//        Mockito.verify(brandRepository, times(1)).findOne(brandId);
//        Mockito.verify(brandRepository, times(1)).save(newBrand);
    }

    /**
     * oldBrand enabled
     * newBrand disabled
     */
    @Test
    public void updateBrandTestEnabledMode2() {
//        String brandId = "BRAND_ID";
//        Brand newBrand = new Brand();
//        newBrand.setId(brandId);
//        newBrand.setEnabled(false);
//        Brand oldBrand = new Brand();
//        oldBrand.setId(brandId);
//        oldBrand.setEnabled(true);
//
//        Mockito.when(brandRepository.findOne(brandId)).thenReturn(oldBrand);
//        Mockito.when(brandRepository.save(newBrand)).thenReturn(newBrand);
//        brandService.updateBrand(newBrand);
//
//        Mockito.verify(brandRepository, times(1)).findOne(brandId);
        //TOOD verify call to disableBrand
    }

    /**
     * oldBrand disabled
     * newBrand enabled
     */
//    @Test
    public void updateBrandTestEnabledMode3() {
        throw new NotImplementedException();
//        String brandId = "BRAND_ID";
//        Brand newBrand = new Brand();
//        newBrand.setId(brandId);
//        newBrand.setEnabled(true);
//        Brand oldBrand = new Brand();
//        oldBrand.setId(brandId);
//        oldBrand.setEnabled(false);
//
//        Mockito.when(brandRepository.findOne(brandId)).thenReturn(oldBrand);
//        Mockito.when(brandRepository.save(newBrand)).thenReturn(newBrand);
//        brandService.updateBrand(newBrand);
//
//        Mockito.verify(brandRepository, times(1)).findOne(brandId);
//        TODO verify enableBrand is called
//        Mockito.verify(brandRepository, times(1)).save(newBrand);
    }

    /**
     * oldBrand disabled
     * newBrand enabled
     */
//    @Test
    public void updateBrandTestEnabledMode4() {
        throw new NotImplementedException();
//        String brandId = "BRAND_ID";
//        Brand newBrand = new Brand();
//        newBrand.setId(brandId);
//        newBrand.setEnabled(true);
//        Brand oldBrand = new Brand();
//        oldBrand.setId(brandId);
//        oldBrand.setEnabled(false);
//
//        Mockito.when(brandRepository.findOne(brandId)).thenReturn(oldBrand);
//        Mockito.when(brandRepository.save(newBrand)).thenReturn(newBrand);
//        brandService.updateBrand(newBrand);
//
//        Mockito.verify(brandRepository, times(1)).findOne(brandId);
//        Mockito.verify(brandRepository, times(1)).save(newBrand);
    }

    private AffiliationManager getDummyAffiliationManager(boolean enabled, String alias) {
        AffiliationManager result = new AffiliationManager();
        result.setEnabled(enabled);
        result.setAlias(alias);
        return result;
    }

    @Test
    public void getAffiliationManagerByItemURLWithNoBrand() {
//        String itemInformationURL = "http://www.etsy.com/zapatos1/comprar/mujer#12311241Gfd";
//
//        List<Brand> listOfAffiliationBrandsOnDB = new ArrayList<>();
//        Mockito.when(brandRepository.findByAffiliationDomain("etsy.com")).thenReturn(listOfAffiliationBrandsOnDB);
//
//        List<AffiliationManager> result = brandService.getAffiliationManagerByItemURL(itemInformationURL);
//        assertThat(result.size(), is(0));
    }

    //    @Test
    public void getAffiliationManagerByItemURLWithNormalData() {
        throw new NotImplementedException();
//        String itemInformationURL = "http://www.etsy.com/zapatos1/comprar/mujer#12311241Gfd";
//
//        AffiliationManager validAffiliationManager = new AffiliationManager();
//        validAffiliationManager.setDomain("etsy.com");
//
//        AffiliationManager dummyAffiliationManager = new AffiliationManager();
//        dummyAffiliationManager.setDomain("etsy.es");
//
//        List<AffiliationManager> affiliationManagers = new ArrayList<>();
//        affiliationManagers.add(validAffiliationManager);
//        affiliationManagers.add(dummyAffiliationManager);
//
//        Brand brand = new Brand();
//        brand.setAffiliationManager(affiliationManagers);
//        List<Brand> listOfAffiliationBrandsOnDB = new ArrayList<>();
//        listOfAffiliationBrandsOnDB.add(brand);
//
//        Mockito.when(brandRepository.findByAffiliationDomain("etsy.com")).thenReturn(listOfAffiliationBrandsOnDB);

//        List<AffiliationManager> result = brandService.getAffiliationManagerByItemURL(itemInformationURL);
//        assertThat(result.size(), is(1));
//        assertThat(result.get(0).getDomain(), is("etsy.com"));
    }

    //    @Test(expected = RecIllegalArgumentException.class)
    public void enableAffiliationManagerWithNullParameters() {
//        brandService.enableAffiliationManager(null);
    }

    @Test
    public void enableAffiliationManagerWithSimpleData() {
//        AffiliationManager affiliationManager = new AffiliationManager();
//        affiliationManager.setAlias("NIKE_ZANOX_ALL");
//        brandService.enableAffiliationManager(affiliationManager);
//
//        Mockito.verify(itemService, times(1)).enableOrAddAffiliationManagerInItems(affiliationManager);
    }

}
