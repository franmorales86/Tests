/**
 * Project: RecCommon
 * Created by: raulanatol at 24/10/13 13:11
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.common.helpers;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author raulanatol
 */
public class JSONHelperTest {

    @Test
    public void fromObjectToJSON() throws Exception {
        DummyClass dummyClass = new DummyClass();
        dummyClass.name = "NameValue";
        dummyClass.description = "DescriptionValue";
        String expected = "{\"name\":\"NameValue\",\"description\":\"DescriptionValue\"}";
        assertThat(JSONHelper.toJSON(dummyClass), is(expected));
    }

    @Test
    public void fromJSONToObject() throws Exception {
        String data = "{\"name\":\"NameValue\",\"description\":\"DescriptionValue\"}";
        DummyClass expected = new DummyClass();
        expected.name = "NameValue";
        expected.description = "DescriptionValue";
        assertThat(JSONHelper.fromJSON(data, DummyClass.class), is(expected));
    }

    private class DummyClass {
        public String name;
        public String description;

        @Override
        public String toString() {
            return "DummyClass{" +
                    "name='" + name + '\'' +
                    ", description='" + description + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof DummyClass)) return false;

            DummyClass that = (DummyClass) o;

            if (description != null ? !description.equals(that.description) : that.description != null) return false;
            if (name != null ? !name.equals(that.name) : that.name != null) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + (description != null ? description.hashCode() : 0);
            return result;
        }
    }
}
