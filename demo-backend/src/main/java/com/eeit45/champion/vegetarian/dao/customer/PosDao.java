package com.eeit45.champion.vegetarian.dao.customer;

import com.eeit45.champion.vegetarian.dto.customer.PosRequest;
import com.eeit45.champion.vegetarian.model.customer.Pos;
import com.eeit45.champion.vegetarian.model.customer.PosBusiness;

import java.text.ParseException;
import java.util.List;

public interface PosDao {
    Pos getPosById(Integer posId);

    Integer buildPos(Integer businessId, PosRequest posRequest);

    List<Pos> getAllPosList();

    List<PosBusiness> getPosBusinessByPosId(Integer posId);

    void buildPosBusiness(Integer posId, Integer businessId);

    Pos getPosByBusinessId(Integer businessId);

    List<Pos> getStautsPos(String businessStatus);
}
