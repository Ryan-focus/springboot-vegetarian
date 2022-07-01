package com.eeit45.champion.vegetarian.service;

import java.util.List;
import java.util.Optional;

import com.eeit45.champion.vegetarian.model.Users;

public interface UsersService {
	
	List<Users> findAll();

	int addUser(Users users);

	Optional<Users> findById(Integer userId);

	int deleteUser(Integer userId);

	int updateUser(Integer userId, Users users);

}
