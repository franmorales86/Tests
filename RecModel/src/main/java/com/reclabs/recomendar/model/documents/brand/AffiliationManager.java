/**
 * Project: RecModel
 * Created by: raulanatol at 10/07/13 10:29
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.model.documents.brand;

import com.reclabs.recomendar.model.documents.brand.data.AffiliationProviderData;
import com.reclabs.recomendar.model.documents.brand.data.BrandData;
import com.reclabs.recomendar.model.types.shop.VendorRegionType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @author raulanatol
 */
@Document
public class AffiliationManager implements Serializable {
    private static final long serialVersionUID = -8083984879110356208L;

    @Id
    private String id;

    /**
     * {BRAND}_{PROVIDER}_{VENDOR-REGION}
     */
    private String alias;

    private String domain;

    private String pattern;

    private String eliminatePattern;

    private VendorRegionType vendorRegionType;

    private Boolean enabled;

    private AffiliationProviderData affiliationProviderData;

    private BrandData brandData;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getEliminatePattern() {
        return eliminatePattern;
    }

    public void setEliminatePattern(String eliminatePattern) {
        this.eliminatePattern = eliminatePattern;
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

    public AffiliationProviderData getAffiliationProviderData() {
        return affiliationProviderData;
    }

    public void setAffiliationProviderData(AffiliationProviderData affiliationProviderData) {
        this.affiliationProviderData = affiliationProviderData;
    }

    public BrandData getBrandData() {
        return brandData;
    }

    public void setBrandData(BrandData brandData) {
        this.brandData = brandData;
    }
}
