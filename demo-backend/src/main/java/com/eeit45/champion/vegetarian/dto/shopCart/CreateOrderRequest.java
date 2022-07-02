package com.eeit45.champion.vegetarian.dto.shopCart;

import com.eeit45.champion.vegetarian.dto.shopCart.BuyItem;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public class CreateOrderRequest {

    //創建一個參數對應前端送的資料
    //NotEmpty 通常加在 Collection 或者 List上
    @NotEmpty
    private List<BuyItem> buyItemList;

    public List<BuyItem> getBuyItemList() {
        return buyItemList;
    }

    public void setBuyItemList(List<BuyItem> buyItemList) {
        this.buyItemList = buyItemList;
    }
}
