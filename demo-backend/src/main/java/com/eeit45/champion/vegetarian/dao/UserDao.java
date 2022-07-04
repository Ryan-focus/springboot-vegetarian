package com.eeit45.champion.vegetarian.dao;

import java.util.List;

import com.eeit45.champion.vegetarian.dto.UserQueryParams;
import com.eeit45.champion.vegetarian.dto.UserRequest;
import com.eeit45.champion.vegetarian.model.User;

public interface UserDao {
	
	Integer totalUser(UserQueryParams userQueryParams);
	List<User> getUsers(UserQueryParams userQueryParams);
	
	List<User> getAllUser();
	User getUserById(Integer userId);
	
	Integer createUser(UserRequest userRequest);
	
	int updateUser(Integer userId, UserRequest userRequest);
	
	void deleteUserById(Integer userId);
	
	int updateUserStatus(Integer userId);

}
