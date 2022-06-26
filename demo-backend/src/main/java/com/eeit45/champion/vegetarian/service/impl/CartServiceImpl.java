package com.eeit45.champion.vegetarian.service.impl;

import com.eeit45.champion.vegetarian.dao.CartDao;
import com.eeit45.champion.vegetarian.dto.CartEntryRequest;
import com.eeit45.champion.vegetarian.model.Cart;
import com.eeit45.champion.vegetarian.model.CartEntry;
import com.eeit45.champion.vegetarian.service.CartService;
import com.eeit45.champion.vegetarian.util.CartEntryDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
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
    public CartEntry getSingleCartEntry(Integer cartEntryId) {
        return cartDao.getSingleCartEntry(cartEntryId);
    }

    @Override
    public Integer AddToCart(CartEntryRequest cartEntryRequest) {
        return cartDao.AddToCart(cartEntryRequest);
    }

    @Override
    public void deleteProductFromCartById(Integer cartEntryId) {
        cartDao.deleteProductFromCartById(cartEntryId);
    }

    @Override
    public void updateQuantity(Integer cartEntryId, CartEntryRequest cartEntryRequest) {
        cartDao.updateQuantity(cartEntryId, cartEntryRequest);
    }

    @Override
    public Integer CreateNewCart(Integer userId) {
        return cartDao.CreateNewCart(userId);
    }

    @Override
    public CartEntryDetail showCartEntryDetail(CartEntry cartEntry) {
        return cartDao.showCartEntryDetail(cartEntry);
    }

    @Override
    public void deleteCartById(Integer cartId) {
        cartDao.deleteCartById(cartId);
    }

    @Override
    public void deleteCartEntryById(Integer cartId) {
        cartDao.deleteCartEntryById(cartId);
    }
}
