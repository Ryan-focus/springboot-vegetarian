package com.eeit45.champion.vegetarian.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

public class Post {

	// private static final long serialVersionUID = 1L;

	private Integer postId;
	private String title;
	private Date postedDate;

	private String postedText;

	private String imgurl;

	private String postStatus;

	private Date postAuditDate;
	
	private Date postUpdateDate;

	private String postCategory;
	
	private Integer likeCount;
	
	private Integer userId;


	

	

	public Post() {

	}

	public Post(Integer postId, String title, Date postedDate, String postedText, String imgurl, String variant) {

		this.postId = postId;
		this.title = title;
		this.postedDate = postedDate;
		this.postedText = postedText;
		this.imgurl = imgurl;
	}

	public Post(Integer postId, String title, Date postedDate, String postedText, String imgurl) {

		this.postId = postId;
		this.title = title;
		this.postedDate = postedDate;
		this.postedText = postedText;
		this.imgurl = imgurl;
	}

	public Post(Integer postId, String title, String postedText) {
		this.postId = postId;
		this.title = title;
		this.postedText = postedText;
	}

	public Post(Integer postId, String title, String postedText, String imgurl) {
		this.postId = postId;
		this.title = title;
		this.postedText = postedText;
		this.imgurl = imgurl;
	}

	public Post(Integer postId) {
		this.postId = postId;
	}

	public Post(String title, Date postedDate, String postedText, String imgurl) {

		this.title = title;
		this.postedDate = postedDate;
		this.postedText = postedText;
		this.imgurl = imgurl;

	}

	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getPostedDate() {
		return postedDate;
	}

	public void setPostedDate(Date postedDate) {
		this.postedDate = postedDate;
	}

	public String getPostedText() {
		return postedText;
	}

	public void setPostedText(String postedText) {
		this.postedText = postedText;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}



	public String getPostStatus() {
		return postStatus;
	}

	public void setPostStatus(String postStatus) {
		this.postStatus = postStatus;
	}

	public Date getPostAuditDate() {
		return postAuditDate;
	}

	public void setPostAuditDate(Date postAuditDate) {
		this.postAuditDate = postAuditDate;
	}
	
	public String getPostCategory() {
		return postCategory;
	}

	public void setPostCategory(String postCategory) {
		this.postCategory = postCategory;
	}
	
	public Integer getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}
	
	public Date getPostUpdateDate() {
		return postUpdateDate;
	}

	public void setPostUpdateDate(Date postUpdateDate) {
		this.postUpdateDate = postUpdateDate;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}



}
