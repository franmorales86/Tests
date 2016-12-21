/**
 * Project: RecCore
 * Created by: raulanatol at 07/03/2013 13:20:13
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.misc;

import com.reclabs.barneylib.BarneyServer;
import com.reclabs.barneylib.exceptions.ShortCodeDuplicatedException;
import com.reclabs.recomendar.common.exceptions.generic.ErrorException;

import java.util.Arrays;

/**
 * @author raulanatol
 * @see BarneyServerHelper
 */
// @RunWith(MockitoJUnitRunner.class)
public class BarneyServerHelperTest {

    // @Mock
    private BarneyServerHelper barneyServerHelper;

    // @InjectMocks
    // private BarneyServiceImpl barneyService;

    /**
     * @throws ErrorException
     * @throws ShortCodeDuplicatedException
     * @see BarneyServerHelper#generateOnAnyServer(String, String)
     */
    // @Test
    public void generateOnAnyServerTest() throws ShortCodeDuplicatedException, ErrorException {
        BarneyServer barneyServer = new BarneyServer(1, "http://rcmd.me/", "dbNameTest");
        // String shortCode = "AEFdSd";

        barneyServerHelper.setBarneys(Arrays.asList(barneyServer));

        // String validUrl =
        // "http://ad.zanox.com/ppc/?23293909C39929496&amp;ULP=[[kappa-highleet-altas-gris-10k11a004-103.html]]";
        // String shortUrl = "http://rcmd.me/" + shortCode;

        // BarneyData expected = new BarneyData();
        // expected.setBarneyServer(barneyServer);
        // expected.setShortCode(shortCode);
        // expected.setShortUrl(shortUrl);
        // expected.setValidUrl(validUrl);

        // Mockito.when(barneyService.exist(barneyServer, shortCode)).thenReturn(false);
        // Mockito.when(barneyService.exist(any(BarneyServer.class), shortCode)).thenReturn(false);

        // BarneyData actual = barneyServerHelper.generateOnAnyServer(shortCode, validUrl);
        // assertThat(actual, is(expected));
    }
}
