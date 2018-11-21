package com.snxy.business.dao.mapper;

import com.snxy.business.domain.Vegetable;

public interface VegetableMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Vegetable record);

    int insertSelective(Vegetable record);

    Vegetable selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Vegetable record);

    int updateByPrimaryKey(Vegetable record);

    Long selectByVegetableId(Long entranceFeeCategoryId);
}