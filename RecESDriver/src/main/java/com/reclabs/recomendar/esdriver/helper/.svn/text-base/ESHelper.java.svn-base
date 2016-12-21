/**
 * Project: RecESDriver
 * Created by: raulanatol at 20/05/13 17:34
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.esdriver.helper;

import com.reclabs.recomendar.common.helpers.ParameterHelper;

/**
 * @author raulanatol
 */
public class ESHelper {

    /**
     * Parse group of words to pattern: word AND word AND word
     * @param groupOfWords The words to parse
     * @return words on pattern
     */
    public static String toExactlyWords(String groupOfWords) {
        ParameterHelper.assertEmpty(groupOfWords, "groupOfWords can be null");
        StringBuffer buffer = new StringBuffer();
        String[] elements = groupOfWords.split(" ");
        for (String element : elements) {
            buffer.append(element);
            buffer.append(" AND ");
        }
        return (buffer.length() > 0) ? buffer.substring(0, buffer.length() - 5) : "";
    }
}
