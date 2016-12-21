/**
 * Project: RecCore
 * Created by: raulanatol at 19/11/13 12:27
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.dto.generic;

import java.io.Serializable;

/**
 * @author raulanatol
 */
public class EmailDTO implements Serializable {
    private static final long serialVersionUID = -4209100006197639073L;
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmailDTO)) return false;

        EmailDTO emailDTO = (EmailDTO) o;

        if (email != null ? !email.equals(emailDTO.email) : emailDTO.email != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return email != null ? email.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "EmailDTO{" +
                "email='" + email + '\'' +
                '}';
    }
}
