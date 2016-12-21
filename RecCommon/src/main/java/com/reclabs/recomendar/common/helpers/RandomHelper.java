/**
 * Project: RecCommon
 * Created by: raulanatol at 11/01/2013 19:36:03
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.common.helpers;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author raulanatol
 */
public class RandomHelper {
    private static final String CHARSET = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final Random RANDOM = new Random(System.currentTimeMillis());
    private static final int EMAIL_VERIFICATION_CODE_LENGTH = 8;
    private static final int DELETE_ACCOUNT_CODE_LENGTH = 8;

    /**
     * @param minValue The min value
     * @param maxValue The max value
     * @param excludeValues The excludes values
     * @return Valor aleatorio entre minValue y maxValue excluyendo los elementos excludeValues
     */
    public static int getRandomPosition(int minValue, int maxValue, List<Integer> excludeValues) {
        List<Integer> values = new ArrayList<>();
        for (int i = minValue; i < maxValue; i++) {
            if (!excludeValues.contains(i)) {
                values.add(i);
            }
        }
        return values.get(RANDOM.nextInt(values.size()));
    }

    /**
     * Obtenemos un caracter de manera aleatoria.
     * @return El char
     */
    public static char getRandomCharacter() {
        int position = RANDOM.nextInt(CHARSET.length());
        return CHARSET.charAt(position);
    }

    /**
     * @return Gets a number between 0 and 9
     */
    public static int getRandomNumberZeroToNine() {
        return RANDOM.nextInt(10);
    }

    /**
     * Gets a random number with digits
     * @param digits number of digits
     * @return The random numbers
     */
    public static String getRandomNumber(double digits) {
        ParameterHelper.assertZeroOrNegative((int) digits);
        double counter = digits;
        StringBuilder result = new StringBuilder();
        do {
            double digits2Calc = Math.min(5, counter);
            int max = (int) (Math.pow(10d, digits2Calc) - 1);
            int value = RANDOM.nextInt(max + 1);
            StringBuffer buffer = new StringBuffer();
            buffer.append(value);
            while (buffer.length() <= digits2Calc - 1) {
                buffer.append(RANDOM.nextInt(9));
            }
            counter -= digits2Calc;
            result.append(buffer.toString());
        } while (counter > 0);
        return result.toString();
    }

    /**
     * @return Gets the email verification code
     */
    public static String getMailVerificationCode() {
        return RandomStringUtils.randomAlphanumeric(EMAIL_VERIFICATION_CODE_LENGTH);
    }

    /**
     * @return Gets the delete account code
     */
    public static String getDeleteAccountVerificationCode() {
        return RandomStringUtils.randomAlphabetic(DELETE_ACCOUNT_CODE_LENGTH);
    }

    /**
     * Generate a list of username following a pattern specified by parameters
     * @param pattern Pattern to generate the username list.
     * @return List of username
     */
    public static List<String> generateUsernameList(String pattern) {
        ParameterHelper.assertEmpty(pattern);
        List<String> result = new ArrayList<>();
        int count = 9;
        result.add(pattern);
        do {
            result.add(pattern + getRandomNumber(3));
        } while (--count > 0);
        return result;
    }
}
