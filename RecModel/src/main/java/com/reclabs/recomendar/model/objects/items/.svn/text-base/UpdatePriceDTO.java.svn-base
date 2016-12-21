/**
 * Project: RecModel
 * Created by: raulanatol at 20/08/13 13:47
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.model.objects.items;

/**
 * @author raulanatol
 */
public class UpdatePriceDTO {
    private String money;

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UpdatePriceDTO)) return false;

        UpdatePriceDTO that = (UpdatePriceDTO) o;

        return !(money != null ? !money.equals(that.money) : that.money != null);

    }

    @Override
    public int hashCode() {
        return money != null ? money.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "UpdatePriceDTO{" +
                "money='" + money + '\'' +
                '}';
    }
}
