/**
 * Project: RecModel
 * Created by: raulanatol at 23/03/2013 15:47:11
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.model.repositories;

import com.reclabs.recomendar.model.documents.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Funcionalidades de acceso al document de categorías.
 * @author raulanatol
 */
public interface CategoryRepository extends MongoRepository<Category, String> {
    /**
     * @return Listado de todas las categorías principales
     */
    List<Category> getMainCategories();

    /**
     * Dado el nombre de una categoría la buscamos en la base de datos.
     * @param categoryName El nombre de la categoría a buscar.
     * @return La categoría si la encontrados.
     */
    Category findByName(String categoryName);

}
