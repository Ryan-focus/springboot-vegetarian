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
import java.util.List;

@RestController
public class PosController {

    @Autowired
    private PosService posService;

    @Autowired
    private  BusinessService businessService;


    //寫一個方法 ，預期返回一個total值 與所有POS狀態的List
    @GetMapping("/pos/{businessStatus}")
    public ResponseEntity<List<Pos>> getPosStatus(@PathVariable String businessStatus){
        List<Pos> posList  = posService.getStatusPosList(businessStatus);

        if(posList != null ) return ResponseEntity.status(HttpStatus.OK).body(posList);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


    @GetMapping("/pos")
    public ResponseEntity<List<Pos>> getAllPosList(){
        List<Pos> posList = posService.getAllPosList();

        if(posList != null){
            return ResponseEntity.status(HttpStatus.OK).body(posList);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping ("/business/{businessId}/pos")
    public ResponseEntity<Pos> buildPos(@PathVariable Integer businessId,
                                      @RequestBody @Valid PosRequest posRequest){

        Integer posId = posService.buildPos(businessId , posRequest);

        Pos pos = posService.getPosById(posId);

        return ResponseEntity.status(HttpStatus.CREATED).body(pos);
    }

}
