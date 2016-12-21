/**
 * Project: RecCommon
 * Created by: raulanatol at 21/05/13 10:52
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.common.helpers;

import com.reclabs.recomendar.common.exceptions.RecIllegalArgumentException;
import com.reclabs.recomendar.common.helpers.types.DateHelper;
import com.reclabs.recomendar.common.types.DatePrecisionType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Arrays;
import java.util.Date;

/**
 * @author raulanatol
 * @see ParameterHelper
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(DateHelper.class)
public class ParameterHelperTest {

    /**
     * @see ParameterHelper#assertEmpty(String)
     */
    @Test(expected = RecIllegalArgumentException.class)
    public void assertEmptyWithNullValue() {
        ParameterHelper.assertEmpty(null);
    }

    /**
     * @see ParameterHelper#assertEmpty(String)
     */
    @Test(expected = RecIllegalArgumentException.class)
    public void assertEmptyWithEmptyValue() {
        ParameterHelper.assertEmpty("");
    }

    /**
     * @see ParameterHelper#assertEmpty(String)
     */
    @Test
    public void assertEmptyWithValidValue() {
        ParameterHelper.assertEmpty("Valid");
    }

    /**
     * @see ParameterHelper#assertAnyEmpty(String...)
     */
    @Test(expected = RecIllegalArgumentException.class)
    public void assertAnyEmptyWithNullValue() {
        ParameterHelper.assertAnyEmpty((String[]) null);
    }

    /**
     * @see ParameterHelper#assertAnyEmpty(String...)
     */
    @Test(expected = RecIllegalArgumentException.class)
    public void assertAnyEmptyWithEmptyValue() {
        ParameterHelper.assertAnyEmpty("");
    }

    /**
     * @see ParameterHelper#assertAnyEmpty(String...)
     */
    @Test(expected = RecIllegalArgumentException.class)
    public void assertAnyEmptyWithAnyNullValue() {
        ParameterHelper.assertAnyEmpty("one", "two", null, "three");
    }

    /**
     * @see ParameterHelper#assertAnyEmpty(String...)
     */
    @Test(expected = RecIllegalArgumentException.class)
    public void assertAnyEmptyWithAnyEmptyValue() {
        ParameterHelper.assertAnyEmpty("one", "two", "", "three");
    }

    /**
     * @see ParameterHelper#assertAnyEmpty(String...)
     */
    @Test
    public void assertAnyEmptyWithValidValue() {
        ParameterHelper.assertAnyEmpty("one", "two", "three");
    }

    /**
     * @see ParameterHelper#assertCollectionEmpty(java.util.Collection)
     */
    @Test(expected = RecIllegalArgumentException.class)
    public void assertCollectionEmptyWithNullValues() {
        ParameterHelper.assertCollectionEmpty(null);
    }

    /**
     * @see ParameterHelper#assertCollectionEmpty(java.util.Collection)
     */
    @Test
    public void assertCollectionEmptyWithEmptyValues() {
        ParameterHelper.assertCollectionEmpty(Arrays.asList("one", ""));
    }

    /**
     * @see ParameterHelper#assertCollectionEmpty(java.util.Collection)
     */
    @Test
    public void assertCollectionEmptyWithValidValues() {
        ParameterHelper.assertCollectionEmpty(Arrays.asList("one", "two"));
    }

    /**
     * @see ParameterHelper#assertNegative(int)
     */
    @Test
    public void assertNegativeWithPositiveValue() {
        ParameterHelper.assertNegative(15);
    }

    /**
     * @see ParameterHelper#assertNegative(int)
     */
    @Test
    public void assertNegativeWithZeroValue() {
        ParameterHelper.assertNegative(0);
    }

    /**
     * @see ParameterHelper#assertNegative(int)
     */
    @Test(expected = RecIllegalArgumentException.class)
    public void assertNegativeWithNegativeValue() {
        ParameterHelper.assertNegative(-13);
    }

    /**
     * @see ParameterHelper#assertZeroOrNegative(int)
     */
    @Test
    public void assertZeroOrNegativeWithPositiveValue() {
        ParameterHelper.assertZeroOrNegative(15);
    }

    /**
     * @see ParameterHelper#assertZeroOrNegative(int)
     */
    @Test(expected = RecIllegalArgumentException.class)
    public void assertZeroOrNegativeWithZeroValue() {
        ParameterHelper.assertZeroOrNegative(0);
    }

    /**
     * @see ParameterHelper#assertZeroOrNegative(int)
     */
    @Test(expected = RecIllegalArgumentException.class)
    public void assertZeroOrNegativeWithNegativeValue() {
        ParameterHelper.assertZeroOrNegative(-190);
    }

    @Test
    public void assertNegativeLongWithPositiveValue() throws Exception {
        ParameterHelper.assertNegative(5L);
    }

    @Test
    public void assertNegativeLongWithZeroValue() throws Exception {
        ParameterHelper.assertNegative(0L);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void assertNegativeLongWithNegativeValue() throws Exception {
        ParameterHelper.assertNegative(-10L);
    }

    @Test
    public void assertZeroOrNegativeLongWithPositiveValue() throws Exception {
        ParameterHelper.assertZeroOrNegative(5L);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void assertZeroOrNegativeLongWithNegativeValue() throws Exception {
        ParameterHelper.assertZeroOrNegative(-15L);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void assertZeroOrNegativeLongWithZeroValue() throws Exception {
        ParameterHelper.assertZeroOrNegative(0L);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void assertNotNullWithNotNullValue() throws Exception {
        ParameterHelper.assertNotNull("NOT_NULL_OBJECT");
    }

    @Test
    public void assertNotNullWithNullValue() throws Exception {
        ParameterHelper.assertNotNull(null);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void assertTrueWithTrueValue() throws Exception {
        ParameterHelper.assertTrue(true);
    }

    @Test
    public void assertTrueWithFalseValue() throws Exception {
        ParameterHelper.assertTrue(false);
    }

    @Test
    public void assertDateAfterNowWithAfterDate() throws Exception {
        Date currentDate = DateHelper.getFixedDate(2010, 10, 5);

        PowerMockito.mockStatic(DateHelper.class);
        Mockito.when(DateHelper.isAfterNow(currentDate, DatePrecisionType.HOUR)).thenReturn(true);

        ParameterHelper.assertDateAfterNow(currentDate, DatePrecisionType.HOUR);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void assertDateAfterNowWithBeforeDate() throws Exception {
        Date currentDate = DateHelper.getFixedDate(2010, 10, 5);

        PowerMockito.mockStatic(DateHelper.class);
        Mockito.when(DateHelper.isAfterNow(currentDate, DatePrecisionType.HOUR)).thenReturn(false);

        ParameterHelper.assertDateAfterNow(currentDate, DatePrecisionType.HOUR);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void assertDateAfterNowWithEqualsDate() throws Exception {
        Date currentDate = DateHelper.getFixedDate(2010, 10, 5);

        PowerMockito.mockStatic(DateHelper.class);
        Mockito.when(DateHelper.isAfterNow(currentDate, DatePrecisionType.HOUR)).thenReturn(false);

        ParameterHelper.assertDateAfterNow(currentDate, DatePrecisionType.HOUR);
    }
}
