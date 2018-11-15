package com.snxy.business.service.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReceiverVO {
    //收货人姓名
    private String receiverName;
    //收货人电话
    private String receiverMobile;
    //收货地址
    private String endAddr;
}
