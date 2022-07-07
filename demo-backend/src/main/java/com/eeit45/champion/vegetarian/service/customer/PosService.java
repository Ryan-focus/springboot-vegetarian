package com.eeit45.champion.vegetarian.service.customer;

import com.eeit45.champion.vegetarian.dto.customer.PosQueryParams;
import com.eeit45.champion.vegetarian.dto.customer.PosRequest;
import com.eeit45.champion.vegetarian.model.customer.Pos;

import java.util.List;

public interface PosService {
    Integer buildPos(Integer businessId, PosRequest posRequest);

    Pos getPosById(Integer posId);

    List<Pos> getAllPosList(PosQueryParams posQueryParams);

    Integer totalPos(PosQueryParams posQueryParams);
}
