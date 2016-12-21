/**
 * Project: RecModel
 * Created by: fjmorales at 10/11/2012 20:17:21
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.model.repositories.items;

import com.reclabs.recomendar.model.documents.items.Item;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


/**
 * @author fjmorales
 */
public interface ItemRepository extends MongoRepository<Item, String> {

    /**
     * Metodo que devuelve los Itemos que coinciden exactamente con el nombre sin tener en cuenta
     * mayusculas y minusculas
     * @param name Name to find
     * @return Listado de Itemos.
     */
    List<Item> findByExactlyName(String name);

    /**
     * Metodo que devuelve los Itemos que contengan el nombre pasado por parametro sin tener en cuenta
     * mayusculas y minusculas
     * @param name Name to find
     * @return Listado de Itemos.
     */
    List<Item> findByName(String name);

    /**
     * Metodo que devuelve los Itemos que contengan en el nombre o la descripcion del Itemo, el nombre pasado
     * por parametro sin tener en cuenta mayusculas y minusculas
     * @param name Name to find
     * @return Listado de Itemos.
     */
    List<Item> findByNameOrDescription(String name);

    /**
     * Dado un listado de ids de items obtenemos todos los Itemos con dicha id
     * @param itemIds Ids de los elementos.
     * @return Listado de Itemos.
     */
    List<Item> findByIds(List<String> itemIds);

    /**
     * Get all items from a specified list of items ids, order by created date descending
     * @param itemIds List of ids
     * @return List of items
     */
    List<Item> findByIdsOrderByCreatedDateDesc(List<String> itemIds);

    /**
     * Listado de items de una categoría paginados.
     * @param categoryId La id de la categoría.
     * @param page Numero de página a mostrar
     * @param size Tamaño de la página
     * @return Listado de items
     */
    List<Item> findByCategory(String categoryId, Integer page, Integer size);

    /**
     * Delete all relations over the tag with id = tagId
     * @param tagId Id of the tag to remove.
     */
    void deleteTag(String tagId);

    /**
     * Change the name of the tag with id = tagId
     * @param tagId The id of the tag
     * @param newName The new name of the tag
     */
    void renameTag(String tagId, String newName);

    /**
     * Get a list of item that any of yours affiliation manager are the specified domainToSearch
     * @param domainToSearch The domain to search
     * @return List of items.
     */
    List<Item> findByDomainURL(String domainToSearch);

    /**
     * @param alias The alias to find
     * @return List of items that contains an affiliation manager with the alias: "alias"
     */
    List<Item> findItemsByAffiliationManager(String alias);

    /**
     * Find element by informationURL
     * @param informationURL The informationURL to find.
     * @return The item found
     */
    Item findByInformationURL(String informationURL);

    /**
     * Find an element by his originImageURL
     * @param originImageURL The originURL to find.
     * @return The item found or null if any is found.
     */
    Item findByOriginImageURL(String originImageURL);
}
