package com.eeit45.champion.vegetarian.controller.customer;

import com.eeit45.champion.vegetarian.model.customer.Business;
import com.eeit45.champion.vegetarian.model.customer.Pos;
import com.eeit45.champion.vegetarian.service.customer.BusinessService;
import com.eeit45.champion.vegetarian.service.customer.PosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PosController {

    @Autowired
    private PosService posService;

    @Autowired
    private  BusinessService businessService;

    @GetMapping("{businessId}/pos")
    public ResponseEntity<Pos> getPos (
            @PathVariable Integer businessId){

//        List<Business> businessList = businessService

        return  null;
    }

}
