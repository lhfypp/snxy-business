package com.snxy.business.dao.mapper;

public interface EntranceFeeCapacityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(EntranceFeeCapacity record);

    int insertSelective(EntranceFeeCapacity record);

    EntranceFeeCapacity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(EntranceFeeCapacity record);

    int updateByPrimaryKey(EntranceFeeCapacity record);
}