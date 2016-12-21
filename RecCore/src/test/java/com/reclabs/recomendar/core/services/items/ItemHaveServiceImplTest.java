/**
 * Project: RecCore
 * Created by: raulanatol at 05/10/13 09:44
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.services.items;

import com.reclabs.recomendar.model.documents.items.ItemHave;
import com.reclabs.recomendar.model.repositories.items.ItemHaveRepository;
import com.reclabs.recomendar.model.services.items.ItemHaveService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author raulanatol
 */
@SuppressWarnings("unchecked")
@RunWith(MockitoJUnitRunner.class)
public class ItemHaveServiceImplTest {

    @Mock
    private ItemHaveRepository itemHaveRepository;

    @InjectMocks
    private ItemHaveService itemHaveService = new ItemHaveServiceImpl();

    @Test
    public void userHaveItemWithNullUserIdTest() throws Exception {
        itemHaveService.userHaveItem(null, "itemID");
    }

    @Test
    public void userHaveItemWithEmptyUserIdTest() throws Exception {
        itemHaveService.userHaveItem("", "itemID");
    }

    @Test
    public void userHaveItemWithEmptyItemIdTest() throws Exception {
        itemHaveService.userHaveItem("userID", "");
    }

    @Test
    public void userHaveItemWithNullItemIdTest() throws Exception {
        itemHaveService.userHaveItem("userID", null);
    }

    @Test
    public void userHaveItemWithAlreadyHaveItemIdTest() throws Exception {
        ItemHave itemHave = new ItemHave();
        String itemId = "itemID";
        String userId = "userID";
        Mockito.when(itemHaveRepository.findByUserIdAndItemId(userId, itemId)).thenReturn(itemHave);
        itemHaveService.userHaveItem("userID", "itemID");

        Mockito.verify(itemHaveRepository, Mockito.never()).save((Iterable<ItemHave>) Mockito.anyObject());
    }

    @Test
    public void userHaveItemNormalDataTest() throws Exception {
        String itemId = "itemID";
        String userId = "userID";
        Mockito.when(itemHaveRepository.findByUserIdAndItemId(userId, itemId)).thenReturn(null);

        itemHaveService.userHaveItem("userID", "itemID");

        Mockito.verify(itemHaveRepository, Mockito.times(1)).save((ItemHave) Mockito.anyObject());
    }
}
