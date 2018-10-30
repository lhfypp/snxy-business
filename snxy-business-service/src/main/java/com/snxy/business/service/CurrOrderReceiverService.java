package com.snxy.business.service;

import com.snxy.business.domain.DriverPartInfo;
import com.snxy.business.domain.InsertCurrentOrder;
import com.snxy.business.domain.CurrOrderReceiver;


import java.util.List;


public interface CurrOrderReceiverService {
    DriverPartInfo selectDriverPartInfo(long orderId);
    void tranferOrder( long orderId, String driveMobile ,  String driverName);
    void updateCurrOrderReceiver(String OrderId,  String vehicleId) ;
    List selectOrderIdByDriverMobile(Long driverMobile);
    void changeDriver( long orderId,  String driverName, String drivePhone);
    CurrOrderReceiver selectDriverMessageByOrderId(Long deliveryOrderId);
    void updateByAgent(DriverPartInfo driverPartInfo);
}
