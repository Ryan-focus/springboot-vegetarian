package com.eeit45.champion.vegetarian.controller;

import com.eeit45.champion.vegetarian.dao.customer.BusinessDao;
import com.eeit45.champion.vegetarian.dto.customer.BusinessLoginRequest;
import com.eeit45.champion.vegetarian.dto.customer.BusinessRegisterRequest;
import com.eeit45.champion.vegetarian.model.customer.Business;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class BusinessControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BusinessDao businessDao;

    private ObjectMapper objectMapper = new ObjectMapper();

    // 註冊新帳號
    @Test
    public void register_success() throws Exception {
        BusinessRegisterRequest businessRegisterRequest = new BusinessRegisterRequest();
        businessRegisterRequest.setLoginEmail(("test1@gmail.com"));
        businessRegisterRequest.setPassword("123");

        String json = objectMapper.writeValueAsString(businessRegisterRequest);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(201))
                .andExpect(jsonPath("$.userId", notNullValue()))
                .andExpect(jsonPath("$.email", equalTo("test1@gmail.com")))
                .andExpect(jsonPath("$.createdDate", notNullValue()))
                .andExpect(jsonPath("$.lastModifiedDate", notNullValue()));

        // 檢查資料庫中的密碼不為明碼
        Business business = businessDao.getUserByEmail(businessRegisterRequest.getLoginEmail());
        //比較從資料庫取得的使用者的密碼，不得與使用者輸入的密碼相同。
        assertNotEquals(businessRegisterRequest.getPassword(), business.getPassword());
    }

    @Test
    public void register_invalidEmailFormat() throws Exception {
        BusinessRegisterRequest businessRegisterRequest = new BusinessRegisterRequest();
        businessRegisterRequest.setLoginEmail("3gd8e7q34l9");
        businessRegisterRequest.setPassword("123");

        String json = objectMapper.writeValueAsString(businessRegisterRequest);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(400));
    }

    @Test
    public void register_emailAlreadyExist() throws Exception {
        // 先註冊一個帳號
        BusinessRegisterRequest businessRegisterRequest = new BusinessRegisterRequest();
        businessRegisterRequest.setLoginEmail("test2@gmail.com");
        businessRegisterRequest.setPassword("123");

        String json = objectMapper.writeValueAsString(businessRegisterRequest);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(201));

        // 再次使用同個 email 註冊
        mockMvc.perform(requestBuilder)
                .andExpect(status().is(400));
    }

    // 登入
    @Test
    public void login_success() throws Exception {
        // 先註冊新帳號
        BusinessRegisterRequest businessRegisterRequest = new BusinessRegisterRequest();
        businessRegisterRequest.setLoginEmail("test3@gmail.com");
        businessRegisterRequest.setPassword("123");

        register(businessRegisterRequest);

        // 再測試登入功能
        BusinessLoginRequest businessLoginRequest = new BusinessLoginRequest();
        businessLoginRequest.setLoginEmail(businessRegisterRequest.getLoginEmail());
        businessLoginRequest.setPassword(businessRegisterRequest.getPassword());

        String json = objectMapper.writeValueAsString(businessRegisterRequest);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.userId", notNullValue()))
                .andExpect(jsonPath("$.email", equalTo(businessRegisterRequest.getLoginEmail())))
                .andExpect(jsonPath("$.createdDate", notNullValue()))
                .andExpect(jsonPath("$.lastModifiedDate", notNullValue()));
    }

    @Test
    public void login_invalidEmailFormat() throws Exception {
        BusinessLoginRequest businessLoginRequest = new BusinessLoginRequest();
        businessLoginRequest.setLoginEmail("hkbudsr324");
        businessLoginRequest.setPassword("123");

        String json = objectMapper.writeValueAsString(businessLoginRequest);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(400));
    }

    @Test
    public void login_emailNotExist() throws Exception {
        BusinessLoginRequest businessLoginRequest = new BusinessLoginRequest();
        businessLoginRequest.setLoginEmail("unknown@gmail.com");
        businessLoginRequest.setPassword("123");

        String json = objectMapper.writeValueAsString(businessLoginRequest);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(400));
    }

    @Test
    public void login_wrongPassword() throws Exception {
        // 先註冊新帳號
        BusinessRegisterRequest businessRegisterRequest = new BusinessRegisterRequest();
        businessRegisterRequest.setLoginEmail("test4@gmail.com");
        businessRegisterRequest.setPassword("123");

        register(businessRegisterRequest);

        // 測試密碼輸入錯誤的情況
        BusinessLoginRequest businessLoginRequest = new BusinessLoginRequest();
        businessLoginRequest.setLoginEmail(businessRegisterRequest.getLoginEmail());
        businessLoginRequest.setPassword("unknown");

        String json = objectMapper.writeValueAsString(businessLoginRequest);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(400));
    }

    private void register(BusinessRegisterRequest businessRegisterRequest) throws Exception {
        String json = objectMapper.writeValueAsString(businessRegisterRequest);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(201));
    }
}