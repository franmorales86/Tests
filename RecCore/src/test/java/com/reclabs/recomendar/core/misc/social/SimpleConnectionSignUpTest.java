/**
 * Project: RecCore
 * Created by: raulanatol at 04/12/13 09:04
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.misc.social;

import com.reclabs.recomendar.model.documents.users.SocialUser;
import com.reclabs.recomendar.model.documents.users.User;
import com.reclabs.recomendar.model.services.UserService;
import org.apache.commons.lang.NotImplementedException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.facebook.api.UserOperations;

import java.util.Locale;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author raulanatol
 */
@RunWith(MockitoJUnitRunner.class)
public class SimpleConnectionSignUpTest {

    @Mock
    private UserService userService;

    @InjectMocks
    protected SimpleConnectionSignUp simpleConnectionSignUp = new SimpleConnectionSignUp();

    class FacebookConnectionTest implements Connection<Facebook> {
        public Facebook facebook;

        public FacebookConnectionTest(Facebook facebook) {
            this.facebook = facebook;
        }

        @Override
        public ConnectionKey getKey() {
            throw new NotImplementedException();
        }

        @Override
        public String getDisplayName() {
            throw new NotImplementedException();
        }

        @Override
        public String getProfileUrl() {
            throw new NotImplementedException();
        }

        @Override
        public String getImageUrl() {
            throw new NotImplementedException();
        }

        @Override
        public void sync() {
            throw new NotImplementedException();
        }

        @Override
        public boolean test() {
            throw new NotImplementedException();
        }

        @Override
        public boolean hasExpired() {
            throw new NotImplementedException();
        }

        @Override
        public void refresh() {
            throw new NotImplementedException();
        }

        @Override
        public UserProfile fetchUserProfile() {
            throw new NotImplementedException();
        }

        @Override
        public void updateStatus(String message) {
            throw new NotImplementedException();
        }

        @Override
        public Facebook getApi() {
            return facebook;
        }

        @Override
        public ConnectionData createData() {
            throw new NotImplementedException();
        }
    }

    @Test
    public void updateUserDataFromFacebookConnectionWithSimpleData() throws Exception {
        User user = new User();
        SocialUser socialUser = new SocialUser();
        String id = "ID";
        String username = "USERNAME";
        String name = "NAME";
        String firstName = "FIRST_NAME";
        String lastName = "LAST_NAME";
        String gender = "GENDER";
        FacebookProfile userProfile = new FacebookProfile(id, username, name, firstName, lastName, gender, Locale.ENGLISH);

        Facebook mockFacebook = Mockito.mock(Facebook.class);
        UserOperations mockUserOperations = Mockito.mock(UserOperations.class);
        Connection<Facebook> facebookConnection = new FacebookConnectionTest(mockFacebook);

        Mockito.when(mockFacebook.userOperations()).thenReturn(mockUserOperations);
        Mockito.when(mockUserOperations.getUserProfile()).thenReturn(userProfile);
        Mockito.when(userService.getValidUsername(username)).thenReturn(username);

        simpleConnectionSignUp.updateUserDataFromFacebookConnection(user, socialUser, facebookConnection);

        assertThat(user.getName(), is(name));
        assertThat(user.getUsername(), is(username));
    }
}
