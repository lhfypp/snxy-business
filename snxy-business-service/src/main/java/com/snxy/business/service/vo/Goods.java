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
public class Goods {
    private Long goodsId;	//Long 货品名称
    private String goodsImg;	//String 货品图片
    private String goodsName;	//String 货品名称
}
