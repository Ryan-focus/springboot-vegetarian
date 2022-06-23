package com.eeit45.champion.vegetarian.dao;

import com.eeit45.champion.vegetarian.dto.CartEntryRequest;
import com.eeit45.champion.vegetarian.model.Cart;
import com.eeit45.champion.vegetarian.model.CartEntry;

import java.util.List;

public interface CartDao {

    Cart getCartById(Integer userId);

    List<CartEntry>getCartEntriesById(Integer cartId);

    Integer AddToCart(CartEntryRequest cartEntryRequest);

    void deleteProductFromCartById(Integer productId);

    void updateQuantity(Integer productId, CartEntryRequest cartEntryRequest);
}
