package com.snxy.business.service;

import com.snxy.business.domain.*;
import com.snxy.business.service.vo.*;
import com.snxy.common.util.PageInfo;

import java.util.List;
import java.util.Map;

public interface DeliveryOrderService {

    void saveDeliveryOrder(DeliveryOrderVo deliveryOrderVo);

    String getOrderNo();

    List<BillInfo> selectDriverOrder(Long driverMobile);

    DeliveryOrder selectOrderByOrderId(Long orderId);

    BillInfoDetail searchDeliverOrderinfo(Long deliveryOrderId);
    void cancelOrder(Long orderId,Integer status);

    void updateEndLoading(Long deliveryOrderId,Integer status);

    void adminModifyOrder(AdminChangeOrderVo adminChangeOrderVo);

    OrderVo selectOrderMessageBydeliveryOrderId(Long deliveryOrderId);
    void cancelOrder(String  orderId);
    void changeDriver(String orderId,String driverName,String drivePhone);
    void updateOrder(UpdateBillInfoDetailVo billInfoDetail);
    void acceptOrder(String orderId);//司机接受订单有Id

    Map<String ,Object> getVehiclesForDriver(String OrderId);
    void updateCurrOrderReceiver(String OrderId,String vehicleId);
    void resufedOrder(String orderId);
    void tranferOrder(String orderId,String driveMobile ,String driverName);
	void checkProductionCertificate(Long productionCertificate, Integer qualitied,Long orderNo);

    void checkQualityCertificate(Long qualityCertificateId, Integer qualitied,Long orderNo);
    PageInfo<BillInfo> searchDeliveryOrderByPage(String orderStatus, String searchName, SystemUserVo systemUserVO);
}
