package com.snxy.business.service;

import com.snxy.business.domain.Vehicle;

public interface VehicleService {
    Vehicle selectByDriverId(Long driverOnlineUserId);
}
