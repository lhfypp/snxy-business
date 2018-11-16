package com.snxy.business.dao.mapper;

import com.snxy.business.domain.Vegetable;

import java.util.List;

public interface VegetableMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Vegetable record);

    int insertSelective(Vegetable record);

    Vegetable selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Vegetable record);

    int updateByPrimaryKey(Vegetable record);

    List<Vegetable> selectAll();

    List<Vegetable> selectByCategoryId(Long vegetableCategoryId);

    List<Vegetable> selectByVegetableName(String vegetableName);
}