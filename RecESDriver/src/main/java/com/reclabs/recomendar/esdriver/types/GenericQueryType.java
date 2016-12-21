package com.reclabs.recomendar.esdriver.types;

/**
 * Project: RecESDriver
 * Created by: raulanatol at 13/05/13 13:00
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 * <p/>
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
public enum GenericQueryType {
    /**
     * Wildcard
     */
    WILDCARD("wildcard"),
    /**
     * Text
     */
    TEXT("text"),
    /**
     * Match
     */
    MATCH("match");


    private String name;

    /**
     * @param name
     */
    private GenericQueryType(String name) {
        this.name = name;
    }

    /**
     * @return Name of the field type
     */
    public String getName() {
        return name;
    }

}
