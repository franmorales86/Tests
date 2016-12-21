/**
 * Project: RecCore
 * Created by: raulanatol at 08/03/2013 12:59:13
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.helpers;

import com.reclabs.recomendar.model.documents.users.SocialUser;
import com.reclabs.recomendar.model.documents.users.User;
import com.reclabs.recomendar.model.types.SocialUserProviderId;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * @author raulanatol
 * @see UserHelper
 */
public class UserHelperTest {

    @Test
    public void toVisibleNameTest() {
        String[][] valuesAndResults = {
                {"name", "lastName1", "lastName2", "name lastName1 lastName2"},
                {"name", "lastName1", null, "name lastName1"},
                {"John", null, "Doe", "John Doe"},
                {null, null, "Doe", "Doe"},
                {"name", null, null, "name"},
                {null, null, null, ""},
        };
        for (String[] strings : valuesAndResults) {
            assertThat(UserHelper.toVisibleName(getUserByValues(strings[0], strings[1], strings[2])), is(strings[3]));
        }
    }

    @Test
    public void toVisibleNameWithDuplicatedSurnames() throws Exception {
        User user = new User();
        user.setName("Pepe Juan Izquierdo Delgado");
        user.setLastname1("");
        user.setLastname2("Izquierdo Delgado");
        assertThat(UserHelper.toVisibleName(user), is("Pepe Juan Izquierdo Delgado"));
    }

    @Test
    public void toVisibleNameWithEqualsSurnames() throws Exception {
        User user = new User();
        user.setName("Juan");
        user.setLastname1("Luis");
        user.setLastname2("Luis");
        assertThat(UserHelper.toVisibleName(user), is("Juan Luis Luis"));
    }

    @Test
    public void toVisibleNameWithEqualsNameAndSurnames() throws Exception {
        User user = new User();
        user.setName("Rafael Izquierdo");
        user.setLastname1("Izquierdo");
        assertThat(UserHelper.toVisibleName(user), is("Rafael Izquierdo"));
    }

    @Test
    public void toVisibleNameWithDuplicatedValues() throws Exception {
        User user = new User();
        user.setName("Pepe Juan Izquierdo Delgado");
        user.setLastname1("Izquierdo");
        user.setLastname2("Delgado");
        assertThat(UserHelper.toVisibleName(user), is("Pepe Juan Izquierdo Delgado"));
    }

    /**
     * @see UserHelper#userHasProviders(User, java.util.List)
     */
    @Test
    public void userHasProvidersSimpleData() {
        User user = new User();
        List<SocialUser> socialUsers = new ArrayList<>();
        socialUsers.add(getSocialUserByProviderId("Twitter"));
        socialUsers.add(getSocialUserByProviderId("Facebook"));
        user.setSocialUsers(socialUsers);
        assertTrue(UserHelper.userHasProviders(user, Arrays.asList("Twitter", "Facebook")));
        assertTrue(UserHelper.userHasProviders(user, Arrays.asList("Facebook")));
        assertTrue(UserHelper.userHasProviders(user, Arrays.asList("Twitter")));
        assertFalse(UserHelper.userHasProviders(user, Arrays.asList("Twitter", "Linkedin")));
    }

    private SocialUser getSocialUserByProviderId(String providerId) {
        SocialUser result = new SocialUser();
        result.setProviderId(providerId);
        return result;
    }

    private User getUserByValues(String name, String lastName1, String lastName2) {
        User result = new User();
        result.setName(name);
        result.setLastname1(lastName1);
        result.setLastname2(lastName2);
        return result;
    }

    /**
     * With normal data
     * @see UserHelper#userHasProviderId(User, com.reclabs.recomendar.model.types.SocialUserProviderId)
     */
    @Test
    public void userHasProviderIdWithNormalData() {
        SocialUser twitterSocial = new SocialUser();
        twitterSocial.setProviderId(SocialUserProviderId.TWITTER.getProviderId());
        List<SocialUser> socialUsers = Arrays.asList(twitterSocial);
        User user = new User();
        user.setSocialUsers(socialUsers);
        assertTrue(UserHelper.userHasProviderId(user, SocialUserProviderId.TWITTER));
    }

}
