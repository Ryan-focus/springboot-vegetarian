package com.eeit45.champion.vegetarian.controller.customer;

import com.eeit45.champion.vegetarian.dto.customer.PosRequest;
import com.eeit45.champion.vegetarian.model.customer.Pos;
import com.eeit45.champion.vegetarian.service.customer.BusinessService;
import com.eeit45.champion.vegetarian.service.customer.PosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class PosController {

    @Autowired
    private PosService posService;

    @Autowired
    private  BusinessService businessService;


    @PostMapping ("{businessId}/pos")
    public ResponseEntity<?> buildPos(@PathVariable Integer businessId,
                                      @RequestBody @Valid PosRequest posRequest){

        Integer posId = posService.buildPos(businessId , posRequest);

        Pos pos = posService.getPosById(posId);

        return ResponseEntity.status(HttpStatus.CREATED).body(pos);
    }

}
