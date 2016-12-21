/**
 * Project: RecModel
 * Created by: raulanatol at 23/03/2013 15:45:44
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.model.services;

import com.reclabs.recomendar.model.documents.Category;
import com.reclabs.recomendar.objects.documents.category.CategoryDTO;
import com.reclabs.recomendar.objects.list.ListCategoryTreeDTO;

import java.util.List;

/**
 * Servicios asociados a las categorías.
 * @author raulanatol
 */
public interface CategoryService {

    /**
     * @return El listado de todas las categorías principales.
     */
    List<CategoryDTO> getMainCategories();

    /**
     * Creamos la categoría principal.
     * @param categoryDTO The categoryDTO
     */
    void createMainCategory(CategoryDTO categoryDTO);

    /**
     * Obtenemos la categoría con id indicada.
     * @param categoryId La id de la categoría
     * @return La categoría.
     */
    CategoryDTO getCategoryById(String categoryId);

    /**
     * Obtenemos la categoría con el nombre indicado.
     * @param categoryName El nombre de la categoría.
     * @return La categoría con el nombre indicado.
     */
    CategoryDTO getCategoryByName(String categoryName);

    /**
     * Obtenemos el documento de la categoría con el nombre indicado.
     * @param categoryName El nombre de la categoría.
     * @return El document de la categoría con el nombre indicado.
     */
    Category getCategoryDocByName(String categoryName);

    /**
     * Remove the category with id specified
     * @param categoryId Category to remove
     */
    void removeById(String categoryId);

    /**
     * @return Obtenemos el árbol de categorías.
     */
    ListCategoryTreeDTO getTree();

    /**
     * Creamos una categoría con los datos indicados.
     * @param categoryName El nombre de la nueva categoría.
     * @param parentId El id del padre de la neuva categoría.
     * @return La categoría creada en formato DTO.
     */
    CategoryDTO createCategory(String categoryName, String parentId);

}
