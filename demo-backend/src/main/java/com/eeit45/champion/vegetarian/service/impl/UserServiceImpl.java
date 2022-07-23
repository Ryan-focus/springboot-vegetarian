package com.eeit45.champion.vegetarian.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.eeit45.champion.vegetarian.dao.UserDao;
import com.eeit45.champion.vegetarian.dto.LoginRequest;
import com.eeit45.champion.vegetarian.dto.UserQueryParams;
import com.eeit45.champion.vegetarian.dto.UserRequest;
import com.eeit45.champion.vegetarian.model.User;
import com.eeit45.champion.vegetarian.service.UserService;

@Component
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private JavaMailSender javaMailSender;

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
	public User getUserByEmail(String loginEmail) {
		return userDao.getUserByEmail(loginEmail);
	}

	@Override
	public Integer createUser(UserRequest userRequest) {
		
		int result = userDao.createUser(userRequest);
		
		if(result != 0) {
			SimpleMailMessage sm = new SimpleMailMessage();
			sm.setFrom("eeit45no1@gmail.com");		//發送者
			sm.setTo(userRequest.getEmail());	//收件者
			sm.setSubject("愛蔬網帳號驗證信");	//主旨
			sm.setText("您好，會員" + userRequest.getUserName() + "\n\n請點選下方連結驗證email" + "\n\nhttp://localhost:8080/#/user/verification");	//內文
			javaMailSender.send(sm);
		}
		
		return result;
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
	public User login(LoginRequest loginRequest) {

		
		User user = userDao.getUserByEmail(loginRequest.getAccount());

//		System.out.println(user.toString());
		//帳號存在 且 密碼相符合
		if(user != null ) {
		//有此帳密但被禁用

		if(new BCryptPasswordEncoder().matches(loginRequest.getPassword(),user.getPassword())){
			if(userDao.isBanned(loginRequest.getAccount())) {

				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
			}
			userDao.updateLastLoginTime(loginRequest.getAccount());
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
	
	@Override
	public User resetPassword(LoginRequest loginRequest) {
		
		User user = userDao.getUserByEmail(loginRequest.getAccount());
		
		if(user == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
		String newPassword = userDao.resetPassword(user.getEmail());
		
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setFrom("eeit45no1@gmail.com");		//發送者
		sm.setTo(loginRequest.getAccount());	//收件者
		sm.setSubject("愛蔬網會員，您的密碼已重設");	//主旨
		sm.setText("您好，會員" + user.getUserName() + "\n\n你的新密碼為" + newPassword +"\n\n請查收");	//內文
		javaMailSender.send(sm);
		
		return user;
		
	}

	@Override
	public int updateImage(String base64DataString, int id) {
		return userDao.updateImage(base64DataString, id);
	}

	@Override
	public int updateUserName(String name, int id) {
		return userDao.updateUserName(name, id);
	}

	@Override
	public int updatePassword(String password, String newPassword, int id) {
		
		User user = userDao.getUserById(id);
		
		if(!new BCryptPasswordEncoder().matches(password ,user.getPassword())) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		return userDao.updatePassword(password, newPassword, id);
	}

	@Override
	public Integer countUser() {
		return userDao.countUser();
	}

	@Override
	public Integer countRegister() {
		return userDao.countRegister();
	}

	@Override
	public Integer countLogin() {
		return userDao.countLogin();
	}

	@Override
	public Double countPercentLogin() {
		return userDao.countPercentLogin();
	}

	@Override
	public int updateStatus(String status, String email) {
		return userDao.updateStatus(status, email);
	}

}
