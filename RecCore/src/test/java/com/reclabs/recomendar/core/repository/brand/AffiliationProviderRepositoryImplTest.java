/**
 * Project: RecCore
 * Created by: raulanatol at 17/07/13 10:03
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.repository.brand;

import com.reclabs.recomendar.core.repository.generic.RepositoryTest;
import com.reclabs.recomendar.model.documents.brand.AffiliationManager;
import com.reclabs.recomendar.model.documents.brand.AffiliationProvider;
import com.reclabs.recomendar.model.repositories.brand.AffiliationProviderRepository;
import org.junit.After;
import org.junit.Before;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * @author raulanatol
 */
public class AffiliationProviderRepositoryImplTest extends RepositoryTest {

    private MongoTemplate mongoTemplate;
    private AffiliationProviderRepository affiliationProviderRepository;

    @Before
    public void setUp() {
        mongoTemplate = new MongoTemplate(mongo, DB_NAME);
        affiliationProviderRepository = new AffiliationProviderRepositoryImpl(mongoTemplate);
    }

    @After
    public void tearDown() {
        mongoTemplate.dropCollection(AffiliationProvider.class);
        mongoTemplate.dropCollection(AffiliationManager.class);

    }


}
