/**
 * Project: RecCore
 * Created by: raulanatol at 21/11/13 09:08
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.misc.security;

import com.reclabs.recomendar.model.types.SecurityRole;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * @author raulanatol
 */
public class SecurityHelperTest {

    @Test
    public void isSandboxAdminWithNullRoles() throws Exception {
        assertFalse(SecurityHelper.isSandboxAdmin(null));
    }

    @Test
    public void isSandboxAdminWithEmptyRoles() throws Exception {
        assertFalse(SecurityHelper.isSandboxAdmin(new ArrayList<SecurityRole>()));
    }

    @Test
    public void isSandboxAdminWithNoSandboxRoles() throws Exception {
        List<SecurityRole> roles = new ArrayList<>();
        roles.add(SecurityRole.ROLE_USER);
        roles.add(SecurityRole.ROLE_WEB_ADMIN);
        assertFalse(SecurityHelper.isSandboxAdmin(roles));
    }

    @Test
    public void isSandboxAdminWithSandboxRoles() throws Exception {
        List<SecurityRole> roles = new ArrayList<>();
        roles.add(SecurityRole.ROLE_SANDBOX_ADMIN);
        roles.add(SecurityRole.ROLE_SANDBOX_USER);
        roles.add(SecurityRole.ROLE_USER);
        roles.add(SecurityRole.ROLE_WEB_ADMIN);
        assertTrue(SecurityHelper.isSandboxAdmin(roles));
    }

    @Test
    public void isSandboxAdminWithSandboxAdminOnly() throws Exception {
        List<SecurityRole> roles = new ArrayList<>();
        roles.add(SecurityRole.ROLE_SANDBOX_ADMIN);
        assertTrue(SecurityHelper.isSandboxAdmin(roles));
    }

    @Test
    public void isSandboxAdminWithSandboxUserOnly() throws Exception {
        List<SecurityRole> roles = new ArrayList<>();
        roles.add(SecurityRole.ROLE_SANDBOX_USER);
        assertTrue(SecurityHelper.isSandboxAdmin(roles));
    }
}
