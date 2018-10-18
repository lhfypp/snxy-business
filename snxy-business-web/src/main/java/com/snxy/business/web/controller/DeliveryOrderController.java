package com.snxy.business.web.controller;

import com.snxy.business.domain.BillInfo;
import com.snxy.business.domain.DeliveryOrder;
import com.snxy.business.service.DeliveryOrderService;
import com.snxy.common.response.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


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
    @RequestMapping(value = "/seller/order/list")
    public ResultData<List<BillInfo> >searchDeliveryOrder(HttpServletRequest request){
        long userId=Long.parseLong(request.getParameter("userId"));//用户标识
        String orderStatus=request.getParameter("orderStatus");//订单状态
        String serchName=request.getParameter("serchName");//地点or联系人or单号


        ResultData<List<BillInfo>>listResultData=new ResultData<List<BillInfo>>();
        listResultData.setData(deliveryOrderService.searchDeliveryOrder(userId,orderStatus,serchName));

     return listResultData;
    }
    @RequestMapping("/failed")
    public ResultData  requestFailed(){


        return ResultData.fail("用户id为空");
    }
}
