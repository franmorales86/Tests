/**
 * Project: RecCore
 * Created by: raulanatol at 23/03/2013 15:48:44
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.services;

import com.reclabs.recomendar.common.helpers.types.NullHelper;
import com.reclabs.recomendar.core.assemblers.CategoryAssembler;
import com.reclabs.recomendar.model.documents.Category;
import com.reclabs.recomendar.model.exceptions.CategoryNotFoundException;
import com.reclabs.recomendar.model.repositories.CategoryRepository;
import com.reclabs.recomendar.model.services.CategoryService;
import com.reclabs.recomendar.objects.documents.category.CategoryDTO;
import com.reclabs.recomendar.objects.list.ListCategoryTreeDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author raulanatol
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryAssembler categoryAssembler;

    /*
     * (non-Javadoc)
     * @see com.reclabs.recomendar.model.services.CategoryService#getMainCategories()
     */
    @Override
    public List<CategoryDTO> getMainCategories() {
        List<Category> mainCategories = categoryRepository.getMainCategories();
        return categoryAssembler.toRight(mainCategories);
    }

    /*
     * (non-Javadoc)
     * @see
     * com.reclabs.recomendar.model.services.CategoryService#createMainCategory(com.reclabs.recomendar.objects.
     * documents.category.CategoryDTO)
     */
    //TODO quitar el DTO y poner directamente el document
    @Override
    public void createMainCategory(CategoryDTO categoryDTO) {
        LOGGER.info("[Starting process to create a category {}]", categoryDTO.getName());
        Category document = categoryAssembler.toLeft(categoryDTO);
        categoryRepository.save(document);
    }

    /*
     * (non-Javadoc)
     * @see com.reclabs.recomendar.model.services.CategoryService#getCategoryById(java.lang.String)
     */
    @Override
    public CategoryDTO getCategoryById(String categoryId) {
        //TODO hacer que si no se encuentra la categoría se envie una exception
        Category document = categoryRepository.findOne(categoryId);
        return categoryAssembler.toRight(document);
    }

    /*
     * (non-Javadoc)
     * @see com.reclabs.recomendar.model.services.CategoryService#removeById(java.lang.String)
     */
    @Override
    public void removeById(String categoryId) {
        categoryRepository.delete(categoryId);
    }

    /*
     * (non-Javadoc)
     * @see com.reclabs.recomendar.model.services.CategoryService#getTree()
     */
    @Override
    public ListCategoryTreeDTO getTree() {
        List<Category> categories = categoryRepository.findAll();
        return categoryAssembler.toTree(categories);
    }

    /*
     * (non-Javadoc)
     * @see com.reclabs.recomendar.model.services.CategoryService#createCategory(java.lang.String,
     * java.lang.String)
     */
    @Override
    public CategoryDTO createCategory(String categoryName, String parentId) {
        Category parent = categoryRepository.findOne(parentId);
        Category newCategory = new Category();
        newCategory.setName(categoryName);
        newCategory.setParent(parent);
        Category savedCategory = categoryRepository.save(newCategory);
        return categoryAssembler.toRight(savedCategory);
    }

    /*
     * (non-Javadoc)
     * @see com.reclabs.recomendar.model.services.CategoryService#getCategoryByName(java.lang.String)
     */
    @Override
    public CategoryDTO getCategoryByName(String categoryName) {
        Category category = categoryRepository.findByName(categoryName);
        if (NullHelper.isAnyNull(category)) {
            throw new CategoryNotFoundException(categoryName);
        }
        return categoryAssembler.toRight(category);
    }

    /*
     * (non-Javadoc)
     * @see com.reclabs.recomendar.model.services.CategoryService#getCategoryDocByName(java.lang.String)
     */
    @Override
    public Category getCategoryDocByName(String categoryName) {
        return categoryRepository.findByName(categoryName);
    }

}
