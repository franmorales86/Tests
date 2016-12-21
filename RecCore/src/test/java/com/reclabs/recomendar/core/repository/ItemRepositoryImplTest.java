/**
 * Project: RecCore
 * Created by: fjmorales at 19/11/2012 22:11:28
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.repository;

import com.reclabs.recomendar.common.helpers.types.DateHelper;
import com.reclabs.recomendar.common.types.DatePrecisionType;
import com.reclabs.recomendar.core.repository.generic.RepositoryTest;
import com.reclabs.recomendar.model.documents.Tag;
import com.reclabs.recomendar.model.documents.brand.AffiliationManager;
import com.reclabs.recomendar.model.documents.brand.Brand;
import com.reclabs.recomendar.model.documents.brand.data.AffiliationManagerData;
import com.reclabs.recomendar.model.documents.items.Item;
import com.reclabs.recomendar.model.documents.items.ItemTag;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * @author fjmorales
 */
public class ItemRepositoryImplTest extends RepositoryTest {
    private static final String TAG_REMOVE_ID = "TAG_REMOVE_ID";
    private ItemRepositoryImpl repoImpl;
    private MongoTemplate template;

    private Item initialize() {
        Item item = new Item();
        item.setName("testName");
        item.setDescription("descriptionTest");
        // Item.setState(StateItemType.ACTIVE);
        item.setCreatedDate(DateHelper.getCurrentDate(DatePrecisionType.MILLISECOND));
        item.setEan("eanTest");
        item.setIsbn("isbnTest");
        // Item.setType(ItemType.URL);
        // Item.setImageUrl("testUrl");
        item.setCategory("categoryName");
        item.setRecommendations(14L);
        Tag t = new Tag();
        t.setName("tagName");
        this.template.insert(t);
        Tag t2 = new Tag();
        t2.setName("tagName2");
        this.template.insert(t2);
        return item;
    }

    /**
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        this.template = createMongoTemplate();
        this.repoImpl = new ItemRepositoryImpl(this.template);
    }

    /**
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
        this.template.dropCollection(Item.class);
        this.template.dropCollection(Tag.class);
        template.dropCollection(AffiliationManager.class);
        template.dropCollection(Brand.class);
    }

    /**
     *
     */
    @Test
    public void testCreateUpdateItem() {
        Item Item = initialize();
        this.repoImpl.save(Item);
        List<Item> ItemList = this.template.findAll(Item.class);
        int samplesInCollection = ItemList.size();

        Item itemBBDD = ItemList.get(0);
        assertEquals("Only 1 Sample should exist collection, but there are " + samplesInCollection, 1, samplesInCollection);
        assertThat(itemBBDD, is(Item));

        this.repoImpl.save(Item);

        this.template.findById(Item.getId(), Item.class);
    }

    /**
     *
     */
    @Test
    public void testDeleteItem() {
        Item item = initialize();
        this.template.insert(item);

        this.repoImpl.delete(item);
        List<Item> itemList = this.template.findAll(Item.class);
        assertThat("No items", itemList.size(), is(0));

        this.template.insert(item);
        this.repoImpl.delete(item.getId());
        itemList = this.template.findAll(Item.class);
        assertThat("No items", itemList.size(), is(0));
    }

    /**
     * @see ItemRepositoryImpl#findByExactlyName(String)
     */
    @Test
    public void testFindByExactlyName() {
        Item item = initialize();
        Item item2 = initialize();
        item2.setName("testName2");
        this.template.insert(item);
        this.template.insert(item2);

        List<Item> itemList = this.repoImpl.findByExactlyName("TESTNAME");
        assertThat("Deberian existir 1 Itemos coincidentes", itemList.size(), is(1));

        itemList = this.repoImpl.findByExactlyName("testName2");
        assertThat("Deberian existir 1 Itemo coincidente", itemList.size(), is(1));

        itemList = this.repoImpl.findByExactlyName("est");
        assertThat("No deberian existir ningun item", itemList.size(), is(0));
    }

    /**
     * @see ItemRepositoryImpl#findByName(String)
     */
    @Test
    public void testFindByName() {
        Item Item = initialize();
        Item Item2 = initialize();
        Item2.setName("testName status");
        this.template.insert(Item);
        this.template.insert(Item2);

        List<Item> ItemList = this.repoImpl.findByName("ESTN");
        int samplesInCollection = ItemList.size();
        assertEquals("Deberian existir 2 Itemos coincidentes, pero hay " + samplesInCollection, 2, samplesInCollection);

        ItemList = this.repoImpl.findByName("ame stat");
        samplesInCollection = ItemList.size();
        assertEquals("Deberian existir 1 Itemo coincidente, pero hay " + samplesInCollection, 1, samplesInCollection);

        ItemList = this.repoImpl.findByName("testt");
        samplesInCollection = ItemList.size();
        assertEquals("No deberian existir ningun Itemo coincidente, pero hay " + samplesInCollection, 0, samplesInCollection);
    }

    /**
     * @see ItemRepositoryImpl#findOne(java.io.Serializable)
     */
    @Test
    public void testFindById() {
        Item Item = initialize();
        Item Item2 = initialize();
        Item2.setName("testName status");
        this.template.insert(Item);
        this.template.insert(Item2);

        Item ItemBBDD = this.repoImpl.findOne(Item.getId());
        assertThat(ItemBBDD, is(Item));

        ItemBBDD = this.repoImpl.findOne(Item2.getId());
        assertThat(ItemBBDD, is(Item2));

        ItemBBDD = this.repoImpl.findOne("Item3");
        assertNull(ItemBBDD);
    }

    /**
     * @see ItemRepositoryImpl#findByNameOrDescription(String)
     */
    @Test
    public void testFindByNameOrDescription() {
        Item Item = initialize();
        Item Item2 = initialize();
        Item2.setName("testName status");
        Item.setDescription("description2");
        this.template.insert(Item);
        this.template.insert(Item2);

        List<Item> ItemList = this.repoImpl.findByNameOrDescription("TESTNAME");
        int samplesInCollection = ItemList.size();
        assertEquals("Deberian existir 2 Itemos coincidentes, pero hay " + samplesInCollection, 2, samplesInCollection);

        ItemList = this.repoImpl.findByNameOrDescription("ame stat");
        samplesInCollection = ItemList.size();
        assertEquals("Deberian existir 1 Itemo coincidente, pero hay " + samplesInCollection, 1, samplesInCollection);

        ItemList = this.repoImpl.findByNameOrDescription("description");
        samplesInCollection = ItemList.size();
        assertEquals("Deberian existir 2 Itemos coincidentes, pero hay " + samplesInCollection, 2, samplesInCollection);

        ItemList = this.repoImpl.findByNameOrDescription("DESCRIPTION2");
        samplesInCollection = ItemList.size();
        assertEquals("Deberian existir 1 Itemo coincidente, pero hay " + samplesInCollection, 1, samplesInCollection);

        ItemList = this.repoImpl.findByNameOrDescription("testt");
        samplesInCollection = ItemList.size();
        assertEquals("No deberian existir ningun Itemo coincidente, pero hay " + samplesInCollection, 0, samplesInCollection);
    }

    /**
     * @return Gets simple item tags without remove ID
     */
    private List<ItemTag> getSimpleNoRemoveTags() {
        List<ItemTag> tags = new ArrayList<>();
        ItemTag itemTag1 = new ItemTag();
        itemTag1.setName("NoRemove");
        itemTag1.setTagId("NO_REMOVE_ID");
        tags.add(itemTag1);

        ItemTag itemTag2 = new ItemTag();
        itemTag2.setName("NoRemove2");
        itemTag2.setTagId("NO_REMOVE_2_ID");
        tags.add(itemTag2);
        return tags;
    }

    /**
     * @return Gets simple item tags with remove ID
     */
    public List<ItemTag> getSimpleRemoveTags() {
        List<ItemTag> tags = new ArrayList<>();
        ItemTag itemTag1 = new ItemTag();
        itemTag1.setName("NoRemove");
        itemTag1.setTagId("NO_REMOVE_ID");
        tags.add(itemTag1);

        ItemTag itemTag2 = new ItemTag();
        itemTag2.setName("Remove");
        itemTag2.setTagId(TAG_REMOVE_ID);
        tags.add(itemTag2);
        return tags;
    }

    /**
     * @see ItemRepositoryImpl#deleteTag(String)
     */
    @Test
    public void deleteTagSimpleData() {
        Item item = new Item();
        item.setTags(getSimpleNoRemoveTags());
        item.setId("ITEM_ID_1");
        Item item2 = new Item();
        item2.setTags(getSimpleRemoveTags());
        item2.setId("ITEM_ID_2");
        //
        template.insert(item);
        template.insert(item2);

        repoImpl.deleteTag(TAG_REMOVE_ID);

        assertThat(repoImpl.findAll().size(), is(2));
        assertThat(repoImpl.findOne("ITEM_ID_1").getTags().size(), is(2));
        assertThat(repoImpl.findOne("ITEM_ID_2").getTags().size(), is(1));
        assertThat(repoImpl.findOne("ITEM_ID_2").getTags().get(0).getTagId(), is("NO_REMOVE_ID"));
        assertThat(repoImpl.findOne("ITEM_ID_1").getTags().get(0).getTagId(), is("NO_REMOVE_ID"));
        assertThat(repoImpl.findOne("ITEM_ID_1").getTags().get(1).getTagId(), is("NO_REMOVE_2_ID"));
    }

    /**
     * @see ItemRepositoryImpl#deleteTag(String)
     */
    @Test
    public void deleteTagMultipleData() {
        Item item = new Item();
        item.setTags(getSimpleNoRemoveTags());
        item.setId("ITEM_ID_1");
        Item item2 = new Item();
        item2.setTags(getSimpleRemoveTags());
        item2.setId("ITEM_ID_2");
        Item item3 = new Item();
        item3.setId("ITEM_ID_3");
        item3.setTags(getSimpleRemoveTags());
        Item item4 = new Item();
        item4.setId("ITEM_ID_4");
        item4.setTags(getSimpleNoRemoveTags());
        //
        template.insert(item);
        template.insert(item2);
        template.insert(item3);
        template.insert(item4);

        repoImpl.deleteTag(TAG_REMOVE_ID);
        assertThat(repoImpl.findAll().size(), is(4));
        assertThat(repoImpl.findOne("ITEM_ID_1").getTags().size(), is(2));
        assertThat(repoImpl.findOne("ITEM_ID_2").getTags().size(), is(1));
        assertThat(repoImpl.findOne("ITEM_ID_3").getTags().size(), is(1));
        assertThat(repoImpl.findOne("ITEM_ID_4").getTags().size(), is(2));
        assertThat(repoImpl.findOne("ITEM_ID_2").getTags().get(0).getTagId(), is("NO_REMOVE_ID"));
        assertThat(repoImpl.findOne("ITEM_ID_1").getTags().get(0).getTagId(), is("NO_REMOVE_ID"));
        assertThat(repoImpl.findOne("ITEM_ID_1").getTags().get(1).getTagId(), is("NO_REMOVE_2_ID"));
        assertThat(repoImpl.findOne("ITEM_ID_3").getTags().get(0).getTagId(), is("NO_REMOVE_ID"));
        assertThat(repoImpl.findOne("ITEM_ID_4").getTags().get(0).getTagId(), is("NO_REMOVE_ID"));
        assertThat(repoImpl.findOne("ITEM_ID_4").getTags().get(1).getTagId(), is("NO_REMOVE_2_ID"));
    }

    @Test
    public void renameTagSimpleData() {
        Item item = new Item();
        item.setTags(getSimpleNoRemoveTags());
        item.setId("ITEM_ID_1");
        Item item2 = new Item();
        item2.setTags(getSimpleRemoveTags());
        item2.setId("ITEM_ID_2");
        //
        template.insert(item);
        template.insert(item2);

        repoImpl.renameTag(TAG_REMOVE_ID, "YesChanged");

        assertThat(repoImpl.findAll().size(), is(2));
        assertThat(repoImpl.findOne("ITEM_ID_1").getTags().size(), is(2));
        assertThat(repoImpl.findOne("ITEM_ID_1").getTags().get(0).getName(), is("NoRemove"));
        assertThat(repoImpl.findOne("ITEM_ID_1").getTags().get(1).getName(), is("NoRemove2"));

        assertThat(repoImpl.findOne("ITEM_ID_2").getTags().size(), is(2));
        assertThat(repoImpl.findOne("ITEM_ID_2").getTags().get(0).getName(), is("NoRemove"));
        assertThat(repoImpl.findOne("ITEM_ID_2").getTags().get(0).getTagId(), is("NO_REMOVE_ID"));
        assertThat(repoImpl.findOne("ITEM_ID_2").getTags().get(1).getTagId(), is(TAG_REMOVE_ID));
        assertThat(repoImpl.findOne("ITEM_ID_2").getTags().get(1).getName(), is("YesChanged"));
    }

    @Test
    public void renameTagMultipleData() {
        Item item = new Item();
        item.setTags(getSimpleNoRemoveTags());
        item.setId("ITEM_ID_1");
        Item item2 = new Item();
        item2.setTags(getSimpleRemoveTags());
        item2.setId("ITEM_ID_2");
        Item item3 = new Item();
        item3.setId("ITEM_ID_3");
        item3.setTags(getSimpleRemoveTags());
        Item item4 = new Item();
        item4.setId("ITEM_ID_4");
        item4.setTags(getSimpleNoRemoveTags());
        //
        template.insert(item);
        template.insert(item2);
        template.insert(item3);
        template.insert(item4);
        //
        template.insert(item);
        template.insert(item2);

        repoImpl.renameTag(TAG_REMOVE_ID, "YesChanged");

        assertThat(repoImpl.findAll().size(), is(4));
        assertThat(repoImpl.findOne("ITEM_ID_1").getTags().size(), is(2));
        assertThat(repoImpl.findOne("ITEM_ID_1").getTags().get(0).getName(), is("NoRemove"));
        assertThat(repoImpl.findOne("ITEM_ID_1").getTags().get(1).getName(), is("NoRemove2"));

        assertThat(repoImpl.findOne("ITEM_ID_2").getTags().size(), is(2));
        assertThat(repoImpl.findOne("ITEM_ID_2").getTags().get(0).getName(), is("NoRemove"));
        assertThat(repoImpl.findOne("ITEM_ID_2").getTags().get(0).getTagId(), is("NO_REMOVE_ID"));
        assertThat(repoImpl.findOne("ITEM_ID_2").getTags().get(1).getTagId(), is(TAG_REMOVE_ID));
        assertThat(repoImpl.findOne("ITEM_ID_2").getTags().get(1).getName(), is("YesChanged"));

        assertThat(repoImpl.findOne("ITEM_ID_3").getTags().size(), is(2));
        assertThat(repoImpl.findOne("ITEM_ID_3").getTags().get(0).getName(), is("NoRemove"));
        assertThat(repoImpl.findOne("ITEM_ID_3").getTags().get(0).getTagId(), is("NO_REMOVE_ID"));
        assertThat(repoImpl.findOne("ITEM_ID_3").getTags().get(1).getTagId(), is(TAG_REMOVE_ID));
        assertThat(repoImpl.findOne("ITEM_ID_3").getTags().get(1).getName(), is("YesChanged"));

        assertThat(repoImpl.findOne("ITEM_ID_4").getTags().size(), is(2));
        assertThat(repoImpl.findOne("ITEM_ID_4").getTags().get(0).getName(), is("NoRemove"));
        assertThat(repoImpl.findOne("ITEM_ID_4").getTags().get(1).getName(), is("NoRemove2"));
    }

    @Test
    public void findByDomainURL() throws Exception {
        Item item1 = new Item();
        item1.setInformationURL("http://es.dawanda.com/product/41993434-Camiseta-Free-Soul");
        template.insert(item1);

        Item item2 = new Item();
        item2.setInformationURL("dawanda.com");
        template.insert(item2);

        Item item3 = new Item();
        item3.setInformationURL("http://nike.com.dawanda.com");
        template.insert(item3);

        Item item4 = new Item();
        item4.setInformationURL("http://zalando.es/dawanda/zapatos");
        template.insert(item4);

        Item item5 = new Item();
        item5.setInformationURL("http://zalando.es/dawanda.com/zapatos");
        template.insert(item5);

        List<Item> items = repoImpl.findByDomainURL("dawanda.com");
        assertThat(items.size(), is(4));
    }

    @Test
    public void findItemsByAffiliationManagerSimpleData() {
//        String affManagerAlias = "AFF_MANAGER_1";
//        AffiliationManager manager1 = new AffiliationManager();
//        manager1.setAlias(affManagerAlias);
//        template.insert(manager1);
//
//        AffiliationManagerData managerData1 = new AffiliationManagerData();
//        managerData1.setAffiliationManagerId(manager1.getId());
//        managerData1.setAlias(manager1.getAlias());
//        managerData1.setUrlResult("Result1");
//
//        AffiliationManagerData managerData2 = new AffiliationManagerData();
//        managerData2.setAffiliationManagerId(manager1.getId());
//        managerData2.setAlias(manager1.getAlias());
//        managerData2.setUrlResult("Result2");
//
//        List<AffiliationManagerData> affiliationManagerDataList = new ArrayList<>();
//        affiliationManagerDataList.add(managerData1);
//        affiliationManagerDataList.add(managerData2);
//
//        Item item = new Item();
//        item.setName("Item1");
//        item.setAffiliationManagerDataList(affiliationManagerDataList);
//        template.insert(item);
//
//        List<Item> result = repoImpl.findItemsByAffiliationManager(affManagerAlias);
//        assertThat(result.size(), is(1));
//        assertThat(result.get(0).getName(), is("Item1"));
    }

    @Test
    public void findItemsByBrandNameSimpleData() {
//        String brandNameToFind = "BrandNameToFind";
//        Brand brand1 = new Brand();
//        brand1.setName(brandNameToFind);
//        template.insert(brand1);
//
//        Brand brand2 = new Brand();
//        brand2.setName("Brand2");
//        template.insert(brand2);
//
//        Item item1 = new Item();
//        item1.setBrandList(Arrays.asList(brand1));
//        item1.setName("item1");
//        template.insert(item1);
//
//        Item item2 = new Item();
//        item2.setName("item2");
//        template.insert(item2);
//
//        assertThat(repoImpl.findItemsByBrandName("Brand2").size(), is(0));
//        List<Item> result = repoImpl.findItemsByBrandName(brandNameToFind);
//        assertThat(result.size(), is(1));
//        assertThat(result.get(0).getName(), is("item1"));
    }

    //FIXME GOGOGO!!
    //verifyAffiliationManagerByItemId
    //verifyBrandByItemId
    //verifyAffiliationProviderByItemId

    /**
     * @see ItemRepositoryImpl#findByIds(List)
     */
    @Test
    public void findByIdsWithAnyItems() {
        String itemId1 = "0000FF";
        String itemId2 = "00FF00";
        String itemId3 = "FF0000";

        Item item1 = new Item();
        item1.setId(itemId1);
        Item item2 = new Item();
        item2.setId(itemId2);
        Item item3 = new Item();
        item3.setId(itemId3);
        Item item4 = new Item();
        item4.setId("000000");
        Item item5 = new Item();
        item5.setId("FFFFFF");

        repoImpl.save(item1);
        repoImpl.save(item2);
        repoImpl.save(item3);
        repoImpl.save(item4);
        repoImpl.save(item5);

        List<Item> resultList = repoImpl.findByIds(Arrays.asList(itemId1, itemId2, itemId3));

        assertTrue("The size should be 3, but was " + resultList.size(), resultList.size() == 3);
        assertTrue("The item1 was not found", resultList.contains(item1));
        assertTrue("The item2 was not found", resultList.contains(item2));
        assertTrue("The item3 was not found", resultList.contains(item3));
    }

    /**
     * @see ItemRepositoryImpl#findByIdsOrderByCreatedDateDesc(List)
     */
    @Test
    public void findByIdsOrderByCreatedDateDesc() {
        String itemId1 = "0000FF";
        String itemId2 = "00FF00";
        String itemId3 = "FF0000";

        Item item1 = new Item();
        item1.setId(itemId1);
        item1.setCreatedDate(DateHelper.getFixedDate(2010, 10, 9));
        Item item2 = new Item();
        item2.setId(itemId2);
        item2.setCreatedDate(DateHelper.getFixedDate(2010, 10, 10));
        Item item3 = new Item();
        item3.setId(itemId3);
        item3.setCreatedDate(DateHelper.getFixedDate(2010, 10, 11));
        Item item4 = new Item();
        item4.setId("000000");
        item4.setCreatedDate(DateHelper.getFixedDate(2010, 10, 12));
        Item item5 = new Item();
        item5.setId("FFFFFF");
        item5.setCreatedDate(DateHelper.getFixedDate(2010, 10, 13));

        repoImpl.save(item1);
        repoImpl.save(item2);
        repoImpl.save(item3);
        repoImpl.save(item4);
        repoImpl.save(item5);

        List<Item> resultList = repoImpl.findByIdsOrderByCreatedDateDesc(Arrays.asList(itemId1, itemId2, itemId3));

        assertTrue("The size should be 3, but was " + resultList.size(), resultList.size() == 3);
        assertTrue("The item1 was not found in position 2", resultList.get(2).equals(item1));
        assertTrue("The item2 was not found in position 1", resultList.get(1).equals(item2));
        assertTrue("The item3 was not found in position 0", resultList.get(0).equals(item3));
        assertFalse("The item4 was found", resultList.contains(item4));
    }

    /**
     * @see ItemRepositoryImpl#findItemsByAffiliationManager
     */
    @Test
    public void findItemsByAffiliationManageraaa() {
        AffiliationManagerData affiliationManagerDataList1 = new AffiliationManagerData();
        affiliationManagerDataList1.setAlias("alias1");
        AffiliationManagerData affiliationManagerDataList2 = new AffiliationManagerData();
        affiliationManagerDataList2.setAlias("alias2");
        AffiliationManagerData affiliationManagerDataList3 = new AffiliationManagerData();
        affiliationManagerDataList3.setAlias("alias3");
        AffiliationManagerData affiliationManagerDataList4 = new AffiliationManagerData();
        affiliationManagerDataList4.setAlias("alias4");

        Item item1 = new Item();
        item1.setId("item1");
        item1.setAffiliationManagerDataList(Arrays.asList(affiliationManagerDataList1));
        Item item2 = new Item();
        item2.setId("item2");
        item2.setAffiliationManagerDataList(Arrays.asList(affiliationManagerDataList1, affiliationManagerDataList2));
        Item item3 = new Item();
        item3.setId("item3");
        item3.setAffiliationManagerDataList(Arrays.asList(affiliationManagerDataList2, affiliationManagerDataList3));
        Item item4 = new Item();
        item4.setId("item4");
        item4.setAffiliationManagerDataList(Arrays.asList(affiliationManagerDataList3, affiliationManagerDataList4));

        repoImpl.save(item1);
        repoImpl.save(item2);
        repoImpl.save(item3);
        repoImpl.save(item4);

        List<Item> result = repoImpl.findItemsByAffiliationManager("alias1");

        assertTrue("The size should be 2, but was " + result.size(), result.size() == 2);
        assertTrue("The item1 was not found", result.contains(item1));
        assertTrue("The item2 was not found", result.contains(item2));
        assertTrue("The item3 was found", !result.contains(item3));
        assertTrue("The item4 was found", !result.contains(item4));
    }

    /**
     * @see ItemRepositoryImpl#findByOriginImageURL
     */
    @Test
    public void findByOriginImageURL() {
        String originImageURL1 = "http://www.example.com/AB12CD34/qwerty/image1.png";
        String originImageURL2 = "http://www.example.com/AB12CD34/qwerty/image2.png";
        String originImageURL3 = "http://www.example.com/AB12CD34/qwerty/image3.png";

        Item item1 = new Item();
        item1.setId("item1");
        item1.setOriginImageURL(originImageURL1);
        Item item2 = new Item();
        item2.setId("item2");
        item2.setOriginImageURL(originImageURL2);
        Item item3 = new Item();
        item3.setId("item3");
        item3.setOriginImageURL(originImageURL3);

        repoImpl.save(item1);
        repoImpl.save(item2);
        repoImpl.save(item3);

        Item result = repoImpl.findByOriginImageURL(originImageURL1);

        assertTrue("The item1 was not found", result.equals(item1));
    }

}
