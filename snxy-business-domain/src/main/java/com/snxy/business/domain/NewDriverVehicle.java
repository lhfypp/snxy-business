package com.snxy.business.domain;

import java.util.List;

public class NewDriverVehicle {
    private Long id;//车辆id

    private String plateNumber;//车牌号

    private Integer carTypeDesc;//车类型

    private String drivingLicenseNumber;//行驶证编号

    private String operationCertificate;//运营证

    private Float tonnage;//吨位

    private String vehicleDrivingLicenseUrl;//行驶证图片

    private String vehicleUrl;//车辆图片

    public String getPlateNumber() {
        return plateNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public Integer getCarTypeDesc() {
        return carTypeDesc;
    }

    public void setCarTypeDesc(Integer carTypeDesc) {
        this.carTypeDesc = carTypeDesc;
    }

    public String getDrivingLicenseNumber() {
        return drivingLicenseNumber;
    }

    public void setDrivingLicenseNumber(String drivingLicenseNumber) {
        this.drivingLicenseNumber = drivingLicenseNumber;
    }

    public String getOperationCertificate() {
        return operationCertificate;
    }

    public void setOperationCertificate(String operationCertificate) {
        this.operationCertificate = operationCertificate;
    }

    public Float getTonnage() {
        return tonnage;
    }

    public void setTonnage(Float tonnage) {
        this.tonnage = tonnage;
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
}
