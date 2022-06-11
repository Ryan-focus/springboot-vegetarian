package com.eeit45.champion.vegetarian.dao;

import com.eeit45.champion.vegetarian.dto.CartRequest;
import com.eeit45.champion.vegetarian.dto.ProductRequest;
import com.eeit45.champion.vegetarian.dto.ReserveRequest;
import com.eeit45.champion.vegetarian.model.Cart;
import com.eeit45.champion.vegetarian.model.Reserve;

public interface CartDao {

    Cart addToCart(Integer productId);

    Cart getCartItemById(Integer userId);

    Integer insertCartItem(CartRequest cartRequest);

    void updateQuantity(Integer productId, CartRequest cartRequest);

    void deleteCartItem(Integer productId);
}
