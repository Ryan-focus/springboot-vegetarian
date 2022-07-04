package com.eeit45.champion.vegetarian.service.customer;


import com.eeit45.champion.vegetarian.dto.customer.ReserveRequest;
import com.eeit45.champion.vegetarian.model.customer.Reserve;

public interface ReserveService {

    Reserve getReserveById(Integer reserveId);

    Integer createReserve(ReserveRequest reserveRequest);
}
