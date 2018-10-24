package com.snxy.business.service;

import com.snxy.business.domain.*;
import com.snxy.business.service.vo.AdminChangeOrderVo;
import com.snxy.business.service.vo.DeliveryOrderVo;
import com.snxy.business.service.vo.OrderVo;
import com.snxy.common.util.PageInfo;

import java.util.List;
import java.util.Map;

public interface DeliveryOrderService {

    void createDeliveryOrder(DeliveryOrderVo deliveryOrderVo);

    String getOrderNo();

    List<BillInfo> selectDriverOrder(Long driverMobile);

    DriverOrderInfo selectOrderByOrderId(Long orderId);

    void createDeliveryOrder(DeliveryOrder deliveryOrder, VegetableDeliveryRelation vegetableDeliveryRelation, VegetableCertificate vegetableCertificate, VegetableImage vegetableImage);
    List<BillInfo>  searchDeliveryOrder(String orderStatus, String searchName);
    BillInfoDetail searchDeliverOrderinfo(Long deliveryOrderId);
    PageInfo<BillInfo> searchDeliveryOrderByPage(String orderStatus, String searchName);
    void cancelOrder(Long orderId,Integer status);

    void updateEndLoading(Long deliveryOrderId);

    void adminModifyOrder(AdminChangeOrderVo adminChangeOrderVo);

    OrderVo selectOrderMessageBydeliveryOrderId(Long deliveryOrderId);
    void cancelOrder(String  orderId);
    void changeDriver(String orderId,String driverName,String drivePhone);
    void updateOrder(UpdateBillInfoDetail billInfoDetail);
    void acceptOrder(String orderId);//司机接受订单有Id

    Map<String ,Object> getVehiclesForDriver(String OrderId);
    void insertCurrOrderReceiver(String OrderId,String vehicleId);
    void updateCurrOrderReceiver(String OrderId,String vehicleId);
    void resufedOrder(String orderId);
    void tranferOrder(String orderId,String driveMobile ,String driverName);
}
