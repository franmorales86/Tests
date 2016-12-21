/**
 * Project: RecCore
 * Created by: fjmorales at 01/04/2013 19:49:52
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.controllers.api.tags;

import com.reclabs.recomendar.common.exceptions.RecIllegalArgumentException;
import com.reclabs.recomendar.core.services.TagServiceImpl;
import com.reclabs.recomendar.model.services.TagService;
import com.reclabs.recomendar.objects.TagDTO;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

/**
 * @author fjmorales
 */
public class TagControllerTest {

    @InjectMocks
    protected TagController tagController = new TagController();

    @Mock
    private final TagService tagService = new TagServiceImpl();

    /**
     * @see TagController#createOrFindTag(com.reclabs.recomendar.objects.TagDTO)
     */
    @Test(expected = RecIllegalArgumentException.class)
    public void createOrFindTagTestWithNull() {
        tagController.createOrFindTag(null);
    }

    /**
     * @see TagController#createOrFindTag(com.reclabs.recomendar.objects.TagDTO)
     */
    @Test(expected = RecIllegalArgumentException.class)
    public void createOrFindTagTestWithEmptyString() {
        TagDTO tag = new TagDTO();
        tag.setName("");
        tagController.createOrFindTag(tag);
    }

    /**
     * @see TagController#deleteTag(String)
     */
    @Test(expected = RecIllegalArgumentException.class)
    public void deleteTagTestWithNull() {
        tagController.deleteTag(null);
    }

    /**
     * @see TagController#deleteTag(String)
     */
    @Test(expected = RecIllegalArgumentException.class)
    public void deleteTagTestWithEmptyString() {
        tagController.deleteTag("");
    }

}
