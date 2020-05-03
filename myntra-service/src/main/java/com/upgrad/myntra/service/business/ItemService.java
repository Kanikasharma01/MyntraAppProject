package com.upgrad.myntra.service.business;



import com.upgrad.myntra.service.entity.ItemEntity;

import java.util.List;

/*
 * This ItemService interface gives the list of all the service that exist in the item service implementation class.
 * Controller class will be calling the service methods by this interface.
 */
public interface ItemService {

    List<ItemEntity> getItemsByCategoryAndBrand(String brandId, String categoryId);
}
