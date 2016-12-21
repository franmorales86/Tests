/**
 * Project: RecCore
 * Created by: raulanatol at 20/09/13 15:57
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.services;

import com.reclabs.barneylib.BarneyServer;
import com.reclabs.barneylib.LinkType;
import com.reclabs.barneylib.types.BarneyData;
import com.reclabs.barneylib.types.BarneyUrlData;
import com.reclabs.recomendar.common.exceptions.RecIllegalArgumentException;
import com.reclabs.recomendar.core.misc.BarneyServerHelper;
import com.reclabs.recomendar.model.repositories.BarneyRepository;
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
public class BarneyServiceImplTest {

    @Mock
    private BarneyRepository barneyRepository;

    @Mock
    private BarneyServerHelper barneyServerHelper;


    @InjectMocks
    private BarneyServiceImpl barneyServiceImpl = new BarneyServiceImpl();

    @Test(expected = RecIllegalArgumentException.class)
    public void createShortURLWithNullURL() throws Exception {
        barneyServiceImpl.createShortUrl(null, "webName", LinkType.SIMPLE, "1");
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void createShortURLWithNullWebName() throws Exception {
        barneyServiceImpl.createShortUrl("http://www.test.com", null, LinkType.SIMPLE, "1");
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void createShortURLWithNullLinkTypes() throws Exception {
        barneyServiceImpl.createShortUrl("http://www.test.com", "webName", null, "1");
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void createShortURLWithNullUserId() throws Exception {
        barneyServiceImpl.createShortUrl("http://www.test.com", "webName", LinkType.SIMPLE, null);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void createShortURLWithEmptyUrl() throws Exception {
        barneyServiceImpl.createShortUrl("", "webName", LinkType.SIMPLE, "1");
    }

    @Test
    public void createShortURLWithValidParameters() throws Exception {
        String expectedURL = "http://www.test.com";
        String url = "http://www.test.com";
        String webName = "webName";
        LinkType linkTypes = LinkType.SIMPLE;
        String userId = "1";
        String shortCode = "AAAAAA";
        String urlBase = "http://rcmd.me/";
        String dbName = "";
        String data1 = null;
        BarneyServer server = new BarneyServer(0L, urlBase, dbName);
        BarneyUrlData barneyUrlData = new BarneyUrlData();
        barneyUrlData.setShortCode(shortCode);
        barneyUrlData.setLinkType(linkTypes);
        barneyUrlData.setShortUrl(url);

        BarneyData barneyData = new BarneyData();
        barneyData.setBarneyServer(server);
        barneyData.setUrlData(barneyUrlData);

        Mockito.when(barneyServerHelper.generateShortURL(Mockito.anyString(), Mockito.eq(url), Mockito.eq(webName), Mockito.eq(linkTypes), Mockito.eq(userId), Mockito.eq(data1))).thenReturn(barneyData);
        String currentURL = barneyServiceImpl.createShortUrl(url, webName, linkTypes, userId);

        assertThat(currentURL, is(expectedURL));
    }


    @Test(expected = RecIllegalArgumentException.class)
    public void createItemShortURLWithNullUrlBase() throws Exception {
        String userId = "a1b2c3d4";
        String itemId = "1a2b3c4d";
        barneyServiceImpl.createItemShortURL(null, userId, itemId);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void createItemShortURLWithEmptyUrlBase() throws Exception {
        String userId = "a1b2c3d4";
        String itemId = "1a2b3c4d";
        barneyServiceImpl.createItemShortURL("", userId, itemId);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void createItemShortURLWithNullUserId() throws Exception {
        String urlBase = "http://rcmd.me/";
        String itemId = "1a2b3c4d";
        barneyServiceImpl.createItemShortURL(urlBase, null, itemId);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void createItemShortURLWithEmptyUserId() throws Exception {
        String urlBase = "http://rcmd.me/";
        String itemId = "1a2b3c4d";
        barneyServiceImpl.createItemShortURL(urlBase, "", itemId);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void createItemShortURLWithNullItemId() throws Exception {
        String urlBase = "http://rcmd.me/";
        String userId = "a1b2c3d4";
        barneyServiceImpl.createItemShortURL(urlBase, userId, null);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void createItemShortURLWithEmptyItemId() throws Exception {
        String urlBase = "http://rcmd.me/";
        String userId = "a1b2c3d4";
        barneyServiceImpl.createItemShortURL(urlBase, userId, "");
    }

    @Test
    public void createItemShortURLWithValidParameters() throws Exception {
        String expectedURL = "http://www.test.com";
        String url = "http://www.test.com";
        String webName = "-";
        LinkType linkTypes = LinkType.REC_ITEM;
        String shortCode = "AAAAAA";
        String urlBase = "http://rcmd.me/";
        String dbName = "";
        String userId = "123fdadf22";
        String itemId = "asdi12";
        BarneyServer server = new BarneyServer(0L, urlBase, dbName);
        BarneyUrlData barneyUrlData = new BarneyUrlData();
        barneyUrlData.setShortCode(shortCode);
        barneyUrlData.setLinkType(linkTypes);
        barneyUrlData.setShortUrl(url);

        BarneyData barneyData = new BarneyData();
        barneyData.setBarneyServer(server);
        barneyData.setUrlData(barneyUrlData);

        Mockito.when(barneyServerHelper.generateShortURL(Mockito.anyString(), Mockito.eq(url), Mockito.eq(webName), Mockito.eq(linkTypes), Mockito.eq(userId), Mockito.eq(itemId))).thenReturn(barneyData);
        String currentURL = barneyServiceImpl.createItemShortURL(url, userId, itemId);

        assertThat(currentURL, is(expectedURL));
    }
}
