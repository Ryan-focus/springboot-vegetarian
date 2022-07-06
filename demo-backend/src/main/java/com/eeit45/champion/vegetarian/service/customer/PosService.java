package com.eeit45.champion.vegetarian.service.customer;

import com.eeit45.champion.vegetarian.dto.customer.PosRequest;
import com.eeit45.champion.vegetarian.model.customer.Pos;

public interface PosService {
    Integer buildPos(Integer businessId, PosRequest posRequest);

    Pos getPosById(Integer posId);
}
