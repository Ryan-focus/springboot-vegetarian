package com.eeit45.champion.vegetarian.dao.customer;

import com.eeit45.champion.vegetarian.dto.customer.PosQueryParams;
import com.eeit45.champion.vegetarian.dto.customer.PosRequest;
import com.eeit45.champion.vegetarian.model.customer.Pos;
import com.eeit45.champion.vegetarian.model.customer.PosBusiness;

import java.util.List;

public interface PosDao {
    Pos getPosById(Integer posId);

    Integer buildPos(Integer businessId, PosRequest posRequest);

    List<Pos> getAllPosList(PosQueryParams posQueryParams);

    List<PosBusiness> getPosBusinessByPosId(Integer posId);

    void buildPosBusiness(Integer posId, Integer businessId);

    Pos getPosByBusinessId(Integer businessId);

    Integer totalPos(PosQueryParams posQueryParams);
}
