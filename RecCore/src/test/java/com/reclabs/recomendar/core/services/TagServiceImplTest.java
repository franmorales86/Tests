/**
 * Project: RecCore
 * Created by: fjmorales at 01/04/2013 18:30:41
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.services;

import com.reclabs.recomendar.core.assemblers.TagAssembler;
import com.reclabs.recomendar.model.documents.Tag;
import com.reclabs.recomendar.model.documents.items.ItemTag;
import com.reclabs.recomendar.model.repositories.TagRepository;
import com.reclabs.recomendar.objects.TagDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.dao.DuplicateKeyException;

import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * @author fjmorales
 */
@SuppressWarnings("unchecked")
@RunWith(MockitoJUnitRunner.class)
public class TagServiceImplTest {

    @Mock
    private TagRepository tagRepository;

    @Mock
    private TagAssembler tagAssembler;

    @InjectMocks
    private TagServiceImpl tagService = new TagServiceImpl();

    /**
     * Método para la inicialización de los datos de una etiqueta
     * @return Datos de la etiqueta
     */
    private Tag initialize() {
        Tag dummyTag = new Tag();
        String idValue = "25";
        String nameValue = "juegos";
        dummyTag.setId(idValue);
        dummyTag.setName(nameValue);
        return dummyTag;
    }

    /**
     * Método para la inicialización de los datos de un objeto de transferencia con los datos de una etiqueta
     * @return Datos de la etiqueta
     */
    private TagDTO initializeDTO() {
        TagDTO dummyTagDTO = new TagDTO();
        String idValue = "25";
        String nameValue = "juegos";
        dummyTagDTO.setId(idValue);
        dummyTagDTO.setName(nameValue);
        return dummyTagDTO;
    }

    /**
     * @see TagServiceImpl#findAllTags()
     */
    @Test
    public void findAllTagsTest() {
        Tag dummyTag = initialize();
        Tag dummyTag2 = initialize();
        Tag dummyTag3 = initialize();
        Tag dummyTag4 = initialize();
        dummyTag2.setId("2222");
        dummyTag3.setId("3333");
        dummyTag4.setId("4444");

        TagDTO dummyTagDTO = initializeDTO();
        TagDTO dummyTagDTO2 = initializeDTO();
        TagDTO dummyTagDTO3 = initializeDTO();
        TagDTO dummyTagDTO4 = initializeDTO();
        dummyTagDTO2.setId("2222");
        dummyTagDTO3.setId("3333");
        dummyTagDTO4.setId("4444");

        tagService.tagAssembler = new TagAssembler();
        Mockito.when(tagRepository.findAll()).thenReturn(Arrays.asList(dummyTag, dummyTag2, dummyTag3, dummyTag4));

        List<TagDTO> result = tagService.findAllTags();
        assertThat(result, is(Arrays.asList(dummyTagDTO, dummyTagDTO2, dummyTagDTO3, dummyTagDTO4)));

    }

    /**
     * @see TagServiceImpl#findListByExactlyName(List)
     */
    @Test
    public void findListByExactlyNameTest() {
        Tag dummyTag = initialize();
        Tag dummyTag2 = initialize();
        Tag dummyTag3 = initialize();
        Tag dummyTag4 = initialize();
        dummyTag2.setId("2222");
        dummyTag2.setName("electrónica");
        dummyTag3.setId("3333");
        dummyTag3.setName("deportes");
        dummyTag4.setId("4444");
        dummyTag4.setName("moda");

        List<String> names = new ArrayList<>();
        names.add("juegos");
        names.add("electrónica");
        names.add("deportes");
        names.add("moda");
        Mockito.when(tagRepository.findListByExactlyName(names)).thenReturn(Arrays.asList(dummyTag, dummyTag2, dummyTag3, dummyTag4));

        Map<String, Tag> result = new HashMap<>();
        result.put("juegos", dummyTag);
        result.put("electrónica", dummyTag2);
        result.put("deportes", dummyTag3);
        result.put("moda", dummyTag4);

        assertThat(tagService.findListByExactlyName(names), is(result));
    }

    @Test(expected = DuplicateKeyException.class)
    public void findListByExactlyNameTestWithDuplicationException() {
        Tag dummyTag = initialize();
        Tag dummyTag2 = initialize();
        Tag dummyTag3 = initialize();
        Tag dummyTag4 = initialize();
        dummyTag2.setId("2222");
        dummyTag2.setName("electrónica");
        dummyTag3.setId("3333");
        dummyTag3.setName("deportes");
        dummyTag4.setId("4444");
        dummyTag4.setName("moda");

        List<String> names = new ArrayList<>();
        names.add("juegos");
        names.add("electrónica");
        names.add("deportes");
        names.add("moda");
        Mockito.when(tagRepository.findListByExactlyName(names)).thenReturn(Arrays.asList(dummyTag, dummyTag2, dummyTag3, dummyTag4, dummyTag2));

        tagService.findListByExactlyName(names);
    }

    /**
     * @see TagServiceImpl#findOrInsertTagsByName(List)
     */
    @Test
    public void findOrInsertTagsByNameWhenFind() {
        Tag dummyTag = new Tag();
        Tag dummyTag2 = new Tag();
        Tag dummyTag3 = new Tag();
        Tag dummyTag4 = new Tag();
        dummyTag.setId("1234");
        dummyTag.setName("juegos");
        dummyTag2.setId("2222");
        dummyTag2.setName("electrónica");
        dummyTag3.setId("3333");
        dummyTag3.setName("deportes");
        dummyTag4.setId("4444");
        dummyTag4.setName("moda");

        List<Tag> tagList = new ArrayList<>();
        tagList.add(dummyTag);
        tagList.add(dummyTag2);
        tagList.add(dummyTag3);
        tagList.add(dummyTag4);

        List<String> names = new ArrayList<>();
        names.add("Juegos");
        names.add("electrónica");
        names.add("dePortes");
        names.add("modA");

        Mockito.when(tagAssembler.toItemTag(dummyTag)).thenReturn(new ItemTag());
        Mockito.when(tagRepository.findListByExactlyName(Mockito.any(ArrayList.class))).thenReturn(tagList);

        List<ItemTag> result = tagService.findOrInsertTagsByName(names);
        assertTrue(result.size() == 4);
    }

    /**
     * @see TagServiceImpl#findOrInsertTagsByName(List)
     */
    @Test
    public void findOrInsertTagsByNameWhenInsert() {
        List<String> names = new ArrayList<>();
        names.add("Juegos");
        names.add("eLectrónica");
        names.add("dePortes");
        names.add("modA");

        Mockito.when(tagRepository.findListByExactlyName(names)).thenReturn(null);

        List<ItemTag> result = tagService.findOrInsertTagsByName(names);
        assertTrue(result.size() == 4);
    }

    /**
     * @see TagServiceImpl#createOrUpdateTags(List)
     */
    @Test
    public void createOrUpdateTagWithNullNames() {
        List<ItemTag> itemTagsList = new ArrayList<>();
        ItemTag itemTag = new ItemTag();
        itemTag.setTagId(null);
        ItemTag itemTag2 = new ItemTag();
        itemTag2.setTagId(null);
        ItemTag itemTag3 = new ItemTag();
        itemTag3.setTagId(null);
        itemTagsList.add(itemTag);
        itemTagsList.add(itemTag2);
        itemTagsList.add(itemTag3);

        assertTrue(tagService.createOrUpdateTags(itemTagsList).size() == 0);
    }

    /**
     * @see TagServiceImpl#createOrUpdateTags(List)
     */
    @Test
    public void createOrUpdateTagWithValidNames() {
        List<ItemTag> itemTagsList = new ArrayList<>();
        ItemTag itemTag = new ItemTag();
        itemTag.setTagId(null);
        itemTag.setName("2770i7");
        ItemTag itemTag2 = new ItemTag();
        itemTag2.setTagId(null);
        itemTag2.setName("3770i7");
        ItemTag itemTag3 = new ItemTag();
        itemTag3.setTagId(null);
        itemTag3.setName("4770i7");
        itemTagsList.add(itemTag);
        itemTagsList.add(itemTag2);
        itemTagsList.add(itemTag3);

        assertTrue(tagService.createOrUpdateTags(itemTagsList).size() == 3);
    }

    /**
     * @see TagServiceImpl#createOrUpdateTags(List)
     */
    @Test
    public void createOrUpdateTagWithValidNamesAndTagId() {
        List<ItemTag> itemTagsList = new ArrayList<>();
        ItemTag itemTag = new ItemTag();
        itemTag.setTagId("1024");
        itemTag.setName("2770i7");
        ItemTag itemTag2 = new ItemTag();
        itemTag2.setTagId("2048");
        itemTag2.setName("3770i7");
        ItemTag itemTag3 = new ItemTag();
        itemTag3.setTagId("4096");
        itemTag3.setName("4770i7");
        itemTagsList.add(itemTag);
        itemTagsList.add(itemTag2);
        itemTagsList.add(itemTag3);

        assertTrue(tagService.createOrUpdateTags(itemTagsList).size() == 3);
    }
}
