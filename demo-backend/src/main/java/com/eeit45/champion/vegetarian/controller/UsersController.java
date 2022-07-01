package com.eeit45.champion.vegetarian.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eeit45.champion.vegetarian.dao.UsersDao;
import com.eeit45.champion.vegetarian.model.Users;

@RestController
@RequestMapping("user")
public class UsersController {
	
	private final UsersDao usersDao;
	
	@Autowired
	   public UsersController(UsersDao usersDao) {
	       this.usersDao = usersDao;
	   }
	
	@PostMapping
	public void addEmployee(@Valid @RequestBody Users users){
		usersDao.addUser(users);
	}
	
	@GetMapping
	public List<Users> findAll(){
	   return usersDao.findAll();
	}
	
	@GetMapping("/{userId}")
	public Users findById(@PathVariable("userId") int userId){
	   return usersDao.findById(userId)
	           .orElseThrow(() -> new RuntimeException("employee not found"));
	}
	
	@PutMapping("/{userId}")
	public int updateEmployee(@Valid @RequestBody Users users, @PathVariable("userId") int userId){
	   return usersDao.updateUser(userId,users);
	}
	
	@DeleteMapping("/{userId}")
	public void deleteEmployee(@PathVariable("userId") int userId){
		usersDao.deleteUser(userId);
	}

}
