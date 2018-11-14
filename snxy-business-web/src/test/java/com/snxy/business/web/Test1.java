package com.snxy.business.web;



import com.alibaba.fastjson.JSON;
import com.snxy.business.service.vo.EmployeeVO;

import java.io.IOException;


public class Test1 {
    public static void main(String[] args) throws IOException {
        EmployeeVO employeeVO = EmployeeVO.builder()
                .userName("啦啦啦啦啊")
                .companyId(8L)
                .phone("13999999988")
                .build();
        String s = JSON.toJSONString(employeeVO);
        System.out.println(s);
    }
}
