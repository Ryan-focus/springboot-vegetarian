package com.eeit45.champion.vegetarian.service.customer;

import com.eeit45.champion.vegetarian.dto.LoginRequest;
import com.eeit45.champion.vegetarian.dto.customer.BusinessRegisterRequest;
import com.eeit45.champion.vegetarian.dto.customer.BusinessRequest;
import com.eeit45.champion.vegetarian.model.customer.Business;

import java.util.List;

public interface BusinessService {
    Business login(LoginRequest loginRequest);

    Integer register(BusinessRegisterRequest businessRegisterRequest);

    Business getBusinessId(Integer businessId);

    List<Business> getAllBusiness();

    void updateRestaurantNumber(Integer businessId, BusinessRequest businessRequest);

    Business resetPassword(LoginRequest loginRequest);
}
