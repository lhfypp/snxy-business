package com.snxy.business.web.controller;


import com.snxy.business.domain.CheckBillInfo;
import com.snxy.business.domain.CreateCheckBillVO;
import com.snxy.business.service.ManagementService;
import com.snxy.business.service.vo.CertificateVo;
import com.snxy.common.response.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;

import com.snxy.business.service.DeliveryOrderService;
import com.snxy.business.service.vo.AdminChangeOrderVo;

import com.snxy.business.service.vo.OrderVo;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

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
    public ResultData checkProductionCertificate(@RequestBody CertificateVo certificateVo){
        //合格qualitied参数传1，不合格参数传0
        deliveryOrderService.checkProductionCertificate(certificateVo.getQualitied(),certificateVo.getOrderNo());
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
    public ResultData checkQualityCertificate(@RequestBody CertificateVo certificateVo){
        //合格qualitied参数传1，不合格参数传0
        deliveryOrderService.checkQualityCertificate(certificateVo.getQualitied(),certificateVo.getOrderNo());
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

    //司机查看检测单列表
    @RequestMapping(value="/driver/checkBill/list")
    public ResultData CheckBillList(String driverMobile){
        List<CheckBillInfo> list = managementService.selectCheckBillList(driverMobile);
        return ResultData.success(list);
    }
    //司机检测单证明详情
    @RequestMapping(value="/driver/checkBill/detail")
    public ResultData CheckBillByUserId(String deliveryOrderId){
        CheckBillInfo checkBillInfo = managementService.selectCheckBillByUserId(deliveryOrderId);
        return ResultData.success(checkBillInfo);
    }
    //检测证明的模糊查询
    @RequestMapping(value="/driver/checkBill/vague")
    public ResultData selectVague(String vegetableCategoryName){
        List list = managementService.selectVague(vegetableCategoryName);
        return ResultData.success(list);
    }
}
