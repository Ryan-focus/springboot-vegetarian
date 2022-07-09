package com.eeit45.champion.vegetarian.service;

import java.util.List;

import com.eeit45.champion.vegetarian.dto.LoginRequest;
import com.eeit45.champion.vegetarian.dto.UserQueryParams;
import com.eeit45.champion.vegetarian.dto.UserRequest;
import com.eeit45.champion.vegetarian.model.User;
import com.mysql.cj.log.Log;

public interface UserService {
	
	Integer totalUser(UserQueryParams userQueryParams);
	List<User> getUsers(UserQueryParams userQueryParams);
	
	List<User> getAllUser();
	User getUserById(Integer userId);
	
	User getUserByEmail(String loginEmail);
	
	Integer createUser(UserRequest userRequest);
	
	int updateUser(Integer userId, UserRequest userRequest);
	
	void deleteUserById(Integer userId);
	
	int updateUserStatus(Integer userId);
	
	User login(LoginRequest loginRequest);
	
	User resetPassword(LoginRequest loginRequest);

//	boolean isBanned(UserRequest userRequest);

}
