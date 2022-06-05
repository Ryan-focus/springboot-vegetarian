package com.eeit45.champion.vegetarian.rowmapper;

import com.eeit45.champion.vegetarian.dao.ProductDao;
import com.eeit45.champion.vegetarian.model.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        Product product = new Product();

        product.setProductId(rs.getInt("id"));
        product.setProductName(rs.getString("name"));
        product.setProductCategory(rs.getString("category"));
        product.setPrice(rs.getInt("price"));
        product.setImageUrl(rs.getString("image"));

        return product;
    }
}
