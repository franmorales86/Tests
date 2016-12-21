/**
 * Project: RecCore
 * Created by: raulanatol at 08/11/13 09:02
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.services.brand;

import com.reclabs.recomendar.common.helpers.types.CollectionHelper;
import com.reclabs.recomendar.model.documents.brand.AffiliationManager;
import com.reclabs.recomendar.model.repositories.brand.AffiliationManagerRepository;
import com.reclabs.recomendar.model.services.brand.AffiliationManagerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static com.mongodb.util.MyAsserts.assertTrue;

/**
 * @author raulanatol
 */
@RunWith(MockitoJUnitRunner.class)
public class AffiliationManagerServiceImplTest {

    @Mock
    private AffiliationManagerRepository affiliationManagerRepository;

    @InjectMocks
    private AffiliationManagerService affiliationManagerService = new AffiliationManagerServiceImpl();

    @Test
    public void findAffiliationManagerByItemInformationURLWithEmptyParameter() throws Exception {
        List<AffiliationManager> result = affiliationManagerService.findAffiliationManagerByItemInformationURL("");
        assertTrue(CollectionHelper.isEmpty(result));
    }

    @Test
    public void findAffiliationManagerByItemInformationURLWithNullParameter() throws Exception {
        List<AffiliationManager> result = affiliationManagerService.findAffiliationManagerByItemInformationURL(null);
        assertTrue(CollectionHelper.isEmpty(result));
    }

    @Test
    public void findAffiliationManagerByItemInformationURLWithInvalidDomain() throws Exception {
        List<AffiliationManager> result = affiliationManagerService.findAffiliationManagerByItemInformationURL("noDomain");
        assertTrue(CollectionHelper.isEmpty(result));
    }

    @Test
    public void findAffiliationManagerByItemInformationURLWithValidDomainButNotInDB() throws Exception {
        String testDomain = "dummy.com";
        Mockito.when(affiliationManagerRepository.findByDomainURL(testDomain)).thenReturn(null);

        List<AffiliationManager> result = affiliationManagerService.findAffiliationManagerByItemInformationURL(testDomain);
        assertTrue(CollectionHelper.isEmpty(result));
    }

    @Test
    public void findAffiliationManagerByItemInformationURLWithValidDomainButNotInDBData2() throws Exception {
        String testDomain = "dummy";
        Mockito.when(affiliationManagerRepository.findByDomainURL(testDomain)).thenReturn(null);

        List<AffiliationManager> result = affiliationManagerService.findAffiliationManagerByItemInformationURL(testDomain);

        Mockito.verify(affiliationManagerRepository, Mockito.never()).findByDomainURL(testDomain);
        assertTrue(CollectionHelper.isEmpty(result));
    }

}
