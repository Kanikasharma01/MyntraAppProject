package com.upgrad.myntra.api.controllers;


import com.upgrad.myntra.service.business.CategoryService;
import com.upgrad.myntra.service.entity.CategoryEntity;
import com.upgrad.myntra.service.entity.ItemEntity;
import com.upgrad.myntra.service.exception.CategoryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.upgrad.myntra.api.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * A controller method to get all address from the database.
     *
     * @param //categoryId - The uuid of the category whose detail is asked from the database..
     * @return - ResponseEntity<CategoryDetailsResponse> type object along with Http status OK.
     * @throws //CategoryNotFoundException
     */
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDetailsResponse>getCategoryById(@PathVariable("categoryId") String categoryId)throws Exception{
        CategoryEntity categoryEntity = categoryService.getCategoryById(categoryId);
        List<ItemEntity> itemEntityList= categoryEntity.getItems();
        final List<ItemList> itemLists = itemEntityList.stream()
                .map(developer -> new ItemList().id(UUID.fromString(developer.getUuid())).itemName(developer.getItemName()).price(developer.getPrice())).collect(Collectors.toList());

        CategoryDetailsResponse cl=new CategoryDetailsResponse().id(UUID.fromString(categoryEntity.getUuid())).categoryName(categoryEntity.getCategoryName()).itemList(itemLists);
        return new ResponseEntity<CategoryDetailsResponse>(cl, HttpStatus.OK);
    }

    /**
     * A controller method to get all categories from the database.
     *
     * @return - ResponseEntity<CategoriesListResponse> type object along with Http status OK.
     */
    @GetMapping("/")
    public ResponseEntity<CategoriesListResponse>getAllCategoriesOrderedByName()throws Exception {
        List<CategoryEntity> list = categoryService.getAllCategoriesOrderedByName();
        List<CategoryListResponse> categoryListResponses = new ArrayList<CategoryListResponse>();
        for(int i=0;i<list.size();i++){
            CategoryListResponse categoryListResponse=new CategoryListResponse().id(UUID.fromString(list.get(i).getUuid())).categoryName(list.get(i).getCategoryName());
            categoryListResponses.add(categoryListResponse);
        }

        final CategoriesListResponse categoryLists = new CategoriesListResponse().categories(categoryListResponses);
        return new ResponseEntity<CategoriesListResponse>(categoryLists, HttpStatus.OK);
    }

}
