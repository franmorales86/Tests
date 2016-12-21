/**
 * Project: RecCommon
 * Created by: fjmorales at 04/12/2012 21:07:56
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.common.helpers.types;

import com.reclabs.recomendar.common.exceptions.RecIllegalArgumentException;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * TestSuite para la clase @see {@link CollectionHelper}
 * @author fjmorales
 */
public class TestCollectionHelper {
    /**
     * Caso de prueba para probar la funcionalidad de @link CollectionHelper#isEmpty(Collection)
     */
    @Test
    public void isEmptyTest() {
        assertTrue(CollectionHelper.isEmpty(null));
    }

    /**
     * @see CollectionHelper#list2String(String, Collection)
     */
    @Test
    public void testList2StringWithSimpleData() {
        Collection<String> items1 = Arrays.asList("uno", "dos", "tres");
        assertThat(CollectionHelper.list2String("#", items1), is("uno#dos#tres"));
        assertThat(CollectionHelper.list2String("#", Arrays.asList("uno", "dos", "tres")), is("uno#dos#tres"));
        assertThat(CollectionHelper.list2String(" # ", Arrays.asList("uno", "dos", "tres")), is("uno # dos # tres"));
        assertThat(CollectionHelper.list2String(" # ", Arrays.asList("uno", "dos", null)), is("uno # dos"));
    }

    /**
     * @see CollectionHelper#list2String(String, Collection)
     */
    @Test(expected = NullPointerException.class)
    public void testList2StringWithNullSeparatorValue() {
        assertThat(CollectionHelper.list2String(null, Arrays.asList("uno", "dos", "tres")), is("unodostres"));
        assertThat(CollectionHelper.list2String(null, Arrays.asList("")), is(""));
    }

    /**
     * @see CollectionHelper#list2String(String, Collection)
     */
    @Test(expected = NullPointerException.class)
    public void testList2StringWithNullValues() {
        assertThat(CollectionHelper.list2String("#", null), is("unodostres"));
    }

    /**
     * @see CollectionHelper#list2String(String, Collection, String, String)
     */
    @Test
    public void testList2StringWithStartEndTokens() {
        assertThat(CollectionHelper.list2String("#", (Arrays.asList("uno", "dos", "tres")), "A", "Z"), is("AunoZ#AdosZ#AtresZ"));
        assertThat(CollectionHelper.list2String("#", (Arrays.asList("uno")), "A", "Z"), is("AunoZ"));
        assertThat(CollectionHelper.list2String("#", (Arrays.asList("")), "A", "Z"), is(""));
    }

    /**
     * @see CollectionHelper#list2String(String, Collection, String, String, int)
     */
    public void testList2StringWithStartEndTokensLimit() {
        Collection<String> items = Arrays.asList("uno", "dos", "tres", "cuatro");
        Collection<String> expectedCollection = Arrays.asList("'uno','dos'", "'tres','cuatro'");
        assertThat(CollectionHelper.list2String(", ", items, "'", "'", 2), is(expectedCollection));
    }

    /**
     * @see CollectionHelper#list2String(String, Collection, String, String, int)
     */
    @Test
    public void list2StringWithSimpleLimitTest() {
        Collection<String> idList = new HashSet<>();
        idList.add("125421");
        idList.add("125asdads1");
        List<String> expected = Arrays.asList("'125421','125asdads1'");
        List<String> result = CollectionHelper.list2String(",", idList, "'", "'", 15);
        assertThat(result, is(expected));
    }

    /**
     * @see CollectionHelper#list2String(String, Collection, String, String, int)
     */
    @Test
    public void list2StringWithLongLimitTest() {
        List<String> idList = Arrays.asList("1", "2", "3", "4", "5");
        List<String> expected = Arrays.asList("'1','2'", "'3','4'", "'5'");
        List<String> result = CollectionHelper.list2String(",", idList, "'", "'", 2);
        assertThat(result, is(expected));
    }

    /**
     * @see CollectionHelper#equals(Object)
     */
    @Test
    public void equalsWithNullTest() {
        Collection<String> collection1 = null;
        Collection<String> collection2 = null;
        assertTrue(CollectionHelper.equals(collection1, collection2, String.class));
    }

    /**
     * @see CollectionHelper#equals(Object)
     */
    @Test
    public void equalsWithFirstMemberNullTest() {
        Collection<String> collection1 = null;
        Collection<String> collection2 = new ArrayList<>();
        assertFalse(CollectionHelper.equals(collection1, collection2, String.class));
    }

    /**
     * @see CollectionHelper#equals(Object)
     */
    @Test
    public void equalsWithSecondMemberNullTest() {
        Collection<String> collection1 = new ArrayList<>();
        Collection<String> collection2 = null;
        assertFalse(CollectionHelper.equals(collection1, collection2, String.class));
    }

    /**
     * @see CollectionHelper#equals(Object)
     */
    @Test
    public void equalsWithTwoMembersEmptyTest() {
        Collection<String> collection1 = new ArrayList<>();
        Collection<String> collection2 = new ArrayList<>();
        assertTrue(CollectionHelper.equals(collection1, collection2, String.class));
    }

    /**
     * @see CollectionHelper#diffCollectionsAndGetOne(java.util.Collection, java.util.Collection)
     */
    @Test(expected = RecIllegalArgumentException.class)
    public void diffCollectionsAndGetOneWithNullParameters() {
        CollectionHelper.diffCollectionsAndGetOne(null, null);
    }

    /**
     * @see CollectionHelper#diffCollectionsAndGetOne(java.util.Collection, java.util.Collection)
     */
    @Test(expected = RecIllegalArgumentException.class)
    public void diffCollectionsAndGetOneWithMinuendNullParameters() {
        CollectionHelper.diffCollectionsAndGetOne(null, Arrays.asList("one", "two"));
    }

    /**
     * @see CollectionHelper#diffCollectionsAndGetOne(java.util.Collection, java.util.Collection)
     */
    @Test(expected = RecIllegalArgumentException.class)
    public void diffCollectionsAndGetOneWithMinuendEmptyParameters() {
        CollectionHelper.diffCollectionsAndGetOne(new ArrayList<String>(), Arrays.asList("one", "two"));
    }

    /**
     * @see CollectionHelper#diffCollectionsAndGetOne(java.util.Collection, java.util.Collection)
     */
    @Test
    public void diffCollectionsAndGetOneWithSubtrahendNullParameters() {
        List<String> test = Arrays.asList("one", "two");
        String result = CollectionHelper.diffCollectionsAndGetOne(test, null);
        assertTrue(StringHelper.equals(result, "one") || StringHelper.equals(result, "two"));
    }

    /**
     * @see CollectionHelper#diffCollectionsAndGetOne(java.util.Collection, java.util.Collection)
     */
    @Test
    public void diffCollectionsAndGetOneWithNormalValues() {
        List<String> minuend = Arrays.asList("one", "two");
        List<String> subtrahend = Arrays.asList("two", "three");
        String result = CollectionHelper.diffCollectionsAndGetOne(minuend, subtrahend);
        assertThat(result, is("one"));
    }

    /**
     * @see CollectionHelper#diffCollectionsAndGetOne(java.util.Collection, java.util.Collection)
     */
    @Test(expected = RecIllegalArgumentException.class)
    public void diffCollectionsAndGetOneWithZeroResultValues() {
        List<String> minuend = Arrays.asList("one", "two");
        List<String> subtrahend = Arrays.asList("two", "one");
        CollectionHelper.diffCollectionsAndGetOne(minuend, subtrahend);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void diffCollectionsWithNullMinuend() {
        CollectionHelper.diffCollection(null, Arrays.asList("two", "three"));
    }

    @Test
    public void diffCollectionsWithEmptyMinuend() {
        Collection<String> subtrahend = Arrays.asList("two", "three");
        Collection<String> minuend = new ArrayList<>();
        CollectionHelper.diffCollection(minuend, subtrahend);
    }

    @Test
    public void diffCollectionsWithNullSubtrahend() {
        Collection<String> minuend = Arrays.asList("two", "three");
        assertThat(CollectionHelper.diffCollection(minuend, null), is(minuend));
    }

    @Test
    public void diffCollectionsWithEmptySubtrahend() {
        Collection<String> minuend = Arrays.asList("two", "three");
        assertThat(CollectionHelper.diffCollection(minuend, new ArrayList<String>()), is(minuend));
    }

    @Test
    public void diffCollectionsWithNormalData() {
        Collection<String> subtrahend = Arrays.asList("two", "three");
        Collection<String> minuend = Arrays.asList("one", "two", "twomedium", "three", "four");
        Collection<String> result = Arrays.asList("one", "twomedium", "four");
        assertThat(CollectionHelper.diffCollection(minuend, subtrahend), is(result));
    }

    @Test
    public void diffCollectionsWithEqualsParameters() {
        Collection<String> subtrahend = Arrays.asList("two", "three");
        Collection<String> minuend = Arrays.asList("two", "three");
        Collection<String> result = new ArrayList<>();
        assertThat(CollectionHelper.diffCollection(minuend, subtrahend), is(result));
    }

    @Test
    public void diffCollectionsWithDifferentsParameters() {
        Collection<String> subtrahend = Arrays.asList("two", "three");
        Collection<String> minuend = Arrays.asList("twomedium", "four");
        Collection<String> result = Arrays.asList("twomedium", "four");
        assertThat(CollectionHelper.diffCollection(minuend, subtrahend), is(result));
    }

    protected class URLPath {
        private String name;
        private int value;

        public URLPath(String name, int value) {
            this.name = name;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof URLPath)) return false;

            URLPath urlPath = (URLPath) o;

            return value == urlPath.value && !(name != null ? !name.equals(urlPath.name) : urlPath.name != null);

        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + value;
            return result;
        }

        @Override
        public String toString() {
            return "URLPath{" +
                    "name='" + name + '\'' +
                    ", value=" + value +
                    '}';
        }
    }

    @Test
    public void diffCollectionsWithComplexDataParameters() {
        Collection<URLPath> minuend = Arrays.asList(new URLPath("B", 2), new URLPath("C", 3));
        Collection<URLPath> subtrahend = Arrays.asList(new URLPath("A", 1), new URLPath("B", 2));
        Collection<URLPath> result = Arrays.asList(new URLPath("C", 3));
        assertThat(CollectionHelper.diffCollection(minuend, subtrahend), is(result));
    }

    @Test
    public void diffCollectionsWithComplexDataParametersAndNoSubtract() {
        Collection<URLPath> minuend = Arrays.asList(new URLPath("B", 2), new URLPath("C", 3));
        Collection<URLPath> subtrahend = Arrays.asList(new URLPath("A", 1), new URLPath("H", 2));
        Collection<URLPath> result = Arrays.asList(new URLPath("B", 2), new URLPath("C", 3));
        assertThat(CollectionHelper.diffCollection(minuend, subtrahend), is(result));
    }
}
