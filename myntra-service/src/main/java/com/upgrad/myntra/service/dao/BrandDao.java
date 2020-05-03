package com.upgrad.myntra.service.dao;




import com.upgrad.myntra.service.entity.BrandEntity;

import java.util.List;

/*
 * This BrandDao interface gives the list of all the dao methods that exist in the brand dao implementation class.
 * Service class will be calling the dao methods by this interface.
 */
public interface BrandDao {

    BrandEntity brandByUUID(String brandId);
    List<BrandEntity> brandByName(String brandName);
    List<BrandEntity> brandByRating();
    List<BrandEntity> brandByCategory(String categoryId);
}
