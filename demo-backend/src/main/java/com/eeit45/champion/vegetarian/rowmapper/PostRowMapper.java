package com.eeit45.champion.vegetarian.rowmapper;

import com.eeit45.champion.vegetarian.constant.ProductCategory;
import com.eeit45.champion.vegetarian.constant.VeganCategory;
import com.eeit45.champion.vegetarian.model.Post;
import com.eeit45.champion.vegetarian.model.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class PostRowMapper implements RowMapper<Post> {
    @Override
    public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
        Post post = new Post();

        post.setPostId(rs.getInt("postId"));
        post.setTitle(rs.getString("title"));
        post.setPostedText(rs.getString("postedText"));
        post.setImgurl(rs.getString("imgurl"));
        post.setPostStatus(rs.getString("postStatus"));

//        String categoryStr = rs.getString("category");
//        ProductCategory category = ProductCategory.valueOf(categoryStr);
//        product.setProductCategory(category);
//
//        String veganCategoryStr = rs.getString("veganCategory");
//        VeganCategory veganCategory = VeganCategory.valueOf(veganCategoryStr);
//        product.setVeganCategory(veganCategory);


        //日期處理輸出
        Timestamp createdTime = rs.getTimestamp("postedDate");
        post.setPostedDate(createdTime);

//        Timestamp updatedTime = rs.getTimestamp("updatedTime");
//        product.setUpdatedTime(updatedTime);

        return post;
    }
    
    

}
