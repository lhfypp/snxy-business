package com.snxy.business.service;

import com.github.pagehelper.PageInfo;
import com.snxy.business.domain.*;

import java.util.List;

public interface DeliveryOrderService {
    void createDeliveryOrder(DeliveryOrder deliveryOrder, VegetableDeliveryRelation vegetableDeliveryRelation, VegetableCertificate vegetableCertificate, VegetableImage vegetableImage);
    List<BillInfo>  searchDeliveryOrder( String orderStatus, String searchName);
    BillInfoDetail searchDeliverOrderinfo(Long deliveryOrderId);
    PageInfo<BillInfo> searchDeliveryOrderByPage(String orderStatus, String searchName);
    void cancelOrder(Long orderId);
}
