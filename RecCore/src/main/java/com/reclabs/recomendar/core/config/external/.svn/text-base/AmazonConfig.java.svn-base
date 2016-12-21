/**
 * Project: RecCore
 * Created by: raulanatol at 11/01/2013 19:18:22
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.config.external;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.simpledb.AmazonSimpleDBClient;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;
import com.reclabs.libs.recqueuedriver.RecQueueDriver;
import com.reclabs.libs.s3imagedriver.S3ImageDriver;
import com.reclabs.libs.s3imagedriver.impl.S3ImageDriverImpl;
import com.reclabs.recomendar.core.helpers.QueueHelper;
import com.reclabs.recomendar.core.misc.EnvDataStore;
import com.reclabs.recomendar.core.misc.EnvType;
import com.reclabs.recomendar.maildriver.RecMailDriver;
import org.jets3t.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Amazon services beans
 * @author raulanatol
 */
@Configuration
public class AmazonConfig {

    @Value("${amazon.awsAccessKey}")
    private String awsAccessKey;

    @Value("${amazon.awsSecretAccessKey}")
    private String awsSecretAccessKey;

    @Value("${amazon.awsAccessKeyPRO}")
    private String awsAccessKeyPRO;

    @Value("${amazon.awsSecretAccessKeyPRO}")
    private String awsSecretAccessKeyPRO;

    @Autowired
    private EnvDataStore envDataStore;

    /**
     * @return The AWS credentials ALWAYS PRO target.
     */
    public AWSCredentials awsCredentialsPRO() {
        return new BasicAWSCredentials(this.awsAccessKeyPRO, this.awsSecretAccessKeyPRO);
    }

    /**
     * Driver to S3 service of Recomendar
     * @return The driver singleton
     * @throws ServiceException In case of error creating the service
     */
    @Bean
    public S3ImageDriver s3ImageDriver() throws ServiceException {
        return new S3ImageDriverImpl(awsAccessKey, awsSecretAccessKey, envDataStore.getEnvType() != EnvType.PRO);
    }

    /**
     * Driver to SimpleDB service of Recomendar
     * @return The driver connector
     */
    @Bean(name = "amazonSimpleDBClientPRO")
    public AmazonSimpleDBClient amazonSimpleDBClientPRO() {
        return new AmazonSimpleDBClient(awsCredentialsPRO());
    }

    /**
     * Driver to SES service of Recomendar
     * @return The ses driver
     */
    @Bean
    public RecMailDriver recMailDriver() {
        AmazonSimpleEmailService amazonSimpleEmailService = new AmazonSimpleEmailServiceClient(awsCredentialsPRO());
        return new RecMailDriver(amazonSimpleEmailService);
    }

    @Bean
    public QueueHelper queueHelper() {
        QueueHelper queueHelper = new QueueHelper();
        queueHelper.setDriver(new RecQueueDriver(awsCredentialsPRO()));
        return queueHelper;
    }
}
