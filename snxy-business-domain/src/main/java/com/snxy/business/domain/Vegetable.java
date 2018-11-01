package com.snxy.business.domain;

public class Vegetable {

    private Long vegetableCategoryId;

    private String vegetableCategoryName;

    private String price;

    private String date;

    public Long getVegetableCategoryId() {
        return vegetableCategoryId;
    }

    public void setVegetableCategoryId(Long vegetableCategoryId) {
        this.vegetableCategoryId = vegetableCategoryId;
    }

    public String getVegetableCategoryName() {
        return vegetableCategoryName;
    }

    public void setVegetableCategoryName(String vegetableCategoryName) {
        this.vegetableCategoryName = vegetableCategoryName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
