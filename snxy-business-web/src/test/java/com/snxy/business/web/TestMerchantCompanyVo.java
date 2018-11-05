package com.snxy.business.web;

import com.alibaba.fastjson.JSON;

public class TestMerchantCompanyVo {
    public static void main(String[] args) {
        MerchantCompanyVo merchantCompanyVo = MerchantCompanyVo.builder()
                .merchantName("测试腾讯")
                .merchantType((byte)1)
                .onlineUserId(1L)
                .operationScale(666666666)
                .operationSope("水果")
                .socialInfoCode("123456789")
                .build();
        String s = JSON.toJSONString(merchantCompanyVo);
        System.out.println(s);
    }
}
