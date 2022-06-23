package com.eeit45.champion.vegetarian.model;

import java.util.List;

public class Cart {

    private int cartId;
    private Integer userId;
    private List<CartEntry> cartEntries;

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }


    public Cart() {
    }

    public Cart(Integer userId, List<CartEntry> cartEntries) {
        this.userId = userId;
        this.cartEntries = cartEntries;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<CartEntry> getCartEntries() {
        return cartEntries;
    }

    public void setCartEntries(List<CartEntry> cartEntries) {
        this.cartEntries = cartEntries;
    }
}
