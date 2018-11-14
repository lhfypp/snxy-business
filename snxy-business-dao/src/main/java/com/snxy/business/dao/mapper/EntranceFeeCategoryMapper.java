package com.snxy.business.dao.mapper;

import com.snxy.business.domain.EntranceFeeCategory;

import java.util.List;

public interface EntranceFeeCategoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(EntranceFeeCategory record);

    int insertSelective(EntranceFeeCategory record);

    EntranceFeeCategory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(EntranceFeeCategory record);

    int updateByPrimaryKey(EntranceFeeCategory record);

    List<EntranceFeeCategory> selectAll();

    List<EntranceFeeCategory> selectByCategoryName(String categoryName);
}