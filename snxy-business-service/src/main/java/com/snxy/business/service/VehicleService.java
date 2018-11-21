package com.snxy.business.service;

import com.snxy.business.domain.Vehicle;
import java.util.List;

public interface VehicleService {
    Vehicle selectByDriverId(Long driverOnlineUserId);

    String saveVehicle(Vehicle vehicle);

    List<Vehicle> vehicleList(Long dirverInfoId);

    Vehicle selectTonnageByDriverNum(String driverPlateNumber);

}
