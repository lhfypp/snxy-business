package com.snxy.business.web.controller;

import com.snxy.business.domain.EntranceFeeDetail;
import com.snxy.business.service.DeliveryOrderService;
import com.snxy.common.response.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/DeliveryOrder")
@Slf4j
public class DeliveryOrderController {

    @Resource
    private DeliveryOrderService deliveryOrderService;

    //获取订单节点信息
    @RequestMapping("/status/node")
    public ResultData deliveryOrderStatus(String orderNo){
        String status = deliveryOrderService.selectByOrderNo(orderNo);
        return ResultData.success(status);
    }

    //收费计算
    @RequestMapping("/charge/count")
    public ResultData chargeCount(EntranceFeeDetail entranceFeeDetail){
        String price = deliveryOrderService.chargeCount(entranceFeeDetail);
        return ResultData.success(price);
    }
}
