/**
 * Project: RecCore
 * Created by: fjmorales at 04/04/2013 12:59:07
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.config.external;

import com.reclabs.recomendar.common.helpers.types.NumberHelper;
import com.reclabs.recomendar.esdriver.ESDriver;
import com.reclabs.recomendar.esdriver.ESDriverImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author fjmorales
 */
@Configuration
public class ESConfig {
    /**
     * Host de elasticsearch
     */
    @Value("${elasticsearch.host}")
    private String host;
    /**
     * Puerto de elasticsearch
     */
    @Value("${elasticsearch.port}")
    private String port;

    /**
     * El conector con el servicio de elasticSearch de Recomendar.
     * @return El driver conector a elasticSearch
     */
    @Bean
    public ESDriver esDriver() {
        return new ESDriverImpl(host, NumberHelper.toInteger(port));
    }

}
