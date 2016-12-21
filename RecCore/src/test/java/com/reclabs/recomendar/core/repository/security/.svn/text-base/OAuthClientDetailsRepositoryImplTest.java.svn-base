/**
 * Project: RecCore
 * Created by: raulanatol at 03/01/2013 20:14:50
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.repository.security;

import com.reclabs.recomendar.core.repository.generic.RepositoryTest;
import com.reclabs.recomendar.model.documents.security.OAuthClientDetails;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author raulanatol
 */
public class OAuthClientDetailsRepositoryImplTest extends RepositoryTest {

    private OAuthClientDetailsRepositoryImpl classUnderTest;
    private MongoTemplate template;

    /**
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        this.template = new MongoTemplate(mongo, DB_NAME);
        this.classUnderTest = new OAuthClientDetailsRepositoryImpl(this.template);
    }

    /**
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
        this.template.dropCollection(OAuthClientDetails.class);
    }

    /**
     * Verificamos que se realiza una modificación.
     * @see OAuthClientDetailsRepositoryImpl#save(OAuthClientDetails)
     */
    @Test
    public void saveDuplicateKeyTestWithoutException() {
        String clientValue = "clientID";

        OAuthClientDetails client1 = new OAuthClientDetails();
        client1.setClientId(clientValue);
        client1.setClientSecret("Secret1");

        OAuthClientDetails client2 = new OAuthClientDetails();
        client2.setClientId(clientValue);
        client2.setClientSecret("Secret2");

        this.classUnderTest.save(client1);
        this.classUnderTest.save(client2);

        List<OAuthClientDetails> items = this.template.findAll(OAuthClientDetails.class);
        assertThat(items.size(), is(1));
        assertThat(items.get(0).getClientSecret(), is("Secret2"));
        assertThat(items.get(0).getClientId(), is(clientValue));
    }
}
