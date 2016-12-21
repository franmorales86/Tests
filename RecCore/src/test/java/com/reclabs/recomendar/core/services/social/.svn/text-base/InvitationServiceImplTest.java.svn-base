/**
 * Project: RecCore
 * Created by: raulanatol at 07/01/14 15:37
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.services.social;

import com.reclabs.recomendar.common.helpers.types.DateHelper;
import com.reclabs.recomendar.common.helpers.types.NowDateHelper;
import com.reclabs.recomendar.model.documents.social.Invitation;
import com.reclabs.recomendar.model.repositories.social.InvitationRepository;
import com.reclabs.recomendar.model.services.social.InvitationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import static com.mongodb.util.MyAsserts.assertTrue;

/**
 * @author raulanatol
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(NowDateHelper.class)
@PowerMockIgnore({"javax.management.*", "javax.xml.parsers.*", "com.sun.org.apache.xerces.internal.jaxp.*", "ch.qos.logback.*", "org.slf4j.*"})
public class InvitationServiceImplTest {

    @Mock
    private InvitationRepository invitationRepository;

    @InjectMocks
    private InvitationService invitationService = new InvitationServiceImpl();

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void existInvitationWithSimpleInvitation() throws Exception {
        Calendar now = DateHelper.getCalendar(2010, 10, 10, 10, 12, 15);
        Date invitationCreation = DateHelper.getFixedDate(2010, 10, 12, 12, 43, 54);
        String email = "EMAIL";
        String code = "07a9d7b4a9a23915a61bc89bb0357bf47b348cf4174eb965bb1df8fbfa18b0b5";

        Invitation invitation = new Invitation();
        invitation.setCreationDate(invitationCreation);
        invitation.setCode("CODE");

        PowerMockito.mockStatic(NowDateHelper.class);
        Mockito.when(NowDateHelper.getCurrentCalendar()).thenReturn(now);
        Mockito.when(invitationRepository.findByEmail(email)).thenReturn(Arrays.asList(invitation));

        assertTrue(invitationService.existInvitation(email, code));
    }

    @Test
    public void existInvitationWithMultipleInvitations() throws Exception {
        Calendar now = DateHelper.getCalendar(2010, 10, 10, 10, 12, 15);
        Date invitationCreation = DateHelper.getFixedDate(2010, 10, 12, 12, 43, 54);
        String email = "EMAIL";
        String code = "07a9d7b4a9a23915a61bc89bb0357bf47b348cf4174eb965bb1df8fbfa18b0b5";

        Invitation invitation = new Invitation();
        invitation.setCreationDate(invitationCreation);
        invitation.setCode("CODE2");

        Invitation invitation2 = new Invitation();
        invitation2.setCreationDate(invitationCreation);
        invitation2.setCode("CODE");

        PowerMockito.mockStatic(NowDateHelper.class);
        Mockito.when(NowDateHelper.getCurrentCalendar()).thenReturn(now);
        Mockito.when(invitationRepository.findByEmail(email)).thenReturn(Arrays.asList(invitation, invitation2));

        assertTrue(invitationService.existInvitation(email, code));

    }
}
