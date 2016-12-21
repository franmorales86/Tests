/**
 * Project: RecCore
 * Created by: raulanatol at 04/12/2012 17:15:35
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.services;

import com.reclabs.recomendar.common.exceptions.RecIllegalArgumentException;
import com.reclabs.recomendar.common.helpers.types.CollectionHelper;
import com.reclabs.recomendar.common.helpers.types.DateHelper;
import com.reclabs.recomendar.common.types.RecMoney;
import com.reclabs.recomendar.core.misc.security.SecurityHelper;
import com.reclabs.recomendar.model.documents.brand.AffiliationManager;
import com.reclabs.recomendar.model.documents.brand.Brand;
import com.reclabs.recomendar.model.documents.brand.data.AffiliationManagerData;
import com.reclabs.recomendar.model.documents.brand.data.BrandData;
import com.reclabs.recomendar.model.documents.items.Item;
import com.reclabs.recomendar.model.documents.items.URLPath;
import com.reclabs.recomendar.model.documents.users.User;
import com.reclabs.recomendar.model.exceptions.user.UserNoHaveMoreRecommendationActionException;
import com.reclabs.recomendar.model.repositories.items.ItemRepository;
import com.reclabs.recomendar.model.services.ImageService;
import com.reclabs.recomendar.model.services.RecommendationService;
import com.reclabs.recomendar.model.services.TagService;
import com.reclabs.recomendar.model.services.brand.AffiliationManagerService;
import com.reclabs.recomendar.model.services.brand.BrandService;
import com.reclabs.recomendar.model.services.items.ItemHaveService;
import com.reclabs.recomendar.model.services.items.ItemToVerifyService;
import com.reclabs.recomendar.model.services.items.ItemWantService;
import com.reclabs.recomendar.model.types.SecurityRole;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.mongodb.util.MyAsserts.assertFalse;
import static junit.framework.Assert.assertNull;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * @author raulanatol
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(SecurityHelper.class)
@PowerMockIgnore({"javax.management.*", "javax.xml.parsers.*", "com.sun.org.apache.xerces.internal.jaxp.*", "ch.qos.logback.*", "org.slf4j.*"})
public class ItemServiceImplTest {

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private ItemToVerifyService itemToVerifyService;

    @Mock
    private RecommendationService recommendationService;

    @Mock
    private ImageService imageService;

    @Mock
    private TagService tagService;

    @Mock
    private BrandService brandService;

    @Mock
    private ItemWantService itemWantService;

    @Mock
    private ItemHaveService itemHaveService;

    @Mock
    private AffiliationManagerService affiliationManagerService;

    @InjectMocks
    private ItemServiceImpl itemService = new ItemServiceImpl();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void updateAffiliationManagersByNewItemWithEmptyResultData() {
        String informationURL = "http://www.etsy.com/Zapatos/muje/12313.html#asdad";
        List<AffiliationManager> affiliationManagerList = new ArrayList<>();

        Mockito.when(affiliationManagerService.findAffiliationManagerByItemInformationURL(informationURL)).thenReturn(affiliationManagerList);
        Item item = new Item();
        item.setInformationURL(informationURL);
        assertNull(item.getAffiliationManagerDataList());

        itemService.updateAffiliationManagersByNewItem(item);
        assertTrue(CollectionHelper.isEmpty(item.getAffiliationManagerDataList()));
    }

    @Test
    public void updateAffiliationManagersByNewItemWithSimpleResultData() {
        String informationURL = "http://www.etsy.com/Zapatos/muje/12313.html#asdad";
        List<AffiliationManager> affiliationManagerList = new ArrayList<>();
        AffiliationManager affiliationManager1 = new AffiliationManager();
        affiliationManager1.setDomain("etsy.com");
        affiliationManager1.setEnabled(true);
        affiliationManager1.setPattern("http://affiliation.com/XXX/");
        affiliationManagerList.add(affiliationManager1);

        Mockito.when(affiliationManagerService.findAffiliationManagerByItemInformationURL(informationURL)).thenReturn(affiliationManagerList);
        Item item = new Item();
        item.setInformationURL(informationURL);
        assertNull(item.getAffiliationManagerDataList());

        itemService.updateAffiliationManagersByNewItem(item);
        assertThat(item.getAffiliationManagerDataList().size(), is(1));
        assertThat(item.getAffiliationManagerDataList().get(0).getUrlProductToManager(), is(informationURL));
        assertThat(item.getAffiliationManagerDataList().get(0).getUrlResult(), is("http://affiliation.com/http://www.etsy.com/Zapatos/muje/12313.html#asdad/"));
    }

    /**
     * No different between updateAffiliationManagersByNewItemWithSimpleResultData and this test.
     * Because if the affiliation manager is disabled the method to test "getAffiliationManagerByItemURL" works to the same wide.
     */
    @Test
    public void updateAffiliationManagersByNewItemWithSimpleResultDataDisabled() {
        String informationURL = "http://www.etsy.com/Zapatos/muje/12313.html#asdad";
        List<AffiliationManager> affiliationManagerList = new ArrayList<>();
        AffiliationManager affiliationManager1 = new AffiliationManager();
        affiliationManager1.setDomain("etsy.com");
        affiliationManager1.setEnabled(false);
        affiliationManager1.setPattern("http://affiliation.com/XXX/");
        affiliationManagerList.add(affiliationManager1);

        Mockito.when(affiliationManagerService.findAffiliationManagerByItemInformationURL(informationURL)).thenReturn(affiliationManagerList);
        Item item = new Item();
        item.setInformationURL(informationURL);
        assertNull(item.getAffiliationManagerDataList());

        itemService.updateAffiliationManagersByNewItem(item);
        assertThat(item.getAffiliationManagerDataList().size(), is(1));
        assertThat(item.getAffiliationManagerDataList().get(0).getUrlProductToManager(), is(informationURL));
        assertThat(item.getAffiliationManagerDataList().get(0).getUrlResult(), is("http://affiliation.com/http://www.etsy.com/Zapatos/muje/12313.html#asdad/"));
    }

    @Test
    public void enableOrAddAffiliationManagerInItemsWithSimpleDataWithPatternToEliminate() {
//        AffiliationManager affiliationManager = new AffiliationManager();
//        affiliationManager.setEnabled(true);
//        affiliationManager.setPattern("http://www.happycosas.com/happycosas-shop/?tt=8310_12_125388_&r=XXX");
//        affiliationManager.setDomain("happycosas.com");
//        affiliationManager.setAlias("ALIAS");
//        affiliationManager.setAffiliationProvider(null);
//        affiliationManager.setEliminatePattern("http://www.happycosas.com");
//        affiliationManager.setVendorRegionType(VendorRegionType.ALL);
//
//        String expectedItemURLToSell = "http://www.happycosas.com/happycosas-shop/?tt=8310_12_125388_&r=/comedero-esferico-para-pajaros-eva-solo.html";
//        String domainToSearch = "happycosas.com";
//
//        Item item = new Item();
//        item.setInformationURL("http://www.happycosas.com/comedero-esferico-para-pajaros-eva-solo.html");
//        item.setId("DummyItem1");
//        List<Item> itemsToUpdate = new ArrayList<>();
//        itemsToUpdate.add(item);
//        Mockito.when(itemRepository.findByDomainURL(domainToSearch)).thenReturn(itemsToUpdate);
//
//        itemService.enableOrAddAffiliationManagerInItems(affiliationManager);
//
//        assertThat(itemsToUpdate.get(0).getAffiliationManagerDataList().size(), is(1));
//        assertThat(itemsToUpdate.get(0).getAffiliationManagerDataList().get(0).getUrlResult(), is(expectedItemURLToSell));
//        verify(itemRepository, times(1)).save(itemsToUpdate);
    }

    @Test
    public void enableOrAddAffiliationManagerInItemsWithSimpleDataWithoutPatternToEliminate() {
//        AffiliationManager affiliationManager = new AffiliationManager();
//        affiliationManager.setEnabled(true);
//        affiliationManager.setPattern("http://track.webgains.com/click.html?wgcampaignid=133005&;wgprogramid=6993&wgtarget=XXX");
//        affiliationManager.setDomain("dawanda.com");
//        affiliationManager.setAlias("ALIAS");
//        affiliationManager.setAffiliationProvider(null);
//        affiliationManager.setEliminatePattern(null);
//        affiliationManager.setVendorRegionType(VendorRegionType.ALL);
//
//        String expectedItemURLToSell = "http://track.webgains.com/click.html?wgcampaignid=133005&;wgprogramid=6993&wgtarget=http://es.dawanda.com/product/18369861-Handbemalte-Schuhe-TOILE-DE-FEMME-Unikate";
//        String domainToSearch = "dawanda.com";
//
//        Item item = new Item();
//        item.setInformationURL("http://es.dawanda.com/product/18369861-Handbemalte-Schuhe-TOILE-DE-FEMME-Unikate");
//        item.setId("DummyItem1");
//        List<Item> itemsToUpdate = new ArrayList<>();
//        itemsToUpdate.add(item);
//        Mockito.when(itemRepository.findByDomainURL(domainToSearch)).thenReturn(itemsToUpdate);
//
//        itemService.enableOrAddAffiliationManagerInItems(affiliationManager);
//
//        assertThat(itemsToUpdate.get(0).getAffiliationManagerDataList().size(), is(1));
//        assertThat(itemsToUpdate.get(0).getAffiliationManagerDataList().get(0).getUrlResult(), is(expectedItemURLToSell));
//        verify(itemRepository, times(1)).save(itemsToUpdate);
    }

    @Test
    public void enableOrAddAffiliationManagerInItemsWithSimpleDataWithoutPatternToEliminateV2() {
//        AffiliationManager affiliationManager = new AffiliationManager();
//        affiliationManager.setEnabled(true);
//        affiliationManager.setPattern("http://action.metaffiliation.com/suivi.php?mclic=S4458955903319&redir=XXX");
//        affiliationManager.setDomain("dcshoes.es");
//        affiliationManager.setAlias("ALIAS");
//        affiliationManager.setAffiliationProvider(null);
//        affiliationManager.setEliminatePattern(null);
//        affiliationManager.setVendorRegionType(VendorRegionType.ALL);
//
//        String expectedItemURLToSell = "http://action.metaffiliation.com/suivi.php?mclic=S4458955903319&redir=http://www.dcshoes.es/es/council-tx/d0320305-/D0320305-,es_ES,pd.html#?intcmp=dc_es_shop_home-mea-3:dc_es_shop_%20push_pdt_council";
//        String domainToSearch = "dcshoes.es";
//
//        Item item = new Item();
//        item.setInformationURL("http://www.dcshoes.es/es/council-tx/d0320305-/D0320305-,es_ES,pd.html#?intcmp=dc_es_shop_home-mea-3:dc_es_shop_%20push_pdt_council");
//        item.setId("DummyItem1");
//        List<Item> itemsToUpdate = new ArrayList<>();
//        itemsToUpdate.add(item);
//        Mockito.when(itemRepository.findByDomainURL(domainToSearch)).thenReturn(itemsToUpdate);
//
//        itemService.enableOrAddAffiliationManagerInItems(affiliationManager);
//
//        assertThat(itemsToUpdate.get(0).getAffiliationManagerDataList().size(), is(1));
//        assertThat(itemsToUpdate.get(0).getAffiliationManagerDataList().get(0).getUrlResult(), is(expectedItemURLToSell));
//        verify(itemRepository, times(1)).save(itemsToUpdate);
    }


    @Test
    public void updatePriceFromAffiliationManagerWithSimpleData() throws Exception {
        AffiliationManagerData affiliationManagerData1 = new AffiliationManagerData();
        affiliationManagerData1.setAlias("alias1");

        AffiliationManagerData affiliationManagerData2 = new AffiliationManagerData();
        affiliationManagerData1.setAlias("alias2");

        List<AffiliationManagerData> list = new ArrayList<>();
        list.add(affiliationManagerData1);
        list.add(affiliationManagerData2);


        Item item = new Item();
        item.setAffiliationManagerDataList(list);

        AffiliationManager affiliationManager = new AffiliationManager();
        affiliationManager.setAlias("alias2");

        RecMoney price = RecMoney.parse("USD 25");

        itemService.updatePriceFromAffiliationManager(item, affiliationManager, price);

        Mockito.verify(itemRepository, Mockito.times(1)).save(item);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void updatePriceFromAffiliationManagerWithInvalidAlias() throws Exception {
        AffiliationManagerData affiliationManagerData1 = new AffiliationManagerData();
        affiliationManagerData1.setAlias("alias1");

        AffiliationManagerData affiliationManagerData2 = new AffiliationManagerData();
        affiliationManagerData1.setAlias("alias2");

        List<AffiliationManagerData> list = new ArrayList<>();
        list.add(affiliationManagerData1);
        list.add(affiliationManagerData2);

        Item item = new Item();
        item.setAffiliationManagerDataList(list);

        AffiliationManager affiliationManager = new AffiliationManager();
        affiliationManager.setAlias("alias3");

        RecMoney price = RecMoney.parse("USD 25");

        itemService.updatePriceFromAffiliationManager(item, affiliationManager, price);
    }

    @Test
    public void updateBrandListAfterRemoveAMDEmptyBrand() throws Exception {
        AffiliationManagerData affiliationManagerDeleted = new AffiliationManagerData();
        affiliationManagerDeleted.setAlias("NIKE_ZANOX_ALL");

        BrandData brandData = new BrandData();
        brandData.setName("NIKE");

        Item item = new Item();
        item.setAffiliationManagerDataList(null);
        item.setBrandDataList(Arrays.asList(brandData));

        itemService.updateBrandDataListAfterRemoveAffiliationData(item, affiliationManagerDeleted);
        assertTrue(CollectionHelper.isEmpty(item.getBrandDataList()));
    }

    @Test
    public void updateBrandListAfterRemoveAMDKeepBrand() throws Exception {
        AffiliationManagerData affiliationManagerDeleted = new AffiliationManagerData();
        affiliationManagerDeleted.setAlias("NIKE_ZANOX_UK");

        AffiliationManagerData affiliationManagerKeep = new AffiliationManagerData();
        affiliationManagerKeep.setAlias("NIKE_ZANOX_ES");

        BrandData brandData = new BrandData();
        brandData.setName("NIKE");

        Item item = new Item();
        item.setAffiliationManagerDataList(Arrays.asList(affiliationManagerKeep));
        item.setBrandDataList(Arrays.asList(brandData));

        itemService.updateBrandDataListAfterRemoveAffiliationData(item, affiliationManagerDeleted);
        assertThat(item.getBrandDataList().size(), is(1));
        assertThat(item.getAffiliationManagerDataList().size(), is(1));
        assertThat(item.getBrandDataList().get(0).getName(), is("NIKE"));
        assertThat(item.getAffiliationManagerDataList().get(0).getAlias(), is("NIKE_ZANOX_ES"));
    }

    @Test
    public void updateBrandListAfterRemoveAMDRemoveBrand() throws Exception {
        AffiliationManagerData affiliationManagerDeleted = new AffiliationManagerData();
        affiliationManagerDeleted.setAlias("NIKE_ZANOX_UK");

        AffiliationManagerData affiliationManagerKeep = new AffiliationManagerData();
        affiliationManagerKeep.setAlias("ZALANDO_ZANOX_ES");

        BrandData brandData = new BrandData();
        brandData.setName("NIKE");
        brandData.setId(new ObjectId().toStringMongod());

        BrandData brandData2 = new BrandData();
        brandData2.setName("ZALANDO");
        brandData2.setId(new ObjectId().toStringMongod());

        List<AffiliationManagerData> affiliationManagerDataList = new ArrayList<>();
        affiliationManagerDataList.add(affiliationManagerKeep);

        List<BrandData> brandDataList = new ArrayList<>();
        brandDataList.add(brandData);
        brandDataList.add(brandData2);

        Item item = new Item();
        item.setId(new ObjectId().toStringMongod());
        item.setAffiliationManagerDataList(affiliationManagerDataList);
        item.setBrandDataList(brandDataList);

        itemService.updateBrandDataListAfterRemoveAffiliationData(item, affiliationManagerDeleted);
        assertThat(item.getBrandDataList().size(), is(1));
        assertThat(item.getAffiliationManagerDataList().size(), is(1));
        assertThat(item.getBrandDataList().get(0).getName(), is("ZALANDO"));
        assertThat(item.getAffiliationManagerDataList().get(0).getAlias(), is("ZALANDO_ZANOX_ES"));
    }

    @Test
    public void verifyItemIsDuplicatedWithNullInformationURL() throws Exception {
        String itemID = "itemID";
        Item item = new Item();
        item.setId(itemID);

        Mockito.when(itemRepository.findOne(itemID)).thenReturn(null);

        itemService.verifyItemIsDuplicated(item);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void getByIdWithNullItemId() throws Exception {
        itemService.getById(null);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void getByIdWithEmptyItemId() throws Exception {
        itemService.getById("");
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void updateItemWithNullItem() throws Exception {
        itemService.updateItem(null, new Item());
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void updateItemWithNullOldItem() throws Exception {
        itemService.updateItem(new Item(), null);
    }

    @Test
    public void updateItemCheckingSave1() throws Exception {
        Item item = new Item();
        Item oldItem = new Item();
        item.setId("abcd1234");
        oldItem.setId("1234abcd");

        List<URLPath> newList = new ArrayList<>();
        List<URLPath> oldList = new ArrayList<>();

        URLPath externalUrl = new URLPath();
        URLPath internUrl1 = new URLPath();
        URLPath internUrl2 = new URLPath();
        URLPath internUrl3 = new URLPath();

        externalUrl.setUrl("http://www.example.com/something");
        internUrl1.setUrl("http://images.recstatic.com/sometrue");
        internUrl2.setUrl("recstatic.com/greatdomain");
        internUrl3.setUrl("recitems.s3.amazonaws.com/awesome");

        newList.add(0, externalUrl);
        newList.add(1, internUrl1);
        newList.add(2, internUrl2);
        newList.add(3, internUrl3);

        item.setImagesURL(newList);
        oldItem.setImagesURL(oldList);

        // In uploadImages method check only if image does not belong to Recomendar images
        Mockito.when(imageService.createImage2Item(item.getId(), "")).thenReturn(externalUrl.getUrl());

        itemService.updateItem(item, oldItem);

        Mockito.verify(itemRepository, Mockito.times(1)).save(item);
    }

    @Test
    public void updateItemCheckingSave2() throws Exception {
        Item item = new Item();
        Item oldItem = new Item();
        item.setId("abcd1234");
        oldItem.setId("1234abcd");

        List<URLPath> newList = new ArrayList<>();
        List<URLPath> oldList = new ArrayList<>();

        URLPath externalURL = new URLPath();
        URLPath internUrl1 = new URLPath();
        URLPath internUrl2 = new URLPath();
        URLPath internUrl3 = new URLPath();

        externalURL.setUrl("http://www.example.com/something");
        internUrl1.setUrl("http://images.recstatic.com/sometrue");
        internUrl2.setUrl("recstatic.com/greatdomain");
        internUrl3.setUrl("recitems.s3.amazonaws.com/awesome");

        newList.add(0, externalURL);
        newList.add(1, internUrl1);
        newList.add(2, internUrl2);
        newList.add(3, internUrl3);

        item.setImagesURL(newList);
        oldItem.setImagesURL(oldList);

        // In uploadImages method check only if image belongs to Recomendar images
        Mockito.when(imageService.createImage2Item(item.getId(), "")).thenReturn(internUrl2.getUrl());

        itemService.updateItem(item, oldItem);

        Mockito.verify(itemRepository, Mockito.times(1)).save(item);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void createItemWithNullItem() throws Exception {
        User user = new User();
        itemService.createItem(null, user);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void createItemWithNullUser() throws Exception {
        Item item = new Item();
        itemService.createItem(item, null);
    }

    @Test(expected = UserNoHaveMoreRecommendationActionException.class)
    public void createItemWithoutPendingRecommendationsAndNoSandboxUser() throws Exception {
        Item item = new Item();
        User user = new User();
        user.setPendingRecommends(0L);
        user.setRoles(Arrays.asList(SecurityRole.ROLE_USER));
        PowerMockito.mockStatic(SecurityHelper.class);

        Mockito.when(SecurityHelper.getLoggedInUser()).thenReturn(user);

        Mockito.when(itemRepository.save(item)).thenReturn(item);
        Mockito.when(recommendationService.userCanBeRecommendItem(user)).thenReturn(false);

        itemService.createItem(item, user);

        Mockito.verify(itemRepository, Mockito.never()).save(item);
    }

    @Test
    public void createItemWithoutPendingRecommendationsAndSandboxUser() throws Exception {
        Item item = new Item();
        item.setImagesURL(new ArrayList<URLPath>());

        User user = new User();
        user.setId("USER_ID");
        user.setPendingRecommends(0L);
        user.setRoles(Arrays.asList(SecurityRole.ROLE_USER, SecurityRole.ROLE_SANDBOX_ADMIN));


        PowerMockito.mockStatic(SecurityHelper.class);

        Mockito.when(SecurityHelper.getLoggedInUser()).thenReturn(user);

        Mockito.when(itemRepository.save(item)).thenReturn(item);
        Mockito.when(recommendationService.userCanBeRecommendItem(user.getId())).thenReturn(true);

        itemService.createItem(item, user);

        Mockito.verify(itemRepository, Mockito.times(1)).save(item);
    }

    @Test
    public void createItemCheckingSave() throws Exception {
        User userLogger = new User();
        userLogger.setId("JUnitTest");

        Item item = new Item();
        item.setId("JUnitTest");
        item.setImagesURL(new ArrayList<URLPath>());

        PowerMockito.mockStatic(SecurityHelper.class);

        Mockito.when(SecurityHelper.getLoggedInUser()).thenReturn(userLogger);

        Mockito.when(itemRepository.save(item)).thenReturn(item);
        Mockito.when(recommendationService.userCanBeRecommendItem(userLogger.getId())).thenReturn(true);

        itemService.createItem(item, userLogger);

        Mockito.verify(itemRepository, Mockito.times(1)).save(item);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void createItemByNormalUserWithNullItem() throws Exception {
        itemService.createItemByNormalUser(null, new User());
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void createItemByNormalUserWithNullUser() throws Exception {
        itemService.createItemByNormalUser(new Item(), null);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void deleteItemByIdWithNullItemId() throws Exception {
        itemService.deleteItemById(null);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void createItemByNormalUserWithEmptyItemId() throws Exception {
        itemService.deleteItemById("");
    }

    @Test
    public void deleteItemByIdCheckingDelete() throws Exception {
        String itemId = "4d3ca1b2";

        itemService.deleteItemById(itemId);

        Mockito.verify(recommendationService, Mockito.times(1)).deleteItemByItemId(itemId);
        Mockito.verify(itemWantService, Mockito.times(1)).deleteItemByItemId(itemId);
        Mockito.verify(itemHaveService, Mockito.times(1)).deleteItemByItemId(itemId);
        Mockito.verify(itemToVerifyService, Mockito.times(1)).deleteItemByItemId(itemId);
        Mockito.verify(itemRepository, Mockito.times(1)).delete(itemId);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void addAffiliationManagerWithItemWithNullItem() throws Exception {
        itemService.addAffiliationManager(null, new Brand(), "", "", new RecMoney());
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void addAffiliationManagerWithItemWithNullBrand() throws Exception {
        itemService.addAffiliationManager(new Item(), null, "", "", new RecMoney());
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void addAffiliationManagerWithItemWithNullAffiliationManagerAlias() throws Exception {
        itemService.addAffiliationManager(new Item(), new Brand(), null, "", new RecMoney());
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void addAffiliationManagerWithItemWithNullUrlProductToManager() throws Exception {
        itemService.addAffiliationManager(new Item(), new Brand(), "", null, new RecMoney());
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void addAffiliationManagerWithItemWithNullPrice() throws Exception {
        itemService.addAffiliationManager(new Item(), new Brand(), "", "", null);
    }

    @Test
    public void addAffiliationManagerCheckingSave() {
        Item item = new Item();
        item.setInformationURL("http://www.example2.org");
        Brand brand = new Brand();
        brand.setId("JUnitTest");
        String affiliationManagerAlias = "JUnitTest";
        AffiliationManager affiliationManager = new AffiliationManager();

        affiliationManager.setEliminatePattern("");
        affiliationManager.setPattern("XXX");

        Mockito.when(affiliationManagerService.findAffiliationManagerByAlias(affiliationManagerAlias)).thenReturn(affiliationManager);

        itemService.addAffiliationManager(item, brand, affiliationManagerAlias, "http://www.example.com", new RecMoney());
    }

    @Test
    public void addBrandToItemWithAlreadyExistBrand() throws Exception {
        String brandID = "BRAND_1";
        BrandData brandData1 = new BrandData();
        brandData1.setId(brandID);

        Brand brand = new Brand();
        brand.setId(brandID);

        Item item = new Item();

        item.setBrandDataList(new ArrayList<>(Arrays.asList(brandData1)));
        itemService.addBrandToItem(brand, item);
        assertThat(item.getBrandDataList().size(), is(1));
    }

    @Test
    public void addBrandToItemWithNewBrand() throws Exception {
        String brandID = "BRAND_1";
        BrandData brandData1 = new BrandData();
        brandData1.setId(brandID);

        Brand brand = new Brand();
        brand.setId("NEW_BRAND");

        Item item = new Item();

        item.setBrandDataList(new ArrayList<>(Arrays.asList(brandData1)));
        itemService.addBrandToItem(brand, item);
        assertThat(item.getBrandDataList().size(), is(2));
    }

    @Test
    public void brandDataListIsAlreadyAddedWithEmptyList() throws Exception {
        String brandId = "BRAND_1";
        Item item = new Item();

        assertFalse(itemService.brandDataListIsAlreadyAdded(item, brandId));
    }

    @Test
    public void brandDataListIsAlreadyAddedWithDuplicatedBrandId() throws Exception {
        String brandId = "BRAND_1";

        BrandData brandData1 = new BrandData();
        brandData1.setId(brandId);

        Item item = new Item();
        List<BrandData> brandDataList = new ArrayList<>(Arrays.asList(brandData1));
        item.setBrandDataList(brandDataList);

        assertTrue(itemService.brandDataListIsAlreadyAdded(item, brandId));
    }

    @Test
    public void promoteVIPWithSimpleData() throws Exception {
        String itemId = "ITEM_ID";
        Date promotedDate = DateHelper.getFixedDate(2010, 10, 10);

        Item item = new Item();
        Item itemToSaveExpected = new Item();
        itemToSaveExpected.setVipDate(promotedDate);

        Mockito.when(itemRepository.findOne(itemId)).thenReturn(item);

        itemService.promoteVIP(itemId, promotedDate);

        Mockito.verify(itemRepository, Mockito.times(1)).save(itemToSaveExpected);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void promoteVIPWithInvalidItemId() throws Exception {
        Date promotedDate = DateHelper.getFixedDate(2010, 10, 10);

        itemService.promoteVIP("INVALID_ID", promotedDate);
    }

    @Test
    public void promoteVIPWithInvalidDate() throws Exception {
        String itemId = "ITEM_ID";
        Date promotedDate = null;

        Item item = new Item();
        Item itemToSaveExpected = new Item();
        itemToSaveExpected.setVipDate(promotedDate);

        Mockito.when(itemRepository.findOne(itemId)).thenReturn(item);

        itemService.promoteVIP(itemId, promotedDate);

        Mockito.verify(itemRepository, Mockito.times(1)).save(itemToSaveExpected);
    }


    @Test
    public void verifyItemWithSimpleData() throws Exception {
        String itemId = "ITEM_ID";

        Item item = new Item();
        Item itemToSaveExpected = new Item();
        itemToSaveExpected.setSandboxVerified(true);

        Mockito.when(itemRepository.findOne(itemId)).thenReturn(item);

        itemService.verifyItem(itemId);

        Mockito.verify(itemRepository, Mockito.times(1)).save(itemToSaveExpected);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void verifyItemWithInvalidItemId() throws Exception {
        itemService.verifyItem("INVALID_ID");
    }
}