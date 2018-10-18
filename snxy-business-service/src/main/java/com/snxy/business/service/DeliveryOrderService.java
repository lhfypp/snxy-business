package com.snxy.business.service;

import com.snxy.business.domain.*;

import java.util.List;

public interface DeliveryOrderService {
    void createDeliveryOrder(DeliveryOrder deliveryOrder, VegetableDeliveryRelation vegetableDeliveryRelation, VegetableCertificate vegetableCertificate, VegetableImage vegetableImage);
    List<BillInfo>  searchDeliveryOrder(Long useId, String orderStatus, String serchName);
}
