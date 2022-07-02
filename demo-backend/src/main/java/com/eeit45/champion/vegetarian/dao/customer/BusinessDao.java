package com.eeit45.champion.vegetarian.dao.customer;

import com.eeit45.champion.vegetarian.dto.customer.BusinessRegisterRequest;
import com.eeit45.champion.vegetarian.model.customer.Business;

public interface BusinessDao {
    Integer createUser(BusinessRegisterRequest businessRegisterRequest);

    Business getUserById(Integer userId);

    Business getUserByEmail(String loginEmail);
}
