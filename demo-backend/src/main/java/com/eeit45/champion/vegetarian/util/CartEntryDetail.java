package com.eeit45.champion.vegetarian.util;

import com.eeit45.champion.vegetarian.model.Product;

import javax.validation.constraints.NotNull;

public class CartEntryDetail {

    @NotNull
    private Integer entryId;
    @NotNull
    private String cartUUID;
    @NotNull
    private Integer quantity;
    private Integer entryPrice;
    @NotNull
    private Integer productId;
    private Product product;


    public CartEntryDetail() {
    }

    public CartEntryDetail(Integer entryId, String cartUUID, Integer quantity, Integer entryPrice, Integer productId, Product product) {
        this.entryId = entryId;
        this.cartUUID = cartUUID;
        this.quantity = quantity;
        this.entryPrice = entryPrice;
        this.productId = productId;
        this.product = product;
    }

    public CartEntryDetail(String cartUUID, Integer quantity, Integer entryPrice, Integer productId, Product product) {
        this.cartUUID = cartUUID;
        this.quantity = quantity;
        this.entryPrice = entryPrice;
        this.productId = productId;
        this.product = product;
    }

    public Integer getEntryId() {
        return entryId;
    }

    public void setEntryId(Integer entryId) {
        this.entryId = entryId;
    }

    public String getCartUUID() {
        return cartUUID;
    }

    public void setCartUUID(String cartUUID) {
        this.cartUUID = cartUUID;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getEntryPrice() {
        return entryPrice;
    }

    public void setEntryPrice(Integer entryPrice) {
        this.entryPrice = entryPrice;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
