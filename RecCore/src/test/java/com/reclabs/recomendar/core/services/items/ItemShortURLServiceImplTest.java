/**
 * Project: RecCore
 * Created by: raulanatol at 03/10/13 11:58
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.services.items;

import com.reclabs.recomendar.common.exceptions.RecIllegalArgumentException;
import com.reclabs.recomendar.core.repository.items.ItemShortURLRepositoryImpl;
import com.reclabs.recomendar.model.services.BarneyService;
import com.reclabs.recomendar.model.services.items.ItemShortURLService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author raulanatol
 */
@RunWith(MockitoJUnitRunner.class)
public class ItemShortURLServiceImplTest {

    @Mock
    private ItemShortURLRepositoryImpl itemShortURLRepository;

    @Mock
    private BarneyService barneyService;

    @InjectMocks
    private ItemShortURLService itemShortURLService = new ItemShortURLServiceImpl();

    @Test(expected = RecIllegalArgumentException.class)
    public void getShortURLWithNullUserId() throws Exception {
        itemShortURLService.getShortURL(null, "10", "asd", "");
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void getShortURLWithEmptyUserId() throws Exception {
        itemShortURLService.getShortURL("", "10", "asd", "");
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void getShortURLWithNullItemId() throws Exception {
        itemShortURLService.getShortURL("asd", null, "asd", "");
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void getShortURLWithEmptyItemId() throws Exception {
        itemShortURLService.getShortURL("asd", "", "asd", "");
    }
}
