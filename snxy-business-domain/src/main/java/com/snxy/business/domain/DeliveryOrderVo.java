package com.snxy.business.domain;

import java.math.BigDecimal;
import java.util.Date;

public class DeliveryOrderVo {
    //订单号
    private String orderNo;
    //发货人姓名
    private String sellerName;
    //发货人电话
    private String sellerMobile;
    //发货地址
    private String sendAddr;
    //收货人姓名
    private String buyerName;
    //收货人电话
    private String buyerMobile;
    //收货人公司
    private String buyerCompany;
    //收货地址
    private String buyAddr;
    //货品id
    private Long goodsId;
    //货品名称
    private String goodsName;
    //货品照片上传时间
    private Date GoodsImgUploadTime;
    //货品重量
    private Integer goodsWeight;
    //货品价格
    private BigDecimal goodsPrice;
    //货车类型
    private Integer truckTypeId;
    //运费
    private BigDecimal price;
    //司机姓名
    private String driverName;
    //联系电话
    private String driverMobile;
    //产地证明
    private String locationCertificate;
    //产地证明上传时间
    private Date locationCertificateUploadTime;
    //检测证明
    private String qualityCertificate;
    //检测证明上传时间
    private Date qualityCertificateUploadTime;
    //货物照片
    private String goodsImg;
    //距离
    private float distance;
    //发货时间
    private Date sendTime;
    //预计到达时间
    private Date estArrivalTime;

    public Date getGoodsImgUploadTime() {
        return GoodsImgUploadTime;
    }

    public void setGoodsImgUploadTime(Date goodsImgUploadTime) {
        GoodsImgUploadTime = goodsImgUploadTime;
    }

    public Date getLocationCertificateUploadTime() {
        return locationCertificateUploadTime;
    }

    public void setLocationCertificateUploadTime(Date locationCertificateUploadTime) {
        this.locationCertificateUploadTime = locationCertificateUploadTime;
    }

    public Date getQualityCertificateUploadTime() {
        return qualityCertificateUploadTime;
    }

    public void setQualityCertificateUploadTime(Date qualityCertificateUploadTime) {
        this.qualityCertificateUploadTime = qualityCertificateUploadTime;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getSellerMobile() {
        return sellerMobile;
    }

    public void setSellerMobile(String sellerMobile) {
        this.sellerMobile = sellerMobile;
    }

    public String getSendAddr() {
        return sendAddr;
    }

    public void setSendAddr(String sendAddr) {
        this.sendAddr = sendAddr;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerMobile() {
        return buyerMobile;
    }

    public void setBuyerMobile(String buyerMobile) {
        this.buyerMobile = buyerMobile;
    }

    public String getBuyerCompany() {
        return buyerCompany;
    }

    public void setBuyerCompany(String buyerCompany) {
        this.buyerCompany = buyerCompany;
    }

    public String getBuyAddr() {
        return buyAddr;
    }

    public void setBuyAddr(String buyAddr) {
        this.buyAddr = buyAddr;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getGoodsWeight() {
        return goodsWeight;
    }

    public void setGoodsWeight(Integer goodsWeight) {
        this.goodsWeight = goodsWeight;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Integer getTruckTypeId() {
        return truckTypeId;
    }

    public void setTruckTypeId(Integer truckTypeId) {
        this.truckTypeId = truckTypeId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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

    public String getLocationCertificate() {
        return locationCertificate;
    }

    public void setLocationCertificate(String locationCertificate) {
        this.locationCertificate = locationCertificate;
    }

    public String getQualityCertificate() {
        return qualityCertificate;
    }

    public void setQualityCertificate(String qualityCertificate) {
        this.qualityCertificate = qualityCertificate;
    }

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
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
}
