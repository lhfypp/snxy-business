package com.snxy.business.biz.impl;

import com.snxy.business.dao.mapper.VehicleGpsRecordMapper;
import com.snxy.business.domain.GPSLocation;
import com.snxy.business.domain.VehicleGpsRecord;
import com.snxy.business.service.VehicleGpsRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class VehicleGpsRecordServiceImpl implements VehicleGpsRecordService {

    @Resource
    private VehicleGpsRecordMapper vehicleGpsRecordMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertLocationGPS(VehicleGpsRecord vehicleGpsRecord, Long userId) {
        vehicleGpsRecordMapper.insert(vehicleGpsRecord);
    }

    @Override
    public List<GPSLocation> selectLocationGPS(Long deliveryOrderId) {
        List<GPSLocation> gpsLocations = vehicleGpsRecordMapper.selectByDeviveryOrderId(deliveryOrderId);
        return gpsLocations;
    }
}
