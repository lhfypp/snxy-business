package com.snxy.business.service.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class tradeVO {
    private Long businessTypeId;//业务交易类型
    private String vegetableName;//菜品名称
//    private String driverNumberList;//车牌号
    private String tradeMethod;//支付方式  微信，支付宝 代扣
    private String totalFee;//支付总计
    private String tradeTime;//交易时间

}
