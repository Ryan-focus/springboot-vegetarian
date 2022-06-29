package com.eeit45.champion.vegetarian.model;

import java.util.Date;

public class Forum {
	private Integer forumId;
	private Integer forumCategoryId;
	private String forumTitle;
	private String forumContent;
	private Date forumDate;
	
	
	public Integer getForumId() {
		return forumId;
	}

	public void setForumId(Integer forumId) {
		this.forumId = forumId;
	}

	public Integer getForumCategoryId() {
		return forumCategoryId;
	}

	public void setForumCategoryId(Integer forumCategoryId) {
		this.forumCategoryId = forumCategoryId;
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

	public Date getForumDate() {
		return forumDate;
	}

	public void setForumDate(Date forumDate) {
		this.forumDate = forumDate;
	}


}
