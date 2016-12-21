/**
 * Project: RecModel
 * Created by: raulanatol at 27/09/13 21:22
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.model.objects.utils;

/**
 * @author raulanatol
 */
public class ShortURLDTO {
    private String url;
    private String webName;
    private String linkType;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWebName() {
        return webName;
    }

    public void setWebName(String webName) {
        this.webName = webName;
    }

    public String getLinkType() {
        return linkType;
    }

    public void setLinkType(String linkType) {
        this.linkType = linkType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShortURLDTO)) return false;

        ShortURLDTO that = (ShortURLDTO) o;

        if (linkType != null ? !linkType.equals(that.linkType) : that.linkType != null) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;
        return !(webName != null ? !webName.equals(that.webName) : that.webName != null);

    }

    @Override
    public int hashCode() {
        int result = url != null ? url.hashCode() : 0;
        result = 31 * result + (webName != null ? webName.hashCode() : 0);
        result = 31 * result + (linkType != null ? linkType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ShortURLDTO{" +
                "url='" + url + '\'' +
                ", webName='" + webName + '\'' +
                ", linkType='" + linkType + '\'' +
                '}';
    }
}
