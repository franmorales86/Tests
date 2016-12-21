/**
 * Project: RecCore
 * Created by: raulanatol at 13/11/2012 19:06:27
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.repository.social;

import com.reclabs.recomendar.common.helpers.types.CollectionHelper;
import com.reclabs.recomendar.common.helpers.types.StringHelper;
import com.reclabs.recomendar.model.documents.users.SocialUser;
import com.reclabs.recomendar.model.documents.users.User;
import com.reclabs.recomendar.model.repositories.users.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.social.connect.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;

/**
 * @author raulanatol
 */
public class RecConnectionRepository implements ConnectionRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(RecConnectionRepository.class);

    private UserRepository userRepository;
    private String userId;
    private ConnectionFactoryLocator connectionFactoryLocator;
    private TextEncryptor textEncryptor;

    /**
     * Constructor of the class
     * @param userId The user id
     * @param userRepository The user repository
     * @param textEncryptor A TextEncryptor class
     * @param connectionFactoryLocator Connection factory locator
     */
    public RecConnectionRepository(String userId, UserRepository userRepository, TextEncryptor textEncryptor, ConnectionFactoryLocator connectionFactoryLocator) {
        super();
        this.userId = userId;
        this.userRepository = userRepository;
        this.textEncryptor = textEncryptor;
        this.connectionFactoryLocator = connectionFactoryLocator;
    }

    @Override
    @Transactional(readOnly = false)
    public void addConnection(Connection<?> connection) {
        ConnectionData connectionData = connection.createData();

        checkIfSocialUserExist(connectionData);
        this.userRepository.addSocialUser(getSocialUserByConnectionData(connectionData));
    }

    /**
     * Create a social user using an connectionData
     * @param connectionData A connection data
     * @return The new social user created
     */
    private SocialUser getSocialUserByConnectionData(ConnectionData connectionData) {
        SocialUser socialUser = new SocialUser();
        socialUser.setUserId(this.userId);
        socialUser.setProviderId(connectionData.getProviderId());
        socialUser.setProviderUserId(connectionData.getProviderUserId());
        socialUser.setDisplayName(connectionData.getDisplayName());

        socialUser.setAccessToken(encrypt(connectionData.getAccessToken()));
        socialUser.setSecret(encrypt(connectionData.getSecret()));
        socialUser.setRefreshToken(encrypt(connectionData.getRefreshToken()));
        socialUser.setExpireTime(connectionData.getExpireTime());
        return socialUser;
    }

    /**
     * Encrypt the text pass for parameters.
     * @param text Text to encrypt
     * @return A text encrypt
     */
    private String encrypt(String text) {
        return text != null ? this.textEncryptor.encrypt(text) : text;
    }

    /**
     * Verificamos que los nuevos datos de conexión no existan ya en la base de datos.
     * En caso de que existan devolvemos una excepción en tiempo de ejecución.
     * @param connectionData A connection data to check
     */
    private void checkIfSocialUserExist(ConnectionData connectionData) {
        List<String> userIds = this.userRepository.findUserIdsByProviderIdAndProviderUserId(connectionData.getProviderId(), connectionData.getProviderUserId());
        if (!CollectionHelper.isEmpty(userIds)) {
            throw new DuplicateConnectionException(new ConnectionKey(connectionData.getProviderId(), connectionData.getProviderUserId()));
        }
    }

    @Override
    public MultiValueMap<String, Connection<?>> findAllConnections() {
        MultiValueMap<String, Connection<?>> connections = new LinkedMultiValueMap<>();
        User user = this.userRepository.findByUserId(this.userId);
        for (SocialUser socialUser : user.getSocialUsers()) {
            ConnectionData connectionData = toConnectionData(socialUser);
            Connection<?> connection = createConnection(connectionData);
            connections.add(connectionData.getProviderId(), connection);
        }
        return connections;
    }

    /**
     * @param connectionData A connection data to create
     * @return A connection data
     */
    private Connection<?> createConnection(ConnectionData connectionData) {
        ConnectionFactory<?> connectionFactory = this.connectionFactoryLocator.getConnectionFactory(connectionData.getProviderId());
        return connectionFactory.createConnection(connectionData);
    }

    /**
     * @param socialUser A social user
     * @return A connection data created
     */
    private ConnectionData toConnectionData(SocialUser socialUser) {
        return new ConnectionData(socialUser.getProviderId(), socialUser.getProviderUserId(), socialUser.getDisplayName(), "profileUrl", "imageUrl", socialUser.getAccessToken(), socialUser.getSecret(), socialUser.getRefreshToken(),
                socialUser.getExpireTime());
    }

    @Override
    public List<Connection<?>> findConnections(String providerId) {
        List<Connection<?>> connections = new ArrayList<>();
        List<User> socialUsers = this.userRepository.findByUserIdAndProviderId(this.userId, providerId);
        for (User user : socialUsers) {
            for (SocialUser socialUser : user.getSocialUsers()) {
                ConnectionData connectionData = toConnectionData(socialUser);
                Connection<?> connection = createConnection(connectionData);
                connections.add(connection);
            }
        }
        return connections;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <A> List<Connection<A>> findConnections(Class<A> apiType) {
        String providerId = this.connectionFactoryLocator.getConnectionFactory(apiType).getProviderId();

        // do some lame stuff to make the casting possible
        List<?> connections = findConnections(providerId);
        return (List<Connection<A>>) connections;
    }

    /*
     * (non-Javadoc)
     * @see
     * org.springframework.social.connect.ConnectionRepository#findConnectionsToUsers(org.springframework.util.
     * MultiValueMap)
     */
    @Override
    public MultiValueMap<String, Connection<?>> findConnectionsToUsers(MultiValueMap<String, String> providerUserIds) {
        MultiValueMap<String, Connection<?>> connections = new LinkedMultiValueMap<>();
        List<User> allSocialUsers = this.userRepository.findByUserIdAndProviderUserIds(this.userId, providerUserIds);
        for (User user : allSocialUsers) {
            for (SocialUser socialUser : user.getSocialUsers()) {
                ConnectionData connectionData = toConnectionData(socialUser);
                Connection<?> connection = createConnection(connectionData);
                connections.add(connectionData.getProviderId(), connection);
            }
        }
        return connections;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <A> Connection<A> findPrimaryConnection(Class<A> apiType) {
        Connection<A> result = null;
        String providerId = connectionFactoryLocator.getConnectionFactory(apiType).getProviderId();
        //TODO change result to an User
        List<User> users = userRepository.findPrimaryByUserIdAndProviderId(this.userId, providerId);
        User user = users.get(0);
        if (user != null) {
            if (!CollectionHelper.isEmpty(user.getSocialUsers())) {
                for (SocialUser socialUser : user.getSocialUsers()) {
                    if (StringHelper.equals(socialUser.getProviderId(), providerId)) {
                        result = (Connection<A>) createConnection(toConnectionData(socialUser));
                        break;
                    }
                }
                if (result == null) {
                    LOGGER.warn("User found but no socialUsers with the specified providerId userId: {} providerId: {}", user, providerId);
                }
            } else {
                LOGGER.warn("User found but no socialUsers userId: {} providerId: {}", user, providerId);
            }
        } else {
            LOGGER.warn("User not found userId: {} providerId: {}", user, providerId);
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * @see
     * org.springframework.social.connect.ConnectionRepository#getConnection(org.springframework.social.connect
     * .ConnectionKey)
     */
    @Override
    public Connection<?> getConnection(ConnectionKey connectionKey) {
        User user = this.userRepository.get(this.userId, connectionKey.getProviderId(), connectionKey.getProviderUserId());
        if (user == null || CollectionHelper.isEmpty(user.getSocialUsers())) {
            throw new NoSuchConnectionException(connectionKey);
        }
        return createConnection(toConnectionData(user.getSocialUsers().get(0)));
    }

    @SuppressWarnings("unchecked")
    @Override
    public <A> Connection<A> getConnection(Class<A> apiType, String providerUserId) {
        String providerId = this.connectionFactoryLocator.getConnectionFactory(apiType).getProviderId();
        User user = this.userRepository.get(this.userId, providerId, providerUserId);
        if (user == null || CollectionHelper.isEmpty(user.getSocialUsers())) {
            throw new NoSuchConnectionException(new ConnectionKey(providerId, providerUserId));
        }
        return (Connection<A>) createConnection(toConnectionData(user.getSocialUsers().get(0)));
    }

    @Override
    public <A> Connection<A> getPrimaryConnection(Class<A> apiType) {
        Connection<A> connection = findPrimaryConnection(apiType);
        if (connection == null) {
            String providerId = this.connectionFactoryLocator.getConnectionFactory(apiType).getProviderId();
            throw new NotConnectedException(providerId);
        }
        return connection;
    }

    @Override
    @Transactional(readOnly = false)
    public void removeConnection(ConnectionKey connectionKey) {
        User socialUser = this.userRepository.get(this.userId, connectionKey.getProviderId(), connectionKey.getProviderUserId());
        if (socialUser != null) {
            this.userRepository.delete(socialUser);
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void removeConnections(String providerId) {
        // TODO replace with bulk delete HQL
        List<User> socialUsers = this.userRepository.findByUserIdAndProviderId(this.userId, providerId);
        for (User socialUser : socialUsers) {
            this.userRepository.delete(socialUser);
        }

    }

    @Override
    @Transactional(readOnly = false)
    public void updateConnection(Connection<?> connection) {
        ConnectionData connectionData = connection.createData();
        User user = this.userRepository.findOne(this.userId);
        SocialUser socialUser = getSocialUserByProviderId(user, connectionData.getProviderId(), connectionData.getProviderUserId());
        if (socialUser != null) {
            socialUser.setDisplayName(connectionData.getDisplayName());
            socialUser.setAccessToken(encrypt(connectionData.getAccessToken()));
            socialUser.setSecret(encrypt(connectionData.getSecret()));
            socialUser.setRefreshToken(encrypt(connectionData.getRefreshToken()));
            socialUser.setExpireTime(connectionData.getExpireTime());
            this.userRepository.save(user);
        }
    }

    /**
     * @param user User data
     * @param providerId Provider id
     * @param providerUserId Provider user id
     * @return A social user
     */
    private SocialUser getSocialUserByProviderId(User user, String providerId, String providerUserId) {
        SocialUser result = null;
        for (SocialUser socialUser : user.getSocialUsers()) {
            if (StringHelper.equals(socialUser.getProviderId(), providerId) && StringHelper.equals(socialUser.getProviderUserId(), providerUserId)) {
                result = socialUser;
                break;
            }
        }
        return result;
    }
}
