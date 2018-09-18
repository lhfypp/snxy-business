package com.snxy.business.dao.mapper;

import com.snxy.business.domain.VegetablePrice;

public interface VegetablePriceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(VegetablePrice record);

    int insertSelective(VegetablePrice record);

    VegetablePrice selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(VegetablePrice record);

    int updateByPrimaryKey(VegetablePrice record);
}