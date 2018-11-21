package com.snxy.business.service;

import com.snxy.business.domain.Vehicle;
import com.snxy.business.service.vo.CarVO;
import com.snxy.business.service.vo.VehicleVO;

import java.util.List;

public interface VehicleService {
    List<CarVO> selectByDriverId(Long driverOnlineUserId);

    String saveVehicle(VehicleVO vehicleVO);

    List<Vehicle> vehicleList(Long dirverInfoId);

    Vehicle selectTonnageByDriverNum(String driverPlateNumber);

    Vehicle selectByCarNo(String driverPlateNumber);
}
