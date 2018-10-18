package com.snxy.business.service;

import com.snxy.business.domain.*;


public interface DeliveryOrderService {

    void createDeliveryOrder(DeliveryOrder deliveryOrder, VegetableDeliveryRelation vegetableDeliveryRelation, VegetableCertificate vegetableCertificate, VegetableImage vegetableImage);


}
