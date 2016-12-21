/**
 * Project: RecModel
 * Created by: raulanatol at 10/07/13 18:17
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.model.services.brand;

import com.reclabs.recomendar.model.documents.brand.Brand;
import com.reclabs.recomendar.model.documents.brand.data.BrandData;

import java.util.List;

/**
 * @author raulanatol
 */
public interface BrandService {
    /**
     * Create a brand
     * @param brand the brand element
     * @return The brand created
     */
    Brand createBrand(Brand brand);

    /**
     * @return Get all brands in the database
     */
    List<Brand> findAll();

    /**
     * Find a brand with the id passed through parameters
     * @param brandId The id of the brand
     * @return The brand found
     */
    Brand findBrandById(String brandId);

    /**
     * Find a brand with the name passed through parameters
     * @param brandName The brand name
     * @return The brand found
     */
    Brand findByName(String brandName);

    /**
     * Get BrandData from a brand item
     * @param brand the brand
     * @return The brand data
     */
    BrandData getBrandData(Brand brand);
}
