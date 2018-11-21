package com.snxy.business.dao.mapper;

import com.snxy.business.domain.Vehicle;

import java.util.List;

import com.snxy.business.domain.Vehicle;
import org.apache.ibatis.annotations.Param;


import java.util.Date;

public interface VehicleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Vehicle record);

    int insertSelective(Vehicle record);

    Vehicle selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Vehicle record);

    int updateByPrimaryKey(Vehicle record);

    List<Vehicle> selectByDriverId(Long driverOnlineUserId);
    List<Vehicle> selectByKey(Long dirverInfoId);

    Vehicle selectTonnageByDriverNum(@Param("driverPlateNumber") String driverPlateNumber);

    Vehicle selectByCarNo(String driverPlateNumber);
}