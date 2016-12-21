/**
 * Project: RecModel
 * Created by: fjmorales at 10/11/2012 20:17:21
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.model.repositories;

import com.reclabs.recomendar.model.documents.Tag;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author fjmorales
 */
public interface TagRepository extends MongoRepository<Tag, String> {

    /**
     * Metodo que se encarga de buscar las etiquetas que contengan en el nombre el parametro pasado
     * @param name Name of the tag
     * @return Listado de tags.
     */
    List<Tag> findByName(String name);

    /**
     * Metodo que se encarga de buscar las etiquetas que contengan el nombre exacto igual al parametro
     * @param name Name of the tag
     * @return Listado de tags.
     */
    List<Tag> findByExactlyName(String name);

    /**
     * Metodo que se encarga de buscar un listado de etiquetas que contengan el nombre exacto igual al parametro
     * pasado
     * @param name Listado de nombres a buscar
     * @return Listado de tags
     */
    List<Tag> findListByExactlyName(final List<String> name);

    /**
     * Buscamos el tag con nombre idéntico al pasado por parámetros.
     * @param name El parámetro del nombre
     * @return El tag si se ha encontrado.
     */
    Tag findExactlyName(String name);

    /**
     * Dado el nombre de un tag lo creamos o devolvemos el que está ya creado desde la base de datos.
     * @param tagName El nombre del tag a crear o a obtener
     * @return El tag desde la base de datos.
     */
    Tag getOrCreate(String tagName);

    /**
     * Rename the tag with id = idTag changing the name to = newName
     * @param tagId Id of tag
     * @param newName New name of the tag
     */
    void renameTag(String tagId, String newName);
}
