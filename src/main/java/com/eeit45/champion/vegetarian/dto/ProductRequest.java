package com.eeit45.champion.vegetarian.dto;

import com.eeit45.champion.vegetarian.constant.ProductCategory;

import javax.validation.constraints.NotNull;


public class ProductRequest {
    @NotNull
    private String productName;
    @NotNull
    private ProductCategory productCategory;
    @NotNull
    private Integer price;

    private String imageUrl;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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
