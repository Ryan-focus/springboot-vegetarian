package com.eeit45.champion.vegetarian.dao;

import com.eeit45.champion.vegetarian.dto.CartEntryRequest;
import com.eeit45.champion.vegetarian.model.Cart;
import com.eeit45.champion.vegetarian.model.CartEntry;
import com.eeit45.champion.vegetarian.util.CartEntryDetail;

import java.util.List;

public interface CartDao {

    Cart getCartById(Integer userId);

    List<CartEntry>getCartEntriesByUUID(String cartUUID);

    CartEntry getSingleCartEntryById(Integer cartEntryId);


    Integer AddToCart(CartEntryRequest cartEntryRequest);

    Integer CreateNewCart(Integer userId);

    void deleteProductFromCartById(Integer cartEntryId);

    void updateQuantity(Integer cartEntryId, CartEntryRequest cartEntryRequest);

    CartEntryDetail showCartEntryDetail(CartEntry cartEntry);

    void deleteCartById(String cartUUID);

    void deleteCartEntryById(String cartUUID);

}
