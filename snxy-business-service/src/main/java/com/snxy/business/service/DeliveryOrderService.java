package com.snxy.business.service;

import com.snxy.business.domain.DeliveryOrder;
import com.snxy.business.domain.EntranceFeeDetail;
import com.snxy.common.util.PageInfo;

import java.util.List;

public interface DeliveryOrderService {
    PageInfo<DeliveryOrder> selectByCreatorId(Long onlineUserId, Long status);

    String selectByOrderNo(String orderNo);

    String chargeCount(EntranceFeeDetail entranceFeeDetail);
}
