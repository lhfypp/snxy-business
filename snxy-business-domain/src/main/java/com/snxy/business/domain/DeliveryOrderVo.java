package com.snxy.business.domain;

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
    private Integer goodsId;
    //货品重量
    private String goodsWeight;
    //货品价格
    private String goodsPrice;
    //货车类型
    private Integer truckType;
    //运费
    private String price;
    //司机姓名
    private String driverName;
    //联系电话
    private String driverMobile;
    //产地证明
    private String locationCertificate;
    //检测证明
    private String qualityCertificate;
    //货物照片
    private String goodsImg;

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

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsWeight() {
        return goodsWeight;
    }

    public void setGoodsWeight(String goodsWeight) {
        this.goodsWeight = goodsWeight;
    }

    public String getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Integer getTruckType() {
        return truckType;
    }

    public void setTruckType(Integer truckType) {
        this.truckType = truckType;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
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

    public String getBuyAddr() {
        return buyAddr;
    }

    public void setBuyAddr(String buyAddr) {
        this.buyAddr = buyAddr;
    }
}
