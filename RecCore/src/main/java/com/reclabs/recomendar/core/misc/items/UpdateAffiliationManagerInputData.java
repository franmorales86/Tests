/**
 * Project: RecCore
 * Created by: raulanatol at 18/07/13 17:44
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.misc.items;

import com.reclabs.recomendar.common.types.RecMoney;

import java.io.Serializable;

/**
 * @author raulanatol
 */
public class UpdateAffiliationManagerInputData implements Serializable {
    private static final long serialVersionUID = -5160645102828922282L;

    private String affiliationManagerId;
    private String urlProductToManager;
    private RecMoney price;
    private String alias;


    public String getUrlProductToManager() {
        return urlProductToManager;
    }

    public void setUrlProductToManager(String urlProductToManager) {
        this.urlProductToManager = urlProductToManager;
    }

    public RecMoney getPrice() {
        return price;
    }

    public void setPrice(RecMoney price) {
        this.price = price;
    }

    public String getAffiliationManagerId() {
        return affiliationManagerId;
    }

    public void setAffiliationManagerId(String affiliationManagerId) {
        this.affiliationManagerId = affiliationManagerId;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UpdateAffiliationManagerInputData)) return false;

        UpdateAffiliationManagerInputData that = (UpdateAffiliationManagerInputData) o;

        if (affiliationManagerId != null ? !affiliationManagerId.equals(that.affiliationManagerId) : that.affiliationManagerId != null)
            return false;
        if (alias != null ? !alias.equals(that.alias) : that.alias != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (urlProductToManager != null ? !urlProductToManager.equals(that.urlProductToManager) : that.urlProductToManager != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = affiliationManagerId != null ? affiliationManagerId.hashCode() : 0;
        result = 31 * result + (urlProductToManager != null ? urlProductToManager.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (alias != null ? alias.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UpdateAffiliationManagerInputData{" +
                "affiliationManagerId='" + affiliationManagerId + '\'' +
                ", urlProductToManager='" + urlProductToManager + '\'' +
                ", price=" + price +
                ", alias='" + alias + '\'' +
                '}';
    }
}
