package com.eeit45.champion.vegetarian.service.shopCart.impl;

import com.eeit45.champion.vegetarian.dao.shopCart.CartDao;
import com.eeit45.champion.vegetarian.dto.shopCart.CartEntryRequest;
import com.eeit45.champion.vegetarian.model.shopCart.Cart;
import com.eeit45.champion.vegetarian.model.shopCart.CartEntry;
import com.eeit45.champion.vegetarian.service.shopCart.CartService;
import com.eeit45.champion.vegetarian.util.shopCart.CartEntryDetail;
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
    public List<CartEntry> getCartEntriesByUUID(String cartUUID) {
        return cartDao.getCartEntriesByUUID(cartUUID);
    }

    @Override
    public CartEntry getSingleCartEntry(Integer cartEntryId) {
        return cartDao.getSingleCartEntryById(cartEntryId);
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
    public void deleteCartById(String cartUUID) {
        cartDao.deleteCartById(cartUUID);
    }

    @Override
    public void deleteCartEntryById(String cartUUID) {
        cartDao.deleteCartEntryById(cartUUID);
    }
}