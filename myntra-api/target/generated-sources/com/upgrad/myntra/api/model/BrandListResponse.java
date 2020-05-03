package com.upgrad.myntra.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.upgrad.myntra.api.model.BrandList;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * BrandListResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-04-05T18:32:51.090+05:30")

public class BrandListResponse   {
  @JsonProperty("Brands")
  @Valid
  private List<BrandList> brands = null;

  public BrandListResponse brands(List<BrandList> brands) {
    this.brands = brands;
    return this;
  }

  public BrandListResponse addBrandsItem(BrandList brandsItem) {
    if (this.brands == null) {
      this.brands = new ArrayList<>();
    }
    this.brands.add(brandsItem);
    return this;
  }

  /**
   * List of Brands
   * @return brands
  **/
  @ApiModelProperty(value = "List of Brands")

  @Valid

  public List<BrandList> getBrands() {
    return brands;
  }

  public void setBrands(List<BrandList> brands) {
    this.brands = brands;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BrandListResponse brandListResponse = (BrandListResponse) o;
    return Objects.equals(this.brands, brandListResponse.brands);
  }

  @Override
  public int hashCode() {
    return Objects.hash(brands);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BrandListResponse {\n");
    
    sb.append("    brands: ").append(toIndentedString(brands)).append("\n");
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

