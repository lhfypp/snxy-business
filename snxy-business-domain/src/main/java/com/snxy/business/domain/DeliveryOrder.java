package com.snxy.business.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryOrder {
    private Long id;

    private String orderNo;

    private Long creatorId;

    private String creator;

    private String senderName;

    private String senderMobile;

    private Long receiverOnlineUserId;

    private String receiverMobile;

    private String receiverName;

    private String receiverCompany;

    private Long receiverCompanyId;


    private Long driverOnlineUserId;

    private String driverName;

    private String driverMobile;

    private Long vehicleId;

    private String driverPlateNumber;

    private Date driverLeaveTime;

    private Date sendTime;

    private String startAddr;

    private String endAddr;

    private Float distance;

    private Date estArrivalTime;

    private Date actualArrivalTime;

    private Integer loadStatus;

    private Integer locationCertificate;

    private Integer qualityCertificate;

    private String qrcodeUrl;

    private Integer isAuthPay;

    private Integer infoIntegrity;

    private Integer status;
    //取消0，已接单1，装货完成2，运输中3，检测中4，合格关闭5，不合格关闭6 -- 被遣离

    private Date gmtCreate;

    private Date gmtModified;

    private Byte isDelete;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderMobile() {
        return senderMobile;
    }

    public void setSenderMobile(String senderMobile) {
        this.senderMobile = senderMobile;
    }

    public Long getReceiverOnlineUserId() {
        return receiverOnlineUserId;
    }

    public void setReceiverOnlineUserId(Long receiverOnlineUserId) {
        this.receiverOnlineUserId = receiverOnlineUserId;
    }

    public String getReceiverMobile() {
        return receiverMobile;
    }

    public void setReceiverMobile(String receiverMobile) {
        this.receiverMobile = receiverMobile;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverCompany() {
        return receiverCompany;
    }

    public void setReceiverCompany(String receiverCompany) {
        this.receiverCompany = receiverCompany;
    }

    public Long getDriverOnlineUserId() {
        return driverOnlineUserId;
    }

    public void setDriverOnlineUserId(Long driverOnlineUserId) {
        this.driverOnlineUserId = driverOnlineUserId;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverMobile() {
        return driverMobile;
    }

    public void setDriverMobile(String driverMobile) {
        this.driverMobile = driverMobile;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getDriverPlateNumber() {
        return driverPlateNumber;
    }

    public void setDriverPlateNumber(String driverPlateNumber) {
        this.driverPlateNumber = driverPlateNumber;
    }

    public Date getDriverLeaveTime() {
        return driverLeaveTime;
    }

    public void setDriverLeaveTime(Date driverLeaveTime) {
        this.driverLeaveTime = driverLeaveTime;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getStartAddr() {
        return startAddr;
    }

    public void setStartAddr(String startAddr) {
        this.startAddr = startAddr;
    }

    public String getEndAddr() {
        return endAddr;
    }

    public void setEndAddr(String endAddr) {
        this.endAddr = endAddr;
    }

    public Float getDistance() {
        return distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }

    public Date getEstArrivalTime() {
        return estArrivalTime;
    }

    public void setEstArrivalTime(Date estArrivalTime) {
        this.estArrivalTime = estArrivalTime;
    }

    public Date getActualArrivalTime() {
        return actualArrivalTime;
    }

    public void setActualArrivalTime(Date actualArrivalTime) {
        this.actualArrivalTime = actualArrivalTime;
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

    public String getQrcodeUrl() {
        return qrcodeUrl;
    }

    public void setQrcodeUrl(String qrcodeUrl) {
        this.qrcodeUrl = qrcodeUrl;
    }

    public Integer getIsAuthPay() {
        return isAuthPay;
    }

    public void setIsAuthPay(Integer isAuthPay) {
        this.isAuthPay = isAuthPay;
    }

    public Integer getInfoIntegrity() {
        return infoIntegrity;
    }

    public void setInfoIntegrity(Integer infoIntegrity) {
        this.infoIntegrity = infoIntegrity;
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

    public Long getReceiverCompanyId() {
        return receiverCompanyId;
    }

    public void setReceiverCompanyId(Long receiverCompanyId) {
        this.receiverCompanyId = receiverCompanyId;
    }



}