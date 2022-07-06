package com.eeit45.champion.vegetarian.dao.customer;

import com.eeit45.champion.vegetarian.dto.customer.PosRequest;
import com.eeit45.champion.vegetarian.model.customer.Pos;

import java.util.List;

public interface PosDao {
    Pos getPosById(Integer posId);

    Integer buildPos(Integer businessId, PosRequest posRequest);

    List<Pos> getAllPosList();
}
