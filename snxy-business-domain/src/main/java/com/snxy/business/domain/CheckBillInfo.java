package com.snxy.business.domain;

import javax.xml.crypto.Data;

public class CheckBillInfo {

    private String deliveryOrderId;

    private String proposeTime;

    private String vegetableCategoryName;

    private Integer  weight;

    private String checkStatus;

    private String checkResult;

    private String productionLocation;

    private String url;

    private String qrcodeUrl;

    private Data checkTime;

    private long id;

    private int qualified;

    public String getDeliveryOrderId() {
        return deliveryOrderId;
    }

    public void setDeliveryOrderId(String deliveryOrderId) {
        this.deliveryOrderId = deliveryOrderId;
    }

    public String getProposeTime() {
        return proposeTime;
    }

    public void setProposeTime(String proposeTime) {
        this.proposeTime = proposeTime;
    }

    public String getVegetableCategoryName() {
        return vegetableCategoryName;
    }

    public void setVegetableCategoryName(String vegetableCategoryName) {
        this.vegetableCategoryName = vegetableCategoryName;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus;
    }

    public String getCheckResult() {
        return checkResult;
    }

    public void setCheckResult(String checkResult) {
        this.checkResult = checkResult;
    }

    public String getProductionLocation() {
        return productionLocation;
    }

    public void setProductionLocation(String productionLocation) {
        this.productionLocation = productionLocation;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getQrcodeUrl() {
        return qrcodeUrl;
    }

    public void setQrcodeUrl(String qrcodeUrl) {
        this.qrcodeUrl = qrcodeUrl;
    }

    public Data getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Data checkTime) {
        this.checkTime = checkTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getQualified() {
        return qualified;
    }

    public void setQualified(int qualified) {
        this.qualified = qualified;
    }
}
