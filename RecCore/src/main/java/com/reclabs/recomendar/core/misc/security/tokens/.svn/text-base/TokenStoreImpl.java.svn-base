/**
 * Project: RecCore
 * Created by: raulanatol at 25/12/2012 16:25:37
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.misc.security.tokens;

import com.reclabs.recomendar.common.exceptions.generic.RecRuntimeException;
import com.reclabs.recomendar.common.helpers.types.CollectionHelper;
import com.reclabs.recomendar.common.helpers.types.StringHelper;
import com.reclabs.recomendar.model.documents.security.OAuthAccessToken;
import com.reclabs.recomendar.model.documents.security.OAuthRefreshToken;
import com.reclabs.recomendar.model.repositories.security.OAuthAccessTokenRepository;
import com.reclabs.recomendar.model.repositories.security.OAuthRefreshTokenRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.common.util.SerializationUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.DefaultAuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.TokenStore;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Servicio de almacenamiento de tokens con los repositorios de recomendar.
 * @author raulanatol
 */
public class TokenStoreImpl implements TokenStore {
    private static final Logger LOGGER = LoggerFactory.getLogger(TokenStoreImpl.class);
    /**
     * El repositorio de los tokens de acceso.
     */
    @Autowired
    private OAuthAccessTokenRepository oAuthAccessTokenRepository;
    /**
     * El repositorio de los tokens de refresh
     */
    @Autowired
    private OAuthRefreshTokenRepository oAuthRefreshTokenRepository;
    /**
     * El generador de keys de acceso.
     */
    private final AuthenticationKeyGenerator authenticationKeyGenerator = new DefaultAuthenticationKeyGenerator();

    @Override
    public OAuth2Authentication readAuthentication(final OAuth2AccessToken token) {
        return readAuthentication(token.getValue());
    }

    @Override
    public OAuth2Authentication readAuthentication(final String token) {
        OAuth2Authentication result = null;
        try {
            OAuthAccessToken oAuthAccessToken = oAuthAccessTokenRepository.findByTokenId(extractTokenKey(token));
            if (oAuthAccessToken != null) {
                result = deserializeAuthentication(oAuthAccessToken.getAuthentication());
            } else {
                if (LOGGER.isInfoEnabled()) {
                    LOGGER.info("Failed to find access token for token " + token);
                }
            }
        } catch (IllegalArgumentException e) {
            LOGGER.warn("Failed to deserialize authentication for " + token);
            removeAccessToken(token);
        }
        return result;
    }


    @Override
    public void storeAccessToken(final OAuth2AccessToken token, final OAuth2Authentication authentication) {
        String refreshToken = null;
        if (token.getRefreshToken() != null) {
            refreshToken = token.getRefreshToken().getValue();
        }

        OAuthAccessToken toInsert = new OAuthAccessToken();
        toInsert.setTokenId(extractTokenKey(token.getValue()));
        toInsert.setToken(serializeAccessToken(token));
        toInsert.setAuthenticationId(authenticationKeyGenerator.extractKey(authentication));
        toInsert.setUsername(authentication.isClientOnly() ? null : authentication.getName());
        toInsert.setClientId(authentication.getAuthorizationRequest().getClientId());
        toInsert.setAuthentication(serializeAuthentication(authentication));
        toInsert.setRefreshToken(extractTokenKey(refreshToken));
        oAuthAccessTokenRepository.save(toInsert);
    }

    @Override
    public OAuth2AccessToken readAccessToken(final String tokenValue) {
        OAuth2AccessToken accessToken = null;
        try {
            OAuthAccessToken oAuthAccessToken = oAuthAccessTokenRepository.findByTokenId(extractTokenKey(tokenValue));
            if (oAuthAccessToken != null) {
                accessToken = deserializeAccessToken(oAuthAccessToken.getToken());
            } else {
                if (LOGGER.isInfoEnabled()) {
                    LOGGER.info("Failed to find access token for token " + tokenValue);
                }
            }
        } catch (IllegalArgumentException e) {
            LOGGER.warn("Failed to deserialize access token for " + tokenValue);
            removeAccessToken(tokenValue);
        }
        return accessToken;
    }

    @Override
    public void removeAccessToken(final OAuth2AccessToken token) {
        removeAccessToken(token.getValue());
    }

    /**
     * Delete all accessTokens from the username
     * @param username The username
     */
    public void removeAccessTokenByUsername(String username) {
        LOGGER.info("[Remove access token by username {}", username);
        oAuthAccessTokenRepository.deleteByUsername(username);
    }

    /**
     * @param tokenValue Token to remove
     */
    public void removeAccessToken(final String tokenValue) {
        String tokenId = extractTokenKey(tokenValue);
        oAuthAccessTokenRepository.deleteByTokenId(tokenId);
    }

    @Override
    public void storeRefreshToken(final OAuth2RefreshToken refreshToken, final OAuth2Authentication authentication) {
        OAuthRefreshToken item2Insert = new OAuthRefreshToken();
        item2Insert.setTokenId(extractTokenKey(refreshToken.getValue()));
        item2Insert.setToken(serializeRefreshToken(refreshToken));
        item2Insert.setAuthentication(serializeAuthentication(authentication));
        oAuthRefreshTokenRepository.save(item2Insert);
    }

    @Override
    public OAuth2RefreshToken readRefreshToken(final String tokenValue) {
        OAuth2RefreshToken refreshToken = null;
        try {
            OAuthRefreshToken oAuthRefreshToken = oAuthRefreshTokenRepository.findByTokenId(extractTokenKey(tokenValue));
            if (oAuthRefreshToken != null) {
                refreshToken = deserializeRefreshToken(oAuthRefreshToken.getToken());
            } else {
                if (LOGGER.isInfoEnabled()) {
                    LOGGER.info("Failed to find refresh token for token " + tokenValue);
                }
            }
        } catch (IllegalArgumentException e) {
            LOGGER.warn("Failed to deserialize refresh token for token " + tokenValue);
            removeRefreshToken(tokenValue);
        }
        return refreshToken;
    }

    @Override
    public OAuth2Authentication readAuthenticationForRefreshToken(final OAuth2RefreshToken token) {
        return readAuthenticationForRefreshToken(token.getValue());
    }

    /**
     * @param value Element to read
     * @return El {@link OAuth2Authentication}
     */
    OAuth2Authentication readAuthenticationForRefreshToken(final String value) {
        OAuth2Authentication authentication = null;
        try {
            OAuthRefreshToken oAuthRefreshToken = oAuthRefreshTokenRepository.findByTokenId(extractTokenKey(value));
            if (oAuthRefreshToken != null) {
                authentication = deserializeAuthentication(oAuthRefreshToken.getAuthentication());
            } else {
                if (LOGGER.isInfoEnabled()) {
                    LOGGER.info("Failed to find access token for token {}", value);
                }
            }
        } catch (IllegalArgumentException e) {
            LOGGER.warn("Failed to deserialize access token for {}", value);
            removeRefreshToken(value);
        }

        return authentication;
    }

    @Override
    public void removeRefreshToken(final OAuth2RefreshToken token) {
        oAuthRefreshTokenRepository.deleteByTokenId(token.getValue());
    }

    /**
     * @param token token to remove
     */
    public void removeRefreshToken(final String token) {
        oAuthRefreshTokenRepository.deleteByTokenId(extractTokenKey(token));
    }

    @Override
    public void removeAccessTokenUsingRefreshToken(final OAuth2RefreshToken refreshToken) {
        oAuthAccessTokenRepository.deleteByRefreshTokenId(refreshToken.getValue());
    }

    @Override
    public OAuth2AccessToken getAccessToken(final OAuth2Authentication authentication) {
        OAuth2AccessToken accessToken = null;
        String key = authenticationKeyGenerator.extractKey(authentication);

        try {
            OAuthAccessToken accessTokenDocument = oAuthAccessTokenRepository.findByAuthenticationId(key);
            if (accessTokenDocument != null) {
                accessToken = deserializeAccessToken(accessTokenDocument.getToken());
                if (accessToken != null && !authentication.equals(readAuthentication(accessToken.getValue()))) {
                    removeAccessToken(accessToken);
                    storeAccessToken(accessToken, authentication);
                }
            } else {
                if (LOGGER.isInfoEnabled()) {
                    LOGGER.debug("Failed to find access token for authentication " + authentication);
                }
            }
        } catch (IllegalArgumentException e) {
            LOGGER.error("Could not extract access token for authentication " + authentication);
        }
        return accessToken;
    }

    @Override
    public Collection<OAuth2AccessToken> findTokensByUserName(final String userName) {
        List<OAuth2AccessToken> result = new ArrayList<>();
        List<OAuthAccessToken> listOfTokens = oAuthAccessTokenRepository.findByUsername(userName);
        if (!CollectionHelper.isEmpty(listOfTokens)) {
            result = safeAccessTokenRowMapper(listOfTokens);
        } else {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.warn("Failed to find access token for userName {}");
            }
        }
        return result;
    }

    /**
     * @param listOfTokens List of elements to safe
     * @return List of results
     */
    private List<OAuth2AccessToken> safeAccessTokenRowMapper(final List<OAuthAccessToken> listOfTokens) {
        List<OAuth2AccessToken> result = new ArrayList<>();
        for (OAuthAccessToken oAuthAccessToken : listOfTokens) {
            try {
                result.add(deserializeAccessToken(oAuthAccessToken.getToken()));
            } catch (IllegalArgumentException e) {
                String token = oAuthAccessToken.getTokenId();
                oAuthAccessTokenRepository.deleteByTokenId(token);
            }
        }
        return result;
    }

    @Override
    public Collection<OAuth2AccessToken> findTokensByClientId(final String clientId) {
        List<OAuth2AccessToken> result = new ArrayList<>();
        List<OAuthAccessToken> tokens = oAuthAccessTokenRepository.findByClientId(clientId);
        if (!CollectionHelper.isEmpty(tokens)) {
            result = safeAccessTokenRowMapper(tokens);
        } else {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("Failed to find access token for clientId " + clientId);
            }
        }
        return result;
    }

    /**
     * Renovamos la authentication con los nuevos datos pasados por parámetros.
     * @param tokenValue El valor del token
     * @param newAuhentication La nueva información
     */
    public void renewAuthentication(String tokenValue, Authentication newAuhentication) {
        OAuth2AccessToken accessToken = readAccessToken(tokenValue);
        OAuthAccessToken token2Update = getOAuthAccessTokenByAccessToken(accessToken);

        OAuth2Authentication currentAuthentication = deserializeAuthentication(token2Update.getAuthentication());
        OAuth2Authentication newOauth2 = new OAuth2Authentication(currentAuthentication.getAuthorizationRequest(), newAuhentication);

        token2Update.setAuthenticationId(authenticationKeyGenerator.extractKey(newOauth2));
        token2Update.setUsername(newOauth2.isClientOnly() ? null : newOauth2.getName());
        token2Update.setAuthentication(serializeAuthentication(newOauth2));
        token2Update.setClientId(newOauth2.getAuthorizationRequest().getClientId());

        oAuthAccessTokenRepository.save(token2Update);
    }

    /**
     * @param accessToken accessToken to get oauth
     * @return The transform token
     */
    private OAuthAccessToken getOAuthAccessTokenByAccessToken(OAuth2AccessToken accessToken) {
        OAuthAccessToken token2Update = oAuthAccessTokenRepository.findByTokenId(extractTokenKey(accessToken.getValue()));
        if (token2Update == null) {
            LOGGER.error("Failed token is not found");
            throw new RecRuntimeException("Error invalid token");
        }
        return token2Update;
    }

    /**
     * Dado el valor de un token obtenemos el key del mismo.
     * @param tokenValue Token
     * @return El key del token
     */
    private String extractTokenKey(final String tokenValue) {
        String result = null;
        try {
            if (!StringHelper.isEmpty(tokenValue)) {
                MessageDigest digest = MessageDigest.getInstance("MD5");
                byte[] bytes = digest.digest(tokenValue.getBytes("UTF-8"));
                result = String.format("%032x", new BigInteger(1, bytes));
            }
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("MD5 algorithm not available.  Fatal (should be in the JDK).");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("UTF-8 encoding not available.  Fatal (should be in the JDK).");
        }
        return result;
    }

    /**
     * Serialización de token
     * @param token token
     * @return El token serializado.
     */
    byte[] serializeAccessToken(final OAuth2AccessToken token) {
        return SerializationUtils.serialize(token);
    }

    /**
     * @param token token
     * @return The token serialized
     */
    protected byte[] serializeRefreshToken(final OAuth2RefreshToken token) {
        return SerializationUtils.serialize(token);
    }

    /**
     * @param authentication token
     * @return The token serialized
     */
    byte[] serializeAuthentication(final OAuth2Authentication authentication) {
        return SerializationUtils.serialize(authentication);
    }

    /**
     * @param token token
     * @return The token deSerialized
     */
    OAuth2AccessToken deserializeAccessToken(final byte[] token) {
        return SerializationUtils.deserialize(token);
    }

    /**
     * @param token token
     * @return The token deSerialized
     */
    protected OAuth2RefreshToken deserializeRefreshToken(final byte[] token) {
        return SerializationUtils.deserialize(token);
    }

    /**
     * @param authentication token
     * @return The token deSerialized
     */
    protected OAuth2Authentication deserializeAuthentication(final byte[] authentication) {
        return SerializationUtils.deserialize(authentication);
    }
}
