/**
 * Project: RecCommon
 * Created by: fjmorales at 04/12/2012 18:50:54
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.common.helpers.types;

import com.reclabs.recomendar.common.exceptions.helpers.types.StringHelperException;
import com.reclabs.recomendar.common.helpers.ParameterHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Clase de ayuda para el trato con cadenas
 * @author rmorales
 */
public class StringHelper {
    /**
     * Indica si una String es null o vacía
     * @param str La cadena pasada por parámetros
     * @return <b>true</b> en caso de que la cadena está vacía
     */
    public static boolean isEmpty(final String str) {
        return (str == null || "".equals(str.trim()));
    }

    /**
     * Dadas dos cadenas de texto indicamos si son iguales. <b>CaseSensitive = false</b>
     * @param str first string to compare
     * @param str2 second string to compare
     * @return Indica si las cadenas son iguales
     */
    public static boolean equals(final String str, final String str2) {
        return equals(str, str2, false);
    }

    /**
     * Verify if any of values are equal that strToCompare
     * @param strToCompare String to compare
     * @param values Elements to verify
     * @return true if any member of values is equal that strToCompare
     */
    public static boolean isAnyEquals(String strToCompare, String... values) {
        ParameterHelper.assertNull(strToCompare);
        boolean result = false;
        if (values != null) {
            for (String value : values) {
                if (value != null) {
                    if (equals(strToCompare, value)) {
                        result = true;
                        break;
                    }
                }
            }
        }
        return result;
    }

    /**
     * Dadas dos cadenas indicamos si son iguales o no con caseSensitive activo o inactivo en
     * función del parámetro de entrada.
     * @param str first string to compare
     * @param str2 second string to compare
     * @param caseSensitive true if case sensitive equals is enabled
     * @return Indica si las cadenas son iguales
     */
    public static boolean equals(final String str, final String str2, final boolean caseSensitive) {
        boolean result;
        if (str == null) {
            result = (str2 == null);
        } else if (str2 == null) {
            result = false;
        } else {
            if (caseSensitive) {
                result = (str.compareTo(str2) == 0);
            } else {
                result = (str.compareToIgnoreCase(str2) == 0);
            }
        }
        return result;
    }

    /**
     * Dado un patrón de inicio a buscar indicamos si se encuentra en el inicio de la cadena
     * mainString
     * @param findText Text to find
     * @param mainString String with all data
     * @return Indica si la cadena comienza con el patron pasado
     */
    public static boolean startWith(final String findText, final String mainString) {
        return (!isAnyEmpty(findText, mainString) && mainString.startsWith(findText));
    }

    /**
     * Dado un listado de strings indicamos true en caso de que alguna de ellas sea Empty
     * @param values El listado de las strings
     * @return Indica si algun elemento es Empty
     */
    public static boolean isAnyEmpty(final String... values) {
        boolean result = false;
        if (values != null) {
            for (String value : values) {
                if (isEmpty(value)) {
                    result = true;
                    break;
                }
            }
        } else {
            result = true;
        }
        return result;
    }

    /**
     * Dado un string del tipo: "viaje a nepal" devuelve el elemento: String[] = {"viaje", "a",
     * "nepal"}
     * @param queryString String to find
     * @param separator El separador de las palabras
     * @return Listado de cadenas separadas
     */
    public static String[] extractWords(final String queryString, final String separator) {
        List<String> result = new ArrayList<>();
        if (!StringHelper.isEmpty(queryString) && separator != null) {
            StringTokenizer token = new StringTokenizer(queryString, separator);
            while (token.hasMoreTokens()) {
                String item = token.nextToken();
                result.add(item);
            }
        }
        return result.toArray(new String[result.size()]);
    }

    /**
     * Dada una cadena y un índice a buscar obtenemos la cadena desde ese índice incluyéndolo.
     * @param value El valor donde buscaremos la cadena.
     * @param findPattern El patrón de búsqueda de la cadena.
     * @return null en caso de no encontrar coincidencias
     */
    public static String getSubstringFrom(final String value, final String findPattern) {
        String result = null;
        if (!StringHelper.isAnyEmpty(value, findPattern)) {
            int index = value.indexOf(findPattern);
            if (index > -1) {
                result = value.substring(index);
            }
        }
        return result;
    }

    /**
     * Obtenemos el substring de value que comience por initialFindPattern y termine por
     * endFindPattern.
     * @param value Big string with all data.
     * @param initialFindPattern Pattern to initial find
     * @param endFindPattern Pattern to end find
     * @return null en caso de no encontrar coincidencias
     */
    public static String getSubstringBetween(final String value, final String initialFindPattern, final String endFindPattern) {
        String result = null;
        if (!StringHelper.isAnyEmpty(value, initialFindPattern, endFindPattern)) {
            int startIndex = value.indexOf(initialFindPattern);
            int endIndex = value.indexOf(endFindPattern);
            if (startIndex > -1 && endIndex > -1) {
                result = value.substring(startIndex, endIndex);
            }
        }
        return result;
    }

    /**
     * Dada una cadena de texto le quitamos todos los ceros a la izquierda que podamos.
     * @param value String to modify
     * @return Cadena sin ceros a la izquierda
     */
    public static String removeLeftZeros(final String value) {
        String result = "";
        if (!StringHelper.isEmpty(value)) {
            int i = -1;
            do {
                i++;
            } while (value.charAt(i) == '0');
            result = value.substring(i, value.length());
        }
        return result;
    }

    /**
     * Dado un texto, y una cadena a buscar, modificamos todas las aparariciones de esa cadena en el
     * texto por el valor del parámetro token2Replace
     * @param value El texto a modificar
     * @param token2Find La cadena a buscar en el texto
     * @param token2Replace La cadena a reemplazar
     * @return La nueva cadena con los cambios realizados.
     */
    public static String replaceToken(final String value, final String token2Find, final String token2Replace) {
        if (NullHelper.isAnyNull(value, token2Find, token2Replace)) {
            throw new IllegalArgumentException();
        }
        return value.replaceAll(token2Find, token2Replace);
    }

    /**
     * Obtenemos la última ocurrencia del texto str2Find dentro del texto value.
     * @param value El texto sobre el que buscar
     * @param str2Find La cadena de texto a buscar.
     * @return -1 en caso de que no encuentre la cadena, y la posición en caso de encontrarla.
     */
    public static int lastIndexOf(final String value, final String str2Find) {
        if (NullHelper.isAnyNull(value, str2Find)) {
            throw new IllegalArgumentException("Values can not be null");
        }
        int result;
        int last = -1;
        do {
            result = last;
            last = value.indexOf(str2Find, last + 1);
        } while (last != -1);
        return result;
    }

    /**
     * Dada una cadena de texto un elemento a buscar y un elemento a reemplazar buscamos la primera ocurrencia del
     * texto str2Fing en la cadena value buscando desde la derecha de la misma y la reemplazamos por la cadena de
     * texto str2Replace.
     * @param value Texto a usar.
     * @param str2Find Texto a buscar.
     * @param str2Replace Texto a reemplazar.
     * @return Cadena modificada
     * @throws StringHelperException
     */
    public static String replaceLast(final String value, final String str2Find, final String str2Replace) throws StringHelperException {
        if (NullHelper.isAnyNull(value, str2Find, str2Replace)) {
            throw new IllegalArgumentException("Values can not be null");
        }
        int lastPosition = lastIndexOf(value, str2Find);
        if (lastPosition == -1) {
            throw new StringHelperException("the str2Find is not found");
        }
        StringBuilder result = new StringBuilder();
        result.append(value.substring(0, lastPosition));
        result.append(str2Replace);
        result.append(value.substring(lastPosition + str2Find.length()));
        return result.toString();
    }

    /**
     * Dada una cadena limita el tamaño maximo al numero de elementos pasado como parametro añadiendo "..." si se
     * produce truncamiento
     * @param value La cadena a limitar.
     * @param length El tamaño a limitar.
     * @return La cadena limitada.
     */
    public static String limit(final String value, final int length) {
        if (NullHelper.isAnyNull(value)) {
            throw new IllegalArgumentException("Values can not be null");
        }
        StringBuilder buf = new StringBuilder(value);
        if (buf.length() > length) {
            buf.setLength(length);
            buf.append("...");
        }

        return buf.toString();
    }

    /**
     * @param separator The string separator
     * @param elements List of strings to concat
     * @return Result to concat the null elements of element params in the same order to is passed.
     */
    public static String toStringSeparator(String separator, String... elements) {
        String result;
        StringBuilder buffer = new StringBuilder();
        for (String element : elements) {
            if (!StringHelper.isEmpty(element)) {
                buffer.append(element);
                buffer.append(separator);
            }
        }
        if (buffer.length() > separator.length()) {
            result = buffer.substring(0, buffer.length() - separator.length());
        } else {
            result = buffer.toString();
        }
        return result;
    }

    /**
     * Convierte una lista de cadenas a minusculas
     * @param elements Lista de cadenas a convertir
     * @return Listado de cadenas en minusculas
     */
    public static List<String> toLowerCase(List<String> elements) {
        List<String> result = new ArrayList<>();
        for (String element : elements) {
            result.add(element.toLowerCase());
        }
        return result;
    }

    /**
     * Gets the difference between mainString and diffString
     * @param mainString first string to compare
     * @param diffString second string to compare
     * @return The differences
     */
    public static String extract(String mainString, String diffString) {
        ParameterHelper.assertNull(mainString);
        String result;
        if (StringHelper.isEmpty(diffString)) {
            result = mainString;
        } else {
            StringBuilder buffer = new StringBuilder(mainString);
            int position;
            while ((position = buffer.indexOf(diffString)) != -1) {
                buffer.delete(position, position + diffString.length());
            }
            result = buffer.toString();
        }
        return result;
    }

    /**
     * Verify if any element of the elements array is contains on the itemToVerify.
     * @param itemToVerify Item to verify
     * @param elements List of elements to compare
     */
    public static boolean containsAny(String itemToVerify, String... elements) {
        ParameterHelper.assertNull(itemToVerify);
        boolean result = false;
        for (String element : elements) {
            if (element != null) {
                if (itemToVerify.contains(element)) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    public static boolean contains(String source, String elementToCompare) {
        ParameterHelper.assertNull(source);
        boolean result = false;
        if (elementToCompare != null) {
            result = source.contains(elementToCompare);
        }
        return result;
    }


    /**
     * Return the word of parameters with the first letter to lowercase
     * @param word The word
     * @return The word modified or RecIllegalArgumentException
     */
    public static String firstLetterToLowerCase(String word) {
        ParameterHelper.assertEmpty(word);
        return word.substring(0, 1).toLowerCase() + word.substring(1);
    }


    /**
     * @param word represents the string to check
     * @return true o false based on if word it's palindrome
     */
    public static boolean isPalindrome(String word) {
        boolean result = true;
        if (!StringHelper.isEmpty(word)) {
            int stringLength = word.length();
            int stringMediumLength = stringLength / 2;
            for (int i = 0; i < stringMediumLength; i++) {
                if (word.charAt(i) != word.charAt(stringLength - i - 1)) {
                    result = false;
                    break;
                }
            }
        } else {
            result = false;
        }
        return result;
    }

    /**
     * Convert a String parameter to a boolean value
     * @param value The value to parse
     * @return true if the values contains in (true, True, TRUE, 1) false otherwise
     */
    public static boolean toBoolean(String value) {
        ParameterHelper.assertEmpty(value);
        boolean result = false;
        switch (value) {
            case "true":
            case "True":
            case "TRUE":
            case "1":
                result = true;
                break;
        }
        return result;
    }
}
