package com.snxy.business.web.controller;

import com.github.pagehelper.PageInfo;
import com.snxy.business.domain.*;
import com.snxy.business.service.CompanyUserRelationService;
import com.snxy.business.service.CompanyVegetableService;
import com.snxy.business.service.DeliveryOrderService;
import com.snxy.business.service.OnlineUserService;
import com.snxy.common.response.ResultData;
import lombok.extern.slf4j.Slf4j;


import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


import javax.validation.Valid;



import java.util.List;
import java.util.Map;


/*
* 发货订单管理
* */

@RestController
@Slf4j
@Validated
@RequestMapping("/delivery")

public class DeliveryOrderController {

    @Resource
    private DeliveryOrderService    deliveryOrderService;



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
    // 查看订单列表
    @RequestMapping(value = "/seller/order/list")
    public ResultData<List<BillInfo>> searchDeliveryOrder(HttpServletRequest request) {

        String orderStatus = request.getParameter("orderStatus");//订单状态
        String searchName = request.getParameter("searchName");//地点or联系人or单号


        ResultData<List<BillInfo>> listResultData = new ResultData<List<BillInfo>>();
        listResultData.setData(deliveryOrderService.searchDeliveryOrder(orderStatus, searchName));

        return listResultData;
    }
    //查看订单详情
    @RequestMapping(value = "/seller/order/detail")
    public ResultData<BillInfoDetail >searchDeliveryOrderInfo(@NotBlank(message="订单id不能为空") @RequestParam String deliveryOrderId) {

        return ResultData.success (deliveryOrderService.searchDeliverOrderinfo(deliveryOrderId));

    }
    //实现分页查询
    @RequestMapping(value = "/seller/order/list2")
    public ResultData<PageInfo<BillInfo>>searchDeliveryOrderbypage(HttpServletRequest request){
        PageInfo<BillInfo> pageInfo=null;
        String orderStatus = request.getParameter("orderStatus");//订单状态
        String searchName = request.getParameter("searchName");//地点or联系人or单号
        pageInfo=deliveryOrderService.searchDeliveryOrderByPage(orderStatus, searchName);
        return ResultData.success(pageInfo);
    }
   //商户取消订单
    @RequestMapping("/seller/bill/cancel")
    public ResultData cancelOrder(@NotBlank(message="订单号id不能为空")@RequestParam String logisticOrderId){

        //从用户对象获取
        String identityName="1";//商户
        if("1".equals(identityName)){

        deliveryOrderService.cancelOrder(logisticOrderId);
        return ResultData.success("商户取消订单成功");
        }else{
            return ResultData.fail("该用户没有权限取消订单");
        }
    }
    //卖家或者代办换司机
    @RequestMapping("/seller/order/changeDriver")
    public  ResultData<String> changeDriver(@RequestParam  @NotBlank(message="订单号不能为空") String logisticOrderId, @RequestParam String driverMobile, @RequestParam String driverName){
        deliveryOrderService.changeDriver(logisticOrderId,driverName,driverMobile);
        return ResultData.success("卖家或者代办更换司机成功");

    }
    // 关闭订单 合格关闭和不合格关闭（是否与关闭订单合并?,还是三个按钮分别请求）
    public ResultData<? extends Object> closeOrder(@NotBlank(message="订单id不能为空")@RequestParam String logisticOrderId ){

        //从用户对象获取
        String identityName="1";//商户
        if("1".equals(identityName)){

            deliveryOrderService.cancelOrder(logisticOrderId);
            return ResultData.success("商户取消订单成功");
        }else{
            return ResultData.fail("该用户没有权限取消订单");
        }
    }
    //修改订单
    public ResultData<? extends Object> updateOrder(@Valid @RequestBody UpdateBillInfoDetail billInfoDetail){
        deliveryOrderService.updateOrder(billInfoDetail);
        return ResultData.success("修改订单成功");

    }
    //司机接受订单
    @RequestMapping("/driver/bill/accept")
    public ResultData<Map<?,?>> accectOrderByDriver(@RequestParam @NotBlank(message = "订单id不能为空")String orderId){

        //返回司机的车辆信息，用在线用户id进行查询
     return  ResultData.success(deliveryOrderService.getVehiclesForDriver(orderId));
    }


    //司机选择车辆 车牌号，和orderNO确定
    @RequestMapping("/driver/vehicle/choose")
    public ResultData ChooseVehicle(@RequestParam @NotBlank(message = "订单id不能为空")String OrderId,@RequestParam @NotBlank(message="车类型id")String vehicleId){

        deliveryOrderService.acceptOrder(OrderId);//把状态改为2
      //  deliveryOrderService.insertCurrOrderReceiver(OrderId,vehicleId);//把信息插入curr_order_receiver表
        deliveryOrderService.updateCurrOrderReceiver(OrderId,vehicleId);//司机选择车辆信息
        return ResultData.success("选择车辆成功");
    }
    //司机拒绝接单
    @RequestMapping("/driver/bill/reject")
    public ResultData RefusedOrder(@NotBlank(message="订单id不能为空")@RequestParam String deliveryOrderId){
        deliveryOrderService.resufedOrder(deliveryOrderId);
        return ResultData.success("司机拒绝订单成功");
    }
    //司机转让订单
    @RequestMapping("/driver/order/reject")
    public void transferOrder(@RequestParam @NotBlank(message="订单id不能为空")String deliveryOrderId
           ,@RequestParam String driverMobile,@RequestParam String driverName)
    {
        deliveryOrderService.tranferOrder(deliveryOrderId,driverMobile,driverName);
    }

}