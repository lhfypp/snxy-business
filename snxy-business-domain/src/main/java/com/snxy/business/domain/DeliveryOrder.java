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

    private String receiverMobile;

    private String receiverName;

    private String receiverCompany;

    private Long receiverCompanyId;

    private Long receiverOnlineUserId;

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


}