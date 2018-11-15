package com.snxy.business.domain;

import java.math.BigDecimal;
import java.util.Date;

public class OutFeeOrder {
    private Long id;

    private String outFeeNo;

    private Long entranceFeeCapacityId;

    private String driverPlateNumber;

    private BigDecimal outFee;

    private Integer loadStatus;

    private Integer status;

    private Date gmtCreate;

    private Date gmtModified;

    private Byte isDelete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOutFeeNo() {
        return outFeeNo;
    }

    public void setOutFeeNo(String outFeeNo) {
        this.outFeeNo = outFeeNo;
    }

    public Long getEntranceFeeCapacityId() {
        return entranceFeeCapacityId;
    }

    public void setEntranceFeeCapacityId(Long entranceFeeCapacityId) {
        this.entranceFeeCapacityId = entranceFeeCapacityId;
    }

    public String getDriverPlateNumber() {
        return driverPlateNumber;
    }

    public void setDriverPlateNumber(String driverPlateNumber) {
        this.driverPlateNumber = driverPlateNumber;
    }

    public BigDecimal getOutFee() {
        return outFee;
    }

    public void setOutFee(BigDecimal outFee) {
        this.outFee = outFee;
    }

    public Integer getLoadStatus() {
        return loadStatus;
    }

    public void setLoadStatus(Integer loadStatus) {
        this.loadStatus = loadStatus;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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