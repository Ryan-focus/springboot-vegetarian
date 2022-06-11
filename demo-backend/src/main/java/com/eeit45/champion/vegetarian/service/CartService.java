package com.eeit45.champion.vegetarian.service;

import com.eeit45.champion.vegetarian.dto.CartRequest;
import com.eeit45.champion.vegetarian.dto.ProductQueryParams;
import com.eeit45.champion.vegetarian.dto.ProductRequest;
import com.eeit45.champion.vegetarian.model.Cart;
import com.eeit45.champion.vegetarian.model.Product;

import java.util.List;

public interface CartService {

//    Integer totalProduct(ProductQueryParams productQueryParams);
//    List<Product> getProducts(ProductQueryParams productQueryParams);
    Cart getCartItemById(Integer userId);

    Integer insertCartItem(CartRequest cartRequest);

    void updateQuantity(Integer productId, CartRequest cartRequest);

    void deleteCartItem(Integer productId);


}
