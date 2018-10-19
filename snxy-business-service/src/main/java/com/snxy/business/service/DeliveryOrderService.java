package com.snxy.business.service;

import com.snxy.business.domain.*;

import java.util.List;

public interface DeliveryOrderService {
    void createDeliveryOrder(DeliveryOrderVo deliveryOrderVo);
    List<BillInfo>  searchDeliveryOrder(Long useId, String orderStatus, String serchName);

    String getOrderNo();
}
