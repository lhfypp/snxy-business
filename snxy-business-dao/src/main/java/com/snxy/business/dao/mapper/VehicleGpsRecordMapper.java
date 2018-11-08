package com.snxy.business.dao.mapper;

import com.snxy.business.domain.VehicleGpsRecord;

import com.snxy.business.domain.VehicleGpsRecord;

import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;


public interface VehicleGpsRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(VehicleGpsRecord record);

    int insertSelective(VehicleGpsRecord record);

    VehicleGpsRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(VehicleGpsRecord record);

    int updateByPrimaryKey(VehicleGpsRecord record);

    void uploadLocation(@Param("onlineUserId") Long onlineUserId, @Param("deliveryOrderId") Long deliveryOrderId,@Param("longitude") String longitude, @Param("latitude") String latitude, @Param("time") Date time);

    List<VehicleGpsRecord> selectVehicleGpsRecordByDeliveryOrderId(Long deliveryOrderId);
}