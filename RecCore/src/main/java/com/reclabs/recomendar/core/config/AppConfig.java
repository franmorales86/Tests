/**
 * Project: RecCore
 * Created by: raulanatol at 09/11/2012 18:31:54
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.config;

import com.reclabs.recomendar.core.misc.EnvDataStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author raulanatol
 */
@Configuration
@EnableWebMvc
@EnableMongoRepositories("com.reclabs.recomendar.model.respositories")
@ComponentScan("com.reclabs.recomendar.core")
public class AppConfig {

    /**
     * @return Returns the environment store tool.
     */
    @Bean
    public EnvDataStore envDataStore() {
        return new EnvDataStore();
    }

    /**
     * Bean con las propiedades del classpath
     * @param envDataStore Environment data.
     * @return El bean de propiedades de configuración desde el classpath
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer properties(EnvDataStore envDataStore) {
        Resource[] resources = generateResources(envDataStore);
        PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
        pspc.setLocations(resources);
        pspc.setIgnoreUnresolvablePlaceholders(true);
        return pspc;
    }

    /**
     * @param envDataStore Environment data
     * @return The resources array
     */
    private static Resource[] generateResources(EnvDataStore envDataStore) {
        String envPath = envDataStore.getEnvType().getCode();
        Resource[] result = new Resource[5];
        result[0] = new ClassPathResource("properties/" + envPath + "/amazon.properties");
        result[1] = new ClassPathResource("properties/" + envPath + "/social.properties");
        result[2] = new ClassPathResource("properties/" + envPath + "/mongo.properties");
        result[3] = new ClassPathResource("properties/" + envPath + "/elasticSearch.properties");
        result[4] = new ClassPathResource("properties/" + envPath + "/misc.properties");
        return result;
    }

    /**
     * Obtenemos el multipart resolver para la subida de ficheros por multipart.
     * @return El multipart
     */
    @Bean
    public MultipartResolver multipartResolver() {
        return new CommonsMultipartResolver();
    }
}
