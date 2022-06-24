package com.eeit45.champion.vegetarian.controller;

import com.eeit45.champion.vegetarian.model.Cart;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {

        @GetMapping("/")
        public ResponseEntity<Cart> getCartById(@PathVariable Integer userId){
        return null;
        }
}
