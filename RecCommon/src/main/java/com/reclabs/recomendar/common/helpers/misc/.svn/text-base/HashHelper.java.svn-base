/**
 * Project: RecCommon
 * Created by: fjmorales at 04/12/2012 19:12:01
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.common.helpers.misc;

import com.reclabs.recomendar.common.exceptions.generic.ErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * Clase de ayuda para el uso de hash.
 */
public class HashHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(HashHelper.class);
    private static final String ALGORITHM = "SHA-256";
    private static final String CHAR_SET_UTF8 = "UTF-8";

    /**
     * Codificamos la cadena introducida en formato SHA-256
     * @param code Cadena a codificar
     * @return Cadena codificada
     * @throws ErrorException Excepción lanzada cuando no se indica uun algoritmo hash inválido o cuando el
     *                        encoding no es soportado (o no es válido).
     */
    public static String encode(final String code) throws ErrorException {
        try {
            MessageDigest sha = MessageDigest.getInstance(HashHelper.ALGORITHM);
            sha.update(code.getBytes(HashHelper.CHAR_SET_UTF8));
            return Arrays.toString(sha.digest());
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("[Trying encode with code: {}][Exception: {}]", code, e);
            throw new ErrorException("No se encuentra el algoritmo de codificación requerido.", e);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("[Trying encode with code: {}][Exception: {}]", code, e);
            throw new ErrorException("Formato de codificación de caracteres no soportado.", e);
        }
    }

    /**
     * Encode the code parameter into codification algorithmic.
     * @param code the code to encode
     * @return The encoded string.
     * @throws ErrorException
     */
    public static String encodeStringMode(final String code) throws ErrorException {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(HashHelper.ALGORITHM);
            messageDigest.update(code.getBytes(HashHelper.CHAR_SET_UTF8));
            byte[] digest = messageDigest.digest();
            StringBuilder buffer = new StringBuilder();
            for (byte aDigest : digest) {
                buffer.append(String.format("%02x", aDigest));
            }
            return buffer.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new ErrorException("No se encuentra el algoritmo de codificación requerido.", e);
        } catch (UnsupportedEncodingException e) {
            throw new ErrorException("Formato de codificación de caracteres no soportado.", e);
        }
    }
}
