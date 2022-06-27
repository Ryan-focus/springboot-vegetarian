package com.eeit45.champion.vegetarian.controller;

import com.eeit45.champion.vegetarian.constant.ProductCategory;
import com.eeit45.champion.vegetarian.constant.VeganCategory;
import com.eeit45.champion.vegetarian.dto.ProductQueryParams;
import com.eeit45.champion.vegetarian.dto.ProductRequest;
import com.eeit45.champion.vegetarian.model.Product;
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
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<Page<Product>> getProducts(
            //Filtering
            @RequestParam(required = false) ProductCategory category,
            @RequestParam(required = false) VeganCategory veganCategory,
            @RequestParam(required = false) String search,
            //Sorting
            @RequestParam(defaultValue = "updatedTime") String orderBy,
            @RequestParam(defaultValue = "desc") String sorting,
            //Pagination
            @RequestParam(defaultValue = "5")@Max(100) @Min(0) Integer limit,
            @RequestParam(defaultValue = "0")@Min(0) Integer offset
    ){
        ProductQueryParams productQueryParams = new ProductQueryParams();
        productQueryParams.setCategory(category);
        productQueryParams.setVeganCategory(veganCategory);
        productQueryParams.setSearch(search);
        productQueryParams.setOrderBy(orderBy);
        productQueryParams.setSorting(sorting);
        productQueryParams.setLimit(limit);
        productQueryParams.setOffset(offset);

        //GET product list
        List<Product> productList =  productService.getProducts(productQueryParams);

        //GET product total
        Integer total = productService.totalProduct(productQueryParams);

        //分頁
        Page<Product> page = new Page<>();
        page.setLimit(limit);
        page.setOffset(offset);
        page.setTotal(total);
        page.setResults(productList);
        
        return ResponseEntity.status(HttpStatus.OK).body(page);
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer productId){
        Product product = productService.getProductById(productId);

        if(product != null){
            return ResponseEntity.status(HttpStatus.OK).body(product);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductRequest productRequest){
        Integer productId = productService.createProduct(productRequest);

        Product product = productService.getProductById(productId);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("/products/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer productId,
                                                 @RequestBody @Valid ProductRequest productRequest){
        //Check Product 是否存在
        Product checkProduct = productService.getProductById(productId);
        if( checkProduct == null ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

       // 修改商品的數據
       productService.updateProduct(productId,productRequest);

        Product updateProduct = productService.getProductById(productId);

        return ResponseEntity.status(HttpStatus.OK).body(updateProduct);
    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Integer productId){
        productService.deleteProductById(productId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}