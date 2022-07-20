package com.eeit45.champion.vegetarian.dao.customer;

import com.eeit45.champion.vegetarian.dto.customer.BusinessRegisterRequest;
import com.eeit45.champion.vegetarian.dto.customer.BusinessRequest;
import com.eeit45.champion.vegetarian.model.customer.Business;

import java.util.List;

public interface BusinessDao {
    Integer createBusiness(BusinessRegisterRequest businessRegisterRequest);

    Business getBusinessById(Integer businessId);

    Business getBusinessByEmail(String account);

    void updateStatus(Integer businessId, String status,String UUID);

    List<Business> getAllBusiness();

    void updateRestaurantNumber(Integer businessId, BusinessRequest businessRequest);

    String resetPassword(String email);
}
