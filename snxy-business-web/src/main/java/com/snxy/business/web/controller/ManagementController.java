package com.snxy.business.web.controller;

import com.snxy.business.domain.CreateCheckBillVO;
import com.snxy.business.service.ManagementService;
import com.snxy.common.response.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@Validated
@RequestMapping("/management")
public class ManagementController {
    @Resource
    private ManagementService managementService;
    //创建我的检测单
    @RequestMapping("/driver/checkBill/create")
    public ResultData createcCheckBill(CreateCheckBillVO createcCheckBillVO){
        //整合数据，存入quality_sheet表中
        managementService.save(createcCheckBillVO);

        return ResultData.success("创建检测单提交成功");


    }
}
