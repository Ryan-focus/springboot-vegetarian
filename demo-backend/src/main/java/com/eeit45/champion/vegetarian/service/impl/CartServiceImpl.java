package com.eeit45.champion.vegetarian.service.impl;

import com.eeit45.champion.vegetarian.dao.CartDao;
import com.eeit45.champion.vegetarian.dao.ProductDao;
import com.eeit45.champion.vegetarian.dto.CartRequest;
import com.eeit45.champion.vegetarian.dto.ProductQueryParams;
import com.eeit45.champion.vegetarian.dto.ProductRequest;
import com.eeit45.champion.vegetarian.model.Cart;
import com.eeit45.champion.vegetarian.model.Product;
import com.eeit45.champion.vegetarian.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CartServiceImpl implements CartService {

    @Autowired
    private CartDao cartDao;
    @Autowired
    private  ProductDao productDao;

//    @Override
//    public Integer totalProduct(ProductQueryParams productQueryParams) {
//        return productDao.totalProduct(productQueryParams);
//    }
//    @Override
//    public List<Product> getProducts(ProductQueryParams productQueryParams) {
//        return productDao.getProducts(productQueryParams);
//    }

    @Override
    public Cart getCartItemById(Integer userId) {
        return cartDao.getCartItemById(userId);
    }

    @Override
    public Integer insertCartItem(CartRequest cartRequest) {
        return cartDao.insertCartItem(cartRequest);
    }

    @Override
    public void updateQuantity(Integer productId, CartRequest cartRequest) {
        cartDao.updateQuantity(productId,cartRequest);
    }

    @Override
    public void deleteCartItem(Integer productId) {
        cartDao.deleteCartItem(productId);;
    }


}
