/**
 * Project: RecModel
 * Created by: fjmorales at 04/04/2013 16:07:32
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.model.repositories.categories;

import com.reclabs.recomendar.model.documents.categories.OPTProductTagCategory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author fjmorales
 */
public interface OPTProductTagCategoryRepository extends MongoRepository<OPTProductTagCategory, String> {

    /**
     * Obtiene los datos de los productos asociados a una etiqueta y una categoria determinada
     * @param tagId Identificador de la etiqueta
     * @param categoryId Identificador de la categoria
     * @return Datos de los productos
     */
    OPTProductTagCategory findByTagCategory(String tagId, String categoryId);

    /**
     * Obtiene el listado de etiquetas asociadas a una categoria determinada con el numero de productos asociados a
     * dichas etiquetas
     * @param categoryId Identificador de una categoria
     * @return Listado de etiquetas asociadas a una categoria
     */
    List<OPTProductTagCategory> findByCategory(String categoryId);

    /**
     * Obtiene un numero de etiquetas asociadas a una categoria determinada ordenados por el numero de productos
     * asociados
     * @param categoryId Identificador de la categoria
     * @param numberOfTags Numero de etiquetas a obtener
     * @return Listado de etiquetas asociadas a la categoria
     */
    List<OPTProductTagCategory> findByCategoryPriority(String categoryId, int numberOfTags);
}
