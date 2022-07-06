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
import java.util.List;

@Validated
@RestController
public class ReserveController {

    @Autowired
    private ReserveService reserveService;

    @GetMapping("/{businessId}/reserves/{reserveId}")
    public ResponseEntity<Reserve> getProductById(@PathVariable Integer businessId,
                                                  @PathVariable Integer reserveId){
        Reserve reserve = reserveService.getReserveById(businessId,reserveId);

        if(reserve != null){
            return ResponseEntity.status(HttpStatus.OK).body(reserve);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{businessId}/reserves")
    public ResponseEntity<List<Reserve>> getProductById(@PathVariable Integer businessId){
        List<Reserve> reserveList = reserveService.getAllReserve(businessId);

        if(reserveList != null){
            return ResponseEntity.status(HttpStatus.OK).body(reserveList);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    //預訂餐廳功能依附在Business(合作商家)底下
    @PostMapping("/{businessId}/reserves")
    public ResponseEntity<Reserve> newReserve(@PathVariable Integer businessId,
                                              @RequestBody @Valid ReserveRequest reserveRequest){
        if(reserveRequest.getChild() == null) reserveRequest.setChild(0);
        if(reserveRequest.getBaby() == null) reserveRequest.setBaby(0);

        Integer reserveId = reserveService.createReserve(businessId,reserveRequest);

        Reserve reserve = reserveService.getReserveById(businessId,reserveId);
        return ResponseEntity.status(HttpStatus.CREATED).body(reserve);
    }

}
