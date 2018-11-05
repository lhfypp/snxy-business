package com.snxy.business.dao.mapper;

import com.snxy.business.domain.TruckType;

public interface TruckTypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TruckType record);

    int insertSelective(TruckType record);

    TruckType selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TruckType record);

    int updateByPrimaryKey(TruckType record);
}