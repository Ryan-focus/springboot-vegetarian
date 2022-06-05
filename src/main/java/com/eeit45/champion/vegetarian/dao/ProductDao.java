package com.eeit45.champion.vegetarian.dao;

import com.eeit45.champion.vegetarian.dto.ProductRequest;
import com.eeit45.champion.vegetarian.model.Product;

public interface ProductDao {

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);
}
