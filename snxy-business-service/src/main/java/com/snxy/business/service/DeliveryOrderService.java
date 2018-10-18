package com.snxy.business.service;

import com.snxy.business.domain.BillInfo;
import com.snxy.business.domain.DeliveryOrder;

import java.util.List;

public interface DeliveryOrderService {
    void createDeliveryOrder(DeliveryOrder deliveryOrder);
    List<BillInfo>  searchDeliveryOrder(Long useId, String orderStatus, String serchName);
}
