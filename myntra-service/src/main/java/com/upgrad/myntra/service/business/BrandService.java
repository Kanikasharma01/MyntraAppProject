package com.upgrad.myntra.service.business;





import com.upgrad.myntra.service.entity.BrandEntity;
import com.upgrad.myntra.service.exception.BrandNotFoundException;
import com.upgrad.myntra.service.exception.CategoryNotFoundException;

import java.util.List;

/*
 * This BrandService interface gives the list of all the service that exist in the brand service implementation class.
 * Controller class will be calling the service methods by this interface.
 */
public interface BrandService {

    BrandEntity brandByUUID(String brandId) throws BrandNotFoundException;

    List<BrandEntity> brandsByName(String brandName) throws BrandNotFoundException;

    List<BrandEntity> brandsByRating();

    List<BrandEntity> brandByCategory(String categoryId) throws CategoryNotFoundException;
}
