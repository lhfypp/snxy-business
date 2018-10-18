package com.snxy.business.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillInfo {
    private Long id;
    private String orderNo;
    private String senderName;
    private String receiverName;
    private String startAddr;
    private String endAddr;
    private Date gmtCreate;
    private Integer status;



}
