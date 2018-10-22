package com.snxy.business.domain;

import java.math.BigDecimal;
import java.util.Date;

public class DeliveryOrder {
    private Long id;

    private String orderNo;

    private String senderName;

    private String senderMobile;

    private String receiverMobile;

    private String receiverName;

    private String receiverCompany;

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

    private Date gmtCreate;

    private Date gmtModified;

    private BigDecimal deliveryFee;

    private String qrcodeUrl;

    //待接单0，取消1，已接单2，装货完成3，运输中4，检测中5，合格关闭6，不合格关闭7 -- 被遣离8
    private Integer status;

    private Byte isDelete;

    private Integer truckTypeId;

    public Integer getTruckTypeId() {
        return truckTypeId;
    }

    public void setTruckTypeId(Integer truckTypeId) {
        this.truckTypeId = truckTypeId;
    }

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

    public BigDecimal getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(BigDecimal deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public String getQrcodeUrl() {
        return qrcodeUrl;
    }

    public void setQrcodeUrl(String qrcodeUrl) {
        this.qrcodeUrl = qrcodeUrl;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }
}