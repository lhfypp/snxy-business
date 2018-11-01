package com.snxy.business.service.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderNoVo {
    //订单号
    private String orderNo;
    //发起人姓名
    private String name;
    private Long onlineUserId;
    //公司名
    private String merchantName;
}
