/**
 * Project: RecCore
 * Created by: Diego Nieto at 18/11/2013 09:36:11
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.services.security;

import com.reclabs.recomendar.common.exceptions.RecIllegalArgumentException;
import com.reclabs.recomendar.core.misc.security.UserDetailsImpl;
import com.reclabs.recomendar.model.documents.users.User;
import com.reclabs.recomendar.model.repositories.users.UserRepository;
import com.reclabs.recomendar.model.types.SecurityRole;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Diego Nieto
 */
@RunWith(MockitoJUnitRunner.class)
public class UserDetailsServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserDetailsServiceImpl userDetailsServiceImpl = new UserDetailsServiceImpl();

    @Test(expected = RecIllegalArgumentException.class)
    public void loadUserByUsernameWithNullUsername() {
        userDetailsServiceImpl.loadUserByUsername(null);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void loadUserByUsernameWithEmptyUsername() {
        userDetailsServiceImpl.loadUserByUsername("");
    }

    /**
     * @see UserDetailsServiceImpl#loadUserByUsername(String)
     */
    @Test
    public void loadUserByUsernameWithoutRoles() {
        String username = "M05TCH3CK3R";

        User user = new User();
        user.setName(username);
        user.setRoles(null);

        Collection<GrantedAuthority> result = new ArrayList<>();

        Mockito.when(userRepository.getByUsernameOrEmail(username)).thenReturn(user);

        UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(username);

        boolean expected = userDetails instanceof UserDetailsImpl;

        assertTrue(expected);

        assertEquals(userDetails.getAuthorities(), result);
    }

    /**
     * @see UserDetailsServiceImpl#loadUserByUsername(String)
     */
    @Test
    public void loadUserByUsernameWithFiveRoles() {
        String username = "MT6589";

        List<SecurityRole> securityRoleList = new ArrayList<>();

        securityRoleList.add(SecurityRole.ROLE_SANDBOX_ADMIN);
        securityRoleList.add(SecurityRole.ROLE_SANDBOX_USER);
        securityRoleList.add(SecurityRole.ROLE_USER);
        securityRoleList.add(SecurityRole.ROLE_WEB_ADMIN);
        securityRoleList.add(SecurityRole.ROLE_WEB_USER);

        Collection<GrantedAuthority> collectionTest = new ArrayList<>();
        for (SecurityRole securityRole : securityRoleList) {
            collectionTest.add(new SimpleGrantedAuthority(securityRole.toString()));
        }

        User user = new User();
        user.setName(username);
        user.setRoles(securityRoleList);

        Mockito.when(userRepository.getByUsernameOrEmail(username)).thenReturn(user);

        UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(username);

        boolean expected = userDetails instanceof UserDetailsImpl;

        assertTrue(expected);

        assertEquals(userDetails.getAuthorities(), collectionTest);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void findByUserIdWithNullUserId() {
        userDetailsServiceImpl.findByUserId(null);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void findByUserIdWithEmptyUserId() {
        userDetailsServiceImpl.findByUserId("");
    }

    /**
     * @see UserDetailsServiceImpl#findByUserId(String)
     */
    @Test
    public void findByUserIdWithoutRoles() {
        String userId = "L355CH3CK3R";

        User user = new User();
        user.setName(userId);
        user.setRoles(null);

        Collection<GrantedAuthority> result = new ArrayList<>();

        Mockito.when(userRepository.findByUserId(userId)).thenReturn(user);

        UserDetails userDetails = userDetailsServiceImpl.findByUserId(userId);

        boolean expected = userDetails instanceof UserDetailsImpl;

        assertTrue(expected);

        assertEquals(userDetails.getAuthorities(), result);
    }

    /**
     * @see UserDetailsServiceImpl#findByUserId(String)
     */
    @Test
    public void findByUserIdWithFiveRoles() {
        String userId = "FX9590";

        List<SecurityRole> securityRoleList = new ArrayList<>();

        securityRoleList.add(SecurityRole.ROLE_USER);
        securityRoleList.add(SecurityRole.ROLE_SANDBOX_USER);
        securityRoleList.add(SecurityRole.ROLE_WEB_USER);
        securityRoleList.add(SecurityRole.ROLE_SANDBOX_ADMIN);
        securityRoleList.add(SecurityRole.ROLE_WEB_ADMIN);

        Collection<GrantedAuthority> collectionTest = new ArrayList<>();
        for (SecurityRole securityRole : securityRoleList) {
            collectionTest.add(new SimpleGrantedAuthority(securityRole.toString()));
        }

        User user = new User();
        user.setName(userId);
        user.setRoles(securityRoleList);

        Mockito.when(userRepository.findByUserId(userId)).thenReturn(user);

        UserDetails userDetails = userDetailsServiceImpl.findByUserId(userId);

        boolean expected = userDetails instanceof UserDetailsImpl;

        assertTrue(expected);

        assertEquals(userDetails.getAuthorities(), collectionTest);
    }

}
