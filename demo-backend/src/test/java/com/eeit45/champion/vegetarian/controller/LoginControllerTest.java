package com.eeit45.champion.vegetarian.controller;

import com.eeit45.champion.vegetarian.dao.customer.BusinessDao;
import com.eeit45.champion.vegetarian.dto.LoginRequest;
import com.eeit45.champion.vegetarian.dto.customer.BusinessRegisterRequest;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class LoginControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BusinessDao businessDao;

    private ObjectMapper objectMapper = new ObjectMapper();


    // 測試商家註冊 且登入狀態方法
    @Test
    public void login_success() throws Exception {
        // 先註冊新帳號
        BusinessRegisterRequest businessRegisterRequest = new BusinessRegisterRequest();
        businessRegisterRequest.setAccount("test3@gmail.com");
        businessRegisterRequest.setPassword("12345678");

        businessRegister(businessRegisterRequest);

        // 再測試登入功能
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setAccount(businessRegisterRequest.getAccount());
        loginRequest.setPassword(businessRegisterRequest.getPassword());

        String json = objectMapper.writeValueAsString(businessRegisterRequest);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.businessId", notNullValue()))
                .andExpect(jsonPath("$.email", equalTo(businessRegisterRequest.getAccount())))
                .andExpect(jsonPath("$.createdDate", notNullValue()))
                .andExpect(jsonPath("$.lastModifiedDate", notNullValue()));
    }

    @Test
    public void login_invalidEmailFormat() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setAccount("hkbudsr324");
        loginRequest.setPassword("12345678");

        String json = objectMapper.writeValueAsString(loginRequest);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(400));
    }

    @Test
    public void login_emailNotExist() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setAccount("unknown@gmail.com");
        loginRequest.setPassword("12345678");

        String json = objectMapper.writeValueAsString(loginRequest);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(400));
    }

    @Test
    public void login_wrongPassword() throws Exception {
        // 先註冊新帳號
        BusinessRegisterRequest businessRegisterRequest = new BusinessRegisterRequest();
        businessRegisterRequest.setAccount("test4@gmail.com");
        businessRegisterRequest.setPassword("123");

        businessRegister(businessRegisterRequest);

        // 測試密碼輸入錯誤的情況
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setAccount(businessRegisterRequest.getAccount());
        loginRequest.setPassword("unknown");

        String json = objectMapper.writeValueAsString(loginRequest);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(400));
    }



    private void businessRegister(BusinessRegisterRequest businessRegisterRequest) throws Exception {
        String json = objectMapper.writeValueAsString(businessRegisterRequest);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/business/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(201));
    }
}
