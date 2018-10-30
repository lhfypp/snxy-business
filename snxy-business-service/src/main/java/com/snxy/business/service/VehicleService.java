package com.snxy.business.service;

import com.snxy.business.domain.Vehicle;
import com.snxy.business.domain.VehiclePartInfo;

import java.util.List;

public interface VehicleService {
    Vehicle searchVehicleByVehicleId(long id);
    List<VehiclePartInfo> searchVehiclePartInfo( long onlineUserId);
    List<Vehicle>searchVehicleList(long driver_info_id);
}
