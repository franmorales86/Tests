/**
 * Project: RecCore
 * Created by: raulanatol at 23/03/2013 15:52:23
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.assemblers;

import com.reclabs.recomendar.model.assemblers.GenericAssembler;
import com.reclabs.recomendar.model.documents.Category;
import com.reclabs.recomendar.objects.documents.category.CategoryDTO;
import com.reclabs.recomendar.objects.documents.category.CategoryTreeDTO;
import com.reclabs.recomendar.objects.list.ListCategoryTreeDTO;

import java.util.*;

/**
 * Emsamblador de categorías.
 * @author raulanatol
 */
public class CategoryAssembler extends GenericAssembler<CategoryDTO, Category> {

    /*
     * (non-Javadoc)
     * @see com.reclabs.recomendar.model.assemblers.GenericAssembler#internalToRight(java.lang.Object,
     * java.lang.Object)
     */
    @Override
    public CategoryDTO internalToRight(Category source, CategoryDTO result) {
        return result;
    }

    /*
     * (non-Javadoc)
     * @see com.reclabs.recomendar.model.assemblers.GenericAssembler#internalToLeft(java.lang.Object,
     * java.lang.Object)
     */
    @Override
    public Category internalToLeft(CategoryDTO source, Category result) {
        return result;
    }

    /**
     * Dado un listado de categorias obtenemos un elemento {@link ListCategoryTreeDTO} en formato árbol con la
     * herencia entre categorías.
     * @param categories El listado de las categorías.
     * @return El árbol de categorías.
     */
    public ListCategoryTreeDTO toTree(List<Category> categories) {
        HashMap<String, CategoryTreeDTO> elements = new HashMap<>();
        Stack<CategoryTreeDTO> pending = new Stack<>();
        for (Category category : categories) {
            CategoryTreeDTO item = toSimpleCategoryTreeDTO(category);
            if (category.getParent() != null) {
                CategoryTreeDTO parent = elements.get(category.getParent().getId());
                if (parent != null) {
                    parent.addChildren(item);
                    elements.put(item.getId(), item);
                } else {
                    pending.add(item);
                }
            } else {
                elements.put(item.getId(), item);
            }
        }
        // Revisamos todos los pending...
        while (!pending.isEmpty()) {
            CategoryTreeDTO item = pending.pop();
            CategoryTreeDTO parent = elements.get(item.getParentId());
            if (parent != null) {
                parent.addChildren(item);
                elements.put(item.getId(), item);
            } else {
                pending.add(item);
            }
        }
        List<CategoryTreeDTO> parents = new ArrayList<>();
        // Sacar los padres principales...
        for (CategoryTreeDTO element : elements.values()) {
            if (element.getParentId() == null) {
                parents.add(element);
            }
        }
        Collections.sort(parents);

        ListCategoryTreeDTO result = new ListCategoryTreeDTO();
        for (CategoryTreeDTO categoryTreeDTO : parents) {
            result.add(categoryTreeDTO);
        }

        return result;
    }

    /**
     * @param category
     * @return
     */
    private CategoryTreeDTO toSimpleCategoryTreeDTO(Category category) {
        CategoryTreeDTO result = new CategoryTreeDTO();
        result.setId(category.getId());
        result.setText(category.getName());
        if (category.getParent() != null) {
            result.setParentId(category.getParent().getId());
        }
        return result;
    }
}
