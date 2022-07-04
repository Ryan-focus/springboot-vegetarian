package com.eeit45.champion.vegetarian.controller.customer;


import com.eeit45.champion.vegetarian.dto.customer.ReserveRequest;
import com.eeit45.champion.vegetarian.model.customer.Reserve;
import com.eeit45.champion.vegetarian.service.customer.ReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RestController
public class ReserveController {

    @Autowired
    private ReserveService reserveService;

//    @GetMapping("/reserves")
//    public ResponseEntity<Reserve> getReserveList(
//
//    ){
//
//        return ResponseEntity.status(HttpStatus.OK).body(1);
//    }


    @GetMapping("/reserves/{reserveId}")
    public ResponseEntity<Reserve> getProduct(@PathVariable Integer reserveId){
        Reserve reserve = reserveService.getReserveById(reserveId);

        if(reserve != null){
            return ResponseEntity.status(HttpStatus.OK).body(reserve);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/reserves")
    public ResponseEntity<Reserve> newReserve(@RequestBody @Valid ReserveRequest reserveRequest){
        Integer reserveId = reserveService.createReserve(reserveRequest);

        Reserve reserve = reserveService.getReserveById(reserveId);
        return ResponseEntity.status(HttpStatus.CREATED).body(reserve);
    }

}
