package com.upgrad.myntra.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.upgrad.myntra.api.model.BrandDetailsResponseAddress;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.util.UUID;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * BrandList
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-04-05T18:32:51.090+05:30")

public class BrandList   {
  @JsonProperty("id")
  private UUID id = null;

  @JsonProperty("Brand_name")
  private String brandName = null;

  @JsonProperty("photo_URL")
  private String photoURL = null;

  @JsonProperty("customer_rating")
  private BigDecimal customerRating = null;

  @JsonProperty("average_price")
  private Integer averagePrice = null;

  @JsonProperty("number_customers_rated")
  private Integer numberCustomersRated = null;

  @JsonProperty("address")
  private BrandDetailsResponseAddress address = null;

  @JsonProperty("categories")
  private String categories = null;

  public BrandList id(UUID id) {
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

  public BrandList brandName(String brandName) {
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

  public BrandList photoURL(String photoURL) {
    this.photoURL = photoURL;
    return this;
  }

  /**
   * URL for the picture of the Brand
   * @return photoURL
  **/
  @ApiModelProperty(value = "URL for the picture of the Brand")


  public String getPhotoURL() {
    return photoURL;
  }

  public void setPhotoURL(String photoURL) {
    this.photoURL = photoURL;
  }

  public BrandList customerRating(BigDecimal customerRating) {
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

  public BrandList averagePrice(Integer averagePrice) {
    this.averagePrice = averagePrice;
    return this;
  }

  /**
   * Average price for two people
   * @return averagePrice
  **/
  @ApiModelProperty(value = "Average price for two people")


  public Integer getAveragePrice() {
    return averagePrice;
  }

  public void setAveragePrice(Integer averagePrice) {
    this.averagePrice = averagePrice;
  }

  public BrandList numberCustomersRated(Integer numberCustomersRated) {
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

  public BrandList address(BrandDetailsResponseAddress address) {
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

  public BrandList categories(String categories) {
    this.categories = categories;
    return this;
  }

  /**
   * List of categories
   * @return categories
  **/
  @ApiModelProperty(value = "List of categories")


  public String getCategories() {
    return categories;
  }

  public void setCategories(String categories) {
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
    BrandList brandList = (BrandList) o;
    return Objects.equals(this.id, brandList.id) &&
        Objects.equals(this.brandName, brandList.brandName) &&
        Objects.equals(this.photoURL, brandList.photoURL) &&
        Objects.equals(this.customerRating, brandList.customerRating) &&
        Objects.equals(this.averagePrice, brandList.averagePrice) &&
        Objects.equals(this.numberCustomersRated, brandList.numberCustomersRated) &&
        Objects.equals(this.address, brandList.address) &&
        Objects.equals(this.categories, brandList.categories);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, brandName, photoURL, customerRating, averagePrice, numberCustomersRated, address, categories);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BrandList {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    brandName: ").append(toIndentedString(brandName)).append("\n");
    sb.append("    photoURL: ").append(toIndentedString(photoURL)).append("\n");
    sb.append("    customerRating: ").append(toIndentedString(customerRating)).append("\n");
    sb.append("    averagePrice: ").append(toIndentedString(averagePrice)).append("\n");
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

