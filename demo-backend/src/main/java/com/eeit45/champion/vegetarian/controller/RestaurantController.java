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

import com.eeit45.champion.vegetarian.constant.RestaurantCategory;
import com.eeit45.champion.vegetarian.constant.RestaurantType;
import com.eeit45.champion.vegetarian.dto.RestaurantQueryParams;
import com.eeit45.champion.vegetarian.dto.RestaurantRequest;
import com.eeit45.champion.vegetarian.model.Restaurant;
import com.eeit45.champion.vegetarian.service.RestaurantService;
import com.eeit45.champion.vegetarian.util.Page;

@Validated
@RestController
public class RestaurantController {
	
	@Autowired
	private RestaurantService restaurantService;
	
	//搜尋全部、有條件的篩選
	@GetMapping("/restaurantList")
	public ResponseEntity<Page<Restaurant>> getRestaurants(
			//查詢條件 Filtering
			@RequestParam(required = false) RestaurantCategory restaurantCategory,
			@RequestParam(required = false) RestaurantType restaurantType,
			@RequestParam(required = false) String searchName,
			@RequestParam(required = false) String searchAddress,
			
			//排序 Sorting--orderBy是根據欄位排序,sort是升降冪,desc是大到小 asc是小到大
			@RequestParam(defaultValue = "createdTime") String orderBy,
			@RequestParam(defaultValue = "desc") String sort,
			
			//分頁 Pagination--limit限制只顯示幾筆資料,offset跳過幾筆資料
			@RequestParam(defaultValue = "5") @Max(1000) @Min(0) Integer limit,
			@RequestParam(defaultValue = "0") @Min(0) Integer offset
		){
		
		//設定一個變數用來存放前端傳過來的所有參數
		RestaurantQueryParams restaurantQueryParams = new RestaurantQueryParams();
		restaurantQueryParams.setRestaurantCategory(restaurantCategory);
		restaurantQueryParams.setRestaurantType(restaurantType);
		restaurantQueryParams.setSearchName(searchName);
		restaurantQueryParams.setSearchAddress(searchAddress);
		
		restaurantQueryParams.setOrderBy(orderBy);
		restaurantQueryParams.setSort(sort);
		
		restaurantQueryParams.setLimit(limit);
		restaurantQueryParams.setOffset(offset);
		
		//將查詢到的資料放在List中傳回
		List<Restaurant> restaurantList = restaurantService.getRestaurants(restaurantQueryParams);
		
		//取得restaurant總數
		Integer total = restaurantService.countRestaurant(restaurantQueryParams);
		
		//將總數分頁
		Page<Restaurant> page = new Page<>();
		page.setLimit(limit);
		page.setOffset(offset);
		page.setTotal(total);
		page.setResults(restaurantList);
		
		return ResponseEntity.status(HttpStatus.OK).body(page);
	}
	
	@GetMapping("/restaurants")
	public ResponseEntity<List<Restaurant>> getAllRestaurant() {
		List<Restaurant> restaurantList = restaurantService.getAllRestaurants();

		if (restaurantList != null) {
			return ResponseEntity.status(HttpStatus.OK).body(restaurantList);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	
	@GetMapping("/restaurants/{restaurantNumber}")
	public ResponseEntity<Restaurant> getRestaurant(@PathVariable Integer restaurantNumber) {
		Restaurant restaurant = restaurantService.getRestaurantByNumber(restaurantNumber);

		if (restaurant != null) {
			return ResponseEntity.status(HttpStatus.OK).body(restaurant);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@PostMapping("/restaurants")
	public ResponseEntity<Restaurant> createRestaurant(@RequestBody @Valid RestaurantRequest restaurantRequest){
		Integer restaurantNumber = restaurantService.createRestaurant(restaurantRequest);
		Restaurant restaurant = restaurantService.getRestaurantByNumber(restaurantNumber);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(restaurant);
	}
	
	@PutMapping("/restaurants/{restaurantNumber}")
	public ResponseEntity<Restaurant> updateRestaurant(@PathVariable Integer restaurantNumber,@RequestBody @Valid RestaurantRequest restaurantRequest){
		
		//檢查餐廳是否存在
		Restaurant restaurant = restaurantService.getRestaurantByNumber(restaurantNumber);
		if(restaurant == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		//修改餐廳數據
		restaurantService.updateRestaurant(restaurantNumber,restaurantRequest);
		Restaurant updateRestaurant = restaurantService.getRestaurantByNumber(restaurantNumber);
		
		return ResponseEntity.status(HttpStatus.OK).body(updateRestaurant);
	}
	
	@DeleteMapping("/restaurants/{restaurantNumber}")
	public ResponseEntity<?> deleteRestaurant(@PathVariable Integer restaurantNumber){
		restaurantService.deleteRestaurantByNumber(restaurantNumber);
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
