/**
 * Project: RecCore
 * Created by: raulanatol at 03/12/2012 20:02:36
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.services;

import com.reclabs.recomendar.common.exceptions.RecIllegalArgumentException;
import com.reclabs.recomendar.common.helpers.CurrencyHelper;
import com.reclabs.recomendar.common.helpers.ParameterHelper;
import com.reclabs.recomendar.common.helpers.misc.ImageHelper;
import com.reclabs.recomendar.common.helpers.types.CollectionHelper;
import com.reclabs.recomendar.common.helpers.types.DateHelper;
import com.reclabs.recomendar.common.helpers.types.StringHelper;
import com.reclabs.recomendar.common.types.DatePrecisionType;
import com.reclabs.recomendar.common.types.RecMoney;
import com.reclabs.recomendar.core.helpers.AffiliationHelper;
import com.reclabs.recomendar.core.misc.security.SecurityHelper;
import com.reclabs.recomendar.model.documents.brand.AffiliationManager;
import com.reclabs.recomendar.model.documents.brand.Brand;
import com.reclabs.recomendar.model.documents.brand.MarketPlaceManager;
import com.reclabs.recomendar.model.documents.brand.data.AffiliationManagerData;
import com.reclabs.recomendar.model.documents.brand.data.BrandData;
import com.reclabs.recomendar.model.documents.items.Item;
import com.reclabs.recomendar.model.documents.items.ItemTag;
import com.reclabs.recomendar.model.documents.items.URLPath;
import com.reclabs.recomendar.model.documents.users.User;
import com.reclabs.recomendar.model.exceptions.user.UserNoHaveMoreRecommendationActionException;
import com.reclabs.recomendar.model.repositories.items.ItemRepository;
import com.reclabs.recomendar.model.services.ImageService;
import com.reclabs.recomendar.model.services.RecommendationService;
import com.reclabs.recomendar.model.services.TagService;
import com.reclabs.recomendar.model.services.brand.AffiliationManagerService;
import com.reclabs.recomendar.model.services.items.ItemHaveService;
import com.reclabs.recomendar.model.services.items.ItemService;
import com.reclabs.recomendar.model.services.items.ItemToVerifyService;
import com.reclabs.recomendar.model.services.items.ItemWantService;
import org.apache.commons.lang.NotImplementedException;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Item service implementation.
 * @author raulanatol
 */
@Service
public class ItemServiceImpl implements ItemService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ItemServiceImpl.class);

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ImageService imageService;

    @Autowired
    private TagService tagService;

    @Autowired
    private ItemToVerifyService itemToVerifyService;

    @Autowired
    private RecommendationService recommendationService;

    @Autowired
    private ItemWantService itemWantService;

    @Autowired
    private ItemHaveService itemHaveService;

    @Autowired
    private AffiliationManagerService affiliationManagerService;

    /*
     * (non-Javadoc)
     * @see
     * com.reclabs.recomendar.model.services.ItemService#getById(java.lang.String
     * )
     */
    @Override
    public Item getById(String itemId) {
        ParameterHelper.assertEmpty(itemId);
        return itemRepository.findOne(itemId);
    }

    /*
     * (non-Javadoc)
     * @see
     * com.reclabs.recomendar.model.services.ItemService#updateItem(com.reclabs.recomendar.model.documents.items
     * .Item)
     */
    @Override
    public void updateItem(Item item, Item oldItem) {
        ParameterHelper.assertAnyNull(item, oldItem);
        updateTags(item);
        updateImages(item, oldItem);
        itemRepository.save(item);
    }

    /**
     * Verificamos todas las imágenes que son de nuestro CDN y las que no sean las añadimos con el método de
     * uploadImages las que son no las tocamos.
     * @param item El item con las imágenes a actualizar...
     * @param oldItem El antiguo item
     */
    private void updateImages(Item item, Item oldItem) {
        normalizeImagePath(item);
        Collection<URLPath> imagesToDelete = CollectionHelper.diffCollection(oldItem.getImagesURL(), item.getImagesURL());
        uploadImages(item);
        deleteImages(imagesToDelete);
    }

    /**
     * All imagesURL with name are empty will be null
     * @param item Item to normalize his images
     */
    private void normalizeImagePath(Item item) {
        if (!CollectionHelper.isEmpty(item.getImagesURL())) {
            for (URLPath urlPath : item.getImagesURL()) {
                if (StringHelper.isEmpty(urlPath.getName())) {
                    urlPath.setName(null);
                }
            }
        }
    }

    /**
     * Actualizamos todos los datos de los tags del item. Creando los que sean necesario u obteniendo la id de los
     * que no se tengan.
     * @param item El item completo.
     */
    private void updateTags(Item item) {
        List<ItemTag> tagList = tagService.createOrUpdateTags(item.getTags());
        item.setTags(tagList);
    }

    /**
     * Delete the followings images from the image server of Recomendar.
     * @param imagesToDelete The images to delete
     */
    private void deleteImages(Collection<URLPath> imagesToDelete) {
        for (URLPath urlPath : imagesToDelete) {
            imageService.deleteImageFromPath(urlPath.getUrl());
        }
    }

    /**
     * Subimos todas las imágenes del item y actualizamos sus urls.
     * @param item The item to upload images
     */
    private void uploadImages(Item item) {
        List<URLPath> newImages = new ArrayList<>();
        for (URLPath imageURLPath : item.getImagesURL()) {
            if (ImageHelper.isRecomendarImage(imageURLPath.getUrl())) {
                newImages.add(imageURLPath);
            } else {
                String newURL = imageService.createImage2Item(item.getId(), imageURLPath.getUrl());
                URLPath newURLPath = new URLPath();
                newURLPath.setUrl(newURL);
                newImages.add(newURLPath);
            }
        }
        item.setImagesURL(newImages);
    }

    @Override
    public Item createItem(Item item, User user) {
        ParameterHelper.assertAnyNull(item, user);
        if (!recommendationService.userCanBeRecommendItem(user.getId())) {
            LOGGER.info("[Trying to create an item with no pending recommendations][User: {}]", user.getId());
            throw new UserNoHaveMoreRecommendationActionException();
        }
        item.setId(new ObjectId().toStringMongod());
        LOGGER.info("[Starting process to create an item: {}]", item.getId());
        verifyItemIsDuplicated(item);
        uploadImages(item);
        updateTags(item);
        item.setCreatedDate(DateHelper.getCurrentDate(DatePrecisionType.MILLISECOND));
        updateAffiliationManagersByNewItem(item);
        Item result = itemRepository.save(item);
        LOGGER.info("[Create item successful][User: " + SecurityHelper.getLoggedInUser().getId() + "][Item: " + result.getId() + "]");
        return result;
    }

    /**
     * Update the brand data of an new item.
     * @param item New item
     * @param brandDataToAdd List of brandData
     */
    private void updateBrandDataByNewItem(Item item, final List<BrandData> brandDataToAdd) {
        LOGGER.info("[Updating brandDataList size: {} of new item: {}]", brandDataToAdd.size(), item.getId());
        item.setBrandDataList(brandDataToAdd);
    }

    /**
     * Update the affiliation manager list by a new item
     * @param item The item to update
     */
    protected void updateAffiliationManagersByNewItem(Item item) {
        // TODO en el futuro meter esto en una cola para no bloquear el resto del proceso...
        List<AffiliationManagerData> affiliationManagerDataList = new ArrayList<>();
        List<AffiliationManager> affiliationManagerList = affiliationManagerService.findAffiliationManagerByItemInformationURL(item.getInformationURL());
        List<BrandData> brandDataToAdd = new ArrayList<>();
        if (!CollectionHelper.isEmpty(affiliationManagerList)) {
            for (AffiliationManager affiliationManager : affiliationManagerList) {
                AffiliationManagerData data = generateAffiliationManagerData(item, affiliationManager, null, item.getInformationURL());
                affiliationManagerDataList.add(data);
                brandDataToAdd.add(affiliationManager.getBrandData());
            }
        }
        item.setAffiliationManagerDataList(affiliationManagerDataList);
        updateBrandDataByNewItem(item, brandDataToAdd);
    }

    @Override
    public Item createItemByNormalUser(Item item, User user) {
        ParameterHelper.assertAnyNull(item, user);
        item.setOwnerId(user.getId());
        item.setOwnerUsername(user.getUsername());
        Item newItem = createItem(item, user);
        itemToVerifyService.createItem(item.getInformationURL(), newItem.getId(), user.getId());
        recommendationService.recommendItem(user.getId(), newItem.getId());
        return newItem;
    }

    @Override
    public void deleteItemById(String itemId) {
        ParameterHelper.assertEmpty(itemId);
        LOGGER.info("Starting delete item: {}", itemId);
        recommendationService.deleteItemByItemId(itemId);
        itemWantService.deleteItemByItemId(itemId);
        itemHaveService.deleteItemByItemId(itemId);
        itemToVerifyService.deleteItemByItemId(itemId);
        itemRepository.delete(itemId);
    }

    @Override
    public void deleteTag(String tagId) {
        itemRepository.deleteTag(tagId);
    }

    @Override
    public void renameTag(String tagId, String newName) {
        LOGGER.info("[Starting to rename tag {} with name {}", tagId, newName);
        itemRepository.renameTag(tagId, newName);
        LOGGER.info("[Finish to rename tag {} with name {}", tagId, newName);
    }

    @Override
    public void addMarketPlaceManager(Item item, Brand brand, String marketPlaceManagerAlias) {
        throw new NotImplementedException();
    }

    @Override
    public void addAffiliationManager(Item item, Brand brand, String affiliationManagerAlias, String urlProductToManager, RecMoney price) {
        ParameterHelper.assertAnyNull(item, brand, affiliationManagerAlias, urlProductToManager, price);
        LOGGER.info("[Starting to add affiliation with alias {} to item {}]", affiliationManagerAlias, item.getId());
        AffiliationManager affiliationManager = affiliationManagerService.findAffiliationManagerByAlias(affiliationManagerAlias);
        if (affiliationManager == null) {
            LOGGER.error("The affiliation manager {} not exist", affiliationManagerAlias);
            throw new RecIllegalArgumentException("Invalid affiliation manager alias: " + affiliationManagerAlias);
        }
        addAffiliationManagerToItem(affiliationManager, item, urlProductToManager, price);
        addBrandToItem(brand, item);
        itemRepository.save(item);
    }

    /**
     * @param affiliationManager The affiliation manager to add
     * @param item The item to edit
     * @param urlProductToManager The url of the product using in the affiliation manager
     * @param price The price of the item. Can be null
     */
    private void addAffiliationManagerToItem(AffiliationManager affiliationManager, Item item, String urlProductToManager, RecMoney price) {
        if (affiliationManagerAlreadyAdded(item, affiliationManager.getAlias())) {
            LOGGER.warn("[Trying to add affiliation manager to a Item {} that already exist: {}", item.getId(), affiliationManager.getAlias());
            throw new RecIllegalArgumentException("The affiliationManagerAlias already exist in this item");
        }
        if (item.getAffiliationManagerDataList() == null) {
            item.setAffiliationManagerDataList(new ArrayList<AffiliationManagerData>());
        }
        AffiliationManagerData element2Append = generateAffiliationManagerData(item, affiliationManager, price, urlProductToManager);
        if (affiliationManager.getBrandData() != null) {
            updateBrandDataByNewItem(item, new ArrayList<>(Arrays.asList(affiliationManager.getBrandData())));
        } else {
            updateBrandDataByNewItem(item, new ArrayList<BrandData>());
        }
        item.getAffiliationManagerDataList().add(element2Append);
    }

    private AffiliationManagerData generateAffiliationManagerData(Item item, AffiliationManager affiliationManager, RecMoney price, String urlProductToManager) {
        LOGGER.info("[Appending affiliation manager {} to item {}]", affiliationManager.getAlias(), item.getId());
        AffiliationManagerData data = new AffiliationManagerData();
        data.setId(affiliationManager.getId());
        data.setAlias(affiliationManager.getAlias());
        data.setVendorRegionType(affiliationManager.getVendorRegionType());
        data.setEnabled(affiliationManager.getEnabled());
        data.setUrlProductToManager(urlProductToManager);
        data.setUrlResult(AffiliationHelper.generateResult(affiliationManager, urlProductToManager));
        data.setPrice(price);
        return data;
    }

    /**
     * Add a brand into an item
     * @param brand The brand to append
     * @param item The item to modify.
     */
    protected void addBrandToItem(Brand brand, Item item) {
        LOGGER.debug("[Starting process to add brand {} to item {}]", brand.getId(), item.getId());
        if (!brandDataListIsAlreadyAdded(item, brand.getId())) {
            BrandData brandData = new BrandData();
            brandData.setId(brand.getId());
            brandData.setName(brand.getName());
            if (CollectionHelper.isEmpty(item.getBrandDataList())) {
                item.setBrandDataList(new ArrayList<>(Arrays.asList(brandData)));
            } else {
                item.getBrandDataList().add(brandData);
            }
        }
    }

    protected boolean brandDataListIsAlreadyAdded(Item item, String brandId) {
        boolean result = false;
        if (!CollectionHelper.isEmpty(item.getBrandDataList())) {
            for (BrandData brandData : item.getBrandDataList()) {
                if (StringHelper.equals(brandId, brandData.getId())) {
                    LOGGER.debug("[Already added]");
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    @Override
    public void enableOrAddAffiliationManagerInItems(AffiliationManager affiliationManager) {
        // TODO in the future do it this using a thread or another system (JMS...) because is a expensive
        // procedure.
        String domainToSearch = affiliationManager.getDomain();
        List<Item> itemsToUpdate = itemRepository.findByDomainURL(domainToSearch);
        LOGGER.debug("[{} elements found using the domain to search {} ", itemsToUpdate.size(), domainToSearch);
        List<Item> itemsToSave = new ArrayList<>();
        for (Item item : itemsToUpdate) {
            if (AffiliationHelper.compareDomain(item.getInformationURL(), domainToSearch)) {
                try {
                    addAffiliationManagerToItem(affiliationManager, item, item.getInformationURL(), null);
                } catch (RecIllegalArgumentException e) {
                    LOGGER.warn("[Starting enable affiliation manager {} to a Item {}", affiliationManager.getAlias(), item.getId());
                    enableAffiliationManagerInItem(item, affiliationManager);
                }
                itemsToSave.add(item);
            } else {
                LOGGER.warn("Trying to update items from the domain {} and the item {} has appeared", domainToSearch, item.getId());
            }
        }
        if (!CollectionHelper.isEmpty(itemsToSave)) {
            LOGGER.info("[Starting save {} items]", itemsToSave.size());
            itemRepository.save(itemsToSave);
            // TODO Se debe avisar al Content Manager para que actualice todos los precios de los items enlazados
            // con el nuevo AM.
        }
    }

    @Override
    public void enableAffiliationManagerInItem(Item item, AffiliationManager affiliationManager) {
        boolean modified = false;
        // FIXME upgrade
        // for (AffiliationManagerData affiliationManagerData : item.getAffiliationManagerDataList()) {
        // if (StringHelper.equals(affiliationManagerData.getAffiliationManager().getAlias(),
        // affiliationManager.getAlias())) {
        // LOGGER.debug("[Perform to update the affiliation manager {} in the item {}",
        // affiliationManagerData.getAffiliationManager().getAlias(), item.getId());
        // if (StringHelper.isEmpty(affiliationManagerData.getUrlResult())) {
        // if (StringHelper.isEmpty(affiliationManagerData.getUrlProductToManager())) {
        // affiliationManagerData.setUrlProductToManager(item.getInformationURL());
        // }
        // affiliationManagerData.setUrlResult(AffiliationHelper.generateResult(affiliationManager,
        // affiliationManagerData.getUrlProductToManager()));
        // modified = true;
        // }
        // }
        // }
        // if (modified) {
        // LOGGER.info("[Item {} modified - Affiliation manager {} enabled]", item.getId(),
        // affiliationManager.getAlias());
        // }
    }

    @Override
    public void updateAffiliationManager(Item item, String alias, String urlProduct, RecMoney price) {
        AffiliationManagerData affiliationManagerData = null;
        for (AffiliationManagerData affiliationManagerDataItem : item.getAffiliationManagerDataList()) {
            if (StringHelper.equals(affiliationManagerDataItem.getAlias(), alias)) {
                affiliationManagerData = affiliationManagerDataItem;
                break;
            }
        }
        if (affiliationManagerData == null) {
            LOGGER.warn("[Trying to update affiliation manager with alias: {} and this alias is not valid, userId: {}]", alias, item.getId());
            throw new RecIllegalArgumentException("Alias is invalid");
        }

        if (!CurrencyHelper.equals(price, affiliationManagerData.getPrice())) {
            LOGGER.warn("[Updating price old: {} and new: {}]", affiliationManagerData.getPrice(), price);
            affiliationManagerData.setPrice(price);
        }

        if (!StringHelper.equals(urlProduct, affiliationManagerData.getUrlProductToManager())) {
            LOGGER.warn("[Updating url product old: {} and new: {}]", affiliationManagerData.getUrlProductToManager(), urlProduct);
            AffiliationManager affiliationManager = affiliationManagerService.findAffiliationManagerByAlias(alias);
            affiliationManagerData.setUrlProductToManager(urlProduct);
            affiliationManagerData.setUrlResult(AffiliationHelper.generateResult(affiliationManager, urlProduct));
            LOGGER.warn("[Regenerated url of item: {} new URL: {}]", item.getId(), affiliationManagerData.getUrlResult());
        }

        itemRepository.save(item);
    }

    @Override
    public void verifyItemIsDuplicated(Item item) throws RecIllegalArgumentException {
        Item itemResult = itemRepository.findOne(item.getId());
        if (itemResult != null) {
            LOGGER.info("[Item with duplicated id={} informationURL={}]", item.getId(), item.getInformationURL());
            throw new RecIllegalArgumentException("Item id = is duplicated");
        }

        if (!StringHelper.isEmpty(item.getOriginImageURL())) {
            itemResult = itemRepository.findByOriginImageURL(item.getOriginImageURL());
            if (itemResult != null) {
                LOGGER.info("[Item duplicated. id {} originImagenURL {}]", item.getId(), item.getOriginImageURL());
                throw new RecIllegalArgumentException("Item is duplicated");
            }
        }
    }

    @Override
    public void updatePriceFromAffiliationManager(Item item, AffiliationManager affiliationManager, RecMoney price) {
        LOGGER.info("[Starting process to update the price of an item: {} new price {}", item.getId(), price.toString());
        boolean modified = false;
        for (AffiliationManagerData affiliationManagerData : item.getAffiliationManagerDataList()) {
            if (StringHelper.equals(affiliationManagerData.getAlias(), affiliationManager.getAlias())) {
                affiliationManagerData.setPrice(price);
                modified = true;
            }
        }
        if (!modified) {
            LOGGER.warn("[Trying to modify the price of an item without the affiliation manager specified. Alias: {} itemID: {}]", affiliationManager.getAlias(), item.getId());
            throw new RecIllegalArgumentException("Error the alias is invalid");
        }
        itemRepository.save(item);
    }

    @Override
    public void deleteAffiliationManager(Item item, String affiliationManagerAlias) {
        LOGGER.info("[Starting process to delete affiliation manager ({}) from an item with id {}]", affiliationManagerAlias, item.getId());
        AffiliationManagerData affiliationManagerData = getAffiliationManagerDataByAliasFromItem(item, affiliationManagerAlias);
        if (affiliationManagerData == null) {
            LOGGER.error("[Trying to delete an affiliation manager: {} with not exist in the item: {}]", affiliationManagerAlias, item.getId());
            throw new RecIllegalArgumentException("The item do not contains the alias specified");
        }

        item.getAffiliationManagerDataList().remove(affiliationManagerData);
        updateBrandDataListAfterRemoveAffiliationData(item, affiliationManagerData);
        itemRepository.save(item);
    }

    @Override
    public void promoteVIP(String itemId, Date promoteDate) {
        LOGGER.info("[Starting process to promote an item][ItemId: {}]", itemId);
        Item item = itemRepository.findOne(itemId);
        if (item == null) {
            LOGGER.error("[Trying to promote vip an invalid item][ItemId: {}]", itemId);
            throw new RecIllegalArgumentException("The item id is invalid");
        }
        item.setVipDate(promoteDate);
        itemRepository.save(item);
    }

    @Override
    public void verifyItem(String itemId) {
        LOGGER.info("[Starting process to verify item][ItemId: {}]", itemId);
        Item item = itemRepository.findOne(itemId);
        if (item == null) {
            LOGGER.error("[Trying to verify an invalid item][ItemId: {}]", itemId);
            throw new RecIllegalArgumentException("The item id is invalid");
        }
        item.setSandboxVerified(true);
        itemRepository.save(item);
    }

    /**
     * Update the brand data list from a specified item after has been deleted an affiliationManager
     * @param item Item to update his brand data list.
     * @param affiliationManagerDeleted The affiliation manager data deleted
     */
    protected void updateBrandDataListAfterRemoveAffiliationData(Item item, AffiliationManagerData affiliationManagerDeleted) {
        if (CollectionHelper.isEmpty(item.getAffiliationManagerDataList())) {
            item.setBrandDataList(new ArrayList<BrandData>());
        } else {
            boolean deleteBrand = true;
            String brandNameForDelete = AffiliationHelper.extractBrandNameFromAlias(affiliationManagerDeleted.getAlias());
            for (AffiliationManagerData affiliationManagerData : item.getAffiliationManagerDataList()) {
                if (StringHelper.equals(brandNameForDelete, AffiliationHelper.extractBrandNameFromAlias(affiliationManagerData.getAlias()))) {
                    deleteBrand = false;
                    break;
                }
            }
            if (deleteBrand) {
                BrandData brandToDelete = null;
                for (BrandData brandData : item.getBrandDataList()) {
                    if (StringHelper.equals(brandNameForDelete, brandData.getName())) {
                        brandToDelete = brandData;
                    }
                }

                if (brandToDelete == null) {
                    LOGGER.warn("[Trying delete the brand {} but not found on the item {}]", brandNameForDelete, item.getId());
                } else {
                    LOGGER.info("[Delete brand relationship between item: {} and brand: {}]", item.getId(), brandToDelete.getId());
                    item.getBrandDataList().remove(brandToDelete);
                }
            }
        }
    }

    /**
     * Verify if an item contains an affiliation manager relationship
     * @param item The item to verify
     * @param alias The alias to find.
     * @return True if the item contains the affiliation manager data with alias are "alias"
     */
    private AffiliationManagerData getAffiliationManagerDataByAliasFromItem(Item item, String alias) {
        AffiliationManagerData result = null;
        for (AffiliationManagerData affiliationManagerData : item.getAffiliationManagerDataList()) {
            if (StringHelper.equals(affiliationManagerData.getAlias(), alias)) {
                result = affiliationManagerData;
                break;
            }
        }
        return result;
    }

    /**
     * @param item
     * @param alias
     * @return
     */
    private boolean affiliationManagerAlreadyAdded(Item item, String alias) {
        boolean result = false;
        // FIXME upgrade
        // if (!CollectionHelper.isEmpty(item.getAffiliationManagerDataList())) {
        // for (AffiliationManagerData affiliationManagerData : item.getAffiliationManagerDataList()) {
        // AffiliationManager affiliationManagerToCompare = affiliationManagerData.getAffiliationManager();
        // if (affiliationManagerToCompare != null && StringHelper.equals(affiliationManagerToCompare.getAlias(),
        // alias)) {
        // result = true;
        // break;
        // }
        // }
        // }
        return result;
    }

    /**
     * @param brand
     * @param alias
     * @return
     */
    private MarketPlaceManager getMarketPlaceByAlias(Brand brand, String alias) {
        // FIXME
        // MarketPlaceManager result = brand.getMarketPlaceManager();
        // if (!StringHelper.equals(result.getAlias(), alias)) {
        // LOGGER.warn("[Trying to add marketplace that not exist on brandId: {} alias: {}", brand.getId(), alias);
        // throw new RecIllegalArgumentException("The marketPlaceManagerAlias already exist in this item");
        // }
        // return result;
        return null;
    }

}
