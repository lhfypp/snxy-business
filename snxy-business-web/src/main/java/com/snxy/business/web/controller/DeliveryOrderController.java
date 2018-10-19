package com.snxy.business.web.controller;

import com.snxy.business.domain.*;
import com.snxy.business.service.CompanyUserRelationService;
import com.snxy.business.service.CompanyVegetableService;
import com.snxy.business.service.DeliveryOrderService;
import com.snxy.business.service.OnlineUserService;
import com.snxy.common.response.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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



    @Resource
    private CompanyVegetableService companyVegetableService;
    @Resource
    private OnlineUserService onlineUserService;
    @Resource
    private CompanyUserRelationService companyUserRelationService;


    //发货信息填写保存
    @RequestMapping(value = "/seller/saveSendMessage")
    public ResultData saveSendMessage (DeliveryOrderVo deliveryOrderVo){
        return ResultData.success(deliveryOrderVo);
    }

    //收获信息填写保存
    @RequestMapping(value = "/seller/saveGetMessage")
    public ResultData saveGetMessage (DeliveryOrderVo deliveryOrderVo){
        return ResultData.success(deliveryOrderVo);
    }

    //货品信息添加页展示
    @RequestMapping(value = "/seller/showGoods")
    public ResultData showGoods (Long userCompanyId){
        List<CompanyVegetable> companyVegetableList =  companyVegetableService.showGoods(1L);
        System.out.println(companyVegetableList);
        return ResultData.success(companyVegetableList);
    }

    //货品信息搜索
    @RequestMapping(value = "/seller/selectByGoodsName")
    public ResultData selectByGoodsName (String goodsName){
        CompanyVegetable companyVegetable = companyVegetableService.selectByGoodsName("大白菜");
        return ResultData.success(companyVegetable);
    }

    //货品信息添加保存
    @RequestMapping(value = "/seller/saveGoodsMesssage")
    public ResultData saveGoodsMessage (){
        return ResultData.success("");
    }


    //货品信息删除
    @RequestMapping(value = "/seller/deleteGoodsMessage")
    public ResultData deleteGoodsMessage (){
        return ResultData.success("");
    }


    //新建发货订单
    @RequestMapping(value = "/seller/bill/new")
    public ResultData createDeliveryOrder (DeliveryOrderVo deliveryOrderVo){
        //先进行权限判断

        //如果已认证则新建一个订单,此处系统生成订单号暂时写死
        String orderNo = "FH20181018104500000001";
        deliveryOrderVo.setOrderNo(orderNo);
        return ResultData.success(deliveryOrderVo);
    }

    //保存发布订单
    @RequestMapping(value = "/seller/bill/save")
    public ResultData saveDeliveryOrder (DeliveryOrderVo deliveryOrderVo){
        //订单信息DeliveryOrder，假数据
        DeliveryOrder deliveryOrder = new DeliveryOrder();
        //货品信息VegetableDeliveryRelation，假数据
        VegetableDeliveryRelation vegetableDeliveryRelation = new VegetableDeliveryRelation();
        //产地证明，质检单VegetableCertificate,假数据
        VegetableCertificate vegetableCertificate = new VegetableCertificate();
        //货品照片VegetableImage,假数据
        VegetableImage vegetableImage = new VegetableImage();

        deliveryOrderService.createDeliveryOrder(deliveryOrder,vegetableDeliveryRelation,vegetableCertificate,vegetableImage);

        return ResultData.success("订单发布成功");
    }

    @RequestMapping(value = "/seller/order/list")
    public ResultData<List<BillInfo>> searchDeliveryOrder(HttpServletRequest request) {

        String orderStatus = request.getParameter("orderStatus");//订单状态
        String searchName = request.getParameter("searchName");//地点or联系人or单号


        ResultData<List<BillInfo>> listResultData = new ResultData<List<BillInfo>>();
        listResultData.setData(deliveryOrderService.searchDeliveryOrder(orderStatus, searchName));

        return listResultData;
    }

    @RequestMapping(value = "/seller/order/detail")
    public ResultData<DeliveryOrder >searchDeliveryOrderInfo(HttpServletRequest request) {
        long deliveryOrderId= Long.parseLong(request.getParameter("deliveryOrderId"));
        return ResultData.success (deliveryOrderService.searchDeliverOrderinfo(deliveryOrderId));

    }


}