/**
 * Project: RecCore
 * Created by: raulanatol at 14/11/2012 10:10:23
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.misc.social;

import com.reclabs.recomendar.core.services.security.UserDetailsServiceImpl;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.web.context.request.NativeWebRequest;

/**
 * @author raulanatol
 */
public class SimpleSignInAdapter implements SignInAdapter {

    private final UserDetailsServiceImpl userDetailsService;

    /**
     * @param userDetailsService
     */
    public SimpleSignInAdapter(UserDetailsServiceImpl userDetailsService) {
        super();
        this.userDetailsService = userDetailsService;
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.social.connect.web.SignInAdapter#signIn(java.lang.String,
     * org.springframework.social.connect.Connection, org.springframework.web.context.request.NativeWebRequest)
     */
    @Override
    public String signIn(String userId, Connection<?> connection, NativeWebRequest request) {
        UserDetails userDetails = userDetailsService.findByUserId(userId);
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // tokenBasedRememberMeServices.onLoginSuccess((HttpServletRequest) request.getNativeRequest(),
        // (HttpServletResponse) request.getNativeResponse(), authentication);
        // FIMXE devolvemos a la url de callback que haya solicitado al API.
        //FIXME para el momento en el que se conecta una red social nueva en el perfil del usuario estaría bien hacer un redirect para esa misma págna.
        return null;
    }
}
