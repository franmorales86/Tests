/**
 * Project: RecCore
 * Created by: fjmorales at 17/02/2013 17:49:41
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 * 
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.common.exceptions;

import com.reclabs.recomendar.common.exceptions.generic.UserException;

/**
 * Excepcion lanzada cuando el producto no existe
 * @author fjmorales
 * 
 */
public class ProductNotFoundException extends UserException {

	private static final long serialVersionUID = -6980795394564489322L;

	/**
	 * @param userMessage
	 */
	public ProductNotFoundException(final String userMessage) {
		super(userMessage);
	}

}
