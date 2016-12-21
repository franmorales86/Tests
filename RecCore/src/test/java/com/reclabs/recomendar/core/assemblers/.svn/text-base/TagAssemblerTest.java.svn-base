/**
 * Project: RecCore
 * Created by: fjmorales at 01/04/2013 18:46:01
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.assemblers;

import com.reclabs.recomendar.model.documents.Tag;
import com.reclabs.recomendar.objects.TagDTO;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * @author fjmorales
 */
public class TagAssemblerTest {
    private TagAssembler classUnderTest;

    /**
     * Creacion previa del assembler a testear
     */
    @Before
    public void init() {
        classUnderTest = new TagAssembler();
    }

    /**
     * @see TagAssembler#toRight(Tag)
     */
    @Test
    public void toRightTest() {
        Tag dummyTag = new Tag();
        String idValue = "25";
        String nameValue = "juegos";
        dummyTag.setId(idValue);
        dummyTag.setName(nameValue);
        TagDTO result = classUnderTest.toRight(dummyTag);

        assertThat(result.getId(), is(idValue));
        assertThat(result.getName(), is(nameValue));
    }

    /**
     * @see TagAssembler#toLeft(TagDTO)
     */
    @Test
    public void toLeftTest() {
        TagDTO dummyTag = new TagDTO();
        String idValue = "25";
        String nameValue = "juegos";
        dummyTag.setId(idValue);
        dummyTag.setName(nameValue);
        Tag result = classUnderTest.toLeft(dummyTag);

        assertThat(result.getId(), is(idValue));
        assertThat(result.getName(), is(nameValue));
    }

    /**
     * @see TagAssembler#toRight(java.util.List)
     */
    @Test
    public void toListRightTest() {
        Tag dummyTag = new Tag();
        String idValue = "25";
        String nameValue = "juegos";
        dummyTag.setId(idValue);
        dummyTag.setName(nameValue);
        Tag dummyTag2 = new Tag();
        String idValue2 = "213";
        String nameValue2 = "moda";
        dummyTag2.setId(idValue2);
        dummyTag2.setName(nameValue2);
        List<TagDTO> result = classUnderTest.toRight(Arrays.asList(dummyTag, dummyTag2));

        int samplesInCollection = result.size();
        assertEquals("Deberian existir 2 etiquetas, pero hay " + samplesInCollection, 2, samplesInCollection);

        assertThat(result.get(0).getId(), is(idValue));
        assertThat(result.get(0).getName(), is(nameValue));
        assertThat(result.get(1).getId(), is(idValue2));
        assertThat(result.get(1).getName(), is(nameValue2));

    }

}
