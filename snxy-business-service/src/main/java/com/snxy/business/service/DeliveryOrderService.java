package com.snxy.business.service;

import com.snxy.business.domain.*;

import java.util.List;


public interface DeliveryOrderService {
    void createDeliveryOrder(DeliveryOrderVo deliveryOrderVo);

    String getOrderNo();

    List<BillInfo> selectDriverOrder(Long userId);

    DriverOrderInfo selectOrderByOrderId(Long orderId);
}
