/**
 * Project: RecCore
 * Created by: fjmorales at 19/11/2012 22:11:28
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.repository;

import com.reclabs.recomendar.core.repository.generic.RepositoryTest;
import com.reclabs.recomendar.model.documents.Tag;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * @author fjmorales
 */
public class TagRepositoryImplTest extends RepositoryTest {

    private TagRepositoryImpl repoImpl;
    private MongoTemplate template;

    @Before
    public void setUp() {
        this.template = new MongoTemplate(mongo, DB_NAME);
        this.repoImpl = new TagRepositoryImpl(this.template);
    }

    @After
    public void tearDown() {
        this.template.dropCollection(Tag.class);
    }

    /**
     * @return El tag
     */
    public Tag initialize() {
        Tag tag = new Tag();
        tag.setName("testTagName");
        return tag;
    }

    /**
     *
     */
    @Test
    public void testCreateUpdateTag() {
        // Test de insert en BBDD
        Tag tag = initialize();
        this.repoImpl.save(tag);

        List<Tag> tagList = this.template.findAll(Tag.class);
        int samplesInCollection = tagList.size();

        assertEquals("Only 1 Sample should exist collection, but there are " + samplesInCollection, 1, samplesInCollection);
        Tag tagBBDD = tagList.get(0);
        assertThat(tagBBDD, is(tag));

        // Test de update en BBDD
        tag.setName("testTagName2");
        this.repoImpl.save(tag);

        tagList = this.template.findAll(Tag.class);
        samplesInCollection = tagList.size();

        assertEquals("Only 1 Sample should exist collection, but there are " + samplesInCollection, 1, samplesInCollection);
        tagBBDD = tagList.get(0);
        assertThat(tagBBDD, is(tag));

    }

    /**
     *
     */
    @Test
    public void testDeleteTag() {
        Tag tag = initialize();
        this.template.insert(tag);

        // Test de delete by Entity
        this.repoImpl.delete(tag);
        List<Tag> tagList = this.template.findAll(Tag.class);
        int samplesInCollection = tagList.size();
        assertEquals("No deberia existir ningun producto, pero hay " + samplesInCollection, 0, samplesInCollection);

        // Test de delete by Id
        this.template.insert(tag);
        this.repoImpl.delete(tag.getId());
        tagList = this.template.findAll(Tag.class);
        samplesInCollection = tagList.size();
        assertEquals("No deberia existir ninguna coleccion, pero hay " + samplesInCollection, 0, samplesInCollection);
    }

    /**
     * @see TagRepositoryImpl#findByExactlyName(String)
     */
    @Test
    public void testFindByExactlyName() {
        Tag tag = initialize();
        Tag tag2 = initialize();
        tag2.setName("testTagName2");
        this.template.insert(tag);
        this.template.insert(tag2);

        List<Tag> tagList = this.repoImpl.findByExactlyName("TESTTAGNAME");
        int samplesInCollection = tagList.size();
        assertEquals("Deberian existir 1 etiqueta coincidentes, pero hay " + samplesInCollection, 1, samplesInCollection);

        tagList = this.repoImpl.findByExactlyName("testTagName2");
        samplesInCollection = tagList.size();
        assertEquals("Deberian existir 1 etiqueta coincidente, pero hay " + samplesInCollection, 1, samplesInCollection);

        tagList = this.repoImpl.findByExactlyName("est");
        samplesInCollection = tagList.size();
        assertEquals("No deberian existir ninguna etiqueta coincidente, pero hay " + samplesInCollection, 0, samplesInCollection);
    }

    /**
     * @see TagRepositoryImpl#findByName(String)
     */
    @Test
    public void testFindByName() {
        Tag tag = initialize();
        Tag tag2 = initialize();
        tag2.setName("testTagName status");
        this.template.insert(tag);
        this.template.insert(tag2);

        List<Tag> tagList = this.repoImpl.findByName("ESTT");
        int samplesInCollection = tagList.size();
        assertEquals("Deberian existir 2 etiquetas coincidentes, pero hay " + samplesInCollection, 2, samplesInCollection);

        tagList = this.repoImpl.findByName("ame stat");
        samplesInCollection = tagList.size();
        assertEquals("Deberian existir 1 etiqueta coincidente, pero hay " + samplesInCollection, 1, samplesInCollection);

        tagList = this.repoImpl.findByName("testN");
        samplesInCollection = tagList.size();
        assertEquals("No deberian existir ninguna etiqueta coincidente, pero hay " + samplesInCollection, 0, samplesInCollection);
    }

    /**
     * @see TagRepositoryImpl#findOne(java.io.Serializable)
     */
    @Test
    public void testFindById() {
        Tag tag = initialize();
        Tag tag2 = initialize();
        tag2.setName("testName2");
        this.template.insert(tag);
        this.template.insert(tag2);

        Tag tagBBDD = this.repoImpl.findOne(tag.getId());
        assertThat(tagBBDD, is(tag));

        tagBBDD = this.repoImpl.findOne(tag2.getId());
        assertThat(tagBBDD, is(tag2));

        tagBBDD = this.repoImpl.findOne("tag3");
        assertNull(tagBBDD);
    }

    /**
     * @see TagRepositoryImpl#findListByExactlyName(List)
     */
    @Test
    public void testFindListByExactlyName() {
        Tag tag = initialize();
        Tag tag2 = initialize();
        Tag tag3 = initialize();
        Tag tag4 = initialize();
        tag2.setName("testTagName2");
        tag3.setName("testTagName3");
        tag4.setName("testTagName4");
        this.template.insert(tag);
        this.template.insert(tag2);
        this.template.insert(tag3);
        this.template.insert(tag4);

        List<Tag> tagList = this.repoImpl.findListByExactlyName(Arrays.asList("testTagName"));
        int samplesInCollection = tagList.size();
        assertEquals("Deberian existir 1 etiqueta coincidentes, pero hay " + samplesInCollection, 1, samplesInCollection);
        assertThat(tag, is(tagList.get(0)));

        tagList = this.repoImpl.findListByExactlyName(Arrays.asList("testTagName", "testTagName3", "TESTTAGNAMe"));
        samplesInCollection = tagList.size();
        assertEquals("Deberian existir 2 etiquetas coincidentes, pero hay " + samplesInCollection, 2, samplesInCollection);

        tagList = this.repoImpl.findListByExactlyName(Arrays.asList("testTagName", "testTagName3", "TESTTAGNAMe", "testTagName4", "testTagName2", "test"));
        samplesInCollection = tagList.size();
        assertEquals("Deberian existir 4 etiquetas coincidentes, pero hay " + samplesInCollection, 4, samplesInCollection);

        tagList = this.repoImpl.findListByExactlyName(Arrays.asList("est"));
        samplesInCollection = tagList.size();
        assertEquals("No deberian existir ninguna etiqueta coincidente, pero hay " + samplesInCollection, 0, samplesInCollection);
    }

    @Test
    public void renameWithUnknownTag() {
        Tag tag1 = new Tag();
        tag1.setId("ID_1");
        tag1.setName("KNOW");

        template.insert(tag1);
        repoImpl.renameTag("NO", "newName");

        assertThat(repoImpl.findOne("ID_1").getName(), is("KNOW"));
    }

    @Test
    public void renameWithKnownTag() {
        Tag tag1 = new Tag();
        tag1.setId("ID_1");
        tag1.setName("KNOW");

        template.insert(tag1);
        repoImpl.renameTag("ID_1", "newName");
        Tag tagNew = repoImpl.findOne("ID_1");
        assertThat(tagNew.getName(), is("newName"));
    }

    @Test
    public void renameWithMultipleTag() {
        Tag tag1 = new Tag();
        tag1.setId("ID_1");
        tag1.setName("KNOW");

        Tag tag2 = new Tag();
        tag2.setId("ID_2");
        tag2.setName("NEW_NAME");

        template.insert(tag1);
        template.insert(tag2);

        repoImpl.renameTag("ID_2", "NEW_NAME_2");
        assertThat(repoImpl.findOne("ID_2").getName(), is("NEW_NAME_2"));
    }

}
