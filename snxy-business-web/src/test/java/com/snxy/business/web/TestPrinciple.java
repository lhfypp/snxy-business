package com.snxy.business.web;

import com.alibaba.fastjson.JSON;
import com.snxy.business.service.vo.ChangePrincipleVo;

public class TestPrinciple {
    public static void main(String[] args) {
        ChangePrincipleVo changePrincipleVo = ChangePrincipleVo.builder()
                .companyId(11)
                .onlineUserId(1L)
                .isResponsible(1)
                .build();
        String s = JSON.toJSONString(changePrincipleVo);
        System.out.println(s);
    }
}
