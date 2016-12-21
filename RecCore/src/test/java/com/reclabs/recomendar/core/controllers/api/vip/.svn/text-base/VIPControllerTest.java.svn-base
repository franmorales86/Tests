/**
 * Project: RecCore
 * Created by: raulanatol at 07/01/14 12:38
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.controllers.api.vip;

import com.reclabs.recomendar.common.exceptions.RecIllegalArgumentException;
import com.reclabs.recomendar.common.helpers.types.DateHelper;
import com.reclabs.recomendar.common.helpers.types.NowDateHelper;
import com.reclabs.recomendar.model.services.items.ItemService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Calendar;
import java.util.Date;

/**
 * @author raulanatol
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(NowDateHelper.class)
public class VIPControllerTest {

    @Mock
    private ItemService itemService;

    @InjectMocks
    private VIPController vipController = new VIPController();


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void promoteVIPWithSimpleData() throws Exception {
        Calendar now = DateHelper.getCalendar(2010, 1, 1, 15, 25, 28);
        PowerMockito.mockStatic(NowDateHelper.class);
        Mockito.when(NowDateHelper.getCurrentCalendar()).thenReturn(now);

        String itemId = "ITEM_ID";
        Date promoteDate = DateHelper.getFixedDate(2010, 10, 10);
        vipController.promoteVIP(itemId, promoteDate);

        Mockito.verify(itemService, Mockito.times(1)).promoteVIP(itemId, promoteDate);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void promoteVIPWithNullItemId() throws Exception {
        Date promoteDate = DateHelper.getFixedDate(2010, 10, 10);
        vipController.promoteVIP(null, promoteDate);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void promoteVIPWithEmptyItemId() throws Exception {
        Date promoteDate = DateHelper.getFixedDate(2010, 10, 10);
        vipController.promoteVIP("", promoteDate);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void promoteVIPWithNullPromotedDate() throws Exception {
        String itemId = "ITEM_ID";
        vipController.promoteVIP(itemId, null);
    }


    @Test(expected = RecIllegalArgumentException.class)
    public void promoteVIPWithPassPromotedDate() throws Exception {
        Calendar now = DateHelper.getCalendar(2010, 1, 1, 15, 25, 28);
        PowerMockito.mockStatic(NowDateHelper.class);
        Mockito.when(NowDateHelper.getCurrentCalendar()).thenReturn(now);

        String itemId = "ITEM_ID";
        Date promoteDate = DateHelper.getFixedDate(1996, 10, 10);
        vipController.promoteVIP(itemId, promoteDate);
    }

}
