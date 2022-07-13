package com.eeit45.champion.vegetarian.model;

import java.util.Date;

public class PostLike {
	
	private Integer postId;
	private Date LikeDate;
	private Integer userId;
	
	
	public PostLike() {
		
	}
	
	public PostLike(Integer postId, Date likeDate, Integer userId) {
		this.postId = postId;
		this.LikeDate = likeDate;
		this.userId = userId;
	}
	
	public Integer getPostId() {
		return postId;
	}
	public void setPostId(Integer postId) {
		this.postId = postId;
	}
	public Date getLikeDate() {
		return LikeDate;
	}
	public void setLikeDate(Date likeDate) {
		LikeDate = likeDate;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	

}
