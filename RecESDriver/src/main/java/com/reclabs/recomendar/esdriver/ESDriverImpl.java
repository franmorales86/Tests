/**
 * Project: RecESDriver
 * Created by: fjmorales at 04/04/2013 12:42:26
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.esdriver;

import com.reclabs.recomendar.esdriver.actions.ESFind;
import com.reclabs.recomendar.esdriver.exceptions.ESCommunicationException;
import com.reclabs.recomendar.esdriver.helper.JestResultHelper;
import io.searchbox.Action;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.JestResult;
import io.searchbox.client.config.ClientConfig;
import io.searchbox.client.config.ClientConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.LinkedHashSet;

/**
 * Implementación del driver del ES
 * @author fjmorales
 */
public class ESDriverImpl implements ESDriver {
    private static final Logger LOGGER = LoggerFactory.getLogger(ESDriverImpl.class);

    /**
     * Cliente de comunicación con el elasticsearch
     */
    private JestClient client;

    /**
     * Constructor por defecto
     * @param host Host de elasticSearch
     * @param port Puerto de conexion de elasticSearch
     */
    public ESDriverImpl(String host, int port) {
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.getProperties().put(ClientConstants.SERVER_LIST, getServers(host, port));
        clientConfig.getProperties().put(ClientConstants.IS_MULTI_THREADED, true);

        JestClientFactory factory = new JestClientFactory();
        factory.setClientConfig(clientConfig);
        client = factory.getObject();
    }

    /**
     * Obtenemos los servidores de elasticsearch
     * @param host La dirección del servidor de elasticsearch
     * @param port El puerto de comunicación
     */
    private LinkedHashSet<String> getServers(String host, int port) {
        LinkedHashSet<String> servers = new LinkedHashSet<>();
        servers.add(host + ":" + port);
        return servers;
    }

    /**
     * Ejecutamos una acción sobre el client.
     * @param action La acción a ejecutar.
     * @return El resultado de la consulta.
     */
    @Override
    public JestResult executeAction(Action action) {
        try {
            return client.execute(action);
        } catch (Exception e) {
            LOGGER.error("Error de comunicación con ES", e);
            throw new ESCommunicationException("Communication error", e);
        }
    }

    @Override
    public <T> Collection<T> searchByQueryList(ESFind query, Class<T> resultClass) {
        JestResult result = executeAction(query.getAction());
        return JestResultHelper.getSourceAsObjectList(result, resultClass);
    }

    @Override
    public <T> Collection<T> searchSimpleFieldByQueryList(ESFind query, Class<T> resultClass, String fieldName) {
        JestResult result = executeAction(query.getAction());
        return JestResultHelper.getSourceAsObjectListByField(result, resultClass, fieldName);
    }

}
