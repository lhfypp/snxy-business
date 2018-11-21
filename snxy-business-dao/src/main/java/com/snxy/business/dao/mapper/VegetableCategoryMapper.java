package com.snxy.business.dao.mapper;

import com.snxy.business.domain.VegetableCategory;

public interface VegetableCategoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(VegetableCategory record);

    int insertSelective(VegetableCategory record);

    VegetableCategory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(VegetableCategory record);

    int updateByPrimaryKey(VegetableCategory record);
}