package com.eeit45.champion.vegetarian.dao.shopCart;

import com.eeit45.champion.vegetarian.dto.shopCart.CartEntryRequest;
import com.eeit45.champion.vegetarian.model.shopCart.Cart;
import com.eeit45.champion.vegetarian.model.shopCart.CartEntry;
import com.eeit45.champion.vegetarian.util.shopCart.CartEntryDetail;

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
