package com.snxy.business.dao.mapper;

import com.snxy.business.domain.TruckType;

import java.util.List;

public interface TruckTypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TruckType record);

    int insertSelective(TruckType record);

    TruckType selectByPrimaryKey(Long id);

    List<TruckType> selectAll();

    int updateByPrimaryKeySelective(TruckType record);

    int updateByPrimaryKey(TruckType record);
}