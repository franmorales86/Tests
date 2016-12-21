/**
 * Project: RecModel
 * Created by: raulanatol at 11/11/13 08:34
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.model.objects.user;

import com.reclabs.recomendar.model.types.user.UserState;

import java.io.Serializable;

/**
 * Is the representation of the session user
 * @author raulanatol
 */
public class UserOwnDataDTO implements Serializable {
    private static final long serialVersionUID = 7961898402651209893L;
    /**
     * Id of the owner user.
     */
    private String id;
    /**
     * State of the user on recomendar.
     */
    private UserState state;
    /**
     * Username of the recomendar user.
     */
    private String username;
    /**
     * Formal name of the user
     */
    private String name;
    /**
     * Description or biography of the user
     */
    private String bio;
    /**
     * true if the user is linked with twitter
     */
    private boolean twitterLinked;
    /**
     * Username of twitter user
     */
    private String twitterUsername;
    /**
     * True if the autoShare on twitter is enabled
     */
    private boolean twitterAutoShare;
    /**
     * true if the user is linked with facebook
     */
    private boolean facebookLinked;
    /**
     * Username of Facebook user
     */
    private String facebookUsername;
    /**
     * true is the autoShare on Facebook is enabled
     */
    private boolean facebookAutoShare;
    /**
     * Email of the user
     */
    private String email;
    /**
     * Url of the user avatar
     */
    private String avatarURL;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserState getState() {
        return state;
    }

    public void setState(UserState state) {
        this.state = state;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public boolean isTwitterLinked() {
        return twitterLinked;
    }

    public void setTwitterLinked(boolean twitterLinked) {
        this.twitterLinked = twitterLinked;
    }

    public String getTwitterUsername() {
        return twitterUsername;
    }

    public void setTwitterUsername(String twitterUsername) {
        this.twitterUsername = twitterUsername;
    }

    public boolean isTwitterAutoShare() {
        return twitterAutoShare;
    }

    public void setTwitterAutoShare(boolean twitterAutoShare) {
        this.twitterAutoShare = twitterAutoShare;
    }

    public boolean isFacebookLinked() {
        return facebookLinked;
    }

    public void setFacebookLinked(boolean facebookLinked) {
        this.facebookLinked = facebookLinked;
    }

    public String getFacebookUsername() {
        return facebookUsername;
    }

    public void setFacebookUsername(String facebookUsername) {
        this.facebookUsername = facebookUsername;
    }

    public boolean isFacebookAutoShare() {
        return facebookAutoShare;
    }

    public void setFacebookAutoShare(boolean facebookAutoShare) {
        this.facebookAutoShare = facebookAutoShare;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatarURL() {
        return avatarURL;
    }

    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserOwnDataDTO)) return false;

        UserOwnDataDTO that = (UserOwnDataDTO) o;

        if (facebookAutoShare != that.facebookAutoShare) return false;
        if (facebookLinked != that.facebookLinked) return false;
        if (twitterAutoShare != that.twitterAutoShare) return false;
        if (twitterLinked != that.twitterLinked) return false;
        if (avatarURL != null ? !avatarURL.equals(that.avatarURL) : that.avatarURL != null) return false;
        if (bio != null ? !bio.equals(that.bio) : that.bio != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (facebookUsername != null ? !facebookUsername.equals(that.facebookUsername) : that.facebookUsername != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (state != that.state) return false;
        if (twitterUsername != null ? !twitterUsername.equals(that.twitterUsername) : that.twitterUsername != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (bio != null ? bio.hashCode() : 0);
        result = 31 * result + (twitterLinked ? 1 : 0);
        result = 31 * result + (twitterUsername != null ? twitterUsername.hashCode() : 0);
        result = 31 * result + (twitterAutoShare ? 1 : 0);
        result = 31 * result + (facebookLinked ? 1 : 0);
        result = 31 * result + (facebookUsername != null ? facebookUsername.hashCode() : 0);
        result = 31 * result + (facebookAutoShare ? 1 : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (avatarURL != null ? avatarURL.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserOwnDataDTO{" +
                "id='" + id + '\'' +
                ", state=" + state +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", bio='" + bio + '\'' +
                ", twitterLinked=" + twitterLinked +
                ", twitterUsername='" + twitterUsername + '\'' +
                ", twitterAutoShare=" + twitterAutoShare +
                ", facebookLinked=" + facebookLinked +
                ", facebookUsername='" + facebookUsername + '\'' +
                ", facebookAutoShare=" + facebookAutoShare +
                ", email='" + email + '\'' +
                ", avatarURL='" + avatarURL + '\'' +
                '}';
    }
}
