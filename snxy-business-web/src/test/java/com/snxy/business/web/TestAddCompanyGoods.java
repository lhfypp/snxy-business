package com.snxy.business.web;

import com.alibaba.fastjson.JSON;

public class TestAddCompanyGoods {
    public static void main(String[] args) {
        AddCompanyGoodsVo addCompanyGoodsVo = AddCompanyGoodsVo.builder()
                .companyId(1L)
                .vegetablePriceId(1L)
                .build();
        String s = JSON.toJSONString(addCompanyGoodsVo);
        System.out.println(s);
    }
}
