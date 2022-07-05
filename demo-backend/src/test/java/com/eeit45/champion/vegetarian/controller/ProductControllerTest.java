package com.eeit45.champion.vegetarian.controller;

import com.eeit45.champion.vegetarian.constant.ProductCategory;
import com.eeit45.champion.vegetarian.constant.VeganCategory;
import com.eeit45.champion.vegetarian.dto.shopCart.ProductRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void getProduct() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/products/{productId}",1);

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productName", equalTo("《UFUMI》純素無麩質米圈圈 紫薯腰果口味~無麩質全素甜甜圈 Q軟口感超好吃！")))
                .andExpect(jsonPath("$.category", equalTo("飾品")))
                .andExpect(jsonPath("$.veganCategory",equalTo("VEGAN")))
                .andExpect(jsonPath("$.imageUrl",notNullValue()))
                .andExpect(jsonPath("$.createdTime",notNullValue()))
                .andExpect(jsonPath("$.updatedTime",notNullValue()));
    }

    @Test
    public void getProduct_notFound() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/products/{productId}",2000);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(404));
    }

    @Transactional
    @Test
    public  void createProduct_illegalArgument() throws Exception{
        ProductRequest productRequest = new ProductRequest();
        productRequest.setProductName("testName");

        String json  = objectMapper.writeValueAsString(productRequest);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);
        mockMvc.perform(requestBuilder)
                .andExpect(status().is(400));
    }

    @Transactional
    @Test
    public void updatedProduct() throws Exception{
        ProductRequest productRequest = new ProductRequest();
        productRequest.setProductName("test product");
        productRequest.setCategory(ProductCategory.飾品);
        productRequest.setVeganCategory(VeganCategory.VEGAN);
        productRequest.setProductPrice(30678);
        productRequest.setProductImage("30678.jpg");

        String json = objectMapper.writeValueAsString(productRequest);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/products/{productId}",29);
    }

}
