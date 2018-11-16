package com.snxy.business.domain;

import java.util.Date;

public class DeliveryOrderBak {
    private Long id;

    private Long deliveryOrderId;

    private String driverPlateNumber;

    private Integer loadStatus;

    private Integer locationCertificate;

    private Integer qualityCertificate;

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

    public Long getDeliveryOrderId() {
        return deliveryOrderId;
    }

    public void setDeliveryOrderId(Long deliveryOrderId) {
        this.deliveryOrderId = deliveryOrderId;
    }

    public String getDriverPlateNumber() {
        return driverPlateNumber;
    }

    public void setDriverPlateNumber(String driverPlateNumber) {
        this.driverPlateNumber = driverPlateNumber;
    }

    public Integer getLoadStatus() {
        return loadStatus;
    }

    public void setLoadStatus(Integer loadStatus) {
        this.loadStatus = loadStatus;
    }

    public Integer getLocationCertificate() {
        return locationCertificate;
    }

    public void setLocationCertificate(Integer locationCertificate) {
        this.locationCertificate = locationCertificate;
    }

    public Integer getQualityCertificate() {
        return qualityCertificate;
    }

    public void setQualityCertificate(Integer qualityCertificate) {
        this.qualityCertificate = qualityCertificate;
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