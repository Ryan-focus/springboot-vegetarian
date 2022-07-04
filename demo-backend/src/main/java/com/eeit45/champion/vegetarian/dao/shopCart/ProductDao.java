package com.eeit45.champion.vegetarian.dao.shopCart;

import com.eeit45.champion.vegetarian.dto.shopCart.ProductQueryParams;
import com.eeit45.champion.vegetarian.dto.shopCart.ProductRequest;
import com.eeit45.champion.vegetarian.model.shopCart.Product;

import java.util.List;

public interface ProductDao {

    Integer totalProduct(ProductQueryParams productQueryParams);
    List<Product> getProducts(ProductQueryParams productQueryParams);
    List<Product> getAllProduct();
    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void updateStock(Integer productId , Integer stock);

    void deleteProductById(Integer productId);

}
