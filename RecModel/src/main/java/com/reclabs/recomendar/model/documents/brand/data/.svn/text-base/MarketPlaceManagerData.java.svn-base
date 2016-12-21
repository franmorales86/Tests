/**
 * Project: RecModel
 * Created by: raulanatol at 13/07/13 19:30
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.model.documents.brand.data;

import com.reclabs.recomendar.model.documents.brand.MarketPlaceManager;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @author raulanatol
 */
@Document
public class MarketPlaceManagerData implements Serializable {
    private static final long serialVersionUID = -1927920449984489398L;

    private MarketPlaceManager marketPlaceManager;

    /**
     * Number of elements on stock.
     */
    private Long stock;

    //In the future add dimensions, weight....


    public MarketPlaceManager getMarketPlaceManager() {
        return marketPlaceManager;
    }

    public void setMarketPlaceManager(MarketPlaceManager marketPlaceManager) {
        this.marketPlaceManager = marketPlaceManager;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MarketPlaceManagerData)) return false;

        MarketPlaceManagerData that = (MarketPlaceManagerData) o;

        return !(marketPlaceManager != null ? !marketPlaceManager.equals(that.marketPlaceManager) : that.marketPlaceManager != null) && !(stock != null ? !stock.equals(that.stock) : that.stock != null);

    }

    @Override
    public int hashCode() {
        int result = marketPlaceManager != null ? marketPlaceManager.hashCode() : 0;
        result = 31 * result + (stock != null ? stock.hashCode() : 0);
        return result;
    }


    @Override
    public String toString() {
        return "MarketPlaceManagerData{" +
                "marketPlaceManager=" + marketPlaceManager +
                ", stock=" + stock +
                '}';
    }
}
