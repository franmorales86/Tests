/**
 * Project: RecCore
 * Created by: raulanatol at 12/10/13 11:02
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.services.items;

import com.reclabs.recomendar.model.documents.items.ItemToVerify;
import com.reclabs.recomendar.model.repositories.items.ItemToVerifyRepository;
import com.reclabs.recomendar.model.services.items.ItemToVerifyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author raulanatol
 */
@RunWith(MockitoJUnitRunner.class)
public class ItemToVerifyServiceImplTest {

    @Mock
    private ItemToVerifyRepository itemToVerifyRepository;

    @InjectMocks
    private ItemToVerifyService itemToVerifyService = new ItemToVerifyServiceImpl();

    @Test
    public void createItemWithNullCurrentWeb() throws Exception {
        ItemToVerify itemToVerify = new ItemToVerify();
        String itemId = "itemId";
        String userId = "userId";
        itemToVerify.setUserId(userId);
        itemToVerify.setItemId(itemId);
        itemToVerify.setCurrentWeb(null);

        Mockito.when(itemToVerifyRepository.save(itemToVerify)).thenReturn(itemToVerify);
        ItemToVerify expected = itemToVerifyService.createItem(null, itemId, userId);
        assertThat(expected, is(itemToVerify));
    }
}
