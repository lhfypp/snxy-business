package com.snxy.business.service;

import com.snxy.business.domain.DeliveryOrder;





import com.snxy.business.service.vo.*;

public interface DeliveryOrderService {
    BillVO createOrderNo(Long onlineUserId);

    void saveDeliveryOrder(SystemUserVO systemUserVO, DeliveryOrderVo deliveryOrderVo);

    HomePageOrderVO showNewestOrder(SystemUserVO systemUserVO);

    void confirmationOrder(SystemUserVO systemUserVO, DriverConfirmationVO driverConfirmationVO);

    DeliveryOrder showOrderDetails(Long deliveryOrderId);

    DeliveryOrder searchDeliveryOrderById(Long id);
}

