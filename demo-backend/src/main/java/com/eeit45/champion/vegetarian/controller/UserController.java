package com.eeit45.champion.vegetarian.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.eeit45.champion.vegetarian.dto.LoginRequest;
import com.eeit45.champion.vegetarian.dto.UserQueryParams;
import com.eeit45.champion.vegetarian.dto.UserRequest;
import com.eeit45.champion.vegetarian.model.User;
import com.eeit45.champion.vegetarian.service.UserService;
import com.eeit45.champion.vegetarian.util.Page;

@Validated
@RestController
public class UserController {

	@Autowired
	private UserService userService;

	// 有分頁的GetMapping List
	@GetMapping("/users")
	public ResponseEntity<Page<User>> getUsers(
			// Filtering
			@RequestParam(required = false) String search,

			// Sorting
			@RequestParam(defaultValue = "lastLoginTime") String orderBy,
			@RequestParam(defaultValue = "desc") String sorting,

			// Pagination
			@RequestParam(defaultValue = "5") @Max(100) @Min(0) Integer limit,
			@RequestParam(defaultValue = "0") @Min(0) Integer offset) {
		UserQueryParams userQueryParams = new UserQueryParams();
		userQueryParams.setSearch(search);
		userQueryParams.setOrderBy(orderBy);
		userQueryParams.setSorting(sorting);
		userQueryParams.setLimit(limit);
		userQueryParams.setOffset(offset);

		// GET product list
		List<User> userList = userService.getUsers(userQueryParams);

		// GET product total
		Integer total = userService.totalUser(userQueryParams);

		// 分頁
		Page<User> page = new Page<>();
		page.setLimit(limit);
		page.setOffset(offset);
		page.setTotal(total);
		page.setResults(userList);

		return ResponseEntity.status(HttpStatus.OK).body(page);
	}

	// 無分頁的GetMapping List
	@GetMapping("/users/all")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> userList = userService.getAllUser();
		if (userList != null) {
			return ResponseEntity.status(HttpStatus.OK).body(userList);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@GetMapping("/users/{userId}")
	public ResponseEntity<User> getUser(@PathVariable Integer userId) {
		User user = userService.getUserById(userId);

		if (user != null) {
			return ResponseEntity.status(HttpStatus.OK).body(user);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@PostMapping("/users")
	public ResponseEntity<User> createUser(@RequestBody @Valid UserRequest userRequest) {
		Integer userId = userService.createUser(userRequest);

		User user = userService.getUserById(userId);
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}

	@PutMapping("/users/{userId}")
	public ResponseEntity<User> updateUser(@PathVariable Integer userId, @RequestBody @Valid UserRequest userRequest) {

		User checkUser = userService.getUserById(userId);
		if (checkUser == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		userService.updateUser(userId, userRequest);

		User updateUser = userService.getUserById(userId);

		return ResponseEntity.status(HttpStatus.OK).body(updateUser);
	}

	@DeleteMapping("/users/{userId}")
	public ResponseEntity<User> deleteUser(@PathVariable Integer userId) {
		userService.deleteUserById(userId);

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@PatchMapping("/users/{userId}")
	public ResponseEntity<User> updateUserStatus(@PathVariable Integer userId) {

		User checkUser = userService.getUserById(userId);
		if (checkUser == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		userService.updateUserStatus(userId);

		User updateUserStatus = userService.getUserById(userId);

		return ResponseEntity.status(HttpStatus.OK).body(updateUserStatus);
	}

	@PostMapping("/user/login")
	public ResponseEntity<User> login(@RequestBody LoginRequest loginRequest) {

		User user = userService.login(loginRequest);

		System.out.println(1);
		if (user != null) {
			return ResponseEntity.status(HttpStatus.OK).body(user); // 登入成功
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // 沒註冊
	}

	@PostMapping("/user/sendMail")
	public ResponseEntity<User> resetPassword(@RequestBody LoginRequest loginRequest) {

		User user = userService.resetPassword(loginRequest);

		if (user != null) {
			return ResponseEntity.status(HttpStatus.OK).body(user);
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}

	@PostMapping("/user/register")
	public ResponseEntity<User> register(@RequestBody UserRequest userRequest) {

		String checkEmail = userRequest.getEmail();

		User user = userService.getUserByEmail(checkEmail);

		if (user != null) {
			throw new ResponseStatusException(HttpStatus.CONFLICT);
		}

		int insertResult = userService.createUser(userRequest);
		// 測東東
		if (insertResult == 0) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
		}
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@PostMapping("/user/uploadImage")
	public ResponseEntity<User> uploadImage(@RequestParam("img") MultipartFile file, @RequestParam("id") int id) {

		try {
			if (!file.isEmpty()) {

				byte[] bytes = file.getBytes();

				String base64DataString = Base64.getEncoder().encodeToString(bytes);

				int result = userService.updateImage(base64DataString, id);

				if (result == 1) {
					return ResponseEntity.status(HttpStatus.OK).build();
				} else {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

	}
	
	@PatchMapping("/user/updateUserName")
	public ResponseEntity<User> updateUserName(@RequestParam("name") String name, @RequestParam("id") int id){
		
		int result = userService.updateUserName(name, id);
		
		if (result == 1) {
			
			return ResponseEntity.status(HttpStatus.OK).build();
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
	}
	
	@PostMapping("/user/updatePassword")
	public ResponseEntity<User> updatePassword(@RequestParam("pw") String password, @RequestParam("newpw") String newPassword, @RequestParam("id") int id){
		
		int result = userService.updatePassword(password, newPassword, id);

		if (result == 1) {
			
			return ResponseEntity.status(HttpStatus.OK).build();
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
	}
	
	@GetMapping("/user/countUser")
	public Integer countUser() {
		return userService.countUser();
	}
	
	@GetMapping("/user/countRegister")
	public Integer countRegister() {
		return userService.countRegister();
	}
	
	@GetMapping("/user/countLogin")
	public Integer countLogin() {
		return userService.countLogin();
	}
	
	@GetMapping("/user/countPercentLogin")
	public Double countPercentLogin() {
		return userService.countPercentLogin();
	}
	
	@PatchMapping("/user/updateStatus")
	public ResponseEntity<User> updateStatus(@RequestParam("status") String status, @RequestParam("email") String email){
		
		int result = userService.updateStatus(status, email);
		
		if (result == 1) {
			
			return ResponseEntity.status(HttpStatus.OK).build();
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
	}

}
