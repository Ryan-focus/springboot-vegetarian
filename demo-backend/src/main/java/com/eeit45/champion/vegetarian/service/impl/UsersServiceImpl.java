package com.eeit45.champion.vegetarian.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eeit45.champion.vegetarian.dao.UsersDao;
import com.eeit45.champion.vegetarian.model.Users;
import com.eeit45.champion.vegetarian.service.UsersService;

@Component
public class UsersServiceImpl implements UsersService {

	@Autowired
    private UsersDao usersDao;
	
	@Override
	public List<Users> findAll() {
		return usersDao.findAll();
	}

	@Override
	public int addUser(Users users) {
		return usersDao.addUser(users);
	}

	@Override
	public Optional<Users> findById(Integer userId) {
		return usersDao.findById(userId);
	}

	@Override
	public int deleteUser(Integer userId) {
		return usersDao.deleteUser(userId);
	}

	@Override
	public int updateUser(Integer userId, Users users) {
		return usersDao.updateUser(userId, users);
	}

}
