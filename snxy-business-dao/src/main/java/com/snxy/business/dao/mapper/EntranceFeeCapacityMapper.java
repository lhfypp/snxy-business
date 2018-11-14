package com.snxy.business.dao.mapper;

import com.snxy.business.domain.EntranceFeeCapacity;

import java.util.List;

import com.snxy.business.domain.EntranceFeeCapacity;

public interface EntranceFeeCapacityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(EntranceFeeCapacity record);

    int insertSelective(EntranceFeeCapacity record);

    EntranceFeeCapacity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(EntranceFeeCapacity record);

    int updateByPrimaryKey(EntranceFeeCapacity record);

    List<EntranceFeeCapacity> selectcarList();
}