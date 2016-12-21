/**
 * Project: RecCore
 * Created by: raulanatol at 11/01/2013 17:30:14
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.misc;

import com.reclabs.barneylib.BarneyServer;
import com.reclabs.barneylib.LinkType;
import com.reclabs.barneylib.exceptions.ShortCodeDuplicatedException;
import com.reclabs.barneylib.types.BarneyData;
import com.reclabs.barneylib.types.BarneyUrlData;
import com.reclabs.recomendar.common.exceptions.generic.ErrorException;
import com.reclabs.recomendar.common.helpers.RandomHelper;
import com.reclabs.recomendar.common.helpers.types.DateHelper;
import com.reclabs.recomendar.common.types.CodeErrorType;
import com.reclabs.recomendar.common.types.DatePrecisionType;
import com.reclabs.recomendar.model.services.BarneyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase controladora de todos los Barneys de Recomendar
 * @author raulanatol
 */
public class BarneyServerHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(BarneyServerHelper.class);

    @Autowired
    private BarneyService barneyService;
    /**
     * Número de intentos para generar una url acortada.
     */
    private static final int MAX_ATTEMPTS = 10;
    /**
     * Listado de todos los Barneys de Recomendar.
     */
    private List<BarneyServer> barneys;

    /**
     * Dado un shortCode intentamos generar una URL acortada en alguno de los servidores disponibles, en caso de
     * que ese shortCode esté cogido en todos los servidores se devolverá una excepción.
     * @param shortCode El shortCode generado.
     * @param validUrl La url a la que se desea acceder.
     * @param webName El nombre de la web de la url acortada.
     * @param linkType El tipo de enlace.
     * @param userId Identificador del usuario que crea la url acortada.
     * @param data1 Value of data1
     * @return La url generada.
     * @throws ShortCodeDuplicatedException En caso de que el shortCode esté duplicado en todos los servidores.
     * @throws ErrorException               En caso de que no sea posible la creación de la url acortada.
     */
    protected BarneyData generateOnAnyServer(final String shortCode, final String validUrl, String webName, LinkType linkType, String userId, String data1) throws ShortCodeDuplicatedException, ErrorException {
        List<Integer> usedPositions = new ArrayList<>();
        BarneyServer server;
        int position;
        boolean exist;
        do {
            position = RandomHelper.getRandomPosition(0, barneys.size(), usedPositions);
            if (position == -1) {
                throw new ShortCodeDuplicatedException(shortCode);
            }
            usedPositions.add(position);
            server = barneys.get(position);
            exist = barneyService.exist(server, shortCode);
        } while (exist);

        BarneyUrlData urlData = new BarneyUrlData();
        urlData.setCreatedDate(DateHelper.getCurrentDate(DatePrecisionType.MILLISECOND));
        urlData.setShortCode(shortCode);
        urlData.setValidUrl(validUrl);
        urlData.setShortUrl(server.generateShortUrl(shortCode));
        urlData.setWebname(webName);
        urlData.setLinkType(linkType);
        urlData.setUserId(userId);
        urlData.setData1(data1);

        BarneyData result = new BarneyData();
        result.setBarneyServer(server);
        result.setUrlData(urlData);
        return result;
    }

    /**
     * Generamos la url acortada de la url pasada por parámetros en cualquier servidor.
     * @param shortCode El shortCode generado.
     * @param url La url que se desea acortar.
     * @param webName El nombre de la web.
     * @param linkType El tipo de enlace acortado.
     * @param userId Identificador del usuario que crea la url acortada.
     * @param data1 Value of data1
     * @return Los datos de la url acortada.
     * @throws ErrorException En caso de que no se consiga generar la url acortada.
     */
    public BarneyData generateShortURL(final String shortCode, final String url, String webName, LinkType linkType, String userId, String data1) throws ErrorException {
        int attempts = 0;
        do {
            try {
                return generateOnAnyServer(shortCode, url, webName, linkType, userId, data1);
            } catch (ShortCodeDuplicatedException e) {
                if (++attempts > BarneyServerHelper.MAX_ATTEMPTS) {
                    LOGGER.error("Made " + attempts + " attempts to create the url shortCode = " + shortCode + " to the url = " + url);
                    throw new ErrorException(CodeErrorType.ILEGAL_SHORT_CODE_CREATION);
                } else {
                    LOGGER.warn(attempts + " attempts to create the url shortCode = " + shortCode + " to the url = " + url);
                }
            }
        } while (true);
    }

    /**
     * @return the barneys
     */
    public List<BarneyServer> getBarneys() {
        return barneys;
    }

    /**
     * @param barneys the barneys to set
     */
    public void setBarneys(final List<BarneyServer> barneys) {
        this.barneys = barneys;
    }
}
