package com.snxy.business.web.controller;

import com.snxy.business.domain.DeliveryOrder;
import com.snxy.business.domain.EntranceFeeCategory;
import com.snxy.business.service.DeliveryOrderService;
import com.snxy.business.service.EntranceFeeCategoryService;
import com.snxy.business.service.vo.*;
import com.snxy.common.response.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/homePage")
public class HomePageController {
    @Resource
    private DeliveryOrderService deliveryOrderService;
    @Resource
    private EntranceFeeCategoryService entranceFeeCategoryService;

    /*
    * 获取当前最需要最新订单（一张）
    * */
    @RequestMapping("/order/show/newest")
    public ResultData showNewestOrder(@RequestAttribute("systemUser")SystemUserVO systemUserVO){
        HomePageOrderVO homePageOrderVO = deliveryOrderService.showNewestOrder(systemUserVO);
        return ResultData.success(homePageOrderVO);
    }

    /*
    * 新建订单时系统默认生成数据
    * */
    @RequestMapping("/order/bill/new")
    public ResultData createOrderNo (@RequestAttribute("systemUser")SystemUserVO systemUserVO){
        BillVO billVO = deliveryOrderService.createOrderNo(systemUserVO.getOnlineUserId());
        return ResultData.success(billVO);
    }

    /*
    * 发布保存订单
    * */
    @RequestMapping(value = "/order/save")
    public ResultData saveDeliveryOrder (@RequestAttribute("systemUser")SystemUserVO systemUserVO,@RequestBody DeliveryOrderVo deliveryOrderVo){
        deliveryOrderService.saveDeliveryOrder(systemUserVO,deliveryOrderVo);
        return ResultData.success("");
    }

    /*
    * 添加货品界面展示
    * */
    @RequestMapping("/order/goods/show")
    public ResultData showAllGoods(){
        List<EntranceFeeCategory> entranceFeeCategoryList = entranceFeeCategoryService.showAllGoods();
        return ResultData.success(entranceFeeCategoryList);
    }

    /*
    * 货品信息搜索
    * */
    @RequestMapping(value = "/order/selectByGoodsName")
    public ResultData selectCompanyGoodsByGoodsNameAndCompanyId (String categoryName){
        List<EntranceFeeCategory> entranceFeeCategoryList = entranceFeeCategoryService.selectByCategoryName(categoryName);
        return ResultData.success(entranceFeeCategoryList);
    }

    /*
    * 司机选择车辆确认发货
    * 商户确认订单，商确认订单时不需传递车辆信息
    * */
    @RequestMapping("/order/driver/confirmation")
    public ResultData confirmationOrder(@RequestAttribute("systemUser")SystemUserVO systemUserVO,@RequestBody DriverConfirmationVO driverConfirmationVO){
        deliveryOrderService.confirmationOrder(systemUserVO,driverConfirmationVO);
        return ResultData.success("");
    }

    /*
    * 发货订单详细信息
    * */
    @RequestMapping("/order/details")
    public ResultData showOrderDetails(Long deliveryOrderId){
        DeliveryOrder deliveryOrder = deliveryOrderService.showOrderDetails(deliveryOrderId);

        return ResultData.success(deliveryOrder);
    }
}