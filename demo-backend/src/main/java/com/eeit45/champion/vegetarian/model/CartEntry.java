package com.eeit45.champion.vegetarian.model;

import javax.validation.constraints.NotNull;

public class CartEntry{

    private Integer entryId;
    @NotNull
    private Integer productId;
    @NotNull
    private String cartUUID;
    @NotNull
    private Integer quantity;
    private Integer entryPrice;

    public CartEntry() {
    }

    public CartEntry(int productId, String cartUUID, int quantity, int entryPrice) {
        this.productId = productId;
        this.cartUUID = cartUUID;
        this.quantity = quantity;
        this.entryPrice = entryPrice;
    }

    public CartEntry(int entryId, int productId, String cartUUID, int quantity, int entryPrice) {
        this.entryId = entryId;
        this.productId = productId;
        this.cartUUID = cartUUID;
        this.quantity = quantity;
        this.entryPrice = entryPrice;
    }

    @Override
    public String toString() {
        return "CartEntry{" +
                "entryId=" + entryId +
                ", productId=" + productId +
                ", cartUUID='" + cartUUID + '\'' +
                ", quantity=" + quantity +
                ", entryPrice=" + entryPrice +
                '}';
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

    public String getCartUUID() {
        return cartUUID;
    }

    public void setCartUUID(String cartUUID) {
        this.cartUUID = cartUUID;
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
