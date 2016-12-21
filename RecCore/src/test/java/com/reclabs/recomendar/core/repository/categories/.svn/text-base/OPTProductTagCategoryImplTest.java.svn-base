/**
 * Project: RecCore
 * Created by: fjmorales at 04/04/2013 16:28:16
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.repository.categories;

import com.reclabs.recomendar.core.repository.generic.RepositoryTest;
import com.reclabs.recomendar.model.documents.Category;
import com.reclabs.recomendar.model.documents.Tag;
import com.reclabs.recomendar.model.documents.categories.OPTProductTagCategory;
import com.reclabs.recomendar.model.repositories.categories.OPTProductTagCategoryRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * @author fjmorales
 */
public class OPTProductTagCategoryImplTest extends RepositoryTest {

    private OPTProductTagCategoryRepositoryImpl repoImpl;
    private MongoTemplate template;

    /**
     * @throws Exception
     */
    @Before
    public void setUp() {
        this.template = new MongoTemplate(mongo, DB_NAME);
        this.repoImpl = new OPTProductTagCategoryRepositoryImpl(this.template);
    }

    /**
     * @throws Exception
     */
    @After
    public void tearDown() {
        this.template.dropCollection(Tag.class);
        this.template.dropCollection(Category.class);
        this.template.dropCollection(OPTProductTagCategory.class);
    }

    /**
     * @return
     */
    private OPTProductTagCategory initialize() {
        Tag tag = new Tag();
        tag.setName("tagName");
        this.template.insert(tag);
        Category category = new Category();
        category.setDescription("descriptionCategory");
        category.setName("nameCategory");
        this.template.insert(category);
        OPTProductTagCategory ptc = new OPTProductTagCategory();
        ptc.setCategory(category);
        ptc.setTag(tag);
        ptc.setNumberOfProducts(23);
        return ptc;
    }

    /**
     * @see OPTProductTagCategoryRepository#save(Iterable)
     */
    @Test
    public void testCreateUpdatePTC() {
        // Test de insert en BBDD
        OPTProductTagCategory ptc = initialize();
        this.repoImpl.save(ptc);

        List<OPTProductTagCategory> ptcList = this.template.findAll(OPTProductTagCategory.class);
        int samplesInCollection = ptcList.size();

        assertEquals("Only 1 Sample should exist collection, but there are " + samplesInCollection, 1, samplesInCollection);
        OPTProductTagCategory ptcBBDD = ptcList.get(0);
        assertThat(ptcBBDD, is(ptc));

        // Test de update en BBDD
        ptc.setNumberOfProducts(45);
        this.repoImpl.save(ptc);

        ptcList = this.template.findAll(OPTProductTagCategory.class);
        samplesInCollection = ptcList.size();

        assertEquals("Only 1 Sample should exist collection, but there are " + samplesInCollection, 1, samplesInCollection);
        ptcBBDD = ptcList.get(0);
        assertThat(ptcBBDD, is(ptc));

    }

    /**
     * @see OPTProductTagCategoryRepository#delete(OPTProductTagCategory)
     * @see OPTProductTagCategoryRepository#delete(String)
     */
    @Test
    public void testDeleteTag() {
        OPTProductTagCategory ptc = initialize();
        this.template.insert(ptc);

        // Test de delete by Entity
        this.repoImpl.delete(ptc);
        List<OPTProductTagCategory> ptcList = this.template.findAll(OPTProductTagCategory.class);
        int samplesInCollection = ptcList.size();
        assertEquals("No deberia existir ningun producto, pero hay " + samplesInCollection, 0, samplesInCollection);

        // Test de delete by Id
        this.template.insert(ptc);
        this.repoImpl.delete(ptc.getId());
        ptcList = this.template.findAll(OPTProductTagCategory.class);
        samplesInCollection = ptcList.size();
        assertEquals("No deberia existir ninguna coleccion, pero hay " + samplesInCollection, 0, samplesInCollection);
    }

    /**
     * @see OPTProductTagCategoryRepository#findOne(java.io.Serializable)
     */
    @Test
    public void testFindById() {
        OPTProductTagCategory ptc = initialize();
        OPTProductTagCategory ptc2 = initialize();
        ptc2.setNumberOfProducts(67);
        this.template.insert(ptc);
        this.template.insert(ptc2);

        OPTProductTagCategory ptcBBDD = this.repoImpl.findOne(ptc.getId());
        assertThat(ptcBBDD, is(ptc));

        ptcBBDD = this.repoImpl.findOne(ptc2.getId());
        assertThat(ptcBBDD, is(ptc2));

        ptcBBDD = this.repoImpl.findOne("ptc3");
        assertNull(ptcBBDD);
    }

    /**
     * @see OPTProductTagCategoryRepository#findByTagCategory(String, String)
     */
    @Test
    public void findByTagCategoryTest() {
        OPTProductTagCategory ptc = initialize();
        OPTProductTagCategory ptc2 = initialize();
        ptc2.setNumberOfProducts(67);
        this.template.insert(ptc);
        this.template.insert(ptc2);

        OPTProductTagCategory ptcBBDD = this.repoImpl.findByTagCategory(ptc.getTag().getId(), ptc.getCategory().getId());
        assertThat(ptcBBDD, is(ptc));

        ptcBBDD = this.repoImpl.findByTagCategory(ptc2.getTag().getId(), ptc2.getCategory().getId());
        assertThat(ptcBBDD, is(ptc2));

        ptcBBDD = this.repoImpl.findByTagCategory(ptc2.getTag().getId(), ptc.getCategory().getId());
        assertNull(ptcBBDD);
    }

    /**
     * @see OPTProductTagCategoryRepository#findByCategory(String)
     */
    @Test
    public void findByCategoryTest() {
        OPTProductTagCategory ptc = initialize();
        OPTProductTagCategory ptc2 = initialize();
        OPTProductTagCategory ptc3 = initialize();
        ptc2.setNumberOfProducts(67);
        ptc3.setNumberOfProducts(276);
        ptc2.setCategory(ptc.getCategory());
        this.template.insert(ptc);
        this.template.insert(ptc2);
        this.template.insert(ptc3);

        List<OPTProductTagCategory> ptcBBDDList = this.repoImpl.findByCategory(ptc.getCategory().getId());
        int samplesInCollection = ptcBBDDList.size();
        assertEquals("Deberia existir 2 productos, pero hay " + samplesInCollection, 2, samplesInCollection);

        ptcBBDDList = this.repoImpl.findByCategory(ptc3.getCategory().getId());
        samplesInCollection = ptcBBDDList.size();
        assertEquals("Deberia existir 1 producto, pero hay " + samplesInCollection, 1, samplesInCollection);

        ptcBBDDList = this.repoImpl.findByCategory(ptc2.getTag().getId());
        samplesInCollection = ptcBBDDList.size();
        assertEquals("No deberia existir ningun producto, pero hay " + samplesInCollection, 0, samplesInCollection);

    }
}
