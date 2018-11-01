package com.snxy.business.biz.impl;

import com.snxy.business.dao.mapper.VehicleMapper;
import com.snxy.business.domain.TruckType;
import com.snxy.business.domain.Vehicle;
import com.snxy.business.domain.VehiclePartInfo;
import com.snxy.business.service.TruckTypeService;
import com.snxy.business.service.VehicleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class VehicleServiceImpl implements VehicleService {
    @Resource
    private VehicleMapper vehicleMapper;
    @Resource
    private TruckTypeService truckTypeService;
    @Override
    public Vehicle searchVehicleByVehicleId(long id) {
        Vehicle vehicle= vehicleMapper.selectByPrimaryKey(id);
        //查询出车类型
        TruckType truckType=new TruckType();
        if(vehicle!=null&&vehicle.getTruckTypeId()!=null){
            truckType= truckTypeService.selectTruckTypeByTruckId(vehicle.getTruckTypeId());
            vehicle.setTonnage(truckType.getTypeDesc());
        }

        return vehicle;
    }

    @Override
    public List<VehiclePartInfo> searchVehiclePartInfo(long onlineUserId) {
        return vehicleMapper.searchVehiclePartInfo(onlineUserId);
    }
}
