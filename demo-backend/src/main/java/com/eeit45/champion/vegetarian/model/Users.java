package com.eeit45.champion.vegetarian.model;


import java.sql.Date;
import java.sql.Timestamp;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Users {
	
	@JsonProperty("userId")
	private Integer userId;
	
	@NotBlank
	@Email
    @JsonProperty("email")
    private String email;
	
	@NotBlank
    @JsonProperty("password")
    private String password;
	
	@NotBlank
    @JsonProperty("userName")
    private String userName;
	
	@NotBlank
    @JsonProperty("status")
    private String status;
	
    @JsonProperty("userPic")
    private String userPic;
    
    @JsonProperty("registerTime")
    private Date registerTime;
    
    @JsonProperty("lastLoginTime")
    private Timestamp lastLoginTime;
    
    
	public Users() {
	}
	
	public Users(Integer userId, String email, String password, String userName, String status, String userPic,
			Date registerTime, Timestamp lastLoginTime) {
		this.userId = userId;
		this.email = email;
		this.password = password;
		this.userName = userName;
		this.status = status;
		this.userPic = userPic;
		this.registerTime = registerTime;
		this.lastLoginTime = lastLoginTime;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserPic() {
		return userPic;
	}

	public void setUserPic(String userPic) {
		this.userPic = userPic;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public Timestamp getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Timestamp lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Users [userId=");
		builder.append(userId);
		builder.append(", email=");
		builder.append(email);
		builder.append(", password=");
		builder.append(password);
		builder.append(", userName=");
		builder.append(userName);
		builder.append(", status=");
		builder.append(status);
		builder.append(", userPic=");
		builder.append(userPic);
		builder.append(", registerTime=");
		builder.append(registerTime);
		builder.append(", lastLoginTime=");
		builder.append(lastLoginTime);
		builder.append("]");
		return builder.toString();
	}

}
