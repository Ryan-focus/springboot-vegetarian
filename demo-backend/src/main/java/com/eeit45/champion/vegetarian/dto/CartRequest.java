package com.eeit45.champion.vegetarian.dto;

import javax.validation.constraints.NotNull;

public class CartRequest {

    @NotNull
    private Integer userId;

    @NotNull
    private Integer productId;

    @NotNull
    private Integer quantity;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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
}
