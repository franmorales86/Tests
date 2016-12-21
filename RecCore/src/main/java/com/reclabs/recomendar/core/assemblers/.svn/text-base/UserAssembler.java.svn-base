/**
 * Project: RecCore
 * Created by: raulanatol at 13/02/2013 15:04:28
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.assemblers;

import com.reclabs.recomendar.common.helpers.misc.ReflectionHelper;
import com.reclabs.recomendar.core.helpers.UserHelper;
import com.reclabs.recomendar.model.assemblers.GenericAssembler;
import com.reclabs.recomendar.model.documents.users.User;
import com.reclabs.recomendar.model.objects.user.UserDTO;
import com.reclabs.recomendar.model.objects.user.UserSimpleDataDTO;
import com.reclabs.recomendar.model.types.SocialUserProviderId;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Assembler del document {@link User}
 * @author raulanatol
 */
public class UserAssembler extends GenericAssembler<UserDTO, User> {
    /**
     *
     */
    @Autowired
    protected SocialUserAssembler socialUserAssembler;

    /**
     * Añadimos los campos que queremos ignorar.
     */
    public UserAssembler() {
        super();
        this.ignoreProperties = new String[]{"socialUsers"};
    }

    /*
     * (non-Javadoc)
     * @see com.reclabs.recomendar.model.assemblers.GenericAssembler#internalToRight(java.lang.Object,
     * java.lang.Object)
     */
    @Override
    public UserDTO internalToRight(User source, UserDTO result) {
        result.setSocialUsers(socialUserAssembler.toRight(source.getSocialUsers()));
        return result;
    }

    /*
     * (non-Javadoc)
     * @see com.reclabs.recomendar.model.assemblers.GenericAssembler#internalToLeft(java.lang.Object,
     * java.lang.Object)
     */
    @Override
    public User internalToLeft(UserDTO source, User result) {
        return result;
    }

    /**
     * Actualizamos en el objeto user todos los datos de {@link UserDTO} que no sean null.
     * @param user
     * @param values
     * @return El User modificado.
     */
    public User copyNotNullValues(User user, UserDTO values) {
        List<String> ignore = ReflectionHelper.getNullFields(values, UserDTO.class);
        return toLeft(values, user, ignore);
    }

    /**
     * Simple copy of the parameters of user
     * @param user
     * @return El {@link UserSimpleDataDTO}
     */
    public UserSimpleDataDTO toUserSimpleDTO(User user) {
        UserSimpleDataDTO result = new UserSimpleDataDTO();
        BeanUtils.copyProperties(user, result);
        result.setVisibleName(UserHelper.toVisibleName(user));
        result.setTwitterLinked(UserHelper.userHasProviderId(user, SocialUserProviderId.TWITTER));
        result.setTwitterUsername(UserHelper.usernameFromProviderId(user, SocialUserProviderId.TWITTER));
        result.setFacebookLinked(UserHelper.userHasProviderId(user, SocialUserProviderId.FACEBOOK));
        result.setFacebookUsername(UserHelper.usernameFromProviderId(user, SocialUserProviderId.FACEBOOK));
        result.setId(user.getId());
        return result;
    }

    /**
     * Clone of the user object.
     * @param user2Clone User to clone
     * @return user cloned.
     */
    public User clone(User user2Clone) {
        User result = new User();
        BeanUtils.copyProperties(user2Clone, result);
        return result;
    }
}
