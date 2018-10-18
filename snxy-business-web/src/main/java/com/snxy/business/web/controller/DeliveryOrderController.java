package com.snxy.business.web.controller;

import com.snxy.business.domain.DeliveryOrder;
import com.snxy.business.service.DeliveryOrderService;
import com.snxy.common.response.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/*
* 发货订单管理
* */
@RestController
@Slf4j
@RequestMapping("/delivery")
public class DeliveryOrderController {

    @Resource
    private DeliveryOrderService deliveryOrderService;

    @RequestMapping(value = "/seller/bill/new")
    public ResultData createDeliveryOrder (){

        DeliveryOrder deliveryOrder = new DeliveryOrder();

        deliveryOrderService.createDeliveryOrder(deliveryOrder);

        return ResultData.success("发货订单新建成功");
    }
}
