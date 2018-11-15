package com.snxy.business.service;

import com.snxy.business.domain.DeliveryOrder;
import com.snxy.business.domain.EntranceFeeDetail;
import com.snxy.common.util.PageInfo;

import java.util.List;

import com.snxy.business.domain.DeliveryOrder;





import com.snxy.business.service.vo.*;

public interface DeliveryOrderService {
    PageInfo<DeliveryOrder> selectByCreatorId(Long onlineUserId, Long status);

    String selectByOrderNo(String orderNo);

    String chargeCount(EntranceFeeDetail entranceFeeDetail);
    BillVO createOrderNo(Long onlineUserId);

    void saveDeliveryOrder(SystemUserVO systemUserVO, DeliveryOrderVo deliveryOrderVo);

    HomePageOrderVO showNewestOrder(SystemUserVO systemUserVO);

    void confirmationOrder(SystemUserVO systemUserVO, DriverConfirmationVO driverConfirmationVO);

    DeliveryOrder showOrderDetails(Long deliveryOrderId);

    DeliveryOrder searchDeliveryOrderById(Long id);
}

