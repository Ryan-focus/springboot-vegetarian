package com.eeit45.champion.vegetarian.controller;

import com.eeit45.champion.vegetarian.dto.CartEntryRequest;
import com.eeit45.champion.vegetarian.model.Cart;
import com.eeit45.champion.vegetarian.model.CartEntry;
import com.eeit45.champion.vegetarian.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @PostMapping("/ ")
    public ResponseEntity<CartEntry> addToCart(@RequestBody @Valid CartEntryRequest cartEntryRequest) {
        Integer cartEntryId = cartService.AddToCart(cartEntryRequest);
        CartEntry cartEntry = cartService.getSingleCartEntry(cartEntryId);


        return ResponseEntity.status(HttpStatus.CREATED).body(cartEntry);
    }

    @DeleteMapping("/{cartEntryId}")
    public ResponseEntity<CartEntry> deleteProduct(@PathVariable Integer cartEntryId) {
        cartService.deleteProductFromCartById(cartEntryId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
   @PatchMapping("/updateQuantity/{cartEntryId}")
    public ResponseEntity<CartEntry> updateQuantity(@PathVariable Integer cartEntryId,
                                                    @RequestBody CartEntryRequest cartEntryRequest) {
        CartEntry checkCartEntry = cartService.getSingleCartEntry(cartEntryId);
        if (checkCartEntry == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        cartService.updateQuantity(cartEntryId, cartEntryRequest);
        CartEntry updateCartEntry = cartService.getSingleCartEntry(cartEntryId);
        return ResponseEntity.status(HttpStatus.OK).body(updateCartEntry);
    }


}
