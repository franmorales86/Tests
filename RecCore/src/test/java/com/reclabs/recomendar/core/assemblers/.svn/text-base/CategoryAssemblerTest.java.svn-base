/**
 * Project: RecCore
 * Created by: raulanatol at 24/03/2013 16:58:20
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.assemblers;

import com.reclabs.recomendar.model.documents.Category;
import com.reclabs.recomendar.objects.documents.category.CategoryTreeDTO;
import com.reclabs.recomendar.objects.list.ListCategoryTreeDTO;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author raulanatol
 */
public class CategoryAssemblerTest {

    /**
     * @see CategoryAssembler#toTree(java.util.List)
     */
    @Test
    public void toTreeOnlyParentsTest() {
        Category parent1 = new Category();
        parent1.setId("10");
        parent1.setName("Parent1");
        parent1.setParent(null);

        Category parent2 = new Category();
        parent2.setId("20");
        parent2.setName("Parent2");
        parent2.setParent(null);
        List<Category> categories = Arrays.asList(parent1, parent2);

        ListCategoryTreeDTO expected = new ListCategoryTreeDTO();
        CategoryTreeDTO categoryTreeDTO1 = new CategoryTreeDTO();
        categoryTreeDTO1.setId("10");
        categoryTreeDTO1.setText("Parent1");
        categoryTreeDTO1.setChildren(null);
        expected.add(categoryTreeDTO1);
        CategoryTreeDTO categoryTreeDTO2 = new CategoryTreeDTO();
        categoryTreeDTO2.setId("20");
        categoryTreeDTO2.setText("Parent2");
        categoryTreeDTO2.setChildren(null);
        expected.add(categoryTreeDTO2);

        CategoryAssembler classUnderTest = new CategoryAssembler();
        assertThat(classUnderTest.toTree(categories), is(expected));
    }

    /**
     * @see CategoryAssembler#toTree(java.util.List)
     */
    @Test
    public void toTreeWithChildrenTest() {
        Category parent1 = new Category();
        parent1.setId("10");
        parent1.setName("Parent1");
        parent1.setParent(null);

        Category child1 = new Category();
        child1.setId("101");
        child1.setName("Child1");
        child1.setParent(parent1);

        Category child2 = new Category();
        child2.setId("102");
        child2.setName("Child2");
        child2.setParent(parent1);

        Category parent2 = new Category();
        parent2.setId("20");
        parent2.setName("Parent2");
        parent2.setParent(null);

        Category child3 = new Category();
        child3.setId("201");
        child3.setName("Child3");
        child3.setParent(parent2);

        Category parent3 = new Category();
        parent3.setId("30");
        parent3.setName("Parent3");
        parent3.setParent(null);

        List<Category> categories = Arrays.asList(parent1, parent2, child1, child2, child3, parent3);

        ListCategoryTreeDTO expected = new ListCategoryTreeDTO();

        CategoryTreeDTO expectedChild1 = new CategoryTreeDTO();
        expectedChild1.setId("101");
        expectedChild1.setText("Child1");
        CategoryTreeDTO expectedChild2 = new CategoryTreeDTO();
        expectedChild2.setId("102");
        expectedChild2.setText("Child2");
        CategoryTreeDTO expectedParent1 = new CategoryTreeDTO();
        expectedParent1.setChildren(Arrays.asList(expectedChild1, expectedChild2));
        expectedParent1.setText("Parent1");
        expectedParent1.setId("10");

        CategoryTreeDTO expectedChild3 = new CategoryTreeDTO();
        expectedChild3.setId("201");
        expectedChild3.setText("Child3");
        CategoryTreeDTO expectedParent2 = new CategoryTreeDTO();
        expectedParent2.setChildren(Arrays.asList(expectedChild3));
        expectedParent2.setId("20");
        expectedParent2.setText("Parent2");

        CategoryTreeDTO expectedParent3 = new CategoryTreeDTO();
        expectedParent3.setId("30");
        expectedParent3.setText("Parent3");

        expected.add(expectedParent1);
        expected.add(expectedParent2);
        expected.add(expectedParent3);

        CategoryAssembler classUnderTest = new CategoryAssembler();
        assertThat(classUnderTest.toTree(categories), is(expected));
    }
}
