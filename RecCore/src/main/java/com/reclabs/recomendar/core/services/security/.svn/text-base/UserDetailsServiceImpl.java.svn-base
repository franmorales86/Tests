/**
 * Project: RecCore
 * Created by: raulanatol at 24/12/2012 17:46:01
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.services.security;

import com.reclabs.recomendar.common.helpers.ParameterHelper;
import com.reclabs.recomendar.core.misc.security.UserDetailsImpl;
import com.reclabs.recomendar.model.documents.users.User;
import com.reclabs.recomendar.model.repositories.users.UserRepository;
import com.reclabs.recomendar.model.services.security.UserDetailsService;
import com.reclabs.recomendar.model.types.SecurityRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author raulanatol
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService, org.springframework.security.core.userdetails.UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        ParameterHelper.assertEmpty(username);
        User user = userRepository.getByUsernameOrEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        Collection<GrantedAuthority> authorities = getAuthorities(user);
        return new UserDetailsImpl(user, authorities);
    }

    /**
     * Gets the authorities of a specified user.
     * @param user The user to find
     * @return The list of authorities or empty if no authorities
     */
    private Collection<GrantedAuthority> getAuthorities(final User user) {
        Collection<GrantedAuthority> result = new ArrayList<>();
        List<SecurityRole> roles = user.getRoles();
        if (roles != null) {
            for (SecurityRole securityRole : roles) {
                result.add(new SimpleGrantedAuthority(securityRole.toString()));
            }
        }
        return result;
    }

    public UserDetails findByUserId(String userId) {
        ParameterHelper.assertEmpty(userId);
        User user = userRepository.findByUserId(userId);
        Collection<GrantedAuthority> authorities = getAuthorities(user);
        return new UserDetailsImpl(user, authorities);
    }
}
