package com.eeit45.champion.vegetarian.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eeit45.champion.vegetarian.dto.RestaurantRequest;
import com.eeit45.champion.vegetarian.model.Restaurant;
import com.eeit45.champion.vegetarian.service.RestaurantService;

@RestController
public class RestaurantController {
	
	@Autowired
	private RestaurantService restaurantService;

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
