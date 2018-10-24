package com.snxy.business.service;

import com.snxy.business.domain.*;
import com.snxy.business.service.vo.AdminChangeOrderVo;
import com.snxy.business.service.vo.DeliveryOrderVo;
import com.snxy.business.service.vo.OrderVo;
import com.snxy.common.util.PageInfo;

import java.util.List;


public interface DeliveryOrderService {

    void createDeliveryOrder(DeliveryOrderVo deliveryOrderVo);

    String getOrderNo();

    List<BillInfo> selectDriverOrder(Long userId);

    DriverOrderInfo selectOrderByOrderId(Long orderId);

    List<BillInfo>  searchDeliveryOrder( String orderStatus, String searchName);
    BillInfoDetail searchDeliverOrderinfo(Long deliveryOrderId);
    PageInfo<BillInfo> searchDeliveryOrderByPage(String orderStatus, String searchName);
    void cancelOrder(Long orderId,Integer status);

    void updateEndLoading(Long deliveryOrderId);

    void adminModifyOrder(AdminChangeOrderVo adminChangeOrderVo);

    OrderVo selectOrderMessageBydeliveryOrderId(Long deliveryOrderId);
}
