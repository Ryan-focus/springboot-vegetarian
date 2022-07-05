package com.eeit45.champion.vegetarian.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

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

	@Override
	public int updateUserStatus(Integer userId) {
		return userDao.updateUserStatus(userId);
	}

	@Override
	public User login(UserRequest userRequest) {
//		Boolean userBannded = userDao.isBanned(userRequest);
		User user = userDao.login(userRequest);
		//帳號存在 且 密碼相符合
		if(user != null ) {
		//有此帳密但被禁用
		if(userDao.isBanned(userRequest)) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
		}
		if(new BCryptPasswordEncoder().matches(userRequest.getPassword(),user.getPassword())){
			return user;
		}
		//帳號存在 但密碼錯誤
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
		return null;
	}

//	@Override
//	public boolean isBanned(UserRequest userRequest) {
//		return false;
//	}

}
