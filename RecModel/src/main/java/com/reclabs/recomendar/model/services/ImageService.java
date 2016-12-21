/**
 * Project: RecModel
 * Created by: raulanatol at 21/11/2012 19:18:07
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.model.services;

import java.io.InputStream;

/**
 * Servicio de uso de imágenes de recomendar.
 * @author raulanatol
 */
public interface ImageService {
    /**
     * Se creada la imagen con url y tipo especificado en el servidor de imágenes obteniendo la url del CDN de
     * recomendar.
     * @param itemId If of the item
     * @param imageURL La url de la imagen source.
     * @return La url de la imagen en el CDN de recomendar.
     */
    String createImage2Item(String itemId, String imageURL);

    /**
     * Añadimos una imagen al servidor de imágenes de usuarios.
     * @param userId La id del usuario
     * @param imageSource El inputStream de la imagen.
     * @param contentLength El tamaño de la imagen.
     * @param contentType El tipo de la imagen.
     * @return La url de imagen en el servidor de imágenes.
     */
    String addUserImage(String userId, InputStream imageSource, long contentLength, String contentType);

    /**
     * Add image to user from a specified imagen url.
     * @param userId Id of the user
     * @param imageURL URL of the image to set.
     * @return The new url of the user.
     */
    String addUserImage(String userId, String imageURL);

    /**
     * Add image to temporal directory and return the url.
     * @param imageSource The image source
     * @param contentLength The content length
     * @param contentType Type of content
     * @return The url of the imagen uploaded
     */
    String addTemporalImage(InputStream imageSource, long contentLength, String contentType);

    /**
     * Add image to temporal directory and return the url.
     * @param image The image
     * @return The url of the image
     */
    String addTemporalImage(byte[] image);

    /**
     * Delete the image from a specific path
     * @param imagePath path of the image to delete
     */
    void deleteImageFromPath(String imagePath);
}
