/**
 * Project: RecModel
 * Created by: fjmorales at 04/04/2013 16:13:03
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.model.services.categories;

import com.reclabs.recomendar.objects.documents.category.CategoryTagsDTO;

import java.util.List;

/**
 * @author fjmorales
 */
public interface OPTProductTagCategoryService {

    /**
     * Incrementa el numero de productos asociados a una etiqueta en una categoria
     * @param tagId Identificador de la etiqueta
     * @param categoryId Identificador de una categoria
     */
    void incrementNumberOfProducts(String tagId, String categoryId);

    /**
     * Obtiene el listado de etiquetas asociados a una categoria con el numero de productos en cada etiqueta
     * @param categoryName Nombre de la categoria
     * @return Listado de etiquetas asociados a una categoria
     */
    List<CategoryTagsDTO> findByCategory(String categoryName);

    /**
     * Obtiene el listado de etiquetas asociados a una categoria con el numero de productos en cada etiqueta
     * @param categoryId Identificador de categoria
     * @param numberOfTags Numero de etiquetas a obtener
     * @return Listado de etiquetas asociadas a una categoria
     */
    List<CategoryTagsDTO> findTagsByCategoryPriority(String categoryId, int numberOfTags);
}
