/**
 * Project: RecCore
 * Created by: raulanatol at 09/03/2013 11:55:36
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.controllers.api.items;

import com.reclabs.recomendar.common.exceptions.RecIllegalArgumentException;
import com.reclabs.recomendar.common.helpers.CurrencyHelper;
import com.reclabs.recomendar.common.helpers.ParameterHelper;
import com.reclabs.recomendar.common.helpers.types.NullHelper;
import com.reclabs.recomendar.common.types.RecMoney;
import com.reclabs.recomendar.core.exceptions.items.RecItemNotFoundException;
import com.reclabs.recomendar.core.helpers.ItemHelper;
import com.reclabs.recomendar.core.misc.items.AddAffiliationManagerInputData;
import com.reclabs.recomendar.core.misc.items.UpdateAffiliationManagerInputData;
import com.reclabs.recomendar.core.misc.security.SecurityHelper;
import com.reclabs.recomendar.core.services.social.ShareServiceImpl;
import com.reclabs.recomendar.esdriver.ESDriver;
import com.reclabs.recomendar.esdriver.actions.ESFind;
import com.reclabs.recomendar.esdriver.actions.queries.ESBoolQuery;
import com.reclabs.recomendar.esdriver.actions.queries.ESCustomScoreQuery;
import com.reclabs.recomendar.esdriver.actions.queries.ESHasChildQuery;
import com.reclabs.recomendar.esdriver.actions.queries.generics.ESMatchQuery;
import com.reclabs.recomendar.esdriver.actions.queries.generics.ESWildcardQuery;
import com.reclabs.recomendar.esdriver.index.ESTypes;
import com.reclabs.recomendar.esdriver.index.IndexType;
import com.reclabs.recomendar.esdriver.types.BoolQueryField;
import com.reclabs.recomendar.esdriver.types.BooldFieldType;
import com.reclabs.recomendar.esdriver.types.ESScoreMode;
import com.reclabs.recomendar.model.documents.Category;
import com.reclabs.recomendar.model.documents.brand.AffiliationManager;
import com.reclabs.recomendar.model.documents.brand.Brand;
import com.reclabs.recomendar.model.documents.items.Item;
import com.reclabs.recomendar.model.documents.users.User;
import com.reclabs.recomendar.model.objects.items.ListItemsTagsOrdered;
import com.reclabs.recomendar.model.objects.items.UpdatePriceDTO;
import com.reclabs.recomendar.model.services.CategoryService;
import com.reclabs.recomendar.model.services.RecommendationService;
import com.reclabs.recomendar.model.services.brand.AffiliationManagerService;
import com.reclabs.recomendar.model.services.brand.BrandService;
import com.reclabs.recomendar.model.services.items.ItemService;
import com.reclabs.recomendar.objects.ResponseDTO;
import io.searchbox.core.search.sort.Sort;
import org.apache.commons.lang.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author raulanatol
 */
@Controller
@RequestMapping(value = "/v1/items/")
public class ItemController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    private ItemService itemService;

    @Autowired
    private ESDriver esDriver;

    @Autowired
    private RecommendationService recommendationService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private AffiliationManagerService affiliationManagerService;

    //FIXME cambiar por su interfaz
    @Autowired
    private ShareServiceImpl shareService;

    /**
     * Search items by query, category and tag, based on definitions of parameters. In all cases, paginated the results list
     * with page and size parameters
     * @param query Pattern of search
     * @param category Id of category
     * @param tag Id of tag
     * @param page Page of the paginated
     * @param size Number of item per page
     * @param numberOfTags Limit of numbers of tags
     * @return Result list of items and tags ordered
     */
    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ListItemsTagsOrdered searchItem(@RequestParam(value = "q", required = false) String query,
                                           @RequestParam(value = "category", required = false) String category,
                                           @RequestParam(value = "tag", required = false) String tag,
                                           @RequestParam(value = "page", defaultValue = "0") Integer page,
                                           @RequestParam(value = "size", defaultValue = "20") Integer size,
                                           @RequestParam(value = "numberOfTags", defaultValue = "15") Long numberOfTags) {
        ParameterHelper.assertEmpty(query);
        String categoryName = null;
        if (!NullHelper.isAnyNull(category)) {
            categoryName = categoryService.getCategoryById(category).getName();
        }
        List<BoolQueryField> where = ItemHelper.searchItem(query, categoryName, tag);
        ESFind queryElement = new ESFind(IndexType.ITEM_INDEX, new ESBoolQuery(where), Arrays.asList(new Sort("recommendations", Sort.Sorting.DESC)), page, size);
        Collection<Item> resultOfSearch = esDriver.searchByQueryList(queryElement, Item.class);
        return ItemHelper.getOrderListItemsTags(resultOfSearch, numberOfTags);
    }

    /**
     * Perform a basic search
     * @param queryString The string to search
     * @param categoryName The name of the category to search
     * @param tagsName List of tags to search
     * @param page Page of the search
     * @param size Number of items per page
     * @param numberOfTags Number of tags per page
     * @return Result of the query
     */
    @ResponseBody
    @RequestMapping(value = "/basicSearch", method = RequestMethod.GET)
    public ListItemsTagsOrdered basicSearchItems(@RequestParam(value = "q", required = false) String queryString,
                                                 @RequestParam(value = "c", required = false) String categoryName,
                                                 @RequestParam(value = "t", required = false) String[] tagsName,
                                                 @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                 @RequestParam(value = "size", defaultValue = "20") Integer size,
                                                 @RequestParam(value = "numberOfTags", defaultValue = "15") Long numberOfTags) {
        List<BoolQueryField> where = ItemHelper.searchItem(queryString, categoryName, tagsName);
        ESFind query = new ESFind(IndexType.ITEM_INDEX, new ESBoolQuery(where), Arrays.asList(new Sort("recommendations", Sort.Sorting.DESC)), page, size);
        Collection<Item> resultOfSearch = esDriver.searchByQueryList(query, Item.class);
        return ItemHelper.getOrderListItemsTags(resultOfSearch, numberOfTags);
    }

    /**
     * Returns items most recommended by a user and that don't include concrete item.
     * @param userId Id of user
     * @param itemId Id of item
     * @param page Page of the paginated
     * @param size Number of item per page
     * @return List of items most recommended
     */
    @ResponseBody
    @RequestMapping(value = "/getRecommendsByUser", method = RequestMethod.GET)
    public Collection<Item> getRecommendsByUser(@RequestParam(value = "userId") String userId,
                                                @RequestParam(value = "itemId") String itemId,
                                                @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                @RequestParam(value = "size", defaultValue = "20") Integer size) {
        ParameterHelper.assertEmpty(userId);
        ParameterHelper.assertEmpty(itemId);
        List<BoolQueryField> where = new ArrayList<>();
        where.add(new BoolQueryField(new ESHasChildQuery(ESTypes.RECOMMENDATIONS, new ESMatchQuery("userId", userId)), BooldFieldType.MUST));
        where.add(new BoolQueryField(new ESMatchQuery("_id", itemId), BooldFieldType.MUST_NOT));
        ESFind query = new ESFind(IndexType.RECITEM_INDEX, new ESBoolQuery(where), Arrays.asList(new Sort("recommendations", Sort.Sorting.DESC)), page, size);
        return esDriver.searchByQueryList(query, Item.class);
    }

    /**
     * Gets a list of vip items
     * @param numberOfItems Number of items to get
     * @return List of Item sort by recommendations
     */
    @ResponseBody
    @RequestMapping(value = "/vip/{numberOfItems}", method = RequestMethod.GET)
    public Collection<Item> getVIPItems(@PathVariable("numberOfItems") Integer numberOfItems) {
        ParameterHelper.assertZeroOrNegative(numberOfItems);
        List<BoolQueryField> where = new ArrayList<>();
        where.add(new BoolQueryField(new ESWildcardQuery("item.name", "*"), BooldFieldType.MUST));
        ESFind query = new ESFind(IndexType.ITEM_INDEX, new ESBoolQuery(where));
        return esDriver.searchByQueryList(query, Item.class);
    }

    /**
     * List of paginated items sort by recommendation.
     * @param page Page of the paginated
     * @param size Number of item per page
     * @return List of paginated items.
     */
    @ResponseBody
    @RequestMapping(value = "/mostRecommended", method = RequestMethod.GET)
    public Collection<Item> mostRecommended(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                            @RequestParam(value = "size", defaultValue = "20") Integer size) {
        Collection<Item> result;
        try {
            ESFind query = new ESFind(IndexType.ITEMVIP_INDEX, new ESHasChildQuery(ESTypes.VIP, new ESCustomScoreQuery("doc[\\\"vipDate\\\"].value", new ESBoolQuery()), ESScoreMode.AVG), Arrays.asList(new Sort("_score", Sort.Sorting.DESC)), page, size);
            result = esDriver.searchByQueryList(query, Item.class);
        } catch (Exception e) {
            LOGGER.warn("[Error loading data from ES]", e);
            result = new ArrayList<>();
        }
        return result;
    }

    /**
     * Busqueda de items a partir del nombre USADO en el Sandbox.
     * @param pattern Nombre del item a buscar.
     * @return Listado de items
     */
    //TODO no está en el API actualizarla o borrar el métood si no se usa.
    @ResponseBody
    @RequestMapping(value = "/list/all", method = RequestMethod.GET)
    public Collection<Item> searchItemFromPattern(@RequestParam("pattern") String pattern) {
        ParameterHelper.assertEmpty(pattern, "Pattern param is mandatory.");
        List<BoolQueryField> where = new ArrayList<>();
        where.add(new BoolQueryField(new ESWildcardQuery("item.name", "*" + pattern + "*"), BooldFieldType.SHOULD));
        where.add(new BoolQueryField(new ESWildcardQuery("item.description", "*" + pattern + "*"), BooldFieldType.SHOULD));
        where.add(new BoolQueryField(new ESWildcardQuery("item.tags.name", "*" + pattern + "*"), BooldFieldType.SHOULD));
        ESFind query = new ESFind(IndexType.ITEM_INDEX, new ESBoolQuery(where));
        return esDriver.searchByQueryList(query, Item.class);
    }

    /**
     * Obtenemos el item con la id pasada por parámetros.
     * @param itemId La id del item.
     * @return El item solicitado.
     */
    @ResponseBody
    @RequestMapping(value = "/{itemId}", method = RequestMethod.GET)
    public Item getItem(@PathVariable("itemId") String itemId) {
        ParameterHelper.assertEmpty(itemId);
        Item result = itemService.getById(itemId);
        if (result == null) {
            LOGGER.warn("[Trying to obtain an item with id {} and not found]", itemId);
            throw new RecItemNotFoundException();
        }
        return result;
    }

    /**
     * Delete the item with id is equals that itemId parameter.
     * @param itemId The id of the item to delete
     */
    @ResponseBody
    @RequestMapping(value = "/{itemId}", method = RequestMethod.DELETE)
    @PreAuthorize("#oauth2.isUser() and #oauth2.clientHasRole('ROLE_SANDBOX') and hasRole('ROLE_SANDBOX_ADMIN') and #oauth2.hasScope('write')")
    public ResponseDTO deleteItem(@PathVariable("itemId") String itemId) {
        ParameterHelper.assertEmpty(itemId);
        itemService.deleteItemById(itemId);
        return ResponseDTO.OK;
    }

    /**
     * Actualizamos los datos del item pasados por parámetros.
     * @param item Datos del item a insertar
     * @return Respuesta de la API
     */
    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    @PreAuthorize("#oauth2.isUser() and #oauth2.clientHasRole('ROLE_SANDBOX') and hasAnyRole('ROLE_SANDBOX_ADMIN', 'ROLE_SANDBOX_USER') and #oauth2.hasScope('write')")
    public ResponseDTO updateItem(@RequestBody Item item) {
        ParameterHelper.assertNull(item);
        ParameterHelper.assertEmpty(item.getId(), "ID of item is mandatory");
        ParameterHelper.assertEmpty(item.getName(), "Name of item is mandatory");
        ParameterHelper.assertEmpty(item.getDescription(), "Description is mandatory");
        ParameterHelper.assertEmpty(item.getCategory(), "Category of item is mandatory");
        ParameterHelper.assertEmpty(item.getInformationURL(), "Item url information is mandatory");
        ParameterHelper.assertCollectionEmpty(item.getTags());
        ParameterHelper.assertEmpty(item.getTags().get(0).getName(), "Tag name is mandatory");
        ParameterHelper.assertCollectionEmpty(item.getImagesURL());
        ParameterHelper.assertEmpty(item.getImagesURL().get(0).getUrl(), "Images url is mandatory");
        Item oldItem = itemService.getById(item.getId());
        if (oldItem == null) {
            LOGGER.warn("[Trying to update an unknown item][Item: {}]", item.getId());
            throw new RecIllegalArgumentException("Invalid item");
        } else if (oldItem.equals(item)) {
            LOGGER.debug("Trying to update the same item");
        } else {
            Category category = categoryService.getCategoryDocByName(item.getCategory());
            if (category == null) {
                LOGGER.warn("[Trying to change item to an unknown category][Item: {}][Category: {}]", item.getId(), item.getCategory());
                throw new RecIllegalArgumentException("The new category is invalid: " + item.getCategory());
            } else {
                itemService.updateItem(item, oldItem);
            }
        }
        return ResponseDTO.OK;
    }

    /**
     * Creamos el item pasado por parámetros.
     * Note: Used by the sandbox
     * @param item Datos del item a insertar
     * @return Respuesta de la API
     */
    //TODO ponerlo en el API
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize("#oauth2.isUser() and #oauth2.clientHasRole('ROLE_SANDBOX') and hasAnyRole('ROLE_SANDBOX_ADMIN', 'ROLE_SANDBOX_USER') and #oauth2.hasScope('write')")
    public ResponseDTO createItem(@RequestBody Item item) {
        ParameterHelper.assertNull(item);
        ParameterHelper.assertEmpty(item.getName(), "Name of item is mandatory");
        ParameterHelper.assertEmpty(item.getDescription(), "Description is mandatory");
        ParameterHelper.assertEmpty(item.getCategory(), "Category of item is mandatory");
        ParameterHelper.assertEmpty(item.getInformationURL(), "Item url information is mandatory");
        ParameterHelper.assertCollectionEmpty(item.getTags());
        ParameterHelper.assertEmpty(item.getTags().get(0).getName(), "Tag name is mandatory");
        ParameterHelper.assertCollectionEmpty(item.getImagesURL());
        ParameterHelper.assertEmpty(item.getImagesURL().get(0).getUrl(), "Images url is mandatory");
        User userLogged = SecurityHelper.getLoggedInUser();
        if (userLogged == null) {
            LOGGER.error("[Trying to create an item with invalid user]");
            throw new RecIllegalArgumentException("User is invalid");
        }
        Category category = categoryService.getCategoryDocByName(item.getCategory());
        ParameterHelper.assertNull(category);
        item.setOwnerId(userLogged.getId());
        item.setOwnerUsername(userLogged.getUsername());
        Item newItem = itemService.createItem(item, userLogged);
        recommendationService.recommendItem(userLogged.getId(), newItem.getId());
        return new ResponseDTO(ResponseDTO.OK.getCode(), newItem.getId());
    }


    /**
     * Add item from the current user on session.
     * Note: Used by the normal users.
     * @param item Item to create.
     * @return Response.OK in case of success
     */
    //TODO añadirlo al API
    @ResponseBody
    @RequestMapping(value = "/newItem", method = RequestMethod.POST)
    @PreAuthorize("#oauth2.isUser() and #oauth2.clientHasRole('ROLE_USER') and #oauth2.hasScope('write')")
    public Item userCreateItem(@RequestBody Item item) {
        ParameterHelper.assertNull(item);
        ParameterHelper.assertEmpty(item.getName(), "Name of item is mandatory");
        ParameterHelper.assertEmpty(item.getCategory(), "Category of item is mandatory");
        ParameterHelper.assertEmpty(item.getDescription(), "Description is mandatory");
        ParameterHelper.assertCollectionEmpty(item.getImagesURL());
        ParameterHelper.assertEmpty(item.getImagesURL().get(0).getUrl(), "Images url is mandatory");
        User user = SecurityHelper.getLoggedInUser();
        return itemService.createItemByNormalUser(item, user);
    }

    /**
     * The item with itemId pass to parameters is recommended by the session user?.
     * @param itemId The itemId of the item.
     * @return ResponseDTO.TRUE if the item is recommended by the session user. ResponseDTO.FALSE otherwise
     */
    @ResponseBody
    @RequestMapping(value = "/isRecommended/{itemId}", method = RequestMethod.POST)
    @PreAuthorize("#oauth2.isUser() and #oauth2.clientHasRole('ROLE_USER') and #oauth2.hasScope('write')")
    public ResponseDTO isRecommended(@PathVariable String itemId) {
        ParameterHelper.assertEmpty(itemId);
        User user = SecurityHelper.getLoggedInUser();
        return (recommendationService.isRecommendedByUserId(user.getId(), itemId)) ? ResponseDTO.TRUE : ResponseDTO.FALSE;
    }

    /**
     * Get a list of items recommended by a logged user.
     * @return List of items
     */
    @ResponseBody
    @RequestMapping(value = "/getByUser", method = RequestMethod.GET)
    @PreAuthorize("#oauth2.isUser() and #oauth2.clientHasRole('ROLE_USER') and #oauth2.hasScope('write')")
    public Collection<String> getByUser() {
        User user = SecurityHelper.getLoggedInUser();
        return recommendationService.findItemsIdByRecommendationUser(user.getId());
    }

    @ResponseBody
    @RequestMapping(value = "/addMarketPlace/{itemId}", method = RequestMethod.PUT)
    public void addMarketPlaceManager(@PathVariable String itemId, @RequestBody String brandId, @RequestBody String marketPlaceManagerAlias) {
        throw new NotImplementedException();
    }


    @ResponseBody
    @RequestMapping(value = "/updateAffiliationManager/{itemId}", method = RequestMethod.PUT)
    public void updateAffiliationManager(@PathVariable String itemId, @RequestBody UpdateAffiliationManagerInputData updateAffiliationManagerInputData) {
        ParameterHelper.assertNull(updateAffiliationManagerInputData);
        ParameterHelper.assertAnyEmpty(itemId, updateAffiliationManagerInputData.getAffiliationManagerId(), updateAffiliationManagerInputData.getUrlProductToManager(), updateAffiliationManagerInputData.getAlias());
        Item item = itemService.getById(itemId);
        if (item == null) {
            LOGGER.warn("[Trying to update affiliation manager from an item with id {} and affiliation manager alias {}]", itemId, updateAffiliationManagerInputData.getAlias());
            throw new RecIllegalArgumentException("Item id is invalid");
        }
        itemService.updateAffiliationManager(item, updateAffiliationManagerInputData.getAlias(), updateAffiliationManagerInputData.getUrlProductToManager(), updateAffiliationManagerInputData.getPrice());
    }

    @ResponseBody
    @RequestMapping(value = "/addAffiliationManager/{itemId}", method = RequestMethod.PUT)
    public void addAffiliationManager(@PathVariable String itemId, @RequestBody AddAffiliationManagerInputData addAffiliationManagerInputData) {
        ParameterHelper.assertAnyEmpty(itemId, addAffiliationManagerInputData.getBrandId(), addAffiliationManagerInputData.getAffiliationAlias(), addAffiliationManagerInputData.getAffiliationProductURL());
        Item item = itemService.getById(itemId);
        if (item == null) {
            LOGGER.warn("[Trying to add affiliation manager with this itemID {} and this id is not exist]", itemId);
            throw new RecIllegalArgumentException("The itemId is invalid {}", itemId);
        }

        Brand brand = brandService.findBrandById(addAffiliationManagerInputData.getBrandId());
        if (brand == null) {
            LOGGER.warn("[Trying to add affiliation manager with this brandId {} and this id is not exist]", addAffiliationManagerInputData.getBrandId());
            throw new RecIllegalArgumentException("The brand is invalid {}", addAffiliationManagerInputData.getBrandId());
        }

        String alias = addAffiliationManagerInputData.getAffiliationAlias();
        String urlProduct = addAffiliationManagerInputData.getAffiliationProductURL();
        RecMoney price = RecMoney.parse(addAffiliationManagerInputData.getCurrency() + " " + addAffiliationManagerInputData.getPrice());
        itemService.addAffiliationManager(item, brand, alias, urlProduct, price);
    }


    @ResponseBody
    @RequestMapping(value = "/updatePrice/{itemId}/{affiliationManagerAlias}", method = RequestMethod.PUT)
    public void updatePriceFromAffiliationManager(@PathVariable String itemId, @PathVariable String affiliationManagerAlias, @RequestBody UpdatePriceDTO price) {
        ParameterHelper.assertAnyEmpty(itemId, affiliationManagerAlias, price.getMoney());
        RecMoney money = CurrencyHelper.toMoney(price.getMoney());
        Item item = itemService.getById(itemId);
        if (item == null) {
            LOGGER.warn("[Trying to update price of an item with id {} and affiliation manager alias {} and itemId is invalid]", itemId, affiliationManagerAlias);
            throw new RecIllegalArgumentException("Item id is invalid");
        }

        ParameterHelper.assertCollectionEmpty(item.getAffiliationManagerDataList());
        AffiliationManager affiliationManager = affiliationManagerService.findAffiliationManagerByAlias(affiliationManagerAlias);
        if (affiliationManager == null) {
            LOGGER.warn("[Trying to update price of an item with id {} and affiliation manager alias {} and alias is invalid]", itemId, affiliationManagerAlias);
            throw new RecIllegalArgumentException("Alias is invalid");
        }
        itemService.updatePriceFromAffiliationManager(item, affiliationManager, money);
    }

    /**
     * Delete an affiliation manager data from a specified itemId and a specified affiliationManagerAlias.
     * @param itemId The id of the item.
     * @param affiliationManagerAlias The alias of the affiliation manager to delete.
     */
    @ResponseBody
    @RequestMapping(value = "/deleteAffiliationManager/{itemId}/{affiliationManagerAlias}", method = RequestMethod.PUT)
    public void deleteAffiliationManager(@PathVariable String itemId, @PathVariable String affiliationManagerAlias) {
        ParameterHelper.assertAnyEmpty(itemId, affiliationManagerAlias);
        Item item = itemService.getById(itemId);
        if (item == null) {
            LOGGER.warn("[Trying to delete an affiliation manager from an item with id {} and affiliation manager alias {} and itemId is invalid]", itemId, affiliationManagerAlias);
            throw new RecIllegalArgumentException("Item id is invalid");
        }

        ParameterHelper.assertCollectionEmpty(item.getAffiliationManagerDataList());
        itemService.deleteAffiliationManager(item, affiliationManagerAlias);
    }

    /**
     * Create a shortURL adding a utm campaing data.
     * {item_url}/?utm_source={utmSource}&utm_medium=item_page&utm_campaing=products_shared_by_users&utm_content={name_product}
     * @param itemId The id of the item
     * @param utmSource The utmSource
     * @return The shortURL
     */
    @ResponseBody
    @RequestMapping(value = "/shortURL/{itemId}/{utmSource}", method = RequestMethod.GET)
    public ResponseDTO getShortURL(@PathVariable String itemId, @PathVariable String utmSource) {
        ParameterHelper.assertAnyEmpty(itemId, utmSource);
        User user = SecurityHelper.getSessionUserOrNull();
        String shortURL;
        switch (utmSource) {
            case "Facebook":
            case "facebook":
                shortURL = (user != null) ? shareService.getShortURLFromItemToShareOnFacebook(itemId, user.getId()) : shareService.getShortURLFromItemToShareOnFacebookAnonymousUser(itemId);
                break;
            case "Twitter":
            case "twitter":
                shortURL = (user != null) ? shareService.getShortURLFromItemToShareOnTwitter(itemId, user.getId()) : shareService.getShortURLFromItemToShareOnTwitterAnonymousUser(itemId);
                break;
            case "Google+":
                shortURL = (user != null) ? shareService.getShortURLFromItemToShareOnGooglePlus(itemId, user.getId()) : shareService.getShortURLFromItemToShareOnGooglePlusAnonymousUser(itemId);
                break;
            case "CopyLink":
                shortURL = (user != null) ? shareService.getShortURLFromItemToShareOnClipboard(itemId, user.getId()) : shareService.getShortURLFromItemToShareOnClipboardAnonymousUser(itemId);
                break;
            default:
                LOGGER.warn("[Trying to getShortURL of an unexpected utmSource][utmSource: {}]", utmSource);
                throw new RecIllegalArgumentException("Unexpected utmSource: " + utmSource);
        }
        ResponseDTO result = new ResponseDTO();
        result.setCode(200);
        result.setMessage(shortURL);
        return result;
    }

    /**
     * @see <a href="http://hugo.recstatic.com:10116/pages/viewpage.action?pageId=1704478">API</a>
     */
    @ResponseBody
    @RequestMapping(value = "/verify/{itemId}", method = RequestMethod.PUT)
    @PreAuthorize("#oauth2.isUser() and #oauth2.clientHasRole('ROLE_SANDBOX') and hasAnyRole('ROLE_SANDBOX_ADMIN', 'ROLE_SANDBOX_USER') and #oauth2.hasScope('write')")
    public ResponseDTO verifyItem(@PathVariable String itemId) {
        ParameterHelper.assertEmpty(itemId);
        itemService.verifyItem(itemId);
        return ResponseDTO.OK;
    }
}