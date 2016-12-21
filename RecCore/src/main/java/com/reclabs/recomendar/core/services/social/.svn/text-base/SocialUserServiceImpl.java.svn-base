/**
 * Project: RecCore
 * Created by: raulanatol at 13/11/2012 18:54:44
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.services.social;

import com.reclabs.recomendar.core.repository.social.RecConnectionRepository;
import com.reclabs.recomendar.model.repositories.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author raulanatol
 */
@Transactional(readOnly = true)
public class SocialUserServiceImpl implements SocialUserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TextEncryptor textEncryptor;
    @Autowired
    private ConnectionFactoryLocator connectionFactoryLocator;

    /*
     * (non-Javadoc)
     * @see
     * org.springframework.social.connect.UsersConnectionRepository#createConnectionRepository(java.lang.String)
     */
    @Override
    public ConnectionRepository createConnectionRepository(String userId) {
        if (userId == null) {
            throw new IllegalArgumentException("UserId no puede ser null");
        }
        return new RecConnectionRepository(userId, userRepository, textEncryptor, connectionFactoryLocator);
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.social.connect.UsersConnectionRepository#findUserIdsConnectedTo(java.lang.String,
     * java.util.Set)
     */
    @Override
    public Set<String> findUserIdsConnectedTo(String providerId, Set<String> providerUserIds) {
        return new HashSet<>(userRepository.findUserIdsByProviderIdAndProviderUserIds(providerId, providerUserIds));
    }

    /*
     * (non-Javadoc)
     * @see
     * org.springframework.social.connect.UsersConnectionRepository#findUserIdsWithConnection(org.springframework
     * .social.connect.Connection)
     */
    @Override
    public List<String> findUserIdsWithConnection(Connection<?> connection) {
        ConnectionKey connectionKey = connection.getKey();
        return userRepository.findUserIdsByProviderIdAndProviderUserId(connectionKey.getProviderId(), connectionKey.getProviderUserId());
    }
}
