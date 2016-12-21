/**
 * Project: RecCore
 * Created by: raulanatol at 09/11/2012 18:24:38
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.config.external;

import com.mongodb.Mongo;
import com.mongodb.ServerAddress;
import com.reclabs.recomendar.core.config.external.mongo.RecMoneyConverters;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.convert.CustomConversions;

import java.util.Arrays;
import java.util.List;

/**
 * @author raulanatol
 */
@Configuration
public class MongoConfig extends AbstractMongoConfiguration {
    private UserCredentials userCredentials;

    @Value("${mongo.database}")
    private String mongoDatabase;

    //FIXME mirar como obtener un listado de parámetros desde las properties.

    @Value("${mongo.host.a}")
    private String mongoHostA;
    @Value("${mongo.port.a}")
    private int mongoPortA;

    @Value("${mongo.host.b}")
    private String mongoHostB;
    @Value("${mongo.port.b}")
    private int mongoPortB;

    @Value("${mongo.host.c}")
    private String mongoHostC;
    @Value("${mongo.port.c}")
    private int mongoPortC;


    @Value("${mongo.user}")
    private String mongoUser;

    @Value("${mongo.password}")
    private String mongoPassword;

    /*
     * (non-Javadoc)
     * @see org.springframework.data.mongodb.config.AbstractMongoConfiguration#getDatabaseName()
     */
    @Override
    public String getDatabaseName() {
        return mongoDatabase;
    }

    @Bean(destroyMethod = "close")
    @Override
    public Mongo mongo() throws Exception {
        return new Mongo(Arrays.asList(
                new ServerAddress(mongoHostA, mongoPortA),
                new ServerAddress(mongoHostB, mongoPortB),
                new ServerAddress(mongoHostC, mongoPortC)
        ));
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.mongodb.config.AbstractMongoConfiguration#getUserCredentials()
     */
    @Override
    public UserCredentials getUserCredentials() {
        if (userCredentials == null) {
            userCredentials = new UserCredentials(mongoUser, mongoPassword);
        }
        return userCredentials;
    }

    @Override
    public CustomConversions customConversions() {
        return new CustomConversions((List<?>) RecMoneyConverters.getConvertersToRegister());
    }
}
