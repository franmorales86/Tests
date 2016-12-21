/**
 * Project: RecCore
 * Created by: raulanatol at 11/11/2012 00:05:37
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.misc;

import com.reclabs.recomendar.common.helpers.types.StringHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * It's a representation of the environment variables of the recomendar core.
 * @author raulanatol
 */
public class EnvDataStore {
    private static final Logger LOGGER = LoggerFactory.getLogger(EnvDataStore.class);

    /**
     * The type of environment that running now.
     */
    private final EnvType envType;

    /**
     * Gets the running now environment
     */
    public EnvDataStore() {
        String envValue = System.getProperty("PARAM1");
        if (StringHelper.isEmpty(envValue)) {
            envType = EnvType.DEV;
            LOGGER.warn("Loading development environment, because the environment variable is not found");
        } else {
            envType = EnvType.fromValue(envValue);
            LOGGER.info("Loading " + envType + " environment from PARAM1 = [" + envValue + "]");
        }
    }

    /**
     * @return the envType
     */
    public EnvType getEnvType() {
        return envType;
    }
}
