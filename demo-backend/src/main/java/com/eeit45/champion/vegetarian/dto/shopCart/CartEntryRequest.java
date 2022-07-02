package com.eeit45.champion.vegetarian.dto.shopCart;

import javax.validation.constraints.NotNull;

public class CartEntryRequest {

    @NotNull
    private String cartUUID;
    @NotNull
    private Integer productId;
    @NotNull
    private Integer quantity;

    private int entryPrice;

    public String getCartUUID() {
        return cartUUID;
    }

    public void setCartUUID(String cartUUID) {
        this.cartUUID = cartUUID;
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
