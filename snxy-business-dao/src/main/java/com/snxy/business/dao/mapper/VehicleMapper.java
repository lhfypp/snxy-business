package com.snxy.business.dao.mapper;

import com.snxy.business.domain.Vehicle;
import com.snxy.business.domain.VehiclePartInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VehicleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Vehicle record);

    int insertSelective(Vehicle record);

    Vehicle selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Vehicle record);

    int updateByPrimaryKey(Vehicle record);
    //司机查看车辆信息
    List<VehiclePartInfo> searchVehiclePartInfo(@Param("onlineUserId") long onlineUserId);
}