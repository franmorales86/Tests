/**
 * Project: RecModel
 * Created by: raulanatol at 10/07/13 10:42
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.model.documents.brand.data;

import com.reclabs.recomendar.common.types.RecMoney;
import com.reclabs.recomendar.model.types.shop.VendorRegionType;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @author raulanatol
 */
@Document
public class AffiliationManagerData implements Serializable {
    private static final long serialVersionUID = -8025709624508885097L;

    private String id;
    private String alias;
    private VendorRegionType vendorRegionType;
    private Boolean enabled;
    /**
     * The url necessary to generate the result url to the affiliation shop.
     */
    private String urlProductToManager;

    /**
     * Result of the url.
     */
    private String urlResult;

    /**
     * The price of the item in this affiliation manager
     */
    private RecMoney price;


    public String getUrlProductToManager() {
        return urlProductToManager;
    }

    public void setUrlProductToManager(String urlProductToManager) {
        this.urlProductToManager = urlProductToManager;
    }

    public String getUrlResult() {
        return urlResult;
    }

    public void setUrlResult(String urlResult) {
        this.urlResult = urlResult;
    }

    public RecMoney getPrice() {
        return price;
    }

    public void setPrice(RecMoney price) {
        this.price = price;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public VendorRegionType getVendorRegionType() {
        return vendorRegionType;
    }

    public void setVendorRegionType(VendorRegionType vendorRegionType) {
        this.vendorRegionType = vendorRegionType;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AffiliationManagerData)) return false;

        AffiliationManagerData that = (AffiliationManagerData) o;

        if (alias != null ? !alias.equals(that.alias) : that.alias != null) return false;
        if (enabled != null ? !enabled.equals(that.enabled) : that.enabled != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (urlProductToManager != null ? !urlProductToManager.equals(that.urlProductToManager) : that.urlProductToManager != null)
            return false;
        return !(urlResult != null ? !urlResult.equals(that.urlResult) : that.urlResult != null) && vendorRegionType == that.vendorRegionType;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (alias != null ? alias.hashCode() : 0);
        result = 31 * result + (vendorRegionType != null ? vendorRegionType.hashCode() : 0);
        result = 31 * result + (enabled != null ? enabled.hashCode() : 0);
        result = 31 * result + (urlProductToManager != null ? urlProductToManager.hashCode() : 0);
        result = 31 * result + (urlResult != null ? urlResult.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AffiliationManagerData{" +
                "id='" + id + '\'' +
                ", alias='" + alias + '\'' +
                ", vendorRegionType=" + vendorRegionType +
                ", enabled=" + enabled +
                ", urlProductToManager='" + urlProductToManager + '\'' +
                ", urlResult='" + urlResult +
                '}';
    }
}
