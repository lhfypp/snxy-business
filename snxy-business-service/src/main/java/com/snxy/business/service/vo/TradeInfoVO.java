package com.snxy.business.service.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TradeInfoVO {
    private OrderInfoVO orderInfoVO;//订单详情
    private String driverNum;    //车牌号
    private PayInfoVO payInfoVO;//付款明细
    private String tradeMethod;//支付方式
    private String tradeTime;//交易时间
    private String tradeNo;//订单编号
    private String totalFee;//总计 押金+进门费+检测费
    private String refundFee;//退款费
    @Data
    @Builder
    public static class OrderInfoVO {
        private String vegetableName;//菜名
        private Integer loadStatus;//满车 、半车
        private String tonnage;//吨位
    }
    @Data
    @Builder
    public static class PayInfoVO {
        private BigDecimal actualFee;//进门费
        private BigDecimal guaranteeDeposit;//押金
        private BigDecimal testFee;//检测费
    }

}
