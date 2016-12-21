/**
 * Project: RecCore
 * Created by: raulanatol at 10/07/13 18:18
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.services.brand;

import com.reclabs.recomendar.common.exceptions.RecIllegalArgumentException;
import com.reclabs.recomendar.model.documents.brand.Brand;
import com.reclabs.recomendar.model.documents.brand.data.BrandData;
import com.reclabs.recomendar.model.repositories.brand.BrandRepository;
import com.reclabs.recomendar.model.services.brand.BrandService;
import com.reclabs.recomendar.model.services.items.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author raulanatol
 */
@Service
public class BrandServiceImpl implements BrandService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BrandServiceImpl.class);

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private ItemService itemService;

    @Override
    public Brand createBrand(Brand brand) {
        LOGGER.info("[Starting creation of brand {}]", brand.getName());
        Brand duplicateBrand = findByName(brand.getName());
        if (duplicateBrand != null) {
            LOGGER.error("[Trying to create a brand with duplicated name: {}]", brand.getName());
            throw new RecIllegalArgumentException("The brand name is duplicated");
        }
        return brandRepository.save(brand);
    }

    @Override
    public List<Brand> findAll() {
        return brandRepository.findAll();
    }

    @Override
    public Brand findBrandById(String brandId) {
        return brandRepository.findOne(brandId);
    }

    @Override
    public Brand findByName(String brandName) {
        return brandRepository.findByName(brandName);
    }

    @Override
    public BrandData getBrandData(Brand brand) {
        BrandData result = new BrandData();
        result.setName(brand.getName());
        result.setId(brand.getId());
        return result;
    }
}
