package com.eeit45.champion.vegetarian.service.customer;

import com.eeit45.champion.vegetarian.dto.LoginRequest;
import com.eeit45.champion.vegetarian.dto.customer.BusinessRegisterRequest;
import com.eeit45.champion.vegetarian.model.customer.Business;

public interface BusinessService {
    Business login(LoginRequest loginRequest);

    Integer register(BusinessRegisterRequest businessRegisterRequest);

    Business getBusinessId(Integer businessId);
}
