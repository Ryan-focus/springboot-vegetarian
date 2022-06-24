package com.eeit45.champion.vegetarian.service.impl;

import com.eeit45.champion.vegetarian.dao.CartDao;
import com.eeit45.champion.vegetarian.dto.CartEntryRequest;
import com.eeit45.champion.vegetarian.model.Cart;
import com.eeit45.champion.vegetarian.model.CartEntry;
import com.eeit45.champion.vegetarian.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CartServiceImpl implements CartService {

    @Autowired
    private CartDao cartDao;


    @Override
    public Cart getCartById(Integer userId) {
        return cartDao.getCartById(userId);
    }

    @Override
    public List<CartEntry> getCartEntriesById(Integer cartId) {
        return cartDao.getCartEntriesById(cartId);
    }

    @Override
    public Integer AddToCart(CartEntryRequest cartEntryRequest) {
        return cartDao.AddToCart(cartEntryRequest);
    }

    @Override
    public void deleteProductFromCartById(Integer entryId) {
        cartDao.deleteProductFromCartById(entryId);
    }

    @Override
    public void updateQuantity(Integer entryId, CartEntryRequest cartEntryRequest) {
        cartDao.updateQuantity(entryId,cartEntryRequest);
    }
}
