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
public class GoodsVO {
    private Long vegetableId;
    private String vegetableName;
    private Integer loadStatus;
    private BigDecimal vegetablePrice;
    private BigDecimal vegetableWeight;
}
