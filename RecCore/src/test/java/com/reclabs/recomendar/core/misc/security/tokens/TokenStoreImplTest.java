/**
 * Project: RecCore
 * Created by: raulanatol at 03/01/2013 19:10:29
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.misc.security.tokens;

import com.reclabs.recomendar.model.documents.security.OAuthAccessToken;
import com.reclabs.recomendar.model.repositories.security.OAuthAccessTokenRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

/**
 * @author raulanatol
 * @see TokenStoreImpl
 */
@RunWith(MockitoJUnitRunner.class)
public class TokenStoreImplTest {
    @Mock
    private OAuthAccessTokenRepository oAuthAccessTokenRepository;

    @InjectMocks
    private TokenStoreImpl tokenStoreImpl = new TokenStoreImpl();

    /**
     * @see TokenStoreImpl#readAccessToken(String)
     */
    @Test
    public void readAuthenticationNullTokenTest() {
        String token2Find = "asd";
        String tokenCodified = "7815696ecbf1c96e6894b779456d330e";
        Mockito.when(oAuthAccessTokenRepository.findByTokenId(tokenCodified)).thenReturn(null);

        OAuth2Authentication token = tokenStoreImpl.readAuthentication(token2Find);
        assertNull(token);
    }

    /**
     * Verificamos que si falla la serialización se borra el token para que se vaya limpiando la base de datos.
     * @see TokenStoreImpl#readAccessToken(String)
     */
    @Test
    public void readAuthenticationFailed2SerializeTest() {
        byte[] authentication = {0};
        String token2Find = "asd";
        String tokenCodified = "7815696ecbf1c96e6894b779456d330e";
        OAuthAccessToken dummyOAuthAccessToken = new OAuthAccessToken();
        dummyOAuthAccessToken.setTokenId(token2Find);
        dummyOAuthAccessToken.setAuthentication(authentication);
        TokenStoreImpl testMock = spy(tokenStoreImpl);
        Mockito.when(oAuthAccessTokenRepository.findByTokenId(tokenCodified)).thenReturn(dummyOAuthAccessToken);

        OAuth2Authentication token = testMock.readAuthentication(token2Find);
        assertNull(token);
        verify(testMock, times(1)).removeAccessToken(token2Find);
        verify(testMock, times(1)).deserializeAuthentication(authentication);
    }
}
