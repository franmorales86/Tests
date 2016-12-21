/**
 * Project: RecModel
 * Created by: raulanatol at 10/07/13 10:33
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.model.documents.brand;

import com.reclabs.recomendar.model.types.shop.VendorRegionType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @author raulanatol
 */
@Document
public class MarketPlaceManager implements Serializable {
    private static final long serialVersionUID = 1841951387907133232L;

    @Id
    private String id;

    /**
     * {BRAND}_MARKETPLACE_{VENDOR-REGION}
     */
    private String alias;

    private VendorRegionType vendorRegionType;

    private Boolean enabled;

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
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
    public String toString() {
        return "MarketPlaceManager{" +
                "id='" + id + '\'' +
                ", alias='" + alias + '\'' +
                ", vendorRegionType=" + vendorRegionType +
                ", enabled=" + enabled +
                '}';
    }

    public VendorRegionType getVendorRegionType() {
        return vendorRegionType;
    }

    public void setVendorRegionType(VendorRegionType vendorRegionType) {
        this.vendorRegionType = vendorRegionType;
    }


    @SuppressWarnings("RedundantIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MarketPlaceManager)) return false;

        MarketPlaceManager that = (MarketPlaceManager) o;

        if (alias != null ? !alias.equals(that.alias) : that.alias != null) return false;
        if (enabled != null ? !enabled.equals(that.enabled) : that.enabled != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (vendorRegionType != that.vendorRegionType) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (alias != null ? alias.hashCode() : 0);
        result = 31 * result + (vendorRegionType != null ? vendorRegionType.hashCode() : 0);
        result = 31 * result + (enabled != null ? enabled.hashCode() : 0);
        return result;
    }
}
