package com.snxy.business.domain;

import java.math.BigDecimal;
import java.util.Date;

public class VegetablePriceHistory {
    private Long id;

    private Long vegetablePriceId;

    private String code;

    private String vegetableName;

    private Long vegetableCategoryId;

    private BigDecimal price;

    private Date effectiveStartingTime;

    private Date effectiveEndTime;

    private Byte isDelete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVegetablePriceId() {
        return vegetablePriceId;
    }

    public void setVegetablePriceId(Long vegetablePriceId) {
        this.vegetablePriceId = vegetablePriceId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getVegetableName() {
        return vegetableName;
    }

    public void setVegetableName(String vegetableName) {
        this.vegetableName = vegetableName;
    }

    public Long getVegetableCategoryId() {
        return vegetableCategoryId;
    }

    public void setVegetableCategoryId(Long vegetableCategoryId) {
        this.vegetableCategoryId = vegetableCategoryId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getEffectiveStartingTime() {
        return effectiveStartingTime;
    }

    public void setEffectiveStartingTime(Date effectiveStartingTime) {
        this.effectiveStartingTime = effectiveStartingTime;
    }

    public Date getEffectiveEndTime() {
        return effectiveEndTime;
    }

    public void setEffectiveEndTime(Date effectiveEndTime) {
        this.effectiveEndTime = effectiveEndTime;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }
}