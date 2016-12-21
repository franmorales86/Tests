/**
 * Project: RecCore
 * Created by: raulanatol at 11/01/2013 19:24:17
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.config;

import com.reclabs.barneylib.BarneyServer;
import com.reclabs.libs.recwebparser.RecWebParser;
import com.reclabs.recomendar.core.helpers.MailHelper;
import com.reclabs.recomendar.core.misc.BarneyServerHelper;
import com.reclabs.recomendar.core.misc.security.OAuthHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author raulanatol
 */
@Configuration
public class BeansConfig {

    /**
     * El Bean que se encarga de mantener los servidores de Barney.
     * @return El helper de servidores de Barney.
     */
    @Bean
    public BarneyServerHelper barneyServerHelper() {
        List<BarneyServer> barneys = new ArrayList<>();
        barneys.add(new BarneyServer(1, "http://rcmd.me/", "rcmd_me_links"));

        BarneyServerHelper result = new BarneyServerHelper();
        result.setBarneys(barneys);

        return result;
    }

    /**
     * Codificador de las contraseñas.
     * @return el password encoder.
     */
    @Bean
    public ShaPasswordEncoder passwordEncoder() {
        return new ShaPasswordEncoder(256);
    }

    /**
     * @return El Helper de oauth
     */
    @Bean
    public OAuthHelper oAuthHelper() {
        return new OAuthHelper();
    }

    /**
     * @return The mailHelper instance.
     */
    @Bean
    public MailHelper mailHelper() {
        return new MailHelper();
    }

    /**
     * @return Web parser to recomendar.
     */
    @Bean
    public RecWebParser recWebParser() {
        return new RecWebParser();
    }
}
