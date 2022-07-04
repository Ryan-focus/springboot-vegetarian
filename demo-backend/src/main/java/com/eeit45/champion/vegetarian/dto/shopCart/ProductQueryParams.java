package com.eeit45.champion.vegetarian.dto.shopCart;

import com.eeit45.champion.vegetarian.constant.ProductCategory;
import com.eeit45.champion.vegetarian.constant.VeganCategory;

public class ProductQueryParams {

    private VeganCategory veganCategory;
    private ProductCategory category;
    private String search;
    private String orderBy;
    private String sorting;
    private Integer limit;
    private Integer offset;


    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public VeganCategory getVeganCategory() {
        return veganCategory;
    }

    public void setVeganCategory(VeganCategory veganCategory) {
        this.veganCategory = veganCategory;
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
