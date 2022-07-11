package com.eeit45.champion.vegetarian.dao;

import java.util.List;

import com.eeit45.champion.vegetarian.dto.LoginRequest;
import com.eeit45.champion.vegetarian.dto.UserQueryParams;
import com.eeit45.champion.vegetarian.dto.UserRequest;
import com.eeit45.champion.vegetarian.model.User;
import com.mysql.cj.log.Log;

public interface UserDao {
	
	Integer totalUser(UserQueryParams userQueryParams);
	
	List<User> getUsers(UserQueryParams userQueryParams);
	
	List<User> getAllUser();
	User getUserById(Integer userId);

	User getUserByEmail(String loginEmail);
	
	Integer createUser(UserRequest userRequest);
	
	int updateUser(Integer userId, UserRequest userRequest);
	
	void deleteUserById(Integer userId);
	
	int updateUserStatus(Integer userId);
	
	User login(UserRequest userRequest);
	
	boolean isBanned(String loginEmail);
	
	void updateLastLoginTime(String account);

	String resetPassword(String account);

}
