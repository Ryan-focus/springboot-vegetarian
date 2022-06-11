package com.eeit45.champion.vegetarian.controller;

import com.eeit45.champion.vegetarian.constant.ProductCategory;
import com.eeit45.champion.vegetarian.constant.VeganCategory;
import com.eeit45.champion.vegetarian.dto.CartRequest;
import com.eeit45.champion.vegetarian.dto.ProductQueryParams;
import com.eeit45.champion.vegetarian.dto.ProductRequest;
import com.eeit45.champion.vegetarian.model.Cart;
import com.eeit45.champion.vegetarian.model.Product;
import com.eeit45.champion.vegetarian.service.CartService;
import com.eeit45.champion.vegetarian.service.ProductService;
import com.eeit45.champion.vegetarian.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Validated
@RestController
public class CartController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CartService cartService;



    @GetMapping("/cart/{userId}")
    public ResponseEntity<Cart> getCartItemById(@PathVariable Integer userId){
        Cart cart = cartService.getCartItemById(userId);

        if(cart != null){
            return ResponseEntity.status(HttpStatus.OK).body(cart);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/cart")
    public ResponseEntity<Cart> insertCartItem(@RequestBody @Valid CartRequest cartRequest){
        Integer userId = cartService.insertCartItem(cartRequest);

        Cart cart = cartService.getCartItemById(userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(cart);
    }

//    @PutMapping("/products/{productId}")
//    public ResponseEntity<Product> updateProduct(@PathVariable Integer productId,
//                                                 @RequestBody @Valid ProductRequest productRequest){
//        //Check Product 是否存在
//        Product checkProduct = productService.getProductById(productId);
//        if( checkProduct == null ) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//
//       // 修改商品的數據
//       productService.updateProduct(productId,productRequest);
//
//        Product updateProduct = productService.getProductById(productId);
//
//        return ResponseEntity.status(HttpStatus.OK).body(updateProduct);
//    }
//
//    @DeleteMapping("/products/{productId}")
//    public ResponseEntity<Product> deleteProduct(@PathVariable Integer productId){
//        productService.deleteProductById(productId);
//
//        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//    }

}
