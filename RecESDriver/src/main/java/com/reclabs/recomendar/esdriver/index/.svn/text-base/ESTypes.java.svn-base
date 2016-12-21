/**
 * Project: RecESDriver
 * Created by: Fran at 27/06/13 16:18
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 * <p/>
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.esdriver.index;

/**
 * @author fjmorales
 */
public enum ESTypes {

    /**
     * Represents a item of recommendation
     */
    RECOMMENDATIONS("recommend"),
    /**
     * Represents a user of recommendation
     */
    RECUSER("recuser"),
    /**
     * Represents a item that want a user
     */
    ITEMWANT("want"),
    /**
     * Represents a item that have a user
     */
    ITEMHAVE("have"),
    /**
     * Represents a item vip
     */
    VIP("vip");

    private String type;

    /**
     * @param type
     */
    private ESTypes(String type) {
        this.type = type;
    }

    /**
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }
}
