package com.snxy.business.service;

import com.snxy.business.domain.DriverPicture;
import com.snxy.business.service.vo.VehicleInfoVO;

import java.util.List;

public interface DirverInfoService {
    void saveDriverInfo(DriverPicture driverPicture);
    List<VehicleInfoVO> searchVehicleInfo(long driverId);
}
