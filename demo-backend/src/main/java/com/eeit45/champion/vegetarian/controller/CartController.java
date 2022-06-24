package com.eeit45.champion.vegetarian.controller;

import com.eeit45.champion.vegetarian.dto.CartEntryRequest;
import com.eeit45.champion.vegetarian.model.Cart;
import com.eeit45.champion.vegetarian.model.CartEntry;
import com.eeit45.champion.vegetarian.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/{userId}")
    public ResponseEntity<Cart> getCartById(@PathVariable Integer userId) {
        Cart cart = cartService.getCartById(userId);

        if (cart != null) {
            return ResponseEntity.status(HttpStatus.OK).body(cart);
        } else {
            cartService.CreateNewCart(userId);
            Cart newCart = cartService.getCartById(userId);
            return ResponseEntity.status(HttpStatus.OK).body(newCart);
        }
    }

    public ResponseEntity<CartEntry> addToCart(@RequestBody CartEntryRequest cartEntryRequest) {
        Integer cartEntryId = cartService.AddToCart(cartEntryRequest);
        List<CartEntry> cartEntry = cartService.getCartEntriesById(cartEntryId);


        return null;
    }


}
