package com.eeit45.champion.vegetarian.dto;

import javax.validation.constraints.NotNull;

public class CartEntryRequest {

    @NotNull
    private Integer cartId;
    @NotNull
    private Integer productId;
    @NotNull
    private Integer quantity;

    private int entryPrice;

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public int getEntryPrice() {
        return entryPrice;
    }

    public void setEntryPrice(int entryPrice) {
        this.entryPrice = entryPrice;
    }
}
