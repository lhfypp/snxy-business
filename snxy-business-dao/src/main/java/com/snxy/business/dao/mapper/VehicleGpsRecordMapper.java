package com.snxy.business.dao.mapper;

import com.snxy.business.domain.VehicleGpsRecord;

public interface VehicleGpsRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(VehicleGpsRecord record);

    int insertSelective(VehicleGpsRecord record);

    VehicleGpsRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(VehicleGpsRecord record);

    int updateByPrimaryKey(VehicleGpsRecord record);
}