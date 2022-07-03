package com.eeit45.champion.vegetarian.controller;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    //有分頁的GetMapping List
	@GetMapping("/users")
    public ResponseEntity<Page<User>> getUsers(
            //Filtering
            @RequestParam(required = false) String search,
            
            //Sorting
            @RequestParam(defaultValue = "lastLoginTime") String orderBy,
            @RequestParam(defaultValue = "desc") String sorting,

            //Pagination
            @RequestParam(defaultValue = "5")@Max(100) @Min(0) Integer limit,
            @RequestParam(defaultValue = "0")@Min(0) Integer offset
    ) {
		UserQueryParams userQueryParams = new UserQueryParams();
		userQueryParams.setSearch(search);
        userQueryParams.setOrderBy(orderBy);
		userQueryParams.setSorting(sorting);
		userQueryParams.setLimit(limit);
		userQueryParams.setOffset(offset);

        //GET product list
        List<User> userList =  userService.getUsers(userQueryParams);

        //GET product total
        Integer total = userService.totalUser(userQueryParams);

        //分頁
        Page<User> page = new Page<>();
        page.setLimit(limit);
        page.setOffset(offset);
        page.setTotal(total);
        page.setResults(userList);
        
        return ResponseEntity.status(HttpStatus.OK).body(page);
	}

    //無分頁的GetMapping List
	@GetMapping("/users/all")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> userList =userService.getAllUser();
        if (userList!=null){
            return ResponseEntity.status(HttpStatus.OK).body(userList);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
	
	@GetMapping("/users/{userId}")
    public ResponseEntity<User> getUser(@PathVariable Integer userId){
		User user = userService.getUserById(userId);

        if(user != null){
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
	
	@PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody @Valid UserRequest userRequest){
        Integer userId = userService.createUser(userRequest);

        User user = userService.getUserById(userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
	
	@PutMapping("/users/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Integer userId,
                                                 @RequestBody @Valid UserRequest userRequest){
        //Check Product 是否存在
		User checkUser = userService.getUserById(userId);
        if( checkUser == null ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

       // 修改商品的數據
        userService.updateUser(userId,userRequest);

        User updateUser = userService.getUserById(userId);

        return ResponseEntity.status(HttpStatus.OK).body(updateUser);
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<User> deleteUser(@PathVariable Integer userId){
    	userService.deleteUserById(userId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
