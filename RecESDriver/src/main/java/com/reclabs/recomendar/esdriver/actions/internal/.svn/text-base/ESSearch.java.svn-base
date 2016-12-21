/**
 * Project: RecESDriver
 * Created by: raulanatol at 28/04/13 11:12
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.esdriver.actions.internal;

import io.searchbox.core.Search;
import io.searchbox.core.search.sort.Sort;

import java.util.List;

/**
 * @author raulanatol
 */
public class ESSearch extends Search {

    private String pathToResult;

    /**
     * @param query
     * @param pathToResult
     */
    public ESSearch(String query, String pathToResult) {
        super(query);
        this.pathToResult = pathToResult;
    }

    /**
     * @param query
     * @param pathToResult
     */
    public ESSearch(String query, List<Sort> sort, String pathToResult) {
        super(query, sort);
        this.pathToResult = pathToResult;
    }

    @Override
    public String getPathToResult() {
        return pathToResult;
    }
}
