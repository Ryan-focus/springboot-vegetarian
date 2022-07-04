package com.eeit45.champion.vegetarian.service.shopCart.impl;

import com.eeit45.champion.vegetarian.dao.shopCart.ProductDao;
import com.eeit45.champion.vegetarian.dto.shopCart.ProductQueryParams;
import com.eeit45.champion.vegetarian.dto.shopCart.ProductRequest;
import com.eeit45.champion.vegetarian.model.shopCart.Product;
import com.eeit45.champion.vegetarian.service.shopCart.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Integer totalProduct(ProductQueryParams productQueryParams) {
        return productDao.totalProduct(productQueryParams);
    }
    @Override
    public List<Product> getProducts(ProductQueryParams productQueryParams) {
        return productDao.getProducts(productQueryParams);
    }

    @Override
    public Product getProductById(Integer productId) {
        return productDao.getProductById(productId);
    }

    @Override
    public List<Product> getAllProduct() {
        return productDao.getAllProduct();
    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {
        return productDao.createProduct(productRequest);
    }

    @Override
    public void updateProduct(Integer productId, ProductRequest productRequest) {
        productDao.updateProduct(productId,productRequest);
    }

    @Override
    public void deleteProductById(Integer productId) {
        productDao.deleteProductById(productId);
    }


}
