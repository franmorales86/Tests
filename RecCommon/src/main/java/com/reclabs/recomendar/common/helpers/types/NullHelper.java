/**
 * Project: RecCommon
 * Created by: fjmorales at 04/12/2012 18:55:28
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 * 
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.common.helpers.types;

/**
 * Clase de ayuda para el uso de los nulls.
 * @author raulanatol
 */
public class NullHelper {
	/**
	 * Indicamos true si alguno de los items es null.
	 * @param items
	 * @return Indica si alguno de los items es null
	 */
	public static boolean isAnyNull(final Object... items) {
		boolean result = false;
		for (Object object : items) {
			if (object == null) {
				result = true;
				break;
			}
		}
		return result;
	}

	/**
	 * Nota: No confundir empty con null
	 * @param items
	 * @return True si alguno de los elementos es empty.
	 */
	public static boolean isAnyEmpty(final Object... items) {
		boolean result = false;
		for (Object object : items) {
			if (object == null) {
				result = true;
				break;
			} else if (object instanceof String && StringHelper.isEmpty((String) object)) {
				result = true;
				break;
			}
		}
		return result;
	}

    /**
     * We indicate true if all of the items are null.
     * @param items List of items
     * @return Indicate if all itemas are null
     */
    public static boolean isAllNull(final Object... items) {
        boolean result = true;
        for (Object object : items) {
            if (object != null) {
                result = false;
                break;
            }
        }
        return result;
    }
}
