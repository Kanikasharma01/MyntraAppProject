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

@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * A controller method to get all address from the database.
     *
     * @param categoryId - The uuid of the category whose detail is asked from the database..
     * @return - ResponseEntity<CategoryDetailsResponse> type object along with Http status OK.
     * @throws CategoryNotFoundException
     */

    /**
     * A controller method to get all categories from the database.
     *
     * @return - ResponseEntity<CategoriesListResponse> type object along with Http status OK.
     */

}
