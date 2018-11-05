package com.snxy.business.dao.mapper;

public interface EntranceFeeCategoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(EntranceFeeCategory record);

    int insertSelective(EntranceFeeCategory record);

    EntranceFeeCategory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(EntranceFeeCategory record);

    int updateByPrimaryKey(EntranceFeeCategory record);
}