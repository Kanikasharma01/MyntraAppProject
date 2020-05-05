package com.upgrad.myntra.api.controllers;



import com.upgrad.myntra.service.business.BrandService;
import com.upgrad.myntra.service.business.CategoryService;
import com.upgrad.myntra.service.business.CustomerService;
import com.upgrad.myntra.service.business.ItemService;
import com.upgrad.myntra.service.entity.AddressEntity;
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
import java.util.ArrayList;
import java.util.Iterator;
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
	@GetMapping("{brandId}")
	public ResponseEntity<BrandDetailsResponse> BrandById(@PathVariable(name = "brandId",required = false) String brandId) throws BrandNotFoundException {
		if(brandId==null)
			throw new BrandNotFoundException("RNF-002","Brand id field should not be empty");
		BrandEntity brandEntity = this.brandService.brandByUUID(brandId);
		BrandDetailsResponse brandDetailsResponse = new BrandDetailsResponse();

		AddressEntity brandAddress = brandEntity.getAddress();
		BrandDetailsResponseAddressState state = new BrandDetailsResponseAddressState();
		state.id(UUID.fromString(brandAddress.getState().getUuid())).stateName(brandAddress.getState().getStateName());

		BrandDetailsResponseAddress responseAddress = new BrandDetailsResponseAddress();
		responseAddress.id(UUID.fromString(brandAddress.getUuid())).flatBuildingName(brandAddress.getFlatBuilNo()).locality(brandAddress.getLocality()).city(brandAddress.getCity()).pincode(brandAddress.getPincode()).state(state);

		brandDetailsResponse.id(UUID.fromString(brandEntity.getUuid())).brandName(brandEntity.getbrandName()).address(responseAddress).customerRating(BigDecimal.valueOf(brandEntity.getCustomerRating())).numberCustomersRated(brandEntity.getNumberCustomersRated());
		List<CategoryEntity> brandCategories = this.categoryService.getCategoriesByBrand(brandEntity.getUuid());
		List<CategoryList> categoryListArrayList = new ArrayList();
		Iterator var8 = brandCategories.iterator();

		while(var8.hasNext()) {
			CategoryEntity category = (CategoryEntity)var8.next();
			CategoryList categoryList = new CategoryList();
			categoryList.id(UUID.fromString(category.getUuid())).categoryName(category.getCategoryName());
			List<ItemEntity> itemEntities = this.itemService.getItemsByCategoryAndBrand(brandEntity.getUuid(), category.getUuid());
			List<ItemList> itemListArrayList = new ArrayList();
			Iterator var13 = itemEntities.iterator();

			while(var13.hasNext()) {
				ItemEntity itemEntity = (ItemEntity)var13.next();
				ItemList itemList = new ItemList();
				itemList.id(UUID.fromString(itemEntity.getUuid())).itemName(itemEntity.getItemName()).price(itemEntity.getPrice());
				itemListArrayList.add(itemList);
			}

			categoryList.itemList(itemListArrayList);
			categoryListArrayList.add(categoryList);
		}

		brandDetailsResponse.categories(categoryListArrayList);
		return new ResponseEntity(brandDetailsResponse, HttpStatus.OK);
	}

	/**
	 * A controller method to get Brand details by its name from the database.
	 *
	 * @param brandName - The name of the Brand whose details has to be fetched from the database.
	 * @return - ResponseEntity<BrandListResponse> type object along with Http status OK.
	 * @throws BrandNotFoundException
	 */
	@GetMapping("/name/{brandName}")
	public ResponseEntity<BrandListResponse> getBrandByBrandName(@PathVariable(name = "brandName",required = false) String brandName) throws BrandNotFoundException {
		if(brandName==null)
			throw new BrandNotFoundException("RNF-003","Brand name field should not be empty");
		List<BrandEntity> brandEntities = brandService.brandsByName(brandName);
		BrandListResponse brandListResponse = getBrandListResponseFromBrandEntities(brandEntities);
		return new ResponseEntity(brandListResponse,HttpStatus.OK);
	}

	/**
	 * A controller method to get all Brand by a category from the database.
	 *
	 * @param categoryId - The uuid of the category under which the Brand list has to be fetched from the database.
	 * @return - ResponseEntity<BrandListResponse> type object along with Http status OK.
	 * @throws CategoryNotFoundException
	 */
	@GetMapping("/category/{categoryId}")
	public ResponseEntity<BrandListResponse> getBrandsByCategoryId(@PathVariable(name="categoryId", required = false)String categoryId) throws CategoryNotFoundException{
		if(categoryId==null)
			throw new CategoryNotFoundException("CNF-001","Brand name field should not be empty");
		List<BrandEntity> brandEntities = brandService.brandByCategory(categoryId);
		if(brandEntities.isEmpty())
			throw new CategoryNotFoundException("CNF-002","No category by this id");
		BrandListResponse brandListResponse = getBrandListResponseFromBrandEntities(brandEntities);
		return new ResponseEntity(brandListResponse,HttpStatus.OK);
	}


	/**
	 * A controller method to get all Brand from the database.
	 *
	 * @return - ResponseEntity<BrandListResponse> type object along with Http status OK.
	 */


	public BrandListResponse getBrandListResponseFromBrandEntities(List<BrandEntity> brandEntities)
	{
		BrandListResponse brandListResponse = new BrandListResponse();
		List<BrandList> brandLists = new ArrayList<BrandList>();
		for(BrandEntity brandEntity: brandEntities)
		{
			AddressEntity brandAddress = brandEntity.getAddress();
			BrandDetailsResponseAddressState brandDetailsResponseAddressState = new BrandDetailsResponseAddressState();
			brandDetailsResponseAddressState.id(UUID.fromString(brandAddress.getState().getUuid())).stateName(brandAddress.getState().getStateName());
			BrandDetailsResponseAddress brandDetailsResponseAddress = new BrandDetailsResponseAddress();
			brandDetailsResponseAddress.id(UUID.fromString(brandAddress.getUuid())).flatBuildingName(brandAddress.getFlatBuilNo()).locality(brandAddress.getLocality()).city(brandAddress.getCity()).pincode(brandAddress.getPincode()).state(brandDetailsResponseAddressState);
			List<CategoryEntity> brandCategoriesList = brandEntity.getCategories();
			StringBuilder sb = new StringBuilder();

			for(int index = 0; index < brandCategoriesList.size(); ++index) {
				sb.append(((CategoryEntity)brandCategoriesList.get(index)).getCategoryName());
				if (index < brandCategoriesList.size() - 1) {
					sb.append(",").append(" ");
				}
			}
			BrandList brandList = new BrandList();
			brandList.id(UUID.fromString(brandEntity.getUuid())).brandName(brandEntity.getbrandName()).address(brandDetailsResponseAddress).customerRating(BigDecimal.valueOf(brandEntity.getCustomerRating())).numberCustomersRated(brandEntity.getNumberCustomersRated()).categories(sb.toString());

			brandLists.add(brandList);
		}
		brandListResponse.setBrands(brandLists);
		return brandListResponse;
	}

}
