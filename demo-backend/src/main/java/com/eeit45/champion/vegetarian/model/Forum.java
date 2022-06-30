package com.eeit45.champion.vegetarian.model;

import java.util.Date;

public class Forum {


	private Integer forumId;
	private String forumTitle;
	private String forumContent;
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


}
