package com.eeit45.champion.vegetarian.dao;

import com.eeit45.champion.vegetarian.dto.ReserveRequest;
import com.eeit45.champion.vegetarian.model.Reserve;

public interface ReserveDao {

    Reserve getReserveById(Integer reserveId);

    Integer createReserve(ReserveRequest reserveRequest);
}
