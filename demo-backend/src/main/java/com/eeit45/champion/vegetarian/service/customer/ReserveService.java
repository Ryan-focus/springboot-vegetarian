package com.eeit45.champion.vegetarian.service.customer;


import com.eeit45.champion.vegetarian.dto.customer.ReserveRequest;
import com.eeit45.champion.vegetarian.model.customer.Reserve;

import java.util.List;

public interface ReserveService {

    Reserve getReserveById(Integer businessId,Integer reserveId);

    Integer createReserve(Integer businessId, ReserveRequest reserveRequest);

    List<Reserve> getAllReserve(Integer business);
}
