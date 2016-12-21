/**
 * Project: RecCore
 * Created by: raulanatol at 22/12/2012 19:04:29
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.misc.security;

import com.reclabs.recomendar.common.exceptions.generic.ApiException;
import com.reclabs.recomendar.common.types.CodeErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.security.oauth2.provider.approval.TokenServicesUserApprovalHandler;

import java.util.Collection;
import java.util.HashSet;

/**
 * @author raulanatol
 */
@SuppressWarnings("unchecked")
public class UserApprovalHandler extends TokenServicesUserApprovalHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserApprovalHandler.class);

    /**
     * Listado de clientes que por defecto serán auto aprobados.
     */
    private Collection<String> autoApproveClients = new HashSet<>();

    /**
     *
     */
    private boolean useTokenServices = true;

    /**
     * @param autoApproveClients the autoApproveClients to set
     */
    public void setAutoApproveClients(Collection<String> autoApproveClients) {
        this.autoApproveClients = autoApproveClients;
    }

    /**
     * @param useTokenServices the useTokenServices to set
     */
    public void setUseTokenServices(boolean useTokenServices) {
        this.useTokenServices = useTokenServices;
    }

    @SuppressWarnings("EmptyMethod")
    @Override
    public AuthorizationRequest updateBeforeApproval(AuthorizationRequest authorizationRequest, Authentication userAuthentication) {
        return super.updateBeforeApproval(authorizationRequest, userAuthentication);
    }

    @Override
    public boolean isApproved(AuthorizationRequest authorizationRequest, Authentication userAuthentication) {
        String clientId = authorizationRequest.getAuthorizationParameters().get("client_id");
        if (!SecurityHelper.verifySecurityForSandbox(clientId, (Collection<GrantedAuthority>) userAuthentication.getAuthorities())) {
            LOGGER.warn("[Trying to access to sandbox without permissions][ User principal: {}]", userAuthentication.getPrincipal());
            throw new ApiException(CodeErrorType.PERMISSION_DENIED);
        }

        if (useTokenServices && super.isApproved(authorizationRequest, userAuthentication)) {
            return true;
        }

        if (!userAuthentication.isAuthenticated()) {
            return false;
        }

        String flag = authorizationRequest.getApprovalParameters().get(AuthorizationRequest.USER_OAUTH_APPROVAL);
        boolean approved = flag != null && flag.toLowerCase().equals("true");

        return approved || (authorizationRequest.getResponseTypes().contains("token") && autoApproveClients.contains(authorizationRequest.getClientId()));
    }


}
