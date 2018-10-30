package com.snxy.business.web.controller;


import com.snxy.business.domain.CreateCheckBillVO;
import com.snxy.business.service.ManagementService;
import com.snxy.common.response.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;

import com.snxy.business.service.DeliveryOrderService;
import com.snxy.business.service.vo.AdminChangeOrderVo;

import com.snxy.business.service.vo.OrderVo;



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

    @Resource
    private DeliveryOrderService deliveryOrderService;

    //创建我的检测单
    @RequestMapping("/driver/checkBill/create")
    public ResultData createcCheckBill(CreateCheckBillVO createcCheckBillVO){
        //整合数据，存入quality_sheet表中
        managementService.save(createcCheckBillVO);
        return ResultData.success("创建检测单提交成功");
    }

    //管理员设置订单的产地证明是否合格
    @RequestMapping("/order/productionCertificate")
    public ResultData checkProductionCertificate(Long productionCertificate,Integer qualitied,Long orderNo){
        //合格qualitied参数传1，不合格参数传0
        deliveryOrderService.checkProductionCertificate(productionCertificate,qualitied,orderNo);

        return ResultData.success("");
    }

    //无产地证明遣离
    @RequestMapping("/order/updateMoveStatus")
    public ResultData updateMoveStatus(Long deliveryOrderId){
        deliveryOrderService.cancelOrder(deliveryOrderId, 7);
        return ResultData.success("");
    }

    //管理员设置订单的检测证明是否合格
    @RequestMapping("/order/qualityCertificate")
    public ResultData checkQualityCertificate(Long qualityCertificateId,Integer qualitied,Long orderNo){
        //合格qualitied参数传1，不合格参数传0
        deliveryOrderService.checkQualityCertificate(qualityCertificateId,qualitied,orderNo);

        return ResultData.success("");
    }

    //管理员修改订单,主要对货物的实际情况进行验证
    @RequestMapping("/bill/modify")
    public ResultData adminModifyOrder(AdminChangeOrderVo adminChangeOrderVo){
        deliveryOrderService.adminModifyOrder(adminChangeOrderVo);
        
        return ResultData.success("");
    }

    //进门获取货运单单信息(扫描二维码)
    @RequestMapping("/detailBill")
    public ResultData detailBill(Long deliveryOrderId){
        OrderVo orderVo = deliveryOrderService.selectOrderMessageBydeliveryOrderId(deliveryOrderId);
        return ResultData.success(orderVo);
    }



}
