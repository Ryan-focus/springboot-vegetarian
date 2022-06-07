package com.eeit45.champion.vegetarian.model;

import com.eeit45.champion.vegetarian.constant.ProductCategory;
import com.eeit45.champion.vegetarian.constant.VeganCategory;

public class Product {
    private Integer productId;
    private String productName;
    private ProductCategory productCategory;
    private VeganCategory veganCategory;
    private Integer price;
    private String imageUrl;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

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
    public VeganCategory getVeganCategory() {
        return veganCategory;
    }

    public void setVeganCategory(VeganCategory veganCategory) {
        this.veganCategory = veganCategory;
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
