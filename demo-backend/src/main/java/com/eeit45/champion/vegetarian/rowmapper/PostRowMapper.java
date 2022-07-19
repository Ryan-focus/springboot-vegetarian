package com.eeit45.champion.vegetarian.rowmapper;

import com.eeit45.champion.vegetarian.model.Post;
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
        post.setImgurl(rs.getString("imgUrl"));
        post.setPostStatus(rs.getString("postStatus"));
        post.setPostCategory(rs.getString("postCategory"));
        post.setUserId(rs.getInt("userId"));

        //日期處理輸出
        Timestamp createdTime = rs.getTimestamp("postedDate");
        post.setPostedDate(createdTime);
        Timestamp auditTime = rs.getTimestamp("postAuditDate");
        post.setPostAuditDate(auditTime);
        Timestamp updateTime = rs.getTimestamp("postUpdateDate");
        post.setPostUpdateDate(updateTime);

        return post;
    }
    
    

}
