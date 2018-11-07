package com.snxy.business.dao.mapper;

import com.snxy.business.domain.Vehicle;

import java.util.Date;

public interface VehicleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Vehicle record);

    int insertSelective(Vehicle record);

    Vehicle selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Vehicle record);

    int updateByPrimaryKey(Vehicle record);

}