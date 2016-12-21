/**
 * Project: RecCore
 * Created by: raulanatol at 21/11/2012 19:13:37
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.services;

import com.reclabs.libs.s3imagedriver.S3ImageDriver;
import com.reclabs.recomendar.model.services.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;

/**
 * @author raulanatol
 */
@Service
public class ImageServiceImpl implements ImageService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ImageServiceImpl.class);
    /**
     * Driver para el acceso al servidor de S3
     */
    @Autowired
    private S3ImageDriver s3ImageDriver;

    @Override
    public String createImage2Item(String itemId, String imageURL) {
        return s3ImageDriver.addItemImage(itemId, imageURL);
    }

    @Override
    public String addUserImage(String userId, InputStream imageSource, long contentLength, String contentType) {
        return s3ImageDriver.addUserImage(userId, imageSource, contentLength, contentType);
    }

    @Override
    public String addUserImage(String userId, String imageURL) {
        LOGGER.debug("[Trying to add image to user][UserId: {}][ImageURL: {}]", userId, imageURL);
        return s3ImageDriver.addUserImage(userId, imageURL);
    }

    @Override
    public String addTemporalImage(InputStream imageSource, long contentLength, String contentType) {
        return s3ImageDriver.addTemporalImage(imageSource, contentLength, contentType);
    }

    @Override
    public String addTemporalImage(byte[] image) {
        return s3ImageDriver.addTemporalImage(image);
    }

    @Override
    public void deleteImageFromPath(String imagePath) {
        LOGGER.debug("[Trying to delete an image: {}]", imagePath);
        s3ImageDriver.deleteImageFromPath(imagePath);
    }

//    public String addTemporalImage(byte[] image) {
//        s3ImageDriver.addTemporalImage(image);
//    }

}
