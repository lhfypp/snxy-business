package com.snxy.business.service.vo;

import com.snxy.business.domain.Goods;
import com.snxy.business.domain.VegetableCertificate;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class OrderVo {
    //订单号
    private String orderNo;
    //发货人姓名
    private String senderName;
    //发货人电话
    private String senderMobile;
    //发货地址
    private String startAddr;
    //收货人姓名
    private String receiverName;
    //收货人电话
    private String receiverMobile;
    //收货人公司
    private String receiverCompany;
    //收货地址
    private String endAddr;
    //货品
    private List<Goods> goodsList;
    //货车类型
    private Integer truckTypeId;
    //运费
    private BigDecimal deliveryFee;
    //司机姓名
    private String driverName;
    //联系电话
    private String driverMobile;
    //距离
    private float distance;
    //发货时间
    private Date sendTime;
    //预计到达时间
    private Date estArrivalTime;
    //货物图片地址集合
    private List<String> urlList;
    //产地证明,检测证明图片
    private List<VegetableCertificate> vegetableCertificateList;

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

    public String getStartAddr() {
        return startAddr;
    }

    public void setStartAddr(String startAddr) {
        this.startAddr = startAddr;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverMobile() {
        return receiverMobile;
    }

    public void setReceiverMobile(String receiverMobile) {
        this.receiverMobile = receiverMobile;
    }

    public String getReceiverCompany() {
        return receiverCompany;
    }

    public void setReceiverCompany(String receiverCompany) {
        this.receiverCompany = receiverCompany;
    }

    public String getEndAddr() {
        return endAddr;
    }

    public void setEndAddr(String endAddr) {
        this.endAddr = endAddr;
    }

    public List<Goods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }

    public Integer getTruckTypeId() {
        return truckTypeId;
    }

    public void setTruckTypeId(Integer truckTypeId) {
        this.truckTypeId = truckTypeId;
    }

    public BigDecimal getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(BigDecimal deliveryFee) {
        this.deliveryFee = deliveryFee;
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

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Date getEstArrivalTime() {
        return estArrivalTime;
    }

    public void setEstArrivalTime(Date estArrivalTime) {
        this.estArrivalTime = estArrivalTime;
    }

    public List<String> getUrlList() {
        return urlList;
    }

    public void setUrlList(List<String> urlList) {
        this.urlList = urlList;
    }

    public List<VegetableCertificate> getVegetableCertificateList() {
        return vegetableCertificateList;
    }

    public void setVegetableCertificateList(List<VegetableCertificate> vegetableCertificateList) {
        this.vegetableCertificateList = vegetableCertificateList;
    }
}
