package com.eeit45.champion.vegetarian.dao.customer;

import com.eeit45.champion.vegetarian.dto.customer.ReserveQueryParams;
import com.eeit45.champion.vegetarian.dto.customer.ReserveRequest;
import com.eeit45.champion.vegetarian.model.customer.Reserve;

import java.util.List;

public interface ReserveDao {

    //Get Total
    Integer getTotal(ReserveRequest reserveRequest);

    //Get 現有Reserve List (有分頁功能)
    List<Reserve> getReserves(ReserveQueryParams reserveQueryParams);

    //Get到 現有Reserve List (無分頁功能)
    List<Reserve> getAllReserve();

    //查詢單一對象，回傳使用Reserve預訂的商家(物件)
    Reserve getReserveById(Integer reserveId);

    //新創立一筆使用預訂餐廳功能的商家物件
    Integer createReserve(ReserveRequest reserveRequest);

    //更改企業狀態 '試用中' / '開通中' / '未開通'
    void updateReserve(Integer reserveId , ReserveRequest reserveRequest);

    //刪除一筆預訂功能的商家或者非客戶/ (加入黑名單功能 , 不可使用我們預訂的功能)
    void deleteReserveById(Integer reserveId);


}
