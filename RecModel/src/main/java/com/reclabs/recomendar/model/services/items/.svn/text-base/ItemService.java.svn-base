/**
 * Project: RecModel
 * Created by: Rmahugo at 10/11/2012 13:38:37
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.model.services.items;

import com.reclabs.recomendar.common.exceptions.RecIllegalArgumentException;
import com.reclabs.recomendar.common.types.RecMoney;
import com.reclabs.recomendar.model.documents.brand.AffiliationManager;
import com.reclabs.recomendar.model.documents.brand.Brand;
import com.reclabs.recomendar.model.documents.items.Item;
import com.reclabs.recomendar.model.documents.users.User;

import java.util.Date;

/**
 * @author Rmahugo
 */
public interface ItemService {

    /**
     * Otenemos los datos de un item con la id pasada por parámetros.
     * @param itemId La id del item.
     * @return El item con toda la información necesaria.
     */
    Item getById(String itemId);

    /**
     * Dado un item lo actualizamos en la base de datos.
     * @param item El item a actualizar.
     * @param oldItem The old item
     */
    void updateItem(Item item, Item oldItem);

    /**
     * Creamos el item pasado por parámetros.
     * @param item Los datos del item.
     * @param user The user that create the item
     * @return El item creado.
     */
    Item createItem(Item item, User user);

    /**
     * Create a item from un normal user.
     * @param item The item to create.
     * @param user User owner of the new item.
     * @return The item.
     */
    Item createItemByNormalUser(Item item, User user);

    /**
     * Delete the current item from recomendar.
     * @param itemId The id of the item.
     */
    void deleteItemById(String itemId);

    /**
     * Delete all relations over the tag with id = tagId
     * @param tagId Id of the tag to remove.
     */
    void deleteTag(String tagId);

    /**
     * Rename all the tags of the items that id = tagId for the newName
     * @param tagId Id of the tag to rename
     * @param newName New name of the tag
     */
    void renameTag(String tagId, String newName);

    /**
     * Add to the item the marketplace
     * @param item Item to add
     * @param brand Brand father of the marketplace
     * @param marketPlaceManagerAlias Alias of the marketplace manager to add
     */
    void addMarketPlaceManager(Item item, Brand brand, String marketPlaceManagerAlias);

    /**
     * @param item The item data
     * @param brand The brand father of the affiliation
     * @param affiliationManagerAlias Alias of the affiliation manager to append
     * @param urlProductToManager URL of the product to add.
     * @param price Price of the item
     */
    void addAffiliationManager(Item item, Brand brand, String affiliationManagerAlias, String urlProductToManager, RecMoney price);

    /**
     * For each item with this alias is necessary generate his sell url.
     * @param affiliationManager Affiliation manager to enable.
     */
    void enableOrAddAffiliationManagerInItems(AffiliationManager affiliationManager);

    /**
     * Enable the affiliation manager in the item (first parameter).
     * The affiliation manager do NOT be appended in the item. Should be added as.
     * @param item Item to update
     * @param affiliationManager Affiliation manager to enable
     */
    void enableAffiliationManagerInItem(Item item, AffiliationManager affiliationManager);

    /**
     * Update the affiliation manager from the specified values
     * @param item The item to update
     * @param alias Alias of the affiliationManager
     * @param urlProduct Url of the product
     * @param price Price of the product
     */
    void updateAffiliationManager(Item item, String alias, String urlProduct, RecMoney price);

    /**
     * Verify if the item is duplicated on the database.
     * @param item The item to verify
     * @throws RecIllegalArgumentException In case of item duplication.
     */
    void verifyItemIsDuplicated(Item item) throws RecIllegalArgumentException;

    /**
     * Update the price of an item in an affiliation manager specified
     * @param item The item to update
     * @param affiliationManager The affiliation manager to update
     * @param price The price to change.
     */
    void updatePriceFromAffiliationManager(Item item, AffiliationManager affiliationManager, RecMoney price);

    /**
     * Delete the affiliation manager data relationship between item and affiliation manager alias passed through parameters.
     * @param item Id of the item
     * @param affiliationManagerAlias Alias of the affiliation manager
     */
    void deleteAffiliationManager(Item item, String affiliationManagerAlias);

    /**
     * Promote the item with id itemId to the date: "promoteDate"
     * @param itemId Id of the item
     * @param promoteDate Date to promote
     */
    void promoteVIP(String itemId, Date promoteDate);

    /**
     * Verify the item
     * @param itemId The item Id
     */
    void verifyItem(String itemId);
}
