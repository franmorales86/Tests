/**
 * Project: RecCore
 * Created by: raulanatol at 15/11/2012 17:52:25
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.repository.social;

import com.reclabs.recomendar.common.helpers.ParameterHelper;
import com.reclabs.recomendar.common.helpers.types.CollectionHelper;
import com.reclabs.recomendar.core.repository.users.UserRepositoryImpl;
import com.reclabs.recomendar.model.documents.users.User;
import org.apache.commons.lang.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.social.connect.*;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * @author raulanatol
 */
@Repository
public class RecUsersConnectionRepository implements UsersConnectionRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(RecUsersConnectionRepository.class);

    @Autowired
    private UserRepositoryImpl userRepository;
    @Autowired
    private ConnectionSignUp connectionSignUp;
    @Autowired
    private TextEncryptor textEncryptor;
    @Autowired
    private ConnectionFactoryLocator connectionFactoryLocator;

    public RecUsersConnectionRepository() {
        super();
    }

    /*
         * (non-Javadoc)
         * @see
         * org.springframework.social.connect.UsersConnectionRepository#findUserIdsWithConnection(org.springframework
         * .social.connect.Connection)
         */
    @Override
    public List<String> findUserIdsWithConnection(Connection<?> connection) {
        List<String> result;
        ConnectionKey connectionKey = connection.getKey();
        List<User> users = userRepository.findUsersByProviderIdAndProviderUserId(connectionKey.getProviderId(), connectionKey.getProviderUserId());
        if (CollectionHelper.isEmpty(users)) {
            String userId = connectionSignUp.execute(connection);
            result = Arrays.asList(userId);
        } else {
            // TODO UPGRADE Si existe más de un usuario es un error, xq se está compartiendo un usuario.
            result = Arrays.asList(users.get(0).getId());
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.social.connect.UsersConnectionRepository#findUserIdsConnectedTo(java.lang.String,
     * java.util.Set)
     */
    @Override
    public Set<String> findUserIdsConnectedTo(String providerId, Set<String> providerUserIds) {
        throw new NotImplementedException();
    }

    /*
     * (non-Javadoc)
     * @see
     * org.springframework.social.connect.UsersConnectionRepository#createConnectionRepository(java.lang.String)
     */
    @Override
    public ConnectionRepository createConnectionRepository(String userId) {
        ParameterHelper.assertEmpty(userId);
        return new RecConnectionRepository(userId, userRepository, textEncryptor, connectionFactoryLocator);
    }

    /**
     * @param connectionSignUp
     */
    public void setConnectionSignUp(ConnectionSignUp connectionSignUp) {
        this.connectionSignUp = connectionSignUp;
    }
}
