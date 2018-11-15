package com.snxy.business.web.controller;

import com.snxy.business.domain.DeliveryOrder;
import com.snxy.business.domain.QualitySheet;
import com.snxy.business.service.DeliveryOrderService;
import com.snxy.business.service.QualitySheetService;
import com.snxy.common.response.ResultData;
import com.snxy.common.util.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/commercialUser")
@Slf4j
public class PersonalCompanyController {

    @Resource
    private DeliveryOrderService deliveryOrderService;
    @Resource
    private QualitySheetService qualitySheetService;

    //商户负责人查看在途/已完成订单列表
    @RequestMapping("/onOrder/list")
    public ResultData onOrderList(Long onlineUserId,Long status){//status 2 运输中  5 已完成
        PageInfo<DeliveryOrder> deliveryOrderList = deliveryOrderService.selectByCreatorId(onlineUserId,status);
        return ResultData.success(deliveryOrderList);
    }
    //商户负责人查看质量检测单
    @RequestMapping("/qualitySheet/list")
    public ResultData qualitySheetList(Long onlineUserId){
        PageInfo<QualitySheet> qualitySheetList = qualitySheetService.qualitySheetList(onlineUserId);
        return ResultData.success(qualitySheetList);
    }
}