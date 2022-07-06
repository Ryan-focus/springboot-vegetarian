package com.eeit45.champion.vegetarian.service.customer.impl;

import com.eeit45.champion.vegetarian.dao.customer.ReserveDao;
import com.eeit45.champion.vegetarian.dto.customer.ReserveRequest;
import com.eeit45.champion.vegetarian.model.customer.Reserve;
import com.eeit45.champion.vegetarian.service.customer.ReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReserveServiceImpl implements ReserveService {

    @Autowired
    private ReserveDao reserveDao;

    @Override
    public Reserve getReserveById(Integer businessId , Integer reserveId) {
        return reserveDao.getReserveById(businessId,reserveId);
    }

    @Override
    public Integer createReserve(Integer businessId ,ReserveRequest reserveRequest) {
        return reserveDao.createReserve(businessId,reserveRequest);
    }

    @Override
    public List<Reserve> getAllReserve(Integer businessId) {
        return reserveDao.getAllReserve(businessId);
    }
}
