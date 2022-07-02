package com.eeit45.champion.vegetarian.model.shopCart;

import javax.validation.constraints.NotNull;

public class Cart {

    private int cartId;
    @NotNull
    private Integer userId;

    private String cartUUID;


    public Cart() {
    }

    public Cart(Integer userId, String cartUUID) {
        this.userId = userId;
        this.cartUUID = cartUUID;
    }

    public Cart(int cartId, Integer userId, String cartUUID) {
        this.cartId = cartId;
        this.userId = userId;
        this.cartUUID = cartUUID;
    }

    public String getCartUUID() {
        return cartUUID;
    }

    public void setCartUUID(String cartUUID) {
        this.cartUUID = cartUUID;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
