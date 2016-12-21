/**
 * Project: RecCommon
 * Created by: fjmorales at 04/12/2012 21:04:30
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.common.helpers.types;

import com.reclabs.recomendar.common.exceptions.RecIllegalArgumentException;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * @author raulanatol
 */
public class TestStringHelper {

    /**
     * @see StringHelper#isAnyEmpty(String...)
     */
    @Test
    public void isAnyEmptyWithNullValues() {
        assertTrue(StringHelper.isAnyEmpty((String[]) null));
    }

    /**
     * Probamos que el método de isAnyEmpty funciona correctamente.
     * @see StringHelper#isAnyEmpty(String...)
     */
    @Test
    public void isAnyEmptyTest() {
        assertTrue(StringHelper.isAnyEmpty("uno", "loslo", "asdad", "", "asdadasd"));
        assertTrue(StringHelper.isAnyEmpty("uno", null, "asdad", "", "asdadasd"));
        assertTrue(StringHelper.isAnyEmpty("", null, "asdad", "", "asdadasd"));
        assertFalse(StringHelper.isAnyEmpty("asdasd", "null", "asdad", "12626", "asdadasd"));
        assertFalse(StringHelper.isAnyEmpty("uno", "dos", "true"));
        assertFalse(StringHelper.isAnyEmpty("uno"));
        assertTrue(StringHelper.isAnyEmpty((String[]) null));
        assertTrue(StringHelper.isAnyEmpty(""));
    }

    /**
     * Probamos que funcione correctamente el método de starWith
     * @see StringHelper#startWith(String, String)
     */
    @Test
    public void startWith() {
        assertTrue(StringHelper.startWith("-f", "-fRecomendar"));
        assertFalse(StringHelper.startWith(" ", " -fRecomendar"));
        assertFalse(StringHelper.startWith("", " -fRecomendar"));
        assertFalse(StringHelper.startWith("", "-fRecomendar"));
        assertFalse(StringHelper.startWith("-f", "-gRecomendar"));
        assertFalse(StringHelper.startWith(null, "-gRecomendar"));
        assertFalse(StringHelper.startWith("-g", null));
    }

    /**
     * @see StringHelper#extractWords(String, String)
     */
    @Test
    public void extractWordsSpaceSeparatorTest() {
        String[] expected1 = {"uno", "dos", "tres"};
        assertThat(StringHelper.extractWords("uno dos tres", " "), is(expected1));
    }

    /**
     * @see StringHelper#extractWords(String, String)
     */
    @Test
    public void extractWordsWithVariableSpaceSeparatorTest() {
        String[] expected1 = {"uno", "dos", "trescuatro"};
        assertThat(StringHelper.extractWords("uno    dos  trescuatro", " "), is(expected1));
    }

    /**
     * @see StringHelper#extractWords(String, String)
     */
    @Test
    public void extractNullWordsWithVariableSpaceSeparatorTest() {
        String[] expected1 = {};
        assertThat(StringHelper.extractWords(null, " "), is(expected1));
        assertThat(StringHelper.extractWords("uno, dos tres", null), is(expected1));
    }

    /**
     * @see StringHelper#getSubstringFrom(String, String)
     */
    @Test
    public void getSubstringFromTest() {
        assertThat(StringHelper.getSubstringFrom("basura-f#recomendar", "-f"), is("-f#recomendar"));
    }

    /**
     * @see StringHelper#replaceToken(String, String, String)
     */
    @Test
    public void replaceTokenTest() {
        assertThat(StringHelper.replaceToken("viaje-a-nepal", "-", " "), is("viaje a nepal"));
        assertThat(StringHelper.replaceToken("viaje-a--nepal", "-", " "), is("viaje a  nepal"));
    }

    /**
     * @see StringHelper#removeLeftZeros(String)
     */
    @Test
    public void removeLeftZerosTest() {
        assertThat(StringHelper.removeLeftZeros("00000000012"), is("12"));
        assertThat(StringHelper.removeLeftZeros("100000000012"), is("100000000012"));
    }

    /**
     * @see StringHelper#lastIndexOf(String, String)
     */
    @SuppressWarnings("boxing")
    @Test
    public void lastIndexOfTest() {
        assertThat(StringHelper.lastIndexOf("la casa de la vieja", "la"), is(11));
        assertThat(StringHelper.lastIndexOf("la casa de la vieja", "la c"), is(0));
        assertThat(StringHelper.lastIndexOf("texto que no aparece", "la casa"), is(-1));
    }

    /**
     * @see StringHelper#lastIndexOf(String, String)
     */
    @SuppressWarnings("boxing")
    @Test(expected = IllegalArgumentException.class)
    public void lastIndexOfNullValuesTest() {
        assertThat(StringHelper.lastIndexOf(null, "la c"), is(0));
    }

    /**
     * @see StringHelper#lastIndexOf(String, String)
     */
    @SuppressWarnings("boxing")
    @Test(expected = IllegalArgumentException.class)
    public void lastIndexOfNullSearchValueTest() {
        assertThat(StringHelper.lastIndexOf("texto que no aparece", null), is(-1));
    }

    /**
     * @throws Exception
     * @see StringHelper#replaceLast(String, String, String)
     */
    @Test
    public void replaceLastTest() throws Exception {
        assertThat(StringHelper.replaceLast("texto a reemplazar una vez reemplazar esto", "reemplazar", "cambiado"), is("texto a reemplazar una vez cambiado esto"));
        assertThat(StringHelper.replaceLast("lala lola lala lola lala lala", "lala", "lola"), is("lala lola lala lola lala lola"));
        assertThat(StringHelper.replaceLast("lila lola lala lola lala lola", "lila", "lala"), is("lala lola lala lola lala lola"));
        assertThat(StringHelper.replaceLast("http://a0.twimg.com/profile_images/2577570444/q2dydovu0wo9wl37prn9_normal.jpeg", "_normal.", "_reasonably_small."),
                is("http://a0.twimg.com/profile_images/2577570444/q2dydovu0wo9wl37prn9_reasonably_small.jpeg"));

    }

    /**
     * @see StringHelper#limit(String, int)
     */
    @Test
    public void limitTest() {
        assertThat(StringHelper.limit("0123456789", 5), is("01234..."));
        assertThat(StringHelper.limit("0123456789", 15), is("0123456789"));
    }

    /**
     * @see StringHelper#limit(String, int)
     */
    @Test(expected = IllegalArgumentException.class)
    public void limitNullValueTest() {
        assertThat(StringHelper.limit(null, 5), is("-1"));
    }

    /**
     * @see StringHelper#toLowerCase(java.util.List)
     */
    @Test
    public void toLowerCaseTest() {
        assertThat(StringHelper.toLowerCase(Arrays.asList("aaa", "bbb", "ccc", "ddd")), is(Arrays.asList("aaa", "bbb", "ccc", "ddd")));
        assertThat(StringHelper.toLowerCase(Arrays.asList("AAA", "BBB", "CCC", "DDD")), is(Arrays.asList("aaa", "bbb", "ccc", "ddd")));
        assertThat(StringHelper.toLowerCase(Arrays.asList("aAa", "bBb", "cCc", "dDd")), is(Arrays.asList("aaa", "bbb", "ccc", "ddd")));
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void extractWithNullMainString() {
        StringHelper.extract(null, "AAA");
    }

    @Test
    public void extractWithEmptyMainString() {
        assertThat(StringHelper.extract("", "AAA"), is(""));
    }

    @Test
    public void extractWithNotContainsDiffOnMainString() {
        assertThat(StringHelper.extract("BBB", "AAA"), is("BBB"));
    }

    @Test
    public void extractWithContainsDiffOnMainString() {
        assertThat(StringHelper.extract("AAABBB", "AAA"), is("BBB"));
    }

    @Test
    public void extractWithContainsMultipleDiffOnMainString() {
        assertThat(StringHelper.extract("AAABBBAAA", "AAA"), is("BBB"));
    }

    @Test
    public void extractWithNullDiff() {
        assertThat(StringHelper.extract("AAABBBAAA", null), is("AAABBBAAA"));
    }

    @Test
    public void extractWithEmptyDiff() {
        assertThat(StringHelper.extract("AAABBBAAA", ""), is("AAABBBAAA"));
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void isAnyEqualsNullCompareString() {
        assertFalse(StringHelper.isAnyEquals(null, "a", "b", "c"));
    }

    @Test
    public void isAnyEqualsNullValues() {
        assertFalse(StringHelper.isAnyEquals("search", (String) null));
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void isAnyEqualsNullBothParameters() {
        assertTrue(StringHelper.isAnyEquals(null, (String) null));
    }

    @Test
    public void isAnyEqualsSimpleData() {
        assertTrue(StringHelper.isAnyEquals("a", "b", "c", "a", "d"));
    }

    @Test
    public void isAnyEqualsSimpleDataIgnoreCaseSensitive() {
        assertTrue(StringHelper.isAnyEquals("a", "b", "c", "A", "d"));
    }

    @Test
    public void isAnyEqualsFalseResult() {
        assertFalse(StringHelper.isAnyEquals("a", "b", "c", "d"));
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void firstLetterToLowerCaseWithNullWord() throws Exception {
        StringHelper.firstLetterToLowerCase(null);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void firstLetterToLowerCaseWithEmptyWord() throws Exception {
        StringHelper.firstLetterToLowerCase(" ");
    }

    @Test
    public void firstLetterToLowerCaseWithNormalWord() throws Exception {
        assertThat(StringHelper.firstLetterToLowerCase("NormalWord"), is("normalWord"));
    }

    @Test
    public void firstLetterToLowerCaseWithNoModifiedNecessaryWord() throws Exception {
        assertThat(StringHelper.firstLetterToLowerCase("normalWord"), is("normalWord"));
    }

    @Test
    public void firstLetterToLowerCaseWithSentence() throws Exception {
        assertThat(StringHelper.firstLetterToLowerCase("Normal Word"), is("normal Word"));
    }

    @Test
    public void isPalindromeTest() {
        assertTrue(StringHelper.isPalindrome("aba"));
        assertTrue(StringHelper.isPalindrome("adda"));
        assertTrue(StringHelper.isPalindrome("aaaa"));
        assertTrue(StringHelper.isPalindrome("aaaaa"));
        assertTrue(StringHelper.isPalindrome("ababa"));
        assertTrue(StringHelper.isPalindrome("abaaba"));
        assertFalse(StringHelper.isPalindrome("baba"));
        assertTrue(StringHelper.isPalindrome("b"));
        assertFalse(StringHelper.isPalindrome("barba"));
        assertFalse(StringHelper.isPalindrome(""));
        assertFalse(StringHelper.isPalindrome(null));
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void toBooleanWithNullParameter() throws Exception {
        StringHelper.toBoolean(null);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void toBooleanWithEmptyParameter() throws Exception {
        StringHelper.toBoolean("");
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void toBooleanWithEmptyParameterValue2() throws Exception {
        StringHelper.toBoolean("   ");
    }

    @Test
    public void toBooleanWithFalseValues() throws Exception {
        assertFalse(StringHelper.toBoolean("false"));
        assertFalse(StringHelper.toBoolean("False"));
        assertFalse(StringHelper.toBoolean("0"));
        assertFalse(StringHelper.toBoolean("adsasd"));
    }

    @Test
    public void toBooleanWithTrueValues() throws Exception {
        assertTrue(StringHelper.toBoolean("true"));
        assertTrue(StringHelper.toBoolean("True"));
        assertTrue(StringHelper.toBoolean("1"));
    }

    @Test
    public void containsAnyWithSimpleData() throws Exception {
        assertTrue(StringHelper.containsAny("Uno", "Dos", "tres", "Uno"));
    }

    @Test
    public void containsAnyWithSimpleDataWithCaseSensitive() throws Exception {
        assertTrue(StringHelper.containsAny("Uno", "Dos", "uno", "no"));
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void containsAnyWithSimpleDataWithNullSourceString() throws Exception {
        assertTrue(StringHelper.containsAny(null, "Dos", "uno", "no"));
    }

    @Test
    public void containsAnyWithSimpleDataWithEmptySourceString() throws Exception {
        assertFalse(StringHelper.containsAny("   ", "Dos", "uno", "no"));
    }

    @Test
    public void containsAnyWithSimpleDataWithNullParameters() throws Exception {
        assertFalse(StringHelper.containsAny("Uno", "Dos", null, "noj"));
    }

    @Test
    public void containsWithSimpleData() throws Exception {
        assertTrue(StringHelper.contains("Uno", "Un"));
    }

    @Test
    public void containsWithEmptyParameterTrueResult() throws Exception {
        assertTrue(StringHelper.contains("Uno", ""));
    }

    @Test
    public void containsWithEmptyParameterFalseResult() throws Exception {
        assertFalse(StringHelper.contains("Uno", "   "));
    }

    @Test
    public void containsWithSimpleDataFalseResult() throws Exception {
        assertFalse(StringHelper.contains("Uno", "usn"));
    }

    @Test
    public void containsWithEmptySource() throws Exception {
        assertFalse(StringHelper.contains("  ", "usn"));
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void containsWithNullSource() throws Exception {
        assertFalse(StringHelper.contains(null, "usn"));
    }

    @Test
    public void containsWithNullParameter() throws Exception {
        assertFalse(StringHelper.contains("Uno", null));
    }

    @Test
    public void containsWithEmptyParameter() throws Exception {
        assertFalse(StringHelper.contains("Uno", "   "));
    }

    @Test
    public void equalsWithNormalData() throws Exception {
        assertTrue(StringHelper.equals("foo", "foo"));
    }

    @Test
    public void equalsWithEmptyValue() throws Exception {
        assertFalse(StringHelper.equals("", "foo"));
        assertFalse(StringHelper.equals("foo", ""));
        assertTrue(StringHelper.equals(" ", " "));
        assertFalse(StringHelper.equals(" ", "    "));
    }

    @Test
    public void equalsWithNullValue() throws Exception {
        assertFalse(StringHelper.equals(null, "foo"));
        assertFalse(StringHelper.equals("foo", null));
        assertTrue(StringHelper.equals(null, null));
    }

    @Test
    public void equalsWithCaseSensitive() throws Exception {
        assertTrue(StringHelper.equals("Foo", "foo", false));
        assertFalse(StringHelper.equals("Foo", "foo", true));
    }

    @Test
    public void equalsWithCaseSensitiveAndNull() throws Exception {
        assertFalse(StringHelper.equals("Foo", null, false));
        assertFalse(StringHelper.equals(null, "foo", false));
        assertFalse(StringHelper.equals("Foo", null, true));
        assertFalse(StringHelper.equals(null, "foo", true));
    }
}
