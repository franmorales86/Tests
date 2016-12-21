/**
 * Project: RecModel
 * Created by: raulanatol at 05/08/13 15:18
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.model.types;

import com.reclabs.recomendar.common.exceptions.RecIllegalArgumentException;
import org.junit.Test;

import static com.mongodb.util.MyAsserts.assertFalse;
import static com.mongodb.util.MyAsserts.assertTrue;

/**
 * @author raulanatol
 */
public class SecurityRoleTest {

    @Test(expected = RecIllegalArgumentException.class)
    public void nullParameter() {
        SecurityRole.permitLoginSandbox(null);
    }

    @Test
    public void falseResult() {
        assertFalse(SecurityRole.permitLoginSandbox(SecurityRole.ROLE_USER.name()));
    }

    @Test
    public void trueResultUser() {
        assertTrue(SecurityRole.permitLoginSandbox(SecurityRole.ROLE_SANDBOX_USER.name()));
    }

    @Test
    public void trueResultAdmin() {
        assertTrue(SecurityRole.permitLoginSandbox(SecurityRole.ROLE_SANDBOX_ADMIN.name()));
    }
}
