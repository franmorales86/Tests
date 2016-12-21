/**
 * Project: RecModel
 * Created by: raulanatol at 11/01/2013 18:32:55
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.model.repositories;

import com.reclabs.barneylib.BarneyServer;
import com.reclabs.barneylib.types.BarneyUrlData;
import com.reclabs.recomendar.common.exceptions.generic.ErrorException;

/**
 * Repositorio de acceso a las tablas de Barney para el acortador de URLs
 * @author raulanatol
 */
public interface BarneyRepository {
    /**
     * Dada la url acortada y la normal las enlazamos en la base de datos.
     * @param server El servidor de barney que produce el shorturl
     * @param barneyUrlData Los datos de la url a guardar en la base de datos.
     * @throws ErrorException En caso de que exista algún error del sistema.
     */
    void save(BarneyServer server, BarneyUrlData barneyUrlData) throws ErrorException;

    /**
     * indica si existe o no la url generada a partir del servidor y shortCode indicados.
     * @param server The barney server
     * @param shortCode The shortcode to verify if exist
     * @return true si existe
     * @throws ErrorException En caso de que exista algún error del sistema.
     */
    boolean exist(BarneyServer server, String shortCode) throws ErrorException;
}
