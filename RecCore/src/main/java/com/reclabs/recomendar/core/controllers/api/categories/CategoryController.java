/**
 * Project: RecCore
 * Created by: raulanatol at 23/03/2013 15:40:54
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.controllers.api.categories;

import com.reclabs.recomendar.common.helpers.ParameterHelper;
import com.reclabs.recomendar.model.services.CategoryService;
import com.reclabs.recomendar.objects.ResponseDTO;
import com.reclabs.recomendar.objects.documents.category.CategoryDTO;
import com.reclabs.recomendar.objects.list.ListCategoryTreeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author raulanatol
 */
@Controller
@RequestMapping(value = "/v1/categories/")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * @param categoryDTO The category to create
     * @return ResponseDTO.OK if always is OK
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize("#oauth2.isUser() and #oauth2.clientHasRole('ROLE_SANDBOX') and hasAnyRole('ROLE_SANDBOX_ADMIN', 'ROLE_SANDBOX_USER') and #oauth2.hasScope('write')")
    public ResponseDTO createCategory(@RequestBody CategoryDTO categoryDTO) {
        ParameterHelper.assertNull(categoryDTO);
        ParameterHelper.assertEmpty(categoryDTO.getName());
        ParameterHelper.assertEmpty(categoryDTO.getDescription());
        categoryService.createMainCategory(categoryDTO);
        return ResponseDTO.OK;
    }

    /**
     * Note: Using for RecView
     * @return Obtenemos un listado de todas las categorías principales.
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "main/all")
    public List<CategoryDTO> getMainCategories() {
        return categoryService.getMainCategories();
    }

    /**
     * @param categoryDTO
     */
    //TODO mirar si está en deshuso.
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "main/create")
    public void createMainCategory(@RequestBody CategoryDTO categoryDTO) {
        ParameterHelper.assertNull(categoryDTO);
        ParameterHelper.assertEmpty(categoryDTO.getName());
        categoryService.createMainCategory(categoryDTO);
    }

    /**
     * Dada la id de una categoría la obtenemos desde base de datos.
     * @param categoryId La id de la categoría.
     * @return La categoría.
     */
    //TODO mirar si está en deshuso.
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "get/{categoryId}")
    public CategoryDTO getCategoryById(@PathVariable("categoryId") String categoryId) {
        ParameterHelper.assertEmpty(categoryId);
        return categoryService.getCategoryById(categoryId);
    }

    /**
     * Dado el nombre de una categoría la obtenemos de base de datos.
     * @param categoryName El nombre de la categoría.
     * @return La categoría encontrada, error en caso de que no se encuentre.
     */
    //TODO mirar si está en deshuso.
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/{categoryName}")
    public CategoryDTO getCategoryByName(@PathVariable("categoryName") String categoryName) {
        ParameterHelper.assertEmpty(categoryName);
        return categoryService.getCategoryByName(categoryName);
    }

    /**
     * Dada la id de una categoría la borramos de la base de datos.
     * @param categoryId La id de la categoría.
     */
    //TODO mirar si está en deshuso.
    @ResponseBody
    @RequestMapping(method = RequestMethod.DELETE, value = "/{categoryId}")
    public void remCategoryById(@PathVariable("categoryId") String categoryId) {
        ParameterHelper.assertEmpty(categoryId);
        categoryService.removeById(categoryId);
    }

    /**
     * @return El árbol de categorías.
     */
    //TODO mirar si está en deshuso.
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "tree")
    public ListCategoryTreeDTO getTree() {
        return categoryService.getTree();
    }

    /**
     * Creamos la categoría con nombre indicado con padre con id pasado por parámetros.
     * @param categoryName El nombre de la nueva categoría.
     * @param parentId La id del padre.
     * @return La nueva categoría creada.
     */
    //TODO mirar si está en deshuso.
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "create/{categoryName}/{parentId}")
    @PreAuthorize("#oauth2.isUser() and #oauth2.clientHasRole('ROLE_SANDBOX') and #oauth2.hasScope('write')")
    public CategoryDTO createCategory(@PathVariable("categoryName") String categoryName, @PathVariable("parentId") String parentId) {
        ParameterHelper.assertNull(categoryName);
        ParameterHelper.assertNull(parentId);
        return categoryService.createCategory(categoryName, parentId);
    }

    /**
     * Obtenemos todas las caegorías.
     * @return El listado de categorías.
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "list/all")
    @PreAuthorize("#oauth2.isUser() and #oauth2.clientHasRole('ROLE_SANDBOX') and hasAnyRole('ROLE_SANDBOX_ADMIN', 'ROLE_SANDBOX_USER') and #oauth2.hasScope('write')")
    public List<CategoryDTO> getAll() {
        return categoryService.getMainCategories();
    }

}
