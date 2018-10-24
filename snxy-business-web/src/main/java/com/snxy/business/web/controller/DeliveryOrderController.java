package com.snxy.business.web.controller;

import com.snxy.business.domain.*;
import com.snxy.business.service.*;
import com.snxy.business.service.vo.DeliveryOrderVo;
import com.snxy.common.response.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    private SystemUserService systemUserService;

    @Resource
    private VehicleGpsRecordService vehicleGpsRecordService;


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

    // 查看订单列表
    @RequestMapping(value = "/seller/order/list")
    public ResultData<List<BillInfo>> searchDeliveryOrder(HttpServletRequest request) {
        String orderStatus = request.getParameter("orderStatus");//订单状态
        String searchName = request.getParameter("searchName");//地点or联系人or单号


        ResultData<List<BillInfo>> listResultData = new ResultData<List<BillInfo>>();
        listResultData.setData(deliveryOrderService.searchDeliveryOrder(orderStatus, searchName));

        return listResultData;
    }


    //司机查看订单
    @RequestMapping(value = "/driver/order/list")
    public ResultData showDriverOrder(Long driverMobile){
        List<BillInfo> driverOrders = deliveryOrderService.selectDriverOrder(driverMobile);

        return ResultData.success(driverOrders);
    }

    //查看订单详情
    @RequestMapping(value = "/seller/order/detail")
    public ResultData<BillInfoDetail > searchDeliveryOrderInfo(HttpServletRequest request) {
        long deliveryOrderId= Long.parseLong(request.getParameter("deliveryOrderId"));
        return ResultData.success (deliveryOrderService.searchDeliverOrderinfo(deliveryOrderId));

    }

    //司机订单详情
    @RequestMapping(value = "/driver/bill/bill/detail")
    public ResultData selectOrderByOrderId(Long orderId){

        DeliveryOrder driverOrderInfo = deliveryOrderService.selectOrderByOrderId(orderId);
        return ResultData.success(driverOrderInfo);
    }
   //商户取消订单
    @RequestMapping("/seller/bill/cancel")
    public ResultData cancelOrder(HttpServletRequest request){
        long logisticOrderId= Long.parseLong(request.getParameter("logisticOrderId"));
        //从用户对象获取
        String identityName="1";//商户
        if("1".equals(identityName)){

        deliveryOrderService.cancelOrder(logisticOrderId,1);
        return ResultData.success("商户取消订单成功");
        }else{
            return ResultData.fail("该用户没有权限取消订单");
        }
    }

    //司机提示装货完毕
    @RequestMapping("/driver/endLoading")
    public ResultData driverEndLoading(Long deliveryOrderId){
        deliveryOrderService.updateEndLoading(deliveryOrderId);

        return ResultData.success("");
    }

    //上传GPS
    @RequestMapping("/driver/location/upload")
    public ResultData uploadLocation(VehicleGpsRecord vehicleGpsRecord,Long userId){
        vehicleGpsRecordService.insertLocationGPS(vehicleGpsRecord,userId);
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
    public  ResultData<String> changeDriver(@RequestParam @NotBlank(message="订单号不能为空") String logisticOrderId, @RequestParam String driverMobile, @RequestParam String driverName){
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