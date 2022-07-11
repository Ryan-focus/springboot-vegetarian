package com.eeit45.champion.vegetarian.model;

import java.util.Date;

import com.eeit45.champion.vegetarian.constant.ForumCategory;

public class Forum {


	private Integer forumId;
	private String forumTitle;
	private String forumContent;
	private ForumCategory forumCategory;
	private String forumImage;
	private Date forumCreateTime;
	private Date forumUpdateTime;
	
	
	
	public Integer getForumId() {
		return forumId;
	}

	public void setForumId(Integer forumId) {
		this.forumId = forumId;
	}


	public String getForumTitle() {
		return forumTitle;
	}

	public void setForumTitle(String forumTitle) {
		this.forumTitle = forumTitle;
	}

	public String getForumContent() {
		return forumContent;
	}

	public void setForumContent(String forumContent) {
		this.forumContent = forumContent;
	}

	public Date getForumCreateTime() {
		return forumCreateTime;
	}

	public void setForumCreateTime(Date forumCreateTime) {
		this.forumCreateTime = forumCreateTime;
	}

	public Date getForumUpdateTime() {
		return forumUpdateTime;
	}

	public void setForumUpdateTime(Date forumUpdateTime) {
		this.forumUpdateTime = forumUpdateTime;
	}

	public String getForumImage() {
		return forumImage;
	}

	public void setForumImage(String forumImage) {
		this.forumImage = forumImage;
	}

	public ForumCategory getForumCategory() {
		return forumCategory;
	}

	public void setForumCategory(ForumCategory forumCategory) {
		this.forumCategory = forumCategory;
	}




}
