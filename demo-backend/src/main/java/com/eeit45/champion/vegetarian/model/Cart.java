package com.eeit45.champion.vegetarian.model;

import java.util.List;

public class Cart {

    private int cartId;
    private Integer userId;


    public Cart() {
    }

    public Cart(int cartId, Integer userId) {
        this.cartId = cartId;
        this.userId = userId;
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
