package com.upgrad.myntra.service.business;



import com.upgrad.myntra.service.dao.ItemDao;
import com.upgrad.myntra.service.entity.ItemEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemDao itemDao;

    /**
     * The method implements the business logic for getting list of items based on brand and category uuid.
     */
    @Override
    public List<ItemEntity> getItemsByCategoryAndBrand(String brandId, String categoryId) {
        return itemDao.getItemsByCategoryAndBrand(brandId, categoryId);
    }

}
