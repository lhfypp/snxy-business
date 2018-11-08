package com.snxy.business.biz.impl;

import com.snxy.business.dao.mapper.VehicleMapper;
import com.snxy.business.domain.Vehicle;
import com.snxy.business.service.VehicleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class VehicleServiceImpl implements VehicleService {

    @Resource
    private VehicleMapper vehicleMapper;

    //保存行驶证
    @Override
    public void saveVehicle(Vehicle vehicle) {
        vehicle.setGmtCreate(new Date());
        vehicleMapper.insert(vehicle);
    }

    //行驶证列表展示
    @Override
    public List<Vehicle> vehicleList(Long dirverInfoId) {
        return vehicleMapper.selectByKey(dirverInfoId);
    }
}
