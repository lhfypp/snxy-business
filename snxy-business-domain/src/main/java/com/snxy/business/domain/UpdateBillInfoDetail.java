package com.snxy.business.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBillInfoDetail {

    private  long deliveryOrderId;
    private String  deliveryOrderNo;

    private Date sendTime;

    private Date estArrivalTime;

    private String sender;

    private String senderMobile;

    private String sendLocation;

    private String receiver;

    private String receiverMobile;

    private String receiverLocation;
    private String driverName;
    private String driveMobile;

    private long truckTypeId;

    private BigDecimal deliveryFee;



}
