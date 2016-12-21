/**
 * Project: RecModel
 * Created by: Rmahugo at 10/11/2012 18:16:31
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.model.services;

import com.reclabs.recomendar.model.documents.items.ItemTag;
import com.reclabs.recomendar.objects.TagDTO;

import java.util.List;

/**
 * @author Rmahugo
 */
public interface TagService {

    /**
     * Metodo que se encarga de devolver todas las etiquetas disponibles en la base de datos
     * @return Listado de etiquetas
     */
    List<TagDTO> findAllTags();

    /**
     * Busca las etiquetas que contengan los nombres pasados por parametro, en caso de no existir crea las
     * etiquetas en bbdd y devuelve el listado con todas las etiquetas, las existentes y las creadas
     * @param names Listado de nombres de etiquetas a buscar
     * @return Listado de etiquetas
     */
    List<ItemTag> findOrInsertTagsByName(final List<String> names);

    /**
     * Realiza el borrado de la etiqueta a partir del identificador de la etiqueta
     * @param id Identificador de la etiqueta
     */
    void deleteTag(final String id);

    /**
     * Creamos o actualizamos el listado de tags pasados por parámetros.
     * @param tags El listado de tags a actualizar o crear.
     * @return El listado de tags completo modificado.
     */
    List<ItemTag> createOrUpdateTags(List<ItemTag> tags);

    /**
     * Creamos un tag por el nombre dado, en caso de que el nombre ya esté lo devolvemos sin crearlo.
     * @param name El nombre del nuevo tag.
     * @return El itemTag que representa al tag creado.
     */
    ItemTag createTagByName(String name);

    /**
     * Rename the tag with id = idTag changing the name to = newName
     * @param tagId Id of tag
     * @param newName New name of the tag
     */
    void renameTag(String tagId, String newName);
}
