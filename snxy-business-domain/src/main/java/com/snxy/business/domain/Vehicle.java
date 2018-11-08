package com.snxy.business.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Vehicle {
    private Long id;

    private Long driverInfoId;

    private String plateNumber;

    private Long entranceFeeCapacityId;

    private String vehicleDrivingLicenseNumber;

    private String vehicleDrivingLicenseFrontUrl;

    private String vehicleDrivingLicenseBackUrl;

    private String tonnage;

    private Date gmtCreate;

    private Date gmtModified;

    private Long onlineUserId;

    private Byte isDelete;

    private String carryingCapacity;

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

    public Long getEntranceFeeCapacityId() {
        return entranceFeeCapacityId;
    }

    public void setEntranceFeeCapacityId(Long entranceFeeCapacityId) {
        this.entranceFeeCapacityId = entranceFeeCapacityId;
    }

    public String getVehicleDrivingLicenseNumber() {
        return vehicleDrivingLicenseNumber;
    }

    public void setVehicleDrivingLicenseNumber(String vehicleDrivingLicenseNumber) {
        this.vehicleDrivingLicenseNumber = vehicleDrivingLicenseNumber;
    }

    public String getVehicleDrivingLicenseFrontUrl() {
        return vehicleDrivingLicenseFrontUrl;
    }

    public void setVehicleDrivingLicenseFrontUrl(String vehicleDrivingLicenseFrontUrl) {
        this.vehicleDrivingLicenseFrontUrl = vehicleDrivingLicenseFrontUrl;
    }

    public String getVehicleDrivingLicenseBackUrl() {
        return vehicleDrivingLicenseBackUrl;
    }

    public void setVehicleDrivingLicenseBackUrl(String vehicleDrivingLicenseBackUrl) {
        this.vehicleDrivingLicenseBackUrl = vehicleDrivingLicenseBackUrl;
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

    public String getCarryingCapacity() {
        return carryingCapacity;
    }

    public void setCarryingCapacity(String carryingCapacity) {
        this.carryingCapacity = carryingCapacity;
    }
}