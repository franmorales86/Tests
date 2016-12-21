/**
 * Project: RecModel
 * Created by: raulanatol at 28/04/2013 16:03:17
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.model.documents.items;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @author raulanatol
 */
@SuppressWarnings("WeakerAccess")
@Document
public class URLPath implements Serializable {
    private static final long serialVersionUID = -3280707527939294354L;

    private String url;
    private String name;

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "URLPath{" +
                "url='" + url + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof URLPath)) return false;

        URLPath urlPath = (URLPath) o;

        if (name != null ? !name.equals(urlPath.name) : urlPath.name != null) return false;
        return !(url != null ? !url.equals(urlPath.url) : urlPath.url != null);

    }

    @Override
    public int hashCode() {
        int result = url != null ? url.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
