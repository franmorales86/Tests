/**
 * Project: RecModel
 * Created by: raulanatol at 30/10/13 17:49
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.model.documents.statistics;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @author raulanatol
 */
@Document
public class KarmaDCoefficient implements Serializable {
    private static final long serialVersionUID = -7849975536817717129L;
    private String id;
    private Double value;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KarmaDCoefficient)) return false;

        KarmaDCoefficient that = (KarmaDCoefficient) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "KarmaDCoefficient{" +
                "id='" + id + '\'' +
                ", value=" + value +
                '}';
    }
}
