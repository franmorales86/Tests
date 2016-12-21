/**
 * Project: RecCommon
 * Created by: fjmorales at 04/12/2012 21:01:47
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.common.helpers.types;

import com.reclabs.recomendar.common.exceptions.RecIllegalArgumentException;
import com.reclabs.recomendar.common.types.DatePrecisionType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(PowerMockRunner.class)
@PrepareForTest(NowDateHelper.class)
public class TestDateHelper {

    @Test(expected = NullPointerException.class)
    public void getDifToSysDateInUnitTimeNullValueTest() {
        DateHelper.getDifToSysDateInUnitTime(null);
    }

    @Test
    public void getDifToSysDateInUnitTimeIsNotNullTest() {
        Date dummyDateSame = new Date();
        assertNotNull(DateHelper.getDifToSysDateInUnitTime(dummyDateSame));

        Calendar dummyCalendarOneDifYear = Calendar.getInstance();
        dummyCalendarOneDifYear.add(Calendar.YEAR, -1);
        assertNotNull(DateHelper.getDifToSysDateInUnitTime(dummyCalendarOneDifYear.getTime()));

        Calendar dummyCalendarMoreOneDifYear = Calendar.getInstance();
        dummyCalendarMoreOneDifYear.add(Calendar.YEAR, -2);
        assertNotNull(DateHelper.getDifToSysDateInUnitTime(dummyCalendarMoreOneDifYear.getTime()));

        Calendar dummyCalendarOneDifMonth = Calendar.getInstance();
        dummyCalendarOneDifMonth.add(Calendar.MONTH, -1);
        assertNotNull(DateHelper.getDifToSysDateInUnitTime(dummyCalendarOneDifMonth.getTime()));

        Calendar dummyCalendarMoreOneDifMonth = Calendar.getInstance();
        dummyCalendarMoreOneDifMonth.add(Calendar.MONTH, -2);
        assertNotNull(DateHelper.getDifToSysDateInUnitTime(dummyCalendarMoreOneDifMonth.getTime()));

        Calendar dummyCalendarOneDifDay = Calendar.getInstance();
        dummyCalendarOneDifDay.add(Calendar.DAY_OF_YEAR, -1);
        assertNotNull(DateHelper.getDifToSysDateInUnitTime(dummyCalendarOneDifDay.getTime()));

        Calendar dummyCalendarMoreOneDifDay = Calendar.getInstance();
        dummyCalendarMoreOneDifDay.add(Calendar.DAY_OF_YEAR, -2);
        assertNotNull(DateHelper.getDifToSysDateInUnitTime(dummyCalendarMoreOneDifDay.getTime()));

        Calendar dummyCalendarOneDifMinute = Calendar.getInstance();
        dummyCalendarOneDifMinute.add(Calendar.MINUTE, -1);
        assertNotNull(DateHelper.getDifToSysDateInUnitTime(dummyCalendarOneDifMinute.getTime()));

        Calendar dummyCalendarMoreOneDifMinute = Calendar.getInstance();
        dummyCalendarMoreOneDifMinute.add(Calendar.MINUTE, -2);
        assertNotNull(DateHelper.getDifToSysDateInUnitTime(dummyCalendarMoreOneDifMinute.getTime()));
    }

    @Test
    public void getDifToSysDateInUnitTimeTest() {
        Calendar dummyCalendarCurrentDate = Calendar.getInstance();
        assertThat(DateHelper.getDifToSysDateInUnitTime(dummyCalendarCurrentDate.getTime()), is("Ahora"));
        SimpleDateFormat sdf = new SimpleDateFormat("d MMM");

        Calendar dummyCalendarOneDifYear = Calendar.getInstance();
        dummyCalendarOneDifYear.add(Calendar.YEAR, -1);
        assertThat(DateHelper.getDifToSysDateInUnitTime(dummyCalendarOneDifYear.getTime()), is(sdf.format(dummyCalendarOneDifYear.getTime())));

        Calendar dummyCalendarMoreOneDifYear = Calendar.getInstance();
        dummyCalendarMoreOneDifYear.add(Calendar.YEAR, -2);
        assertThat(DateHelper.getDifToSysDateInUnitTime(dummyCalendarMoreOneDifYear.getTime()), is(sdf.format(dummyCalendarMoreOneDifYear.getTime())));

        Calendar dummyCalendarOneDifMonth = Calendar.getInstance();
        dummyCalendarOneDifMonth.add(Calendar.MONTH, -1);
        assertThat(DateHelper.getDifToSysDateInUnitTime(dummyCalendarOneDifMonth.getTime()), is(sdf.format(dummyCalendarOneDifMonth.getTime())));

        Calendar dummyCalendarMoreOneDifMonth = Calendar.getInstance();
        dummyCalendarMoreOneDifMonth.add(Calendar.MONTH, -2);
        assertThat(DateHelper.getDifToSysDateInUnitTime(dummyCalendarMoreOneDifMonth.getTime()), is(sdf.format(dummyCalendarMoreOneDifMonth.getTime())));

        Calendar dummyCalendarOneDifDay = Calendar.getInstance();
        dummyCalendarOneDifDay.add(Calendar.DAY_OF_YEAR, -1);
        assertThat(DateHelper.getDifToSysDateInUnitTime(dummyCalendarOneDifDay.getTime()), is(sdf.format(dummyCalendarOneDifDay.getTime())));

        Calendar dummyCalendarMoreOneDifDay = Calendar.getInstance();
        dummyCalendarMoreOneDifDay.add(Calendar.DAY_OF_YEAR, -2);
        assertThat(DateHelper.getDifToSysDateInUnitTime(dummyCalendarMoreOneDifDay.getTime()), is(sdf.format(dummyCalendarMoreOneDifDay.getTime())));

        Calendar dummyCalendarOneDifHour = Calendar.getInstance();
        dummyCalendarOneDifHour.add(Calendar.HOUR_OF_DAY, -1);
        assertThat(DateHelper.getDifToSysDateInUnitTime(dummyCalendarOneDifHour.getTime()), is("1 Hora"));

        Calendar dummyCalendarMoreOneDifHour = Calendar.getInstance();
        dummyCalendarMoreOneDifHour.add(Calendar.HOUR_OF_DAY, -2);
        assertThat(DateHelper.getDifToSysDateInUnitTime(dummyCalendarMoreOneDifHour.getTime()), is("2 Horas"));

        Calendar dummyCalendarOneDifMinute = Calendar.getInstance();
        dummyCalendarOneDifMinute.add(Calendar.MINUTE, -1);
        assertThat(DateHelper.getDifToSysDateInUnitTime(dummyCalendarOneDifMinute.getTime()), is("1 Minuto"));

        Calendar dummyCalendarMoreOneDifMinute = Calendar.getInstance();
        dummyCalendarMoreOneDifMinute.add(Calendar.MINUTE, -2);
        assertThat(DateHelper.getDifToSysDateInUnitTime(dummyCalendarMoreOneDifMinute.getTime()), is("2 Minutos"));
    }

    @Test
    public void toStringExtendedTest() {
        Calendar dummyCalendar = Calendar.getInstance();
        dummyCalendar.set(Calendar.YEAR, 2011);
        dummyCalendar.set(Calendar.MONTH, 2);
        dummyCalendar.set(Calendar.DAY_OF_MONTH, 1);
        dummyCalendar.set(Calendar.HOUR_OF_DAY, 10);
        dummyCalendar.set(Calendar.MINUTE, 25);
        dummyCalendar.set(Calendar.SECOND, 43);

        assertThat(DateHelper.toStringExtend(dummyCalendar), is("01/03/2011 10:25:43"));
    }

    @Test(expected = NullPointerException.class)
    public void toStringExtendedNullValueTest() {
        DateHelper.toStringExtend((Calendar) null);
    }

    /**
     * @see DateHelper#getDifToCompareDateInUnitTimeLowRound(Date, Date)
     */
    @Test
    public void dateHelperDifTestSeconds() {
        Date valueA = DateHelper.getFixedDate(2010, 2, 4, 5, 10, 14);
        Date valueB = DateHelper.getFixedDate(2010, 2, 4, 5, 15, 0);
        assertThat(DateHelper.getDifToCompareDateInUnitTimeLowRound(valueA, valueB), is("Ahora"));
    }

    /**
     * @see DateHelper#getDifToCompareDateInUnitTimeLowRound(Date, Date)
     */
    @Test
    public void dateHelperDifTestMinutes() {
        Date valueA = DateHelper.getFixedDate(2010, 2, 4, 5, 15, 0);
        Date valueB = DateHelper.getFixedDate(2010, 2, 4, 5, 10, 0);
        assertThat(DateHelper.getDifToCompareDateInUnitTimeLowRound(valueA, valueB), is("5 Minutos"));
        valueB = DateHelper.getFixedDate(2010, 2, 4, 5, 10, 5);
        assertThat(DateHelper.getDifToCompareDateInUnitTimeLowRound(valueA, valueB), is("4 Minutos"));
        valueB = DateHelper.getFixedDate(2010, 2, 4, 5, 13, 5);
        assertThat(DateHelper.getDifToCompareDateInUnitTimeLowRound(valueA, valueB), is("1 Minuto"));
    }

    /**
     * @see DateHelper#getDifToCompareDateInUnitTimeLowRound(Date, Date)
     */
    @Test
    public void dateHelperDifTestHours() {
        Date valueA = DateHelper.getFixedDate(2010, 2, 4, 8, 10, 0);
        Date valueB = DateHelper.getFixedDate(2010, 2, 4, 5, 15, 0);
        assertThat(DateHelper.getDifToCompareDateInUnitTimeLowRound(valueA, valueB), is("2 Horas"));
        valueB = DateHelper.getFixedDate(2010, 2, 4, 5, 10, 0);
        assertThat(DateHelper.getDifToCompareDateInUnitTimeLowRound(valueA, valueB), is("3 Horas"));
        valueB = DateHelper.getFixedDate(2010, 2, 4, 7, 10, 0);
        assertThat(DateHelper.getDifToCompareDateInUnitTimeLowRound(valueA, valueB), is("1 Hora"));
    }

    /**
     * @see DateHelper#getDifToCompareDateInUnitTimeLowRound(Date, Date)
     */
    @Test
    public void dateHelperDifTestDays() {
        Date valueA = DateHelper.getFixedDate(2010, 5, 6, 5, 10, 0);
        Date valueB = DateHelper.getFixedDate(2010, 5, 4, 5, 15, 0);
        assertThat(DateHelper.getDifToCompareDateInUnitTimeLowRound(valueA, valueB), is("4 may"));
        valueB = DateHelper.getFixedDate(2010, 5, 5, 5, 15, 0);
        assertThat(DateHelper.getDifToCompareDateInUnitTimeLowRound(valueA, valueB), is("23 Horas"));
        valueB = DateHelper.getFixedDate(2010, 5, 5, 5, 10, 0);
        assertThat(DateHelper.getDifToCompareDateInUnitTimeLowRound(valueA, valueB), is("5 may"));
    }

    /**
     * @see DateHelper#getDifToCompareDateInUnitTimeLowRound(Date, Date)
     */
    @Test
    public void dateHelperDifTestMonths() {
        Date valueA = DateHelper.getFixedDate(2010, 2, 12, 5, 10, 0);
        Date valueB = DateHelper.getFixedDate(2010, 1, 12, 5, 15, 0);
        assertThat(DateHelper.getDifToCompareDateInUnitTimeLowRound(valueA, valueB), is("12 ene"));
    }

    /**
     * @see DateHelper#getDifToCompareDateInUnitTimeLowRound(Date, Date)
     */
    @Test
    public void dateHelperDifTestYears() {
        Date valueA = DateHelper.getFixedDate(2012, 11, 24, 5, 10, 0);
        Date valueB = DateHelper.getFixedDate(2010, 11, 24, 5, 15, 0);
        assertThat(DateHelper.getDifToCompareDateInUnitTimeLowRound(valueA, valueB), is("24 nov"));
    }

    /**
     * @see DateHelper#toDateFromExtended(String)
     */
    @Test
    public void toDateFromExtendedSimpleDataTest() {
        Date expected = DateHelper.getFixedDate(2013, 3, 19, 21, 4, 58);
        assertThat(DateHelper.toDateFromExtended("19/03/2013 21:04:58"), is(expected));
    }

    @Test
    @SuppressWarnings("deprecation")
    public void getCurrentDateHourPrecision() throws Exception {
        Date result = DateHelper.getCurrentDatePrecision(DatePrecisionType.HOUR);
        assertThat(result.getYear(), not(0));
        assertThat(result.getDate(), not(0));
        assertThat(result.getHours(), not(0));
        assertThat(result.getMinutes(), is(0));
        assertThat(result.getSeconds(), is(0));
    }

    @Test
    @SuppressWarnings("deprecation")
    public void getCurrentDateMonthPrecision() throws Exception {
        Date result = DateHelper.getCurrentDatePrecision(DatePrecisionType.MONTH);
        assertThat(result.getYear(), not(0));
        assertThat(result.getDate(), is(1));
        assertThat(result.getHours(), is(0));
        assertThat(result.getMinutes(), is(0));
        assertThat(result.getSeconds(), is(0));
    }


    @Test(expected = RecIllegalArgumentException.class)
    public void getDateDiffWithFirstNullValue() throws Exception {
        Date date2 = DateHelper.getFixedDate(2010, 10, 10);
        DateHelper.getDateDiff(null, date2);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void getDateDiffWithSecondNullValue() throws Exception {
        Date date1 = DateHelper.getFixedDate(2010, 10, 10);
        DateHelper.getDateDiff(date1, null);
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void getDateDiffWithNullValues() throws Exception {
        DateHelper.getDateDiff(null, null);
    }

    @Test
    public void getDateWithSimpleValues() throws Exception {
        Date date1 = DateHelper.getFixedDate(2013, 11, 5);
        Date date2 = DateHelper.getFixedDate(2013, 11, 10);
        assertThat(DateHelper.getDateDiff(date1, date2), is(5));
    }

    @Test
    public void getDateWithZeroResult() throws Exception {
        Date date1 = DateHelper.getFixedDate(2013, 11, 5);
        Date date2 = DateHelper.getFixedDate(2013, 11, 5);
        assertThat(DateHelper.getDateDiff(date1, date2), is(0));
    }

    @Test
    public void getDateWithSecondsDiff() throws Exception {
        Date date1 = DateHelper.getFixedDate(2013, 11, 5, 10, 0, 0);
        Date date2 = DateHelper.getFixedDate(2013, 11, 5, 15, 0, 0);
        assertThat(DateHelper.getDateDiff(date1, date2), is(0));
    }

    @Test
    public void getDateDiffWithNegativeResult() throws Exception {
        Date date1 = DateHelper.getFixedDate(2013, 11, 15);
        Date date2 = DateHelper.getFixedDate(2013, 11, 5);
        assertThat(DateHelper.getDateDiff(date1, date2), is(-10));
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void getMonthNameWithNullDate() throws Exception {
        DateHelper.getMonthName(null);
    }

    @Test
    public void getMonthNameWithSimpleDate() throws Exception {
        assertThat(DateHelper.getMonthName(DateHelper.getFixedDate(2010, 11, 10)), is("noviembre"));
        assertThat(DateHelper.getMonthName(DateHelper.getFixedDate(2010, 1, 10)), is("enero"));
        assertThat(DateHelper.getMonthName(DateHelper.getFixedDate(2010, 2, 10)), is("febrero"));
    }

    @Test
    public void getCurrentDateHour() throws Exception {
        Date result = DateHelper.getCurrentDate(DatePrecisionType.HOUR);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(result);
        assertThat(calendar.get(Calendar.MINUTE), is(0));
        assertThat(calendar.get(Calendar.SECOND), is(0));
        assertThat(calendar.get(Calendar.MILLISECOND), is(0));
    }

    @Test
    public void getCurrentDateMonth() throws Exception {
        Date result = DateHelper.getCurrentDate(DatePrecisionType.MONTH);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(result);
        assertThat(calendar.get(Calendar.DAY_OF_MONTH), is(1));
        assertThat(calendar.get(Calendar.HOUR_OF_DAY), is(0));
        assertThat(calendar.get(Calendar.MINUTE), is(0));
        assertThat(calendar.get(Calendar.SECOND), is(0));
        assertThat(calendar.get(Calendar.MILLISECOND), is(0));
    }

    @Test
    public void getCurrentDateMaximumHour() throws Exception {
        Date result = DateHelper.getCurrentDateWithMaxPrecision(DatePrecisionType.HOUR);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(result);
        assertThat(calendar.get(Calendar.MINUTE), is(59));
        assertThat(calendar.get(Calendar.SECOND), is(59));
        assertThat(calendar.get(Calendar.MILLISECOND), is(999));
    }

    @Test
    public void getCurrentDateMaximumMonth() throws Exception {
        Date result = DateHelper.getCurrentDateWithMaxPrecision((DatePrecisionType.MONTH));
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(result);
        assertThat(calendar.get(Calendar.HOUR_OF_DAY), is(23));
        assertThat(calendar.get(Calendar.MINUTE), is(59));
        assertThat(calendar.get(Calendar.SECOND), is(59));
        assertThat(calendar.get(Calendar.MILLISECOND), is(999));
    }

    @Test
    public void getCurrentDateMaximumDay() throws Exception {
        Date result = DateHelper.getCurrentDateWithMaxPrecision((DatePrecisionType.DAY));
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(result);
        assertThat(calendar.get(Calendar.HOUR_OF_DAY), is(23));
        assertThat(calendar.get(Calendar.MINUTE), is(59));
        assertThat(calendar.get(Calendar.SECOND), is(59));
        assertThat(calendar.get(Calendar.MILLISECOND), is(999));
    }

    @Test
    public void getCurrentDateStringTest() throws Exception {
        Date dummyDate = DateHelper.getFixedDate(2010, 11, 5, 15, 19, 28);

        PowerMockito.mockStatic(NowDateHelper.class);
        Mockito.when(NowDateHelper.getCurrentDate()).thenReturn(dummyDate);

        assertThat(DateHelper.getCurrentDateString(), is("05/11/2010 15:19:28"));
    }

    @Test
    public void isBeforeNowWithTrueExpectedResult() throws Exception {
        Calendar now = DateHelper.getCalendar(2010, 10, 3, 15, 19, 28);
        Calendar dummy = DateHelper.getCalendar(2015, 10, 18, 15, 19, 28);
        PowerMockito.mockStatic(NowDateHelper.class);
        Mockito.when(NowDateHelper.getCurrentCalendar()).thenReturn(now, dummy);

        Date currentDate = DateHelper.getFixedDate(2010, 10, 4, 16, 18, 20);
        assertTrue(DateHelper.isBeforeNow(currentDate, DatePrecisionType.DAY));
    }

    @Test
    public void isBeforeNowWithFalseExpectedResult() throws Exception {
        Calendar now = new GregorianCalendar(2010, 10, 18, 15, 19, 28);
        Calendar dummy = new GregorianCalendar(2015, 10, 18, 15, 19, 28);
        PowerMockito.mockStatic(NowDateHelper.class);
        Mockito.when(NowDateHelper.getCurrentCalendar()).thenReturn(now, dummy);

        Date currentDate = DateHelper.getFixedDate(2010, 10, 8, 16, 18, 20);
        assertFalse(DateHelper.isBeforeNow(currentDate, DatePrecisionType.DAY));
    }

    @Test
    public void isBeforeNowWithSameDateExpectedResult() throws Exception {
        Calendar now = new GregorianCalendar(2010, 10, 5, 15, 19, 28);
        Calendar dummy = new GregorianCalendar(2015, 10, 18, 15, 19, 28);
        PowerMockito.mockStatic(NowDateHelper.class);
        Mockito.when(NowDateHelper.getCurrentCalendar()).thenReturn(now, dummy);

        Date currentDate = DateHelper.getFixedDate(2010, 10, 5, 15, 19, 28);
        assertFalse(DateHelper.isBeforeNow(currentDate, DatePrecisionType.DAY));
    }

    @Test
    public void toStringExtendWithNormalData() throws Exception {
        Date date = DateHelper.getFixedDate(2010, 11, 5, 15, 28, 36);
        assertThat(DateHelper.toStringExtend(date), is("05/11/2010 15:28:36"));
    }


    @Test
    public void getCurrentDateExtended() throws Exception {
        Date now = DateHelper.getFixedDate(2010, 11, 5, 15, 19, 28);
        PowerMockito.mockStatic(NowDateHelper.class);
        Mockito.when(NowDateHelper.getCurrentDate()).thenReturn(now);

        assertThat(DateHelper.getCurrentDateExtended(), is("05/11/2010 15:19:28"));
    }

    @Test
    public void isAfterNowHoursDifferenceAndYearPrecision() throws Exception {
        Calendar now = DateHelper.getCalendar(2010, 10, 10, 15, 25, 28);
        PowerMockito.mockStatic(NowDateHelper.class);
        Mockito.when(NowDateHelper.getCurrentCalendar()).thenReturn(now);

        Date dateToVerify = DateHelper.getFixedDate(2010, 10, 10, 16, 48, 0);
        assertFalse(DateHelper.isAfterNow(dateToVerify, DatePrecisionType.YEAR));
    }

    @Test
    public void isAfterNowHoursDifferenceAndMonthPrecision() throws Exception {
        Calendar now = DateHelper.getCalendar(2010, 10, 10, 15, 25, 28);
        PowerMockito.mockStatic(NowDateHelper.class);
        Mockito.when(NowDateHelper.getCurrentCalendar()).thenReturn(now);

        Date dateToVerify = DateHelper.getFixedDate(2010, 10, 10, 16, 48, 0);
        assertFalse(DateHelper.isAfterNow(dateToVerify, DatePrecisionType.MONTH));
    }

    @Test
    public void isAfterNowHoursDifferenceAndDayPrecision() throws Exception {
        Calendar now = DateHelper.getCalendar(2010, 10, 10, 15, 25, 28);
        PowerMockito.mockStatic(NowDateHelper.class);
        Mockito.when(NowDateHelper.getCurrentCalendar()).thenReturn(now);

        Date dateToVerify = DateHelper.getFixedDate(2010, 10, 10, 16, 48, 0);
        assertFalse(DateHelper.isAfterNow(dateToVerify, DatePrecisionType.DAY));
    }

    @Test
    public void isAfterNowHoursDifferenceAndHourPrecision() throws Exception {
        Calendar now = DateHelper.getCalendar(2010, 10, 10, 15, 25, 28);
        PowerMockito.mockStatic(NowDateHelper.class);
        Mockito.when(NowDateHelper.getCurrentCalendar()).thenReturn(now);

        Date dateToVerify = DateHelper.getFixedDate(2010, 10, 10, 16, 48, 0);
        assertTrue(DateHelper.isAfterNow(dateToVerify, DatePrecisionType.HOUR));
    }

    @Test
    public void isAfterNowHoursDifferenceAndMinutePrecision() throws Exception {
        Calendar now = DateHelper.getCalendar(2010, 10, 10, 15, 25, 28);
        PowerMockito.mockStatic(NowDateHelper.class);
        Mockito.when(NowDateHelper.getCurrentCalendar()).thenReturn(now);

        Date dateToVerify = DateHelper.getFixedDate(2010, 10, 10, 16, 48, 0);
        assertTrue(DateHelper.isAfterNow(dateToVerify, DatePrecisionType.MINUTE));
    }

    @Test
    public void isAfterNowHoursDifferenceAndSecondPrecision() throws Exception {
        Calendar now = DateHelper.getCalendar(2010, 10, 10, 15, 25, 28);
        PowerMockito.mockStatic(NowDateHelper.class);
        Mockito.when(NowDateHelper.getCurrentCalendar()).thenReturn(now);

        Date dateToVerify = DateHelper.getFixedDate(2010, 10, 10, 16, 48, 0);
        assertTrue(DateHelper.isAfterNow(dateToVerify, DatePrecisionType.SECOND));
    }

    @Test
    public void isAfterNowHoursDifferenceAndMillisecondPrecision() throws Exception {
        Calendar now = DateHelper.getCalendar(2010, 10, 10, 15, 25, 28);
        PowerMockito.mockStatic(NowDateHelper.class);
        Mockito.when(NowDateHelper.getCurrentCalendar()).thenReturn(now);

        Date dateToVerify = DateHelper.getFixedDate(2010, 10, 10, 16, 48, 0);
        assertTrue(DateHelper.isAfterNow(dateToVerify, DatePrecisionType.MILLISECOND));
    }
}
