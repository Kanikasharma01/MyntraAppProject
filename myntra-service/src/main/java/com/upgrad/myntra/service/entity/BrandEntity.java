package com.upgrad.myntra.service.entity;



import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * BrandEntity class contains all the attributes to be mapped to all the fields in brands table in the database.
 * All the annotations which are used to specify all the constraints to the columns in the database must be correctly implemented.
 */
@Entity
@Table(name = "brands")
@NamedQueries({
		@NamedQuery(name = "brandsByName", query = "select b from BrandEntity b where UPPER" +
				"(b.brandName) LIKE concat('%',UPPER(:brandName),'%') ORDER BY b.brandName ASC"),
		@NamedQuery(name = "brandsByRating", query = "select b from BrandEntity b ORDER BY b.customerRating DESC"),
		@NamedQuery(name = "brandsByUUID", query = "select b from BrandEntity b where b.uuid = :uuid"),
		@NamedQuery(name = "brandsByCategory", query = "select distinct b FROM BrandEntity b " +
				"INNER JOIN b.categories c WHERE c.uuid = :uuid " +
				"ORDER BY b.brandName ASC")
})
public class BrandEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "UUID", length = 64, nullable = false)
	private String uuid;

	@Column(name = "brand_name", nullable = false)
	private String brandName;

	@Column(name = "customer_rating", nullable = false)
	private Double customerRating;

	@Column(name = "number_of_customers_rated", nullable = false)
	private Integer numberCustomersRated;

	@OneToOne
	private AddressEntity address;

	@OneToMany
	@JoinTable(name = "brands_item", joinColumns = @JoinColumn(name = "brand_id"), inverseJoinColumns = @JoinColumn(name = "item_id"))
	private List<ItemEntity> items = new ArrayList<>();

	@ManyToMany
	@JoinTable(name = "brands_category", joinColumns = @JoinColumn(name = "brand_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
	private List<CategoryEntity> categories = new ArrayList<>();

	public BrandEntity() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getbrandName() {
		return brandName;
	}

	public void setbrandName(String brandName) {
		this.brandName = brandName;
	}

	public Double getCustomerRating() {
		return customerRating;
	}

	public void setCustomerRating(Double customerRating) {
		this.customerRating = customerRating;
	}

	public Integer getNumberCustomersRated() {
		return numberCustomersRated;
	}

	public void setNumberCustomersRated(Integer numberCustomersRated) {
		this.numberCustomersRated = numberCustomersRated;
	}

	public AddressEntity getAddress() {
		return address;
	}

	public void setAddress(AddressEntity address) {
		this.address = address;
	}

	public List<ItemEntity> getItems() {
		return items;
	}

	public void setItems(List<ItemEntity> items) {
		this.items = items;
	}

	public List<CategoryEntity> getCategories() {
		return categories;
	}

	public void setCategories(List<CategoryEntity> categories) {
		this.categories = categories;
	}
}
