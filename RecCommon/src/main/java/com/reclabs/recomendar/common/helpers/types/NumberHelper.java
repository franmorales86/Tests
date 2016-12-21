/**
 * Project: RecCommon
 * Created by: fjmorales at 04/12/2012 18:57:23
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.common.helpers.types;

/**
 * Clase de ayuda para valores numéricos.
 * @author raulanatol
 */
public class NumberHelper {
    /**
     * Dados dos extra long en formato String obtenemos el mayor de ellos. Un null es lo más
     * pequeño.
     * @param valueA
     * @param valueB
     * @return String mayor
     */
    public static String maxExtraLongs(final String valueA, final String valueB) {
        String result = null;
        if (valueA == null) {
            result = valueB;
        }
        if (valueB == null) {
            result = valueA;
        }
        // Quitamos los ceros a la izquierda para comparar...
        String valueA2Compare = StringHelper.removeLeftZeros(valueA);
        String valueB2Compare = StringHelper.removeLeftZeros(valueB);
        // Comparamos.
        if (valueA2Compare.length() == valueB2Compare.length()) {
            int cont = 0;
            char aChar;
            char bChar;
            do {
                aChar = valueA2Compare.charAt(cont);
                bChar = valueB2Compare.charAt(cont);
                cont++;
            } while (aChar == bChar && cont < valueA2Compare.length());
            result = (aChar > bChar) ? valueA : valueB;
        } else if (valueA2Compare.length() > valueB2Compare.length()) {
            result = valueA;
        } else {
            result = valueB;
        }
        return result;
    }

    /**
     * @param integerValue
     * @return El valor entero.
     */
    public static int toInteger(String integerValue) {
        int result;
        try {
            result = Integer.parseInt(integerValue);
        } catch (NumberFormatException e) {
            result = 0;
        }
        return result;
    }

    /**
     * Increment a long variable considering that can be null.
     * @param value Value to increment. (Can be null)
     * @param incrementValue Unit to increment a value parameter
     * @return The value incremented
     */
    public static long incrementLong(Long value, long incrementValue) {
        return (value == null) ? incrementValue : value + incrementValue;
    }

    /**
     * Decrement a long variable considering that can be null with a min value limit.
     * @param value Value to decrement. (Can be null)
     * @param decrementValue Unit to decrement a value parameter
     * @return The value decremented
     */
    public static long decrementLong(Long value, long decrementValue, long minValue) {
        return (value == null) ? minValue : (((value - decrementValue) < minValue) ? minValue : (value - decrementValue));
    }

}
