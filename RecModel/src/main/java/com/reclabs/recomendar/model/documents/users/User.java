/**
 * Project: RecModel
 * Created by: raulanatol at 09/11/2012 20:03:51
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.model.documents.users;

import com.reclabs.recomendar.model.types.SecurityRole;
import com.reclabs.recomendar.model.types.SexType;
import com.reclabs.recomendar.model.types.user.UserState;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author raulanatol
 *         Representa los datos de un usuario.
 */
@Document
public class User implements Serializable {

    private static final long serialVersionUID = -2497698232208260210L;

    @Id
    private String id;
    /**
     * Login del usuario
     */
    @Indexed
    private String username;
    /**
     * Password del usuario
     */
    private String password;

    private Date createdDate;

    /**
     * Nombre del usuario
     */
    @Indexed
    private String name;
    /**
     * Primer Apellido del usuario
     */
    private String lastname1;
    /**
     * Segundo Apellido del usuario
     */
    private String lastname2;
    /**
     * Fecha de nacimiento
     */
    private Date birthday;
    /**
     * Sexo del usuario
     */
    private SexType sex;
    /**
     * Descripcion del usuario
     */
    private String description;
    /**
     * Email del usuario
     */
    private String email;
    /**
     * URL de la imagen del avatar del usuario
     */
    private String avatarURL;
    /**
     * Estado del usuario
     */
    private UserState state;
    /**
     * Listado de amigos de un usuario
     */
    @DBRef
    private List<User> friends;
    /**
     * Representan los diferentes roles de seguridad que tiene un usuario.
     */
    private List<SecurityRole> roles;
    /**
     *
     */
    private List<SocialUser> socialUsers;

    /**
     * Is the code of verification email, sending to user.
     */
    private String emailVerificationCode;

    /**
     * Is the code of delete account, sending to user to permit delete account.
     */
    private String deleteAccountCode;

    /**
     * Number of pending recommends of the user.
     */
    private Long pendingRecommends;

    /**
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * @param id the id to set
     */
    public void setId(final String id) {
        this.id = id;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(final String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(final String password) {
        this.password = password;
    }

    /**
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * @param name the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @return the lastName1
     */
    public String getLastname1() {
        return this.lastname1;
    }

    /**
     * @param lastname1 the lastname1 to set
     */
    public void setLastname1(final String lastname1) {
        this.lastname1 = lastname1;
    }

    /**
     * @return the lastname2
     */
    public String getLastname2() {
        return this.lastname2;
    }

    /**
     * @param lastname2 the lastname2 to set
     */
    public void setLastname2(final String lastname2) {
        this.lastname2 = lastname2;
    }

    /**
     * @return the birthday
     */
    public Date getBirthday() {
        return this.birthday;
    }

    /**
     * @param birthday the birthday to set
     */
    public void setBirthday(final Date birthday) {
        this.birthday = birthday;
    }

    /**
     * @return the sex
     */
    public SexType getSex() {
        return this.sex;
    }

    /**
     * @param sex the sex to set
     */
    public void setSex(final SexType sex) {
        this.sex = sex;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(final String email) {
        this.email = email;
    }

    /**
     * @return the avatarURL
     */
    public String getAvatarURL() {
        return this.avatarURL;
    }

    /**
     * @param avatarURL the avatarURL to set
     */
    public void setAvatarURL(final String avatarURL) {
        this.avatarURL = avatarURL;
    }

    /**
     * @return the state
     */
    public UserState getState() {
        return this.state;
    }

    /**
     * @param state the state to set
     */
    public void setState(final UserState state) {
        this.state = state;
    }

    /**
     * @return the friends
     */
    public List<User> getFriends() {
        return this.friends;
    }

    /**
     * @param friends the friends to set
     */
    public void setFriends(final List<User> friends) {
        this.friends = friends;
    }

    /**
     * @return the roles
     */
    public List<SecurityRole> getRoles() {
        return this.roles;
    }

    /**
     * @param roles the roles to set
     */
    public void setRoles(final List<SecurityRole> roles) {
        this.roles = roles;
    }

    /**
     * @return the socialUsers
     */
    public List<SocialUser> getSocialUsers() {
        return this.socialUsers;
    }

    /**
     * @param socialUsers the socialUsers to set
     */
    public void setSocialUsers(final List<SocialUser> socialUsers) {
        this.socialUsers = socialUsers;
    }

    public Long getPendingRecommends() {
        return pendingRecommends;
    }

    public void setPendingRecommends(Long pendingRecommends) {
        this.pendingRecommends = pendingRecommends;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", createdDate=" + createdDate +
                ", name='" + name + '\'' +
                ", lastname1='" + lastname1 + '\'' +
                ", lastname2='" + lastname2 + '\'' +
                ", birthday=" + birthday +
                ", sex=" + sex +
                ", description='" + description + '\'' +
                ", email='" + email + '\'' +
                ", avatarURL='" + avatarURL + '\'' +
                ", state=" + state +
                ", friends=" + friends +
                ", roles=" + roles +
                ", socialUsers=" + socialUsers +
                ", emailVerificationCode='" + emailVerificationCode + '\'' +
                ", deleteAccountCode='" + deleteAccountCode + '\'' +
                ", pendingRecommends=" + pendingRecommends +
                '}';
    }

    public String getEmailVerificationCode() {
        return emailVerificationCode;
    }

    public void setEmailVerificationCode(String emailVerificationCode) {
        this.emailVerificationCode = emailVerificationCode;
    }

    public String getDeleteAccountCode() {
        return deleteAccountCode;
    }

    public void setDeleteAccountCode(String deleteAccountCode) {
        this.deleteAccountCode = deleteAccountCode;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (avatarURL != null ? !avatarURL.equals(user.avatarURL) : user.avatarURL != null) return false;
        if (birthday != null ? !birthday.equals(user.birthday) : user.birthday != null) return false;
        if (createdDate != null ? !createdDate.equals(user.createdDate) : user.createdDate != null) return false;
        if (deleteAccountCode != null ? !deleteAccountCode.equals(user.deleteAccountCode) : user.deleteAccountCode != null) return false;
        if (description != null ? !description.equals(user.description) : user.description != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (emailVerificationCode != null ? !emailVerificationCode.equals(user.emailVerificationCode) : user.emailVerificationCode != null) return false;
        if (friends != null ? !friends.equals(user.friends) : user.friends != null) return false;
        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (lastname1 != null ? !lastname1.equals(user.lastname1) : user.lastname1 != null) return false;
        if (lastname2 != null ? !lastname2.equals(user.lastname2) : user.lastname2 != null) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (pendingRecommends != null ? !pendingRecommends.equals(user.pendingRecommends) : user.pendingRecommends != null) return false;
        if (roles != null ? !roles.equals(user.roles) : user.roles != null) return false;
        if (sex != user.sex) return false;
        if (socialUsers != null ? !socialUsers.equals(user.socialUsers) : user.socialUsers != null) return false;
        if (state != user.state) return false;
        if (username != null ? !username.equals(user.username) : user.username != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (lastname1 != null ? lastname1.hashCode() : 0);
        result = 31 * result + (lastname2 != null ? lastname2.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (avatarURL != null ? avatarURL.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (friends != null ? friends.hashCode() : 0);
        result = 31 * result + (roles != null ? roles.hashCode() : 0);
        result = 31 * result + (socialUsers != null ? socialUsers.hashCode() : 0);
        result = 31 * result + (emailVerificationCode != null ? emailVerificationCode.hashCode() : 0);
        result = 31 * result + (deleteAccountCode != null ? deleteAccountCode.hashCode() : 0);
        result = 31 * result + (pendingRecommends != null ? pendingRecommends.hashCode() : 0);
        return result;
    }
}
