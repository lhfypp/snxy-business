package com.snxy.business.dao.mapper;

import com.snxy.business.domain.LoadType;

public interface LoadTypeMapper {
    int deleteByPrimaryKey(Byte id);

    int insert(LoadType record);

    int insertSelective(LoadType record);

    LoadType selectByPrimaryKey(Byte id);

    int updateByPrimaryKeySelective(LoadType record);

    int updateByPrimaryKey(LoadType record);
}