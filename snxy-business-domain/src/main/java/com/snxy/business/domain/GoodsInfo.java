package com.snxy.business.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class GoodsInfo {
    private String goodsName;
    private Integer weight;
}
