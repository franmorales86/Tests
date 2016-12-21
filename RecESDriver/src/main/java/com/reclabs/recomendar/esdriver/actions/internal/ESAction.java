/**
 * Project: RecESDriver
 * Created by: raulanatol at 27/04/13 19:03
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.esdriver.actions.internal;

import io.searchbox.Action;

/**
 * @author raulanatol
 */
public interface ESAction {
    /**
     * Obtenemos los datos del action.
     * @return
     */
    Action getAction();
}
