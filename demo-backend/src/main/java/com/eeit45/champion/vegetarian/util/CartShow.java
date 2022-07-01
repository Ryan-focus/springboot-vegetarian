package com.eeit45.champion.vegetarian.util;

import com.eeit45.champion.vegetarian.model.CartEntry;
import com.eeit45.champion.vegetarian.model.Product;

import javax.validation.constraints.NotNull;
import java.util.List;

public class CartShow <T>{

    @NotNull
    private String cartUUID;
    @NotNull
    private int userId;
    private List<CartEntryDetail> cartEntryDetailList;


    public CartShow(int userId, List<CartEntryDetail> cartEntryDetailList) {
        this.userId = userId;
        this.cartEntryDetailList = cartEntryDetailList;
    }

    public CartShow() {
    }


    public CartShow(String cartUUID, int userId, List<CartEntryDetail> cartEntryDetailList) {
        this.cartUUID = cartUUID;
        this.userId = userId;
        this.cartEntryDetailList = cartEntryDetailList;
    }

    public String getCartUUID() {
        return cartUUID;
    }

    public void setCartUUID(String cartUUID) {
        this.cartUUID = cartUUID;
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
