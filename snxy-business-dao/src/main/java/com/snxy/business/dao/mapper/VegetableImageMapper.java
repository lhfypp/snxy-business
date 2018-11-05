package com.snxy.business.dao.mapper;

public interface VegetableImageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(VegetableImage record);

    int insertSelective(VegetableImage record);

    VegetableImage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(VegetableImage record);

    int updateByPrimaryKey(VegetableImage record);
}