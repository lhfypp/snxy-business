package com.snxy.business.domain;

import java.util.Date;

public class Vehicle {
    private Long id;

    private Long driverInfoId;

    private String plateNumber;

    private Long truckTypeId;

    private String vehicleDrivingLicenseNumber;

    private String vehicleDrivingLicenseUrl;

    private String vehicleUrl;

    private String operationCertificate;

    private String tonnage;

    private Date gmtCreate;

    private Date gmtModified;

    private Long onlineUserId;

    private Byte isDelete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDriverInfoId() {
        return driverInfoId;
    }

    public void setDriverInfoId(Long driverInfoId) {
        this.driverInfoId = driverInfoId;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public Long getTruckTypeId() {
        return truckTypeId;
    }

    public void setTruckTypeId(Long truckTypeId) {
        this.truckTypeId = truckTypeId;
    }

    public String getVehicleDrivingLicenseNumber() {
        return vehicleDrivingLicenseNumber;
    }

    public void setVehicleDrivingLicenseNumber(String vehicleDrivingLicenseNumber) {
        this.vehicleDrivingLicenseNumber = vehicleDrivingLicenseNumber;
    }

    public String getVehicleDrivingLicenseUrl() {
        return vehicleDrivingLicenseUrl;
    }

    public void setVehicleDrivingLicenseUrl(String vehicleDrivingLicenseUrl) {
        this.vehicleDrivingLicenseUrl = vehicleDrivingLicenseUrl;
    }

    public String getVehicleUrl() {
        return vehicleUrl;
    }

    public void setVehicleUrl(String vehicleUrl) {
        this.vehicleUrl = vehicleUrl;
    }

    public String getOperationCertificate() {
        return operationCertificate;
    }

    public void setOperationCertificate(String operationCertificate) {
        this.operationCertificate = operationCertificate;
    }

    public String getTonnage() {
        return tonnage;
    }

    public void setTonnage(String tonnage) {
        this.tonnage = tonnage;
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

    public Long getOnlineUserId() {
        return onlineUserId;
    }

    public void setOnlineUserId(Long onlineUserId) {
        this.onlineUserId = onlineUserId;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }
}