package com.snxy.business.service;

import com.snxy.business.domain.Vehicle;
import com.snxy.business.service.vo.CarVO;

import java.util.List;

public interface VehicleService {
    List<CarVO> selectByDriverId(Long driverOnlineUserId);

    String saveVehicle(Vehicle vehicle);

    List<Vehicle> vehicleList(Long dirverInfoId);

    Vehicle selectByCarNo(String driverPlateNumber);
}
