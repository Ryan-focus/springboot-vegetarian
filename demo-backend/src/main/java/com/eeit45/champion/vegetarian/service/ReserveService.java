package com.eeit45.champion.vegetarian.service;


import com.eeit45.champion.vegetarian.dto.ReserveRequest;
import com.eeit45.champion.vegetarian.model.Reserve;

public interface ReserveService {

    Reserve getReserveById(Integer reserveId);

    Integer createReserve(ReserveRequest reserveRequest);
}
