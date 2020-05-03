package com.upgrad.myntra.service.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * CategoryEntity class contains all the attributes to be mapped to all the fields in category table in the database.
 * All the annotations which are used to specify all the constraints to the columns in the database must be correctly implemented.
 */
@Entity
@Table(name = "category")
@NamedQueries({
        @NamedQuery(name = "getAllCategoriesOrderedByName", query = "select c from CategoryEntity c order by c.categoryName ASC"),
        @NamedQuery(name = "getCategoryById", query = "select c from CategoryEntity c where " +
                "c.uuid = :uuid"),
        @NamedQuery(name = "getCategoriesBybrand", query = "select c from CategoryEntity c inner join c.brands s where s.uuid = :uuid order by c.categoryName")
})
public class CategoryEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "UUID", length = 64, nullable = false)
    private String uuid;

    @Column(name = "category_name", nullable = false)
    private String categoryName;

    @ManyToMany
    @JoinTable(name = "category_item", joinColumns = @JoinColumn(name = "category_id"), inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<ItemEntity> items = new ArrayList<>();

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "brands_category", joinColumns = @JoinColumn(name = "category_id"), inverseJoinColumns = @JoinColumn(name = "brand_id"))
    private List<BrandEntity> brands = new ArrayList<>();

    public CategoryEntity() {
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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<ItemEntity> getItems() {
        return items;
    }

    public void setItems(List<ItemEntity> items) {
        this.items = items;
    }

    public List<BrandEntity> getbrands() {
        return brands;
    }

    public void setbrands(List<BrandEntity> brands) {
        this.brands = brands;
    }
}
