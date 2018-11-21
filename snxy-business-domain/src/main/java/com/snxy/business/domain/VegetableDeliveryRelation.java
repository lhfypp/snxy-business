package com.snxy.business.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VegetableDeliveryRelation {
    private Long id;

    private Long deliveryOrderId;

    private Long vegetableId;

    private String vegetableName;

    private Integer loadStatus;

    private Date gmtCreate;

    private Date gmtModified;

    private Byte isDelete;

    private BigDecimal vegetablePrice;

    private BigDecimal vegetableWeight;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDeliveryOrderId() {
        return deliveryOrderId;
    }

    public void setDeliveryOrderId(Long deliveryOrderId) {
        this.deliveryOrderId = deliveryOrderId;
    }

    public Long getVegetableId() {
        return vegetableId;
    }

    public void setVegetableId(Long vegetableId) {
        this.vegetableId = vegetableId;
    }

    public String getVegetableName() {
        return vegetableName;
    }

    public void setVegetableName(String vegetableName) {
        this.vegetableName = vegetableName;
    }

    public Integer getLoadStatus() {
        return loadStatus;
    }

    public void setLoadStatus(Integer loadStatus) {
        this.loadStatus = loadStatus;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }

    public BigDecimal getVegetablePrice() {
        return vegetablePrice;
    }

    public void setVegetablePrice(BigDecimal vegetablePrice) {
        this.vegetablePrice = vegetablePrice;
    }

    public BigDecimal getVegetableWeight() {
        return vegetableWeight;
    }

    public void setVegetableWeight(BigDecimal vegetableWeight) {
        this.vegetableWeight = vegetableWeight;
    }
}