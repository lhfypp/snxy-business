package com.snxy.business.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillInfoDetail {
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

    private Integer status;

    public List<Goods> getGoods() {
        return goods;
    }

    public void setGoods(List<Goods> goods) {
        this.goods = goods;
    }

    private List<Goods>goods;
    private List<GPSLocation>gPSLocations;
    private List<Valication>valications;
    private List<String>images;
}
