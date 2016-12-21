/**
 * Project: RecCore
 * Created by: raulanatol at 18/02/2013 21:33:27
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.controllers.api.utilities;

import com.reclabs.barneylib.LinkType;
import com.reclabs.recomendar.common.exceptions.RecIllegalArgumentException;
import com.reclabs.recomendar.core.services.UserServiceImpl;
import com.reclabs.recomendar.model.documents.users.User;
import com.reclabs.recomendar.model.objects.utils.ShortURLDTO;
import com.reclabs.recomendar.model.services.BarneyService;
import com.reclabs.recomendar.objects.ResponseDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author raulanatol
 */
@RunWith(MockitoJUnitRunner.class)
public class UtilitiesControllerTest {
    @Mock
    private UserServiceImpl userService;

    @Mock
    private BarneyService barneyService;

    @InjectMocks
    private UtilitiesController utilitiesController = new UtilitiesController();

    /**
     * @see UtilitiesController#userExist(String)
     */
    @Test
    public void userExistSimpleTest() {
        Mockito.when(userService.findByUsername("unknowUser")).thenReturn(null);
        Mockito.when(userService.findByUsername("knowUser")).thenReturn(new User());

        assertThat(utilitiesController.userExist("unknowUser"), is(ResponseDTO.FALSE));
        assertThat(utilitiesController.userExist("knowUser"), is(ResponseDTO.TRUE));
    }

    /**
     * @see UtilitiesController#userExist(String)
     */
    @Test
    public void emailExistTest() {
        Mockito.when(userService.findByEmail("unknowMail")).thenReturn(null);
        Mockito.when(userService.findByEmail("knowMail")).thenReturn(new User());

        assertThat(utilitiesController.emailExist("unknowMail"), is(ResponseDTO.FALSE));
        assertThat(utilitiesController.emailExist("knowMail"), is(ResponseDTO.TRUE));
    }

    /**
     * @see UtilitiesController#createShortUrl(com.reclabs.recomendar.model.objects.utils.ShortURLDTO)
     */
    @Test(expected = RecIllegalArgumentException.class)
    public void createShortUrlNullValuesTest() {
        ShortURLDTO data = new ShortURLDTO();
        data.setUrl(null);
        data.setWebName("test");
        data.setLinkType("test");
        utilitiesController.createShortUrl(data);
    }

    /**
     * @see UtilitiesController#createShortUrl(com.reclabs.recomendar.model.objects.utils.ShortURLDTO)
     */
    // @Test
    public void createShortUrlBarneyUseTest() {
        String testUrl = "http://recomendar.com/urlTest";
        String dummyUrl = "http://rcmd.me/1123da";

        Mockito.when(barneyService.createShortUrl(testUrl, "dummy", LinkType.SIMPLE, "0")).thenReturn(dummyUrl);

        // TODO emular el SecurityHelper
        ShortURLDTO data = new ShortURLDTO();
        data.setUrl(testUrl);
        data.setWebName("test");
        data.setLinkType("Simple");
        assertThat(utilitiesController.createShortUrl(data), is(dummyUrl));
    }
}
