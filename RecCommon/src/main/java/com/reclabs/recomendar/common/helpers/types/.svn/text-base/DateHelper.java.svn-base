/**
 * Project: RecCommon
 * Created by: fjmorales at 04/12/2012 19:00:56
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.common.helpers.types;

import com.reclabs.recomendar.common.exceptions.RecIllegalArgumentException;
import com.reclabs.recomendar.common.helpers.ParameterHelper;
import com.reclabs.recomendar.common.types.DatePrecisionType;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Clase de ayuda para el uso de las fechas
 * @author rmorales
 */
public class DateHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(DateHelper.class);
    private static final SimpleDateFormat EXTENDED_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private static final SimpleDateFormat REDUCED_DATE_FORMAT = new SimpleDateFormat("d MMM");

    /**
     * Obtenemos la fecha actual en formato String extendido
     * @return Fecha actual
     */
    public static String getCurrentDateString() {
        return toStringExtend(NowDateHelper.getCurrentDate());
    }

    /**
     * Dado un calendar lo convertimos a un String en formato
     * con precisión de segundos
     * @param calendar The date to convert
     * @return String que representa la fecha dada
     */
    public static String toStringExtend(final Calendar calendar) {
        return EXTENDED_DATE_FORMAT.format(calendar.getTime());
    }

    /**
     * Dado un date lo convertimos en String con el formato extendido.
     * @param date El date a convertir.
     * @return El String en formato extendido.
     */
    public static String toStringExtend(final Date date) {
        return EXTENDED_DATE_FORMAT.format(date);
    }

    /**
     * Dada una fecha obtenemos un String con la fecha en formato extendido.
     * Es decir, con horas minutos y segundos incluidos.
     * @param date The date to convert
     * @return String de fecha con formato extendido
     */
    public static String date2ExtendedString(final Date date) {
        return EXTENDED_DATE_FORMAT.format(date);
    }

    /**
     * Dado el año, mes, día obtenemos el day que lo representa.
     * @param year The year
     * @param month Base-0 -> El 0 es enero
     * @param day The day of the month
     * @return Date con la fecha pasada por parámetro
     * @deprecated Change for getFixedDate because this method use month base 0
     */
    @Deprecated
    public static Date getDate(final int year, final int month, final int day) {
        return getDate(year, month, day, 0, 0, 0);
    }

    /**
     * @param year The year
     * @param month Base-0 -> El 0 es enero
     * @param day The day of the month 1-Base
     * @param hour The hour of the day
     * @param minute The minutes of the day
     * @param second The seconds
     * @return Date con la fecha pasada por parámetro Dados los datos de una fecha la obtenemos
     * @deprecated Change for getFixedDate because this method use month base 0
     */
    @Deprecated
    public static Date getDate(final int year, final int month, final int day, final int hour, final int minute, final int second) {
        return getDatetime(year, month + 1, day, hour, minute, second).toDate();
    }

    /**
     * Gets a calendar
     * @param year The year
     * @param month Base-1 -> 1 is January
     * @param day Base-1
     * @param hour 0-23
     * @param minute 0-54
     * @param second 0-54
     * @return The calendar
     */
    public static Calendar getCalendar(int year, int month, int day, int hour, int minute, int second) {
        return getDatetime(year, month, day, hour, minute, second).toGregorianCalendar();
    }

    /**
     * Gets a date
     * @param year The year
     * @param month Base-1 -> 1 is January
     * @param day Base-1
     * @return The calendar
     */
    public static Date getFixedDate(int year, int month, int day) {
        return getDatetime(year, month, day, 0, 0, 0).toDate();
    }

    /**
     * Gets a date
     * @param year The year
     * @param month Base-1 -> 1 is January
     * @param day Base-1
     * @param hour 0-23
     * @param minute 0-54
     * @param second 0-54
     * @return The calendar
     */
    public static Date getFixedDate(int year, int month, int day, int hour, int minute, int second) {
        return getDatetime(year, month, day, hour, minute, second).toDate();
    }

    /**
     * Gets a dateTime
     * @param year The year
     * @param month Base-1 -> 1 is January
     * @param day Base-1
     * @param hour 0-23
     * @param minute 0-54
     * @param second 0-54
     * @return The DateTime
     */
    private static DateTime getDatetime(int year, int month, int day, int hour, int minute, int second) {
        return new DateTime(year, month, day, hour, minute, second);
    }

    /**
     * Dada una fecha de referencia obtenemos la diferencia de esta con la fecha del sistema, el formato dependerá
     * del rango de la diferencia, es decir, si hay menos de
     * un mes de diferencia obtendremos la diferencia en días.
     * @param date Date to diff
     * @return Diferencia con la fecha del sistema
     */
    public static String getDifToSysDateInUnitTime(final Date date) {
        return getDifToCompareDateInUnitTimeLowRound(new Date(), date);
    }

    /**
     * Dada una fecha de referencia y una fecha a comparar obtenemos la diferencia de la primera con la segunda, el
     * formato dependerá del rango de la diferencia, es decir, si hay menos de
     * un mes de diferencia obtendremos la diferencia en días.
     * @param referenceDate The date of reference
     * @param compareDate The date to compare
     * @return Diferencia entre las fechas
     */
    public static String getDifToCompareDateInUnitTimeLowRound(final Date referenceDate, final Date compareDate) {
        Period period = new Period(compareDate.getTime(), referenceDate.getTime(), PeriodType.yearMonthDayTime().withSecondsRemoved().withMillisRemoved());
        if ((period.getYears() > 0) || (period.getMonths() > 0) || (period.getDays() > 0)) {
            return REDUCED_DATE_FORMAT.format(compareDate);
        } else if (period.getHours() > 0) {
            return period.getHours() + " Hora" + (period.getHours() > 1 ? "s" : "");
        } else if (period.getMinutes() > 0) {
            return period.getMinutes() + " Minuto" + (period.getMinutes() > 1 ? "s" : "");
        } else {
            return "Ahora";
        }
    }

    /**
     * @param precision The precision of the result
     * @return The current date with the precision
     */
    public static Date getCurrentDatePrecision(DatePrecisionType precision) {
        return getDateWithPrecision(NowDateHelper.getCurrentDate(), precision);
    }

    /**
     * Get a date with a specific precision
     * @param precision Precision of the date
     * @return The current date
     */
    public static Date getCurrentDate(DatePrecisionType precision) {
        Calendar result = NowDateHelper.getCurrentCalendar();
        switch (precision) {
            case YEAR:
                result.set(Calendar.MONTH, result.getActualMinimum(Calendar.MONTH));
            case MONTH:
                result.set(Calendar.DAY_OF_MONTH, result.getActualMinimum(Calendar.DAY_OF_MONTH));
            case DAY:
                result.set(Calendar.HOUR_OF_DAY, result.getActualMinimum(Calendar.HOUR_OF_DAY));
            case HOUR:
                result.set(Calendar.MINUTE, result.getActualMinimum(Calendar.MINUTE));
            case MINUTE:
                result.set(Calendar.SECOND, result.getActualMinimum(Calendar.SECOND));
            case SECOND:
                result.set(Calendar.MILLISECOND, result.getActualMinimum(Calendar.MILLISECOND));
            case MILLISECOND:
                break;
            default:
                LOGGER.warn("[Error getCurrentDate, precision value is invalid: {}]", precision);
                throw new RecIllegalArgumentException("The precision value is invalid " + precision);
        }
        return result.getTime();
    }

    /**
     * Get a date with a specific precision and with maximum value in the rest of fields
     * @param precision Precision of the date
     * @return The current date with the maximum value
     */
    public static Date getCurrentDateWithMaxPrecision(DatePrecisionType precision) {
        Calendar result = NowDateHelper.getCurrentCalendar();
        switch (precision) {
            case YEAR:
                result.set(Calendar.MONTH, result.getActualMaximum(Calendar.MONTH));
            case MONTH:
                result.set(Calendar.DAY_OF_MONTH, result.getActualMaximum(Calendar.DAY_OF_MONTH));
            case DAY:
                result.set(Calendar.HOUR_OF_DAY, result.getActualMaximum(Calendar.HOUR_OF_DAY));
            case HOUR:
                result.set(Calendar.MINUTE, result.getActualMaximum(Calendar.MINUTE));
            case MINUTE:
                result.set(Calendar.SECOND, result.getActualMaximum(Calendar.SECOND));
            case SECOND:
                result.set(Calendar.MILLISECOND, result.getActualMaximum(Calendar.MILLISECOND));
            case MILLISECOND:
                break;
            default:
                LOGGER.warn("[Error getCurrentDate, precision value is invalid: {}]", precision);
                throw new RecIllegalArgumentException("The precision value is invalid " + precision);
        }
        return result.getTime();
    }

    /**
     * @param dateString Date on String format
     * @return The date object or error.
     */
    public static Date toDateFromExtended(String dateString) {
        Date result = null;
        try {
            result = EXTENDED_DATE_FORMAT.parse(dateString);
        } catch (ParseException ignore) {
            // ignore
        }
        return result;
    }

    /**
     * @return The extended current date
     */
    public static String getCurrentDateExtended() {
        return date2ExtendedString(NowDateHelper.getCurrentDate());
    }

    /**
     * Compare the dates given and return the difference in days
     * @param date1 first date
     * @param date2 second date
     * @return number of difference days
     */
    public static int getDateDiff(Date date1, Date date2) {
        ParameterHelper.assertAnyNull(date1, date2);
        DateTime jodaDate1 = new DateTime(date1.getTime());
        DateTime jodaDate2 = new DateTime(date2.getTime());
        return Days.daysBetween(jodaDate1, jodaDate2).getDays();
    }

    /**
     * Subtract to the current date the amount of that precisionType represent.
     * @param date The date to subtract
     * @param amount The amount to subtract
     * @param precisionType The field to subtract
     * @return The new date
     */
    public static Date subtract(Date date, int amount, DatePrecisionType precisionType) {
        DateTime jodaDate1 = new DateTime(date.getTime());
        DateTime result;
        switch (precisionType) {
            case YEAR:
                result = jodaDate1.minusYears(amount);
                break;
            case MONTH:
                result = jodaDate1.minusMonths(amount);
                break;
            case DAY:
                result = jodaDate1.minusDays(amount);
                break;
            case HOUR:
                result = jodaDate1.minusHours(amount);
                break;
            case MINUTE:
                result = jodaDate1.minusMinutes(amount);
                break;
            case SECOND:
                result = jodaDate1.minusSeconds(amount);
                break;
            case MILLISECOND:
                result = jodaDate1.minusMillis(amount);
                break;
            default:
                LOGGER.warn("[Error subtract, precision value is invalid: {}]", precisionType);
                throw new RecIllegalArgumentException("The precision value is invalid " + precisionType);
        }
        return result.toDate();
    }

    /**
     * Gets the name of the month to the parameter
     * @param date The date to extract the month
     * @return The month name
     */
    public static String getMonthName(Date date) {
        ParameterHelper.assertNull(date);
        return new SimpleDateFormat("MMMM").format(date);
    }

    /**
     * @param date The date to verify
     * @param datePrecisionType The date precision
     * @return True if the date is after that now
     */
    public static boolean isAfterNow(Date date, DatePrecisionType datePrecisionType) {
        Date dateToVerify = getDateWithPrecision(date, datePrecisionType);
        Date nowToVerify = getCurrentDate(datePrecisionType);
        return dateToVerify.after(nowToVerify);
    }

    /**
     * @param date The date to verify
     * @param datePrecisionType The date precision
     * @return True if the date is before that now
     */
    public static boolean isBeforeNow(Date date, DatePrecisionType datePrecisionType) {
        Date dateToVerify = getDateWithPrecision(date, datePrecisionType);
        Date nowToVerify = getCurrentDate(datePrecisionType);
        return dateToVerify.after(nowToVerify);
    }

    /**
     * @param date The date to parse
     * @param precision The precision to do it.
     * @return The date with the precision format.
     */
    protected static Date getDateWithPrecision(Date date, DatePrecisionType precision) {
        Calendar calendar = toCalendar(date);
        switch (precision) {
            case YEAR:
                calendar.set(Calendar.MONTH, calendar.getActualMinimum(Calendar.MONTH));
            case MONTH:
                calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
            case DAY:
                calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
            case HOUR:
                calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
            case MINUTE:
                calendar.set(Calendar.SECOND, calendar.getActualMinimum(Calendar.SECOND));
            case SECOND:
                calendar.set(Calendar.MILLISECOND, calendar.getActualMinimum(Calendar.MILLISECOND));
            case MILLISECOND:
                break;
            default:
                LOGGER.warn("[Error getCurrentDate, precision value is invalid: {}]", precision);
                throw new RecIllegalArgumentException("The precision value is invalid " + precision);
        }
        return calendar.getTime();
    }

    private static Calendar toCalendar(Date date) {
        Calendar result = Calendar.getInstance();
        result.setTime(date);
        return result;
    }
}
 