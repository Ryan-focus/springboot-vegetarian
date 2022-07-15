package com.eeit45.champion.vegetarian.controller.customer;

import com.eeit45.champion.vegetarian.dto.customer.PosQueryParams;
import com.eeit45.champion.vegetarian.dto.customer.PosRequest;
import com.eeit45.champion.vegetarian.constant.StatusCategory;
import com.eeit45.champion.vegetarian.dto.shopCart.ProductRequest;
import com.eeit45.champion.vegetarian.model.customer.Pos;
import com.eeit45.champion.vegetarian.model.shopCart.Product;
import com.eeit45.champion.vegetarian.service.customer.BusinessService;
import com.eeit45.champion.vegetarian.service.customer.PosService;
import com.eeit45.champion.vegetarian.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
public class PosController {

    @Autowired
    private PosService posService;

    @Autowired
    private  BusinessService businessService;

    @GetMapping("/pos")
    public ResponseEntity<Page<Pos>> getAllPosList(
            //Filtering
            @RequestParam(required = false) StatusCategory statusCategory,
            //Sorting
            @RequestParam(defaultValue = "expiryDate") String orderBy,
            @RequestParam(defaultValue = "desc") String sorting,
            //Pagination
            @RequestParam(defaultValue = "100")@Max(100) @Min(0) Integer limit,
            @RequestParam(defaultValue = "0")@Min(0) Integer offset
    ){
        PosQueryParams posQueryParams = new PosQueryParams();
        posQueryParams.setStatusCategory(statusCategory);
        posQueryParams.setOrderBy(orderBy);
        posQueryParams.setSorting(sorting);
        posQueryParams.setLimit(limit);
        posQueryParams.setOffset(offset);

        List<Pos> posList = posService.getAllPosList(posQueryParams);

        Integer total = posService.totalPos(posQueryParams);

        Page<Pos> page = new Page<>();
        page.setLimit(limit);
        page.setOffset(offset);
        page.setTotal(total);
        page.setResults(posList);

        return ResponseEntity.status(HttpStatus.OK).body(page);
    }

    @PostMapping ("/business/{businessId}/pos")
    public ResponseEntity<Pos> buildPos(@PathVariable Integer businessId,
                                      @RequestBody @Valid PosRequest posRequest){

        Integer posId = posService.buildPos(businessId , posRequest);

        Pos pos = posService.getPosById(posId);

        return ResponseEntity.status(HttpStatus.CREATED).body(pos);
    }

    @GetMapping("/pos/{posId}")
    public ResponseEntity<Pos> getPos(@PathVariable Integer posId){
        Pos pos = posService.getPosById(posId);

        if(pos != null){
            return ResponseEntity.status(HttpStatus.OK).body(pos);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/pos/business/{businessId}")
    public ResponseEntity<Pos> getPosByBusinessId(@PathVariable Integer businessId){
        Pos pos = posService.getPosByBusinessId(businessId);

        if(pos != null){
            return ResponseEntity.status(HttpStatus.OK).body(pos);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/pos/{posId}")
    public ResponseEntity<Pos> updateStatus(@PathVariable Integer posId,
                                                 @RequestBody @Valid PosRequest posRequest){
        //Check Pos 是否存在
        Pos pos = posService.getPosById(posId);
        if( pos == null ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        // 修改pos的數據
        posService.updateStatus(posId,posRequest);

        Pos afterUpdatePos = posService.getPosById(posId);

        return ResponseEntity.status(HttpStatus.OK).body(afterUpdatePos);
    }

}
