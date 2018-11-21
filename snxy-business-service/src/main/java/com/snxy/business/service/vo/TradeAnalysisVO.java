package com.snxy.business.service.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TradeAnalysisVO {
    private String entryOrderTotal;//进门订单数总计
    private String entryCostTotal;//进门费用总计
    private String weChatTotal;//微信支付总计
    private String alipayTotal;//支付宝支付总计
    private String noQualityTotal;//无检测证明订单总计
    private Integer qualityTotal;//检测费总计
    private BigDecimal vegetableWeightTotal;//进出门货物公斤总计
    private List<VegetableVO> vegetableVOList;//菜品详情vo
}
