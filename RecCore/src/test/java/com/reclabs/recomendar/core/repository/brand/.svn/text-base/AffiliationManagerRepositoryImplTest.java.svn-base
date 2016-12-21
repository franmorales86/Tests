/**
 * Project: RecCore
 * Created by: raulanatol at 17/07/13 10:24
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.repository.brand;

import com.reclabs.recomendar.common.helpers.types.CollectionHelper;
import com.reclabs.recomendar.core.repository.generic.RepositoryTest;
import com.reclabs.recomendar.model.documents.brand.AffiliationManager;
import com.reclabs.recomendar.model.documents.brand.AffiliationProvider;
import com.reclabs.recomendar.model.documents.brand.Brand;
import com.reclabs.recomendar.model.repositories.brand.AffiliationManagerRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

import static com.mongodb.util.MyAsserts.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author raulanatol
 */
public class AffiliationManagerRepositoryImplTest extends RepositoryTest {

    private MongoTemplate mongoTemplate;
    private AffiliationManagerRepository affiliationManagerRepository;

    @Before
    public void setUp() {
        mongoTemplate = new MongoTemplate(mongo, DB_NAME);
        affiliationManagerRepository = new AffiliationManagerRepositoryImpl(mongoTemplate);
    }

    @After
    public void tearDown() {
        mongoTemplate.dropCollection(AffiliationProvider.class);
        mongoTemplate.dropCollection(AffiliationManager.class);
        mongoTemplate.dropCollection(Brand.class);

    }

    @Test
    public void getAffiliationManagerByProviderNameSimpleData() {
//        String providerName = "Zanox";
//
//        AffiliationProvider provider1 = new AffiliationProvider();
//        provider1.setName(providerName);
//        provider1.setEnabled(true);
//        mongoTemplate.insert(provider1);
//
//        AffiliationManager affiliationManager = new AffiliationManager();
//        affiliationManager.setAffiliationProvider(provider1);
//        affiliationManager.setAlias("Zanox_ES");
//        mongoTemplate.insert(affiliationManager);
//
//        AffiliationManager affiliationManager2 = new AffiliationManager();
//        affiliationManager2.setAffiliationProvider(provider1);
//        affiliationManager2.setAlias("Zanox_ALL");
//        mongoTemplate.insert(affiliationManager2);
//
//        AffiliationProvider provider2 = new AffiliationProvider();
//        provider2.setName("Other provider");
//        provider2.setEnabled(true);
//        mongoTemplate.insert(provider2);
//
//        AffiliationManager affiliationManager3 = new AffiliationManager();
//        affiliationManager3.setAffiliationProvider(provider2);
//        affiliationManager3.setAlias("Other_provider_ALL");
//        mongoTemplate.insert(affiliationManager3);
//
//        AffiliationProvider provider3 = new AffiliationProvider();
//        provider3.setName("Another provider");
//        provider3.setEnabled(true);
//        mongoTemplate.insert(provider3);
//
//        List<AffiliationManager> affiliationManagerList = affiliationManagerRepository.getAffiliationManagerByProviderName(providerName);
//        assertThat(affiliationManagerList.size(), is(2));
    }

    @Test
    public void getAffiliationManagerByBrandNameSimpleData() throws Exception {
//        String brandName = "Zalando";
//        String providerName = "Zanox";
//
//        Brand brand1 = new Brand();
//        brand1.setName(brandName);
//        brand1.setEnabled(true);
//        mongoTemplate.insert(brand1);
//
//        Brand brand2 = new Brand();
//        brand2.setName("OtherBrand");
//        brand2.setEnabled(true);
//        mongoTemplate.insert(brand1);
//
//        AffiliationProvider provider1 = new AffiliationProvider();
//        provider1.setName(providerName);
//        provider1.setEnabled(true);
//        mongoTemplate.insert(provider1);
//
//        AffiliationManager affiliationManager = new AffiliationManager();
//        affiliationManager.setAffiliationProvider(provider1);
//        affiliationManager.setAlias("Zanox_ES");
//        affiliationManager.setBrand(brand1);
//        mongoTemplate.insert(affiliationManager);
//
//        AffiliationManager affiliationManager2 = new AffiliationManager();
//        affiliationManager2.setAffiliationProvider(provider1);
//        affiliationManager2.setAlias("Zanox_ALL");
//        affiliationManager.setBrand(brand2);
//        mongoTemplate.insert(affiliationManager2);
//
//        AffiliationProvider provider2 = new AffiliationProvider();
//        provider2.setName("Other provider");
//        provider2.setEnabled(true);
//        mongoTemplate.insert(provider2);
//
//        AffiliationManager affiliationManager3 = new AffiliationManager();
//        affiliationManager3.setAffiliationProvider(provider2);
//        affiliationManager3.setAlias("Other_provider_ALL");
//        affiliationManager3.setBrand(brand1);
//        mongoTemplate.insert(affiliationManager3);
//
//        AffiliationProvider provider3 = new AffiliationProvider();
//        provider3.setName("Another provider");
//        provider3.setEnabled(true);
//        mongoTemplate.insert(provider3);
//
//        List<AffiliationManager> affiliationManagerList = affiliationManagerRepository.getAffiliationManagerByBrandName(brandName);
//        assertThat(affiliationManagerList.size(), is(2));
//        assertThat(affiliationManagerList.get(0).getAlias(), is("Zanox_ES"));
//        assertThat(affiliationManagerList.get(1).getAlias(), is("Other_provider_ALL"));
    }


    @Test
    public void findByDomainURL() throws Exception {
        AffiliationManager affiliationManager1 = new AffiliationManager();
        affiliationManager1.setId("25");
        affiliationManager1.setDomain("etsy.com");
        mongoTemplate.insert(affiliationManager1);

        AffiliationManager affiliationManager2 = new AffiliationManager();
        affiliationManager2.setId("18");
        affiliationManager2.setDomain("google.com");
        mongoTemplate.insert(affiliationManager2);

        List<AffiliationManager> result = affiliationManagerRepository.findByDomainURL("google.com");
        assertThat(result.size(), is(1));
        assertThat(result.get(0).getId(), is("18"));
    }

    @Test
    public void findByDomainURLNormalDataNoResult() throws Exception {
        assertTrue(CollectionHelper.isEmpty(affiliationManagerRepository.findByDomainURL("domain.com")));
        assertTrue(CollectionHelper.isEmpty(affiliationManagerRepository.findByDomainURL("domain.com/")));
        assertTrue(CollectionHelper.isEmpty(affiliationManagerRepository.findByDomainURL("domain")));
    }

    @Test
    public void findByDomainURLNormalData() throws Exception {
        AffiliationManager affiliationManager = new AffiliationManager();
        affiliationManager.setId("foo");
        affiliationManager.setDomain("bar");
        mongoTemplate.insert(affiliationManager);

        assertThat(affiliationManagerRepository.findByDomainURL("bar").size(), is(1));
    }


    @Test
    public void findByDomainURLNormalDataWithFinalSlash() throws Exception {
        AffiliationManager affiliationManager = new AffiliationManager();
        affiliationManager.setId("foo");
        affiliationManager.setDomain("bar");
        mongoTemplate.insert(affiliationManager);

        assertThat(affiliationManagerRepository.findByDomainURL("bar").size(), is(1));
        assertThat(affiliationManagerRepository.findByDomainURL("bar/").size(), is(0));
        assertThat(affiliationManagerRepository.findByDomainURL("bar/").size(), is(0));
    }

}