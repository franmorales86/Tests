/**
 * Project: RecModel
 * Created by: raulanatol at 11/01/2013 18:28:38
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.model.services;

import com.reclabs.barneylib.BarneyServer;
import com.reclabs.barneylib.LinkType;
import com.reclabs.recomendar.common.exceptions.generic.ErrorException;

/**
 * Servicio de acceso a datos de Barney
 * @author raulanatol
 */
public interface BarneyService {

    /**
     * @param url La url que se desea acortar.
     * @param webname El nombre de la web.
     * @param linkType El tipo de enlace.
     * @param userId El userId del usuario que crea el enlace acortado.
     * @return La url acortada.
     * @throws ErrorException En caso de que no sea posible la creación de la url acortada.
     */
    public String createShortUrl(final String url, String webname, LinkType linkType, String userId) throws ErrorException;

    /**
     * Dado un servidor barney y un shortCode verificamos que ese shortCode existe para ese servidor.
     * @param server El servidor que obtendrá la url a verificar si existe.
     * @param shortCode El código acortado a verificar.
     * @return Si existe o no la el shortCode en el servidor.
     * @throws ErrorException En caso de que no sea posible la creación de la url acortada.
     */
    public boolean exist(BarneyServer server, String shortCode) throws ErrorException;


    /**
     * @param url The item url on recomendar.com
     * @param userId The userId. 0 = Anonymous
     * @param itemId Id of the item
     * @return the shortURL of an item
     */
    public String createItemShortURL(String url, String userId, String itemId);
}
