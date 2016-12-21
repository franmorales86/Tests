/**
 * Project: RecCore
 * Created by: raulanatol at 22/01/2013 21:25:03
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.config.social;

import com.reclabs.recomendar.core.misc.EnvDataStore;
import com.reclabs.recomendar.core.misc.security.SecurityHelper;
import com.reclabs.recomendar.core.misc.social.SimpleConnectionSignUp;
import com.reclabs.recomendar.core.misc.social.SimpleSignInAdapter;
import com.reclabs.recomendar.core.repository.social.RecUsersConnectionRepository;
import com.reclabs.recomendar.core.services.security.UserDetailsServiceImpl;
import com.reclabs.recomendar.model.documents.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.connect.web.ProviderSignInController;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.connect.TwitterConnectionFactory;

/**
 * Class to configure all beans related with social part of Recomendar.
 * @author raulanatol
 */
@Configuration
public class SocialConfig {

    /**
     * Is used to configure the environment variable of the servers
     */
    @Autowired
    protected EnvDataStore envDataStore;

    /**
     * Twitter consumer key of recomendar account
     */
    @Value("${social.twitter.consumer.key}")
    private String twitterConsumerKey;

    /**
     * Twitter secret of recomendar account
     */
    @Value("${social.twitter.consumer.secret}")
    private String twitterConsumerSecret;

    /**
     * Facebook secret id of recomendar account
     */
    @Value("${social.facebook.secret}")
    private String facebookSecretId;

    /**
     * Facebook client id of recomendar account
     */
    @Value("${social.facebook.clientId}")
    private String facebookClientId;

    /**
     * Encryption code of passwords
     */
    @Value("${social.encryption.password}")
    private String encryptionPassword;

    /**
     * Salt of encryption code
     */
    @Value("${social.encryption.salt}")
    private String encryptionSalt;

    @Autowired
    private RecUsersConnectionRepository recUsersConnectionRepository;

    /**
     * @return El TextEncryption
     */
    @Bean
    public TextEncryptor textEncryptor() {
        return Encryptors.noOpText();
    }

    /**
     * Controlador que se encargará de toda la conexión con las aplicaciones externas sociales.
     * @param simpleSignInAdapter Adaptador de SignIn
     * @return El controlador ya instanciado.
     */
    @Bean
    @Autowired
    public ProviderSignInController providerSignInController(SimpleSignInAdapter simpleSignInAdapter) {
        ProviderSignInController controller = new ProviderSignInController(connectionFactoryLocator(), recUsersConnectionRepository, simpleSignInAdapter);
        controller.setPostSignInUrl("/social/signin");
        return controller;
    }

    /**
     * Listado de todas las conexiones a las factorías de aplicaciones sociales.
     * @return La factoría de conexiones a aplicaciones sociales.
     */
    @Bean
    public ConnectionFactoryLocator connectionFactoryLocator() {
        ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();
        registry.addConnectionFactory(new TwitterConnectionFactory(twitterConsumerKey, twitterConsumerSecret));
        registry.addConnectionFactory(new FacebookConnectionFactory(facebookClientId, facebookSecretId));
        return registry;
    }

    /**
     * @return El simpleSignUp
     */
    @Bean
    public SimpleConnectionSignUp simpleConnectionSignUp() {
        return new SimpleConnectionSignUp();
    }

    @Bean
    @Autowired
    public SimpleSignInAdapter simpleSignInAdapter(UserDetailsServiceImpl userDetailsServiceImpl) {
        return new SimpleSignInAdapter(userDetailsServiceImpl);
    }

    @Bean
    @Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
    public Twitter twitter() {
        User user = SecurityHelper.getLoggedInUser();
        ConnectionRepository recConnectionRepository = recUsersConnectionRepository.createConnectionRepository(user.getId());
        Connection<Twitter> primaryConnection = recConnectionRepository.findPrimaryConnection(Twitter.class);
        return primaryConnection.getApi();
    }

    @Bean
    @Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
    public Facebook facebook() {
        User user = SecurityHelper.getLoggedInUser();
        ConnectionRepository recConnectionRepository = recUsersConnectionRepository.createConnectionRepository(user.getId());
        Connection<Facebook> primaryConnection = recConnectionRepository.findPrimaryConnection(Facebook.class);
        return primaryConnection.getApi();
    }
}
