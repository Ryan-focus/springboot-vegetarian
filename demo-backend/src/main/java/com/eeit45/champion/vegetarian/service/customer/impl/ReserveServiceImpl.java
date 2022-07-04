package com.eeit45.champion.vegetarian.service.customer.impl;

import com.eeit45.champion.vegetarian.dao.customer.ReserveDao;
import com.eeit45.champion.vegetarian.dto.customer.ReserveRequest;
import com.eeit45.champion.vegetarian.model.customer.Reserve;
import com.eeit45.champion.vegetarian.service.customer.ReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReserveServiceImpl implements ReserveService {

    @Autowired
    private ReserveDao reserveDao;

    @Override
    public Reserve getReserveById(Integer reserveId) {
        return reserveDao.getReserveById(reserveId);
    }

    @Override
    public Integer createReserve(ReserveRequest reserveRequest) {
        return reserveDao.createReserve(reserveRequest);
    }
}
