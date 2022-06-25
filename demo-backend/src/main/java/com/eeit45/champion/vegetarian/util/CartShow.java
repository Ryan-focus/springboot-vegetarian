package com.eeit45.champion.vegetarian.util;

import com.eeit45.champion.vegetarian.model.CartEntry;
import com.eeit45.champion.vegetarian.model.Product;

import java.util.List;

public class CartShow {

    private int cartId;
    private int userId;
    private List<CartEntryDetail> cartEntryDetailList;


    public CartShow(int userId, List<CartEntryDetail> cartEntryDetailList) {
        this.userId = userId;
        this.cartEntryDetailList = cartEntryDetailList;
    }

    public CartShow() {
    }

    public CartShow(int cartId, int userId, List<CartEntryDetail> cartEntryDetailList) {
        this.cartId = cartId;
        this.userId = userId;
        this.cartEntryDetailList = cartEntryDetailList;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<CartEntryDetail> getCartEntryDetailList() {
        return cartEntryDetailList;
    }

    public void setCartEntryDetailList(List<CartEntryDetail> cartEntryDetailList) {
        this.cartEntryDetailList = cartEntryDetailList;
    }
}
