/**
 * Project: RecCore
 * Created by: raulanatol at 24/12/2012 17:56:52
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.misc.security;

import com.reclabs.recomendar.common.exceptions.RecIllegalArgumentException;
import com.reclabs.recomendar.core.exceptions.login.RecUserPendingMailLoginException;
import com.reclabs.recomendar.model.documents.users.User;
import com.reclabs.recomendar.model.types.user.UserState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * The representation of a session user.
 * @author raulanatol
 */
public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = -379723429290966336L;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsImpl.class);
    /**
     * EL link al usuario
     */
    private final User user;
    /**
     * List of permissions of an user
     */
    private final Collection<GrantedAuthority> authorities;

    public UserDetailsImpl(final User user, final Collection<GrantedAuthority> authorities) {
        this.user = user;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        boolean result = user.getState() == UserState.BANNED || user.getState() == UserState.PENDING_MAIL;
        return !result;
    }

    @Override
    public boolean isAccountNonLocked() {
        if (user.getState() == UserState.PENDING_MAIL) {
            LOGGER.debug("[Trying to login with a pending mail user][UserID {}]", user.getId());
            throw new RecUserPendingMailLoginException(user.getId(), user.getEmail());
        }
        return user.getState() != UserState.BANNED;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return (user.getState() != UserState.EXPIRED);
    }

    @Override
    public boolean isEnabled() {
        boolean result = false;
        if (user.getState() != null) {
            switch (user.getState()) {
                case ACTIVE:
                    result = true;
                    break;
                case PENDING_MAIL:
                case HALF_DATA:
                case EXPIRED:
                    result = false;
                    break;
                default:
                    throw new RecIllegalArgumentException("User state is null");
            }
        }
        return result;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        String userId = (user != null) ? user.getId() : "null";
        return "UserDetailsImpl { user_id: " + userId + ", authorities: " + authorities + "}";
    }
}
