package com.eeit45.champion.vegetarian.dao;

import com.eeit45.champion.vegetarian.constant.ProductCategory;
import com.eeit45.champion.vegetarian.dto.ProductRequest;
import com.eeit45.champion.vegetarian.model.Product;

import java.util.List;

public interface ProductDao {

    List<Product> getProducts(ProductCategory category,String search);
    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);
}
