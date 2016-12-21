/**
 * Project: RecCommon
 * Created by: raulanatol at 12/03/2013 12:28:28
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.common.helpers;

import com.reclabs.recomendar.common.exceptions.RecIllegalArgumentException;
import com.reclabs.recomendar.common.helpers.types.CollectionHelper;
import com.reclabs.recomendar.common.helpers.types.DateHelper;
import com.reclabs.recomendar.common.helpers.types.StringHelper;
import com.reclabs.recomendar.common.types.DatePrecisionType;

import java.util.Collection;
import java.util.Date;

/**
 * @author raulanatol
 */
public class ParameterHelper {

    /**
     * Throw RecIllegalArgumentException when element is empty
     * @param element Element to verify
     */
    public static void assertEmpty(String element) {
        assertEmpty(element, "A parameter is empty");
    }

    /**
     * Throw RecIllegalArgumentException when element is empty
     * @param element Element to verify
     * @param message Message to show
     */
    public static void assertEmpty(String element, String message) {
        if (StringHelper.isEmpty(element)) {
            throw new RecIllegalArgumentException(message);
        }
    }

    /**
     * Throw RecIllegalArgumentException when any element of array is empty.
     * @param elements Elements to verify.
     */
    public static void assertAnyEmpty(String... elements) {
        if (StringHelper.isAnyEmpty(elements)) {
            throw new RecIllegalArgumentException("An element is empty");
        }
    }

    /**
     * Throw RecIllegalArgumentException when a collection is empty.
     * @param collection Element to verify.
     */
    public static void assertCollectionEmpty(Collection<?> collection) {
        if (CollectionHelper.isEmpty(collection)) {
            throw new RecIllegalArgumentException();
        }
    }

    /**
     * RecIllegalArgumentException if value is negative.
     * @param value Value to verify
     */
    public static void assertNegative(int value) {
        if (value < 0) {
            throw new RecIllegalArgumentException("Is negative" + value);
        }
    }

    /**
     * RecIllegalArgumentException if value is negative or zero.
     * @param value Value to verify
     */
    public static void assertZeroOrNegative(int value) {
        if (value <= 0) {
            throw new RecIllegalArgumentException("Is zero or negative" + value);
        }
    }

    /**
     * RecIllegalArgumentException if value is negative.
     * @param value Value to verify
     */
    public static void assertNegative(long value) {
        if (value < 0) {
            throw new RecIllegalArgumentException("Is negative" + value);
        }
    }

    /**
     * RecIllegalArgumentException if value is negative or zero.
     * @param value Value to verify
     */
    public static void assertZeroOrNegative(long value) {
        if (value <= 0) {
            throw new RecIllegalArgumentException("Is zero or negative" + value);
        }
    }

    /**
     * RecIllegalArgumentException if element is null
     * @param element Value to verify
     */
    public static void assertNull(Object element) {
        if (element == null) {
            throw new RecIllegalArgumentException();
        }
    }

    /**
     * RecIllegalArgumentException if any element is null
     * @param elements List of elements to verify
     */
    public static void assertAnyNull(Object... elements) {
        if (elements == null) {
            throw new RecIllegalArgumentException("An element is null");
        }
        for (Object element : elements) {
            if (element == null) {
                throw new RecIllegalArgumentException("An element is null");
            }
        }
    }

    /**
     * RecIllegalArgumentException if element is not null
     * @param element Element to verify
     */
    public static void assertNotNull(Object element) {
        if (element != null) {
            throw new RecIllegalArgumentException();
        }
    }

    /**
     * Throw exception if the element is true
     * @param element The element to verify
     * @param message Message shown
     */
    public static void assertTrue(boolean element, String message) {
        if (element) {
            throw new RecIllegalArgumentException(message);
        }
    }

    /**
     * Throw exception if the element is true
     * @param element The element to verify
     */
    public static void assertTrue(boolean element) {
        assertTrue(element, "Condition is true");
    }

    /**
     * Throw exception if the current date is equals or before that NOW.
     * @param date The date to verify
     */
    public static void assertDateAfterNow(Date date, DatePrecisionType datePrecisionType) {
        assertNull(date);
        if (!DateHelper.isAfterNow(date, datePrecisionType)) {
            throw new RecIllegalArgumentException("The current date is before or equals that now: " + date);
        }
    }
}
