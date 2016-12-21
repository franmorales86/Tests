/**
 * Project: RecCommon
 * Created by: fjmorales at 04/12/2012 20:25:39
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 * 
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.common.types;

import com.reclabs.recomendar.common.helpers.types.StringHelper;

/**
 * Representan los diferentes tipos de formatos de imágenes usandos en recomendar.
 * @author raulanatol
 */
public enum ImageFormat {
	/**
	 * 
	 */
	ICON("icon", 57, 57),
	/**
	 * 
	 */
	AVATAR("avatar", 128, 128),
	/**
	 * El default es un formato que no realiza conversión es el de la imagen tal cual.
	 */
	DEFAULT("default", 0, 0);

	private String directoryPath;
	private int width;
	private int height;

	/**
	 * 
	 * @param directoryPath
	 * @param width
	 * @param height
	 */
	private ImageFormat(final String directoryPath, final int width, final int height) {
		this.directoryPath = directoryPath;
		this.width = width;
		this.height = height;
	}

	/**
	 * 
	 * @return Ruta del directorio de la imagen
	 */
	public String getDirectoryPath() {
		return this.directoryPath;
	}

	/**
	 * @return Ancho de la imagen
	 */
	public int getWidth() {
		return this.width;
	}

	/**
	 * @return Altura de la imagen
	 */
	public int getHeight() {
		return this.height;
	}

	/**
	 * Devuelve la imagen asociada al nombre pasado por parametro
	 * @param name
	 * @return Imagen asociada
	 */
	public static ImageFormat getByName(final String name) {
		ImageFormat result = DEFAULT;
		for (ImageFormat imageFormat : values()) {
			if (StringHelper.equals(imageFormat.name(), name)) {
				result = imageFormat;
				break;
			}
		}
		return result;
	}

}
