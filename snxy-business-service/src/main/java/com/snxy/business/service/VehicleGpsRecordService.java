package com.snxy.business.service;

import com.snxy.business.domain.VehicleGpsRecord;

import java.util.Date;
import java.util.List;

public interface VehicleGpsRecordService {
    void uploadLocation(Long systemUserId, Long deliveryOrderId, String longitude, String latitude, Date time);
    List<VehicleGpsRecord> selectVehicleGpsRecordByDeliveryOrderId(Long deliveryOrderId);

}
