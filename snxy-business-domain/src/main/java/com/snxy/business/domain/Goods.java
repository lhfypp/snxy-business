package com.snxy.business.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Goods {
    private Long goodsId;

    private String goodsName;

    private Integer goodsWeight;

    private BigDecimal goodsPrice;
}
