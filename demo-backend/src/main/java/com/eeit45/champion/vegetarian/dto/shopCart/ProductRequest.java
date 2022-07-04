package com.eeit45.champion.vegetarian.dto.shopCart;

import com.eeit45.champion.vegetarian.constant.ProductCategory;
import com.eeit45.champion.vegetarian.constant.VeganCategory;

import javax.validation.constraints.NotNull;
import java.time.format.DateTimeFormatter;


public class ProductRequest {
    @NotNull
    private String productName;
    @NotNull
    private ProductCategory category;

    @NotNull
    private VeganCategory veganCategory;
    @NotNull
    private Integer productPrice;

    private String stock;
    private String productImage;


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

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public Integer getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }
}
