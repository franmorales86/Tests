/**
 * Project: RecCommon
 * Created by: raulanatol at 07/01/14 09:48
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.common.helpers.types;

import org.joda.time.DateTime;

import java.util.Calendar;
import java.util.Date;

/**
 * This class is used to instantiate a Calendar.
 * Note: Is used for testing propose
 * @author raulanatol
 */
public class NowDateHelper {
    /**
     * @return Gets the current date of the system
     */
    public static Date getCurrentDate() {
        return DateTime.now().toDate();
    }

    /**
     * @return Gets the current calendar
     */
    public static Calendar getCurrentCalendar() {
        return DateTime.now().toGregorianCalendar();
    }
}
