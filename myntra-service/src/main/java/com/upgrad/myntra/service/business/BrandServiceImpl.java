package com.upgrad.myntra.service.business;



import com.upgrad.myntra.service.dao.BrandDao;
import com.upgrad.myntra.service.dao.CategoryDao;
import com.upgrad.myntra.service.entity.BrandEntity;
import com.upgrad.myntra.service.exception.BrandNotFoundException;
import com.upgrad.myntra.service.exception.CategoryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandDao brandDao;

    @Autowired
    private CategoryDao categoryDao;

    /**
     * The method implements the business logic for getting brand details by brand uuid.
     */
    @Override
    public BrandEntity brandByUUID(String brandId) throws BrandNotFoundException {
        return brandDao.brandByUUID(brandId);
    }

    /**
     * The method implements the business logic for getting brands by brand name.
     */
    @Override
    public List<BrandEntity> brandsByName(String brandName) throws BrandNotFoundException {
        return null;
    }

    /**
     * The method implements the business logic for getting all brands ordered by their rating.
     */
    @Override

    public List<BrandEntity> brandsByRating() {
        return null;
    }

    /**
     * The method implements the business logic for getting brands by their category.
     */
    @Override
    public List<BrandEntity> brandByCategory(String categoryId) throws CategoryNotFoundException {
        return null;
    }

}
