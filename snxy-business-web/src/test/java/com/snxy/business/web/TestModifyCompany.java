package com.snxy.business.web;

import com.alibaba.fastjson.JSON;
import com.snxy.business.service.vo.ModifyCompanyMessageVo;

public class TestModifyCompany {
    public static void main(String[] args) {
        ModifyCompanyMessageVo modifyCompanyMessageVo = ModifyCompanyMessageVo.builder()
                .id(11L)
                .merchantName("修改测试公司")
                .merchantType(2)
                .operationScale(11111)
                .operationSope("测试水果蔬菜")
                .socialInfoCode("11111111")
                .build();

        String s = JSON.toJSONString(modifyCompanyMessageVo);
        System.out.println(s);
    }
}
