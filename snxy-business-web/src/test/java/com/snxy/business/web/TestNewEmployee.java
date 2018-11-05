package com.snxy.business.web;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

public class TestNewEmployee {
    public static void main(String[] args) {
        List<Identy> identyList = new ArrayList<>();
        Identy identy1 = Identy.builder()
                .identyName("商户")
                .identityId(0)
                .build();
        Identy identy2 = Identy.builder()
                .identyName("代办")
                .identityId(1)
                .build();
        Identy identy3 = Identy.builder()
                .identyName("司机")
                .identityId(2)
                .build();

        identyList.add(identy2);
        identyList.add(identy3);

        CompanyUserListVo companyUserListVo = CompanyUserListVo.builder()
                .companyId(11L)
                .onlineUserId(9L)
                .identyList(identyList)
                .companyType((byte)0)
                .name("张三1111")
                .phone("1111666666666")
                .build();
        String s = JSON.toJSONString(companyUserListVo);
        System.out.println(s);
    }
}
