package com.snxy.business.biz.impl;

import com.snxy.business.dao.mapper.VehicleMapper;
import com.snxy.business.domain.Vehicle;
import com.snxy.business.service.VehicleService;
import com.snxy.common.exception.BizException;
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

    @Override
    public Vehicle selectByDriverId(Long driverOnlineUserId) {
        Vehicle vehicle = vehicleMapper.selectByDriverId(driverOnlineUserId);
        return vehicle;
    }

    //保存行驶证
    @Override
    public String saveVehicle(Vehicle vehicle) {
        vehicle.setGmtCreate(new Date());
        int result =vehicleMapper.insert(vehicle);
        if (result!=1){
            throw new BizException("保存失败，请重新再试");
        }
        return "保存成功";
    }

    //行驶证列表展示
    @Override
    public List<Vehicle> vehicleList(Long dirverInfoId) {
        return vehicleMapper.selectByKey(dirverInfoId);
    }
}
