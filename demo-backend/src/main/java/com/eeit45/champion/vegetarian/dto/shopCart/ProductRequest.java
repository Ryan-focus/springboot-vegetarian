package com.eeit45.champion.vegetarian.dto.shopCart;

import com.eeit45.champion.vegetarian.constant.ProductCategory;
import com.eeit45.champion.vegetarian.constant.VeganCategory;

import javax.validation.constraints.NotNull;
import java.time.format.DateTimeFormatter;


public class ProductRequest {
    @NotNull
    private String productName;
    @NotNull
    private ProductCategory productCategory;

    @NotNull
    private VeganCategory veganCategory;
    @NotNull
    private Integer price;

    private String imageUrl;

    private DateTimeFormatter createdTime;

    private DateTimeFormatter updatedTime;

    private String description;

    public String getDescription() {return description;}

    public void setDescription(String description) {this.description = description;}

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public VeganCategory getVeganCategory() {
        return veganCategory;
    }

    public void setVeganCategory(VeganCategory veganCategory) {
        this.veganCategory = veganCategory;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


}
