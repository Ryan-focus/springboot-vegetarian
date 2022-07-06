package com.eeit45.champion.vegetarian.dao.customer;

import com.eeit45.champion.vegetarian.dto.customer.ReserveQueryParams;
import com.eeit45.champion.vegetarian.dto.customer.ReserveRequest;
import com.eeit45.champion.vegetarian.model.customer.Reserve;

import java.util.List;

public interface ReserveDao {

    //查詢單一對象，回傳使用Reserve(物件)
    Reserve getReserveById(Integer businessId, Integer reserveId);

    //使用者前台發送一筆創建訂單
    Integer createReserve(Integer businessId, ReserveRequest reserveRequest);


    List<Reserve> getAllReserve(Integer businessId);
}
