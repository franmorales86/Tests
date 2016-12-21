/**
 * Project: RecModel
 * Created by: raulanatol at 10/07/13 10:25
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.model.documents.brand;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @author raulanatol
 */
@Document
public class Brand implements Serializable {
    private static final long serialVersionUID = 4230113348531147301L;

    @Id
    private String id;

    private String name;

    private String brandURL;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrandURL() {
        return brandURL;
    }

    public void setBrandURL(String brandURL) {
        this.brandURL = brandURL;
    }
}