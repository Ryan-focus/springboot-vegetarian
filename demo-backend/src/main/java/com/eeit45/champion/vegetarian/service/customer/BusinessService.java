package com.eeit45.champion.vegetarian.service.customer;

import com.eeit45.champion.vegetarian.dto.customer.BusinessLoginRequest;
import com.eeit45.champion.vegetarian.dto.customer.BusinessRegisterRequest;
import com.eeit45.champion.vegetarian.model.customer.Business;

public interface BusinessService {
    Business login(BusinessLoginRequest businessLoginRequest);

    Integer register(BusinessRegisterRequest businessRegisterRequest);

    Business getBusinessId(Integer businessId);
}
