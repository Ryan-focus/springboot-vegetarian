package com.eeit45.champion.vegetarian.model;


import java.util.Date;

public class User {
	
	private Integer userId;
	
    private String email;
	
    private String password;
	
    private String userName;
	
    private String status;
	
    private String userPic;
    
    private Date registerTime;
    
    private Date lastLoginTime;

	public User() {
	}

	public User(Integer userId, String email, String password, String userName, String status, String userPic, Date registerTime, Date lastLoginTime) {
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

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [userId=");
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
