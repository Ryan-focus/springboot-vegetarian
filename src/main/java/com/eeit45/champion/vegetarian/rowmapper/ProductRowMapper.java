package com.eeit45.champion.vegetarian.rowmapper;

import com.eeit45.champion.vegetarian.constant.ProductCategory;
import com.eeit45.champion.vegetarian.constant.VeganCategory;
import com.eeit45.champion.vegetarian.model.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class ProductRowMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        Product product = new Product();

        product.setProductId(rs.getInt("id"));
        product.setProductName(rs.getString("name"));

        String categoryStr = rs.getString("category");
        ProductCategory category = ProductCategory.valueOf(categoryStr);
        product.setProductCategory(category);

        String veganCategoryStr = rs.getString("veganCategory");
        VeganCategory veganCategory = VeganCategory.valueOf(veganCategoryStr);
        product.setVeganCategory(veganCategory);

        product.setPrice(rs.getInt("price"));
        product.setImageUrl(rs.getString("image"));

        //日期處理輸出
        Timestamp createdTime = rs.getTimestamp("createdTime");
        product.setCreatedTime(createdTime);

        Timestamp updatedTime = rs.getTimestamp("updatedTime");
        product.setUpdatedTime(updatedTime);

        return product;
    }

}
