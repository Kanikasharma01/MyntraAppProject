package com.upgrad.myntra.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.upgrad.myntra.api.model.BrandDetailsResponseAddress;
import com.upgrad.myntra.api.model.CategoryList;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Brand details
 */
@ApiModel(description = "Brand details")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-05-04T17:17:15.014+05:30")

public class BrandDetailsResponse   {
  @JsonProperty("id")
  private UUID id = null;

  @JsonProperty("Brand_name")
  private String brandName = null;

  @JsonProperty("customer_rating")
  private BigDecimal customerRating = null;

  @JsonProperty("number_customers_rated")
  private Integer numberCustomersRated = null;

  @JsonProperty("address")
  private BrandDetailsResponseAddress address = null;

  @JsonProperty("categories")
  @Valid
  private List<CategoryList> categories = null;

  public BrandDetailsResponse id(UUID id) {
    this.id = id;
    return this;
  }

  /**
   * Unique identifier of the Brand in a standard UUID format
   * @return id
  **/
  @ApiModelProperty(value = "Unique identifier of the Brand in a standard UUID format")

  @Valid

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public BrandDetailsResponse brandName(String brandName) {
    this.brandName = brandName;
    return this;
  }

  /**
   * Name of the Brand
   * @return brandName
  **/
  @ApiModelProperty(value = "Name of the Brand")


  public String getBrandName() {
    return brandName;
  }

  public void setBrandName(String brandName) {
    this.brandName = brandName;
  }

  public BrandDetailsResponse customerRating(BigDecimal customerRating) {
    this.customerRating = customerRating;
    return this;
  }

  /**
   * Rating of the Brand
   * @return customerRating
  **/
  @ApiModelProperty(value = "Rating of the Brand")

  @Valid

  public BigDecimal getCustomerRating() {
    return customerRating;
  }

  public void setCustomerRating(BigDecimal customerRating) {
    this.customerRating = customerRating;
  }

  public BrandDetailsResponse numberCustomersRated(Integer numberCustomersRated) {
    this.numberCustomersRated = numberCustomersRated;
    return this;
  }

  /**
   * Number of customers rated the Brand
   * @return numberCustomersRated
  **/
  @ApiModelProperty(value = "Number of customers rated the Brand")


  public Integer getNumberCustomersRated() {
    return numberCustomersRated;
  }

  public void setNumberCustomersRated(Integer numberCustomersRated) {
    this.numberCustomersRated = numberCustomersRated;
  }

  public BrandDetailsResponse address(BrandDetailsResponseAddress address) {
    this.address = address;
    return this;
  }

  /**
   * Get address
   * @return address
  **/
  @ApiModelProperty(value = "")

  @Valid

  public BrandDetailsResponseAddress getAddress() {
    return address;
  }

  public void setAddress(BrandDetailsResponseAddress address) {
    this.address = address;
  }

  public BrandDetailsResponse categories(List<CategoryList> categories) {
    this.categories = categories;
    return this;
  }

  public BrandDetailsResponse addCategoriesItem(CategoryList categoriesItem) {
    if (this.categories == null) {
      this.categories = new ArrayList<>();
    }
    this.categories.add(categoriesItem);
    return this;
  }

  /**
   * List of categories
   * @return categories
  **/
  @ApiModelProperty(value = "List of categories")

  @Valid

  public List<CategoryList> getCategories() {
    return categories;
  }

  public void setCategories(List<CategoryList> categories) {
    this.categories = categories;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BrandDetailsResponse brandDetailsResponse = (BrandDetailsResponse) o;
    return Objects.equals(this.id, brandDetailsResponse.id) &&
        Objects.equals(this.brandName, brandDetailsResponse.brandName) &&
        Objects.equals(this.customerRating, brandDetailsResponse.customerRating) &&
        Objects.equals(this.numberCustomersRated, brandDetailsResponse.numberCustomersRated) &&
        Objects.equals(this.address, brandDetailsResponse.address) &&
        Objects.equals(this.categories, brandDetailsResponse.categories);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, brandName, customerRating, numberCustomersRated, address, categories);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BrandDetailsResponse {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    brandName: ").append(toIndentedString(brandName)).append("\n");
    sb.append("    customerRating: ").append(toIndentedString(customerRating)).append("\n");
    sb.append("    numberCustomersRated: ").append(toIndentedString(numberCustomersRated)).append("\n");
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
    sb.append("    categories: ").append(toIndentedString(categories)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

