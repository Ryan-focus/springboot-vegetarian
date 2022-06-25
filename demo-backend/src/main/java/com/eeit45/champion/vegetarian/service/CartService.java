package com.eeit45.champion.vegetarian.service;

import com.eeit45.champion.vegetarian.dto.CartEntryRequest;
import com.eeit45.champion.vegetarian.model.Cart;
import com.eeit45.champion.vegetarian.model.CartEntry;
import com.eeit45.champion.vegetarian.util.CartEntryDetail;


import java.util.List;

public interface CartService {


    Cart getCartById(Integer userId);

    List<CartEntry> getCartEntriesById(Integer cartId);

    CartEntry getSingleCartEntry(Integer cartEntryId);

    Integer AddToCart(CartEntryRequest cartEntryRequest);

    void deleteProductFromCartById(Integer cartEntryId);

    void updateQuantity(Integer cartEntryId, CartEntryRequest cartEntryRequest);

    Integer CreateNewCart(Integer userId);

    CartEntryDetail showCartEntryDetail(CartEntry cartEntry);
}
