package com.upgrad.myntra.api.controllers;



import com.upgrad.myntra.service.business.BrandService;
import com.upgrad.myntra.service.business.CategoryService;
import com.upgrad.myntra.service.business.CustomerService;
import com.upgrad.myntra.service.business.ItemService;
import com.upgrad.myntra.service.entity.BrandEntity;
import com.upgrad.myntra.service.entity.CategoryEntity;
import com.upgrad.myntra.service.entity.ItemEntity;
import com.upgrad.myntra.service.exception.BrandNotFoundException;
import com.upgrad.myntra.service.exception.CategoryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.upgrad.myntra.api.model.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RequestMapping("/brand")
@RestController
public class BrandController {

	@Autowired private BrandService brandService;

	@Autowired private ItemService itemService;

	@Autowired private CategoryService categoryService;

	@Autowired private CustomerService customerService;

	/**
	 * A controller method to get a Brand details from the database.
	 *
	 * @param brandId - The uuid of the Brand whose details has to be fetched from the database.
	 * @return - ResponseEntity<BrandDetailsResponse> type object along with Http status OK.
	 *
	 *
	 * @throws BrandNotFoundException
	 */

	/**
	 * A controller method to get Brand details by its name from the database.
	 *
	 * @param brandName - The name of the Brand whose details has to be fetched from the database.
	 * @return - ResponseEntity<BrandListResponse> type object along with Http status OK.
	 * @throws BrandNotFoundException
	 */

	/**
	 * A controller method to get all Brand by a category from the database.
	 *
	 * @param categoryId - The uuid of the category under which the Brand list has to be fetched from the database.
	 * @return - ResponseEntity<BrandListResponse> type object along with Http status OK.
	 * @throws CategoryNotFoundException
	 */


	/**
	 * A controller method to get all Brand from the database.
	 *
	 * @return - ResponseEntity<BrandListResponse> type object along with Http status OK.
	 */

}
