package com.eeit45.champion.vegetarian.service.shopCart;

import com.eeit45.champion.vegetarian.dto.shopCart.ProductQueryParams;
import com.eeit45.champion.vegetarian.dto.shopCart.ProductRequest;
import com.eeit45.champion.vegetarian.model.shopCart.Product;

import java.util.List;

public interface ProductService {

    Integer totalProduct(ProductQueryParams productQueryParams);
    List<Product> getProducts(ProductQueryParams productQueryParams);
    Product getProductById(Integer productId);

    List<Product> getAllProduct();

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);


}
