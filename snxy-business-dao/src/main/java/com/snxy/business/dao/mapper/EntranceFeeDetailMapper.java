package com.snxy.business.dao.mapper;

public interface EntranceFeeDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(EntranceFeeDetail record);

    int insertSelective(EntranceFeeDetail record);

    EntranceFeeDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(EntranceFeeDetail record);

    int updateByPrimaryKey(EntranceFeeDetail record);
}