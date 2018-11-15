package com.snxy.business.domain;

import java.math.BigDecimal;
import java.util.Date;

public class EntranceFeeDetail {
    private Long id;

    private Long entranceFeeCategoryId;

    private Long entranceFeeCapacityId;

    private BigDecimal cost;

    private Date gmtCreate;

    private Date gmtModified;

    private Byte isDelete;

    private Long status;

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEntranceFeeCategoryId() {
        return entranceFeeCategoryId;
    }

    public void setEntranceFeeCategoryId(Long entranceFeeCategoryId) {
        this.entranceFeeCategoryId = entranceFeeCategoryId;
    }

    public Long getEntranceFeeCapacityId() {
        return entranceFeeCapacityId;
    }

    public void setEntranceFeeCapacityId(Long entranceFeeCapacityId) {
        this.entranceFeeCapacityId = entranceFeeCapacityId;
    }

    public BigDecimal getVegetableName() {
        return cost;
    }

    public void setVegetableName(BigDecimal vegetableName) {
        this.cost = vegetableName;
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
}