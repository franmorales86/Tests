/**
 * Project: RecModel
 * Created by: raulanatol at 11/03/2013 13:05:08
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.model.types;

import com.reclabs.recomendar.common.helpers.types.CollectionHelper;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * @author raulanatol
 * @see SocialUserProviderId
 */
public class SocialUserProviderIdTest {

    /**
     * @see SocialUserProviderId#byProviderId(String)
     */
    @Test
    public void byProviderIdWithNullOrEmptyParameter() {
        assertNull(SocialUserProviderId.byProviderId(null));
        assertNull(SocialUserProviderId.byProviderId(""));
        assertNull(SocialUserProviderId.byProviderId(" "));
    }

    /**
     * @see SocialUserProviderId#byProviderId(String)
     */
    @Test
    public void byProviderIdWithTwitterParameter() {
        assertThat(SocialUserProviderId.byProviderId("twitter"), is(SocialUserProviderId.TWITTER));
        assertThat(SocialUserProviderId.byProviderId("Twitter"), is(SocialUserProviderId.TWITTER));
        assertNull(SocialUserProviderId.byProviderId("Twitter "));
        assertNull(SocialUserProviderId.byProviderId(" Twitter"));
        assertNull(SocialUserProviderId.byProviderId("Twit ter"));
    }

    /**
     * @see SocialUserProviderId#byStrings(java.util.List)
     */
    @Test
    public void byStringsNullParameter() {
        String[] values = {null, " ", "null", "notTwitter"};
        List<SocialUserProviderId> actual;
        for (String value : values) {
            actual = SocialUserProviderId.byStrings(Arrays.asList(value));
            assertTrue(CollectionHelper.isEmpty(actual));
            assertNotNull(actual);
        }
    }

    /**
     * @see SocialUserProviderId#byStrings(java.util.List)
     */
    @Test
    public void byStringsWithValidParameter() {
        List<SocialUserProviderId> actual = SocialUserProviderId.byStrings(Arrays.asList("Twitter", "notTwitter"));
        assertTrue(!CollectionHelper.isEmpty(actual));
        assertNotNull(actual);
        assertNotNull(actual.size() == 1);

        actual = SocialUserProviderId.byStrings(Arrays.asList("Twitter", "Facebook", "facebook"));
        assertTrue(!CollectionHelper.isEmpty(actual));
        assertNotNull(actual);
        assertNotNull(actual.size() == 3);
    }

    /**
     * @see SocialUserProviderId#is(String)
     */
    @Test
    public void isWithNullParameter() {
        assertFalse(SocialUserProviderId.FACEBOOK.is(null));
        assertFalse(SocialUserProviderId.FACEBOOK.is(""));
        assertTrue(SocialUserProviderId.FACEBOOK.is("Facebook"));
    }
}
