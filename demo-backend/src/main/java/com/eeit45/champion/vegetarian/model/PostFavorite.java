package com.eeit45.champion.vegetarian.model;

import java.util.Date;

public class PostFavorite {
	
	private Integer postId;
	private Date favDate;
	private Integer userId;
	
	public PostFavorite() {
		
	}

	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}

	public Date getFavDate() {
		return favDate;
	}

	public void setFavDate(Date favDate) {
		this.favDate = favDate;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	
	
	
	

}
