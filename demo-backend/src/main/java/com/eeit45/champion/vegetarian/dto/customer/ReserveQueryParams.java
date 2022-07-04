package com.eeit45.champion.vegetarian.dto.customer;

import com.eeit45.champion.vegetarian.constant.ProductCategory;
import com.eeit45.champion.vegetarian.constant.VeganCategory;

public class ReserveQueryParams {

    private String search;
    private String orderBy;
    private String sorting;
    private Integer limit;
    private Integer offset;


    public ReserveQueryParams() {
    }

    public ReserveQueryParams(String search, String orderBy, String sorting, Integer limit, Integer offset) {
        this.search = search;
        this.orderBy = orderBy;
        this.sorting = sorting;
        this.limit = limit;
        this.offset = offset;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getSorting() {
        return sorting;
    }

    public void setSorting(String sorting) {
        this.sorting = sorting;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }
}
