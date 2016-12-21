/**
 * Project: RecESDriver
 * Created by: fjmorales at 04/04/2013 12:42:13
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.esdriver;

import com.reclabs.recomendar.esdriver.actions.ESFind;
import io.searchbox.Action;
import io.searchbox.client.JestResult;

import java.util.Collection;

/**
 * @author fjmorales
 */
public interface ESDriver {
    /**
     * Dada una acción ESFind obtenemos el listado de resultados de tipo T que contenga dicha acción.
     * @param query La consulta a ejecutar.
     * @param resultClass La clase que da como resultado.
     * @param <T> La clase a tratar.
     * @return El listado de items de resultado.
     */
    <T> Collection<T> searchByQueryList(ESFind query, Class<T> resultClass);

    /**
     * Dada una acción ESFind obtenemos el listado de resultado de tipo T del campo fieldName que contenga dicha acción.
     * @param query La consulta a ajecutar.
     * @param resultClass La clase que da como resultado.
     * @param fieldName El nombre de parámetro a mostrar en el listado.
     * @param <T> La clase a tratar.
     * @return Listado de items de resultado.
     */
    <T> Collection<T> searchSimpleFieldByQueryList(ESFind query, Class<T> resultClass, String fieldName);

    /**
     * @param action
     * @return
     */
    JestResult executeAction(Action action);
}
