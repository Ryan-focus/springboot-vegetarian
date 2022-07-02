package com.eeit45.champion.vegetarian.dao.customer;

import com.eeit45.champion.vegetarian.dto.customer.ReserveRequest;
import com.eeit45.champion.vegetarian.model.customer.Reserve;

public interface ReserveDao {

    Reserve getReserveById(Integer reserveId);

    Integer createReserve(ReserveRequest reserveRequest);
}
