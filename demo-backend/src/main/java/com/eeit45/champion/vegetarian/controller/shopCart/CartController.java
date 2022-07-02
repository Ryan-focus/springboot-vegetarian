package com.eeit45.champion.vegetarian.controller.shopCart;

import com.eeit45.champion.vegetarian.dto.shopCart.CartEntryRequest;
import com.eeit45.champion.vegetarian.model.shopCart.Cart;
import com.eeit45.champion.vegetarian.model.shopCart.CartEntry;
import com.eeit45.champion.vegetarian.service.shopCart.CartService;
import com.eeit45.champion.vegetarian.util.shopCart.CartEntryDetail;
import com.eeit45.champion.vegetarian.util.shopCart.CartShow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/{userId}")
    public ResponseEntity<CartShow> getCartById(@PathVariable Integer userId
    ) {

        Cart cart = cartService.getCartById(userId);
        if (cart != null) {
            CartShow cartShow = new CartShow();
            cartShow.setCartUUID(cart.getCartUUID());
            cartShow.setUserId(cart.getUserId());
            //轉換原始資料cart資料，搜尋購物車裡面的商品
            List<CartEntry> originalCart = cartService.getCartEntriesByUUID(cart.getCartUUID());
            List<CartEntryDetail> cartEntryDetailList = new ArrayList<>();
            //判斷購物車裡面是否已有商品,有的話去撈資料，沒有就回傳null
            if(originalCart!=null){
            for (int i = 0; i <originalCart.size() ; i++) {
                //將cartEntry裡的ProductId轉換成product
                CartEntryDetail cartEntryDetail= cartService.showCartEntryDetail(originalCart.get(i));
                cartEntryDetailList.add(cartEntryDetail);
                cartShow.setCartEntryDetailList(cartEntryDetailList);
            }}else {
                cartShow.setCartEntryDetailList(null);
            }
            return ResponseEntity.status(HttpStatus.OK).body(cartShow);
        } else {
            cartService.CreateNewCart(userId);
            Cart newCart = cartService.getCartById(userId);
            CartShow cartShow = new CartShow();
            cartShow.setCartUUID(newCart.getCartUUID());
            cartShow.setUserId(newCart.getUserId());
            cartShow.setCartEntryDetailList(null);
            return ResponseEntity.status(HttpStatus.OK).body(cartShow);
        }

    }

    @PostMapping
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
   @PatchMapping("/{cartEntryId}")
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
