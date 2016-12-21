/**
 * Project: RecCore
 * Created by: raulanatol at 03/01/2013 18:07:13
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.repository.security;

import com.reclabs.recomendar.common.helpers.types.CollectionHelper;
import com.reclabs.recomendar.core.repository.generic.RepositoryTest;
import com.reclabs.recomendar.model.documents.security.OAuthAccessToken;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * @author raulanatol
 */
public class OAuthAccessTokenRepositoryImplTest extends RepositoryTest {

    private OAuthAccessTokenRepositoryImpl classUnderTest;
    private MongoTemplate template;

    /**
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        this.template = new MongoTemplate(mongo, DB_NAME);
        this.classUnderTest = new OAuthAccessTokenRepositoryImpl(this.template);
    }

    /**
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
        this.template.dropCollection(OAuthAccessToken.class);
    }

    /**
     * @see OAuthAccessTokenRepositoryImpl#deleteByTokenId(String)
     */
    @Test
    public void deleteByTokenIdTest() {
        String token2Remove = "TokenId2";

        OAuthAccessToken token = new OAuthAccessToken();
        token.setAuthenticationId("id1");
        token.setTokenId("TokenId1");
        this.template.insert(token);

        token = new OAuthAccessToken();
        token.setAuthenticationId("id2");
        token.setTokenId(token2Remove);
        this.template.insert(token);

        assertThat(this.classUnderTest.findAll().size(), is(2));
        this.classUnderTest.deleteByTokenId(token2Remove);
        List<OAuthAccessToken> tokens = this.classUnderTest.findAll();
        assertThat(tokens.size(), is(1));
        assertThat(tokens.get(0).getTokenId(), is("TokenId1"));
        assertThat(tokens.get(0).getAuthenticationId(), is("id1"));
    }

    /**
     * @see OAuthAccessTokenRepositoryImpl#deleteByRefreshTokenId(String)
     */
    @Test
    public void deleteByRefreshTokenIdTest() {
        String token2Remove = "TokenId2";

        OAuthAccessToken token = new OAuthAccessToken();
        token.setAuthenticationId("id1");
        token.setRefreshToken("TokenId1");
        this.template.insert(token);

        token = new OAuthAccessToken();
        token.setAuthenticationId("id2");
        token.setRefreshToken(token2Remove);
        this.template.insert(token);

        assertThat(this.classUnderTest.findAll().size(), is(2));
        this.classUnderTest.deleteByRefreshTokenId(token2Remove);
        List<OAuthAccessToken> tokens = this.classUnderTest.findAll();
        assertThat(tokens.size(), is(1));
        assertThat(tokens.get(0).getRefreshToken(), is("TokenId1"));
        assertThat(tokens.get(0).getAuthenticationId(), is("id1"));
    }

    /**
     * @see OAuthAccessTokenRepositoryImpl#findByAuthenticationId(String)
     */
    @Test
    public void findByAuthenticationIdTest() {
        String authentication2Find = "authenticationId";

        OAuthAccessToken token = new OAuthAccessToken();
        token.setAuthenticationId("id1");
        token.setRefreshToken("TokenId1");
        this.template.insert(token);

        token = new OAuthAccessToken();
        token.setAuthenticationId(authentication2Find);
        token.setRefreshToken("TokenId2");
        this.template.insert(token);

        assertThat(this.classUnderTest.findAll().size(), is(2));
        OAuthAccessToken itemFound = this.classUnderTest.findByAuthenticationId(authentication2Find);
        assertThat(itemFound.getAuthenticationId(), is(authentication2Find));
        assertThat(itemFound.getRefreshToken(), is("TokenId2"));
    }

    /**
     * @see OAuthAccessTokenRepositoryImpl#findByUsername(String)
     */
    @Test
    public void findByUsernameNullNameTest() {
        List<OAuthAccessToken> result = this.classUnderTest.findByUsername("noUser");
        assertTrue(CollectionHelper.isEmpty(result));
        assertNotNull(result);
    }
}
