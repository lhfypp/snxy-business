package com.snxy.business.service;

import com.snxy.business.domain.*;
import com.snxy.common.util.PageInfo;

import java.util.List;


public interface DeliveryOrderService {

    void createDeliveryOrder(DeliveryOrderVo deliveryOrderVo);

    String getOrderNo();

    List<BillInfo> selectDriverOrder(Long userId);

    DriverOrderInfo selectOrderByOrderId(Long orderId);

    void createDeliveryOrder(DeliveryOrder deliveryOrder, VegetableDeliveryRelation vegetableDeliveryRelation, VegetableCertificate vegetableCertificate, VegetableImage vegetableImage);
    List<BillInfo>  searchDeliveryOrder( String orderStatus, String searchName);
    BillInfoDetail searchDeliverOrderinfo(Long deliveryOrderId);
    PageInfo<BillInfo> searchDeliveryOrderByPage(String orderStatus, String searchName);
    void cancelOrder(Long orderId);

}
