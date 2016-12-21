/**
 * Project: RecCore
 * Created by: raulanatol at 30/12/2012 19:23:06
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.misc.security;

import com.reclabs.recomendar.model.documents.security.OAuthClientDetails;
import com.reclabs.recomendar.model.repositories.security.OAuthClientDetailsRepository;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.*;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Service to obtain the clientDetails
 * @author raulanatol
 */
public class ClientDetailsServiceImpl implements ClientDetailsService, ClientRegistrationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientDetailsServiceImpl.class);

    /**
     * El encoder del password
     */
    private final PasswordEncoder passwordEncoder = NoOpPasswordEncoder.getInstance();

    /**
     * Mapeador de los datos del ClientDetails
     */
    private final ObjectMapper mapper = new ObjectMapper();

    /**
     * Repositorio del document {@link OAuthClientDetails}
     */
    @Autowired
    private OAuthClientDetailsRepository oAuthClientDetailsRepository;

    @Override
    public void addClientDetails(ClientDetails clientDetails) throws ClientAlreadyExistsException {
        try {
            oAuthClientDetailsRepository.save(fromClientDetails(clientDetails));
        } catch (DuplicateKeyException e) {
            throw new ClientAlreadyExistsException("Client already exists: " + clientDetails.getClientId(), e);
        }
    }

    /**
     * @param clientDetails
     * @return
     */
    private OAuthClientDetails fromClientDetails(ClientDetails clientDetails) {
        OAuthClientDetails result = new OAuthClientDetails();
        String additionalInformation = null;
        try {
            additionalInformation = mapper.writeValueAsString(clientDetails.getAdditionalInformation());
        } catch (Exception e) {
            LOGGER.warn("Could not serialize additional information: {}", e);
        }
        result.setClientSecret(clientDetails.getClientSecret() != null ? passwordEncoder.encode(clientDetails.getClientSecret()) : null);
        result.setResourceIds(clientDetails.getResourceIds() != null ? StringUtils.collectionToCommaDelimitedString(clientDetails.getResourceIds()) : null);
        result.setScope(clientDetails.getScope() != null ? StringUtils.collectionToCommaDelimitedString(clientDetails.getScope()) : null);
        result.setAuthorizedGrantTypes(clientDetails.getAuthorizedGrantTypes() != null ? StringUtils.collectionToCommaDelimitedString(clientDetails.getAuthorizedGrantTypes()) : null);
        result.setWebServerRedirectUri(clientDetails.getRegisteredRedirectUri() != null ? StringUtils.collectionToCommaDelimitedString(clientDetails.getRegisteredRedirectUri()) : null);
        result.setAuthorities(clientDetails.getAuthorities() != null ? StringUtils.collectionToCommaDelimitedString(clientDetails.getAuthorities()) : null);
        result.setAccessTokenValidity(clientDetails.getAccessTokenValiditySeconds());
        result.setRefreshTokenValidity(clientDetails.getRefreshTokenValiditySeconds());
        result.setAdditionalInformation(additionalInformation);
        result.setClientId(clientDetails.getClientId());
        return result;
    }

    @Override
    public void updateClientDetails(ClientDetails clientDetails) throws NoSuchClientException {
        oAuthClientDetailsRepository.save(fromClientDetails(clientDetails));
    }

    @Override
    public void updateClientSecret(String clientId, String secret) throws NoSuchClientException {
        oAuthClientDetailsRepository.updateClientSecret(clientId, passwordEncoder.encode(secret));
        // TODO verificar si se ha realizado o no una actualización.
        // throw new NoSuchClientException("No client found with id = " + clientId);
    }

    @Override
    public void removeClientDetails(String clientId) throws NoSuchClientException {
        // TODO verificar si se ha realizado o no el borrado.
        oAuthClientDetailsRepository.deleteByClientId(clientId);
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.spring.security.oauth2.provider.ClientRegistrationService#listClientDetails()
     */
    @Override
    public List<ClientDetails> listClientDetails() {
        List<OAuthClientDetails> details = oAuthClientDetailsRepository.findAll();
        return fromOAuthClientDetails(details);
    }

    /**
     * @param details
     * @return
     */
    private List<ClientDetails> fromOAuthClientDetails(List<OAuthClientDetails> details) {
        List<ClientDetails> result = new ArrayList<>();
        for (OAuthClientDetails oAuthClientDetails : details) {
            result.add(fromOAuthClientDetail(oAuthClientDetails));
        }
        return result;
    }

    /**
     * @param oAuthClientDetails
     * @return
     */
    @SuppressWarnings("unchecked")
    private ClientDetails fromOAuthClientDetail(OAuthClientDetails oAuthClientDetails) {
        String clientId = oAuthClientDetails.getClientId();
        String resourceId = oAuthClientDetails.getResourceIds();
        String scopes = oAuthClientDetails.getScope();
        String grantTypes = oAuthClientDetails.getAuthorizedGrantTypes();
        String authorities = oAuthClientDetails.getAuthorities();
        String redirectUris = oAuthClientDetails.getWebServerRedirectUri();
        BaseClientDetails result = new BaseClientDetails(clientId, resourceId, scopes, grantTypes, authorities, redirectUris);
        result.setClientSecret(oAuthClientDetails.getClientSecret());

        if (oAuthClientDetails.getAccessTokenValidity() != null) {
            result.setAccessTokenValiditySeconds(oAuthClientDetails.getAccessTokenValidity());
        }

        if (oAuthClientDetails.getRefreshTokenValidity() != null) {
            result.setRefreshTokenValiditySeconds(oAuthClientDetails.getRefreshTokenValidity());
        }

        String jsonAdditionalInformation = oAuthClientDetails.getAdditionalInformation();
        if (jsonAdditionalInformation != null) {
            try {
                Map<String, Object> additionalInformation = mapper.readValue(jsonAdditionalInformation, Map.class);
                result.setAdditionalInformation(additionalInformation);
            } catch (Exception e) {
                LOGGER.warn("Could not decode JSON for additional information: " + result, e);
            }
        }
        return result;
    }

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws OAuth2Exception {
        OAuthClientDetails clientDetails = oAuthClientDetailsRepository.findByClientId(clientId);
        // TODO en caso de que no se encuentre... throw new NoSuchClientException("No client with requested id: " +
        // clientId);
        return fromOAuthClientDetail(clientDetails);
    }


}
