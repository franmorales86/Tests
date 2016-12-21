/**
 * Project: RecESDriver
 * Created by: raulanatol at 20/04/2013 14:06:31
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.esdriver;

import com.reclabs.recomendar.common.helpers.types.CollectionHelper;
import com.reclabs.recomendar.esdriver.actions.ESFind;
import com.reclabs.recomendar.esdriver.actions.queries.ESBoolQuery;
import com.reclabs.recomendar.esdriver.actions.queries.ESQueryStringQuery;
import com.reclabs.recomendar.esdriver.actions.queries.generics.ESWildcardQuery;
import com.reclabs.recomendar.esdriver.index.IndexType;
import com.reclabs.recomendar.esdriver.types.BoolQueryField;
import com.reclabs.recomendar.esdriver.types.BooldFieldType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertFalse;


/**
 * TestSuite de la clase {@link ESDriver}
 * @author raulanatol
 */
public class ESDriverTest {
    private static final int PORT = 9200;
    private static final String HOST = "http://essearch.recomendar.com";
    /**
     * Driver a testear.
     */
    private ESDriver classUnderTest;

    /**
     * Inicialización del driver.
     */
    @Before
    public void initDriver() {
        classUnderTest = new ESDriverImpl(HOST, PORT);
    }

    /**
     * Hacemos la prueba de la búsquea de tags.
     */
    @Test
    public void searchTags() {
        List<BoolQueryField> where = new ArrayList<>();
        where.add(new BoolQueryField(new ESWildcardQuery("tag.name", "*a*"), BooldFieldType.MUST));
        ESFind query = new ESFind(IndexType.TAG_INDEX, new ESBoolQuery(where));
        Collection<Object> values = classUnderTest.searchByQueryList(query, Object.class);
        assertFalse(CollectionHelper.isEmpty(values));
    }

    /**
     * Buscamos los tags de un nombre dado pero solo obtenemos el name del mismo
     */
    @Test
    public void searchTagsNameByQuery() {
        List<String> fields = Arrays.asList("name");
        List<BoolQueryField> where = new ArrayList<>();
        where.add(new BoolQueryField(new ESWildcardQuery("tag.name", "*a*"), BooldFieldType.MUST));
        ESFind query = new ESFind(IndexType.TAG_INDEX, fields, new ESBoolQuery(where));
        Collection<Object> result = classUnderTest.searchByQueryList(query, Object.class);
        assertFalse(CollectionHelper.isEmpty(result));
    }

    /**
     * Realizamos la búsqueda sobre items con mutievaluaciones.
     */
    @Test
    public void searchItemMultiEvaluatedByQuery() {
        //TODO
//        MultiEvalList<String, String> whereClause = new MultiEvalList<>();
//        whereClause.addElement("name", "*a*");
        //FIXME no tiene más parámetros :( whereClause.addElement("description", "*a*");

//        ESFind query = new ESFind(IndexType.TAG_INDEX, whereClause);
//        Collection<Object> result = classUnderTest.searchByQueryList(query, Object.class);
//        assertFalse(CollectionHelper.isEmpty(result));
    }

    /**
     * Realizamos la búsqueda sobre tags con multievaluaciones y especificando los campos de resultado.
     */
    @Test
    public void searchTagsNameByMultiWhere() {
        //TODO
//        MultiEvalList<String, String> whereClause = new MultiEvalList<>();
//        whereClause.addElement("name", "*a*");
//        FIXME no tiene más parámetros :( whereClause.addElement("description", "*a*");
//
//        ESFind query = new ESFind(IndexType.TAG_INDEX, whereClause);
//        Collection<Object> result = classUnderTest.searchByQueryList(query, Object.class);
//        assertFalse(CollectionHelper.isEmpty(result));
    }

    /**
     * Realizmos una búsqueda de todos los tags con a y obtenemos todos los nombres en un array.
     */
    @Test
    public void searchSimpleFieldByQueryListTest() {
        //TODO
//        MultiEvalList<String, String> whereClause = new MultiEvalList<>();
//        whereClause.addElement("name", "*a*");

//        ESFind query = new ESFind(IndexType.TAG_INDEX, whereClause);
//        Collection<String> result = classUnderTest.searchSimpleFieldByQueryList(query, String.class, "name");
//        assertFalse(CollectionHelper.isEmpty(result));
    }

    /**
     * Realizmos una búsqueda de todos los item con a y obtenemos todos los nombres en un array.
     */
    @Test
    public void searchItemsSimpleFieldByQueryListTest() {
        //TODO
//        MultiEvalList<String, String> whereClause = new MultiEvalList<>();
//        whereClause.addElement("name", "*a*");
//        whereClause.addElement("description", "*a*");

//        ESFind query = new ESFind(IndexType.ITEM_INDEX, whereClause);
//        Collection<String> result = classUnderTest.searchSimpleFieldByQueryList(query, String.class, "name");
//        assertFalse(CollectionHelper.isEmpty(result));
    }

    /**
     * Search multi fields with wildcard
     */
    @Test
    public void searchItemsNameByMultiWhere() {
        //TODO
//        MultiEvalList<String, String> whereClause = new MultiEvalList<>();
//        whereClause.addElement("name", "*a*");
//        whereClause.addElement("description", "*a*");
//        ESFind query = new ESFind(IndexType.ITEM_INDEX, whereClause);
//        Collection<Object> result = classUnderTest.searchByQueryList(query, Object.class);
//        assertFalse(CollectionHelper.isEmpty(result));
    }

    @Test
    public void eSFindTest() {
        List<BoolQueryField> whereClause = new ArrayList<>();
        whereClause.add(new BoolQueryField(new ESWildcardQuery("item.name", "*comedero*"), BooldFieldType.MUST));
        whereClause.add(new BoolQueryField(new ESWildcardQuery("item.description", "*a*"), BooldFieldType.MUST));

        ESFind find = new ESFind(IndexType.ITEM_INDEX, new ESBoolQuery(whereClause));
        Collection<Object> result = classUnderTest.searchByQueryList(find, Object.class);
        assertNotNull(result);
    }

    /**
     *
     */
    @Test
    public void eSFindCategoryHogarTest() {
        List<BoolQueryField> whereClause = new ArrayList<>();
        whereClause.add(new BoolQueryField(new ESQueryStringQuery("item.category", "*Hogar*"), BooldFieldType.MUST));

        ESFind find = new ESFind(IndexType.ITEM_INDEX, new ESBoolQuery(whereClause));
        Collection<Object> result = classUnderTest.searchByQueryList(find, Object.class);
        assertNotNull(result);
    }

    /**
     * @see ESDriver#searchByQueryList(com.reclabs.recomendar.esdriver.actions.ESFind, Class)
     */
    @Test
    public void esFindUserIndexExist() {
        List<BoolQueryField> whereClause = new ArrayList<>();
        whereClause.add(new BoolQueryField(new ESQueryStringQuery("user.username", "*a*"), BooldFieldType.MUST));
        ESFind find = new ESFind(IndexType.USER_INDEX, new ESBoolQuery(whereClause));
        Collection<Object> result = classUnderTest.searchByQueryList(find, Object.class);
        Assert.assertNotNull(result);
    }
}