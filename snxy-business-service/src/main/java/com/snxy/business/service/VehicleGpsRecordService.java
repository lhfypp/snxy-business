package com.snxy.business.service;

import com.snxy.business.domain.GPSLocation;
import com.snxy.business.service.vo.VehicleGpsVo;

import java.util.List;

public interface VehicleGpsRecordService {
    void insertLocationGPS(VehicleGpsVo vehicleGpsVo);

    List<GPSLocation> selectLocationGPS(Long deliveryOrderId);
}
