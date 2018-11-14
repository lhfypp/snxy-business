package com.snxy.business.dao.mapper;

import com.snxy.business.domain.Door;

import java.util.List;

public interface DoorMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Door record);

    int insertSelective(Door record);

    Door selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Door record);

    int updateByPrimaryKey(Door record);

    List<Door> selectAllDoorGps();
}