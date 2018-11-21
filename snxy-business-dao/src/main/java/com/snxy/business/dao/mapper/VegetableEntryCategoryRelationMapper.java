package com.snxy.business.dao.mapper;

import com.snxy.business.domain.VegetableEntryCategoryRelation;

public interface VegetableEntryCategoryRelationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(VegetableEntryCategoryRelation record);

    int insertSelective(VegetableEntryCategoryRelation record);

    VegetableEntryCategoryRelation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(VegetableEntryCategoryRelation record);

    int updateByPrimaryKey(VegetableEntryCategoryRelation record);
}