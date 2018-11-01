package com.snxy.business.service;

import com.snxy.business.domain.*;
import com.snxy.business.service.vo.*;
<<<<<<< HEAD
=======
import com.snxy.business.service.vo.AdminChangeOrderVo;
import com.snxy.business.service.vo.DeliveryOrderVo;
import com.snxy.business.service.vo.OrderVo;
import com.snxy.business.service.vo.UpdateBillInfoDetailVo;
>>>>>>> d1aef324a1116338d372624d22129509a4e81ae8
import com.snxy.common.util.PageInfo;

import java.util.List;
import java.util.Map;

public interface DeliveryOrderService {

    String saveDeliveryOrder(DeliveryOrderVo deliveryOrderVo);

    String getOrderNo();

    List<DriverOrderVo> selectDriverOrder(String driverMobile);

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
	void checkProductionCertificate(Integer qualitied,Long orderNo);

    void checkQualityCertificate(Integer qualitied,Long orderNo);

    OrderNoVo createDeliveryOrder(Long onlineUserId);

<<<<<<< HEAD
    void checkQualityCertificate(Long qualityCertificateId, Integer qualitied,Long orderNo);
    PageInfo<BillInfo> searchDeliveryOrderByPage(String orderStatus, String searchName, SystemUserVo systemUserVO);
=======
    PageInfo<BillInfo> searchDeliveryOrderByPage(String orderStatus, String searchName);
>>>>>>> d1aef324a1116338d372624d22129509a4e81ae8
}
