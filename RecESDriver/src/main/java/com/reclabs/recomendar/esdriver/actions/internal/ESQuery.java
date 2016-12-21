/**
 * Project: RecESDriver
 * Created by: Fran at 27/06/13 15:19
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 * <p/>
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.esdriver.actions.internal;


/**
 * @author fjmorales
 */
public interface ESQuery {

    /**
     * Generate the String SQL query for the search
     * @return String SQL query
     */
    String generateQuerySQL();
}
