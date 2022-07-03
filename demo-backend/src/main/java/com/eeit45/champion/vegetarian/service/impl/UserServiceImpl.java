package com.eeit45.champion.vegetarian.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eeit45.champion.vegetarian.dao.UserDao;
import com.eeit45.champion.vegetarian.dto.UserQueryParams;
import com.eeit45.champion.vegetarian.dto.UserRequest;
import com.eeit45.champion.vegetarian.model.User;
import com.eeit45.champion.vegetarian.service.UserService;

@Component
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	@Override
	public Integer totalUser(UserQueryParams userQueryParams) {
		return userDao.totalUser(userQueryParams);
	}

	@Override
	public List<User> getUsers(UserQueryParams userQueryParams) {
		return userDao.getUsers(userQueryParams);
	}

	@Override
	public List<User> getAllUser() {
		return userDao.getAllUser();
	}

	@Override
	public User getUserById(Integer userId) {
		return userDao.getUserById(userId);
	}

	@Override
	public Integer createUser(UserRequest userRequest) {
		return userDao.createUser(userRequest);
	}

	@Override
	public int updateUser(Integer userId, UserRequest userRequest) {
		return userDao.updateUser(userId, userRequest);
	}

	@Override
	public void deleteUserById(Integer userId) {
		userDao.deleteUserById(userId);
	}

}
