package com.eeit45.champion.vegetarian.service.shopCart;

import com.eeit45.champion.vegetarian.dto.shopCart.CartEntryRequest;
import com.eeit45.champion.vegetarian.model.shopCart.Cart;
import com.eeit45.champion.vegetarian.model.shopCart.CartEntry;
import com.eeit45.champion.vegetarian.util.shopCart.CartEntryDetail;


import java.util.List;

public interface CartService {


    Cart getCartById(Integer userId);

    List<CartEntry> getCartEntriesByUUID(String cartUUID);

    CartEntry getSingleCartEntry(Integer cartEntryId);

    Integer AddToCart(CartEntryRequest cartEntryRequest);

    void deleteProductFromCartById(Integer cartEntryId);

    void updateQuantity(Integer cartEntryId, CartEntryRequest cartEntryRequest);

    Integer CreateNewCart(Integer userId);

    CartEntryDetail showCartEntryDetail(CartEntry cartEntry);

    public void deleteCartById(String cartUUID);

    public void deleteCartEntryById(String cartUUID);
}