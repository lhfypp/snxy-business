package com.snxy.business.service;

import com.snxy.business.domain.Vehicle;

import java.util.List;

public interface VehicleService {

    String saveVehicle(Vehicle vehicle);

    List<Vehicle> vehicleList(Long dirverInfoId);
}
