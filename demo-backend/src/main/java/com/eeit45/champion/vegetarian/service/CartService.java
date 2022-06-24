package com.eeit45.champion.vegetarian.service;

import com.eeit45.champion.vegetarian.dto.CartEntryRequest;
import com.eeit45.champion.vegetarian.model.Cart;
import com.eeit45.champion.vegetarian.model.CartEntry;


import java.util.List;

public interface CartService {


    Cart getCartById(Integer userId);

    List<CartEntry> getCartEntriesById(Integer cartId);

    List<CartEntry> getSingleCartEntry(Integer cartEntryId);

    Integer AddToCart(CartEntryRequest cartEntryRequest);

    void deleteProductFromCartById(Integer entryId);

    void updateQuantity(Integer entryId, CartEntryRequest cartEntryRequest);

    public Integer CreateNewCart(Integer userId);
}
