package com.snxy.business.service;

import com.github.pagehelper.PageInfo;
import com.snxy.business.domain.*;

import java.util.List;
import java.util.Map;

public interface DeliveryOrderService {
    void createDeliveryOrder(DeliveryOrder deliveryOrder, VegetableDeliveryRelation vegetableDeliveryRelation, VegetableCertificate vegetableCertificate, VegetableImage vegetableImage);
    List<BillInfo>  searchDeliveryOrder( String orderStatus, String searchName);
    BillInfoDetail searchDeliverOrderinfo(String deliveryOrderId);
    PageInfo<BillInfo> searchDeliveryOrderByPage(String orderStatus, String searchName);
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
