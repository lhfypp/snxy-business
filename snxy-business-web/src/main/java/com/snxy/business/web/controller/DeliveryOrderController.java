package com.snxy.business.web.controller;

import com.snxy.business.domain.*;
import com.snxy.business.service.*;
import com.snxy.business.service.vo.*;
import com.snxy.common.response.ResultData;
import com.snxy.common.util.PageInfo;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.NotBlank;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

import java.util.List;
import java.util.Map;


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
    private VehicleGpsRecordService vehicleGpsRecordService;


    //货品信息添加页展示
    @RequestMapping(value = "/seller/showGoods")
    public ResultData showGoods (Long userCompanyId){
        List<GoodsVo> goodsVoList =  companyVegetableService.showGoods(userCompanyId);
        return ResultData.success(goodsVoList);
    }

    //货品信息搜索
    @RequestMapping(value = "/seller/selectByGoodsName")
    public ResultData selectCompanyGoodsByGoodsNameAndCompanyId (CompanyGoodsVo companyGoodsVo){
        CompanyVegetable companyVegetable = companyVegetableService.selectCompanyGoodsByGoodsNameAndCompanyId(companyGoodsVo);
        return ResultData.success(companyVegetable);
    }

    //新建发货订单的权限判断
    @RequestMapping(value = "/seller/bill/new")
    public ResultData createDeliveryOrder (OnlineUserVo onlineUserVo){
        //先进行权限判断，是否为商户或者代办，公司信息是否完整，设置商户，代办身份标识为0，1
        if(onlineUserVo.getIdentyType()!=0 && onlineUserVo.getIdentyType()!=1){
            return ResultData.fail("您不是商户或代办，没有新建发货订单权限");
        }
        //查询是否完成公司信息填写
        List<Long> list = companyUserRelationService.selectCompanyIsExist(onlineUserVo.getOnlineUserId());
        if(list==null || list.size()==0){
            return ResultData.fail("你还没有完成认证");
        }

        //如果已认证则新建一个订单,此处系统生成订单号

        DeliveryOrderVo deliveryOrderVo = new DeliveryOrderVo();
        String orderNo = deliveryOrderService.getOrderNo();


        deliveryOrderVo.setOrderNo(orderNo);
        return ResultData.success(deliveryOrderVo);
    }

    //保存发布订单
    @RequestMapping(value = "/seller/bill/save")
    public ResultData saveDeliveryOrder (DeliveryOrderVo deliveryOrderVo){
        deliveryOrderService.saveDeliveryOrder(deliveryOrderVo);
        return ResultData.success("");
    }


    //司机查看订单
    @RequestMapping(value = "/driver/order/list")
    public ResultData showDriverOrder(Long driverMobile){
        List<BillInfo> driverOrders = deliveryOrderService.selectDriverOrder(driverMobile);

        return ResultData.success(driverOrders);
    }



    //查看订单详情
    @RequestMapping(value = "/seller/order/detail")
    public ResultData<BillInfoDetail > searchDeliveryOrderInfo(@NotBlank(message="订单id不能为空") String deliveryOrderId) {
        long orderId= Long.parseLong(deliveryOrderId);
        return ResultData.success (deliveryOrderService.searchDeliverOrderinfo(orderId));

    }

    //司机订单详情
    @RequestMapping(value = "/driver/bill/bill/detail")
    public ResultData selectOrderByOrderId(Long orderId){

        DeliveryOrder driverOrderInfo = deliveryOrderService.selectOrderByOrderId(orderId);
        return ResultData.success(driverOrderInfo);
    }
   //商户取消订单
    @RequestMapping("/seller/bill/cancel")
    public ResultData cancelOrder(String logisticOrderId){
        long orderId= Long.parseLong(logisticOrderId);
        //从用户对象获取
        String identityName="1";//商户
        if("1".equals(identityName)){

        deliveryOrderService.cancelOrder(orderId,1);
        return ResultData.success("商户取消订单成功");
        }else{
            return ResultData.fail("该用户没有权限取消订单");
        }
    }

    //司机提示装货完毕
    @RequestMapping("/driver/endLoading")
    public ResultData driverEndLoading(Long deliveryOrderId){
        deliveryOrderService.updateEndLoading(deliveryOrderId,3);
        return ResultData.success("");
    }

    //上传GPS
    @RequestMapping("/driver/location/upload")
    public ResultData uploadLocation(VehicleGpsVo vehicleGpsVo){
        vehicleGpsRecordService.insertLocationGPS(vehicleGpsVo);
        return ResultData.success("");
    }

    //下载GPS
    @RequestMapping("/location/download")
    public ResultData downloadLocation(Long deliveryOrderId){
        List<GPSLocation> gpsLocations = vehicleGpsRecordService.selectLocationGPS(deliveryOrderId);
        return ResultData.success(gpsLocations);
    }

    //卖家或者代办换司机
    @RequestMapping("/seller/order/changeDriver")
    public  ResultData<String> changeDriver(ChangeDriversVo changeDriversVo){
        deliveryOrderService.changeDriver(changeDriversVo.getLogisticOrderId(),changeDriversVo.getDriverName(),changeDriversVo.getDriverMobile());
        return ResultData.success("卖家或者代办更换司机成功");

    }
    // 关闭订单 合格关闭和不合格关闭（是否与关闭订单合并?,还是三个按钮分别请求）
    @RequestMapping("/seller/order/closeOrder")
    public ResultData<? extends Object> closeOrder(@NotBlank(message="订单id不能为空")@RequestParam String logisticOrderId ,@RequestAttribute(value = "systemUser",required = false) SystemUserVo systemUserVO){
        log.error("获取用户信息:{}",systemUserVO);
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
    @RequestMapping("/seller/order/updateOrder")
    public ResultData<? extends Object> updateOrder(@Valid UpdateBillInfoDetailVo billInfoDetail){
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
    public ResultData chooseVehicle(ChooseVehicle chooseVehicle){

        deliveryOrderService.acceptOrder(chooseVehicle.getOrderId());//把状态改为2
        deliveryOrderService.updateCurrOrderReceiver(chooseVehicle.getOrderId(),chooseVehicle.getVehicleId());//司机选择车辆信息
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

    //查看订单列表,实现分页查询
    @RequestMapping(value = "/seller/order/list")
    public ResultData<PageInfo<BillInfo>>searchDeliveryOrderbypage(@Valid OrderListVo orderListVo){

        PageInfo<BillInfo> pageInfo;
        String orderStatus = orderListVo.getOrderStatus();//订单状态
        String searchName = orderListVo.getSearchName();//地点or联系人or单号
        pageInfo=deliveryOrderService.searchDeliveryOrderByPage(orderStatus, searchName);
        return ResultData.success(pageInfo);
    }


}