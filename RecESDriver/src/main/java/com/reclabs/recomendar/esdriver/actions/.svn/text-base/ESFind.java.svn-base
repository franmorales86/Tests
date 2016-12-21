/**
 * Project: RecESDriver
 * Created by: raulanatol at 27/04/13 17:44
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.esdriver.actions;

import com.reclabs.recomendar.common.helpers.types.CollectionHelper;
import com.reclabs.recomendar.common.helpers.types.NullHelper;
import com.reclabs.recomendar.esdriver.actions.internal.ESAction;
import com.reclabs.recomendar.esdriver.actions.internal.ESQuery;
import com.reclabs.recomendar.esdriver.actions.internal.ESSearch;
import com.reclabs.recomendar.esdriver.index.IndexType;
import io.searchbox.Action;
import io.searchbox.core.search.sort.Sort;

import java.util.ArrayList;
import java.util.List;

/**
 * Search over elasticsearch
 * @author raulanatol
 */
public class ESFind implements ESAction {
    private static final String QUERY_PATTERN = "{%1$s\n\"query\":%2$s,\n\"from\":%3$s,\n\"size\":%4$s}";
    private static final List<Sort> EMPTY_SORT = new ArrayList<>();
    private static final List<String> EMPTY_RESULT_FIELDS = null;
    private static final Integer EMPTY_PAGE = null;
    private static final Integer EMPTY_SIZE = null;

    /**
     * List of resulting fields
     */
    private List<String> resultFields;
    /**
     * Index where the search is performed
     */
    private IndexType index;
    private int from = 0;
    private int size = 20;
    private List<Sort> sorts = new ArrayList<>();
    private ESQuery esQuery;

    /**
     * @param index Where the search is performed
     * @param resultFields fields to return
     * @param esQuery Query to execute
     */
    public ESFind(IndexType index, List<String> resultFields, ESQuery esQuery) {
        this(index, resultFields, EMPTY_SORT, esQuery, EMPTY_PAGE, EMPTY_SIZE);
    }

    /**
     * @param index Where the search is performed
     * @param esQuery Query to execute
     */
    public ESFind(IndexType index, ESQuery esQuery) {
        this(index, EMPTY_RESULT_FIELDS, EMPTY_SORT, esQuery, EMPTY_PAGE, EMPTY_SIZE);
    }

    /**
     * @param index Where the search is performed
     * @param resultFields fields to return
     * @param esQuery Query to execute
     * @param sorts Sort methods
     */
    @SuppressWarnings("unused")
    public ESFind(IndexType index, List<String> resultFields, ESQuery esQuery, List<Sort> sorts) {
        this(index, resultFields, sorts, esQuery, EMPTY_PAGE, EMPTY_SIZE);
    }

    /**
     * Constructor que permite realizar ordenación de los resultados de la búsqueda, asi como incluir los parametros de paginacion
     * @param index Where the search is performed
     * @param esQuery Query to execute
     * @param sorts Filtros de ordenacion
     * @param page Indice de la pagina actual
     * @param size Tamaño de la pagina
     */
    @SuppressWarnings("unused")
    public ESFind(IndexType index, ESQuery esQuery, List<Sort> sorts, Integer page, Integer size) {
        this(index, EMPTY_RESULT_FIELDS, sorts, esQuery, page, size);
    }

    /**
     * Creation of elasticSearch finder with the parameters.
     * @param index Where the search is performed
     * @param esQuery Query to execute
     * @param page Page to return
     * @param size Size to return
     */
    @SuppressWarnings("unused")
    public ESFind(IndexType index, ESQuery esQuery, Integer page, Integer size) {
        this(index, EMPTY_RESULT_FIELDS, EMPTY_SORT, esQuery, page, size);
    }


    /**
     * @param index Where the search is performed
     * @param resultFields fields to return
     * @param esQuery Query to execute
     * @param page Page to return
     * @param size Size to return
     */
    @SuppressWarnings("unused")
    public ESFind(IndexType index, List<String> resultFields, ESQuery esQuery, Integer page, Integer size) {
        this(index, resultFields, EMPTY_SORT, esQuery, page, size);
    }


    /**
     * @param index Where the search is performed
     * @param resultFields fields to return
     * @param esQuery Query to execute
     * @param page Page to return
     * @param size Size to return
     */
    public ESFind(IndexType index, List<String> resultFields, List<Sort> sorts, ESQuery esQuery, Integer page, Integer size) {
        super();
        this.index = index;
        this.resultFields = resultFields;
        this.esQuery = esQuery;
        this.sorts = sorts;
        if (!NullHelper.isAnyNull(page, size)) {
            this.from = page * size;
        }
        if (!NullHelper.isAnyNull(size)) {
            this.size = size;
        }
    }


    @Override
    public Action getAction() {
        String pathToResult = CollectionHelper.isEmpty(resultFields) ? "hits/hits/_source" : "hits/hits/fields";
        ESSearch result = new ESSearch(generateSQL(), sorts, pathToResult);
        result.addIndex(index.getName());
        return result;
    }

    /**
     * Generate the sql of the find action
     * @return the sql string
     */
    protected String generateSQL() {
        String fields = generateFieldsJSON();
        return String.format(QUERY_PATTERN, fields, esQuery.generateQuerySQL(), from, size);
    }

    /**
     * @return JSON fields String
     */
    protected String generateFieldsJSON() {
        String result = "";
        if (!CollectionHelper.isEmpty(resultFields)) {
            StringBuilder fieldBuffer = new StringBuilder();
            for (String field : resultFields) {
                fieldBuffer.append("\"");
                fieldBuffer.append(field);
                fieldBuffer.append("\",");
            }
            String fieldString = fieldBuffer.substring(0, fieldBuffer.length() - 1);
            result = "\"fields\": [" + fieldString + "],";
        }
        return result;
    }
}