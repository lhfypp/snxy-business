package com.snxy.business.service.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SenderVO {
    //发货人姓名
    private String senderName;
    //发货人电话
    private String senderMobile;
    //发货地址
    private String startAddr;
}
