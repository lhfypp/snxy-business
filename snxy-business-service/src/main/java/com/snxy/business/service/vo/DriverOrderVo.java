package com.snxy.business.service.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DriverOrderVo {
    private Long id;
    private String orderNo;
    private String senderName;
    private String receiverName;
    private String startAddr;
    private String endAddr;
    private Date gmtCreate;
    private String qrcodeUrl;
    private String statusDes;
}
