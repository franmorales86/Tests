/**
 * Project: RecCommon
 * Created by: fjmorales at 04/12/2012 19:16:39
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.common.helpers.misc;

import com.reclabs.recomendar.common.exceptions.generic.ErrorException;
import com.reclabs.recomendar.common.types.FixedProperties;
import com.reclabs.recomendar.common.types.ImageFormat;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;

/**
 * Clase de apoyo para el uso de imágenes.
 * @author rmorales
 */
public class ImageHelper {
    /**
     * Listado de urls que indican que la url es de recomendar.
     */
    private static final String[] KNOWN_URL_IMAGES = {"http://images.recstatic.com", "recstatic.com", "recitems.s3.amazonaws.com"};
    private static final String IMAGE_PATH_CONVERSION = "http://%1$s/%2$s/%3$s";
    private static final String ASGARD_URL = "rec-asgard.elasticbeanstalk.com";

    /**
     * Dada la id del usuario de recomendar y un formato de imagen obtenemos la
     * url de esa imagen en el bucket de recomendar.
     * @param recUserId
     * @param format
     * @return Url de la imagen en el bucket de recomendar
     */
    public static String getImageUrlByUserIdAndFormat(final String recUserId, final ImageFormat format) {
        return String.format(IMAGE_PATH_CONVERSION, FixedProperties.S3_BUCKET_IMAGE_USER_END_POINT, format.getDirectoryPath(), recUserId);
    }

    /**
     * Dada la id de un usuario de recomendar y un formato de imagen obtenemos
     * la url de esa imagen en el servidor de Asgard.
     * @param recUserId
     * @param format
     * @return Url de la imagen en el servidor de Asgard
     */
    public static String getAsgardUrl(final String recUserId, final ImageFormat format) {
        return String.format(IMAGE_PATH_CONVERSION, ASGARD_URL, format.getDirectoryPath(), recUserId);
    }

    /**
     * Dados el id del usuario y un formato de imágen obtenemos la ruta en la
     * que se debe guardar la imagen de ese usuario y ese formato.
     * @param recUserId
     * @param format
     * @return Ruta donde guardar la imagen
     */
    public static String getBucketDir(final String recUserId, final ImageFormat format) {
        return format.getDirectoryPath() + "/" + recUserId;
    }

    /**
     * Dada la url de una imagen indicamos si forma parte o no de una imagen de Recomendar.
     * @param imageURL La url de la imagen.
     * @return True en caso de que la imagen sea de recomendar.
     */
    public static boolean isRecomendarImage(String imageURL) {
        boolean result = false;
        for (String pattern : KNOWN_URL_IMAGES) {
            if (imageURL.contains(pattern)) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     *
     * @param imageURL
     * @return
     */
    public static Dimension getImageDimensionsByURL(String imageURL) {
        try {
            BufferedImage image = ImageIO.read(new URL(imageURL));
            return new Dimension(image.getWidth(), image.getHeight());
        } catch (Exception e) {
            throw new ErrorException("ImageURL error", e);
        }
    }
}
