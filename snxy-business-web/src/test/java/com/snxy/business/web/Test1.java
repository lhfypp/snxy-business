package com.snxy.business.web;

import com.alibaba.fastjson.JSON;
import com.snxy.business.service.vo.NewCompanyVO;

public class Test1 {
    public static void main(String[] args) {
        NewCompanyVO newCompanyVO = NewCompanyVO.builder()
                .merchantName("腾讯")
                .corporateCertificationUrl("ssssssssssssssss")
                .onlineUserId(4L)
                .socialInfoCode("123456789")
                .build();
        String s = JSON.toJSONString(newCompanyVO);
        System.out.println(s);
    }
}
