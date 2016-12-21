/**
 * Project: RecCore
 * Created by: raulanatol at 14/07/13 16:42
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.misc.items;

import java.io.Serializable;

/**
 * Object used to send into the addAffiliationManager controller method
 * @author raulanatol
 */
public class AddAffiliationManagerInputData implements Serializable {
    private static final long serialVersionUID = 423105650221860270L;

    private String brandId;
    private String affiliationAlias;
    private String affiliationProductURL;
    private String price;
    private String currency;

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getAffiliationAlias() {
        return affiliationAlias;
    }

    public void setAffiliationAlias(String affiliationAlias) {
        this.affiliationAlias = affiliationAlias;
    }

    public String getAffiliationProductURL() {
        return affiliationProductURL;
    }

    public void setAffiliationProductURL(String affiliationProductURL) {
        this.affiliationProductURL = affiliationProductURL;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @SuppressWarnings("RedundantIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AddAffiliationManagerInputData)) return false;

        AddAffiliationManagerInputData that = (AddAffiliationManagerInputData) o;

        if (affiliationAlias != null ? !affiliationAlias.equals(that.affiliationAlias) : that.affiliationAlias != null)
            return false;
        if (affiliationProductURL != null ? !affiliationProductURL.equals(that.affiliationProductURL) : that.affiliationProductURL != null)
            return false;
        if (brandId != null ? !brandId.equals(that.brandId) : that.brandId != null) return false;
        if (currency != null ? !currency.equals(that.currency) : that.currency != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = brandId != null ? brandId.hashCode() : 0;
        result = 31 * result + (affiliationAlias != null ? affiliationAlias.hashCode() : 0);
        result = 31 * result + (affiliationProductURL != null ? affiliationProductURL.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        return result;
    }


    @Override
    public String toString() {
        return "AddAffiliationManagerInputData{" +
                "brandId='" + brandId + '\'' +
                ", affiliationAlias='" + affiliationAlias + '\'' +
                ", affiliationProductURL='" + affiliationProductURL + '\'' +
                ", price='" + price + '\'' +
                ", currency='" + currency + '\'' +
                '}';
    }
}
