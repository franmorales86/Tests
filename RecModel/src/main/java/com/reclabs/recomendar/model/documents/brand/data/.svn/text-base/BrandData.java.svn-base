/**
 * Project: RecModel
 * Created by: raulanatol at 17/07/13 16:17
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.model.documents.brand.data;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @author raulanatol
 */
@Document
public class BrandData implements Serializable {
    private static final long serialVersionUID = -1364552044806537654L;

    private String id;
    private String name;

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BrandData)) return false;

        BrandData brandData = (BrandData) o;

        if (id != null ? !id.equals(brandData.id) : brandData.id != null) return false;
        return !(name != null ? !name.equals(brandData.name) : brandData.name != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BrandData{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
