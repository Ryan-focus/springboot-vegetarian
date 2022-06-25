package com.eeit45.champion.vegetarian.model;

import javax.validation.constraints.NotNull;

public class CartEntry{

    private int entryId;
    @NotNull
    private int productId;
    @NotNull
    private int cartId;
    @NotNull
    private int quantity;
    private int entryPrice;

    public CartEntry() {
    }

    public CartEntry(int entryId, int productId, int cartId, int quantity, int entryPrice) {
        this.entryId = entryId;
        this.productId = productId;
        this.cartId = cartId;
        this.quantity = quantity;
        this.entryPrice = entryPrice;
    }

    public CartEntry(int productId, int cartId, int quantity, int entryPrice) {
        this.productId = productId;
        this.cartId = cartId;
        this.quantity = quantity;
        this.entryPrice = entryPrice;
    }

    public int getEntryId() {
        return entryId;
    }

    public void setEntryId(int entryId) {
        this.entryId = entryId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
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
}
