package com.eeit45.champion.vegetarian.util;

import com.eeit45.champion.vegetarian.model.Product;

import javax.validation.constraints.NotNull;

public class CartEntryDetail {

    @NotNull
    private int entryId;
    @NotNull
    private int cartId;
    @NotNull
    private int quantity;
    private int entryPrice;
    @NotNull
    private int productId;
    private Product product;


    public CartEntryDetail() {
    }

    public CartEntryDetail(int entryId, int cartId, int quantity, int entryPrice, int productId, Product product) {
        this.entryId = entryId;
        this.cartId = cartId;
        this.quantity = quantity;
        this.entryPrice = entryPrice;
        this.productId = productId;
        this.product = product;
    }

    public CartEntryDetail(int cartId, int quantity, int entryPrice, int productId, Product product) {
        this.cartId = cartId;
        this.quantity = quantity;
        this.entryPrice = entryPrice;
        this.productId = productId;
        this.product = product;
    }

    public int getEntryId() {
        return entryId;
    }

    public void setEntryId(int entryId) {
        this.entryId = entryId;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getEntryPrice() {
        return entryPrice;
    }

    public void setEntryPrice(int entryPrice) {
        this.entryPrice = entryPrice;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void add(CartEntryDetail cartEntryDetail) {
    }
}
