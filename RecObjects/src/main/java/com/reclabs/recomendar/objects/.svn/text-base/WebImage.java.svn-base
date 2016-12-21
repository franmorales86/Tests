/**
 * Project: RecObjects
 * Created by: raulanatol at 08/06/13 16:13
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.objects;

import java.io.Serializable;

/**
 * @author raulanatol
 */
public class WebImage implements Serializable {
    private static final long serialVersionUID = -7099154722559580153L;
    /**
     * Url of the destination.
     */
    private String sourceURL;
    /**
     * Image url to the correct image
     */
    private String imageURL;
    /**
     * Name of the image into the web.
     * alt or name
     */
    private String name;


    public String getSourceURL() {
        return sourceURL;
    }

    public void setSourceURL(String sourceURL) {
        this.sourceURL = sourceURL;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WebImage)) return false;

        WebImage webImage = (WebImage) o;

        if (imageURL != null ? !imageURL.equals(webImage.imageURL) : webImage.imageURL != null) return false;
        if (name != null ? !name.equals(webImage.name) : webImage.name != null) return false;
        if (sourceURL != null ? !sourceURL.equals(webImage.sourceURL) : webImage.sourceURL != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sourceURL != null ? sourceURL.hashCode() : 0;
        result = 31 * result + (imageURL != null ? imageURL.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "WebImage{" +
                "sourceURL='" + sourceURL + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
