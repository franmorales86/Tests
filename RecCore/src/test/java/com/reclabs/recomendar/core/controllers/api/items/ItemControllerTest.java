/**
 * Project: RecCore
 * Created by: raulanatol at 09/03/2013 13:41:20
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.controllers.api.items;

import com.reclabs.recomendar.common.exceptions.RecIllegalArgumentException;
import com.reclabs.recomendar.common.helpers.types.CollectionHelper;
import com.reclabs.recomendar.common.types.RecMoney;
import com.reclabs.recomendar.core.misc.items.UpdateAffiliationManagerInputData;
import com.reclabs.recomendar.core.services.CategoryServiceImpl;
import com.reclabs.recomendar.core.services.ItemServiceImpl;
import com.reclabs.recomendar.esdriver.ESDriver;
import com.reclabs.recomendar.esdriver.ESDriverImpl;
import com.reclabs.recomendar.esdriver.actions.ESFind;
import com.reclabs.recomendar.model.documents.Category;
import com.reclabs.recomendar.model.documents.items.Item;
import com.reclabs.recomendar.model.documents.items.ItemTag;
import com.reclabs.recomendar.model.documents.items.URLPath;
import com.reclabs.recomendar.model.services.CategoryService;
import com.reclabs.recomendar.model.services.items.ItemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.util.ArrayList;
import java.util.Arrays;

import static com.mongodb.util.MyAsserts.assertTrue;

/**
 * @author raulanatol
 */
@RunWith(MockitoJUnitRunner.class)
public class ItemControllerTest {

    @InjectMocks
    protected ItemController itemController = new ItemController();

    @Mock
    private final ItemService itemService = new ItemServiceImpl();

    @Mock
    private ESDriver esDriver = new ESDriverImpl("HOST", 0);

    @Mock
    private final CategoryService categoryService = new CategoryServiceImpl();

    /**
     * With null parameter.
     * @see ItemController#userCreateItem(com.reclabs.recomendar.model.documents.items.Item)
     */
    @Test(expected = RecIllegalArgumentException.class)
    public void userCreateItemWithNullItem() {
        itemController.userCreateItem(null);
    }

    /**
     * With null item name.
     * @see ItemController#userCreateItem(com.reclabs.recomendar.model.documents.items.Item)
     */
    @Test(expected = RecIllegalArgumentException.class)
    public void userCreateItemWithNullItemName() {
        Item item = new Item();
        itemController.userCreateItem(item);
    }

    /**
     * With null category name.
     * @see ItemController#userCreateItem(com.reclabs.recomendar.model.documents.items.Item)
     */
    @Test(expected = RecIllegalArgumentException.class)
    public void userCreateItemWithNullCategory() {
        Item item = new Item();
        item.setName("test");
        itemController.userCreateItem(item);
    }

    /**
     * With null description name.
     * @see ItemController#userCreateItem(com.reclabs.recomendar.model.documents.items.Item)
     */
    @Test(expected = RecIllegalArgumentException.class)
    public void userCreateItemWithNullDescription() {
        Item item = new Item();
        item.setName("test");
        item.setCategory("categoryTest");
        itemController.userCreateItem(item);
    }

    /**
     * With null shop info.
     * @see ItemController#userCreateItem(com.reclabs.recomendar.model.documents.items.Item)
     */
    @Test(expected = RecIllegalArgumentException.class)
    public void userCreateItemWithNullShopInfo() {
        Item item = new Item();
        item.setName("test");
        item.setCategory("categoryTest");
        itemController.userCreateItem(item);
    }

    /**
     * With empty shop info.
     * @see ItemController#userCreateItem(com.reclabs.recomendar.model.documents.items.Item)
     */
    @Test(expected = RecIllegalArgumentException.class)
    public void userCreateItemWithEmptyShopInfo() {
        Item item = new Item();
        item.setName("test");
        item.setCategory("categoryTest");
        itemController.userCreateItem(item);
    }

    /**
     * With null tag items.
     * @see ItemController#userCreateItem(com.reclabs.recomendar.model.documents.items.Item)
     */
    @Test(expected = RecIllegalArgumentException.class)
    public void userCreateItemWithNullTagInfo() {
        Item item = new Item();
        item.setName("test");
        item.setCategory("categoryTest");
        item.setDescription("descriptionTest");
        item.setTags(null);
        itemController.userCreateItem(item);
    }

    /**
     * With empty tag items.
     * @see ItemController#userCreateItem(com.reclabs.recomendar.model.documents.items.Item)
     */
    @Test(expected = RecIllegalArgumentException.class)
    public void userCreateItemWithEmptyTagInfo() {
        Item item = new Item();
        item.setName("test");
        item.setCategory("categoryTest");
        item.setDescription("descriptionTest");
        item.setTags(Arrays.asList(new ItemTag()));
        itemController.userCreateItem(item);
    }

    /**
     * With null parameter.
     * @see ItemController#createItem(com.reclabs.recomendar.model.documents.items.Item)
     */
    @Test(expected = RecIllegalArgumentException.class)
    public void createItemWithNullItem() {
        itemController.createItem(null);
    }

    /**
     * With null category name.
     * @see ItemController#createItem(com.reclabs.recomendar.model.documents.items.Item)
     */
    @Test(expected = RecIllegalArgumentException.class)
    public void createItemWithNullCategory() {
        Item item = new Item();
        item.setName("test");
        itemController.createItem(item);
    }

    /**
     * With null category name.
     * @see ItemController#createItem(com.reclabs.recomendar.model.documents.items.Item)
     */
    @Test(expected = RecIllegalArgumentException.class)
    public void createItemWithNullDescription() {
        Item item = new Item();
        item.setName("test");
        item.setCategory("categoryTest");
        itemController.createItem(item);
    }

    /**
     * With null shop info.
     * @see ItemController#createItem(com.reclabs.recomendar.model.documents.items.Item)
     */
    @Test(expected = RecIllegalArgumentException.class)
    public void createItemWithNullShopInfo() {
        Item item = new Item();
        item.setName("test");
        item.setCategory("categoryTest");
        item.setDescription("descriptionTest");
        itemController.createItem(item);
    }

    /**
     * With empty shop info.
     * @see ItemController#createItem(com.reclabs.recomendar.model.documents.items.Item)
     */
    @Test(expected = RecIllegalArgumentException.class)
    public void createItemWithEmptyShopInfo() {
        Item item = new Item();
        item.setName("test");
        item.setCategory("categoryTest");
        item.setDescription("descriptionTest");
        itemController.createItem(item);
    }

    /**
     * With null shop info.
     * @see ItemController#createItem(com.reclabs.recomendar.model.documents.items.Item)
     */
    @Test(expected = RecIllegalArgumentException.class)
    public void createItemWithNullTagInfo() {
        Item item = new Item();
        item.setName("test");
        item.setCategory("categoryTest");
        item.setDescription("descriptionTest");
        item.setTags(null);
        itemController.createItem(item);
    }

    /**
     * With empty shop info.
     * @see ItemController#createItem(com.reclabs.recomendar.model.documents.items.Item)
     */
    @Test(expected = RecIllegalArgumentException.class)
    public void createItemWithEmptyTagInfo() {
        Item item = new Item();
        item.setName("test");
        item.setCategory("categoryTest");
        item.setDescription("descriptionTest");
        item.setTags(Arrays.asList(new ItemTag()));
        itemController.createItem(item);
    }

    /**
     * With null images info.
     * @see ItemController#createItem(com.reclabs.recomendar.model.documents.items.Item)
     */
    @Test(expected = RecIllegalArgumentException.class)
    public void createItemWithNullImagesUrl() {
        Item item = new Item();
        item.setName("test");
        item.setCategory("categoryTest");
        item.setDescription("descriptionTest");
        ItemTag tag = new ItemTag();
        tag.setName("tagNameTest");
        item.setTags(Arrays.asList(tag));
        item.setImagesURL(null);
        itemController.createItem(item);
    }

    /**
     * With empty shop info.
     * @see ItemController#createItem(com.reclabs.recomendar.model.documents.items.Item)
     */
    @Test(expected = RecIllegalArgumentException.class)
    public void createItemWithEmptyImagesUrl() {
        Item item = new Item();
        item.setName("test");
        item.setCategory("categoryTest");
        item.setDescription("descriptionTest");
        ItemTag tag = new ItemTag();
        tag.setName("tagNameTest");
        item.setTags(Arrays.asList(tag));
        item.setImagesURL(Arrays.asList(new URLPath()));
        itemController.createItem(item);
    }

    /**
     * @see ItemController#updateAffiliationManager(String, com.reclabs.recomendar.core.misc.items.UpdateAffiliationManagerInputData)
     */
    @Test
    public void updateAffiliationManagerWithDecimalMoney() {
        Item item = new Item();
        String itemId = "itemId";
        RecMoney price = RecMoney.parse("EUR 15.25");
        UpdateAffiliationManagerInputData updateAffiliationManagerInputData = new UpdateAffiliationManagerInputData();
        updateAffiliationManagerInputData.setPrice(price);
        updateAffiliationManagerInputData.setAffiliationManagerId("AffiliationManagerID");
        updateAffiliationManagerInputData.setUrlProductToManager("urlProductToManager");
        updateAffiliationManagerInputData.setAlias("Alias");
        Mockito.when(itemService.getById(itemId)).thenReturn(item);

        itemController.updateAffiliationManager(itemId, updateAffiliationManagerInputData);

        Mockito.verify(itemService, Mockito.times(1)).updateAffiliationManager(item, "Alias", "urlProductToManager", price);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void updateItemNullName() {
        Item item = new Item();
        item.setId("ID");
        itemController.updateItem(item);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void updateItemNullID() {
        Item item = new Item();
        itemController.updateItem(item);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void updateItemNullItem() {
        itemController.updateItem(null);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void updateItemNullDescription() {
        Item item = new Item();
        item.setId("ID");
        item.setName("NAME");
        itemController.updateItem(item);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void updateItemNullCategory() {
        Item item = new Item();
        item.setId("ID");
        item.setName("name");
        item.setDescription("description");
        itemController.updateItem(item);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void updateItemNullInformationURL() {
        Item item = new Item();
        item.setId("ID");
        item.setName("name");
        item.setDescription("description");
        item.setCategory("Category");
        itemController.updateItem(item);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void updateItemNullTags() {
        Item item = new Item();
        item.setId("ID");
        item.setName("name");
        item.setDescription("description");
        item.setCategory("Category");
        item.setInformationURL("InfoURL");
        itemController.updateItem(item);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void updateItemEmptyTags() {
        Item item = new Item();
        item.setId("ID");
        item.setName("name");
        item.setDescription("description");
        item.setCategory("Category");
        item.setInformationURL("InfoURL");
        item.setTags(new ArrayList<ItemTag>());
        itemController.updateItem(item);
    }


    @Test(expected = RecIllegalArgumentException.class)
    public void updateItemNullImagesURL() {
        Item item = new Item();
        item.setId("ID");
        item.setName("name");
        item.setDescription("description");
        item.setCategory("Category");
        item.setInformationURL("InfoURL");
        ItemTag itemTag = new ItemTag();
        itemTag.setName("tag1");
        item.setTags(Arrays.asList(itemTag));
        itemController.updateItem(item);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void updateItemEmptyImagesURL() {
        Item item = new Item();
        item.setId("ID");
        item.setName("name");
        item.setDescription("description");
        item.setCategory("Category");
        item.setInformationURL("InfoURL");
        ItemTag itemTag = new ItemTag();
        itemTag.setName("tag1");
        item.setTags(Arrays.asList(itemTag));
        item.setImagesURL(new ArrayList<URLPath>());
        itemController.updateItem(item);
    }


    @Test
    public void updateItemValidData() {
        Item oldItem = new Item();
        Item item = new Item();
        item.setId("ID");
        item.setName("name");
        item.setDescription("description");
        item.setCategory("Category");
        item.setInformationURL("InfoURL");
        ItemTag itemTag = new ItemTag();
        itemTag.setName("tag1");
        item.setTags(Arrays.asList(itemTag));
        URLPath urlPath = new URLPath();
        urlPath.setUrl("URL");
        item.setImagesURL(Arrays.asList(urlPath));

        Mockito.when(itemService.getById(item.getId())).thenReturn(oldItem);
        Mockito.when(categoryService.getCategoryDocByName(item.getCategory())).thenReturn(new Category());

        itemController.updateItem(item);

        Mockito.verify(itemService, Mockito.times(1)).updateItem(item, oldItem);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void getShortURLWithoutItemID() throws Exception {
        itemController.getShortURL(null, null);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void getShortURLWithEmptyItemID() throws Exception {
        itemController.getShortURL("", null);
    }

    @Test
    public void mostRecommendedWithHttpMessageNotWritableException() throws Exception {
        Mockito.when(esDriver.searchByQueryList(Mockito.any(ESFind.class), Mockito.eq(Item.class))).thenThrow(new HttpMessageNotWritableException("Error"));
        assertTrue(CollectionHelper.isEmpty(itemController.mostRecommended(2, 2)));
    }
}
