package com.snxy.business.service;

import com.snxy.business.domain.GPSLocation;
import com.snxy.business.domain.VehicleGpsRecord;

import java.util.List;

public interface VehicleGpsRecordService {
    void insertLocationGPS(VehicleGpsRecord vehicleGpsRecord, Long userId);

    List<GPSLocation> selectLocationGPS(Long deliveryOrderId);
}
