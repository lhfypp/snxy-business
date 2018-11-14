package com.snxy.business.biz.impl;

import com.snxy.business.dao.mapper.VehicleMapper;
import com.snxy.business.domain.Vehicle;
import com.snxy.business.service.VehicleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class VehicleServiceImpl implements VehicleService {
    @Resource
    private VehicleMapper vehicleMapper;

    @Override
    public Vehicle selectByDriverId(Long driverOnlineUserId) {
        Vehicle vehicle = vehicleMapper.selectByDriverId(driverOnlineUserId);
        return vehicle;
    }
}
