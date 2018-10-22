package com.snxy.business.web.controller;

import com.snxy.business.domain.*;
import com.snxy.business.service.CompanyUserRelationService;
import com.snxy.business.service.CompanyVegetableService;
import com.snxy.business.service.DeliveryOrderService;
import com.snxy.business.service.SystemUserService;
import com.snxy.common.response.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


/*
* 发货订单管理
* */
@RestController
@Slf4j
@RequestMapping("/delivery")
public class DeliveryOrderController {

    @Resource
    private DeliveryOrderService    deliveryOrderService;

    @Resource
    private CompanyVegetableService companyVegetableService;

    @Resource
    private CompanyUserRelationService companyUserRelationService;

    @Resource
    private SystemUserService systemUserService;


    //货品信息添加页展示
    @RequestMapping(value = "/seller/showGoods")
    public ResultData showGoods (Long userCompanyId){
        List<CompanyVegetable> companyVegetableList =  companyVegetableService.showGoods(1L);
        return ResultData.success(companyVegetableList);
    }

    //货品信息搜索
    @RequestMapping(value = "/seller/selectByGoodsName")
    public ResultData selectByGoodsName (String goodsName){
        CompanyVegetable companyVegetable = companyVegetableService.selectByGoodsName("大白菜");
        return ResultData.success(companyVegetable);
    }

    //新建发货订单
    @RequestMapping(value = "/seller/bill/new")
    public ResultData createDeliveryOrder (Integer deviceType,DeliveryOrderVo deliveryOrderVo){
        //先进行权限判断，是否为商户或者代办，公司信息是否完整，设置商户，代办身份标识为0，1
        deviceType = 1;
        if(deviceType!=0 && deviceType!=1){
            return ResultData.fail("你还没有完成认证");
        }
        //查询是否完成公司信息填写
        List<Long> list = companyUserRelationService.selectCompanyIsExist(1L);
        if(list==null || list.size()==0){
            return ResultData.fail("你还没有完成认证");
        }

        //如果已认证则新建一个订单,此处系统生成订单号

        String orderNo = deliveryOrderService.getOrderNo();

        deliveryOrderVo.setOrderNo(orderNo);
        return ResultData.success(deliveryOrderVo);
    }

    //保存发布订单
    @RequestMapping(value = "/seller/bill/save")
    public ResultData saveDeliveryOrder (DeliveryOrderVo deliveryOrderVo){

        deliveryOrderService.createDeliveryOrder(deliveryOrderVo);

        //发布订单对司机手机号进行判断是否注册
        SystemUser systemUser = systemUserService.selectByMobile(deliveryOrderVo.getDriverMobile());
        if (systemUser==null){
            //当查询不到司机的手机号注册信息时给司机手机号发送app下载了短信链接

        }else {
            //如果司机已经注册则app推送订单消息

        }

        return ResultData.success("订单发布成功");
    }

    //司机查看订单
    @RequestMapping(value = "/driver/order/list")
    public ResultData showDriverOrder(Long userId){

        List<BillInfo> driverOrders = deliveryOrderService.selectDriverOrder(userId);

        return ResultData.success(driverOrders);
    }

    //司机订单详情
    @RequestMapping(value = "/driver/bill/bill/detail")
    public ResultData selectOrderByOrderId(Long orderId){
        DriverOrderInfo driverOrderInfo = deliveryOrderService.selectOrderByOrderId(orderId);
        return ResultData.success(driverOrderInfo);
    }

}