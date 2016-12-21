/**
 * Project: RecCore
 * Created by: raulanatol at 15/07/13 11:42
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.repository.brand;

import com.reclabs.recomendar.core.repository.generic.RepositoryTest;
import com.reclabs.recomendar.model.documents.brand.AffiliationManager;
import com.reclabs.recomendar.model.documents.brand.Brand;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author raulanatol
 */
public class BrandRepositoryImplTest extends RepositoryTest {

    private MongoTemplate mongoTemplate;
    private BrandRepositoryImpl brandRepository;

    @Before
    public void setUp() {
        mongoTemplate = new MongoTemplate(mongo, DB_NAME);
        brandRepository = new BrandRepositoryImpl(mongoTemplate);
    }

    @After
    public void tearDown() {
        mongoTemplate.dropCollection(Brand.class);
    }

    @Test
    public void findAllAffiliationDomainWithAnyValues() {
        List<Brand> result = brandRepository.findByAffiliationDomain("http://zalando.es");
        assertThat(result.size(), is(0));
    }

    @Test
    public void findAllAffiliationDomainWithAnyValidValues() {
        //FIXME arreglar
//        Brand brand1 = new Brand();
//        brand1.setAffiliationManager(getAffiliationsManager("http://google.com", "http://facebook.com"));
//        mongoTemplate.save(brand1);
//
//        List<Brand> result = brandRepository.findByAffiliationDomain("http://zalando.es");
//        assertThat(result.size(), is(0));
//        assertThat(mongoTemplate.findAll(Brand.class).size(), is(1));
    }

    /**
     * @param domains
     * @return
     */
    private List<AffiliationManager> getAffiliationsManager(String... domains) {
        List<AffiliationManager> result = new ArrayList<>();
        for (String domain : domains) {
            AffiliationManager affiliationManager = new AffiliationManager();
            affiliationManager.setDomain(domain);
            result.add(affiliationManager);
        }
        return result;
    }

    @Test
    public void findAllAffiliationDomainWithValidValues() {
        //FIXME arreglar
//        Brand brand1 = new Brand();
//        brand1.setAffiliationManager(getAffiliationsManager("http://google.com", "http://zalando.es", "http://facebook.com"));
//        mongoTemplate.save(brand1);
//
//        List<Brand> result = brandRepository.findByAffiliationDomain("http://zalando.es");
//        assertThat(result.size(), is(1));
//        assertThat(mongoTemplate.findAll(Brand.class).size(), is(1));
    }

    @Test
    public void findAllAffiliationDomainWithMultipleValues() {
        //FIXME arreglar
//        Brand brand1 = new Brand();
//        brand1.setAffiliationManager(getAffiliationsManager("http://google.com", "http://zalando.com", "http://facebook.com"));
//        brand1.setName("Brand1");
//        mongoTemplate.save(brand1);
//
//        Brand brand2 = new Brand();
//        brand2.setAffiliationManager(getAffiliationsManager("http://google.com", "http://zalando.es", "http://facebook.com"));
//        brand2.setName("Brand2");
//        mongoTemplate.save(brand2);
//
//        List<Brand> result = brandRepository.findByAffiliationDomain("http://zalando.es");
//        assertThat(result.size(), is(1));
//        assertThat(result.get(0).getName(), is("Brand2"));
//        assertThat(mongoTemplate.findAll(Brand.class).size(), is(2));
    }
}
