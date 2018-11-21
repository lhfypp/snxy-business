package com.snxy.business.dao.mapper;

import com.snxy.business.domain.Gate;

import java.util.List;

public interface GateMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Gate record);

    int insertSelective(Gate record);

    Gate selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Gate record);

    int updateByPrimaryKey(Gate record);

    List<Gate> selectAllGateGps();
}