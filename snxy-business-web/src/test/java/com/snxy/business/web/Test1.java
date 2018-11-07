package com.snxy.business.web;



import com.alibaba.fastjson.JSON;
import com.snxy.business.service.vo.ChangePrincipleVO;
import com.snxy.business.service.vo.EmployeeVO;

import java.util.ArrayList;
import java.util.List;


public class Test1 {
    public static void main(String[] args) {
      List<ChangePrincipleVO> changePrincipleVOS = new ArrayList<>();
      ChangePrincipleVO changePrincipleVO = ChangePrincipleVO.builder()
              .isResponsible(0)
              .phone("136111")
              .build();
      ChangePrincipleVO changePrincipleVO1 = ChangePrincipleVO.builder()
              .isResponsible(1)
              .phone("18093194564")
              .build();
      ChangePrincipleVO changePrincipleVO2 = ChangePrincipleVO.builder()
              .isResponsible(0)
              .phone("15870451234")
              .build();
      changePrincipleVOS.add(changePrincipleVO);
      changePrincipleVOS.add(changePrincipleVO1);
      changePrincipleVOS.add(changePrincipleVO2);
      String s = JSON.toJSONString(changePrincipleVOS);
      System.out.println(s);
    }
}
