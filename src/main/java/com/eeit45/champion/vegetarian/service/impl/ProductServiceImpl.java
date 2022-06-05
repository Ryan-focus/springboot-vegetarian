package com.eeit45.champion.vegetarian.service.impl;

import com.eeit45.champion.vegetarian.dao.ProductDao;
import com.eeit45.champion.vegetarian.model.Product;
import com.eeit45.champion.vegetarian.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Product getProductById(Integer productId) {
        return productDao.getProductById(productId);
    }
}
