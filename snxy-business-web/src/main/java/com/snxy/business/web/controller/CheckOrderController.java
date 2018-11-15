package com.snxy.business.web.controller;

import com.snxy.business.domain.QualitySheet;
import com.snxy.business.service.QualitySheetService;
import com.snxy.common.response.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/Dirver")
@Slf4j
public class CheckOrderController {
    @Resource
    private QualitySheetService qualitySheetService;

    //检测单详情
    @RequestMapping("/qualitySheet/info")
    public ResultData qualitySheetByOrderId(Long deliveryOrderId){
        QualitySheet qualitySheet = qualitySheetService.qualitySheetByOrderId(deliveryOrderId);
        return ResultData.success(qualitySheet);
    }
}
